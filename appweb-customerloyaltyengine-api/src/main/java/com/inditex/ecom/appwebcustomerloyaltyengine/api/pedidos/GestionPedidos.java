package com.inditex.ecom.appwebcustomerloyaltyengine.api.pedidos;

import javax.jms.JMSException;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.AsignacionPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Pedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.BadRequestException;

/**
 * The Interface GestionPedidos.
 */
public interface GestionPedidos {

    /**
     * Generar descuentos.
     * 
     * @param version
     *            the version
     * @param pedidoDTO
     *            the pedido dto
     * @return the asignacion pedido
     * @throws BadRequestException
     *             the bad request exception
     */
    AsignacionPedido generarDescuentos(final String version, final Pedido pedidoDTO) throws BadRequestException;

    /**
     * Consultar estado pedidos.
     * 
     * @param version
     *            the version
     * @param idPedido
     *            the id pedido
     * @return the pedido
     */
    Pedido consultarEstadoPedidos(final String version, final String idPedido);

    /**
     * Consultar estado descuentos.
     * 
     * @param version
     *            the version
     * @param idDescuento
     *            the id descuento
     * @return the pedido
     */
    Pedido consultarEstadoDescuentos(final String version, final String idDescuento);

    /**
     * Send message.
     * 
     * @param version
     *            the version
     * @param orderOperation
     *            the order operation
     * @throws JMSException
     *             the jMS exception
     */
    void sendMessage(final String version, final String orderOperation) throws JMSException;

}
