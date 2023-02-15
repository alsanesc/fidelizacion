package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class EstadoClienteDto.
 */
public class EstadoClienteDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id estado cliente. */
    private Short idEstadoCliente;

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new estado cliente dto.
     */
    public EstadoClienteDto() {
    }

    /**
     * Instantiates a new estado cliente dto.
     * 
     * @param idEstadoCliente
     *            the id estado cliente
     */
    public EstadoClienteDto(Short idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    /**
     * Gets the id estado cliente.
     * 
     * @return the id estado cliente
     */
    public Short getIdEstadoCliente() {
        return idEstadoCliente;
    }

    /**
     * Sets the id estado cliente.
     * 
     * @param idEstadoCliente
     *            the new id estado cliente
     */
    public void setIdEstadoCliente(Short idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
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
        result = prime * result + ((idEstadoCliente == null) ? 0 : idEstadoCliente.hashCode());
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
        EstadoClienteDto other = (EstadoClienteDto) obj;
        if (idEstadoCliente == null) {
            if (other.idEstadoCliente != null) {
                return false;
            }
        } else if (!idEstadoCliente.equals(other.idEstadoCliente)) {
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

        builder.append("[ID_ESTADO_CLIENTE=");
        builder.append(idEstadoCliente);
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
        builder.append("EstadoClienteDTO");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idEstadoCliente\": ");
        builder.append(idEstadoCliente);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"Descripcion\": ");
        builder.append(descripcion);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
