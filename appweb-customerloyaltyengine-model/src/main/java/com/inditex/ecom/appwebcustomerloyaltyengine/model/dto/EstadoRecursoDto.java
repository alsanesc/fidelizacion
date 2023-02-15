package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class EstadoTarjetaDto.
 */
public class EstadoRecursoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id estado tarjeta. */
    private Short idEstadoRecurso;

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new estado tarjeta dto.
     */
    public EstadoRecursoDto() {
    }

    /**
     * Instantiates a new estado tarjeta dto.
     * 
     * @param idEstadoRecurso
     *            the id estado recurso
     */
    public EstadoRecursoDto(Short idEstadoRecurso) {
        this.idEstadoRecurso = idEstadoRecurso;
    }

    /**
     * Gets the id estado recurso.
     * 
     * @return the id estado recurso
     */
    public Short getIdEstadoRecurso() {
        return idEstadoRecurso;
    }

    /**
     * Sets the id estado recurso.
     * 
     * @param idEstadoRecurso
     *            the new id estado recurso
     */
    public void setIdEstadoRecurso(Short idEstadoRecurso) {
        this.idEstadoRecurso = idEstadoRecurso;
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
        result = prime * result + ((idEstadoRecurso == null) ? 0 : idEstadoRecurso.hashCode());
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
        EstadoRecursoDto other = (EstadoRecursoDto) obj;
        if (idEstadoRecurso == null) {
            if (other.idEstadoRecurso != null) {
                return false;
            }
        } else if (!idEstadoRecurso.equals(other.idEstadoRecurso)) {
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

        builder.append("[ID_ESTADO_RECURSO=");
        builder.append(idEstadoRecurso);
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
        builder.append("EstadoRecursoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idEstadoRecurso\": ");
        builder.append(idEstadoRecurso);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);
        builder.append("\t\"descripcion\": ");
        builder.append(descripcion);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
