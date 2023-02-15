package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class PrimaryKeyOperacionDescuentoDto.
 */
public class PrimaryKeyOperacionDescuentoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id operacion descuento. */
    private Long idOperacionDescuento;

    /** The id instalacion. */
    private Integer idInstalacion;

    /**
     * Instantiates a new primary key operacion descuento dto.
     */
    public PrimaryKeyOperacionDescuentoDto() {
    }

    /**
     * Instantiates a new primary key operacion descuento dto.
     * 
     * @param idOperacionDescuento
     *            the id operacion descuento
     * @param idInstalacion
     *            the id instalacion
     */
    public PrimaryKeyOperacionDescuentoDto(Long idOperacionDescuento, Integer idInstalacion) {
        this.idOperacionDescuento = idOperacionDescuento;
        this.idInstalacion = idInstalacion;
    }

    /**
     * Instantiates a new primary key operacion descuento dto.
     * 
     * @param idOperacionDescuento
     *            the id operacion descuento
     */
    public PrimaryKeyOperacionDescuentoDto(String idOperacionDescuento) {
        this.idOperacionDescuento = new Long(idOperacionDescuento.substring(0, idOperacionDescuento.indexOf(Comunes.COLON)));
        this.idInstalacion = Integer.parseInt(idOperacionDescuento.substring(idOperacionDescuento.indexOf(Comunes.COLON) + 1));
    }

    /**
     * Gets the id operacion descuento.
     * 
     * @return the id operacion descuento
     */
    public Long getIdOperacionDescuento() {
        return idOperacionDescuento;
    }

    /**
     * Sets the id operacion descuento.
     * 
     * @param idOperacionDescuento
     *            the new id operacion descuento
     */
    public void setIdOperacionDescuento(Long idOperacionDescuento) {
        this.idOperacionDescuento = idOperacionDescuento;
    }

    /**
     * Gets the id instalacion.
     * 
     * @return the id instalacion
     */
    public Integer getIdInstalacion() {
        return idInstalacion;
    }

    /**
     * Sets the id instalacion.
     * 
     * @param idInstalacion
     *            the new id instalacion
     */
    public void setIdInstalacion(Integer idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idInstalacion == null) ? 0 : idInstalacion.hashCode());
        result = prime * result + ((idOperacionDescuento == null) ? 0 : idOperacionDescuento.hashCode());
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
        PrimaryKeyOperacionDescuentoDto other = (PrimaryKeyOperacionDescuentoDto) obj;
        if (idInstalacion == null) {
            if (other.idInstalacion != null) {
                return false;
            }
        } else if (!idInstalacion.equals(other.idInstalacion)) {
            return false;
        }
        if (idOperacionDescuento == null) {
            if (other.idOperacionDescuento != null) {
                return false;
            }
        } else if (!idOperacionDescuento.equals(other.idOperacionDescuento)) {
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

        builder.append("[ID_OPERACION_DESCUENTO=");
        builder.append(idOperacionDescuento);
        builder.append(", ID_INSTALACION=");
        builder.append(idInstalacion);
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
        builder.append("PrimaryKeyOperacionDescuentoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idOperacionDescuento\": ");
        builder.append(idOperacionDescuento);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);
        builder.append("\t\"idInstalacion\": ");
        builder.append(idInstalacion);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
