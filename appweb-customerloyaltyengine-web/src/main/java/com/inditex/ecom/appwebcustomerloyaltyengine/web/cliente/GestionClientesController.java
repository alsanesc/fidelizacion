package com.inditex.ecom.appwebcustomerloyaltyengine.web.cliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.cliente.GestionClientes;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.AltaCliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Cliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.BadRequestException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.GestionClientesService;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.impl.GestionRecursosServiceImpl;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Utility;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.EstadoCliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.Errores;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.web.common.BaseController;
import com.inditex.ecom.appwebcustomerloyaltyengine.web.common.Naming;

/**
 * The Class GestionClientesController.
 */
@Controller("gestionClientesController")
public class GestionClientesController extends BaseController implements GestionClientes {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory.getLogger(GestionClientesController.class);

    /** The gestion clientes service. */
    @Autowired
    private GestionClientesService gestionClientesService;
    
    /** The gestion recursos service. */
    @Autowired
    private GestionRecursosServiceImpl gestionRecursosService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Secured({ Naming.ROLE_GESTION_USUARIOS })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/rest/v{version}/clientes", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    AltaCliente altaCliente(final @PathVariable("version") String version, @RequestBody Cliente client) throws BadRequestException {
        AltaCliente altaCliente = new AltaCliente();

        final String UUID = String.format(Mensajes.MENSAJE_UUID, Utility.calcularUUID());

        if (client != null) {
            LOGGER.trace(String.format("EL IDENTIFICADOR PARA EL CLIENTE %s SERA %s.", client.toString(), UUID));

            try {
                // Se valida el objeto json de entrada
                Utility.validarJSON(client);
                LOGGER.trace(String.format("%s - Cliente: %s", UUID, client.toStringJSON()));

                altaCliente = gestionClientesService.altaCliente(client, UUID);
            } catch (BadRequestException e) {
                throw new BadRequestException(e.getMessage());
            }
        } else {
            throw new BadRequestException(Errores.OBJETO_JSON_NULO);
        }

        return altaCliente;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Secured({ Naming.ROLE_GESTION_USUARIOS })
    @RequestMapping(value = "/api/rest/v{version}/clientes/{idCliente}/baja", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    AltaCliente bajaCliente(final @PathVariable("version") String version, final @PathVariable("idCliente") String idCliente) throws TimeoutException {

        String UUID = String.format(Mensajes.MENSAJE_UUID, Utility.calcularUUID());

        LOGGER.trace(String.format("EL IDENTIFICADOR PARA LA BAJA %s SERA %s.", idCliente, UUID));

        AltaCliente altaCliente = new AltaCliente();

        try {
            altaCliente.setCliente(gestionClientesService.bajaCliente(idCliente));

            if (altaCliente.getCliente() != null) {
                altaCliente.setCodResultado(Constantes.COD_RESULTADO_SEIS);
                altaCliente.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_SEIS);
            } else {
                altaCliente.setCodResultado(Constantes.COD_RESULTADO_CINCO);
                altaCliente.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_CINCO);
            }
        } catch (TimeoutException e) {
            throw new TimeoutException(e);
        }

        return altaCliente;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Secured({ Naming.ROLE_CONSULTA })
    @RequestMapping(value = "/api/rest/v{version}/clientes/{idCliente}/movimientos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Cliente consultaSaldoMovimientos(final @PathVariable("version") String version, final @PathVariable("idCliente") String idCliente) {

        String UUID = String.format(Mensajes.MENSAJE_UUID, Utility.calcularUUID());

        LOGGER.trace(String.format("EL IDENTIFICADOR PARA LOS MOVIMIENTOS DEL CLIENTE %s SERA %s.", idCliente, UUID));

        return gestionClientesService.movimientos(idCliente);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Secured({ Naming.ROLE_GESTION_USUARIOS })
    @RequestMapping(value = "/api/rest/v{version}/clientes/{idCliente}/recarga", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody AltaCliente recargarPuntos(final @PathVariable("version") String version, final @PathVariable("idCliente") String idCliente, 
            final @RequestParam(value = "idTipoRecurso", required = false) Integer idTipoRecurso, 
            final @RequestParam(value = "recurso", required = false) Integer recurso, 
            final @RequestParam(value = "puntos", required = false) Integer puntos, 
            final @RequestParam(value = "maxPuntos", required = false) Integer maxPuntos,
            final @RequestParam(value = "numPuntos", required = false) Integer numPuntos) throws TimeoutException {
        
        Integer idTipo = null;
        AltaCliente recarga = new AltaCliente();
        
        // Comprobamos si el idRecurso o el idTipoRecurso, viene vacio
        if (recurso == null && idTipoRecurso == null) {
            recarga.setCodResultado(Constantes.COD_RESULTADO_ONCE);
            recarga.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_ONCE);
            
            return recarga;
        }
        
        // Comprobamos si puntos, maxPuntos y numPuntos, vienen vacios
        if (puntos == null && maxPuntos == null && numPuntos == null) {
            recarga.setCodResultado(Constantes.COD_RESULTADO_DOCE);
            recarga.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_DOCE);
            
            return recarga;
        }

        // Comprobamos si el cliente existe y esta activo
        ClienteDto cliente = gestionClientesService.obtenerClienteWallet(idCliente);
        if (cliente == null || cliente.getIdEstadoCliente().compareTo(EstadoCliente.INACTIVO.getValue()) == 0) {
            recarga.setCodResultado(Constantes.COD_RESULTADO_SEIS);
            recarga.setDescripcionMotivoRechazo(Constantes.TEXTO_RESULTADO_SEIS);
            
            return recarga;
        }
        
        // Obtenemos el identificador del recurso
        if (recurso != null) {
            idTipo = gestionRecursosService.obtenerTipoRecurso(recurso.toString());
        } else {
            idTipo = idTipoRecurso;
        }
        
        return gestionClientesService.recargarPuntos(idTipo, cliente, puntos, maxPuntos, numPuntos);
    }

}
