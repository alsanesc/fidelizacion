package com.inditex.ecom.appwebcustomerloyaltyengine.api.promociones;

import java.sql.Timestamp;
import java.util.List;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Recarga;

/**
 * The Interface GestionPromociones.
 */
public interface GestionPromociones {

    /**
     * Cargar promociones.
     * 
     * @param version
     *            the version
     * @param recarga
     *            the recarga
     * @return the recarga
     */
    Recarga cargarPromociones(final String version, final Recarga recarga);

    /**
     * Buscar promociones.
     * 
     * @param version
     *            the version
     * @param idRecarga
     *            the id recarga
     * @param fechaHasta
     *            the fecha hasta
     * @param fechaDesde
     *            the fecha desde
     * @param idCadena
     *            the id cadena
     * @param idPais
     *            the id pais
     * @param idTipoTienda
     *            the id tipo tienda
     * @param idTienda
     *            the id tienda
     * @param activa
     *            the activa
     * @param procesada
     *            the procesada
     * @return the list
     */
    List<Recarga> buscarPromociones(final String version, final Integer idRecarga, final Timestamp fechaHasta, final Timestamp fechaDesde,
            final Integer idCadena, final Integer idPais, final Integer idTipoTienda, final Integer idTienda, final Boolean activa,
            final Boolean procesada);

    /**
     * Desactivar promocion.
     * 
     * @param version
     *            the version
     * @param idCargaPromocion
     *            the id carga promocion
     */
    void desactivarPromocion(final String version, final Integer idCargaPromocion);

}
