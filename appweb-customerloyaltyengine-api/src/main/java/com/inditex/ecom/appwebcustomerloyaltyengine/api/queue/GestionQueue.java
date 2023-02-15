package com.inditex.ecom.appwebcustomerloyaltyengine.api.queue;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Autorizacion;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.OperacionPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.EstadoIncorrectoException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.NumeroSerieException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;

/**
 * <b>Descripcion:</b> Esta Interface determina los metodos del Controlador GestionQueue: nos permitira realizar todas las operaciones relacionadas
 * con colas de PEDIDOS (AUTORIZAR_DESCUENTO, CANCELAR_DESCUENTO, CAPTURAR_DESCUENTO, DEVOLVER_DESCUENTO).
 */
public interface GestionQueue {

    /**
     * Autorizar descuentos.
     * 
     * @param version
     *            the version
     * @param idDescuento
     *            the id descuento
     * @param idPedido
     *            the id pedido
     * @return the autorizacion
     * @throws TimeoutException
     *             the timeout exception
     */
    Autorizacion autorizarDescuentos(final String version, final String idDescuento, final String idPedido) throws TimeoutException;

    /**
     * Capturar descuento.
     * 
     * @param version
     *            the version
     * @param operacionPedido
     *            the operacion pedido
     * @return the operacion pedido
     * @throws NumeroSerieException
     *             the numero serie exception
     * @throws EstadoIncorrectoException
     *             the estado incorrecto exception
     * @throws TimeoutException
     *             the timeout exception
     */
    OperacionPedido capturarDescuento(final String version, OperacionPedido operacionPedido) throws NumeroSerieException,
            EstadoIncorrectoException, TimeoutException;

    /**
     * Devolver descuento.
     * 
     * @param version
     *            the version
     * @param operacionPedido
     *            the operacion pedido
     * @return the operacion pedido
     * @throws NumeroSerieException
     *             the numero serie exception
     * @throws EstadoIncorrectoException
     *             the estado incorrecto exception
     * @throws TimeoutException
     *             the timeout exception
     */
    OperacionPedido devolverDescuento(final String version, OperacionPedido operacionPedido) throws NumeroSerieException,
            EstadoIncorrectoException, TimeoutException;

    /**
     * Cancelar descuento.
     * 
     * @param version
     *            the version
     * @param idOperacionDescuento
     *            the id operacion descuento
     * @throws NumeroSerieException
     *             the numero serie exception
     * @throws EstadoIncorrectoException
     *             the estado incorrecto exception
     * @throws TimeoutException
     *             the timeout exception
     */
    void cancelarDescuento(final String version, String idOperacionDescuento) throws NumeroSerieException, EstadoIncorrectoException,
            TimeoutException;

}
