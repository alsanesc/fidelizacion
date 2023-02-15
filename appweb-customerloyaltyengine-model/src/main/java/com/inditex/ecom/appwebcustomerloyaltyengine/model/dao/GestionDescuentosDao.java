package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao;

import java.util.List;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Cadenas;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DetalleDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyDetalleDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyOperacionDescuentoDto;

/**
 * <b>Descripcion:</b> Esta Interface determina la declaracion de Hibernate de GestionDescuentosDao: definira todas las operaciones relacionadas con
 * las tablas de DESCUENTO.
 */
public interface GestionDescuentosDao {

    /**
     * Obtener descuento por codigo.
     * 
     * @param descuento
     *            the descuento
     * @return the descuento dto
     */
    DescuentoDto obtenerDescuentoPorCodigo(String descuento);

    /**
     * Obtener descuento.
     * 
     * @param descuento
     *            the descuento
     * @return the descuento dto
     */
    DescuentoDto obtenerDescuento(PrimaryKeyDescuentoDto descuento);

    /**
     * Obtener descuentos, para una determinada cadena.
     * 
     * @param estadoDescuento
     *            the estado descuento
     * @param cadena
     *            the cadena
     * @return the list
     */
    List<DescuentoDto> obtenerDescuentos(Short estadoDescuento, Cadenas cadena);

    /**
     * Obtener detalle descuento.
     * 
     * @param detalleDescuento
     *            the detalle descuento
     * @return the detalle descuento dto
     */
    DetalleDescuentoDto obtenerDetalleDescuento(PrimaryKeyDetalleDescuentoDto detalleDescuento);

    /**
     * Obtener detalle descuento.
     * 
     * @param idDescuento
     *            the id descuento
     * @param partNumber
     *            the part number
     * @return the detalle descuento dto
     */
    DetalleDescuentoDto obtenerDetalleDescuento(PrimaryKeyDescuentoDto idDescuento, String partNumber);

    /**
     * Obtener operacion descuento.
     * 
     * @param descuento
     *            the id descuento
     * @param tipoOperacionDescuento
     *            the tipo operacion descuento
     * @return the list
     */
    List<Long> obtenerIdOperacionDescuento(PrimaryKeyDescuentoDto descuento, Short tipoOperacionDescuento);

    /**
     * Obtener operacion descuento.
     * 
     * @param descuento
     *            the descuento
     * @param tipoOperacionDescuento
     *            the tipo operacion descuento
     * @return the list
     */
    List<OperacionDescuentoDto> obtenerOperacionDescuento(PrimaryKeyDescuentoDto descuento, Short tipoOperacionDescuento);

    /**
     * Obtener linea descuento.
     * 
     * @param descuento
     *            the id descuento
     * @param estadoDescuento
     *            the estado descuento
     * @return the list
     */
    List<LineaDescuentoDto> obtenerLineaDescuento(PrimaryKeyDescuentoDto descuento, Short estadoDescuento);

    /**
     * Obtener linea descuento.
     * 
     * @param descuento
     *            the descuento
     * @return the list
     */
    List<LineaDescuentoDto> obtenerLineaDescuento(PrimaryKeyDescuentoDto descuento);

    /**
     * Obtener linea descuento.
     * 
     * @param idPedido
     *            the id pedido
     * @return the list
     */
    List<LineaDescuentoDto> obtenerLineaDescuento(String idPedido);

    /**
     * Actualizar descuento.
     * 
     * @param descuento
     *            the descuento
     * @param estadoDescuento
     *            the estado descuento
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarDescuento(DescuentoDto descuento, Short estadoDescuento) throws TimeoutException;

    /**
     * Actualizar lineas descuento.
     * 
     * @param descuento
     *            the descuento
     * @param estadoDescuento
     *            the estado descuento
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarLineasDescuento(DescuentoDto descuento, Short estadoDescuento) throws TimeoutException;

    /**
     * Actualizar linea descuento.
     * 
     * @param descuento
     *            the descuento
     * @param linea
     *            the linea
     * @param estadoDescuento
     *            the estado descuento
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarLineaDescuentoCaptura(DescuentoDto descuento, LineaDescuentoDto linea, Short estadoDescuento) throws TimeoutException;

    /**
     * Actualizar linea descuento captura.
     * 
     * @param descuento
     *            the descuento
     * @param estadoDescuento
     *            the estado descuento
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarLineaDescuentoCaptura(DescuentoDto descuento, Short estadoDescuento) throws TimeoutException;

    /**
     * Actualizar linea descuento devolucion.
     * 
     * @param descuento
     *            the descuento
     * @param linea
     *            the linea
     * @param estadoDescuento
     *            the estado descuento
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarLineaDescuentoDevolucion(DescuentoDto descuento, LineaDescuentoDto linea, Short estadoDescuento) throws TimeoutException;

    /**
     * Actualizar linea descuento devolucion.
     * 
     * @param descuento
     *            the descuento
     * @param estadoDescuento
     *            the estado descuento
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarLineaDescuentoDevolucion(DescuentoDto descuento, Short estadoDescuento) throws TimeoutException;

    /**
     * Actualizar linea descuento.
     * 
     * @param descuento
     *            the descuento
     * @param linea
     *            the linea
     * @param estadoDescuento
     *            the estado descuento
     */
    void actualizarLineaDescuento(DescuentoDto descuento, LineaDescuentoDto linea, Short estadoDescuento) throws TimeoutException;
    
    /**
     * Obtener descuento linea.
     * 
     * @param codOperacionExterno
     *            the cod operacion externo
     * @return the descuento dto
     */
    DescuentoDto obtenerDescuentoOperacion(String codOperacionExterno);

    /**
     * Obtener operacion descuento.
     * 
     * @param idOperacionDescuento
     *            the id operacion descuento
     * @return the operacion descuento dto
     */
    OperacionDescuentoDto obtenerOperacionDescuento(PrimaryKeyOperacionDescuentoDto idOperacionDescuento);

    /**
     * Obtener codigo pedido.
     * 
     * @param idDescuento
     *            the id descuento
     * @return the string
     */
    String obtenerCodigoPedido(PrimaryKeyDescuentoDto idDescuento);

}
