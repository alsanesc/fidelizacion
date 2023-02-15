package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class PrimaryKeyDescuentoDto.
 */
public class PrimaryKeyDescuentoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id descuento. */
    private Long idDescuento;

    /** The id instalacion. */
    private Integer idInstalacion;

    /**
     * Instantiates a new primary key descuento dto.
     */
    public PrimaryKeyDescuentoDto() {
    }

    /**
     * Instantiates a new primary key descuento dto.
     * 
     * @param idDescuento
     *            the id descuento
     * @param idInstalacion
     *            the id instalacion
     */
    public PrimaryKeyDescuentoDto(Long idDescuento, Integer idInstalacion) {
        this.idDescuento = idDescuento;
        this.idInstalacion = idInstalacion;
    }

    /**
     * Instantiates a new primary key descuento dto.
     * 
     * @param idDescuento
     *            the id descuento
     */
    public PrimaryKeyDescuentoDto(String idDescuento) {
        this.idDescuento = new Long(idDescuento.substring(0, idDescuento.indexOf(Comunes.COLON)));
        this.idInstalacion = Integer.parseInt(idDescuento.substring(idDescuento.indexOf(Comunes.COLON) + 1));
    }

    /**
     * Gets the id descuento.
     * 
     * @return the id descuento
     */
    public Long getIdDescuento() {
        return idDescuento;
    }

    /**
     * Sets the id descuento.
     * 
     * @param idDescuento
     *            the new id descuento
     */
    public void setIdDescuento(Long idDescuento) {
        this.idDescuento = idDescuento;
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
        result = prime * result + ((idDescuento == null) ? 0 : idDescuento.hashCode());
        result = prime * result + ((idInstalacion == null) ? 0 : idInstalacion.hashCode());
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
        PrimaryKeyDescuentoDto other = (PrimaryKeyDescuentoDto) obj;
        if (idDescuento == null) {
            if (other.idDescuento != null) {
                return false;
            }
        } else if (!idDescuento.equals(other.idDescuento)) {
            return false;
        }
        if (idInstalacion == null) {
            if (other.idInstalacion != null) {
                return false;
            }
        } else if (!idInstalacion.equals(other.idInstalacion)) {
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

        builder.append("[ID_DESCUENTO=");
        builder.append(idDescuento);
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
        builder.append("PrimaryKeyDescuentoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idDescuento\": ");
        builder.append(idDescuento);
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
