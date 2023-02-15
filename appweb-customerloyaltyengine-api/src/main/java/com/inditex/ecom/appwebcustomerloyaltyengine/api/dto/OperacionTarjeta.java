package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import java.math.BigDecimal;

import net.sf.oval.constraint.NotNull;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class OperacionTarjetato.
 */
public class OperacionTarjeta extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The importe. */
    @NotNull
    private BigDecimal importe;
    
    /** The estado. */
    @NotNull
    private String estado;
    
    /** The id tienda. */
    @NotNull
    private Integer idTienda;
    
    /** The fecha transaccion. */
    @NotNull
    private String fechaTransaccion;
    
    /** The tipo operacion. */
    @NotNull
    private String tipoOperacion;

    /**
     * Instantiates a new operacion tarjetato.
     */
    public OperacionTarjeta() {
    }

    /**
     * Gets the importe.
     *
     * @return the importe
     */
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * Sets the importe.
     *
     * @param importe the new importe
     */
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the estado.
     *
     * @param estado the new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets the id tienda.
     *
     * @return the id tienda
     */
    public Integer getIdTienda() {
        return idTienda;
    }

    /**
     * Sets the id tienda.
     *
     * @param idTienda the new id tienda
     */
    public void setIdTienda(Integer idTienda) {
        this.idTienda = idTienda;
    }

    /**
     * Gets the fecha transaccion.
     *
     * @return the fecha transaccion
     */
    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    /**
     * Sets the fecha transaccion.
     *
     * @param fechaTransaccion the new fecha transaccion
     */
    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    /**
     * Gets the tipo operacion.
     *
     * @return the tipo operacion
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Sets the tipo operacion.
     *
     * @param tipoOperacion the new tipo operacion
     */
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((fechaTransaccion == null) ? 0 : fechaTransaccion.hashCode());
        result = prime * result + ((idTienda == null) ? 0 : idTienda.hashCode());
        result = prime * result + ((importe == null) ? 0 : importe.hashCode());
        result = prime * result + ((tipoOperacion == null) ? 0 : tipoOperacion.hashCode());
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
        
        OperacionTarjeta other = (OperacionTarjeta) obj;
        if (estado == null) {
            if (other.estado != null) {
                return false;
            }
        } else if (!estado.equals(other.estado)) {
            return false;
        }
        
        if (fechaTransaccion == null) {
            if (other.fechaTransaccion != null) {
                return false;
            }
        } else if (!fechaTransaccion.equals(other.fechaTransaccion)) {
            return false;
        }
        
        if (idTienda == null) {
            if (other.idTienda != null) {
                return false;
            }
        } else if (!idTienda.equals(other.idTienda)) {
            return false;
        }
        
        if (importe == null) {
            if (other.importe != null) {
                return false;
            }
        } else if (!importe.equals(other.importe)) {
            return false;
        }
        
        if (tipoOperacion == null) {
            if (other.tipoOperacion != null) {
                return false;
            }
        } else if (!tipoOperacion.equals(other.tipoOperacion)) {
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
        
        builder.append("[IMPORTE=");
        builder.append(importe);
        builder.append(", ESTADO=");
        builder.append(estado);
        builder.append(", ID_TIENDA=");
        builder.append(idTienda);
        builder.append(", FECHA_TRANSACCION=");
        builder.append(fechaTransaccion);
        builder.append(", TIPO_OPERACION=");
        builder.append(tipoOperacion);
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
        
        builder.append("OperacionTarjeta");
        builder.append(saltoDeLinea);
        builder.append("{");
        builder.append(saltoDeLinea);
        
        builder.append("\t\"importe\": ");
        builder.append(importe);
        builder.append(separator);
        builder.append(saltoDeLinea);

        builder.append("\t\"estado\": ");
        builder.append(estado);
        builder.append(separator);
        builder.append(saltoDeLinea);
        
        builder.append("\t\"idTienda\": ");
        builder.append(idTienda);
        builder.append(separator);
        builder.append(saltoDeLinea);
        
        builder.append("\t\"fechaTransaccion\": ");
        builder.append(fechaTransaccion);
        builder.append(separator);
        builder.append(saltoDeLinea);
        
        builder.append("\t\"tipoOperacion\": ");
        builder.append(tipoOperacion);
        
        builder.append(saltoDeLinea);
        builder.append("}");
        builder.append(saltoDeLinea);
        
        return builder.toString();
    }

}
