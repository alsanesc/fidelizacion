package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class TipoLineaPedidoDto.
 */
public class TipoLineaPedidoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id tipo linea pedido. */
    private Short idTipoLineaPedido;

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new tipo linea pedido dto.
     */
    public TipoLineaPedidoDto() {
    }

    /**
     * Instantiates a new tipo linea pedido dto.
     * 
     * @param idTipoLineaPedido
     *            the id tipo linea pedido
     */
    public TipoLineaPedidoDto(Short idTipoLineaPedido) {
        this.idTipoLineaPedido = idTipoLineaPedido;
    }

    /**
     * Gets the id tipo linea pedido.
     * 
     * @return the id tipo linea pedido
     */
    public Short getIdTipoLineaPedido() {
        return idTipoLineaPedido;
    }

    /**
     * Sets the id tipo linea pedido.
     * 
     * @param idTipoLineaPedido
     *            the new id tipo linea pedido
     */
    public void setIdTipoLineaPedido(Short idTipoLineaPedido) {
        this.idTipoLineaPedido = idTipoLineaPedido;
    }

    /**
     * Gets the descripcion.
     * 
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the descripcion.
     * 
     * @param descripcion
     *            the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idTipoLineaPedido == null) ? 0 : idTipoLineaPedido.hashCode());
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
        TipoLineaPedidoDto other = (TipoLineaPedidoDto) obj;
        if (idTipoLineaPedido == null) {
            if (other.idTipoLineaPedido != null) {
                return false;
            }
        } else if (!idTipoLineaPedido.equals(other.idTipoLineaPedido)) {
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

        builder.append("[ID_TIPO_LINEA_PEDIDO=");
        builder.append(idTipoLineaPedido);
        builder.append(", DESCRIPCION=");
        builder.append(descripcion);
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
        builder.append("TipoLineaPedidoDto");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idTipoLineaPedido\": ");
        builder.append(idTipoLineaPedido);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"Descripcion\": ");
        builder.append(descripcion);

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
