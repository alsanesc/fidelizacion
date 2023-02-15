package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import java.math.BigDecimal;
import java.util.List;

import net.sf.oval.constraint.NotNull;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class OperacionPedidoDto.
 */
public class OperacionPedido extends BaseObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tipo operacion. */
	@NotNull
	private String tipoOperacion;

	/** The cod operacion externo. */
	@NotNull
	private String codOperacionExterno;

	/** The lineas pedido afectadas. */
	@NotNull
	private List<LineaPedido> lineasPedidoAfectadas;

	/** The importe operacion. */
	@NotNull
	private BigDecimal importeOperacion;

	/**
	 * Instantiates a new operacion pedido dto.
	 */
	public OperacionPedido() {
	}

	/**
	 * Gets the tipo operacion.
	 * 
	 * @return the tipo operacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 * 
	 * @param tipoOperacion
	 *            the new tipo operacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public String getCodOperacionExterno() {
		return codOperacionExterno;
	}

	public void setCodOperacionExterno(String codOperacionExterno) {
		this.codOperacionExterno = codOperacionExterno;
	}

	/**
	 * Gets the lineas pedido afectadas.
	 * 
	 * @return the lineas pedido afectadas
	 */
	public List<LineaPedido> getLineasPedidoAfectadas() {
		return lineasPedidoAfectadas;
	}

	/**
	 * Sets the lineas pedido afectadas.
	 * 
	 * @param lineasPedidoAfectadas
	 *            the new lineas pedido afectadas
	 */
	public void setLineasPedidoAfectadas(List<LineaPedido> lineasPedidoAfectadas) {
		this.lineasPedidoAfectadas = lineasPedidoAfectadas;
	}

	/**
	 * Gets the importe operacion.
	 * 
	 * @return the importe operacion
	 */
	public BigDecimal getImporteOperacion() {
		return importeOperacion;
	}

	/**
	 * Sets the importe operacion.
	 * 
	 * @param importeOperacion
	 *            the new importe operacion
	 */
	public void setImporteOperacion(BigDecimal importeOperacion) {
		this.importeOperacion = importeOperacion;
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
				+ ((importeOperacion == null) ? 0 : importeOperacion.hashCode());
		result = prime
				* result
				+ ((lineasPedidoAfectadas == null) ? 0 : lineasPedidoAfectadas
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

		OperacionPedido other = (OperacionPedido) obj;

		if (importeOperacion == null) {
			if (other.importeOperacion != null) {
				return false;
			}
		} else if (!importeOperacion.equals(other.importeOperacion)) {
			return false;
		}

		if (lineasPedidoAfectadas == null) {
			if (other.lineasPedidoAfectadas != null) {
				return false;
			}
		} else if (!lineasPedidoAfectadas.equals(other.lineasPedidoAfectadas)) {
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
		
		builder.append("[TIPO_OPERACION=");
		builder.append(tipoOperacion);
		builder.append(", COD_OPERACION_EXTERNO=");
		builder.append(codOperacionExterno);

		builder.append(", LINEAS_PEDIDO_AFECTADAS={");
		for (LineaPedido lineaPedido : lineasPedidoAfectadas) {
			builder.append(lineaPedido.toString());
			builder.append(",");
		}
		builder.append("}");

		builder.append(", IMPORTE_OPERACION=");
		builder.append(importeOperacion);

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

		builder.append("OperacionPedido");
		builder.append(saltoDeLinea);
		builder.append("{");
		builder.append(saltoDeLinea);
		
		builder.append("\t\"tipoOperacion\": ");
		builder.append(tipoOperacion);
		builder.append(separator);
		builder.append(saltoDeLinea);
		
		builder.append("\t\"codOperacionExterno\": ");
		builder.append(codOperacionExterno);
		builder.append(separator);
		builder.append(saltoDeLinea);

		builder.append("\t\"lineasPedidoAfectadas\": [");
		for (int i = 0; i < lineasPedidoAfectadas.size(); i++) {
			builder.append(lineasPedidoAfectadas.get(i).toStringJSON());
			if (i != lineasPedidoAfectadas.size() - 1) {
				builder.append(separator);
				builder.append(saltoDeLinea);
			}
		}
		builder.append(saltoDeLinea);
		builder.append("\t]");

		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"importeOperacion\": ");
		builder.append(importeOperacion);

		builder.append(saltoDeLinea);
		builder.append("}");
		builder.append(saltoDeLinea);

		return builder.toString();
	}

}
