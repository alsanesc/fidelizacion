package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class PrimaryKeyClienteDto.
 */
public class PrimaryKeyClienteDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id cliente. */
    private Long idCliente;

    /** The id instalacion. */
    private Integer idInstalacion;

    /**
     * Instantiates a new primary key cliente dto.
     */
    public PrimaryKeyClienteDto() {
    }

    /**
     * Instantiates a new primary key cliente dto.
     * 
     * @param idCliente
     *            the id cliente
     * @param idInstalacion
     *            the id instalacion
     */
    public PrimaryKeyClienteDto(Long idCliente, Integer idInstalacion) {
        this.idCliente = idCliente;
        this.idInstalacion = idInstalacion;
    }

    /**
     * Instantiates a new primary key cliente dto.
     * 
     * @param idCliente
     *            the id cliente
     */
    public PrimaryKeyClienteDto(String idCliente) {
        this.idCliente = new Long(idCliente.substring(0, idCliente.indexOf(Comunes.COLON)));
        this.idInstalacion = Integer.parseInt(idCliente.substring(idCliente.indexOf(Comunes.COLON) + 1));
    }

    /**
     * Gets the id cliente.
     * 
     * @return the id cliente
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * Sets the id cliente.
     * 
     * @param idCliente
     *            the new id cliente
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
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
        result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
        PrimaryKeyClienteDto other = (PrimaryKeyClienteDto) obj;
        if (idCliente == null) {
            if (other.idCliente != null) {
                return false;
            }
        } else if (!idCliente.equals(other.idCliente)) {
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

        builder.append("[ID_CLIENTE=");
        builder.append(idCliente);
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
        builder.append("PrimaryKeyClienteDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idCliente\": ");
        builder.append(idCliente);
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
