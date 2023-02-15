package com.inditex.ecom.appwebcustomerloyaltyengine.mdb.api;

import java.util.List;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Autorizacion;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.OperacionPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.EstadoIncorrectoException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.NumeroSerieException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;

/**
 * The Interface GestionDescuentosService.
 */
public interface GestionPedidosMQService {

    /**
     * Autorizamos el descuento de un pedido.
     * 
     * @param idDescuento
     *            the id descuento
     * @param idPedido
     *            the id pedido
     * @param UUID
     *            the uuid
     * @return the string
     * @throws TimeoutException
     *             the timeout exception
     */
    Autorizacion autorizarDescuentos(String idDescuento, String idPedido, String UUID) throws TimeoutException;

    /**
     * Capturar descuento.
     * 
     * @param operacionPedido
     *            the operacion pedido
     * @param UUID
     *            the uuid
     * @return the operacion pedido
     * @throws TimeoutException
     *             the timeout exception
     * @throws NumeroSerieException
     *             the numero serie exception
     * @throws EstadoIncorrectoException
     *             the estado incorrecto exception
     */
    OperacionPedido capturarDescuento(OperacionPedido operacionPedido, String UUID) throws TimeoutException, NumeroSerieException, EstadoIncorrectoException;

    /**
     * Cancelar descuento.
     * 
     * @param idPedido
     *            the id pedido
     * @param idDescuento
     *            the id descuento
     * @param lineas
     *            the lineas
     * @param UUID
     *            the uuid
     * @throws TimeoutException
     *             the timeout exception
     * @throws NumeroSerieException
     *             the numero serie exception
     * @throws EstadoIncorrectoException
     *             the estado incorrecto exception
     */
    void cancelarDescuento(String idPedido, String idDescuento, List<LineaDescuentoDto> lineas, String UUID) throws TimeoutException, NumeroSerieException, EstadoIncorrectoException;

    /**
     * Devolver descuento.
     * 
     * @param operacionPedido
     *            the operacion pedido
     * @param UUID
     *            the uuid
     * @return the operacion pedido
     * @throws TimeoutException
     *             the timeout exception
     * @throws NumeroSerieException
     *             the numero serie exception
     * @throws EstadoIncorrectoException
     *             the estado incorrecto exception
     */
    OperacionPedido devolverDescuento(OperacionPedido operacionPedido, String UUID) throws TimeoutException, NumeroSerieException, EstadoIncorrectoException;

}
