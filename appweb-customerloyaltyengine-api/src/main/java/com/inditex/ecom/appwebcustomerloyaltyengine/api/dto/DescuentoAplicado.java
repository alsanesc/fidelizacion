package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import java.math.BigDecimal;

import net.sf.oval.constraint.NotNull;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class DescuentoAplicadoDto.
 */
public class DescuentoAplicado extends BaseObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The es descuento porcentual. */
	@NotNull
	private Boolean esDescuentoPorcentual;

	/** The es descuento importe. */
	@NotNull
	private Boolean esDescuentoImporte;

	/** The descuento aplicado porcentaje. */
	@NotNull
	private BigDecimal descuentoAplicadoPorcentaje;

	/** The descuento aplicado importe. */
	private BigDecimal descuentoAplicadoImporte;

	/** The importe descuento unitario. */
	private BigDecimal importeDescuentoUnitario;

	/** The importe descuento total. */
	@NotNull
	private BigDecimal importeDescuentoTotal;

	/** The puntos gastados unitarios. */
	private BigDecimal puntosGastadosUnitarios;

	/** The puntos obtenidos unitarios. */
	private BigDecimal puntosObtenidosUnitarios;

	/** The puntos gastados total. */
	@NotNull
	private BigDecimal puntosGastadosTotal;

	/** The puntos obtenidos total. */
	@NotNull
	private BigDecimal puntosObtenidosTotal;

	/** The unidades con derecho a descuento. */
	private Integer unidadesConDerechoADescuento;

	/** The unidades con descuento. */
	private Integer unidadesConDescuento;

	/** The unidades sin descuento. */
	private Integer unidadesSinDescuento;

	/**
	 * Instantiates a new descuento aplicado dto.
	 */
	public DescuentoAplicado() {
	}

	/**
	 * Gets the es descuento porcentual.
	 * 
	 * @return the es descuento porcentual
	 */
	public Boolean getEsDescuentoPorcentual() {
		return esDescuentoPorcentual;
	}

	/**
	 * Sets the es descuento porcentual.
	 * 
	 * @param esDescuentoPorcentual
	 *            the new es descuento porcentual
	 */
	public void setEsDescuentoPorcentual(Boolean esDescuentoPorcentual) {
		this.esDescuentoPorcentual = esDescuentoPorcentual;
	}

	/**
	 * Gets the es descuento importe.
	 * 
	 * @return the es descuento importe
	 */
	public Boolean getEsDescuentoImporte() {
		return esDescuentoImporte;
	}

	/**
	 * Sets the es descuento importe.
	 * 
	 * @param esDescuentoImporte
	 *            the new es descuento importe
	 */
	public void setEsDescuentoImporte(Boolean esDescuentoImporte) {
		this.esDescuentoImporte = esDescuentoImporte;
	}

	/**
	 * Gets the descuento aplicado porcentaje.
	 * 
	 * @return the descuento aplicado porcentaje
	 */
	public BigDecimal getDescuentoAplicadoPorcentaje() {
		return descuentoAplicadoPorcentaje;
	}

	/**
	 * Sets the descuento aplicado porcentaje.
	 * 
	 * @param descuentoAplicadoPorcentaje
	 *            the new descuento aplicado porcentaje
	 */
	public void setDescuentoAplicadoPorcentaje(BigDecimal descuentoAplicadoPorcentaje) {
		this.descuentoAplicadoPorcentaje = descuentoAplicadoPorcentaje;
	}

	/**
	 * Gets the descuento aplicado importe.
	 * 
	 * @return the descuento aplicado importe
	 */
	public BigDecimal getDescuentoAplicadoImporte() {
		return descuentoAplicadoImporte;
	}

	/**
	 * Sets the descuento aplicado importe.
	 * 
	 * @param descuentoAplicadoImporte
	 *            the new descuento aplicado importe
	 */
	public void setDescuentoAplicadoImporte(BigDecimal descuentoAplicadoImporte) {
		this.descuentoAplicadoImporte = descuentoAplicadoImporte;
	}

	/**
	 * Gets the importe descuento unitario.
	 * 
	 * @return the importe descuento unitario
	 */
	public BigDecimal getImporteDescuentoUnitario() {
		return importeDescuentoUnitario;
	}

	/**
	 * Sets the importe descuento unitario.
	 * 
	 * @param importeDescuentoUnitario
	 *            the new importe descuento unitario
	 */
	public void setImporteDescuentoUnitario(BigDecimal importeDescuentoUnitario) {
		this.importeDescuentoUnitario = importeDescuentoUnitario;
	}

	/**
	 * Gets the importe descuento total.
	 * 
	 * @return the importe descuento total
	 */
	public BigDecimal getImporteDescuentoTotal() {
		return importeDescuentoTotal;
	}

	/**
	 * Sets the importe descuento total.
	 * 
	 * @param importeDescuentoTotal
	 *            the new importe descuento total
	 */
	public void setImporteDescuentoTotal(BigDecimal importeDescuentoTotal) {
		this.importeDescuentoTotal = importeDescuentoTotal;
	}

	/**
	 * Gets the puntos gastados unitarios.
	 * 
	 * @return the puntos gastados unitarios
	 */
	public BigDecimal getPuntosGastadosUnitarios() {
		return puntosGastadosUnitarios;
	}

	/**
	 * Sets the puntos gastados unitarios.
	 * 
	 * @param puntosGastadosUnitarios
	 *            the new puntos gastados unitarios
	 */
	public void setPuntosGastadosUnitarios(BigDecimal puntosGastadosUnitarios) {
		this.puntosGastadosUnitarios = puntosGastadosUnitarios;
	}

	/**
	 * Gets the puntos obtenidos unitarios.
	 * 
	 * @return the puntos obtenidos unitarios
	 */
	public BigDecimal getPuntosObtenidosUnitarios() {
		return puntosObtenidosUnitarios;
	}

	/**
	 * Sets the puntos obtenidos unitarios.
	 * 
	 * @param puntosObtenidosUnitarios
	 *            the new puntos obtenidos unitarios
	 */
	public void setPuntosObtenidosUnitarios(BigDecimal puntosObtenidosUnitarios) {
		this.puntosObtenidosUnitarios = puntosObtenidosUnitarios;
	}

	/**
	 * Gets the puntos gastados total.
	 * 
	 * @return the puntos gastados total
	 */
	public BigDecimal getPuntosGastadosTotal() {
		return puntosGastadosTotal;
	}

	/**
	 * Sets the puntos gastados total.
	 * 
	 * @param puntosGastadosTotal
	 *            the new puntos gastados total
	 */
	public void setPuntosGastadosTotal(BigDecimal puntosGastadosTotal) {
		this.puntosGastadosTotal = puntosGastadosTotal;
	}

	/**
	 * Gets the puntos obtenidos total.
	 * 
	 * @return the puntos obtenidos total
	 */
	public BigDecimal getPuntosObtenidosTotal() {
		return puntosObtenidosTotal;
	}

	/**
	 * Sets the puntos obtenidos total.
	 * 
	 * @param puntosObtenidosTotal
	 *            the new puntos obtenidos total
	 */
	public void setPuntosObtenidosTotal(BigDecimal puntosObtenidosTotal) {
		this.puntosObtenidosTotal = puntosObtenidosTotal;
	}

	/**
	 * Gets the unidades con derecho a descuento.
	 * 
	 * @return the unidades con derecho a descuento
	 */
	public Integer getUnidadesConDerechoADescuento() {
		return unidadesConDerechoADescuento;
	}

	/**
	 * Sets the unidades con derecho a descuento.
	 * 
	 * @param unidadesConDerechoADescuento
	 *            the new unidades con derecho a descuento
	 */
	public void setUnidadesConDerechoADescuento(
			Integer unidadesConDerechoADescuento) {
		this.unidadesConDerechoADescuento = unidadesConDerechoADescuento;
	}

	/**
	 * Gets the unidades con descuento.
	 * 
	 * @return the unidades con descuento
	 */
	public Integer getUnidadesConDescuento() {
		return unidadesConDescuento;
	}

	/**
	 * Sets the unidades con descuento.
	 * 
	 * @param unidadesConDescuento
	 *            the new unidades con descuento
	 */
	public void setUnidadesConDescuento(Integer unidadesConDescuento) {
		this.unidadesConDescuento = unidadesConDescuento;
	}

	/**
	 * Gets the unidades sin descuento.
	 * 
	 * @return the unidades sin descuento
	 */
	public Integer getUnidadesSinDescuento() {
		return unidadesSinDescuento;
	}

	/**
	 * Sets the unidades sin descuento.
	 * 
	 * @param unidadesSinDescuento
	 *            the new unidades sin descuento
	 */
	public void setUnidadesSinDescuento(Integer unidadesSinDescuento) {
		this.unidadesSinDescuento = unidadesSinDescuento;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((descuentoAplicadoPorcentaje == null) ? 0
						: descuentoAplicadoPorcentaje.hashCode());
		result = prime
				* result
				+ ((esDescuentoImporte == null) ? 0 : esDescuentoImporte
						.hashCode());
		result = prime
				* result
				+ ((esDescuentoPorcentual == null) ? 0 : esDescuentoPorcentual
						.hashCode());
		result = prime
				* result
				+ ((importeDescuentoTotal == null) ? 0 : importeDescuentoTotal
						.hashCode());
		result = prime
				* result
				+ ((puntosGastadosTotal == null) ? 0 : puntosGastadosTotal
						.hashCode());
		result = prime
				* result
				+ ((puntosObtenidosTotal == null) ? 0 : puntosObtenidosTotal
						.hashCode());
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

		DescuentoAplicado other = (DescuentoAplicado) obj;

		if (descuentoAplicadoPorcentaje == null) {
			if (other.descuentoAplicadoPorcentaje != null) {
				return false;
			}
		} else if (!descuentoAplicadoPorcentaje
				.equals(other.descuentoAplicadoPorcentaje)) {
			return false;
		}

		if (esDescuentoImporte == null) {
			if (other.esDescuentoImporte != null) {
				return false;
			}
		} else if (!esDescuentoImporte.equals(other.esDescuentoImporte)) {
			return false;
		}

		if (esDescuentoPorcentual == null) {
			if (other.esDescuentoPorcentual != null) {
				return false;
			}
		} else if (!esDescuentoPorcentual.equals(other.esDescuentoPorcentual)) {
			return false;
		}

		if (importeDescuentoTotal == null) {
			if (other.importeDescuentoTotal != null) {
				return false;
			}
		} else if (!importeDescuentoTotal.equals(other.importeDescuentoTotal)) {
			return false;
		}

		if (puntosGastadosTotal == null) {
			if (other.puntosGastadosTotal != null) {
				return false;
			}
		} else if (!puntosGastadosTotal.equals(other.puntosGastadosTotal)) {
			return false;
		}

		if (puntosObtenidosTotal == null) {
			if (other.puntosObtenidosTotal != null) {
				return false;
			}
		} else if (!puntosObtenidosTotal.equals(other.puntosObtenidosTotal)) {
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

		builder.append("[ES_DESCUENTO_PORCENTUAL=");
		builder.append(esDescuentoPorcentual);
		builder.append(", ES_DESCUENTO_IMPORTE=");
		builder.append(esDescuentoImporte);
		builder.append(", DESCUENTO_APLICADO_PORCENTAJE=");
		builder.append(descuentoAplicadoPorcentaje);

		if (descuentoAplicadoImporte != null) {
			builder.append(", DESCUENTO_APLICADO_IMPORTE=");
			builder.append(descuentoAplicadoImporte);
		}

		if (importeDescuentoUnitario != null) {
			builder.append(", IMPORTE_DESCUENTO_UNITARIO=");
			builder.append(importeDescuentoUnitario);
		}

		builder.append(", IMPORTE_DESCUENTO_TOTAL=");
		builder.append(importeDescuentoTotal);

		if (puntosGastadosUnitarios != null) {
			builder.append(", PUNTOS_GASTADOS_UNITARIOS=");
			builder.append(puntosGastadosUnitarios);
		}

		if (puntosObtenidosUnitarios != null) {
			builder.append(", PUNTOS_OBTENIDOS_UNITARIOS=");
			builder.append(puntosObtenidosUnitarios);
		}

		builder.append(", PUNTOS_GASTADOS_TOTAL=");
		builder.append(puntosGastadosTotal);
		builder.append(", PUNTOS_OBTENIDOS_TOTAL=");
		builder.append(puntosObtenidosTotal);
		
		if (unidadesConDerechoADescuento != null) {
			builder.append(", UNIDADES_CON_DERECHO_A_DESCUENTO=");
			builder.append(unidadesConDerechoADescuento);
		}
		
		if (unidadesConDescuento != null) {
			builder.append(", UNIDADES_CON_DESCUENTO=");
			builder.append(unidadesConDescuento);
		}
		
		if (unidadesSinDescuento != null) {
			builder.append(", UNIDADES_SIN_DESCUENTO=");
			builder.append(unidadesSinDescuento);
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

		builder.append("DescuentoAplicado");
		builder.append(saltoDeLinea);
		builder.append("{");
		builder.append(saltoDeLinea);

		builder.append("\t\"esDescuentoPorcentual\": ");
		builder.append(esDescuentoPorcentual);
		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"esDescuentoImporte\": ");
		builder.append(esDescuentoImporte);
		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"descuentoAplicadoPorcentaje\": ");
		builder.append(descuentoAplicadoPorcentaje);

		if (descuentoAplicadoImporte != null) {
			builder.append(separator);
			builder.append(saltoDeLinea);
			builder.append("\t\"descuentoAplicadoImporte\": ");
			builder.append(descuentoAplicadoImporte);
		}

		if (importeDescuentoUnitario != null) {
			builder.append(separator);
			builder.append(saltoDeLinea);
			builder.append("\t\"importeDescuentoUnitario\": ");
			builder.append(importeDescuentoUnitario);
		}

		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"importeDescuentoTotal\": ");
		builder.append(importeDescuentoTotal);

		if (puntosGastadosUnitarios != null) {
			builder.append(separator);
			builder.append(saltoDeLinea);
			builder.append("\t\"puntosObtenidosUnitarios\": ");
			builder.append(puntosObtenidosUnitarios);
		}

		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"puntosGastadosTotal\": ");
		builder.append(puntosGastadosTotal);
		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"puntosObtenidosTotal\": ");
		builder.append(puntosObtenidosTotal);
		
		if (unidadesConDerechoADescuento != null) {
			builder.append(separator);
			builder.append(saltoDeLinea);
			builder.append("\t\"unidadesConDerechoADescuento\": ");
			builder.append(unidadesConDerechoADescuento);
		}
		
		if (unidadesConDescuento != null) {
			builder.append(separator);
			builder.append(saltoDeLinea);
			builder.append("\t\"unidadesConDescuento\": ");
			builder.append(unidadesConDescuento);
		}
		
		if (unidadesSinDescuento != null) {
			builder.append(separator);
			builder.append(saltoDeLinea);
			builder.append("\t\"unidadesSinDescuento\": ");
			builder.append(unidadesSinDescuento);
		}
		
		
		builder.append(saltoDeLinea);
		builder.append("}");
		builder.append(saltoDeLinea);

		return builder.toString();
	}

}
