package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class TipoRecursoDto.
 */
public class TipoRecursoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id tipo recurso. */
    private Integer idTipoRecurso;

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new tipo recurso dto.
     */
    public TipoRecursoDto() {
    }

    /**
     * Instantiates a new tipo recurso dto.
     * 
     * @param idTipoRecurso
     *            the id tipo recurso
     */
    public TipoRecursoDto(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    /**
     * Gets the id tipo recurso.
     * 
     * @return the id tipo recurso
     */
    public Integer getIdTipoRecurso() {
        return idTipoRecurso;
    }

    /**
     * Sets the id tipo recurso.
     * 
     * @param idTipoRecurso
     *            the new id tipo recurso
     */
    public void setIdTipoRecurso(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
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
        result = prime * result + ((idTipoRecurso == null) ? 0 : idTipoRecurso.hashCode());
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
        TipoRecursoDto other = (TipoRecursoDto) obj;
        if (idTipoRecurso == null) {
            if (other.idTipoRecurso != null) {
                return false;
            }
        } else if (!idTipoRecurso.equals(other.idTipoRecurso)) {
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

        builder.append("[ID_TIPO_RECURSO=");
        builder.append(idTipoRecurso);
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
        builder.append("TipoRecursoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idTipoRecurso\": ");
        builder.append(idTipoRecurso);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"descripcion\": ");
        builder.append(descripcion);

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return null;
    }

}
