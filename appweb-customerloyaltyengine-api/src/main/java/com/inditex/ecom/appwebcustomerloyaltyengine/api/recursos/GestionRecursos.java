package com.inditex.ecom.appwebcustomerloyaltyengine.api.recursos;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Tarjeta;

/**
 * The Interface GestionRecursos.
 */
public interface GestionRecursos {

    /**
     * Consultar tipo recurso.
     * 
     * @param version
     *            the version
     * @param recurso
     *            the recurso
     * @return the tarjeta
     */
    Tarjeta consultarTipoRecurso(final String version, final String recurso);

}
