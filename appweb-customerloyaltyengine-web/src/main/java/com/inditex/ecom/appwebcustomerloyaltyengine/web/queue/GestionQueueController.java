package com.inditex.ecom.appwebcustomerloyaltyengine.web.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Autorizacion;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.OperacionPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.EstadoIncorrectoException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.NumeroSerieException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.queue.GestionQueue;
import com.inditex.ecom.appwebcustomerloyaltyengine.mdb.api.GestionPedidosMQService;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.GestionPedidosService;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Utility;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyOperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.web.common.BaseController;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion del Controlador GestionQueue: nos permitira realizar todas las operaciones relacionadas
 * con colas de PEDIDOS (AUTORIZAR_DESCUENTO, CANCELAR_DESCUENTO, CAPTURAR_DESCUENTO, DEVOLVER_DESCUENTO).
 */
@Controller("gestionQueueController")
public class GestionQueueController extends BaseController implements GestionQueue {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestionQueueController.class);

    /** The gestion descuentos service. */
    @Autowired
    private GestionPedidosMQService gestionPedidosMQService;
    
    /** The gestion pedidos service. */
    @Autowired
    private GestionPedidosService gestionPedidosService;
    
    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/api/rest/v{version}/descuentos/{idDescuento}/autorizar/{idPedido}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Autorizacion autorizarDescuentos(final @PathVariable("version") String version, final @PathVariable("idDescuento") String idDescuento,
            final @PathVariable("idPedido") String idPedido) throws TimeoutException {

        String UUID = String.format(Mensajes.MENSAJE_UUID, Utility.calcularUUID());

        LOGGER.trace(String.format("EL IDENTIFICADOR PARA EL PEDIDO %s CON EL DESCUENTO %s SERA %s.", idPedido, idDescuento, UUID));

        Autorizacion autorizacion = new Autorizacion();

        try {
            autorizacion = gestionPedidosMQService.autorizarDescuentos(idDescuento, idPedido, UUID);
        } catch (TimeoutException e) {
            // Timeout a la hora de realizar la persistencia
            throw new TimeoutException(e);
        }

        return autorizacion;
    }
    
    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/api/rest/v{version}/capturar", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    OperacionPedido capturarDescuento(final @PathVariable("version") String version, @RequestBody OperacionPedido operacionPedido)
            throws TimeoutException, NumeroSerieException, EstadoIncorrectoException {

        OperacionPedido operacionPedidoCaptura = new OperacionPedido();

        try {
            String UUID = String.format(Mensajes.MENSAJE_UUID, Utility.calcularUUID());

            LOGGER.trace(String.format("EL IDENTIFICADOR PARA EL PEDIDO %s SERA %s.", operacionPedido.getLineasPedidoAfectadas().get(0).getIdLineaPedido().toString(), UUID));

            operacionPedidoCaptura = gestionPedidosMQService.capturarDescuento(operacionPedido, UUID);
        } catch (NumeroSerieException e) {
            throw new NumeroSerieException(e.getMessage());
        } catch (EstadoIncorrectoException e) {
            throw new EstadoIncorrectoException(e.getMessage());
        } catch (TimeoutException e) {
            throw new TimeoutException(e.getMessage());
        }

        return operacionPedidoCaptura;
    }
    
    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/api/rest/v{version}/devolver", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    OperacionPedido devolverDescuento(final @PathVariable("version") String version, @RequestBody OperacionPedido operacionPedido)
            throws TimeoutException, NumeroSerieException, EstadoIncorrectoException {

        OperacionPedido operacionPedidoDevolucion = new OperacionPedido();

        try {
            final String UUID = String.format(Mensajes.MENSAJE_UUID, Utility.calcularUUID());

            LOGGER.trace(String.format("EL IDENTIFICADOR PARA EL PEDIDO %s SERA %s.", operacionPedido.getLineasPedidoAfectadas().get(0).getIdLineaPedido().toString(), UUID));

            operacionPedidoDevolucion = gestionPedidosMQService.devolverDescuento(operacionPedido, UUID);
        } catch (NumeroSerieException e) {
            throw new NumeroSerieException(e.getMessage());
        } catch (EstadoIncorrectoException e) {
            throw new EstadoIncorrectoException(e.getMessage());
        } catch (TimeoutException e) {
            throw new TimeoutException(e.getMessage());
        }

        return operacionPedidoDevolucion;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws TimeoutException
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/rest/v{version}/cancelar/{idOperacionDescuento}", method = RequestMethod.GET)
    public void cancelarDescuento(final @PathVariable("version") String version, final @PathVariable("idOperacionDescuento") String idOperacionDescuento) 
        throws NumeroSerieException, EstadoIncorrectoException, TimeoutException {

        try {
            String UUID = String.format(Mensajes.MENSAJE_UUID, Utility.calcularUUID());

            LOGGER.trace(String.format("EL IDENTIFICADOR PARA LA OPERACION DESCUENTO %s SERA %s.", idOperacionDescuento, UUID));
            
            OperacionDescuentoDto operacionDescuento = gestionPedidosService.obtenerOperacionDescuento(new PrimaryKeyOperacionDescuentoDto(idOperacionDescuento));
            String idDescuento = String.format(Mensajes.INSTALACION, operacionDescuento.getIdDescuento().getIdDescuento(), operacionDescuento.getIdDescuento().getIdInstalacion());

            gestionPedidosMQService.cancelarDescuento(gestionPedidosService.obtenerCodigoPedido(operacionDescuento.getIdDescuento()), idDescuento, null, UUID);
        } catch (NumeroSerieException e) {
            throw new NumeroSerieException(e.getMessage());
        } catch (EstadoIncorrectoException e) {
            throw new EstadoIncorrectoException(e.getMessage());
        } catch (TimeoutException e) {
            throw new TimeoutException(e.getMessage());
        }
    }

}
