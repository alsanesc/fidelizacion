package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class PrimaryKeyRecursoDto.
 */
public class PrimaryKeyRecursoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id recurso. */
    private Long idRecurso;

    /** The id instalacion. */
    private Integer idInstalacion;

    /**
     * Instantiates a new primary key recurso dto.
     */
    public PrimaryKeyRecursoDto() {
    }

    /**
     * Instantiates a new primary key recurso dto.
     * 
     * @param idRecurso
     *            the id recurso
     * @param idInstalacion
     *            the id instalacion
     */
    public PrimaryKeyRecursoDto(Long idRecurso, Integer idInstalacion) {
        this.idRecurso = idRecurso;
        this.idInstalacion = idInstalacion;
    }

    /**
     * Instantiates a new primary key recurso dto.
     * 
     * @param idRecurso
     *            the id recurso
     */
    public PrimaryKeyRecursoDto(String idRecurso) {
        this.idRecurso = new Long(idRecurso.substring(0, idRecurso.indexOf(Comunes.COLON)));
        this.idInstalacion = Integer.parseInt(idRecurso.substring(idRecurso.indexOf(Comunes.COLON) + 1));
    }

    /**
     * Gets the id recurso.
     * 
     * @return the id recurso
     */
    public Long getIdRecurso() {
        return idRecurso;
    }

    /**
     * Sets the id recurso.
     * 
     * @param idRecurso
     *            the new id recurso
     */
    public void setIdRecurso(Long idRecurso) {
        this.idRecurso = idRecurso;
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
        result = prime * result + ((idRecurso == null) ? 0 : idRecurso.hashCode());
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
        PrimaryKeyRecursoDto other = (PrimaryKeyRecursoDto) obj;
        if (idInstalacion == null) {
            if (other.idInstalacion != null) {
                return false;
            }
        } else if (!idInstalacion.equals(other.idInstalacion)) {
            return false;
        }
        if (idRecurso == null) {
            if (other.idRecurso != null) {
                return false;
            }
        } else if (!idRecurso.equals(other.idRecurso)) {
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

        builder.append("[ID_RECURSO=");
        builder.append(idRecurso);
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
        builder.append("PrimaryKeyRecursoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idRecurso\": ");
        builder.append(idRecurso);
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
