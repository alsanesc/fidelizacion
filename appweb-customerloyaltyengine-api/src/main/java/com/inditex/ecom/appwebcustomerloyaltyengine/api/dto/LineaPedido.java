package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import java.math.BigDecimal;
import java.util.ArrayList;

import net.sf.oval.constraint.NotNull;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class LineaPedidoDto.
 */
public class LineaPedido extends BaseObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id linea pedido. */
	@NotNull
	private String idLineaPedido;

	/** The part number. */
	@NotNull
	private String partNumber;

	/** The cantidad solicitada. */
	@NotNull
	private Integer cantidadSolicitada;

	/** The importe unitario. */
	@NotNull
	private BigDecimal importeUnitario;

	/** The importe total. */
	@NotNull
	private BigDecimal importeTotal;

	/** The es gastos envio. */
	@NotNull
	private Boolean esGastosEnvio;

	/** The es saldo. */
	private Boolean esSaldo;

	/** The es promocion. */
	private Boolean esPromocion;

	/** The no aplicar descuento. */
	private Boolean noAplicarDescuento;

	/** The porcentaje corte precio. */
	private Integer porcentajeCortePrecio;

	/** The atributos linea pedido. */
	@NotNull
	private ArrayList<KeyValue> atributosLineaPedido;

	/** The descuento aplicado. */
	private DescuentoAplicado descuentoAplicado;

	/**
	 * Instantiates a new linea pedido dto.
	 */
	public LineaPedido() {
	}

	/**
	 * Gets the id linea pedido.
	 * 
	 * @return the id linea pedido
	 */
	public String getIdLineaPedido() {
		return idLineaPedido;
	}

	/**
	 * Sets the id linea pedido.
	 * 
	 * @param idLineaPedido
	 *            the new id linea pedido
	 */
	public void setIdLineaPedido(String idLineaPedido) {
		this.idLineaPedido = idLineaPedido;
	}

	/**
	 * Gets the part number.
	 * 
	 * @return the part number
	 */
	public String getPartNumber() {
		return partNumber;
	}

	/**
	 * Sets the part number.
	 * 
	 * @param partNumber
	 *            the new part number
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	/**
	 * Gets the cantidad solicitada.
	 * 
	 * @return the cantidad solicitada
	 */
	public Integer getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	/**
	 * Sets the cantidad solicitada.
	 * 
	 * @param cantidadSolicitada
	 *            the new cantidad solicitada
	 */
	public void setCantidadSolicitada(Integer cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

	/**
	 * Gets the importe unitario.
	 * 
	 * @return the importe unitario
	 */
	public BigDecimal getImporteUnitario() {
		return importeUnitario;
	}

	/**
	 * Sets the importe unitario.
	 * 
	 * @param importeUnitario
	 *            the new importe unitario
	 */
	public void setImporteUnitario(BigDecimal importeUnitario) {
		this.importeUnitario = importeUnitario;
	}

	/**
	 * Gets the importe total.
	 * 
	 * @return the importe total
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	/**
	 * Sets the importe total.
	 * 
	 * @param importeTotal
	 *            the new importe total
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * Gets the es gastos envio.
	 * 
	 * @return the es gastos envio
	 */
	public Boolean getEsGastosEnvio() {
		return esGastosEnvio;
	}

	/**
	 * Sets the es gastos envio.
	 * 
	 * @param esGastosEnvio
	 *            the new es gastos envio
	 */
	public void setEsGastosEnvio(Boolean esGastosEnvio) {
		this.esGastosEnvio = esGastosEnvio;
	}

	/**
	 * Gets the es saldo.
	 * 
	 * @return the es saldo
	 */
	public Boolean getEsSaldo() {
		return esSaldo;
	}

	/**
	 * Sets the es saldo.
	 * 
	 * @param esSaldo
	 *            the new es saldo
	 */
	public void setEsSaldo(Boolean esSaldo) {
		this.esSaldo = esSaldo;
	}

	/**
	 * Gets the es promocion.
	 * 
	 * @return the es promocion
	 */
	public Boolean getEsPromocion() {
		return esPromocion;
	}

	/**
	 * Sets the es promocion.
	 * 
	 * @param esPromocion
	 *            the new es promocion
	 */
	public void setEsPromocion(Boolean esPromocion) {
		this.esPromocion = esPromocion;
	}

	/**
	 * Gets the no aplicar descuento.
	 * 
	 * @return the no aplicar descuento
	 */
	public Boolean getNoAplicarDescuento() {
		return noAplicarDescuento;
	}

	/**
	 * Sets the no aplicar descuento.
	 * 
	 * @param noAplicarDescuento
	 *            the new no aplicar descuento
	 */
	public void setNoAplicarDescuento(Boolean noAplicarDescuento) {
		this.noAplicarDescuento = noAplicarDescuento;
	}

	/**
	 * Gets the porcentaje corte precio.
	 * 
	 * @return the porcentaje corte precio
	 */
	public Integer getPorcentajeCortePrecio() {
		return porcentajeCortePrecio;
	}

	/**
	 * Sets the porcentaje corte precio.
	 * 
	 * @param porcentajeCortePrecio
	 *            the new porcentaje corte precio
	 */
	public void setPorcentajeCortePrecio(Integer porcentajeCortePrecio) {
		this.porcentajeCortePrecio = porcentajeCortePrecio;
	}

	/**
	 * Gets the atributos linea pedido.
	 * 
	 * @return the atributos linea pedido
	 */
	public ArrayList<KeyValue> getAtributosLineaPedido() {
		return atributosLineaPedido;
	}

	/**
	 * Sets the atributos linea pedido.
	 * 
	 * @param atributosLineaPedido
	 *            the new atributos linea pedido
	 */
	public void setAtributosLineaPedido(ArrayList<KeyValue> atributosLineaPedido) {
		this.atributosLineaPedido = atributosLineaPedido;
	}

	/**
	 * Gets the descuento aplicado.
	 * 
	 * @return the descuento aplicado
	 */
	public DescuentoAplicado getDescuentoAplicado() {
		return descuentoAplicado;
	}

	/**
	 * Sets the descuento aplicado.
	 * 
	 * @param descuentoAplicado
	 *            the new descuento aplicado
	 */
	public void setDescuentoAplicado(DescuentoAplicado descuentoAplicado) {
		this.descuentoAplicado = descuentoAplicado;
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
				+ ((atributosLineaPedido == null) ? 0 : atributosLineaPedido
						.hashCode());
		result = prime
				* result
				+ ((cantidadSolicitada == null) ? 0 : cantidadSolicitada
						.hashCode());
		result = prime * result
				+ ((esGastosEnvio == null) ? 0 : esGastosEnvio.hashCode());
		result = prime * result
				+ ((idLineaPedido == null) ? 0 : idLineaPedido.hashCode());
		result = prime * result
				+ ((importeTotal == null) ? 0 : importeTotal.hashCode());
		result = prime * result
				+ ((importeUnitario == null) ? 0 : importeUnitario.hashCode());
		result = prime * result
				+ ((partNumber == null) ? 0 : partNumber.hashCode());
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
		LineaPedido other = (LineaPedido) obj;
		if (atributosLineaPedido == null) {
			if (other.atributosLineaPedido != null) {
				return false;
			}
		} else if (!atributosLineaPedido.equals(other.atributosLineaPedido)) {
			return false;
		}
		if (cantidadSolicitada == null) {
			if (other.cantidadSolicitada != null) {
				return false;
			}
		} else if (!cantidadSolicitada.equals(other.cantidadSolicitada)) {
			return false;
		}
		if (esGastosEnvio == null) {
			if (other.esGastosEnvio != null) {
				return false;
			}
		} else if (!esGastosEnvio.equals(other.esGastosEnvio)) {
			return false;
		}
		if (idLineaPedido == null) {
			if (other.idLineaPedido != null) {
				return false;
			}
		} else if (!idLineaPedido.equals(other.idLineaPedido)) {
			return false;
		}
		if (importeTotal == null) {
			if (other.importeTotal != null) {
				return false;
			}
		} else if (!importeTotal.equals(other.importeTotal)) {
			return false;
		}
		if (importeUnitario == null) {
			if (other.importeUnitario != null) {
				return false;
			}
		} else if (!importeUnitario.equals(other.importeUnitario)) {
			return false;
		}
		if (partNumber == null) {
			if (other.partNumber != null) {
				return false;
			}
		} else if (!partNumber.equals(other.partNumber)) {
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

		builder.append("[ID_LINEA_PEDIDO=");
		builder.append(idLineaPedido);
		builder.append(", PARTNUMBER=");
		builder.append(partNumber);
		builder.append(", CANTIDAD_SOLICITADA=");
		builder.append(cantidadSolicitada);
		builder.append(", IMPORTE_UNITARIO=");
		builder.append(importeUnitario);
		builder.append(", IMPORTE_TOTAL=");
		builder.append(importeTotal);
		builder.append(", ES_GASTOS_ENVIO=");
		builder.append(esGastosEnvio);

		if (esSaldo != null) {
			builder.append(", ES_SALDO=");
			builder.append(esSaldo);
		}

		if (esPromocion != null) {
			builder.append(", ES_PROMOCION=");
			builder.append(esPromocion);
		}

		if (noAplicarDescuento != null) {
			builder.append(", NO_APLICAR_DESCUENTO=");
			builder.append(noAplicarDescuento);
		}

		if (porcentajeCortePrecio != null) {
			builder.append(", PORCENTAJE_CORTE_PRECIO=");
			builder.append(porcentajeCortePrecio);
		}
		
		builder.append(", ATRIBUTOS_LINEA_PEDIDO=");
		if (atributosLineaPedido != null) {
		    for (KeyValue clave : atributosLineaPedido) {
		        builder.append(clave.toString());
		    }
		}

		builder.append(", DESCUENTO_APLICADO={");
		if (descuentoAplicado != null) {
			builder.append(descuentoAplicado.toString());
		}
		builder.append("}");

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

		builder.append("LineaPedido");
		builder.append(saltoDeLinea);
		builder.append("{");
		builder.append(saltoDeLinea);
		builder.append("\t\"idLineaPedido\": ");
		builder.append(idLineaPedido);
		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"partNumber\": ");
		builder.append(partNumber);
		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"cantidadSolicitada\": ");
		builder.append(cantidadSolicitada);
		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"importeUnitario\": ");
		builder.append(importeUnitario);
		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"importeTotal\": ");
		builder.append(importeTotal);
		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"esGastosEnvio\": ");
		builder.append(esGastosEnvio);

		if (esSaldo != null) {
			builder.append(separator);
			builder.append(saltoDeLinea);
			builder.append("\t\"esSaldo\": ");
			builder.append(esSaldo);
		}

		if (esPromocion != null) {
			builder.append(separator);
			builder.append(saltoDeLinea);
			builder.append("\t\"esPromocion\": ");
			builder.append(esPromocion);
		}

		if (noAplicarDescuento != null) {
			builder.append(separator);
			builder.append(saltoDeLinea);
			builder.append("\t\"noAplicarDescuento\": ");
			builder.append(noAplicarDescuento);
		}

		if (porcentajeCortePrecio != null) {
			builder.append(separator);
			builder.append(saltoDeLinea);
			builder.append("\t\"porcentajeCortePrecio\": ");
			builder.append(porcentajeCortePrecio);
		}
		
		builder.append("\t\"atributosLineaPedido\": [ ");
        builder.append(saltoDeLinea);
        if (atributosLineaPedido != null) {
            for (int i = 0; i < atributosLineaPedido.size(); i++) {
                builder.append(atributosLineaPedido.get(i).toStringJSON());
                if (i != atributosLineaPedido.size() - 1) {
                    builder.append(separator);
                    builder.append(saltoDeLinea);
                }
            }
        }

		if (descuentoAplicado != null) {
			builder.append(descuentoAplicado.toStringJSON());
		}

		builder.append(saltoDeLinea);
		builder.append("}");
		builder.append(saltoDeLinea);

		return builder.toString();
	}

}
