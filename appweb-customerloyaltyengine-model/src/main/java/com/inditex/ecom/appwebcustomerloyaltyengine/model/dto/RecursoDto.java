package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class TarjetaDto.
 */
public class RecursoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The primary key recurso. */
    private PrimaryKeyRecursoDto idRecurso = new PrimaryKeyRecursoDto();

    /** The id tipo recurso. */
    private Integer idTipoRecurso;

    /** The id cadena. */
    private Short idCadena;

    /** The cod pais iso. */
    private Short idPais;

    /** The id estado recurso. */
    private Short idEstadoRecurso;

    /** The id tipo valor recurso. */
    private Short idTipoValorRecurso;

    /** The valor recurso. */
    private BigDecimal valorRecurso;

    /** The valor disponible recurso. */
    private BigDecimal valorDisponibleRecurso;

    /** The fecha hora alta. */
    private Timestamp fechaHoraAlta;

    /** The fecha hora baja. */
    private Timestamp fechaHoraBaja;

    /** The sis marca tiempo. */
    private Long sisMarcaTiempo;

    /**
     * Instantiates a new recurso dto.
     */
    public RecursoDto() {
    }

    /**
     * Instantiates a new recurso dto.
     * 
     * @param primaryKeyRecurso
     *            the primary key recurso
     */
    public RecursoDto(PrimaryKeyRecursoDto primaryKeyRecurso) {
        this.idRecurso = primaryKeyRecurso;
    }

    /**
     * Instantiates a new recurso dto.
     * 
     * @param primaryKeyRecurso
     *            the primary key recurso
     * @param idTipoRecurso
     *            the id tipo recurso
     * @param idEstadoRecurso
     *            the id estado recurso
     * @param idTipoValorRecurso
     *            the id tipo valor recurso
     * @param fechaHoraAlta
     *            the fecha hora alta
     */
    public RecursoDto(PrimaryKeyRecursoDto primaryKeyRecurso, Integer idTipoRecurso, Short idEstadoRecurso, Short idTipoValorRecurso,
            Timestamp fechaHoraAlta) {
        this.idRecurso = primaryKeyRecurso;
        this.idTipoRecurso = idTipoRecurso;
        this.idEstadoRecurso = idEstadoRecurso;
        this.idTipoValorRecurso = idTipoValorRecurso;
        this.fechaHoraAlta = fechaHoraAlta;
    }

    /**
     * Gets the id recurso.
     * 
     * @return the id recurso
     */
    public PrimaryKeyRecursoDto getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(PrimaryKeyRecursoDto idRecurso) {
        this.idRecurso = idRecurso;
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
     * Gets the id cadena.
     * 
     * @return the id cadena
     */
    public Short getIdCadena() {
        return idCadena;
    }

    /**
     * Sets the id cadena.
     * 
     * @param idCadena
     *            the new id cadena
     */
    public void setIdCadena(Short idCadena) {
        this.idCadena = idCadena;
    }

    /**
     * Gets the id pais.
     * 
     * @return the id pais
     */
    public Short getIdPais() {
        return idPais;
    }

    /**
     * Sets the id pais.
     * 
     * @param idPais
     *            the new id pais
     */
    public void setIdPais(Short idPais) {
        this.idPais = idPais;
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
     * Gets the valor recurso.
     * 
     * @return the valor recurso
     */
    public BigDecimal getValorRecurso() {
        return valorRecurso;
    }

    /**
     * Sets the valor recurso.
     * 
     * @param valorRecurso
     *            the new valor recurso
     */
    public void setValorRecurso(BigDecimal valorRecurso) {
        this.valorRecurso = valorRecurso;
    }

    /**
     * Gets the valor disponible recurso.
     * 
     * @return the valor disponible recurso
     */
    public BigDecimal getValorDisponibleRecurso() {
        return valorDisponibleRecurso;
    }

    /**
     * Sets the valor disponible recurso.
     * 
     * @param valorDisponibleRecurso
     *            the new valor disponible recurso
     */
    public void setValorDisponibleRecurso(BigDecimal valorDisponibleRecurso) {
        this.valorDisponibleRecurso = valorDisponibleRecurso;
    }

    /**
     * Gets the fecha hora alta.
     * 
     * @return the fecha hora alta
     */
    public Timestamp getFechaHoraAlta() {
        return fechaHoraAlta;
    }

    /**
     * Sets the fecha hora alta.
     * 
     * @param fechaHoraAlta
     *            the new fecha hora alta
     */
    public void setFechaHoraAlta(Timestamp fechaHoraAlta) {
        this.fechaHoraAlta = fechaHoraAlta;
    }

    /**
     * Gets the fecha hora baja.
     * 
     * @return the fecha hora baja
     */
    public Timestamp getFechaHoraBaja() {
        return fechaHoraBaja;
    }

    /**
     * Sets the fecha hora baja.
     * 
     * @param fechaHoraBaja
     *            the new fecha hora baja
     */
    public void setFechaHoraBaja(Timestamp fechaHoraBaja) {
        this.fechaHoraBaja = fechaHoraBaja;
    }

    /**
     * Gets the sis marca tiempo.
     * 
     * @return the sis marca tiempo
     */
    public Long getSisMarcaTiempo() {
        return sisMarcaTiempo;
    }

    /**
     * Sets the sis marca tiempo.
     * 
     * @param sisMarcaTiempo
     *            the new sis marca tiempo
     */
    public void setSisMarcaTiempo(Long sisMarcaTiempo) {
        this.sisMarcaTiempo = sisMarcaTiempo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        RecursoDto other = (RecursoDto) obj;
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

        builder.append("[PRIMARY_KEY_RECURSO=");
        builder.append(idRecurso.toString());
        builder.append(", ID_TIPO_RECURSO=");
        builder.append(idTipoRecurso);

        if (idCadena != null) {
            builder.append(", ID_CADENA=");
            builder.append(idCadena);
        }

        if (idPais != null) {
            builder.append(", ID_PAIS=");
            builder.append(idPais);
        }

        builder.append(", ID_ESTADO_RECURSO=");
        builder.append(idEstadoRecurso);
        builder.append(", ID_TIPO_VALOR_RECURSO=");
        builder.append(idTipoValorRecurso);

        if (valorRecurso != null) {
            builder.append(", VALOR_RECURSO=");
            builder.append(valorRecurso);
        }

        if (valorDisponibleRecurso != null) {
            builder.append(", VALOR_DISPONIBLE_RECURSO=");
            builder.append(valorRecurso);
        }

        builder.append(", FECHA_HORA_ALTA=");
        builder.append(fechaHoraAlta);

        if (fechaHoraBaja != null) {
            builder.append(", FECHA_HORA_BAJA=");
            builder.append(fechaHoraBaja);
        }

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

        builder.append("RecursoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"primaryKeyRecurso\": ");
        builder.append(idRecurso.toStringJSON());
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idTipoRecurso\": ");
        builder.append(idTipoRecurso);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        if (idCadena != null) {
            builder.append("\t\"idCadena\": ");
            builder.append(idCadena);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        if (idPais != null) {
            builder.append("\t\"idPais\": ");
            builder.append(idPais);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        builder.append("\t\"idEstadoRecurso\": ");
        builder.append(idEstadoRecurso);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idTipoValorRecurso\": ");
        builder.append(idTipoValorRecurso);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        if (valorRecurso != null) {
            builder.append("\t\"valorRecurso\": ");
            builder.append(valorRecurso);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        if (valorDisponibleRecurso != null) {
            builder.append("\t\"valorDisponibleRecurso\": ");
            builder.append(valorDisponibleRecurso);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        builder.append("\t\"fechaHoraAlta\": ");
        builder.append(fechaHoraAlta);

        if (fechaHoraBaja != null) {
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
            builder.append("\t\"fechaHoraBaja\": ");
            builder.append(fechaHoraBaja);
        }

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
