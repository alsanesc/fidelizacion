package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import java.sql.Timestamp;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class RecursoClienteDto.
 */
public class RecursoClienteDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The primary key recurso cliente. */
    private PrimaryKeyRecursoDto idRecurso = new PrimaryKeyRecursoDto();

    /** The primary key cliente. */
    private PrimaryKeyClienteDto idCliente = new PrimaryKeyClienteDto();

    /** The fecha hora alta. */
    private Timestamp fechaHoraAlta;

    /** The fecha hora baja. */
    private Timestamp fechaHoraBaja;

    /**
     * Instantiates a new recurso cliente dto.
     */
    public RecursoClienteDto() {
    }

    /**
     * Instantiates a new recurso cliente dto.
     * 
     * @param primaryKeyRecurso
     *            the primary key recurso
     */
    public RecursoClienteDto(PrimaryKeyRecursoDto primaryKeyRecurso) {
        this.idRecurso = primaryKeyRecurso;
    }

    /**
     * Instantiates a new recurso cliente dto.
     * 
     * @param primaryKeyRecurso
     *            the primary key recurso
     * @param primaryKeyCliente
     *            the primary key cliente
     * @param fechaHoraAlta
     *            the fecha hora alta
     */
    public RecursoClienteDto(PrimaryKeyRecursoDto primaryKeyRecurso, PrimaryKeyClienteDto primaryKeyCliente, Timestamp fechaHoraAlta) {
        this.idRecurso = primaryKeyRecurso;
        this.idCliente = primaryKeyCliente;
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

    /**
     * Sets the id recurso.
     * 
     * @param idRecurso
     *            the new id recurso
     */
    public void setIdRecurso(PrimaryKeyRecursoDto idRecurso) {
        this.idRecurso = idRecurso;
    }

    /**
     * Gets the id cliente.
     * 
     * @return the id cliente
     */
    public PrimaryKeyClienteDto getIdCliente() {
        return idCliente;
    }

    /**
     * Sets the id cliente.
     * 
     * @param idCliente
     *            the new id cliente
     */
    public void setIdCliente(PrimaryKeyClienteDto idCliente) {
        this.idCliente = idCliente;
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
        RecursoClienteDto other = (RecursoClienteDto) obj;
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
        builder.append(", ID_CLIENTE=");
        builder.append(idCliente.toString());
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

        builder.append(Comunes.RETURN);
        builder.append("RecursoClienteDTO");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"primaryKeyRecurso\": ");
        builder.append(idRecurso.toStringJSON());
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idCliente\": ");
        builder.append(idCliente.toStringJSON());
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"fechaHoraAlta\": ");
        builder.append(fechaHoraAlta);

        if (fechaHoraBaja != null) {
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
            builder.append("\t\"fechaHoraBaja:\": ");
            builder.append(fechaHoraBaja);
        }

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
