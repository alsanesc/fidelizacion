package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao;

/**
 * <b>Descripcion:</b> Esta Interface determina la declaracion de Hibernate de GestionMaestrosDao: definira todas las operaciones relacionadas con el
 * esquema de MAESTROS.
 */
public interface GestionMaestrosDao {

    /**
     * Obtener id cadena.
     * 
     * @param idTienda
     *            the id tienda
     * @return the integer
     */
    Integer obtenerIdCadena(Integer idTienda);

    /**
     * Obtener id divisa.
     * 
     * @param divisa
     *            the divisa
     * @return the short
     */
    Short obtenerIdDivisa(String divisa);

    /**
     * Obtener id pais.
     * 
     * @param codPais
     *            the cod pais
     * @return the short
     */
    Short obtenerIdPais(String codPais);

    /**
     * Obtener cod pais.
     * 
     * @param idPais
     *            the id pais
     * @return the string
     */
    String obtenerCodPais(Short idPais);

}
