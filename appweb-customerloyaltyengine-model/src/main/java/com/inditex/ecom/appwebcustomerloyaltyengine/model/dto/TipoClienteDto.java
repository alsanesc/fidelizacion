package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class TipoClienteDto.
 */
public class TipoClienteDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id tipo cliente. */
    private Short idTipoCliente;

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new tipo cliente dto.
     */
    public TipoClienteDto() {
    }

    /**
     * Instantiates a new tipo cliente dto.
     * 
     * @param idTipoCliente
     *            the id tipo cliente
     */
    public TipoClienteDto(Short idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    /**
     * Gets the id tipo cliente.
     * 
     * @return the id tipo cliente
     */
    public Short getIdTipoCliente() {
        return idTipoCliente;
    }

    /**
     * Sets the id tipo cliente.
     * 
     * @param idTipoCliente
     *            the new id tipo cliente
     */
    public void setIdTipoCliente(Short idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
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
        result = prime * result + ((idTipoCliente == null) ? 0 : idTipoCliente.hashCode());
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
        TipoClienteDto other = (TipoClienteDto) obj;
        if (idTipoCliente == null) {
            if (other.idTipoCliente != null) {
                return false;
            }
        } else if (!idTipoCliente.equals(other.idTipoCliente)) {
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

        builder.append("[ID_TIPO_CLIENTE=");
        builder.append(idTipoCliente);
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
        builder.append("TipoClienteDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idTipoCliente\": ");
        builder.append(idTipoCliente);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"Descripcion\": ");
        builder.append(descripcion);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return null;
    }

}
