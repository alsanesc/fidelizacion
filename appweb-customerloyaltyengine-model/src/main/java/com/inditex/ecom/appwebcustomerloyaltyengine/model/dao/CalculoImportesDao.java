package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao;

import java.math.BigDecimal;

import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyDescuentoDto;

/**
 * <b>Descripcion:</b> Este Interface determina las operaciones DAO sobre calculo de importes.
 */
public interface CalculoImportesDao {

    /**
     * Calcular autorizado.
     * 
     * @param idDescuento
     *            the id descuento
     * @return the big decimal
     */
    BigDecimal calcularAutorizado(PrimaryKeyDescuentoDto idDescuento);

    /**
     * Calcular capturado.
     * 
     * @param idDescuento
     *            the id descuento
     * @return the big decimal
     */
    BigDecimal calcularCapturado(PrimaryKeyDescuentoDto idDescuento);

    /**
     * Calcular devuelto.
     * 
     * @param idDescuento
     *            the id descuento
     * @return the big decimal
     */
    BigDecimal calcularDevuelto(PrimaryKeyDescuentoDto idDescuento);

    /**
     * Calcular pendiente devolver.
     * 
     * @param idDescuento
     *            the id descuento
     * @return the big decimal
     */
    BigDecimal calcularPendienteDevolver(PrimaryKeyDescuentoDto idDescuento);

}
