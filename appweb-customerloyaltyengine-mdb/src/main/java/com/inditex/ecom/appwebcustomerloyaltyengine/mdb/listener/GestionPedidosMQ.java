package com.inditex.ecom.appwebcustomerloyaltyengine.mdb.listener;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.Converter;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.LineaPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.OperacionPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.EstadoIncorrectoException;
import com.inditex.ecom.appwebcustomerloyaltyengine.mdb.api.GestionPedidosMQService;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.GestionPedidosService;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoLineaPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Utility;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.Errores;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.MensajesDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;

/**
 * The listener interface for receiving queue events. The class that is interested in processing a queue event implements this interface, and the
 * object created with that class is registered with a component using the component's <code>addQueueListener<code> method. When
 * the queue event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see QueueEvent
 */
@Component("messageListener")
public class GestionPedidosMQ implements MessageListener {

    /** The Constant logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestionPedidosMQ.class);

    /** The gestion descuentos service. */
    @Autowired
    private GestionPedidosMQService gestionPedidosMQService;

    /** The gestion pedidos service. */
    @Autowired
    private GestionPedidosService gestionPedidosService;
    
    /******************************************************************************************************************************
     * 
     * ACCIONES DE GESTION PEDIDOS MQ
     * 
     ******************************************************************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String UUID = String.format(Mensajes.MENSAJE_UUID, Utility.calcularUUID());

                String body = ((TextMessage) message).getText();

                OperacionPedido operPedido = new OperacionPedido();
                operPedido = (OperacionPedido) Converter.convertToObject(body, operPedido);

                LOGGER.trace(operPedido.toStringJSON());

                // Comprobamos el tipo de operacion
                if (operPedido.getTipoOperacion().equals(Comunes.AUTHORIZE_OPERATION_TYPE)) {
                    // Se trata de una operacion de autorizacion
                    DescuentoDto descuento = gestionPedidosService.obtenerDescuentoPorCodigo(operPedido.getCodOperacionExterno());

                    String idDescuento = String.format(Mensajes.INSTALACION, descuento.getIdDescuento().getIdDescuento(), descuento.getIdDescuento().getIdInstalacion());
                    String idPedido = descuento.getCodigoPedidoExterno();

                    if (descuento == null || idPedido == null) {
                        throw new EstadoIncorrectoException(String.format(MensajesDescuento.DESCUENTO_NO_DADO_ALTA));
                    }

                    gestionPedidosMQService.autorizarDescuentos(idDescuento, idPedido, UUID);

                } else if (operPedido.getTipoOperacion().equals(Comunes.CAPTURE_OPERATION_TYPE)) {
                    // Se trata de una operacion de captura
                    gestionPedidosMQService.capturarDescuento(operPedido, UUID);

                } else if (operPedido.getTipoOperacion().equals(Comunes.REFUND_OPERATION_TYPE)) {
                    // Se trata de una operacion de devolucion
                    gestionPedidosMQService.devolverDescuento(operPedido, UUID);

                } else if (operPedido.getTipoOperacion().equals(Comunes.CANCEL_OPERATION_TYPE)) {
                    // Se trata de una operacion de cancelacion
                    DescuentoDto descuento = gestionPedidosService.obtenerIdDescuento(operPedido.getCodOperacionExterno());

                    String idDescuento = String.format(Mensajes.INSTALACION, descuento.getIdDescuento().getIdDescuento(), descuento.getIdDescuento().getIdInstalacion());
                    String idPedido = descuento.getCodigoPedidoExterno();
                    List<LineaDescuentoDto> lineas = generarLineasDescuento(operPedido.getLineasPedidoAfectadas());

                    if (idDescuento == null || idPedido == null) {
                        throw new EstadoIncorrectoException(String.format(MensajesDescuento.DESCUENTO_NO_DADO_ALTA));
                    }

                    gestionPedidosMQService.cancelarDescuento(idPedido, idDescuento, lineas, UUID);

                } else {
                    LOGGER.error(String.format(Errores.NO_EXISTE_TIPO_MENSAJE));
                }
            } else {
                LOGGER.error(String.format(Errores.NO_MENSAJE_TEXTMESSAGE));
            }
        } catch (Exception e) {
            LOGGER.error(String.format(Errores.ERROR_EN_COLA, e.getMessage()));
        }
    }
    
    /******************************************************************************************************************************
     * 
     * ACCIONES PRIVADAS DE GESTION PEDIDOS MQ
     * 
     ******************************************************************************************************************************/

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
        
        for(LineaPedido lineaPedido : lineasPedidoAfectadas) {
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
