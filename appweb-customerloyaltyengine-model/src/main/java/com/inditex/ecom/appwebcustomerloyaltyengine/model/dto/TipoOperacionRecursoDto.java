package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class TipoOperacionRecursoDto.
 */
public class TipoOperacionRecursoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id tipo operacion recurso. */
    private Short idTipoOperacionRecurso;

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new tipo operacion recurso dto.
     */
    public TipoOperacionRecursoDto() {
    }

    /**
     * Instantiates a new tipo operacion recurso dto.
     * 
     * @param idTipoOperacionRecurso
     *            the id tipo operacion recurso
     */
    public TipoOperacionRecursoDto(Short idTipoOperacionRecurso) {
        this.idTipoOperacionRecurso = idTipoOperacionRecurso;
    }

    /**
     * Gets the id tipo operacion recurso.
     * 
     * @return the id tipo operacion recurso
     */
    public Short getIdTipoOperacionRecurso() {
        return idTipoOperacionRecurso;
    }

    /**
     * Sets the id tipo operacion recurso.
     * 
     * @param idTipoOperacionRecurso
     *            the new id tipo operacion recurso
     */
    public void setIdTipoOperacionRecurso(Short idTipoOperacionRecurso) {
        this.idTipoOperacionRecurso = idTipoOperacionRecurso;
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
        result = prime * result + ((idTipoOperacionRecurso == null) ? 0 : idTipoOperacionRecurso.hashCode());
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
        TipoOperacionRecursoDto other = (TipoOperacionRecursoDto) obj;
        if (idTipoOperacionRecurso == null) {
            if (other.idTipoOperacionRecurso != null) {
                return false;
            }
        } else if (!idTipoOperacionRecurso.equals(other.idTipoOperacionRecurso)) {
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

        builder.append("[ID_TIPO_OPERACION_RECURSO=");
        builder.append(idTipoOperacionRecurso);
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
        builder.append("TipoOperacionRecursoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idTipoOperacionRecurso\": ");
        builder.append(idTipoOperacionRecurso);
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
