package com.inditex.ecom.appwebcustomerloyaltyengine.mdb.api.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.internal.Pair;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Autorizacion;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.LineaPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.OperacionPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.EstadoIncorrectoException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.NumeroSerieException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.mdb.api.GestionPedidosMQService;
import com.inditex.ecom.appwebcustomerloyaltyengine.mdb.dao.GestionPedidosMQDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.EstadoDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Secuencias;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoLineaPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoOperacionDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoOperacionRecurso;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoValorRecurso;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.Errores;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.MensajesDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.Warnings;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.CalculoImportesDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionClientesDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionDescuentosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionPedidosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionRecursosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DetalleDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyOperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyOperacionRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.sequence.dao.SequencesDao;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion de servicioMQ de GestionPedidosMQService: nos permitira realizar cualquier operacion
 * sobre descuentos en modo offline.
 */
@Service("gestionPedidosMQService")
@Transactional(propagation = Propagation.REQUIRED)
public class GestionPedidosMQServiceImpl implements GestionPedidosMQService {

    /** The Constant LOG. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestionPedidosMQServiceImpl.class);

    /** The id instalacion. */
    private @Value("${ID_INSTALACION}")
    Integer idInstalacion;

    /** The gestion descuentos dao. */
    @Autowired
    private GestionPedidosMQDao gestionPedidosMQDao;

    /** The gestion descuentos dao. */
    @Autowired
    private GestionDescuentosDao gestionDescuentosDao;

    /** The gestion clientes dao. */
    @Autowired
    private GestionClientesDao gestionClientesDao;

    /** The gestion tarjetas dao. */
    @Autowired
    private GestionRecursosDao gestionRecursosDao;

    /** The gestion pedidos dao. */
    @Autowired
    private GestionPedidosDao gestionPedidosDao;

    /** The calculo importes dao. */
    @Autowired
    private CalculoImportesDao calculoImportesDao;

    /** The sequences dao. */
    @Autowired
    private SequencesDao sequencesDao;

    /** The maximo numero reintentos. */
    private static int MAXIMO_NUMERO_REINTENTOS = 3;

    static {
        InitialContext ic;

        try {
            ic = new InitialContext();
            MAXIMO_NUMERO_REINTENTOS = (Integer) ic.lookup("java:comp/env/maximoNumeroReintentos");
        } catch (NamingException e) {
            LOGGER.error(String.format(Errores.ERROR_MAXIMO_NUMERO_REINTENTOS, Integer.toString(MAXIMO_NUMERO_REINTENTOS)));
        }
    }

    /******************************************************************************************************************************
     * 
     * ACCIONES DE PEDIDOS MQ
     * 
     ******************************************************************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    public Autorizacion autorizarDescuentos(String idDescuento, String idPedido, String UUID) throws TimeoutException {
        LOGGER.trace("GestionPedidosMQServiceImpl.autorizarDescuentos");

        Autorizacion autorizacion = new Autorizacion();
        int contadorReintentos = 0;

        LOGGER.trace(String.format("%s - Realizamos la autorizacion del descuento %s, con el pedido %s.", UUID, idDescuento, idPedido));

        do {
            // Obtenemos el DESCUENTO
            DescuentoDto descuento = gestionDescuentosDao.obtenerDescuento(new PrimaryKeyDescuentoDto(idDescuento));

            // Comprobamos que el descuento esta dado de alta y en estado PENDIENTE
            if (descuento == null || descuento.getIdEstadoDescuento().compareTo(EstadoDescuento.PENDIENTE.getValue()) != 0) {
                autorizacion.setCodResultado(Constantes.COD_RESULTADO_DOS);
                autorizacion.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_DOS);

                return autorizacion;
            }

            // Comprobamos que el descuento no este en estado PENDIENTE ni tampoco en EXPIRADO
            if (descuento.getIdEstadoDescuento().compareTo(EstadoDescuento.PENDIENTE.getValue()) != 0 || descuento.getIdEstadoDescuento().compareTo(EstadoDescuento.EXPIRADO.getValue()) == 0) {
                autorizacion.setCodResultado(Constantes.COD_RESULTADO_CERO);
                autorizacion.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_CERO);

                return autorizacion;
            }

            // Comprobamos que el descuento esta vigente
            if (descuento.getFechaHoraValidezDescuento().before(new Timestamp(Calendar.getInstance().getTimeInMillis()))) {
                autorizacion.setCodResultado(Constantes.COD_RESULTADO_NUEVE);
                autorizacion.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_NUEVE);

                return autorizacion;
            }

            // Comprobamos que el idPedido esta asociado al idDescuento
            if (!descuento.getCodigoPedidoExterno().equals(idPedido)) {
                autorizacion.setCodResultado(Constantes.COD_RESULTADO_OCHO);
                autorizacion.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_OCHO);

                return autorizacion;
            }

            LOGGER.trace(String.format("%s - Recuperamos el cliente.", UUID));

            // Obtenemos el CLIENTE
            ClienteDto cliente = gestionClientesDao.obtenerCliente(descuento.getIdCliente());

            // Obtenemos el DETALLE_DESCUENTO
            DetalleDescuentoDto detalleDescuento = gestionDescuentosDao.obtenerDetalleDescuento(descuento.getIdDetalleDescuento());

            // Obtenemos los RECURSOS
            List<RecursoDto> recursoList = gestionRecursosDao.obtenerRecursosEstado(cliente.getIdCliente());

            // Calculamos el saldo de puntos de los recursos
            BigDecimal puntos = BigDecimal.ZERO;
            for (RecursoDto recurso : recursoList) {
                puntos = puntos.add(recurso.getValorDisponibleRecurso());
            }

            if (puntos.compareTo(detalleDescuento.getConsumoPuntos()) < 0) {
                autorizacion.setCodResultado(Constantes.COD_RESULTADO_UNO);
                autorizacion.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_UNO);

                return autorizacion;
            }

            try {
                LOGGER.trace(String.format("%s - Generamos las autorizaciones.", UUID));

                // Generamos las autorizaciones (OPERACION_DESCUENTO Y OPERACIONES_RECURSOS)
                Calendar fechaActual = Calendar.getInstance();
                OperacionDescuentoDto operDescuento = generarOperacionDescuento(descuento, TipoOperacionDescuento.AUTORIZACION.getValue(), fechaActual, detalleDescuento.getImporteDescuento());

                List<OperacionRecursoDto> operacionesRecursoList = new ArrayList<OperacionRecursoDto>();
                for (RecursoDto recurso : recursoList) {
                    operacionesRecursoList.add(generarOperacionRecurso(recurso, operDescuento.getIdOperacionDescuento(), TipoOperacionDescuento.AUTORIZACION.toString(), TipoOperacionRecurso.AUTORIZACION.getValue(), fechaActual, detalleDescuento.getIncrementoPuntos(), detalleDescuento.getImporteDescuento()));
                }

                LOGGER.trace(String.format("%s - Persistimos.", UUID));

                // Persistimos
                PrimaryKeyOperacionDescuentoDto pkod = gestionPedidosDao.autorizacionDescuento(operDescuento, operacionesRecursoList, cliente, descuento, recursoList, detalleDescuento, UUID);
                autorizacion.setIdOperacionDescuento(String.format(Mensajes.INSTALACION, pkod.getIdOperacionDescuento(), pkod.getIdInstalacion()));

                LOGGER.trace(String.format("%s - ID_OPERACION_DESCUENTO %s , PARA EL ID_DESCUENTO %s Y PARA EL PEDIDO %s.", UUID, autorizacion.getIdOperacionDescuento().toString(), idDescuento, idPedido));

                autorizacion.setCodResultado(Constantes.COD_RESULTADO_CERO);
                autorizacion.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_CERO);
            } catch (NumeroSerieException e) {
                contadorReintentos++;
            }
        } while (contadorReintentos < MAXIMO_NUMERO_REINTENTOS && autorizacion.getCodResultado() == null);

        if (contadorReintentos == MAXIMO_NUMERO_REINTENTOS) {
            throw new TimeoutException(String.format(Errores.NUMERO_MAXIMO_REINTENTOS, Integer.toString(MAXIMO_NUMERO_REINTENTOS)));
        }

        return autorizacion;
    }

    /**
     * {@inheritDoc}
     */
    public OperacionPedido capturarDescuento(OperacionPedido operacionPedido, String UUID) throws TimeoutException, NumeroSerieException, EstadoIncorrectoException {
        LOGGER.trace("GestionPedidosMQServiceImpl.capturarDescuento");

        int contadorReintentos = 0;
        OperacionPedido operacionPedidoCaptura = new OperacionPedido();
        Boolean ok = false;

        operacionPedidoCaptura.setTipoOperacion(operacionPedido.getTipoOperacion());
        operacionPedidoCaptura.setCodOperacionExterno(operacionPedido.getCodOperacionExterno());

        do {
            // Obtenemos el DESCUENTO
            DescuentoDto descuento = gestionDescuentosDao.obtenerDescuentoOperacion(operacionPedido.getCodOperacionExterno());

            if (descuento == null) {
                throw new EstadoIncorrectoException(String.format(MensajesDescuento.DESCUENTO_NO_DADO_ALTA));
            }

            // Comprobamos que el descuento esta en estado AUTORIZADO o CAPTURADO
            if (descuento.getIdEstadoDescuento().compareTo(EstadoDescuento.AUTORIZADO.getValue()) != 0 && descuento.getIdEstadoDescuento().compareTo(EstadoDescuento.CAPTURADO.getValue()) != 0) {
                throw new EstadoIncorrectoException(String.format(Errores.DESCUENTO_NO_AUTORIZADO_CAPTURADO));
            }

            // Calculamos el pendiente de capturar
            Double autorizado = calculoImportesDao.calcularAutorizado(descuento.getIdDescuento()).doubleValue();
            Double capturado = calculoImportesDao.calcularCapturado(descuento.getIdDescuento()).doubleValue();
            Double pendienteCapturar = autorizado - capturado;

            // El pendiente de capturar es siempre menor o igual en valor absoluto al autorizado
            if (Math.abs(autorizado - capturado) > autorizado) {
                throw new EstadoIncorrectoException(String.format(Errores.PENDIENTE_CAPTURAR_MAYOR_AUTORIZADO, descuento.getCodigoPedidoExterno(), String.format(Mensajes.INSTALACION, descuento.getIdDescuento().getIdDescuento(), descuento.getIdDescuento().getIdInstalacion())));
            }

            // Si pendienteCapturar es cero, generamos warning y salimos
            if (pendienteCapturar.compareTo(BigDecimal.ZERO.doubleValue()) == 0) {
                LOGGER.warn(String.format(Warnings.PENDIENTE_CAPTURAR_CERO, UUID));
                operacionPedidoCaptura.setImporteOperacion(new BigDecimal(pendienteCapturar));

                return operacionPedidoCaptura;
            }

            // Si pendienteCapturar es negativo, recortamos, generamos warning y salimos
            if (pendienteCapturar.compareTo(operacionPedido.getImporteOperacion().doubleValue()) < 0) {
                LOGGER.warn(String.format(Warnings.NO_SE_PUEDE_CUBRIR_CAPTURA, UUID));

                operacionPedidoCaptura.setImporteOperacion((new BigDecimal(pendienteCapturar)).setScale(2, RoundingMode.HALF_DOWN));
                return operacionPedidoCaptura;
            }

            // Obtenemos los objetos CLIENTE
            ClienteDto cliente = gestionClientesDao.obtenerCliente(descuento.getIdCliente());

            // Obtenemos los RECURSOS
            List<RecursoDto> recursoList = gestionRecursosDao.obtenerRecursosEstado(cliente.getIdCliente());

            // Calculamos la parte proporcional de puntos
            Pair<BigDecimal, BigDecimal> par = calculoPuntos(operacionPedido, descuento);
            BigDecimal puntos = par.first;
            BigDecimal importe = par.second;

            try {
                // Generamos OPERACION_DESCUENTO y OPERACION_TARJETA
                Calendar fechaActual = Calendar.getInstance();

                OperacionDescuentoDto operacionDescuento = generarOperacionDescuento(descuento, TipoOperacionDescuento.CAPTURA.getValue(), fechaActual, importe);

                List<OperacionRecursoDto> operacionRecursoList = new ArrayList<OperacionRecursoDto>();
                for (RecursoDto recurso : recursoList) {
                    operacionRecursoList.add(generarOperacionRecurso(recurso, operacionDescuento.getIdOperacionDescuento(), TipoOperacionDescuento.CAPTURA.toString(), TipoOperacionRecurso.CAPTURA.getValue(), fechaActual, operacionPedido.getImporteOperacion(), importe));
                }
                
                DetalleDescuentoDto detalle = gestionDescuentosDao.obtenerDetalleDescuento(descuento.getIdDetalleDescuento());
                
                List<LineaDescuentoDto> lineas = null;
                
                if (detalle.getIncrementoPuntos().compareTo(operacionPedido.getImporteOperacion()) != 0) {
                    lineas = generarLineasDescuento(operacionPedido.getLineasPedidoAfectadas());
                }

                // Persistimos las entidades
                ok = gestionPedidosMQDao.persistirCaptura(cliente, descuento, recursoList, operacionDescuento, operacionRecursoList, lineas, puntos, UUID, EstadoDescuento.CAPTURADO.getValue());

            } catch (Exception e) {
                contadorReintentos++;
            }

            operacionPedidoCaptura.setLineasPedidoAfectadas(obtenerLineasPedidoAfectadas(descuento.getIdDescuento().toString()));
        } while (contadorReintentos < MAXIMO_NUMERO_REINTENTOS && ok != true);

        if (contadorReintentos == MAXIMO_NUMERO_REINTENTOS) {
            throw new TimeoutException(String.format(Errores.NUMERO_MAXIMO_REINTENTOS, Integer.toString(MAXIMO_NUMERO_REINTENTOS)));
        }

        // Rellenamos el objeto de retorno
        operacionPedidoCaptura.setImporteOperacion(operacionPedido.getImporteOperacion());
        operacionPedidoCaptura.setLineasPedidoAfectadas(generarLineasPedido(operacionPedido.getLineasPedidoAfectadas()));

        return operacionPedidoCaptura;
    }

    /**
     * {@inheritDoc}
     */
    public OperacionPedido devolverDescuento(OperacionPedido operacionPedido, String UUID) throws TimeoutException, NumeroSerieException, EstadoIncorrectoException {
        LOGGER.trace("GestionPedidosMQServiceImpl.devolverDescuento");

        int contadorReintentos = 0;
        OperacionPedido operacionPedidoDescuento = new OperacionPedido();
        Boolean ok = false;

        operacionPedidoDescuento.setTipoOperacion(operacionPedido.getTipoOperacion());
        operacionPedidoDescuento.setCodOperacionExterno(operacionPedido.getCodOperacionExterno());

        do {
            // Obtenemos el DESCUENTO
            DescuentoDto descuento = gestionDescuentosDao.obtenerDescuentoOperacion(operacionPedido.getCodOperacionExterno());

            // Comprobamos que el descuento exista
            if (descuento == null) {
                throw new EstadoIncorrectoException(String.format(MensajesDescuento.DESCUENTO_NO_DADO_ALTA));
            }

            // Comprobamos que el descuento esta en estado CAPTURADO
            if (descuento.getIdEstadoDescuento().compareTo(EstadoDescuento.CAPTURADO.getValue()) != 0) {
                throw new EstadoIncorrectoException(String.format(MensajesDescuento.DESCUENTO_NO_CAPTURADO));
            }

            // Calculamos el pendiente a devolver
            BigDecimal pendienteDevolver = calculoImportesDao.calcularPendienteDevolver(descuento.getIdDescuento());

            // Si pendienteDevolver es negativo, recortamos y generamos un warning
            if (pendienteDevolver.compareTo(operacionPedido.getImporteOperacion()) < 0) {
                LOGGER.warn(String.format(Warnings.NO_SE_PUEDE_CUBRIR_DEVOLUCION, UUID));

                operacionPedidoDescuento.setImporteOperacion(operacionPedido.getImporteOperacion());
                return operacionPedidoDescuento;
            }

            // Obtenemos los objetos CLIENTE
            ClienteDto cliente = gestionClientesDao.obtenerCliente(descuento.getIdCliente());

            // Obtenemos los RECURSOS
            List<RecursoDto> tarjetaList = gestionRecursosDao.obtenerRecursosEstado(cliente.getIdCliente());

            // Calculamos la parte proporcional de puntos
            Pair<BigDecimal, BigDecimal> par = calculoPuntos(operacionPedido, descuento);
            BigDecimal puntos = par.first;
            BigDecimal importe = par.second;

            try {
                // Generamos OPERACION_DESCUENTO y OPERACION_TARJETA
                Calendar fechaActual = Calendar.getInstance();
                OperacionDescuentoDto operacionDescuento = generarOperacionDescuento(descuento, TipoOperacionDescuento.DEVOLUCION.getValue(), fechaActual, importe);

                List<OperacionRecursoDto> operacionTarjetaList = new ArrayList<OperacionRecursoDto>();
                for (RecursoDto tarjeta : tarjetaList) {
                    operacionTarjetaList.add(generarOperacionRecurso(tarjeta, operacionDescuento.getIdOperacionDescuento(), TipoOperacionDescuento.DEVOLUCION.toString(), TipoOperacionRecurso.DEVOLUCION.getValue(), fechaActual, operacionPedido.getImporteOperacion(), importe));
                }

                DetalleDescuentoDto detalle = gestionDescuentosDao.obtenerDetalleDescuento(descuento.getIdDetalleDescuento());

                List<LineaDescuentoDto> lineas = null;
                
                if (detalle.getIncrementoPuntos().compareTo(operacionPedido.getImporteOperacion()) != 0) {
                    lineas = generarLineasDescuento(operacionPedido.getLineasPedidoAfectadas());
                }

                // Persistimos las entidades
                ok = gestionPedidosMQDao.persistirCaptura(cliente, descuento, tarjetaList, operacionDescuento, operacionTarjetaList, lineas, puntos, UUID, descuento.getIdEstadoDescuento());

                operacionPedidoDescuento.setImporteOperacion(importe);
            } catch (Exception e) {
                contadorReintentos++;
            }

            operacionPedidoDescuento.setLineasPedidoAfectadas(obtenerLineasPedidoAfectadas(descuento.getIdDescuento().toString()));
        } while (contadorReintentos < MAXIMO_NUMERO_REINTENTOS && ok != true);

        if (contadorReintentos == MAXIMO_NUMERO_REINTENTOS) {
            throw new TimeoutException(String.format(Errores.NUMERO_MAXIMO_REINTENTOS, Integer.toString(MAXIMO_NUMERO_REINTENTOS)));
        }

        // Rellenamos el objeto de retorno
        operacionPedidoDescuento.setImporteOperacion(operacionPedido.getImporteOperacion());
        operacionPedidoDescuento.setLineasPedidoAfectadas(generarLineasPedido(operacionPedido.getLineasPedidoAfectadas()));

        return operacionPedidoDescuento;
    }

    /**
     * {@inheritDoc}
     */
    public void cancelarDescuento(String idPedido, String idDescuento, List<LineaDescuentoDto> lineas, String UUID) throws TimeoutException, EstadoIncorrectoException {
        int contadorReintentos = 0;
        Boolean ok = false;

        LOGGER.trace(String.format("%s - Cancelamos el pedido %s, con el descuento %s.", UUID, idPedido, idDescuento));

        do {
            // Obtenemos el DESCUENTO
            DescuentoDto descuento = gestionDescuentosDao.obtenerDescuento(new PrimaryKeyDescuentoDto(idDescuento));

            // Comprobamos que el descuento exista
            if (descuento == null) {
                throw new EstadoIncorrectoException(String.format(MensajesDescuento.DESCUENTO_NO_DADO_ALTA));
            }

            // Obtenemos el incremento al SALDO_DISPONIBLE que sera pendiente de capturar
            Double autorizado = calculoImportesDao.calcularAutorizado(descuento.getIdDescuento()).doubleValue();
            Double capturado = calculoImportesDao.calcularCapturado(descuento.getIdDescuento()).doubleValue();

            // Comprobamos si el descuento esta AUTORIZADO
            if (descuento.getIdEstadoDescuento().compareTo(EstadoDescuento.AUTORIZADO.getValue()) != 0 && (new BigDecimal(autorizado).subtract(new BigDecimal(capturado)).compareTo(BigDecimal.ZERO) < 0)) {
                throw new EstadoIncorrectoException(String.format(Errores.DESCUENTO_NO_AUTORIZADO));
            }

            // Comprobamos que el idPedido esta asociado al idDescuento
            if (!descuento.getCodigoPedidoExterno().equals(idPedido)) {
                throw new EstadoIncorrectoException(String.format(Errores.DESCUENTO_NO_ASOCIADO_PEDIDO));
            }

            // El pendiente de capturar es siempre menor o igual en valor absoluto al autorizado
            if (Math.abs(autorizado - capturado) > autorizado) {
                throw new EstadoIncorrectoException(String.format(Errores.PENDIENTE_CAPTURAR_MAYOR_AUTORIZADO, idPedido, idDescuento));
            }

            // Obtenemos los objetos CLIENTE
            ClienteDto cliente = gestionClientesDao.obtenerCliente(descuento.getIdCliente());

            // Obtenemos los RECURSOS
            List<RecursoDto> recursoList = gestionRecursosDao.obtenerRecursosEstado(cliente.getIdCliente());

            // Si el cliente esta dado de baja o la tarjeta esta de baja, generamos un warning
            if (cliente.getFechaHoraBaja() != null || recursoList.get(0).getFechaHoraBaja() != null) {
                LOGGER.warn(String.format(Warnings.CLIENTE_DADO_DE_BAJA, cliente.getCodClienteWalletMarca(), recursoList.get(0).getIdRecurso().getIdRecurso(), recursoList.get(0).getIdRecurso().getIdInstalacion()));
            }

            // Calculamos la parte proporcional de puntos
            Pair<BigDecimal, BigDecimal> par = calculoPuntos(lineas, descuento);
            BigDecimal puntos = par.first;
            BigDecimal importe = par.second;

            try {
                LOGGER.trace(String.format("Generamos las autorizaciones para el pedido %s, y para el descuento %s.", idPedido, idDescuento));

                // Generamos OPERACION_DESCUENTO y OPERACION_RECURSO de tipo CANCELACION
                Calendar fechaActual = Calendar.getInstance();

                OperacionDescuentoDto operDescuento = generarOperacionDescuento(descuento, TipoOperacionDescuento.CANCELACION.getValue(), fechaActual, importe);

                List<OperacionRecursoDto> operacionRecursoList = new ArrayList<OperacionRecursoDto>();
                for (RecursoDto recurso : recursoList) {
                    operacionRecursoList.add(generarOperacionRecurso(recurso, operDescuento.getIdOperacionDescuento(), TipoOperacionDescuento.CANCELACION.toString(), TipoOperacionRecurso.CANCELACION.getValue(), fechaActual, puntos, importe));
                }

                // Persistimos las entidades
                LOGGER.trace(String.format("Persistimos la captura"));
                ok = gestionPedidosMQDao.persistirCaptura(cliente, descuento, recursoList, operDescuento, operacionRecursoList, lineas, puntos, UUID, EstadoDescuento.CANCELADO.getValue());
            } catch (Exception e) {
                contadorReintentos++;
            }
        } while (contadorReintentos < MAXIMO_NUMERO_REINTENTOS && ok != true);

        if (contadorReintentos == MAXIMO_NUMERO_REINTENTOS) {
            throw new TimeoutException(String.format(Errores.NUMERO_MAXIMO_REINTENTOS, Integer.toString(MAXIMO_NUMERO_REINTENTOS)));
        }
    }

    /******************************************************************************************************************************
     * 
     * ACCIONES PRIVADAS DE PEDIDOS MQ
     * 
     ******************************************************************************************************************************/

    /**
     * Obtener lineas pedido afectadas.
     * 
     * @param idDescuento
     *            the id descuento
     * @return the list
     */
    private List<LineaPedido> obtenerLineasPedidoAfectadas(String idDescuento) {
        LOGGER.trace("GestionPedidosMQServiceImpl.obtenerLineasPedidoAfectadas");

        List<LineaPedido> lineasPedido = new ArrayList<LineaPedido>();
        List<LineaDescuentoDto> lineaDescuentoList = gestionDescuentosDao.obtenerLineaDescuento(idDescuento);

        for (LineaDescuentoDto lineaDescuento : lineaDescuentoList) {
            LineaPedido lineaPedido = new LineaPedido();

            lineaPedido.setIdLineaPedido(String.format(Mensajes.INSTALACION, lineaDescuento.getIdLineaPedido().getIdLineaDescuento(), lineaDescuento.getIdLineaPedido().getIdInstalacion()));
            lineaPedido.setPartNumber(lineaDescuento.getPartNumber());
            lineaPedido.setCantidadSolicitada(lineaDescuento.getNumeroUnidades());
            lineaPedido.setImporteUnitario(lineaDescuento.getImporteUnitario());
            lineaPedido.setImporteTotal(lineaDescuento.getImporteTotal());

            if (lineaDescuento.getIdTipoLineaPedido().compareTo(TipoLineaPedido.GASTOS_DE_ENVIO.getValue()) == 0) {
                lineaPedido.setEsGastosEnvio(true);
            } else {
                lineaPedido.setEsGastosEnvio(false);
            }

            lineasPedido.add(lineaPedido);
        }

        return lineasPedido;
    }

    /**
     * Generar operacion tarjeta.
     * 
     * @param recurso
     *            the recurso
     * @param idOperacionDescuento
     *            the id operacion descuento
     * @param codOperacionExterno
     *            the cod operacion externo
     * @param tipoOperacionRecurso
     *            the tipo operacion recurso
     * @param fechaActual
     *            the fecha actual
     * @param importeOperacion
     *            the importe operacion
     * @param importeRecursp
     *            the importe recurso
     * @return the operacion tarjeta dto
     */
    private OperacionRecursoDto generarOperacionRecurso(RecursoDto recurso, PrimaryKeyOperacionDescuentoDto idOperacionDescuento, String codOperacionExterno, Short tipoOperacionRecurso, Calendar fechaActual, BigDecimal importeOperacion, BigDecimal importeRecurso) {
        LOGGER.trace("GestionPedidosMQServiceImpl.generarOperacionRecurso");

        OperacionRecursoDto operacionRecurso = new OperacionRecursoDto();

        operacionRecurso.setIdOperacionRecurso(new PrimaryKeyOperacionRecursoDto(sequencesDao.getNextValue(Secuencias.SQ_OPERACION_RECURSO), idInstalacion));
        operacionRecurso.setIdOperacionDescuento(idOperacionDescuento);
        operacionRecurso.setIdTipoOperacionRecurso(tipoOperacionRecurso);
        operacionRecurso.setCodigoOperacionRecursoExterno(codOperacionExterno);
        operacionRecurso.setIdRecurso(recurso.getIdRecurso());
        operacionRecurso.setIdTipoValorOperacion(TipoValorRecurso.PUNTOS.getValue());
        operacionRecurso.setValorOperacion(importeOperacion);
        operacionRecurso.setValorOperacionRecurso(importeRecurso);
        operacionRecurso.setFechaHoraOperacion(new Timestamp(fechaActual.getTimeInMillis()));

        return operacionRecurso;
    }

    /**
     * Generar operacion descuento.
     * 
     * @param fechaActual
     * @param importeOperacion
     * 
     * @param idDescuento
     *            the id descuento
     * @return the operacion descuento dto
     */
    private OperacionDescuentoDto generarOperacionDescuento(DescuentoDto descuento, Short tipoOperacionDescuento, Calendar fechaActual, BigDecimal importeOperacion) {
        LOGGER.trace("GestionPedidosMQServiceImpl.generarOperacionDescuento");

        OperacionDescuentoDto operacionDescuento = new OperacionDescuentoDto();

        operacionDescuento.setIdOperacionDescuento(new PrimaryKeyOperacionDescuentoDto(sequencesDao.getNextValue(Secuencias.SQ_OPERACION_DESCUENTO), idInstalacion));
        operacionDescuento.setCodigoOperacionExterno(descuento.getCodigoPedidoExterno());
        operacionDescuento.setIdDescuento(descuento.getIdDescuento());
        operacionDescuento.setIdTipoOperacionDescuento(tipoOperacionDescuento);
        operacionDescuento.setImporteOperacion(importeOperacion);
        operacionDescuento.setFechaHoraOperacion(new Timestamp(fechaActual.getTimeInMillis()));

        return operacionDescuento;
    }

    /**
     * Generar lineas pedido.
     * 
     * @param lineasPedidoAfectadas
     *            the lineas pedido afectadas
     * @return the list
     */
    private List<LineaPedido> generarLineasPedido(List<LineaPedido> lineasPedidoAfectadas) {
        LOGGER.trace("GestionPedidosMQServiceImpl.generarLineasPedido");

        List<LineaPedido> lineaPedidoList = new ArrayList<LineaPedido>();

        for (LineaPedido linea : lineasPedidoAfectadas) {
            LineaPedido lineaPedido = new LineaPedido();

            lineaPedido.setIdLineaPedido(linea.getIdLineaPedido());
            lineaPedido.setPartNumber(linea.getPartNumber());
            lineaPedido.setCantidadSolicitada(linea.getCantidadSolicitada());
            lineaPedido.setImporteUnitario(linea.getImporteUnitario());
            lineaPedido.setImporteTotal(linea.getImporteTotal());
            lineaPedido.setEsGastosEnvio(linea.getEsGastosEnvio());
            lineaPedido.setEsSaldo(linea.getEsGastosEnvio());
            lineaPedido.setEsPromocion(linea.getEsPromocion());

            lineaPedidoList.add(lineaPedido);
        }

        return lineaPedidoList;
    }

    /**
     * Calculo puntos.
     * 
     * @param operacionPedido
     *            the operacion pedido
     * @param descuento
     *            the descuento
     */
    private Pair<BigDecimal, BigDecimal> calculoPuntos(OperacionPedido operacionPedido, DescuentoDto descuento) {
        BigDecimal puntos = BigDecimal.ZERO;
        BigDecimal importe = BigDecimal.ZERO;

        for (LineaPedido lp : operacionPedido.getLineasPedidoAfectadas()) {
            // No se aplican descuentos a las lineas de gastos de envio
            // No se aplican descuentos a las lineas con el parametro esSaldo a true
            // No se aplican descuentos a las lineas con porcentajeCortePrecio mayor que cero
            if (!lp.getEsGastosEnvio() && !lp.getEsSaldo() && !(lp.getPorcentajeCortePrecio() != null && lp.getPorcentajeCortePrecio() > 0)) {
                DetalleDescuentoDto detalle = gestionDescuentosDao.obtenerDetalleDescuento(descuento.getIdDescuento(), lp.getPartNumber());

                puntos = puntos.add(detalle.getConsumoPuntos().multiply(new BigDecimal(lp.getCantidadSolicitada())));
                importe = importe.add(detalle.getImporteDescuento().multiply(new BigDecimal(lp.getCantidadSolicitada())));
            }
        }

        return new Pair<BigDecimal, BigDecimal>(puntos, importe);
    }
    
    /**
     * Calculo puntos.
     * 
     * @param lineas
     *            the lineas
     * @param descuento
     *            the descuento
     * @return the pair
     */
    private Pair<BigDecimal, BigDecimal> calculoPuntos(List<LineaDescuentoDto> lineas, DescuentoDto descuento) {
        BigDecimal puntos = BigDecimal.ZERO;
        BigDecimal importe = BigDecimal.ZERO;
        
        for (LineaDescuentoDto linea : lineas) {
            // No se aplican descuentos a las lineas de gastos de envio
            // No se aplican descuentos a las lineas con el parametro esSaldo a true
            // No se aplican descuentos a las lineas con porcentajeCortePrecio mayor que cero
            if (linea.getIdTipoLineaPedido().compareTo(TipoLineaPedido.GASTOS_DE_ENVIO.getValue()) != 0) {
                DetalleDescuentoDto detalle = gestionDescuentosDao.obtenerDetalleDescuento(descuento.getIdDescuento(), linea.getPartNumber());
                puntos = puntos.add(detalle.getConsumoPuntos().multiply(new BigDecimal(linea.getNumeroUnidades())));
                importe = importe.add(detalle.getImporteDescuento().multiply(new BigDecimal(linea.getNumeroUnidades())));
            }
        }
        
        return new Pair<BigDecimal, BigDecimal>(puntos, importe);
    }

    /**
     * Generar lineas descuento.
     * 
     * @param lineasPedidoAfectadas
     *            the lineas pedido afectadas
     * @return the list
     */
    private List<LineaDescuentoDto> generarLineasDescuento(List<LineaPedido> lineasPedidoAfectadas) {
        LOGGER.trace("GestionPedidosMQ.generarLineasDescuento");

        List<LineaDescuentoDto> lineas = new ArrayList<LineaDescuentoDto>();

        for (LineaPedido lineaPedido : lineasPedidoAfectadas) {
            LineaDescuentoDto linea = new LineaDescuentoDto();

            linea.setCodigoLineaPedidoExterno(lineaPedido.getIdLineaPedido());
            linea.setPartNumber(lineaPedido.getPartNumber());
            linea.setNumeroUnidades(lineaPedido.getCantidadSolicitada());
            linea.setImporteUnitario(lineaPedido.getImporteUnitario());
            linea.setImporteTotal(lineaPedido.getImporteTotal());

            if (lineaPedido.getEsGastosEnvio()) {
                linea.setIdTipoLineaPedido(TipoLineaPedido.GASTOS_DE_ENVIO.getValue());
            } else {
                linea.setIdTipoLineaPedido(TipoLineaPedido.ARTICULO.getValue());
            }

            lineas.add(linea);
        }

        return lineas;
    }

}
