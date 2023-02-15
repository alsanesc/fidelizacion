package com.inditex.ecom.appwebcustomerloyaltyengine.model.common;

/**
 * The Class Cadenas.
 */
public class Cadenas {

	/** The id cadena. */
	private Integer idCadena;

	/** The cod pais. */
	private String codPais;

	/** The tiempo cancelacion. */
	private Integer tiempoCancelacion;

	/**
	 * Instantiates a new cadenas.
	 */
	public Cadenas() {
	}

	/**
	 * Instantiates a new cadenas.
	 * 
	 * @param codPais
	 *            the cod pais
	 * @param tC
	 *            the t c
	 */
	public Cadenas(String codPais, Integer tC) {
		this.codPais = codPais;
		tiempoCancelacion = tC;
	}

	/**
	 * Instantiates a new cadenas.
	 * 
	 * @param idCadena
	 *            the id cadena
	 * @param codPais
	 *            the cod pais
	 * @param tiempoCancelacion
	 *            the tiempo cancelacion
	 */
	public Cadenas(Integer idCadena, String codPais, Integer tiempoCancelacion) {
		this.idCadena = idCadena;
		this.codPais = codPais;
		this.tiempoCancelacion = tiempoCancelacion;
	}

	/**
	 * Gets the id cadena.
	 * 
	 * @return the id cadena
	 */
	public Integer getIdCadena() {
		return idCadena;
	}

	/**
	 * Sets the id cadena.
	 * 
	 * @param idCadena
	 *            the new id cadena
	 */
	public void setIdCadena(Integer idCadena) {
		this.idCadena = idCadena;
	}

	/**
	 * Gets the cod pais.
	 * 
	 * @return the cod pais
	 */
	public String getCodPais() {
		return codPais;
	}

	/**
	 * Sets the cod pais.
	 * 
	 * @param codPais
	 *            the new cod pais
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	/**
	 * Gets the tiempo cancelacion.
	 * 
	 * @return the tiempo cancelacion
	 */
	public Integer getTiempoCancelacion() {
		return tiempoCancelacion;
	}

	/**
	 * Sets the tiempo cancelacion.
	 * 
	 * @param tiempoCancelacion
	 *            the new tiempo cancelacion
	 */
	public void setTiempoCancelacion(Integer tiempoCancelacion) {
		this.tiempoCancelacion = tiempoCancelacion;
	}

}
