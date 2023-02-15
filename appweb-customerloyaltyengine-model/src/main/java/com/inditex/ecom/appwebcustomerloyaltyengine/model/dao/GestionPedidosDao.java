package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao;

import java.util.List;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.NumeroSerieException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DetalleDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyOperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;

/**
 * <b>Descripcion:</b> Esta Interface determina las operaciones de Hibernate de GestionPedidosDao: definira todas las operaciones relacionadas con
 * PEDIDOS (CALCULAR_DESCUENTO, AUTORIZAR_DESCUENTO, CONSULTAR_DESCUENTO).
 */
public interface GestionPedidosDao {

    /**
     * Autorizacion descuento.
     * 
     * @param operDescuento
     *            the oper descuento
     * @param operacionesTarjetaList
     *            the operaciones tarjeta list
     * @param cliente
     *            the cliente
     * @param descuento
     *            the descuento
     * @param tarjetaList
     *            the tarjeta list
     * @param detalleDescuento
     *            the detalle descuento
     * @param UUID
     *            the uuid
     * @return the primary key operacion descuento dto
     * @throws TimeoutException
     *             the timeout exception
     * @throws NumeroSerieException
     *             the numero serie exception
     */
    PrimaryKeyOperacionDescuentoDto autorizacionDescuento(OperacionDescuentoDto operDescuento, List<OperacionRecursoDto> operacionesTarjetaList,
            ClienteDto cliente, DescuentoDto descuento, List<RecursoDto> tarjetaList, DetalleDescuentoDto detalleDescuento, String UUID)
            throws TimeoutException, NumeroSerieException;

    /**
     * Calcular descuento.
     * 
     * @param descuento
     *            the descuento
     * @param detalleDescuentoList
     *            the detalle descuento list
     * @param lineaDescuentoList
     *            the linea descuento list
     */
    void calcularDescuento(DescuentoDto descuento, List<DetalleDescuentoDto> detalleDescuentoList, List<LineaDescuentoDto> lineaDescuentoList);

}
