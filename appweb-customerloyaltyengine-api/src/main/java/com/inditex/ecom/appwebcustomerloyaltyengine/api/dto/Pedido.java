package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import java.math.BigDecimal;
import java.util.List;

import net.sf.oval.constraint.NotNull;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class PedidoDTO.
 */
public class Pedido extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Identificador del pedido. */
    @NotNull
    private Integer idPedido;

    /** Identificador del cliente ecommerce. */
    private String idClienteEcommerce;

    /** Identificador del cliente descuentos. */
    @NotNull
    private String idClienteDescuentos;

    /** Identificador de la tienda. */
    @NotNull
    private Integer idTienda;

    /** Codigo del pais. */
    private String codPais;

    /** Lista de linea de pedido. */
    @NotNull
    private List<LineaPedido> lineasPedido;

    /** Importe total del pedido. */
    @NotNull
    private BigDecimal importeTotalPedido;

    /** The id divisa. */
    @NotNull
    private String divisa;

    /** Lista de descuentos solicitados. */
    private List<DatosDescuento> descuentosSolicitados;

    /** Identificador de version de reglas. */
    private Integer idVersionReglas;

    /** The puntos adicionales. */
    @NotNull
    private Integer puntosAdicionales;

    /** The estado pedido. */
    private String estadoPedido;

    /**
     * Instantiates a new pedido dto.
     */
    public Pedido() {
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
     * @param idPedido
     *            the new id pedido
     */
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * Gets the id cliente ecommerce.
     * 
     * @return the id cliente ecommerce
     */
    public String getIdClienteEcommerce() {
        return idClienteEcommerce;
    }

    /**
     * Sets the id cliente ecommerce.
     * 
     * @param idClienteEcommerce
     *            the new id cliente ecommerce
     */
    public void setIdClienteEcommerce(String idClienteEcommerce) {
        this.idClienteEcommerce = idClienteEcommerce;
    }

    /**
     * Gets the id cliente descuentos.
     * 
     * @return the id cliente descuentos
     */
    public String getIdClienteDescuentos() {
        return idClienteDescuentos;
    }

    /**
     * Sets the id cliente descuentos.
     * 
     * @param idClienteDescuentos
     *            the new id cliente descuentos
     */
    public void setIdClienteDescuentos(String idClienteDescuentos) {
        this.idClienteDescuentos = idClienteDescuentos;
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
     * @param idTienda
     *            the new id tienda
     */
    public void setIdTienda(Integer idTienda) {
        this.idTienda = idTienda;
    }

    /**
     * Gets the cod pais.
     * 
     * @return the cod pais
     */
    public String getCodPais() {
        return codPais;
    }

    /**
     * Sets the cod pais.
     * 
     * @param codPais
     *            the new cod pais
     */
    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    /**
     * Gets the lineas pedido.
     * 
     * @return the lineas pedido
     */
    public List<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    /**
     * Sets the lineas pedido.
     * 
     * @param lineasPedido
     *            the new lineas pedido
     */
    public void setLineasPedido(List<LineaPedido> lineasPedido) {
        this.lineasPedido = lineasPedido;
    }

    /**
     * Gets the importe total pedido.
     * 
     * @return the importe total pedido
     */
    public BigDecimal getImporteTotalPedido() {
        return importeTotalPedido;
    }

    /**
     * Sets the importe total pedido.
     * 
     * @param importeTotalPedido
     *            the new importe total pedido
     */
    public void setImporteTotalPedido(BigDecimal importeTotalPedido) {
        this.importeTotalPedido = importeTotalPedido;
    }

    /**
     * Gets the divisa.
     * 
     * @return the divisa
     */
    public String getDivisa() {
        return divisa;
    }

    /**
     * Sets the divisa.
     * 
     * @param divisa
     *            the new divisa
     */
    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    /**
     * Gets the descuentos solicitados.
     * 
     * @return the descuentos solicitados
     */
    public List<DatosDescuento> getDescuentosSolicitados() {
        return descuentosSolicitados;
    }

    /**
     * Sets the descuentos solicitados.
     * 
     * @param descuentosSolicitados
     *            the new descuentos solicitados
     */
    public void setDescuentosSolicitados(List<DatosDescuento> descuentosSolicitados) {
        this.descuentosSolicitados = descuentosSolicitados;
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
     * Gets the puntos adicionales.
     * 
     * @return the puntos adicionales
     */
    public Integer getPuntosAdicionales() {
        return puntosAdicionales;
    }

    /**
     * Sets the puntos adicionales.
     * 
     * @param puntosAdicionales
     *            the new puntos adicionales
     */
    public void setPuntosAdicionales(Integer puntosAdicionales) {
        this.puntosAdicionales = puntosAdicionales;
    }

    /**
     * Gets the estado pedido.
     * 
     * @return the estado pedido
     */
    public String getEstadoPedido() {
        return estadoPedido;
    }

    /**
     * Sets the estado pedido.
     * 
     * @param estadoPedido
     *            the new estado pedido
     */
    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lineasPedido == null) ? 0 : lineasPedido.hashCode());
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

        Pedido other = (Pedido) obj;
        if (lineasPedido == null) {
            if (other.lineasPedido != null) {
                return false;
            }
        } else if (!lineasPedido.equals(other.lineasPedido)) {
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
        builder.append("[ID_PEDIDO=");
        builder.append(idPedido);

        if (idClienteEcommerce != null) {
            builder.append(", ID_CLIENTE_ECOMMERCE=");
            builder.append(idClienteEcommerce);
        }

        builder.append(", ID_CLIENTE_DESCUENTOS=");
        builder.append(idClienteDescuentos);
        builder.append(", ID_TIENDA=");
        builder.append(idTienda);

        if (codPais != null) {
            builder.append(", COD_PAIS=");
            builder.append(codPais);
        }

        builder.append(", LINEAS_PEDIDO={");
        if (lineasPedido != null) {
            for (LineaPedido lineaPedido : lineasPedido) {
                builder.append(lineaPedido.toString());
            }
        }

        builder.append("}, IMPORTE_TOTAL_PEDIDO=");
        builder.append(importeTotalPedido);

        builder.append("}, DIVISA=");
        builder.append(divisa);

        builder.append(", DESCUENTOS_SOLICITADOS={");
        if (descuentosSolicitados != null) {
            for (DatosDescuento datosDescuento : descuentosSolicitados) {
                builder.append(datosDescuento.toString());
            }
        }

        if (idVersionReglas != null) {
            builder.append("}, ID_VERSION_REGLAS=");
            builder.append(idVersionReglas);
        } else {
            builder.append("}");
        }

        builder.append("}, PUNTOS_ADICIONALES=");
        builder.append(puntosAdicionales);
        
        if (estadoPedido != null) {
            builder.append(", ESTADO_PEDIDO=");
            builder.append(estadoPedido);
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

        builder.append("Pedidot");
        builder.append(saltoDeLinea);
        builder.append("{");
        builder.append(saltoDeLinea);

        builder.append("\t\"idPedido\": ");
        builder.append(idPedido);

        if (idClienteEcommerce != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"idClienteEcommerce\": ");
            builder.append(idClienteEcommerce);
        }

        builder.append(separator);
        builder.append(saltoDeLinea);
        builder.append("\t\"idClienteDescuentos\": ");
        builder.append(idClienteDescuentos);

        builder.append(separator);
        builder.append(saltoDeLinea);
        builder.append("\t\"idTienda\": ");
        builder.append(idTienda);

        if (codPais != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"codPais\": ");
            builder.append(codPais);
        }

        builder.append(separator);
        builder.append(saltoDeLinea);
        builder.append("\t\"lineasPedido\": [ ");
        builder.append(saltoDeLinea);
        if (lineasPedido != null) {
            for (int i = 0; i < lineasPedido.size(); i++) {
                builder.append(lineasPedido.get(i).toStringJSON());
                if (i != lineasPedido.size() - 1) {
                    builder.append(separator);
                    builder.append(saltoDeLinea);
                }
            }
        }
        builder.append(saltoDeLinea);
        builder.append("\t]");

        builder.append(separator);
        builder.append(saltoDeLinea);
        builder.append("\t\"importeTotalPedido\": ");
        builder.append(importeTotalPedido);

        builder.append(separator);
        builder.append(saltoDeLinea);
        builder.append("\t\"divisa\": ");
        builder.append(divisa);

        builder.append(separator);
        builder.append(saltoDeLinea);
        builder.append("\t\"descuentosSolicitados\": [");
        if (descuentosSolicitados != null) {
            for (int i = 0; i < descuentosSolicitados.size(); i++) {
                builder.append(descuentosSolicitados.get(i).toStringJSON());
                if (i != descuentosSolicitados.size() - 1) {
                    builder.append(separator);
                    builder.append(saltoDeLinea);
                }
            }
        }
        builder.append(saltoDeLinea);
        builder.append("\t]");

        if (idVersionReglas != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t \"idVersionReglas\": ");
            builder.append(idVersionReglas);
        }

        builder.append("\t\"puntosAdicionales\": ");
        builder.append(puntosAdicionales);
        
        if (estadoPedido != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"estadoPedido\": ");
            builder.append(estadoPedido);
        }
        
        builder.append(saltoDeLinea);
        builder.append("}");
        builder.append(saltoDeLinea);

        return builder.toString();
    }

}
