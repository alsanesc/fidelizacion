package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class TipoValorRecursoDto.
 */
public class TipoValorRecursoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id tipo valor recurso. */
    private Short idTipoValorRecurso;

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new tipo valor recurso dto.
     */
    public TipoValorRecursoDto() {
    }

    /**
     * Instantiates a new tipo valor recurso dto.
     * 
     * @param idTipoValorRecurso
     *            the id tipo valor recurso
     * @param descripcion
     *            the descripcion
     */
    public TipoValorRecursoDto(Short idTipoValorRecurso, String descripcion) {
        this.idTipoValorRecurso = idTipoValorRecurso;
        this.descripcion = descripcion;
    }

    /**
     * Gets the id tipo valor recurso.
     * 
     * @return the id tipo valor recurso
     */
    public Short getIdTipoValorRecurso() {
        return idTipoValorRecurso;
    }

    /**
     * Sets the id tipo valor recurso.
     * 
     * @param idTipoValorRecurso
     *            the new id tipo valor recurso
     */
    public void setIdTipoValorRecurso(Short idTipoValorRecurso) {
        this.idTipoValorRecurso = idTipoValorRecurso;
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
        result = prime * result + ((idTipoValorRecurso == null) ? 0 : idTipoValorRecurso.hashCode());
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
        TipoValorRecursoDto other = (TipoValorRecursoDto) obj;
        if (idTipoValorRecurso == null) {
            if (other.idTipoValorRecurso != null) {
                return false;
            }
        } else if (!idTipoValorRecurso.equals(other.idTipoValorRecurso)) {
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

        builder.append("[ID_TIPO_VALOR_RECURSO=");
        builder.append(idTipoValorRecurso);
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
        builder.append("TipoValorRecursoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idTipoValorRecurso\": ");
        builder.append(idTipoValorRecurso);
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
