/*
 * 
 */
package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import net.sf.oval.constraint.NotNull;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class AltaCliente.
 */
public class AltaCliente extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The cod resultado. */
    @NotNull
    private Integer codResultado;

    /** The descripcion motivo rechazo. */
    private String descripcionMotivoRechazo;

    /** The id operacion descuento. */
    private Integer idOperacionDescuento;

    /** The cliente. */
    private Cliente cliente;

    /**
     * Instantiates a new alta cliente.
     */
    public AltaCliente() {
    }

    /**
     * Gets the cod resultado.
     * 
     * @return the cod resultado
     */
    public Integer getCodResultado() {
        return codResultado;
    }

    /**
     * Sets the cod resultado.
     * 
     * @param codResultado
     *            the new cod resultado
     */
    public void setCodResultado(Integer codResultado) {
        this.codResultado = codResultado;
    }

    /**
     * Gets the descripcion motivo rechazo.
     * 
     * @return the descripcion motivo rechazo
     */
    public String getDescripcionMotivoRechazo() {
        return descripcionMotivoRechazo;
    }

    /**
     * Sets the descripcion motivo rechazo.
     * 
     * @param descripcionMotivoRechazo
     *            the new descripcion motivo rechazo
     */
    public void setDescripcionMotivoRechazo(String descripcionMotivoRechazo) {
        this.descripcionMotivoRechazo = descripcionMotivoRechazo;
    }

    /**
     * Gets the id operacion descuento.
     * 
     * @return the id operacion descuento
     */
    public Integer getIdOperacionDescuento() {
        return idOperacionDescuento;
    }

    /**
     * Sets the id operacion descuento.
     * 
     * @param idOperacionDescuento
     *            the new id operacion descuento
     */
    public void setIdOperacionDescuento(Integer idOperacionDescuento) {
        this.idOperacionDescuento = idOperacionDescuento;
    }

    /**
     * Gets the cliente.
     * 
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets the cliente.
     * 
     * @param cliente
     *            the new cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
        result = prime * result + ((codResultado == null) ? 0 : codResultado.hashCode());
        result = prime * result + ((descripcionMotivoRechazo == null) ? 0 : descripcionMotivoRechazo.hashCode());
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
        AltaCliente other = (AltaCliente) obj;
        if (cliente == null) {
            if (other.cliente != null) {
                return false;
            }
        } else if (!cliente.equals(other.cliente)) {
            return false;
        }
        if (codResultado == null) {
            if (other.codResultado != null) {
                return false;
            }
        } else if (!codResultado.equals(other.codResultado)) {
            return false;
        }
        if (descripcionMotivoRechazo == null) {
            if (other.descripcionMotivoRechazo != null) {
                return false;
            }
        } else if (!descripcionMotivoRechazo.equals(other.descripcionMotivoRechazo)) {
            return false;
        }
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
        
        builder.append("[COD_RESULTADO=");
        builder.append(codResultado);
        
        if (descripcionMotivoRechazo != null) {
            builder.append(", DESCRIPCION_MOTIVO_RECHAZO=");
            builder.append(descripcionMotivoRechazo);
        }
        
        if (idOperacionDescuento != null) {
            builder.append(", ID_OPERACION_DESCUENTO=");
            builder.append(idOperacionDescuento);
        }
        
        if (cliente != null) {
            builder.append(", CLIENTE=");
            builder.append(cliente);
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
        String saltoDeLinea = "\n";
        String separator = ",";

        StringBuilder builder = new StringBuilder();
        
        builder.append(saltoDeLinea);
        builder.append("AltaCliente");
        builder.append(saltoDeLinea);
        builder.append("{");
        
        builder.append("\t\"codResultado\": ");
        builder.append(codResultado);
        
        if (descripcionMotivoRechazo != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"descripcionMotivoRechazo\": ");
            builder.append(descripcionMotivoRechazo);
        }
        
        if (idOperacionDescuento != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"idOperacionDescuento\": ");
            builder.append(idOperacionDescuento);
        }
        
        if (cliente != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"cliente\": ");
            builder.append(cliente.toStringJSON());
        }
        
        builder.append(saltoDeLinea);
        builder.append("}");
        builder.append(saltoDeLinea);
        
        return builder.toString();
    }

}
