package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import java.math.BigDecimal;

import net.sf.oval.constraint.NotNull;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class DatosDescuentoDto.
 */
public class DatosDescuento extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** Identificador del descuento. */
    @NotNull
    private Integer idDescuento;
    
    /** Porcentaje de descuento. */
    private BigDecimal porcentajeDescuento;
    
    /** Importe de descuento. */
    private BigDecimal importeDescuento;
    
    /** Puntos del empleado. */
    private BigDecimal puntosEmpleados;

    /**
     * Instantiates a new datos descuento dto.
     */
    public DatosDescuento() {
    }
    
    /**
     * Gets the id descuento.
     *
     * @return the id descuento
     */
    public Integer getIdDescuento() {
        return idDescuento;
    }

    /**
     * Sets the id descuento.
     *
     * @param idDescuento the new id descuento
     */
    public void setIdDescuento(Integer idDescuento) {
        this.idDescuento = idDescuento;
    }

    /**
     * Gets the porcentaje descuento.
     *
     * @return the porcentaje descuento
     */
    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    /**
     * Sets the porcentaje descuento.
     *
     * @param porcentajeDescuento the new porcentaje descuento
     */
    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    /**
     * Gets the importe descuento.
     *
     * @return the importe descuento
     */
    public BigDecimal getImporteDescuento() {
        return importeDescuento;
    }

    /**
     * Sets the importe descuento.
     *
     * @param importeDescuento the new importe descuento
     */
    public void setImporteDescuento(BigDecimal importeDescuento) {
        this.importeDescuento = importeDescuento;
    }

    /**
     * Gets the puntos empleados.
     *
     * @return the puntos empleados
     */
    public BigDecimal getPuntosEmpleados() {
        return puntosEmpleados;
    }

    /**
     * Sets the puntos empleados.
     *
     * @param puntosEmpleados the new puntos empleados
     */
    public void setPuntosEmpleados(BigDecimal puntosEmpleados) {
        this.puntosEmpleados = puntosEmpleados;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idDescuento == null) ? 0 : idDescuento.hashCode());
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
        
        DatosDescuento other = (DatosDescuento) obj;
        if (idDescuento == null) {
            if (other.idDescuento != null)
                return false;
        } else if (!idDescuento.equals(other.idDescuento)) {
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
        
        builder.append("[ID_DESCUENTO=");
        builder.append(idDescuento);
        builder.append(", PORCENTAJE_DESCUENTO=");
        builder.append(porcentajeDescuento);
        builder.append(", IMPORTE_DESCUENTO=");
        builder.append(importeDescuento);
        builder.append(", PUNTOS_EMPLEADOS=");
        builder.append(puntosEmpleados);
        builder.append("]");
        
        return builder.toString();
    }
    
    /**
     * To string json.
     *
     * @return the string
     */
    public String toStringJSON() {
        String saltoDeLinea = "\n";
        String separator = ",";
        
        StringBuilder builder = new StringBuilder();
        builder.append(saltoDeLinea);
        
        builder.append("DatosDescuento");
        builder.append(saltoDeLinea);
        builder.append("{");
        builder.append(saltoDeLinea);
        
        builder.append("\t\"idDescuento\": ");
        builder.append(idDescuento);
        
        if (porcentajeDescuento != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"porcentajeDescuento\": ");
            builder.append(porcentajeDescuento);
        }
        
        if (importeDescuento != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"importeDescuento\": ");
            builder.append(importeDescuento);
        }

        if (puntosEmpleados != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"puntosEmpleados\": ");
            builder.append(puntosEmpleados);
        }
        builder.append(saltoDeLinea);
        builder.append("}");
        builder.append(saltoDeLinea);
        
        return builder.toString();
    }
    
}
