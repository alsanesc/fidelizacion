package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import java.math.BigDecimal;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class DetalleDescuentoDto.
 */
public class DetalleDescuentoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The primary key detalle descuento dto. */
    private PrimaryKeyDetalleDescuentoDto idDetalleDescuento = new PrimaryKeyDetalleDescuentoDto();

    /** The incremento puntos. */
    private BigDecimal incrementoPuntos;

    /** The consumo puntos. */
    private BigDecimal consumoPuntos;

    /** The porcentaje descuento. */
    private BigDecimal porcentajeDescuento;

    /** The importe descuento. */
    private BigDecimal importeDescuento;

    /** The numero unidades. */
    private Integer numeroUnidades;

    /**
     * Instantiates a new detalle descuento dto.
     */
    public DetalleDescuentoDto() {
    }

    /**
     * Instantiates a new detalle descuento dto.
     * 
     * @param idDetalleDescuento
     *            the id detalle descuento
     */
    public DetalleDescuentoDto(PrimaryKeyDetalleDescuentoDto idDetalleDescuento) {
        this.idDetalleDescuento = idDetalleDescuento;
    }

    /**
     * Gets the id detalle descuento.
     * 
     * @return the id detalle descuento
     */
    public PrimaryKeyDetalleDescuentoDto getIdDetalleDescuento() {
        return idDetalleDescuento;
    }

    /**
     * Sets the id detalle descuento.
     * 
     * @param idDetalleDescuento
     *            the new id detalle descuento
     */
    public void setIdDetalleDescuento(PrimaryKeyDetalleDescuentoDto idDetalleDescuento) {
        this.idDetalleDescuento = idDetalleDescuento;
    }

    /**
     * Sets the primary key detalle descuento dto.
     * 
     * @param primaryKeyDetalleDescuentoDto
     *            the new primary key detalle descuento dto
     */
    public void setPrimaryKeyDetalleDescuentoDto(PrimaryKeyDetalleDescuentoDto primaryKeyDetalleDescuentoDto) {
        this.idDetalleDescuento = primaryKeyDetalleDescuentoDto;
    }

    /**
     * Gets the incremento puntos.
     * 
     * @return the incremento puntos
     */
    public BigDecimal getIncrementoPuntos() {
        return incrementoPuntos;
    }

    /**
     * Sets the incremento puntos.
     * 
     * @param incrementoPuntos
     *            the new incremento puntos
     */
    public void setIncrementoPuntos(BigDecimal incrementoPuntos) {
        this.incrementoPuntos = incrementoPuntos;
    }

    /**
     * Gets the consumo puntos.
     * 
     * @return the consumo puntos
     */
    public BigDecimal getConsumoPuntos() {
        return consumoPuntos;
    }

    /**
     * Sets the consumo puntos.
     * 
     * @param consumoPuntos
     *            the new consumo puntos
     */
    public void setConsumoPuntos(BigDecimal consumoPuntos) {
        this.consumoPuntos = consumoPuntos;
    }

    /**
     * Gets the porcentaje descuento.
     * 
     * @return the porcentaje descuento
     */
    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    /**
     * Sets the porcentaje descuento.
     * 
     * @param porcentajeDescuento
     *            the new porcentaje descuento
     */
    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    /**
     * Gets the importe descuento.
     * 
     * @return the importe descuento
     */
    public BigDecimal getImporteDescuento() {
        return importeDescuento;
    }

    /**
     * Sets the importe descuento.
     * 
     * @param importeDescuento
     *            the new importe descuento
     */
    public void setImporteDescuento(BigDecimal importeDescuento) {
        this.importeDescuento = importeDescuento;
    }

    /**
     * Gets the numero unidades.
     * 
     * @return the numero unidades
     */
    public Integer getNumeroUnidades() {
        return numeroUnidades;
    }

    /**
     * Sets the numero unidades.
     * 
     * @param numeroUnidades
     *            the new numero unidades
     */
    public void setNumeroUnidades(Integer numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idDetalleDescuento == null) ? 0 : idDetalleDescuento.hashCode());
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
        DetalleDescuentoDto other = (DetalleDescuentoDto) obj;
        if (idDetalleDescuento == null) {
            if (other.idDetalleDescuento != null) {
                return false;
            }
        } else if (!idDetalleDescuento.equals(other.idDetalleDescuento)) {
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

        builder.append("[PRIMARY_KEY_DETALLE_DESCUENTO=");
        builder.append(idDetalleDescuento.toString());

        if (incrementoPuntos != null) {
            builder.append(", INCREMENTO_PUNTOS=");
            builder.append(incrementoPuntos);
        }

        if (consumoPuntos != null) {
            builder.append(", CONSUMO_PUNTOS=");
            builder.append(consumoPuntos);
        }

        if (porcentajeDescuento != null) {
            builder.append(", PORCENTAJE_DESCUENTO=");
            builder.append(porcentajeDescuento);
        }

        if (importeDescuento != null) {
            builder.append(", IMPORTE_DESCUENTO=");
            builder.append(importeDescuento);
        }
        
        if (numeroUnidades != null) {
            builder.append(", NUMERO_UNIDADES=");
            builder.append(numeroUnidades);
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
        StringBuilder builder = new StringBuilder();

        builder.append(Comunes.RETURN);
        builder.append("DetalleDescuentoDTO");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"primaryKeyDetalleDescuentoDto\": ");
        builder.append(idDetalleDescuento.toStringJSON());

        if (incrementoPuntos != null) {
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
            builder.append("\t\"incrementoPuntos\": ");
            builder.append(incrementoPuntos);
        }

        if (consumoPuntos != null) {
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
            builder.append("\t\"consumoPuntos\": ");
            builder.append(consumoPuntos);
        }

        if (porcentajeDescuento != null) {
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
            builder.append("\t\"porcentajeDescuento\": ");
            builder.append(porcentajeDescuento);
        }

        if (importeDescuento != null) {
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
            builder.append("\t\"importeDescuento\": ");
            builder.append(importeDescuento);
        }
        
        if (numeroUnidades != null) {
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
            builder.append("\t\"numeroUnidades\": ");
            builder.append(numeroUnidades);
        }

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
