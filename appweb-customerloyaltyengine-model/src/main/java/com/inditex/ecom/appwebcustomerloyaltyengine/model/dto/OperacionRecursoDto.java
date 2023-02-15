package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class OperacionRecursoDto.
 */
public class OperacionRecursoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The primary key operacion recurso. */
    private PrimaryKeyOperacionRecursoDto idOperacionRecurso = new PrimaryKeyOperacionRecursoDto();

    /** The codigo operacion recurso externo. */
    private String codigoOperacionRecursoExterno;

    /** The id operacion descuento. */
    private PrimaryKeyOperacionDescuentoDto idOperacionDescuento;

    /** The id tipo operacion recurso. */
    private Short idTipoOperacionRecurso;

    /** The id recurso. */
    private PrimaryKeyRecursoDto idRecurso;

    /** The id tipo valor operacion. */
    private Short idTipoValorOperacion;

    /** The importe operacion. */
    private BigDecimal valorOperacion;

    /** The valor operacion recurso. */
    private BigDecimal valorOperacionRecurso;

    /** The fecha hora operacion. */
    private Timestamp fechaHoraOperacion;

    /**
     * Instantiates a new operacion recurso dto.
     */
    public OperacionRecursoDto() {
    }

    /**
     * Instantiates a new operacion recurso dto.
     * 
     * @param primaryKeyOperacionRecurso
     *            the primary key operacion recurso
     */
    public OperacionRecursoDto(PrimaryKeyOperacionRecursoDto primaryKeyOperacionRecurso) {
        this.idOperacionRecurso = primaryKeyOperacionRecurso;
    }

    /**
     * Instantiates a new operacion recurso dto.
     * 
     * @param primaryKeyOperacionRecurso
     *            the primary key operacion recurso
     * @param idTipoOperacionRecurso
     *            the id tipo operacion recurso
     * @param idRecurso
     *            the id recurso
     * @param fechaHoraOperacion
     *            the fecha hora operacion
     */
    public OperacionRecursoDto(PrimaryKeyOperacionRecursoDto primaryKeyOperacionRecurso, Short idTipoOperacionRecurso,
            PrimaryKeyRecursoDto idRecurso,
            Timestamp fechaHoraOperacion) {
        this.idOperacionRecurso = primaryKeyOperacionRecurso;
        this.idTipoOperacionRecurso = idTipoOperacionRecurso;
        this.idRecurso = idRecurso;
        this.fechaHoraOperacion = fechaHoraOperacion;
    }

    /**
     * Gets the id operacion recurso.
     * 
     * @return the id operacion recurso
     */
    public PrimaryKeyOperacionRecursoDto getIdOperacionRecurso() {
        return idOperacionRecurso;
    }

    /**
     * Sets the id operacion recurso.
     * 
     * @param idOperacionRecurso
     *            the new id operacion recurso
     */
    public void setIdOperacionRecurso(PrimaryKeyOperacionRecursoDto idOperacionRecurso) {
        this.idOperacionRecurso = idOperacionRecurso;
    }

    /**
     * Gets the codigo operacion recurso externo.
     * 
     * @return the codigo operacion recurso externo
     */
    public String getCodigoOperacionRecursoExterno() {
        return codigoOperacionRecursoExterno;
    }

    /**
     * Sets the codigo operacion recurso externo.
     * 
     * @param codigoOperacionRecursoExterno
     *            the new codigo operacion recurso externo
     */
    public void setCodigoOperacionRecursoExterno(String codigoOperacionRecursoExterno) {
        this.codigoOperacionRecursoExterno = codigoOperacionRecursoExterno;
    }

    /**
     * Gets the id operacion descuento.
     * 
     * @return the id operacion descuento
     */
    public PrimaryKeyOperacionDescuentoDto getIdOperacionDescuento() {
        return idOperacionDescuento;
    }

    /**
     * Sets the id operacion descuento.
     * 
     * @param idOperacionDescuento
     *            the new id operacion descuento
     */
    public void setIdOperacionDescuento(PrimaryKeyOperacionDescuentoDto idOperacionDescuento) {
        this.idOperacionDescuento = idOperacionDescuento;
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
     * Gets the id tipo valor operacion.
     * 
     * @return the id tipo valor operacion
     */
    public Short getIdTipoValorOperacion() {
        return idTipoValorOperacion;
    }

    /**
     * Sets the id tipo valor operacion.
     * 
     * @param idTipoValorOperacion
     *            the new id tipo valor operacion
     */
    public void setIdTipoValorOperacion(Short idTipoValorOperacion) {
        this.idTipoValorOperacion = idTipoValorOperacion;
    }

    /**
     * Gets the valor operacion.
     * 
     * @return the valor operacion
     */
    public BigDecimal getValorOperacion() {
        return valorOperacion;
    }

    /**
     * Sets the valor operacion.
     * 
     * @param valorOperacion
     *            the new valor operacion
     */
    public void setValorOperacion(BigDecimal valorOperacion) {
        this.valorOperacion = valorOperacion;
    }

    /**
     * Gets the valor operacion recurso.
     * 
     * @return the valor operacion recurso
     */
    public BigDecimal getValorOperacionRecurso() {
        return valorOperacionRecurso;
    }

    /**
     * Sets the valor operacion recurso.
     * 
     * @param valorOperacionRecurso
     *            the new valor operacion recurso
     */
    public void setValorOperacionRecurso(BigDecimal valorOperacionRecurso) {
        this.valorOperacionRecurso = valorOperacionRecurso;
    }

    /**
     * Gets the fecha hora operacion.
     * 
     * @return the fecha hora operacion
     */
    public Timestamp getFechaHoraOperacion() {
        return fechaHoraOperacion;
    }

    /**
     * Sets the fecha hora operacion.
     * 
     * @param fechaHoraOperacion
     *            the new fecha hora operacion
     */
    public void setFechaHoraOperacion(Timestamp fechaHoraOperacion) {
        this.fechaHoraOperacion = fechaHoraOperacion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        OperacionRecursoDto other = (OperacionRecursoDto) obj;
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

        builder.append("[PRIMARY_KEY_OPERACION_RECURSO=");
        builder.append(idOperacionRecurso.toString());

        if (codigoOperacionRecursoExterno != null) {
            builder.append(", CODIGO_OPERACION_RECURSO_EXTERNO=");
            builder.append(codigoOperacionRecursoExterno);
        }

        if (idOperacionDescuento != null) {
            builder.append(", ID_OPERACION_DESCUENTO=");
            builder.append(idOperacionDescuento.toString());
        }

        builder.append(", ID_TIPO_OPERACION_RECURSO=");
        builder.append(idTipoOperacionRecurso);
        builder.append(", ID_RECURSO=");
        builder.append(idRecurso.toString());

        if (idTipoValorOperacion != null) {
            builder.append(", TIPO_VALOR_OPERACION=");
            builder.append(idTipoValorOperacion);
        }

        if (valorOperacion != null) {
            builder.append(", VALOR_OPERACION=");
            builder.append(valorOperacion);
        }

        if (valorOperacionRecurso != null) {
            builder.append(", VALOR_OPERACION_RECURSO=");
            builder.append(valorOperacionRecurso);
        }

        builder.append(", FECHA_HORA_OPERACION=");
        builder.append(fechaHoraOperacion);
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

        builder.append("OperacionRecurso");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"primaryKeyOperacionRecurso\": ");
        builder.append(idOperacionRecurso.toStringJSON());
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        if (codigoOperacionRecursoExterno != null) {
            builder.append("\t\"codigoOperacionRecursoExterno\": ");
            builder.append(codigoOperacionRecursoExterno);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        if (idOperacionDescuento != null) {
            builder.append("\t\"idOperacionDescuento\":");
            builder.append(idOperacionDescuento.toStringJSON());
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        builder.append("\t\"idTipoOperacionRecurso\": ");
        builder.append(idTipoOperacionRecurso);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idRecurso\": ");
        builder.append(idRecurso.toStringJSON());
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        if (idTipoValorOperacion != null) {
            builder.append("\t\"idTipoValorOperacion\": ");
            builder.append(idTipoValorOperacion);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        if (valorOperacion != null) {
            builder.append("\t\"valorOperacion\": ");
            builder.append(valorOperacion);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        if (valorOperacionRecurso != null) {
            builder.append("\t\"valorOperacionRecurso\": ");
            builder.append(valorOperacionRecurso);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        builder.append("\t\"fechaHoraOperacion\": ");
        builder.append(fechaHoraOperacion);

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
