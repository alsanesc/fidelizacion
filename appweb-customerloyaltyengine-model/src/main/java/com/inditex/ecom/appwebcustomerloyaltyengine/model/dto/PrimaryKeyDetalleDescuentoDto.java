package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class PrimaryDetalleDescuentoDto.
 */
public class PrimaryKeyDetalleDescuentoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id detalle descuento. */
    private Long idDetalleDescuento;

    /** The id instalacion. */
    private Integer idInstalacion;

    /**
     * Instantiates a new primary detalle descuento dto.
     */
    public PrimaryKeyDetalleDescuentoDto() {
    }

    /**
     * Instantiates a new primary detalle descuento dto.
     * 
     * @param idDetalleDescuento
     *            the id detalle descuento
     * @param idInstalacion
     *            the id instalacion
     */
    public PrimaryKeyDetalleDescuentoDto(Long idDetalleDescuento, Integer idInstalacion) {
        this.idDetalleDescuento = idDetalleDescuento;
        this.idInstalacion = idInstalacion;
    }

    /**
     * Instantiates a new primary key detalle descuento dto.
     * 
     * @param idDetalleDescuento
     *            the id detalle descuento
     */
    public PrimaryKeyDetalleDescuentoDto(String idDetalleDescuento) {
        this.idDetalleDescuento = new Long(idDetalleDescuento.substring(0, idDetalleDescuento.indexOf(Comunes.COLON)));
        this.idInstalacion = Integer.parseInt(idDetalleDescuento.substring(idDetalleDescuento.indexOf(Comunes.COLON) + 1));
    }

    /**
     * Gets the id detalle descuento.
     * 
     * @return the id detalle descuento
     */
    public Long getIdDetalleDescuento() {
        return idDetalleDescuento;
    }

    /**
     * Sets the id detalle descuento.
     * 
     * @param idDetalleDescuento
     *            the new id detalle descuento
     */
    public void setIdDetalleDescuento(Long idDetalleDescuento) {
        this.idDetalleDescuento = idDetalleDescuento;
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
        result = prime * result + ((idDetalleDescuento == null) ? 0 : idDetalleDescuento.hashCode());
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
        PrimaryKeyDetalleDescuentoDto other = (PrimaryKeyDetalleDescuentoDto) obj;
        if (idDetalleDescuento == null) {
            if (other.idDetalleDescuento != null) {
                return false;
            }
        } else if (!idDetalleDescuento.equals(other.idDetalleDescuento)) {
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

        builder.append("[ID_DETALLE_DESCUENTO=");
        builder.append(idDetalleDescuento);
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
        builder.append("PrimaryKeyDetalleDescuentoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idDetalleDescuento\": ");
        builder.append(idDetalleDescuento);
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
