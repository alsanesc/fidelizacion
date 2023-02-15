package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class EstadoDescuentoDto.
 */
public class EstadoDescuentoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id estado descuento. */
    private Short idEstadoDescuento;

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new estado descuento dto.
     */
    public EstadoDescuentoDto() {
    }

    /**
     * Instantiates a new estado descuento dto.
     * 
     * @param idEstadoDescuento
     *            the id estado descuento
     */
    public EstadoDescuentoDto(Short idEstadoDescuento) {
        this.idEstadoDescuento = idEstadoDescuento;
    }

    /**
     * Gets the id estado descuento.
     * 
     * @return the id estado descuento
     */
    public Short getIdEstadoDescuento() {
        return idEstadoDescuento;
    }

    /**
     * Sets the id estado descuento.
     * 
     * @param idEstadoDescuento
     *            the new id estado descuento
     */
    public void setIdEstadoDescuento(Short idEstadoDescuento) {
        this.idEstadoDescuento = idEstadoDescuento;
    }

    /**
     * Gets the descripcion.
     * 
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the descripcion.
     * 
     * @param descripcion
     *            the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idEstadoDescuento == null) ? 0 : idEstadoDescuento.hashCode());
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
        EstadoDescuentoDto other = (EstadoDescuentoDto) obj;
        if (idEstadoDescuento == null) {
            if (other.idEstadoDescuento != null) {
                return false;
            }
        } else if (!idEstadoDescuento.equals(other.idEstadoDescuento)) {
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

        builder.append("[ID_ESTADO_DESCUENTO=");
        builder.append(idEstadoDescuento);
        builder.append(", DESCRIPCION=");
        builder.append(descripcion);
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
        builder.append("EstadoDescuentoDTO");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idEstadoDescuento\": ");
        builder.append(idEstadoDescuento);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);
        builder.append("\t\"Descripcion\" :");
        builder.append(descripcion);

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
