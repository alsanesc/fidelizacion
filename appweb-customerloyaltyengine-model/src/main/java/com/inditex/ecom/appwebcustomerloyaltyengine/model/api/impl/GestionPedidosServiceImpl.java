package com.inditex.ecom.appwebcustomerloyaltyengine.model.api.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.AsignacionPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.DatosDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.DescuentoAplicado;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.LineaPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Pedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.PedidoDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.GestionPedidosService;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Cadenas;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Utility;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.EstadoDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Secuencias;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoLineaPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Algoritmos;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.Errores;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.ConfiguracionCadenasDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionClientesDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionDescuentosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionMaestrosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionPedidosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionRecursosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.impl.GestionClientesDaoImpl;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DetalleDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyDetalleDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyLineaPedidoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyOperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.sequence.dao.SequencesDao;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion del Service de GestionPedidosService: nos permitira realizar todas las operaciones
 * relacionadas con PEDIDOS (CALCULAR_DESCUENTO, AUTORIZAR_DESCUENTO, CONSULTAR_DESCUENTO).
 */
@Service("gestionPedidosService")
@Transactional(propagation = Propagation.REQUIRED)
public class GestionPedidosServiceImpl implements GestionPedidosService {

    /** The Constant LOG. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestionClientesDaoImpl.class);

    /** The Constant DESCUENTO. */
    private static final BigDecimal DESCUENTO = new BigDecimal(20);

    /** The id instalacion. */
    private @Value("${ID_INSTALACION}") Integer idInstalacion;

    /** The gestion pedidos dao. */
    @Autowired
    private GestionPedidosDao gestionPedidosDao;

    /** The gestion clientes dao. */
    @Autowired
    private GestionClientesDao gestionClientesDao;

    /** The gestion recursos dao. */
    @Autowired
    private GestionRecursosDao gestionRecursosDao;

    /** The gestion descuentos dao. */
    @Autowired
    private GestionDescuentosDao gestionDescuentosDao;

    /** The gestion maestros dao. */
    @Autowired
    private GestionMaestrosDao gestionMaestrosDao;

    /** The sequences dao. */
    @Autowired
    private SequencesDao sequencesDao;

    /** The configuracion cadenas dao. */
    @Autowired
    private ConfiguracionCadenasDao configuracionCadenasDao;

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

    /**
     * ****************************************************************************************************************************
     * 
     * ACCIONES SOBRE PEDIDOS
     * 
     * ****************************************************************************************************************************.
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public AsignacionPedido generarDescuento(Pedido pedido, String UUID) {
        LOGGER.trace("GestionPedidosServiceImpl.generarDescuento");

        AsignacionPedido asigPedido = new AsignacionPedido();

        // Comprobamos si existe configuracion para cadena/pais
        if (!configuracionCadenasDao.comprobarCadenaPais(new Cadenas(gestionMaestrosDao.obtenerIdCadena(pedido.getIdTienda()), pedido.getCodPais(), null))) {
            asigPedido.setCodResultado(Constantes.COD_RESULTADO_TRES);
            asigPedido.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_TRES);

            return asigPedido;
        }

        // Comprobamos si el cliente existe en la plataforma
        ClienteDto cliente = gestionClientesDao.obtenerClienteWallet(pedido.getIdClienteEcommerce());

        if (cliente == null) {
            asigPedido.setCodResultado(Constantes.COD_RESULTADO_CINCO);
            asigPedido.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_CINCO);

            return asigPedido;
        }

        // Comprobamos si el cliente tiene recursos adecuados
        List<RecursoDto> recursoList = gestionRecursosDao.obtenerRecursosAdecuados(cliente.getIdCliente(), gestionMaestrosDao.obtenerIdCadena(pedido.getIdTienda()), gestionMaestrosDao.obtenerIdPais(pedido.getCodPais()));

        if (recursoList.isEmpty()) {
            asigPedido.setCodResultado(Constantes.COD_RESULTADO_SIETE);
            asigPedido.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_SIETE);

            return asigPedido;
        }

        // Calculamos el saldo de puntos de los recursos
        BigDecimal puntos = BigDecimal.ZERO;
        for (RecursoDto recurso : recursoList) {
            puntos = puntos.add(recurso.getValorDisponibleRecurso());
        }

        // Generamos DESCUENTO
        DescuentoDto descuento = generarDescuento(pedido, cliente.getIdCliente());

        // Ordenamos las lineas de Pedido por importeTotal descendentemente
        ordenarLineasPedido(pedido.getLineasPedido());

        // Calculamos el importe del pedido sin restricciones
        BigDecimal importe = BigDecimal.ZERO;
        for (LineaPedido lineaPedido : pedido.getLineasPedido()) {
            if (!lineaPedido.getEsGastosEnvio() && !lineaPedido.getEsSaldo() && !(lineaPedido.getPorcentajeCortePrecio() != null && lineaPedido.getPorcentajeCortePrecio() > 0)) {
                importe = importe.add(new BigDecimal(Utility.truncar(lineaPedido.getImporteTotal())));
            }
        }

        // Invocacion a LN de calculo de descuento con el Pedido y el saldo de puntos
        List<DetalleDescuentoDto> detalleDescuentoList = new ArrayList<DetalleDescuentoDto>();
        List<LineaDescuentoDto> lineaDescuentoList = new ArrayList<LineaDescuentoDto>();

        if (puntos.compareTo(importe) >= 0) {
            // Tenemos mas puntos en el recurso que el total del pedido
            generarDetalleLineaDescuento(pedido, detalleDescuentoList, lineaDescuentoList, descuento.getIdDescuento());
        } else {
            // Tenemos menos puntos en el recurso que el total del pedido --> MAXIMIZAMOS el descuento
            generarDetalleLineaDescuentoMaximoPrecio(pedido, puntos, detalleDescuentoList, lineaDescuentoList, descuento.getIdDescuento());
            // generarDetalleLineaDescuentoKnapSack(pedido, puntos, detalleDescuentoList, lineaDescuentoList, descuento.getIdDescuento());
        }

        gestionPedidosDao.calcularDescuento(descuento, detalleDescuentoList, lineaDescuentoList);

        // Generamos objeto de resultado
        asigPedido.setListaDescuentosAplicados(obtenerListaDescuentosAplicados(descuento));
        asigPedido.setListaPedidoDescuentos(obtenerPedidoDescuento(lineaDescuentoList, descuento));

        // Asignamos el codigo y descripcion de operacion correcta
        asigPedido.setIdVersionReglas(pedido.getIdVersionReglas());
        asigPedido.setCodResultado(Constantes.COD_RESULTADO_CERO);
        asigPedido.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_CERO);

        return asigPedido;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pedido consultaEstadoDescuento(List<LineaDescuentoDto> lineaDescuento, DescuentoDto descuento, ClienteDto cliente) {
        LOGGER.trace("GestionPedidosServiceImpl.consultaEstadoDescuento");

        Pedido pedido = new Pedido();

        pedido.setIdPedido(Integer.parseInt(descuento.getCodigoPedidoExterno()));
        pedido.setIdClienteEcommerce(cliente.getCodClienteWalletMarca());
        pedido.setIdClienteDescuentos(String.format(Mensajes.INSTALACION, cliente.getIdCliente().getIdCliente(), cliente.getIdCliente().getIdInstalacion()));
        pedido.setIdTienda(descuento.getIdLocalizacion());
        pedido.setCodPais(gestionMaestrosDao.obtenerCodPais(cliente.getIdPais()));
        pedido.setLineasPedido(convertirLineasPedido(lineaDescuento, descuento));
        pedido.setEstadoPedido(EstadoDescuento.get(descuento.getIdEstadoDescuento()).name());

        return pedido;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DescuentoDto obtenerDescuentoPorCodigo(String descuento) {
        return gestionDescuentosDao.obtenerDescuentoPorCodigo(descuento);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DescuentoDto obtenerDescuento(PrimaryKeyDescuentoDto descuento) {
        return gestionDescuentosDao.obtenerDescuento(descuento);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LineaDescuentoDto> obtenerLineaDescuento(PrimaryKeyDescuentoDto descuento, Short estadoDescuento) {
        return gestionDescuentosDao.obtenerLineaDescuento(descuento, estadoDescuento);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<LineaDescuentoDto> obtenerLineaDescuento(PrimaryKeyDescuentoDto descuento) {
        return gestionDescuentosDao.obtenerLineaDescuento(descuento);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ClienteDto obtenerCliente(PrimaryKeyClienteDto clienteDto) {
        return gestionClientesDao.obtenerCliente(clienteDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OperacionDescuentoDto obtenerOperacionDescuento(PrimaryKeyOperacionDescuentoDto idOperacionDescuento) {
        return gestionDescuentosDao.obtenerOperacionDescuento(idOperacionDescuento);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DescuentoDto obtenerIdDescuento(String lineaPedido) {
        return gestionDescuentosDao.obtenerDescuentoOperacion(lineaPedido);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String obtenerIdPedido(Long idDescuento) {
        return gestionDescuentosDao.obtenerDescuentoPorCodigo(idDescuento.toString()).getCodigoPedidoExterno();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String obtenerCodigoPedido(PrimaryKeyDescuentoDto idDescuento) {
        return gestionDescuentosDao.obtenerCodigoPedido(idDescuento);
    }

    /**
     * ************************************************************************* ***************************************************
     * 
     * ACCIONES PRIVADAS SOBRE PEDIDOS
     * 
     * ************************************************************************* ***************************************************.
     */

    /**
     * Generamos el DESCUENTO, para guardar en BBDD.
     * 
     * @param pedido
     *            the pedido
     * @param idCliente
     *            the id cliente
     * @return the descuento dto
     */
    private DescuentoDto generarDescuento(Pedido pedido, PrimaryKeyClienteDto idCliente) {
        LOGGER.trace("GestionPedidosServiceImpl.generarDescuento");

        DescuentoDto descuento = new DescuentoDto();

        descuento.setIdDescuento(new PrimaryKeyDescuentoDto(sequencesDao.getNextValue(Secuencias.SQ_DESCUENTO), idInstalacion));
        descuento.setCodigoPedidoExterno(pedido.getIdPedido().toString());
        descuento.setIdCadena(gestionMaestrosDao.obtenerIdCadena(pedido.getIdTienda()).shortValue());
        descuento.setIdPais(gestionMaestrosDao.obtenerIdPais(pedido.getCodPais()));
        descuento.setIdLocalizacion(pedido.getIdTienda());
        descuento.setIdEstadoDescuento(EstadoDescuento.PENDIENTE.getValue());
        descuento.setIdCliente(idCliente);
        descuento.setImportePedido(pedido.getImporteTotalPedido());
        descuento.setIdDivisa(gestionMaestrosDao.obtenerIdDivisa(pedido.getDivisa()));

        // Hemos establecido un fecha de Validez de descuento de 15 minutos
        Calendar fechaHoraValidezDescuento = Calendar.getInstance();
        fechaHoraValidezDescuento.add(Calendar.MINUTE, 15);
        descuento.setFechaHoraValidezDescuento(new Timestamp(fechaHoraValidezDescuento.getTimeInMillis()));

        return descuento;
    }

    /**
     * Ordenar lineas pedido.
     * 
     * @param list
     *            the list
     */
    private void ordenarLineasPedido(List<LineaPedido> list) {
        LOGGER.trace("GestionPedidosServiceImpl.ordenarLineasPedido");

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).getImporteTotal().compareTo(list.get(i + 1).getImporteTotal()) < 0) {
                LineaPedido lp = list.get(i);
                list.set(i, list.get(i + 1));
                list.set(i + 1, lp);
            }
        }
    }

    /**
     * Generar detalle linea descuento.
     * 
     * @param pedido
     *            the pedido
     * @param detalleDescuentoList
     *            the detalle descuento list
     * @param lineaDescuentoList
     *            the linea descuento list
     * @param descuento
     *            the descuento
     */
    private void generarDetalleLineaDescuento(Pedido pedido, List<DetalleDescuentoDto> detalleDescuentoList, List<LineaDescuentoDto> lineaDescuentoList, PrimaryKeyDescuentoDto descuento) {
        LOGGER.trace("GestionPedidosServiceImpl.generarDetalleLineaDescuento - Sin puntos");

        BigDecimal importe = BigDecimal.ZERO;
        BigDecimal puntos = BigDecimal.ZERO;

        for (LineaPedido lp : pedido.getLineasPedido()) {
            // No se aplican descuentos a las lineas de gastos de envio
            // No se aplican descuentos a las lineas con el parametro esSaldo a true
            // No se aplican descuentos a las lineas con porcentajeCortePrecio mayor que cero
            if (!lp.getEsGastosEnvio() && !lp.getEsSaldo() && !(lp.getPorcentajeCortePrecio() != null && lp.getPorcentajeCortePrecio() > 0)) {
                detalleDescuentoList.add(generarDetalleDescuento(lp.getImporteUnitario(), lp.getImporteUnitario(), DESCUENTO, lp.getCantidadSolicitada()));
                lineaDescuentoList.add(generarLineaDescuento(lp, detalleDescuentoList.get(detalleDescuentoList.size() - 1).getIdDetalleDescuento(), lp.getCantidadSolicitada(), descuento));

                puntos = puntos.add(lp.getImporteUnitario().multiply(new BigDecimal(lp.getCantidadSolicitada())));
                importe = importe.add(lp.getImporteTotal());
            } else {
                lineaDescuentoList.add(generarLineaDescuento(lp, null, lp.getCantidadSolicitada(), descuento));
            }
        }

        detalleDescuentoList.add(generarDetalleDescuento(importe, puntos, DESCUENTO, 0));
    }

    /**
     * Generar detalle linea descuento maximo precio.
     * 
     * @param pedido
     *            the pedido
     * @param puntosRecurso
     *            the puntos recurso
     * @param detalleDescuentoList
     *            the detalle descuento list
     * @param lineaDescuentoList
     *            the linea descuento list
     * @param idDescuento
     *            the id descuento
     */
    private void generarDetalleLineaDescuentoMaximoPrecio(Pedido pedido, BigDecimal puntosRecurso, List<DetalleDescuentoDto> detalleDescuentoList, List<LineaDescuentoDto> lineaDescuentoList, PrimaryKeyDescuentoDto descuento) {
        LOGGER.trace("GestionPedidosServiceImpl.generarDetalleLineaDescuentoMaximoPrecio - Con Puntos");
        
        // Recorremos las lineas de pedido, para obtener los valores de idLineaPedido y precioUnitario, para realizar el algoritmo de KnapSack
        BigDecimal[] price = {};
        String[] partnumber = {};
        
        for (LineaPedido lp : pedido.getLineasPedido()) {
            // No se aplican descuentos a las lineas de gastos de envio
            // No se aplican descuentos a las lineas con el parametro esSaldo a true
            // No se aplican descuentos a las lineas con porcentajeCortePrecio mayor que cero
            if (!lp.getEsGastosEnvio() && !lp.getEsSaldo() && !(lp.getPorcentajeCortePrecio() != null && lp.getPorcentajeCortePrecio() > 0)) {
                for (int i = 1; i <= lp.getCantidadSolicitada(); i++) {
                    price = (BigDecimal[]) ArrayUtils.add(price, new BigDecimal(lp.getImporteUnitario().toString()));
                    partnumber = (String[]) ArrayUtils.add(partnumber, lp.getPartNumber());
                }
            }
        }
        
        BigDecimal[] weights = Algoritmos.priceMaximum(price, puntosRecurso.intValue());
        
        int numUn = 0;
        String pn = null;
        BigDecimal importe = BigDecimal.ZERO;
        BigDecimal puntos = BigDecimal.ZERO;
        List<String> l = new ArrayList<String>();
        // Recorremos la lista de articulos que tenemos que guardar
        for (int i = 0; i < weights.length; i++) {
            if (weights[i].compareTo(BigDecimal.ZERO) > 0) {
                if (pn == null) {
                    pn = partnumber[i];
                    numUn++;
                } else if (pn == partnumber[i]) {
                    numUn++;
                } else {
                    for (LineaPedido lp : pedido.getLineasPedido()) {
                        if (lp.getPartNumber().equals(pn)) {
                            detalleDescuentoList.add(generarDetalleDescuento(lp.getImporteUnitario().multiply(new BigDecimal(numUn)), lp.getImporteUnitario().multiply(new BigDecimal(numUn)), DESCUENTO, numUn));
                            lineaDescuentoList.add(generarLineaDescuento(lp, detalleDescuentoList.get(detalleDescuentoList.size() - 1).getIdDetalleDescuento(), lp.getCantidadSolicitada(), descuento));
                            l.add(pn);

                            puntos = puntos.add(lp.getImporteUnitario().multiply(new BigDecimal(numUn)));
                            importe = importe.add(lp.getImporteUnitario().multiply(new BigDecimal(numUn)));

                            LOGGER.trace("\tp=" + pn + "\tNumUnidades=" + numUn + "\tw=" + Utility.truncar(lp.getImporteUnitario().multiply(new BigDecimal(numUn))));
                        }
                    }

                    pn = partnumber[i];
                    numUn = 1;
                }
            }
        }
        
        for (LineaPedido lp : pedido.getLineasPedido()) {
            if (lp.getPartNumber().equals(pn)) {
                detalleDescuentoList.add(generarDetalleDescuento(lp.getImporteUnitario().multiply(new BigDecimal(numUn)), lp.getImporteUnitario().multiply(new BigDecimal(numUn)), DESCUENTO, numUn));
                lineaDescuentoList.add(generarLineaDescuento(lp, detalleDescuentoList.get(detalleDescuentoList.size() - 1).getIdDetalleDescuento(),lp.getCantidadSolicitada(), descuento));
                l.add(pn);

                puntos = puntos.add(lp.getImporteUnitario().multiply(new BigDecimal(numUn)));
                importe = importe.add(lp.getImporteUnitario().multiply(new BigDecimal(numUn)));

                LOGGER.trace("\tpartNumber=" + pn + "\tNumUnidades=" + numUn + "\tPrice=" + Utility.truncar(lp.getImporteUnitario().multiply(new BigDecimal(numUn))));
            } else if (lp.getEsGastosEnvio() || lp.getEsSaldo() || (lp.getPorcentajeCortePrecio() != null && lp.getPorcentajeCortePrecio() > 0)) {
                lineaDescuentoList.add(generarLineaDescuento(lp, null, lp.getCantidadSolicitada(), descuento));
            } else if (!l.contains(lp.getPartNumber())) {
                lineaDescuentoList.add(generarLineaDescuento(lp, null, lp.getCantidadSolicitada(), descuento));
            }
        }
        
        detalleDescuentoList.add(generarDetalleDescuento(importe, puntos, DESCUENTO, 0));
    }

    /**
     * Generar detalle linea descuento.
     * 
     * @param pedido
     *            the pedido
     * @param puntosRecurso
     *            the puntos
     * @param detalleDescuentoList
     *            the detalle descuento list
     * @param lineaDescuentoList
     *            the linea descuento list
     * @param idDescuento
     *            the id descuento
     */
    private void generarDetalleLineaDescuentoKnapSack(Pedido pedido, BigDecimal puntosRecurso, List<DetalleDescuentoDto> detalleDescuentoList, List<LineaDescuentoDto> lineaDescuentoList, PrimaryKeyDescuentoDto descuento) {
        LOGGER.trace("GestionPedidosServiceImpl.generarDetalleLineaDescuentoKnapSack - Con Puntos");

        // Recorremos las lineas de pedido, para obtener los valores de idLineaPedido y precioUnitario, para realizar el algoritmo de KnapSack
        int[] price = {};
        int[] weights = {};
        String[] partnumber = {};

        for (LineaPedido lp : pedido.getLineasPedido()) {
            // No se aplican descuentos a las lineas de gastos de envio
            // No se aplican descuentos a las lineas con el parametro esSaldo a true
            // No se aplican descuentos a las lineas con porcentajeCortePrecio mayor que cero
            if (!lp.getEsGastosEnvio() && !lp.getEsSaldo() && !(lp.getPorcentajeCortePrecio() != null && lp.getPorcentajeCortePrecio() > 0)) {
                for (int i = 1; i <= lp.getCantidadSolicitada(); i++) {
                    price = ArrayUtils.add(price, 1);
                    weights = ArrayUtils.add(weights, Utility.truncar(lp.getImporteUnitario()));
                    partnumber = (String[]) ArrayUtils.add(partnumber, lp.getPartNumber());
                }
            }
        }

        boolean[][] keep = Algoritmos.knapSack(price, weights, puntosRecurso.intValue());

        int K = puntosRecurso.intValue();
        int n = price.length;
        int numUn = 0;
        String pn = null;
        BigDecimal importe = BigDecimal.ZERO;
        BigDecimal puntos = BigDecimal.ZERO;
        for (int i = n - 1; i >= 0; i--) { // need to go in the reverse order
            if (keep[i][K] == true) {
                if (pn == null) {
                    pn = partnumber[i];
                    numUn++;
                } else if (pn == partnumber[i]) {
                    numUn++;
                } else {
                    for (LineaPedido lp : pedido.getLineasPedido()) {
                        if (lp.getPartNumber().equals(pn)) {
                            detalleDescuentoList.add(generarDetalleDescuento(lp.getImporteUnitario().multiply(new BigDecimal(numUn)), lp.getImporteUnitario().multiply(new BigDecimal(numUn)), DESCUENTO, numUn));
                            lineaDescuentoList.add(generarLineaDescuento(lp, detalleDescuentoList.get(detalleDescuentoList.size() - 1).getIdDetalleDescuento(), lp.getCantidadSolicitada(), descuento));

                            puntos = puntos.add(lp.getImporteUnitario().multiply(new BigDecimal(numUn)));
                            importe = importe.add(lp.getImporteUnitario().multiply(new BigDecimal(numUn)));

                            LOGGER.trace("\tp=" + pn + "\tNumUnidades=" + numUn + "\tw=" + Utility.truncar(lp.getImporteUnitario().multiply(new BigDecimal(numUn))));
                        }
                    }

                    pn = partnumber[i];
                    numUn = 1;
                }

                K = K - weights[i];
            }
        }

        for (LineaPedido lp : pedido.getLineasPedido()) {
            if (lp.getPartNumber().equals(pn)) {
                detalleDescuentoList.add(generarDetalleDescuento(lp.getImporteUnitario().multiply(new BigDecimal(numUn)), lp.getImporteUnitario().multiply(new BigDecimal(numUn)), DESCUENTO, numUn));
                lineaDescuentoList.add(generarLineaDescuento(lp, detalleDescuentoList.get(detalleDescuentoList.size() - 1).getIdDetalleDescuento(),lp.getCantidadSolicitada(), descuento));

                puntos = puntos.add(lp.getImporteUnitario().multiply(new BigDecimal(numUn)));
                importe = importe.add(lp.getImporteUnitario().multiply(new BigDecimal(numUn)));

                LOGGER.trace("\tpartNumber=" + pn + "\tNumUnidades=" + numUn + "\tPrice=" + Utility.truncar(lp.getImporteUnitario().multiply(new BigDecimal(numUn))));
            } else if (lp.getEsGastosEnvio() || lp.getEsSaldo() || (lp.getPorcentajeCortePrecio() != null && lp.getPorcentajeCortePrecio() > 0)) {
                lineaDescuentoList.add(generarLineaDescuento(lp, null, lp.getCantidadSolicitada(), descuento));
            } else if (!lineaDescuentoList.contains(lp.getPartNumber())) {
                lineaDescuentoList.add(generarLineaDescuento(lp, null, lp.getCantidadSolicitada(), descuento));
            }
        }

        detalleDescuentoList.add(generarDetalleDescuento(importe, puntos, DESCUENTO, 0));
    }

    /**
     * Generar detalle descuento.
     * 
     * @param importe
     *            the importe
     * @param puntos 
     * @param porcentaje
     *            the porcentaje
     * @param numeroUnidades
     *            the numero unidades
     * @return the detalle descuento dto
     */
    private DetalleDescuentoDto generarDetalleDescuento(BigDecimal importe, BigDecimal puntos, BigDecimal porcentaje, Integer numeroUnidades) {
        LOGGER.trace("GestionPedidosServiceImpl.generarDetalleDescuento");

        DetalleDescuentoDto detalleDescuento = new DetalleDescuentoDto();

        detalleDescuento.setIdDetalleDescuento(new PrimaryKeyDetalleDescuentoDto(sequencesDao.getNextValue(Secuencias.SQ_DETALLE_DESCUENTO), idInstalacion));
        detalleDescuento.setConsumoPuntos(puntos);
        detalleDescuento.setIncrementoPuntos(puntos);

        detalleDescuento.setPorcentajeDescuento(porcentaje);
        detalleDescuento.setImporteDescuento(importe.multiply(porcentaje.divide(new BigDecimal(100))));
        detalleDescuento.setNumeroUnidades(numeroUnidades);

        return detalleDescuento;
    }

    /**
     * Generar linea descuento.
     * 
     * @param lp
     *            the lp
     * @param idDetalleDescuento
     *            the id detalle descuento
     * @param cantidadSolicitada
     *            the cantidad solicitada
     * @param descuento
     *            the descuento
     * @return the linea descuento dto
     */
    private LineaDescuentoDto generarLineaDescuento(LineaPedido lp, PrimaryKeyDetalleDescuentoDto idDetalleDescuento, Integer cantidadSolicitada, PrimaryKeyDescuentoDto descuento) {
        LOGGER.trace("GestionPedidosServiceImpl.generarLineaDescuento");

        LineaDescuentoDto lineaDescuento = new LineaDescuentoDto();

        lineaDescuento.setIdDescuento(descuento);
        lineaDescuento.setIdLineaPedido(new PrimaryKeyLineaPedidoDto(sequencesDao.getNextValue(Secuencias.SQ_LINEA_DESCUENTO), idInstalacion));
        lineaDescuento.setCodigoLineaPedidoExterno(lp.getIdLineaPedido().toString());

        if (lp.getEsGastosEnvio()) {
            lineaDescuento.setIdTipoLineaPedido(TipoLineaPedido.GASTOS_DE_ENVIO.getValue());
        } else {
            lineaDescuento.setIdTipoLineaPedido(TipoLineaPedido.ARTICULO.getValue());
        }

        lineaDescuento.setPartNumber(lp.getPartNumber());
        lineaDescuento.setNumeroUnidades(cantidadSolicitada);
        lineaDescuento.setNumeroUnidadesCapturadas(0);
        lineaDescuento.setNumeroUnidadesDevueltas(0);
        lineaDescuento.setImporteUnitario(lp.getImporteUnitario());
        lineaDescuento.setImporteTotal(lp.getImporteUnitario().multiply(new BigDecimal(cantidadSolicitada)));
        lineaDescuento.setIdEstadoDescuento(EstadoDescuento.PENDIENTE.getValue());
        lineaDescuento.setIdDetalleDescuento(idDetalleDescuento);

        return lineaDescuento;
    }

    /**
     * Obtenemos la lista de LINEA_PEDIDO, para mostrar en el resultado.
     * 
     * @param lineaDescuentoList
     *            the linea descuento list
     * @param descuento
     *            the descuento
     * @return the list
     */
    private List<LineaPedido> convertirLineasPedido(List<LineaDescuentoDto> lineaDescuentoList, DescuentoDto descuento) {
        LOGGER.trace("GestionPedidosServiceImpl.convertirLineasPedido");

        List<LineaPedido> lineaPedidoList = new ArrayList<LineaPedido>();

        for (LineaDescuentoDto lineaDescuento : lineaDescuentoList) {
            LineaPedido lineaPedido = new LineaPedido();

            lineaPedido.setIdLineaPedido(lineaDescuento.getCodigoLineaPedidoExterno());
            lineaPedido.setPartNumber(lineaDescuento.getPartNumber());
            lineaPedido.setCantidadSolicitada(lineaDescuento.getNumeroUnidades());
            lineaPedido.setImporteUnitario(lineaDescuento.getImporteUnitario());
            lineaPedido.setImporteTotal(lineaDescuento.getImporteTotal());

            if (lineaDescuento.getIdTipoLineaPedido().compareTo(TipoLineaPedido.GASTOS_DE_ENVIO.getValue()) == 0) {
                lineaPedido.setEsGastosEnvio(true);
            } else {
                lineaPedido.setEsGastosEnvio(false);
            }

            lineaPedido.setDescuentoAplicado(convertirDescuento(descuento, lineaDescuento));

            lineaPedidoList.add(lineaPedido);
        }

        return lineaPedidoList;
    }

    /**
     * Obtenemos el DESCUENTO_APLICADO, para mostrar en el resultado.
     * 
     * @param descuento
     *            the descuento
     * @param lineaDescuento
     *            the linea descuento
     * @return the descuento aplicado
     */
    private DescuentoAplicado convertirDescuento(DescuentoDto descuento, LineaDescuentoDto lineaDescuento) {
        LOGGER.trace("GestionPedidosServiceImpl.convertirDescuento");

        DescuentoAplicado descuentoAplicado = null;

        if (lineaDescuento.getIdDetalleDescuento() != null) {
            DetalleDescuentoDto detalleDescuento = gestionDescuentosDao.obtenerDetalleDescuento(lineaDescuento.getIdDetalleDescuento());
            
            descuentoAplicado = new DescuentoAplicado();
            
            descuentoAplicado.setEsDescuentoPorcentual(true);
            descuentoAplicado.setEsDescuentoImporte(false);
            descuentoAplicado.setDescuentoAplicadoPorcentaje(detalleDescuento.getPorcentajeDescuento());
            descuentoAplicado.setDescuentoAplicadoImporte(detalleDescuento.getImporteDescuento());
            
            descuentoAplicado.setImporteDescuentoUnitario(lineaDescuento.getImporteUnitario().multiply((detalleDescuento.getPorcentajeDescuento().divide(new BigDecimal(100)))));
            descuentoAplicado.setImporteDescuentoTotal(lineaDescuento.getImporteTotal().multiply((detalleDescuento.getPorcentajeDescuento().divide(new BigDecimal(100)))));
            descuentoAplicado.setPuntosGastadosUnitarios(detalleDescuento.getConsumoPuntos());
            descuentoAplicado.setPuntosObtenidosUnitarios(detalleDescuento.getIncrementoPuntos());
            descuentoAplicado.setPuntosGastadosTotal(detalleDescuento.getConsumoPuntos().multiply(new BigDecimal(lineaDescuento.getNumeroUnidades())));
            descuentoAplicado.setPuntosObtenidosTotal(detalleDescuento.getIncrementoPuntos().multiply(new BigDecimal(lineaDescuento.getNumeroUnidades())));
        }

        return descuentoAplicado;
    }

    /**
     * Obtenemos la lista de PEDIDO_DESCUENTO, a mostrar en la respuesta.
     * 
     * @param lineaDescuentoList
     *            the linea descuento list
     * @param descuento
     *            the descuento
     * @return the list
     */
    private List<PedidoDescuento> obtenerPedidoDescuento(List<LineaDescuentoDto> lineaDescuentoList, DescuentoDto descuento) {
        LOGGER.trace("GestionPedidosDaoImpl.obtenerPedidoDescuento");

        List<PedidoDescuento> pedidoDescuentoList = new ArrayList<PedidoDescuento>();

        PedidoDescuento pedidoDescuento = new PedidoDescuento();

        pedidoDescuento.setIdDescuento(String.format(Mensajes.INSTALACION, descuento.getIdDescuento().getIdDescuento(), descuento.getIdDescuento().getIdInstalacion()));
        pedidoDescuento.setIdPedido(Integer.parseInt(descuento.getCodigoPedidoExterno()));
        pedidoDescuento.setDescuentoAplicadoPedido(obtenerDescuentoAplicadoPedido(descuento));
        pedidoDescuento.setLineasPedidoDescuento(obtenerLineaPedidoDescuento(lineaDescuentoList, descuento));

        pedidoDescuentoList.add(pedidoDescuento);

        return pedidoDescuentoList;
    }

    /**
     * Obtener descuento aplicado pedido.
     * 
     * @param descuento
     *            the descuento
     * @return the descuento aplicado
     */
    private DescuentoAplicado obtenerDescuentoAplicadoPedido(DescuentoDto descuento) {
        LOGGER.trace("GestionPedidosDaoImpl.obtenerDescuentoAplicadoPedido");

        DescuentoAplicado descuentoAplicado = new DescuentoAplicado();

        descuentoAplicado.setEsDescuentoPorcentual(true);
        descuentoAplicado.setEsDescuentoImporte(false);

        DetalleDescuentoDto detalle = gestionDescuentosDao.obtenerDetalleDescuento(descuento.getIdDetalleDescuento());

        descuentoAplicado.setDescuentoAplicadoPorcentaje(detalle.getPorcentajeDescuento());
        descuentoAplicado.setDescuentoAplicadoImporte(detalle.getImporteDescuento());
        descuentoAplicado.setPuntosGastadosTotal(detalle.getConsumoPuntos());
        descuentoAplicado.setPuntosObtenidosTotal(detalle.getIncrementoPuntos());

        return descuentoAplicado;
    }

    /**
     * Obtenemos la lista de LINEA_PEDIDO_DESCUENTO, datos a mostrar en la respuesta.
     * 
     * @param lineaDescuentoList
     *            the linea descuento dto
     * @param descuento
     *            the descuento
     * @return the list
     */
    private List<LineaPedido> obtenerLineaPedidoDescuento(List<LineaDescuentoDto> lineaDescuentoList, DescuentoDto descuento) {
        LOGGER.trace("GestionPedidosDaoImpl.obtenerLineaPedidoDescuento");

        List<LineaPedido> lineaPedidoList = new ArrayList<LineaPedido>();

        for (LineaDescuentoDto lineaDescuento : lineaDescuentoList) {
            LineaPedido lineaPedido = new LineaPedido();

            lineaPedido.setIdLineaPedido(lineaDescuento.getCodigoLineaPedidoExterno());
            lineaPedido.setPartNumber(lineaDescuento.getPartNumber());
            lineaPedido.setCantidadSolicitada(lineaDescuento.getNumeroUnidades());
            lineaPedido.setImporteUnitario(lineaDescuento.getImporteUnitario());
            lineaPedido.setImporteTotal(lineaDescuento.getImporteTotal());

            if (lineaDescuento.getIdTipoLineaPedido().compareTo(TipoLineaPedido.GASTOS_DE_ENVIO.getValue()) == 0) {
                lineaPedido.setEsGastosEnvio(true);
                lineaPedido.setNoAplicarDescuento(true);
            } else {
                lineaPedido.setEsGastosEnvio(false);
                lineaPedido.setNoAplicarDescuento(false);
                lineaPedido.setDescuentoAplicado(obtenerDescuentoAplicado(lineaDescuento));
            }
            
            lineaPedidoList.add(lineaPedido);
        }

        return lineaPedidoList;
    }

    /**
     * Obtener descuento aplicado.
     * 
     * @param lineaDescuento
     *            the id detalle descuento
     * @return the descuento aplicado
     */
    private DescuentoAplicado obtenerDescuentoAplicado(LineaDescuentoDto lineaDescuento) {
        LOGGER.trace("GestionPedidosDaoImpl.obtenerDescuentoAplicado");

        DescuentoAplicado descuento = null;
        
        if (lineaDescuento.getIdDetalleDescuento() != null) {
            DetalleDescuentoDto detalle = gestionDescuentosDao.obtenerDetalleDescuento(lineaDescuento.getIdDetalleDescuento());
            
            descuento = new DescuentoAplicado();
            
            descuento.setEsDescuentoPorcentual(true);
            descuento.setEsDescuentoImporte(false);
            
            descuento.setDescuentoAplicadoPorcentaje(detalle.getPorcentajeDescuento());
            descuento.setImporteDescuentoUnitario(detalle.getImporteDescuento());
            descuento.setImporteDescuentoTotal(detalle.getImporteDescuento().multiply(new BigDecimal(detalle.getNumeroUnidades())));
            descuento.setPuntosGastadosUnitarios(detalle.getConsumoPuntos());
            descuento.setPuntosObtenidosUnitarios(detalle.getIncrementoPuntos());
            descuento.setPuntosGastadosTotal(detalle.getConsumoPuntos().multiply(new BigDecimal(detalle.getNumeroUnidades())));
            descuento.setPuntosObtenidosTotal(detalle.getIncrementoPuntos().multiply(new BigDecimal(detalle.getNumeroUnidades())));
            descuento.setUnidadesConDerechoADescuento(detalle.getNumeroUnidades());
            descuento.setUnidadesConDescuento(detalle.getNumeroUnidades());
            descuento.setUnidadesSinDescuento(lineaDescuento.getNumeroUnidades() - detalle.getNumeroUnidades());
        }
        

        return descuento;
    }

    /**
     * Obtenemos la lista de DESCUENTOS_APLICADOS, para mostrar en la respuesta.
     * 
     * @param detalleDescuentoList
     *            the detalle descuento list
     * @param descuento
     *            the descuento
     * @return the list
     */
    private List<DatosDescuento> obtenerListaDescuentosAplicados(DescuentoDto descuento) {
        LOGGER.trace("GestionPedidosDaoImpl.obtenerListaDescuentosAplicados");

        List<DatosDescuento> datosDescuentoList = new ArrayList<DatosDescuento>();

        DatosDescuento datosDescuento = new DatosDescuento();

        DetalleDescuentoDto detalle = gestionDescuentosDao.obtenerDetalleDescuento(descuento.getIdDetalleDescuento());

        datosDescuento.setIdDescuento(descuento.getIdDescuento().getIdDescuento().intValue());
        datosDescuento.setPorcentajeDescuento(detalle.getPorcentajeDescuento());
        datosDescuento.setImporteDescuento(detalle.getImporteDescuento().setScale(2, RoundingMode.HALF_EVEN));

        datosDescuentoList.add(datosDescuento);

        return datosDescuentoList;
    }

}
