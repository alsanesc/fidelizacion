package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class OperacionDescuentoDto.
 */
public class OperacionDescuentoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The primary key operacion descuento. */
    private PrimaryKeyOperacionDescuentoDto idOperacionDescuento = new PrimaryKeyOperacionDescuentoDto();

    /** The operacion tarjeta list. */
    private String codigoOperacionExterno;

    /** The id descuento. */
    private PrimaryKeyDescuentoDto idDescuento;

    /** The id tipo operacion descuento. */
    private Short idTipoOperacionDescuento;

    /** The fecha hora operacion. */
    private Timestamp fechaHoraOperacion;

    /** The importe operacion. */
    private BigDecimal importeOperacion;

    /**
     * Instantiates a new operacion descuento dto.
     */
    public OperacionDescuentoDto() {
    }

    /**
     * Instantiates a new operacion descuento dto.
     * 
     * @param primaryKeyOperacionDescuento
     *            the primary key operacion descuento
     */
    public OperacionDescuentoDto(PrimaryKeyOperacionDescuentoDto primaryKeyOperacionDescuento) {
        this.idOperacionDescuento = primaryKeyOperacionDescuento;
    }

    /**
     * Instantiates a new operacion descuento dto.
     * 
     * @param primaryKeyOperacionDescuento
     *            the primary key operacion descuento
     * @param codigoOperacionExterno
     *            the codigo operacion externo
     * @param idDescuento
     *            the id descuento
     * @param idTipoOperacionDescuento
     *            the id tipo operacion descuento
     * @param fechaHoraOperacion
     *            the fecha hora operacion
     */
    public OperacionDescuentoDto(PrimaryKeyOperacionDescuentoDto primaryKeyOperacionDescuento, String codigoOperacionExterno,
            PrimaryKeyDescuentoDto idDescuento, Short idTipoOperacionDescuento, Timestamp fechaHoraOperacion) {
        this.idOperacionDescuento = primaryKeyOperacionDescuento;
        this.codigoOperacionExterno = codigoOperacionExterno;
        this.idDescuento = idDescuento;
        this.idTipoOperacionDescuento = idTipoOperacionDescuento;
        this.fechaHoraOperacion = fechaHoraOperacion;
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
     * Gets the codigo operacion externo.
     * 
     * @return the codigo operacion externo
     */
    public String getCodigoOperacionExterno() {
        return codigoOperacionExterno;
    }

    /**
     * Sets the codigo operacion externo.
     * 
     * @param codigoOperacionExterno
     *            the new codigo operacion externo
     */
    public void setCodigoOperacionExterno(String codigoOperacionExterno) {
        this.codigoOperacionExterno = codigoOperacionExterno;
    }

    /**
     * Gets the id descuento.
     * 
     * @return the id descuento
     */
    public PrimaryKeyDescuentoDto getIdDescuento() {
        return idDescuento;
    }

    /**
     * Sets the id descuento.
     * 
     * @param idDescuento
     *            the new id descuento
     */
    public void setIdDescuento(PrimaryKeyDescuentoDto idDescuento) {
        this.idDescuento = idDescuento;
    }

    /**
     * Gets the id tipo operacion descuento.
     * 
     * @return the id tipo operacion descuento
     */
    public Short getIdTipoOperacionDescuento() {
        return idTipoOperacionDescuento;
    }

    /**
     * Sets the id tipo operacion descuento.
     * 
     * @param idTipoOperacionDescuento
     *            the new id tipo operacion descuento
     */
    public void setIdTipoOperacionDescuento(Short idTipoOperacionDescuento) {
        this.idTipoOperacionDescuento = idTipoOperacionDescuento;
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
     * Gets the importe operacion.
     * 
     * @return the importe operacion
     */
    public BigDecimal getImporteOperacion() {
        return importeOperacion;
    }

    /**
     * Sets the importe operacion.
     * 
     * @param importeOperacion
     *            the new importe operacion
     */
    public void setImporteOperacion(BigDecimal importeOperacion) {
        this.importeOperacion = importeOperacion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idOperacionDescuento == null) ? 0 : idOperacionDescuento.hashCode());
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
        OperacionDescuentoDto other = (OperacionDescuentoDto) obj;
        if (idOperacionDescuento == null) {
            if (other.idOperacionDescuento != null) {
                return false;
            }
        } else if (!idOperacionDescuento.equals(other.idOperacionDescuento)) {
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

        builder.append("[ID_OPERACION_DESCUENTO=");
        builder.append(idOperacionDescuento.toString());
        builder.append("CODIGO_OPERACION_EXTERNO=");
        builder.append(codigoOperacionExterno);
        builder.append(", ID_DESCUENTO=");
        builder.append(idDescuento);
        builder.append(", ID_TIPO_OPERACION_DESCUENTO=");
        builder.append(idTipoOperacionDescuento);
        builder.append(", FECHA_HORA_OPERACION=");
        builder.append(fechaHoraOperacion);

        if (importeOperacion != null) {
            builder.append(", IMPORTE_OPERACION=");
            builder.append(importeOperacion);
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

        builder.append("OperacionDescuentoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idOperacionDescuento\": ");
        builder.append(idOperacionDescuento.toStringJSON());
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"codigoOperacionExterno\": ");
        builder.append(codigoOperacionExterno);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"descuento\": ");
        builder.append(idDescuento);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"tipoOperacionDescuento\": ");
        builder.append(idTipoOperacionDescuento);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"fechaHoraOperacion\": ");
        builder.append(fechaHoraOperacion);

        if (importeOperacion != null) {
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
            builder.append("\t\"importeOperacion\": ");
            builder.append(importeOperacion);
        }

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
