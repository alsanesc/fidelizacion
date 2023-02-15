package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class DescuentoDto.
 */
public class DescuentoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The primary key descuento. */
    private PrimaryKeyDescuentoDto idDescuento = new PrimaryKeyDescuentoDto();

    /** The codigo pedido externo. */
    private String codigoPedidoExterno;

    /** The id cadena. */
    private Short idCadena;

    /** The id pais. */
    private Short idPais;

    /** The id tienda. */
    private Integer idLocalizacion;

    /** The id estado descuento. */
    private Short idEstadoDescuento;

    /** The id detalle descuento. */
    private PrimaryKeyDetalleDescuentoDto idDetalleDescuento;

    /** The id cliente. */
    private PrimaryKeyClienteDto idCliente;

    /** The importe pedido. */
    private BigDecimal importePedido;

    /** The codigo moneda iso. */
    private Short idDivisa;

    /** The fecha hora validez descuento. */
    private Timestamp fechaHoraValidezDescuento;

    /** The numero serie operacion descuento. */
    private Long sisMarcaTiempo;

    /**
     * Instantiates a new descuento dto.
     */
    public DescuentoDto() {
    }

    /**
     * Instantiates a new descuento dto.
     * 
     * @param primaryKeyDescuento
     *            the primary key descuento
     */
    public DescuentoDto(PrimaryKeyDescuentoDto primaryKeyDescuento) {
        this.idDescuento = primaryKeyDescuento;
    }

    /**
     * Instantiates a new descuento dto.
     * 
     * @param primaryKeyDescuento
     *            the primary key descuento
     * @param codigoPedidoExterno
     *            the codigo pedido externo
     * @param idEstadoDescuento
     *            the id estado descuento
     * @param importePedido
     *            the importe pedido
     * @param idDivisa
     *            the id divisa
     * @param fechaHoraValidezDescuento
     *            the fecha hora validez descuento
     */
    public DescuentoDto(PrimaryKeyDescuentoDto primaryKeyDescuento, String codigoPedidoExterno, Short idEstadoDescuento, BigDecimal importePedido,
            Short idDivisa, Timestamp fechaHoraValidezDescuento) {
        this.idDescuento = primaryKeyDescuento;
        this.codigoPedidoExterno = codigoPedidoExterno;
        this.idEstadoDescuento = idEstadoDescuento;
        this.importePedido = importePedido;
        this.idDivisa = idDivisa;
        this.fechaHoraValidezDescuento = fechaHoraValidezDescuento;
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
     * Gets the codigo pedido externo.
     * 
     * @return the codigo pedido externo
     */
    public String getCodigoPedidoExterno() {
        return codigoPedidoExterno;
    }

    /**
     * Sets the codigo pedido externo.
     * 
     * @param codigoPedidoExterno
     *            the new codigo pedido externo
     */
    public void setCodigoPedidoExterno(String codigoPedidoExterno) {
        this.codigoPedidoExterno = codigoPedidoExterno;
    }

    /**
     * Gets the id cadena.
     * 
     * @return the id cadena
     */
    public Short getIdCadena() {
        return idCadena;
    }

    /**
     * Sets the id cadena.
     * 
     * @param idCadena
     *            the new id cadena
     */
    public void setIdCadena(Short idCadena) {
        this.idCadena = idCadena;
    }

    /**
     * Gets the id pais.
     * 
     * @return the id pais
     */
    public Short getIdPais() {
        return idPais;
    }

    /**
     * Sets the id pais.
     * 
     * @param idPais
     *            the new id pais
     */
    public void setIdPais(Short idPais) {
        this.idPais = idPais;
    }

    /**
     * Gets the id localizacion.
     * 
     * @return the id localizacion
     */
    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    /**
     * Sets the id localizacion.
     * 
     * @param idLocalizacion
     *            the new id localizacion
     */
    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    /**
     * Gets the id estado descuento.
     * 
     * @return the id estado descuento
     */
    public Short getIdEstadoDescuento() {
        return idEstadoDescuento;
    }

    /**
     * Sets the id estado descuento.
     * 
     * @param idEstadoDescuento
     *            the new id estado descuento
     */
    public void setIdEstadoDescuento(Short idEstadoDescuento) {
        this.idEstadoDescuento = idEstadoDescuento;
    }

    /**
     * Gets the id detalle descuento.
     * 
     * @return the id detalle descuento
     */
    public PrimaryKeyDetalleDescuentoDto getIdDetalleDescuento() {
        return idDetalleDescuento;
    }

    /**
     * Sets the id detalle descuento.
     * 
     * @param idDetalleDescuento
     *            the new id detalle descuento
     */
    public void setIdDetalleDescuento(PrimaryKeyDetalleDescuentoDto idDetalleDescuento) {
        this.idDetalleDescuento = idDetalleDescuento;
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
     * Gets the importe pedido.
     * 
     * @return the importe pedido
     */
    public BigDecimal getImportePedido() {
        return importePedido;
    }

    /**
     * Sets the importe pedido.
     * 
     * @param importePedido
     *            the new importe pedido
     */
    public void setImportePedido(BigDecimal importePedido) {
        this.importePedido = importePedido;
    }

    /**
     * Gets the id divisa.
     * 
     * @return the id divisa
     */
    public Short getIdDivisa() {
        return idDivisa;
    }

    /**
     * Sets the id divisa.
     * 
     * @param idDivisa
     *            the new id divisa
     */
    public void setIdDivisa(Short idDivisa) {
        this.idDivisa = idDivisa;
    }

    /**
     * Gets the fecha hora validez descuento.
     * 
     * @return the fecha hora validez descuento
     */
    public Timestamp getFechaHoraValidezDescuento() {
        return fechaHoraValidezDescuento;
    }

    /**
     * Sets the fecha hora validez descuento.
     * 
     * @param fechaHoraValidezDescuento
     *            the new fecha hora validez descuento
     */
    public void setFechaHoraValidezDescuento(Timestamp fechaHoraValidezDescuento) {
        this.fechaHoraValidezDescuento = fechaHoraValidezDescuento;
    }

    /**
     * Gets the sis marca tiempo.
     * 
     * @return the sis marca tiempo
     */
    public Long getSisMarcaTiempo() {
        return sisMarcaTiempo;
    }

    /**
     * Sets the sis marca tiempo.
     * 
     * @param sisMarcaTiempo
     *            the new sis marca tiempo
     */
    public void setSisMarcaTiempo(Long sisMarcaTiempo) {
        this.sisMarcaTiempo = sisMarcaTiempo;
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
        DescuentoDto other = (DescuentoDto) obj;
        if (idDescuento == null) {
            if (other.idDescuento != null) {
                return false;
            }
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

        builder.append("[PRIMARY_KEY_DESCUENTO=");
        builder.append(idDescuento.toString());
        builder.append(", CODIGO_PEDIDO_EXTERNO=");
        builder.append(codigoPedidoExterno);

        if (idCadena != null) {
            builder.append(", ID_CADENA=");
            builder.append(idCadena);
        }

        if (idPais != null) {
            builder.append(", ID_PAIS=");
            builder.append(idPais);
        }

        if (idLocalizacion != null) {
            builder.append(", ID_LOCALIZACION=");
            builder.append(idLocalizacion);
        }

        builder.append(", ID_ESTADO_DESCUENTO=");
        builder.append(idEstadoDescuento);

        if (idDetalleDescuento != null) {
            builder.append(", ID_DETALLE_DESCUENTO=");
            builder.append(idDetalleDescuento.toString());
        }

        if (idCliente != null) {
            builder.append(", ID_CLIENTE=");
            builder.append(idCliente.toString());
        }

        builder.append(", IMPORTE_PEDIDO=");
        builder.append(importePedido);
        builder.append(", ID_DIVISA=");
        builder.append(idDivisa);
        builder.append(", FECHA_HORA_VALIDEZ_DESCUENTO=");
        builder.append(fechaHoraValidezDescuento);
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
        builder.append("Descuento");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"primaryKeyDescuento\": ");
        builder.append(idDescuento.toStringJSON());
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"codigoPedidoExterno\": ");
        builder.append(codigoPedidoExterno);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        if (idCadena != null) {
            builder.append("\t\"idCadena\": ");
            builder.append(idCadena);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        if (idPais != null) {
            builder.append("\t\"idPais\": ");
            builder.append(idPais);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        if (idLocalizacion != null) {
            builder.append("\t\"idLocalizacion\": ");
            builder.append(idLocalizacion);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        builder.append("\t\"idEstadoDescuento\": ");
        builder.append(idEstadoDescuento);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        if (idDetalleDescuento != null) {
            builder.append("\t\"idDetalleDescuento\": ");
            builder.append(idDetalleDescuento.toStringJSON());
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        if (idCadena != null) {
            builder.append("\t\"idCliente\": ");
            builder.append(idCliente.toStringJSON());
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        builder.append("\t\"importePedido\": ");
        builder.append(importePedido);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idDivisa\": ");
        builder.append(idDivisa);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"fechaHoraValidezDescuento\": ");
        builder.append(fechaHoraValidezDescuento);

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
