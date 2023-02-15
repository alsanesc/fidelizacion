package com.inditex.ecom.appwebcustomerloyaltyengine.model.api;

import java.util.List;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.AsignacionPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Pedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyOperacionDescuentoDto;

/**
 * <b>Descripcion:</b> Esta Interface determina las operaciones del Service de GestionPedidosService: definira todas las operaciones relacionadas con
 * PEDIDOS (CALCULAR_DESCUENTO, AUTORIZAR_DESCUENTO, CONSULTAR_DESCUENTO).
 */
public interface GestionPedidosService {

    /**
     * Generamos el descuento de un pedido.
     * 
     * @param pedidoDTO
     *            the pedido dto
     * @param UUID
     *            the uuid
     * @return the asignacion pedido dto
     */
    AsignacionPedido generarDescuento(Pedido pedidoDTO, String UUID);

    /**
     * Consultamos el estado descuento de un pedido.
     * 
     * @param lineaDescuento
     *            the linea descuento
     * @param descuento
     *            the descuento
     * @param cliente
     *            the cliente
     * @return the pedido
     */
    Pedido consultaEstadoDescuento(List<LineaDescuentoDto> lineaDescuento, DescuentoDto descuento, ClienteDto cliente);

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
     * Obtener linea descuento.
     * 
     * @param descuento
     *            the descuento
     * @param estadoDescuento
     *            the estado descuento
     * @return the list
     */
    List<LineaDescuentoDto> obtenerLineaDescuento(PrimaryKeyDescuentoDto descuento, Short estadoDescuento);
    
    /**
     * Obtener linea descuento.
     * 
     * @param idDescuento
     *            the id descuento
     * @return the list
     */
    List<LineaDescuentoDto> obtenerLineaDescuento(PrimaryKeyDescuentoDto idDescuento);

    /**
     * Obtener cliente.
     * 
     * @param string
     *            the string
     * @return the cliente dto
     */
    ClienteDto obtenerCliente(PrimaryKeyClienteDto clienteDto);

    /**
     * Obtener operacion descuento.
     * 
     * @param idOperacionDescuento
     *            the id operacion descuento
     * @return the operacion descuento dto
     */
    OperacionDescuentoDto obtenerOperacionDescuento(PrimaryKeyOperacionDescuentoDto idOperacionDescuento);

    /**
     * Obtener id descuento.
     * 
     * @param lineaPedido
     *            the id linea pedido
     * @return the long
     */
    DescuentoDto obtenerIdDescuento(String lineaPedido);

    /**
     * Obtener id pedido.
     * 
     * @param idDescuento
     *            the id descuento
     * @return the string
     */
    String obtenerIdPedido(Long idDescuento);

    /**
     * Obtener codigo pedido.
     * 
     * @param idDescuento
     *            the id descuento
     * @return the string
     */
    String obtenerCodigoPedido(PrimaryKeyDescuentoDto idDescuento);

}
