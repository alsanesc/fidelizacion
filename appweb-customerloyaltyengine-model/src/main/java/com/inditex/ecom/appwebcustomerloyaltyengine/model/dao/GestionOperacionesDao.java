package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao;

import java.util.List;

import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;

/**
 * <b>Descripcion:</b> Esta Interface determina la declaracion de Hibernate de GestionOperacionesDao: definira todas las operaciones relacionadas con
 * las tablas de OPERACIONES_RECURSO.
 */
public interface GestionOperacionesDao {

    /**
     * Obtener operacion recurso.
     * 
     * @param operacionDescuentoList
     *            the operacion descuento list
     * @return the list
     */
    List<OperacionRecursoDto> obtenerOperacionRecurso(List<Long> operacionDescuentoList);

    /**
     * Obtener operacion recurso.
     * 
     * @param recurso
     *            the recurso
     * @return the list
     */
    List<OperacionRecursoDto> obtenerOperacionRecurso(RecursoDto recurso);

}
