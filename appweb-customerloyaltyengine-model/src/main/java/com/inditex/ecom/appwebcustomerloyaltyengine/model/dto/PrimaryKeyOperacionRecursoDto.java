package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class PrimaryKeyOperacionRecursoDto.
 */
public class PrimaryKeyOperacionRecursoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id operacion recurso. */
    private Long idOperacionRecurso;

    /** The id instalacion. */
    private Integer idInstalacion;

    /**
     * Instantiates a new primary key operacion recurso dto.
     */
    public PrimaryKeyOperacionRecursoDto() {
    }

    /**
     * Instantiates a new primary key operacion recurso dto.
     * 
     * @param idOperacionRecurso
     *            the id operacion recurso
     * @param idInstalacion
     *            the id instalacion
     */
    public PrimaryKeyOperacionRecursoDto(Long idOperacionRecurso, Integer idInstalacion) {
        this.idOperacionRecurso = idOperacionRecurso;
        this.idInstalacion = idInstalacion;
    }

    /**
     * Instantiates a new primary key operacion recurso dto.
     * 
     * @param idOperacionRecurso
     *            the id operacion recurso
     */
    public PrimaryKeyOperacionRecursoDto(String idOperacionRecurso) {
        this.idOperacionRecurso = new Long(idOperacionRecurso.substring(0, idOperacionRecurso.indexOf(Comunes.COLON)));
        this.idInstalacion = Integer.parseInt(idOperacionRecurso.substring(idOperacionRecurso.indexOf(Comunes.COLON) + 1));
    }

    /**
     * Gets the id operacion recurso.
     * 
     * @return the id operacion recurso
     */
    public Long getIdOperacionRecurso() {
        return idOperacionRecurso;
    }

    /**
     * Sets the id operacion recurso.
     * 
     * @param idOperacionRecurso
     *            the new id operacion recurso
     */
    public void setIdOperacionRecurso(Long idOperacionRecurso) {
        this.idOperacionRecurso = idOperacionRecurso;
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
        result = prime * result + ((idOperacionRecurso == null) ? 0 : idOperacionRecurso.hashCode());
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
        PrimaryKeyOperacionRecursoDto other = (PrimaryKeyOperacionRecursoDto) obj;
        if (idInstalacion == null) {
            if (other.idInstalacion != null) {
                return false;
            }
        } else if (!idInstalacion.equals(other.idInstalacion)) {
            return false;
        }
        if (idOperacionRecurso == null) {
            if (other.idOperacionRecurso != null) {
                return false;
            }
        } else if (!idOperacionRecurso.equals(other.idOperacionRecurso)) {
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

        builder.append("[ID_OPERACION_RECURSO=");
        builder.append(idOperacionRecurso);
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
        builder.append("PrimaryKeyOperacionRecursoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idOperacionRecurso\": ");
        builder.append(idOperacionRecurso);
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
