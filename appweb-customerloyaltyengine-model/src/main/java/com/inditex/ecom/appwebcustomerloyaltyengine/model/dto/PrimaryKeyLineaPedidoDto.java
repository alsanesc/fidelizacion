package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class PrimaryKeyLineaPedidoDto.
 */
public class PrimaryKeyLineaPedidoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id linea descuento. */
    private Long idLineaDescuento;

    /** The id instalacion. */
    private Integer idInstalacion;

    /**
     * Instantiates a new primary key linea pedido dto.
     */
    public PrimaryKeyLineaPedidoDto() {
    }

    /**
     * Instantiates a new primary key linea pedido dto.
     * 
     * @param idLineaDescuento
     *            the id linea descuento
     * @param idInstalacion
     *            the id instalacion
     */
    public PrimaryKeyLineaPedidoDto(Long idLineaDescuento, Integer idInstalacion) {
        this.idLineaDescuento = idLineaDescuento;
        this.idInstalacion = idInstalacion;
    }
    
    public PrimaryKeyLineaPedidoDto(String idLineaDescuento) {
        this.idLineaDescuento = new Long(idLineaDescuento.substring(0, idLineaDescuento.indexOf(Comunes.COLON)));
        this.idInstalacion = Integer.parseInt(idLineaDescuento.substring(idLineaDescuento.indexOf(Comunes.COLON) + 1));
    }

    /**
     * Gets the id linea descuento.
     * 
     * @return the id linea descuento
     */
    public Long getIdLineaDescuento() {
        return idLineaDescuento;
    }

    /**
     * Sets the id linea descuento.
     * 
     * @param idLineaDescuento
     *            the new id linea descuento
     */
    public void setIdLineaDescuento(Long idLineaDescuento) {
        this.idLineaDescuento = idLineaDescuento;
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
        result = prime * result + ((idLineaDescuento == null) ? 0 : idLineaDescuento.hashCode());
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
        PrimaryKeyLineaPedidoDto other = (PrimaryKeyLineaPedidoDto) obj;
        if (idInstalacion == null) {
            if (other.idInstalacion != null) {
                return false;
            }
        } else if (!idInstalacion.equals(other.idInstalacion)) {
            return false;
        }
        if (idLineaDescuento == null) {
            if (other.idLineaDescuento != null) {
                return false;
            }
        } else if (!idLineaDescuento.equals(other.idLineaDescuento)) {
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

        builder.append("[ID_LINEA_DESCUENTO=");
        builder.append(idLineaDescuento);
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
        builder.append("PrimaryKeyLineaPedidoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idLineaDescuento\": ");
        builder.append(idLineaDescuento);
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
