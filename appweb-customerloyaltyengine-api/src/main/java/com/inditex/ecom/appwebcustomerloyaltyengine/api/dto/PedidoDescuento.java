package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import java.util.List;

import net.sf.oval.constraint.NotNull;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class PedidoDescuentoDto.
 */
public class PedidoDescuento extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The id descuento. */
    @NotNull
    private String idDescuento;
    
    /** The id pedido. */
    @NotNull
    private Integer idPedido;
    
    /** The descuento aplicado pedido. */
    @NotNull
    private DescuentoAplicado descuentoAplicadoPedido;
    
    /** The lineas pedido descuento. */
    @NotNull
    private List<LineaPedido> lineasPedidoDescuento;

    /**
     * Instantiates a new pedido descuento dto.
     */
    public PedidoDescuento() {
    }

    /**
     * Gets the id descuento.
     *
     * @return the id descuento
     */
    public String getIdDescuento() {
        return idDescuento;
    }

    /**
     * Sets the id descuento.
     *
     * @param idDescuento the new id descuento
     */
    public void setIdDescuento(String idDescuento) {
        this.idDescuento = idDescuento;
    }

    /**
     * Gets the id pedido.
     *
     * @return the id pedido
     */
    public Integer getIdPedido() {
        return idPedido;
    }

    /**
     * Sets the id pedido.
     *
     * @param idPedido the new id pedido
     */
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * Gets the descuento aplicado pedido.
     *
     * @return the descuento aplicado pedido
     */
    public DescuentoAplicado getDescuentoAplicadoPedido() {
        return descuentoAplicadoPedido;
    }

    /**
     * Sets the descuento aplicado pedido.
     *
     * @param descuentoAplicadoPedido the new descuento aplicado pedido
     */
    public void setDescuentoAplicadoPedido(DescuentoAplicado descuentoAplicadoPedido) {
        this.descuentoAplicadoPedido = descuentoAplicadoPedido;
    }

    /**
     * Gets the lineas pedido descuento.
     *
     * @return the lineas pedido descuento
     */
    public List<LineaPedido> getLineasPedidoDescuento() {
        return lineasPedidoDescuento;
    }

    /**
     * Sets the lineas pedido descuento.
     *
     * @param lineasPedidoDescuento the new lineas pedido descuento
     */
    public void setLineasPedidoDescuento(List<LineaPedido> lineasPedidoDescuento) {
        this.lineasPedidoDescuento = lineasPedidoDescuento;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descuentoAplicadoPedido == null) ? 0 : descuentoAplicadoPedido.hashCode());
        result = prime * result + ((idDescuento == null) ? 0 : idDescuento.hashCode());
        result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
        result = prime * result + ((lineasPedidoDescuento == null) ? 0 : lineasPedidoDescuento.hashCode());
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
        
        PedidoDescuento other = (PedidoDescuento) obj;
        if (descuentoAplicadoPedido == null) {
            if (other.descuentoAplicadoPedido != null) {
                return false;
            }
        } else if (!descuentoAplicadoPedido.equals(other.descuentoAplicadoPedido)) {
            return false;
        }
        
        if (idDescuento == null) {
            if (other.idDescuento != null) {
                return false;
            }
        } else if (!idDescuento.equals(other.idDescuento)) {
            return false;
        }
        
        if (idPedido == null) {
            if (other.idPedido != null) {
                return false;
            }
        } else if (!idPedido.equals(other.idPedido)) {
            return false;
        }
        
        if (lineasPedidoDescuento == null) {
            if (other.lineasPedidoDescuento != null) {
                return false;
            }
        } else if (!lineasPedidoDescuento.equals(other.lineasPedidoDescuento)) {
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
        builder.append(", ID_PEDIDO=");
        builder.append(idPedido);
        builder.append(", DESCUENTO_APLICADO_PEDIDO");
        builder.append(descuentoAplicadoPedido.toString());
        
        builder.append(", LINEAS_PEDIDO_DESCUENTO");
        for (LineaPedido lineaPedido : lineasPedidoDescuento) {
            builder.append(lineaPedido.toString());
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
        
        builder.append("PedidoDescuento");
        builder.append(saltoDeLinea);
        builder.append("{");
        builder.append(saltoDeLinea);
        
        builder.append("\t\"idDescuento\": ");
        builder.append(idDescuento);
        builder.append(separator);
        builder.append(saltoDeLinea);
        
        builder.append("\t\"idPedido\": ");
        builder.append(idPedido);
        builder.append(separator);
        builder.append(saltoDeLinea);
        
        builder.append("\t\"descuentoAplicadoPedido\": ");
        builder.append(descuentoAplicadoPedido.toStringJSON());
        builder.append(separator);
        builder.append(saltoDeLinea);
        
        builder.append("\t\"lineasPedidoDescuento\": [");
        for (int i = 0; i < lineasPedidoDescuento.size(); i++) {
            builder.append(lineasPedidoDescuento.get(i).toStringJSON());
            if (i != lineasPedidoDescuento.size() - 1) {
                builder.append(separator);
                builder.append(saltoDeLinea);
            }
        }
        builder.append("\t]");
        
        builder.append(saltoDeLinea);
        builder.append("}");
        builder.append(saltoDeLinea);
        
        return builder.toString();
    }

}
