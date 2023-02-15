package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import java.util.List;

import net.sf.oval.constraint.NotNull;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class AsignacionPedidoDto.
 */
public class AsignacionPedido extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The lista descuentos aplicados. */
    @NotNull
    private List<DatosDescuento> listaDescuentosAplicados;

    /** The lista pedido descuentos. */
    @NotNull
    private List<PedidoDescuento> listaPedidoDescuentos;

    /** The id version reglas. */
    @NotNull
    private Integer idVersionReglas;

    /** The cod resultado. */
    @NotNull
    private Integer codResultado;

    /** The descripcion motivo rechazo. */
    private String descripcionMotivoRechazo;
    

    /**
     * Instantiates a new asignacion pedido dto.
     */
    public AsignacionPedido() {
    }

    /**
     * Gets the lista descuentos aplicados.
     * 
     * @return the lista descuentos aplicados
     */
    public List<DatosDescuento> getListaDescuentosAplicados() {
        return listaDescuentosAplicados;
    }

    /**
     * Sets the lista descuentos aplicados.
     * 
     * @param listaDescuentosAplicados
     *            the new lista descuentos aplicados
     */
    public void setListaDescuentosAplicados(List<DatosDescuento> listaDescuentosAplicados) {
        this.listaDescuentosAplicados = listaDescuentosAplicados;
    }

    /**
     * Gets the lista pedido descuentos.
     * 
     * @return the lista pedido descuentos
     */
    public List<PedidoDescuento> getListaPedidoDescuentos() {
        return listaPedidoDescuentos;
    }

    /**
     * Sets the lista pedido descuentos.
     * 
     * @param listaPedidoDescuentos
     *            the new lista pedido descuentos
     */
    public void setListaPedidoDescuentos(List<PedidoDescuento> listaPedidoDescuentos) {
        this.listaPedidoDescuentos = listaPedidoDescuentos;
    }

    /**
     * Gets the id version reglas.
     * 
     * @return the id version reglas
     */
    public Integer getIdVersionReglas() {
        return idVersionReglas;
    }

    /**
     * Sets the id version reglas.
     * 
     * @param idVersionReglas
     *            the new id version reglas
     */
    public void setIdVersionReglas(Integer idVersionReglas) {
        this.idVersionReglas = idVersionReglas;
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
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idVersionReglas == null) ? 0 : idVersionReglas.hashCode());
        result = prime * result + ((listaDescuentosAplicados == null) ? 0 : listaDescuentosAplicados.hashCode());
        result = prime * result + ((listaPedidoDescuentos == null) ? 0 : listaPedidoDescuentos.hashCode());
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

        AsignacionPedido other = (AsignacionPedido) obj;

        if (idVersionReglas == null) {
            if (other.idVersionReglas != null) {
                return false;
            }
        } else if (!idVersionReglas.equals(other.idVersionReglas)) {
            return false;
        }

        if (listaDescuentosAplicados == null) {
            if (other.listaDescuentosAplicados != null) {
                return false;
            }
        } else if (!listaDescuentosAplicados.equals(other.listaDescuentosAplicados)) {
            return false;
        }

        if (listaPedidoDescuentos == null) {
            if (other.listaPedidoDescuentos != null) {
                return false;
            }
        } else if (!listaPedidoDescuentos.equals(other.listaPedidoDescuentos)) {
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

        builder.append("[LISTA_DESCUENTOS_APLICADOS={");
        for (DatosDescuento datosDescuento : listaDescuentosAplicados) {
            builder.append(datosDescuento.toString());
        }

        builder.append("}, LISTA_PEDIDO_DESCUENTOS={");
        for (PedidoDescuento pedidoDescuento : listaPedidoDescuentos) {
            builder.append(pedidoDescuento.toString());
        }

        builder.append(", ID_VERSION_REGLAS=");
        builder.append(idVersionReglas);
        
        builder.append(", COD_RESULTADO=");
        builder.append(codResultado);
        
        if (descripcionMotivoRechazo != null) {
            builder.append(", DESCRIPCION_MOTIVO_RECHAZO=");
            builder.append(descripcionMotivoRechazo);
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

        builder.append("AsignacionPedido");
        builder.append(saltoDeLinea);
        builder.append("{");
        builder.append(saltoDeLinea);

        builder.append("\t\"listaDescuentosAplicados: [");
        builder.append(saltoDeLinea);
        for (int i = 0; i < listaDescuentosAplicados.size(); i++) {
            builder.append(listaDescuentosAplicados.get(i).toStringJSON());
            if (i != listaDescuentosAplicados.size() - 1) {
                builder.append(separator);
                builder.append(saltoDeLinea);
            }
        }
        builder.append("\t]");
        builder.append(separator);
        builder.append(saltoDeLinea);

        builder.append("\t\"listaPedidoDescuentos\": [");
        for (int i = 0; i < listaPedidoDescuentos.size(); i++) {
            builder.append(listaPedidoDescuentos.get(i).toStringJSON());
            if (i != listaPedidoDescuentos.size() - 1) {
                builder.append(separator);
                builder.append(saltoDeLinea);
            }
        }
        builder.append("\t]");
        builder.append(separator);
        builder.append(saltoDeLinea);

        builder.append("\t\"idVersionReglas\": ");
        builder.append(idVersionReglas);
        builder.append(separator);
        builder.append(saltoDeLinea);
        
        builder.append("\t\"codResultado\": ");
        builder.append(codResultado);
        
        if (descripcionMotivoRechazo != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"descripcionMotivoRechazo\": ");
            builder.append(descripcionMotivoRechazo);
        }

        builder.append(saltoDeLinea);
        builder.append("}");
        builder.append(saltoDeLinea);

        return builder.toString();
    }

}
