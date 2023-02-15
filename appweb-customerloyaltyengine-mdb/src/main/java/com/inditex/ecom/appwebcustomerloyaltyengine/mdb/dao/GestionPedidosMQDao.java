package com.inditex.ecom.appwebcustomerloyaltyengine.mdb.dao;

import java.math.BigDecimal;
import java.util.List;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.NumeroSerieException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;

/**
 * The Interface GestionDescuentosDao.
 */
public interface GestionPedidosMQDao {

    /**
     * Persistir captura.
     * 
     * @param cliente
     *            the cliente
     * @param descuento
     *            the descuento
     * @param tarjetaList
     *            the tarjeta list
     * @param operacionDescuento
     *            the operacion descuento
     * @param operacionRecursoList
     *            the operacion tarjeta list
     * @param lineas
     *            the lineas
     * @param importe
     *            the importe
     * @param UUID
     *            the uuid
     * @param estadoDescuento
     *            the estado descuento
     * @return the boolean
     * @throws TimeoutException
     *             the timeout exception
     * @throws NumeroSerieException
     *             the numero serie exception
     */
    Boolean persistirCaptura(ClienteDto cliente, DescuentoDto descuento, List<RecursoDto> tarjetaList, OperacionDescuentoDto operacionDescuento,
            List<OperacionRecursoDto> operacionRecursoList, List<LineaDescuentoDto> lineas, BigDecimal importe, String UUID, Short estadoDescuento)
            throws TimeoutException, NumeroSerieException;

}
