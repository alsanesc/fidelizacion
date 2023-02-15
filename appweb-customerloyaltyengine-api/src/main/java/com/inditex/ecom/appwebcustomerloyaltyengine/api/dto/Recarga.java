package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import java.util.List;

import net.sf.oval.constraint.NotNull;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class RecargaDto.
 */
public class Recarga extends BaseObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id carga promocion. */
	private Integer idCargaPromocion;

	/** The fecha hora activacion. */
	private String fechaHoraActivacion;

	/** The fecha hora desactivacion. */
	private String fechaHoraDesactivacion;

	/** The id cadena. */
	private Integer idCadena;

	/** The id pais. */
	private String codPais;

	/** The id tipo tienda. */
	private Integer idTipoTienda;

	/** The id tienda. */
	private Integer idTienda;

	/** The id grupo reglas. */
	@NotNull
	private Integer idGrupoReglas;

	/** The id version reglas. */
	@NotNull
	private Integer idVersionReglas;

	/** The id tipo promocion. */
	@NotNull
	private Integer idTipoPromocion;

	/** The lista tiendas afectadas. */
	private List<Integer> listaTiendasAfectadas;

	/** The activa. */
	private Boolean activa;

	/** The procesada. */
	private Boolean procesada;

	/**
	 * Instantiates a new recarga dto.
	 */
	public Recarga() {
	}

	/**
	 * Gets the id carga promocion.
	 * 
	 * @return the id carga promocion
	 */
	public Integer getIdCargaPromocion() {
		return idCargaPromocion;
	}

	/**
	 * Sets the id carga promocion.
	 * 
	 * @param idCargaPromocion
	 *            the new id carga promocion
	 */
	public void setIdCargaPromocion(Integer idCargaPromocion) {
		this.idCargaPromocion = idCargaPromocion;
	}

	/**
	 * Gets the fecha hora activacion.
	 * 
	 * @return the fecha hora activacion
	 */
	public String getFechaHoraActivacion() {
		return fechaHoraActivacion;
	}

	/**
	 * Sets the fecha hora activacion.
	 * 
	 * @param fechaHoraActivacion
	 *            the new fecha hora activacion
	 */
	public void setFechaHoraActivacion(String fechaHoraActivacion) {
		this.fechaHoraActivacion = fechaHoraActivacion;
	}

	/**
	 * Gets the fecha hora desactivacion.
	 * 
	 * @return the fecha hora desactivacion
	 */
	public String getFechaHoraDesactivacion() {
		return fechaHoraDesactivacion;
	}

	/**
	 * Sets the fecha hora desactivacion.
	 * 
	 * @param fechaHoraDesactivacion
	 *            the new fecha hora desactivacion
	 */
	public void setFechaHoraDesactivacion(String fechaHoraDesactivacion) {
		this.fechaHoraDesactivacion = fechaHoraDesactivacion;
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
	 * Gets the id tipo tienda.
	 * 
	 * @return the id tipo tienda
	 */
	public Integer getIdTipoTienda() {
		return idTipoTienda;
	}

	/**
	 * Sets the id tipo tienda.
	 * 
	 * @param idTipoTienda
	 *            the new id tipo tienda
	 */
	public void setIdTipoTienda(Integer idTipoTienda) {
		this.idTipoTienda = idTipoTienda;
	}

	/**
	 * Gets the id tienda.
	 * 
	 * @return the id tienda
	 */
	public Integer getIdTienda() {
		return idTienda;
	}

	/**
	 * Sets the id tienda.
	 * 
	 * @param idTienda
	 *            the new id tienda
	 */
	public void setIdTienda(Integer idTienda) {
		this.idTienda = idTienda;
	}

	/**
	 * Gets the id grupo reglas.
	 * 
	 * @return the id grupo reglas
	 */
	public Integer getIdGrupoReglas() {
		return idGrupoReglas;
	}

	/**
	 * Sets the id grupo reglas.
	 * 
	 * @param idGrupoReglas
	 *            the new id grupo reglas
	 */
	public void setIdGrupoReglas(Integer idGrupoReglas) {
		this.idGrupoReglas = idGrupoReglas;
	}

	/**
	 * Gets the id version reglas.
	 * 
	 * @return the id version reglas
	 */
	public Integer getIdVersionReglas() {
		return idVersionReglas;
	}

	/**
	 * Sets the id version reglas.
	 * 
	 * @param idVersionReglas
	 *            the new id version reglas
	 */
	public void setIdVersionReglas(Integer idVersionReglas) {
		this.idVersionReglas = idVersionReglas;
	}

	/**
	 * Gets the id tipo promocion.
	 * 
	 * @return the id tipo promocion
	 */
	public Integer getIdTipoPromocion() {
		return idTipoPromocion;
	}

	/**
	 * Sets the id tipo promocion.
	 * 
	 * @param idTipoPromocion
	 *            the new id tipo promocion
	 */
	public void setIdTipoPromocion(Integer idTipoPromocion) {
		this.idTipoPromocion = idTipoPromocion;
	}

	/**
	 * Gets the lista tiendas afectadas.
	 * 
	 * @return the lista tiendas afectadas
	 */
	public List<Integer> getListaTiendasAfectadas() {
		return listaTiendasAfectadas;
	}

	/**
	 * Sets the lista tiendas afectadas.
	 * 
	 * @param listaTiendasAfectadas
	 *            the new lista tiendas afectadas
	 */
	public void setListaTiendasAfectadas(List<Integer> listaTiendasAfectadas) {
		this.listaTiendasAfectadas = listaTiendasAfectadas;
	}

	/**
	 * Gets the activa.
	 * 
	 * @return the activa
	 */
	public Boolean getActiva() {
		return activa;
	}

	/**
	 * Sets the activa.
	 * 
	 * @param activa
	 *            the new activa
	 */
	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	/**
	 * Gets the procesada.
	 * 
	 * @return the procesada
	 */
	public Boolean getProcesada() {
		return procesada;
	}

	/**
	 * Sets the procesada.
	 * 
	 * @param procesada
	 *            the new procesada
	 */
	public void setProcesada(Boolean procesada) {
		this.procesada = procesada;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idGrupoReglas == null) ? 0 : idGrupoReglas.hashCode());
		result = prime * result
				+ ((idTipoPromocion == null) ? 0 : idTipoPromocion.hashCode());
		result = prime * result
				+ ((idVersionReglas == null) ? 0 : idVersionReglas.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Recarga other = (Recarga) obj;

		if (idGrupoReglas == null) {
			if (other.idGrupoReglas != null) {
				return false;
			}
		} else if (!idGrupoReglas.equals(other.idGrupoReglas)) {
			return false;
		}

		if (idTipoPromocion == null) {
			if (other.idTipoPromocion != null) {
				return false;
			}
		} else if (!idTipoPromocion.equals(other.idTipoPromocion)) {
			return false;
		}

		if (idVersionReglas == null) {
			if (other.idVersionReglas != null) {
				return false;
			}
		} else if (!idVersionReglas.equals(other.idVersionReglas)) {
			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("[");

		if (idCargaPromocion != null) {
			builder.append("ID_CARGA_PROMOCION=");
			builder.append(idCargaPromocion);
			builder.append(",");
		}

		if (fechaHoraActivacion != null) {
			builder.append("FECHA_HORA_ACTIVACION=");
			builder.append(fechaHoraActivacion);
			builder.append(",");
		}

		if (fechaHoraDesactivacion != null) {
			builder.append("FECHA_HORA_DESACTIVACION=");
			builder.append(fechaHoraDesactivacion);
			builder.append(",");
		}

		if (idCadena != null) {
			builder.append(" ID_CADENA=");
			builder.append(idCadena);
			builder.append(",");
		}

		if (codPais != null) {
			builder.append(" COD_PAIS=");
			builder.append(codPais);
			builder.append(",");
		}

		if (idTipoTienda != null) {
			builder.append(" ID_TIPO_TIENDA=");
			builder.append(idTipoTienda);
			builder.append(",");
		}

		if (idTienda != null) {
			builder.append(" ID_TIENDA=");
			builder.append(idTienda);
			builder.append(",");
		}

		builder.append("ID_GRUPO_REGLAS");
		builder.append(idGrupoReglas);

		builder.append(", ID_VERSION_REGLAS=");
		builder.append(idVersionReglas);

		builder.append(", ID_TIPO_PROMOCION=");
		builder.append(idTipoPromocion);

		builder.append(", LISTA_TIENDAS_AFECTADAS={");
		if (listaTiendasAfectadas != null) {
			for (Integer tiendaAfectada : listaTiendasAfectadas) {
				builder.append(tiendaAfectada);
				builder.append(",");
			}
		}
		builder.append("}");

		if (activa != null) {
			builder.append(", ACTIVA=");
			builder.append(activa);
		}

		if (procesada != null) {
			builder.append(", PROCESADA=");
			builder.append(procesada);
		}

		builder.append("]");

		return builder.toString();
	}

	/**
	 * To string json.
	 * 
	 * @return the string
	 */
	public String toStringJSON() {
		String saltoDeLinea = "\n";
		String separator = ",";

		StringBuilder builder = new StringBuilder();

		builder.append("Recarga");
		builder.append(saltoDeLinea);
		builder.append("{");
		builder.append(saltoDeLinea);

		if (idCargaPromocion != null) {
			builder.append("\t\"idCargaPromocion\": ");
			builder.append(idCargaPromocion);
			builder.append(separator);
			builder.append(saltoDeLinea);
		}

		if (fechaHoraActivacion != null) {
			builder.append("\t\"fechaHoraActivacion\": ");
			builder.append(fechaHoraActivacion);
			builder.append(separator);
			builder.append(saltoDeLinea);
		}

		if (fechaHoraDesactivacion != null) {
			builder.append("\t\"fechaHoraDesactivacion\": ");
			builder.append(fechaHoraDesactivacion);
			builder.append(separator);
			builder.append(saltoDeLinea);
		}

		if (idCadena != null) {
			builder.append("\t\"idCadena\": ");
			builder.append(idCadena);
			builder.append(separator);
			builder.append(saltoDeLinea);
		}
		if (codPais != null) {
			builder.append("\t\"codPais\": ");
			builder.append(codPais);
			builder.append(separator);
			builder.append(saltoDeLinea);
		}

		if (idTipoTienda != null) {
			builder.append("\t\"idTipoTienda\": ");
			builder.append(idTipoTienda);
			builder.append(separator);
			builder.append(saltoDeLinea);
		}

		builder.append("\t\"idGrupoReglas\": ");
		builder.append(idGrupoReglas);
		builder.append(separator);
		builder.append(saltoDeLinea);

		builder.append("\t\"idVersionReglas\": ");
		builder.append(idVersionReglas);
		builder.append(separator);
		builder.append(saltoDeLinea);

		builder.append("\t\"idTipoPromocion\": ");
		builder.append(idTipoPromocion);

		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"listaTiendasAfectadas\": [");
		if (listaTiendasAfectadas != null) {
			for (int i = 0; i < listaTiendasAfectadas.size(); i++) {
				builder.append(listaTiendasAfectadas.get(i));
				if (i != listaTiendasAfectadas.size() - 1) {
					builder.append(separator);
					builder.append(saltoDeLinea);
				}
			}
		}
		builder.append("\t]");

		if (activa != null) {
			builder.append(separator);
			builder.append(saltoDeLinea);
			builder.append("\t\"activa\": ");
			builder.append(activa);
		}

		if (procesada != null) {
			builder.append(separator);
			builder.append(saltoDeLinea);
			builder.append("\t\"procesada\": ");
			builder.append(procesada);
		}

		builder.append(saltoDeLinea);
		builder.append("}");
		builder.append(saltoDeLinea);

		return builder.toString();
	}

}
