package com.inditex.ecom.appwebcustomerloyaltyengine.model.api;

/**
 * <b>Descripcion:</b> Esta Interface determina las operaciones del Service de GestionRecursosService: definira todas las operaciones relacionadas con
 * RECURSOS.
 */
public interface GestionRecursosService {

    /**
     * Obtener tipo recurso.
     * 
     * @param recurso
     *            the recurso
     * @return the integer
     */
    Integer obtenerTipoRecurso(String recurso);

}
