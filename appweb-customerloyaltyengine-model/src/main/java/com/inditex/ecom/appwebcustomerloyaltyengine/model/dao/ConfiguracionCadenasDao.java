package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao;

import java.util.List;

import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Cadenas;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;

/**
 * <b>Descripcion:</b> Esta Interface determina la declaracion de Hibernate de
 * ConfiguracionCadenasDao: definira todas las operaciones relacionadas con la
 * configuracion para cada cadena.
 */
public interface ConfiguracionCadenasDao {

	/**
	 * Comprobar configuracion cadena/pais.
	 * 
	 * @param cadenas
	 *            the cadenas
	 * @return true, if successful
	 */
	boolean comprobarCadenaPais(Cadenas cadenas);

	
    /**
     * Obtener recurso.
     * 
     * @param cadenas
     *            the cadenas
     * @param saldoPuntos
     *            the saldo puntos
     * @return the recurso dto
     */
    RecursoDto obtenerRecurso(Cadenas cadenas, Integer saldoPuntos);

	/**
	 * Obtener idCadena/idPais/CodigoPaisISO de cada una de las cadenas.
	 * 
	 * @return the list
	 */
	List<Cadenas> obtenerCadenas();

}
