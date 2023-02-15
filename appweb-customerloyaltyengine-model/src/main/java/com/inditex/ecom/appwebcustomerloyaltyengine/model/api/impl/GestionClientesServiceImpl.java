package com.inditex.ecom.appwebcustomerloyaltyengine.model.api.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.AltaCliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Cliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.OperacionTarjeta;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Tarjeta;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.GestionClientesService;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Cadenas;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.EstadoCliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Secuencias;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoCliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoOperacionRecurso;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoRecurso;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.ConfiguracionCadenasDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionClientesDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionMaestrosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionOperacionesDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionRecursosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.sequence.dao.SequencesDao;

/**
 * The Class GestionClientesServiceImpl.
 */
@Service("gestionClientesService")
@Transactional(propagation = Propagation.REQUIRED)
public class GestionClientesServiceImpl implements GestionClientesService {

    /** The logger. */
    private static Logger LOGGER = LoggerFactory.getLogger(GestionClientesServiceImpl.class);

    /** The id instalacion. */
    private @Value("${ID_INSTALACION}") Integer idInstalacion;

    /** The gestion clientes dao. */
    @Autowired
    private GestionClientesDao gestionClientesDao;

    /** The gestion tarjetas dao. */
    @Autowired
    private GestionRecursosDao gestionRecursosDao;

    /** The gestion operaciones dao. */
    @Autowired
    private GestionOperacionesDao gestionOperacionesDao;
    
    /** The gestion maestros dao. */
    @Autowired
    private GestionMaestrosDao gestionMaestrosDao;

    /** The configuracion cadenas dao. */
    @Autowired
    private ConfiguracionCadenasDao configuracionCadenasDao;
    
    /** The sequences dao. */
    @Autowired
    private SequencesDao sequencesDao;

    /**
     * ****************************************************************************************************************************
     * 
     * ACCIONES SOBRE CLIENTES
     * 
     * ****************************************************************************************************************************.
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public AltaCliente altaCliente(Cliente datosCliente, String UUID) {
        AltaCliente altaCliente = new AltaCliente();

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setIdCliente(new PrimaryKeyClienteDto(sequencesDao.getNextValue(Secuencias.SQ_CLIENTE), idInstalacion));
        clienteDto.setIdCadena(datosCliente.getIdCadena().shortValue());
        clienteDto.setCodClienteWalletMarca(datosCliente.getIdClienteEcommerce());
        clienteDto.setIdPais(gestionMaestrosDao.obtenerIdPais(datosCliente.getCodPais()));
        clienteDto.setIdEstadoCliente(EstadoCliente.ACTIVO.getValue());
        clienteDto.setIdTipoCliente(TipoCliente.CLIENTE_ECOMMERCE.getValue());

        altaCliente.setCliente(datosCliente);

        // Comprobamos si existe configuracion para cadena/pais
        if (!configuracionCadenasDao.comprobarCadenaPais(new Cadenas(clienteDto.getIdCadena().intValue(), datosCliente.getCodPais(), null))) {
            altaCliente.setCodResultado(Constantes.COD_RESULTADO_TRES);
            altaCliente.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_TRES);

            return altaCliente;
        }

        // Comprobamos si el cliente esta dado ya de alta
        if (gestionClientesDao.verificarCliente(datosCliente.getIdCadena(), datosCliente.getCodPais(), datosCliente.getIdClienteEcommerce()) != null) {
            altaCliente.setCodResultado(Constantes.COD_RESULTADO_CUATRO);
            altaCliente.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_CUATRO);

            return altaCliente;
        }

        clienteDto.setFechaHoraAlta(new Timestamp(Calendar.getInstance().getTimeInMillis()));

        // Obtenemos el tipo de tarjeta de configuracion
        RecursoDto recursoDto = configuracionCadenasDao.obtenerRecurso(new Cadenas(clienteDto.getIdCadena().intValue(), datosCliente.getCodPais(), null), datosCliente.getSaldoPuntosInicial());

        gestionClientesDao.altaCliente(clienteDto, Calendar.getInstance(), recursoDto);

        // El cliente se ha dado de alta correctamente
        altaCliente.setCodResultado(Constantes.COD_RESULTADO_CERO);
        altaCliente.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_CERO);
        altaCliente.getCliente().setIdClienteDescuentos(String.format(Mensajes.INSTALACION, clienteDto.getIdCliente().getIdCliente().toString(), clienteDto.getIdCliente().getIdInstalacion().toString()));

        datosCliente.setFechaAlta(clienteDto.getFechaHoraAlta().toString());
        datosCliente.setTarjetas(convertirObjectoToAPI(recursoDto));
        datosCliente.setActivo(true);

        return altaCliente;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cliente bajaCliente(String idCliente) throws TimeoutException {
        // Recuperamos el cliente
        ClienteDto clienteDto = gestionClientesDao.obtenerClienteWallet(idCliente);
        Cliente cliente = null;

        // Compruebo si el cliente no existe o esta dado de baja en el modelo
        if (clienteDto == null || clienteDto.getIdEstadoCliente() == EstadoCliente.INACTIVO.getValue()) {
            return cliente;
        }

        gestionClientesDao.bajaCliente(clienteDto);

        cliente = new Cliente();

        cliente.setIdCadena(clienteDto.getIdCadena().intValue());
        cliente.setCodPais(gestionMaestrosDao.obtenerCodPais(clienteDto.getIdPais()));
        cliente.setIdClienteEcommerce(clienteDto.getCodClienteWalletMarca());
        cliente.setIdClienteDescuentos(String.format(Mensajes.INSTALACION, clienteDto.getIdCliente().getIdCliente().toString(), clienteDto.getIdCliente().getIdInstalacion().toString()));
        cliente.setActivo(false);
        cliente.setFechaAlta(clienteDto.getFechaHoraAlta().toString());
        cliente.setFechaBaja(clienteDto.getFechaHoraBaja().toString());

        List<RecursoDto> recursoList = gestionRecursosDao.obtenerRecursosEstado(clienteDto.getIdCliente());

        if (recursoList != null) {
            List<Tarjeta> tarjList = new ArrayList<Tarjeta>();

            for (RecursoDto recursoDto : recursoList) {
                Tarjeta tarjeta = new Tarjeta();

                tarjeta.setIdTarjeta(String.format(Mensajes.INSTALACION, recursoDto.getIdRecurso().getIdRecurso(), recursoDto.getIdRecurso().getIdInstalacion()));
                tarjeta.setTipoTarjeta(TipoRecurso.TARJETA_PUNTOS_PULLBEAR.toString());
                tarjeta.setSaldo(recursoDto.getValorRecurso());

                tarjList.add(tarjeta);
            }

            cliente.setTarjetas(tarjList);
        }

        return cliente;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cliente movimientos(String idClient) {
        LOGGER.trace(String.format("Realizamos los movimientos para el cliente: %s.", idClient));

        ClienteDto clienteDto = gestionClientesDao.obtenerClienteWallet(idClient);

        // Generamos el cliente
        Cliente cliente = new Cliente();

        cliente.setIdCadena(clienteDto.getIdCadena().intValue());
        cliente.setCodPais(gestionMaestrosDao.obtenerCodPais(clienteDto.getIdPais()));
        cliente.setIdClienteEcommerce(clienteDto.getCodClienteWalletMarca());
        cliente.setIdClienteDescuentos(String.format(Mensajes.INSTALACION, clienteDto.getIdCliente().getIdCliente().toString(), clienteDto.getIdCliente().getIdInstalacion().toString()));

        cliente.setTarjetas(obtenerTarjetas(clienteDto.getIdCliente()));

        if (clienteDto.getFechaHoraBaja() == null) {
            cliente.setActivo(true);
        } else {
            cliente.setActivo(false);
            cliente.setFechaBaja(clienteDto.getFechaHoraBaja().toString());
        }
        cliente.setFechaAlta(clienteDto.getFechaHoraAlta().toString());

        LOGGER.trace(String.format("Movimientos del cliente: %s, son: %s", idClient, cliente.toStringJSON()));

        return cliente;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ClienteDto obtenerClienteWallet(String idCliente) {
        return gestionClientesDao.obtenerClienteWallet(idCliente);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AltaCliente recargarPuntos(Integer idTipo, ClienteDto cliente, Integer puntos, Integer maxPuntos, Integer numPuntos) throws TimeoutException {
        AltaCliente recarga = new AltaCliente();

        List<RecursoDto> recursoList = gestionRecursosDao.obtenerRecursosTipo(cliente.getIdCliente(), idTipo);

        // Comprobamos que opcion tenemos que utilizar
        if (puntos != null) {
            for (RecursoDto recurso : recursoList) {
                gestionRecursosDao.actualizarValorDisponibleRecurso(recurso, recurso.getValorDisponibleRecurso().add(new BigDecimal(puntos)));
            }

        } else if (maxPuntos != null) {
            for (RecursoDto recurso : recursoList) {
                gestionRecursosDao.actualizarValorDisponibleRecurso(recurso, new BigDecimal(maxPuntos));
            }

        } else if (numPuntos != null) {

        }

        // Cargamos el objeto recarga que vamos a devolver
        recarga.setCodResultado(Constantes.COD_RESULTADO_CERO);
        recarga.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_CERO);
        recarga.setIdOperacionDescuento(Integer.valueOf(TipoOperacionRecurso.MODIFICACION_VALOR.getValue()));
        recarga.setCliente(rellenarCliente(cliente));

        return recarga;
    }
    
    /**
     * ****************************************************************************************************************************
     * 
     * ACCIONES PRIVADAS SOBRE CLIENTES
     * 
     * ****************************************************************************************************************************.
     */

    /**
     * Convertimos el Objecto a API.
     * 
     * @param recursoDto
     *            the tarjeta dto
     * @return the list
     */
    private List<Tarjeta> convertirObjectoToAPI(RecursoDto recursoDto) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setIdTarjeta(String.format(Mensajes.INSTALACION, recursoDto.getIdRecurso().getIdRecurso(), recursoDto.getIdRecurso().getIdInstalacion()));
        tarjeta.setTipoTarjeta(TipoRecurso.TARJETA_PUNTOS_PULLBEAR.toString());
        tarjeta.setSaldo(recursoDto.getValorRecurso());

        List<Tarjeta> tarjetaList = new ArrayList<Tarjeta>();
        tarjetaList.add(tarjeta);
        return tarjetaList;
    }

    /**
     * Obtener tarjetas.
     * 
     * @param cliente
     *            the id client
     * @return the list
     */
    private List<Tarjeta> obtenerTarjetas(PrimaryKeyClienteDto cliente) {
        List<RecursoDto> listaTarjetas = gestionRecursosDao.obtenerRecursos(cliente);
        List<Tarjeta> tarjetaList = new ArrayList<Tarjeta>();

        for (RecursoDto recurso : listaTarjetas) {
            Tarjeta tarjeta = new Tarjeta();

            tarjeta.setIdTarjeta(String.format(Mensajes.INSTALACION, recurso.getIdRecurso().getIdRecurso(), recurso.getIdRecurso().getIdInstalacion()));
            tarjeta.setTipoTarjeta(TipoRecurso.TARJETA_PUNTOS_PULLBEAR.toString());
            tarjeta.setSaldo(recurso.getValorDisponibleRecurso());
            tarjeta.setMovimientos(obtenerMovimientos(recurso));

            tarjetaList.add(tarjeta);
        }

        return tarjetaList;
    }

    /**
     * Obtener movimientos.
     * 
     * @param idTarjeta
     *            the id tarjeta
     * @return the list
     */
    private List<OperacionTarjeta> obtenerMovimientos(RecursoDto tarjeta) {
        List<OperacionTarjeta> movimientos = new ArrayList<OperacionTarjeta>();
        List<OperacionRecursoDto> operacionesList = gestionOperacionesDao.obtenerOperacionRecurso(tarjeta);

        for (OperacionRecursoDto operacionRecurso : operacionesList) {
            OperacionTarjeta operTarjeta = new OperacionTarjeta();

            operTarjeta.setFechaTransaccion(operacionRecurso.getFechaHoraOperacion().toString());
            operTarjeta.setImporte(operacionRecurso.getValorOperacion());

            switch (operacionRecurso.getIdTipoOperacionRecurso().intValue()) {
            case 0:
                operTarjeta.setTipoOperacion(TipoOperacionRecurso.AUTORIZACION.toString());
                break;
            case 1:
                operTarjeta.setTipoOperacion(TipoOperacionRecurso.CAPTURA.toString());
                break;
            case 2:
                operTarjeta.setTipoOperacion(TipoOperacionRecurso.DEVOLUCION.toString());
                break;
            case 3:
                operTarjeta.setTipoOperacion(TipoOperacionRecurso.CANCELACION.toString());
                break;
            case 4:
                operTarjeta.setTipoOperacion(TipoOperacionRecurso.ALTA.toString());
                break;
            case 5:
                operTarjeta.setTipoOperacion(TipoOperacionRecurso.BAJA.toString());
                break;
            default:
                operTarjeta.setTipoOperacion(TipoOperacionRecurso.OPERACION_NO_DEFINIDA.toString());
            }

            movimientos.add(operTarjeta);
        }

        return movimientos;
    }

    /**
     * Obtener cliente.
     * 
     * @param cliente2
     *            the id cliente
     * @return the cliente
     */
    private Cliente rellenarCliente(ClienteDto cliente) {
        Cliente client = new Cliente();

        client.setIdCadena(cliente.getIdCadena().intValue());
        client.setCodPais(gestionMaestrosDao.obtenerCodPais(cliente.getIdPais()));
        client.setIdClienteEcommerce(cliente.getCodClienteWalletMarca());
        client.setIdClienteDescuentos(String.format(Mensajes.INSTALACION, cliente.getIdCliente().getIdCliente().toString(), cliente.getIdCliente().getIdInstalacion().toString()));
        client.setActivo(true);
        client.setFechaAlta(cliente.getFechaHoraAlta().toString());

        client.setTarjetas(obtenerTarjetas(cliente.getIdCliente()));

        return client;
    }

}
