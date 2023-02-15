package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class TipoOperacionDescuentoDto.
 */
public class TipoOperacionDescuentoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id tipo operacion descuento. */
    private Short idTipoOperacionDescuento;

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new tipo operacion descuento dto.
     */
    public TipoOperacionDescuentoDto() {
    }

    /**
     * Instantiates a new tipo operacion descuento dto.
     * 
     * @param idTipoOperacionDescuento
     *            the id tipo operacion descuento
     */
    public TipoOperacionDescuentoDto(Short idTipoOperacionDescuento) {
        this.idTipoOperacionDescuento = idTipoOperacionDescuento;
    }

    /**
     * Gets the id tipo operacion descuento.
     * 
     * @return the id tipo operacion descuento
     */
    public Short getIdTipoOperacionDescuento() {
        return idTipoOperacionDescuento;
    }

    /**
     * Sets the id tipo operacion descuento.
     * 
     * @param idTipoOperacionDescuento
     *            the new id tipo operacion descuento
     */
    public void setIdTipoOperacionDescuento(Short idTipoOperacionDescuento) {
        this.idTipoOperacionDescuento = idTipoOperacionDescuento;
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
        result = prime * result + ((idTipoOperacionDescuento == null) ? 0 : idTipoOperacionDescuento.hashCode());
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
        TipoOperacionDescuentoDto other = (TipoOperacionDescuentoDto) obj;
        if (idTipoOperacionDescuento == null) {
            if (other.idTipoOperacionDescuento != null) {
                return false;
            }
        } else if (!idTipoOperacionDescuento.equals(other.idTipoOperacionDescuento)) {
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

        builder.append("[ID_TIPO_OPERACION_DESCUENTO=");
        builder.append(idTipoOperacionDescuento);
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
        builder.append("TipoOperacionDescuentoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idTipoOperacionDescuento\": ");
        builder.append(idTipoOperacionDescuento);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"Descripcion\": ");
        builder.append(descripcion);

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
