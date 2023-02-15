package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import java.math.BigDecimal;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

// TODO: Auto-generated Javadoc
/**
 * The Class LineaDescuentoDto.
 */
public class LineaDescuentoDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The primary key linea pedido. */
    private PrimaryKeyLineaPedidoDto idLineaPedido = new PrimaryKeyLineaPedidoDto();

    /** The id descuento. */
    private PrimaryKeyDescuentoDto idDescuento;

    /** The codigo linea pedido externo. */
    private String codigoLineaPedidoExterno;

    /** The id tipo linea pedido. */
    private Short idTipoLineaPedido;

    /** The part number. */
    private String partNumber;

    /** The numero unidades. */
    private Integer numeroUnidades;

    /** The numero unidades capturadas. */
    private Integer numeroUnidadesCapturadas;

    /** The numero unidades devueltas. */
    private Integer numeroUnidadesDevueltas;

    /** The importe unitario. */
    private BigDecimal importeUnitario;

    /** The importe total. */
    private BigDecimal importeTotal;

    /** The id estado descuento. */
    private Short idEstadoDescuento;

    /** The id detalle descuento. */
    private PrimaryKeyDetalleDescuentoDto idDetalleDescuento;

    /**
     * Instantiates a new linea descuento dto.
     */
    public LineaDescuentoDto() {
    }

    /**
     * Instantiates a new linea descuento dto.
     * 
     * @param primaryKeyLineaPedido
     *            the primary key linea pedido
     */
    public LineaDescuentoDto(PrimaryKeyLineaPedidoDto primaryKeyLineaPedido) {
        this.idLineaPedido = primaryKeyLineaPedido;
    }

    /**
     * Instantiates a new linea descuento dto.
     * 
     * @param primaryKeyLineaPedido
     *            the primary key linea pedido
     * @param idDescuento
     *            the id descuento
     * @param codigoLineaPedidoExterno
     *            the codigo linea pedido externo
     * @param idTipoLineaPedido
     *            the id tipo linea pedido
     * @param partNumber
     *            the part number
     * @param numeroUnidades
     *            the numero unidades
     * @param importeUnitario
     *            the importe unitario
     * @param importeTotal
     *            the importe total
     * @param idEstadoDescuento
     *            the id estado descuento
     */
    public LineaDescuentoDto(PrimaryKeyLineaPedidoDto primaryKeyLineaPedido, PrimaryKeyDescuentoDto idDescuento, String codigoLineaPedidoExterno,
            Short idTipoLineaPedido, String partNumber, Integer numeroUnidades, BigDecimal importeUnitario, BigDecimal importeTotal,
            Short idEstadoDescuento) {
        this.idLineaPedido = primaryKeyLineaPedido;
        this.idDescuento = idDescuento;
        this.codigoLineaPedidoExterno = codigoLineaPedidoExterno;
        this.idTipoLineaPedido = idTipoLineaPedido;
        this.partNumber = partNumber;
        this.numeroUnidades = numeroUnidades;
        this.importeUnitario = importeUnitario;
        this.importeTotal = importeTotal;
        this.idEstadoDescuento = idEstadoDescuento;
    }

    /**
     * Gets the id linea pedido.
     * 
     * @return the id linea pedido
     */
    public PrimaryKeyLineaPedidoDto getIdLineaPedido() {
        return idLineaPedido;
    }

    /**
     * Sets the id linea pedido.
     * 
     * @param idLineaPedido
     *            the new id linea pedido
     */
    public void setIdLineaPedido(PrimaryKeyLineaPedidoDto idLineaPedido) {
        this.idLineaPedido = idLineaPedido;
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
     * Gets the codigo linea pedido externo.
     * 
     * @return the codigo linea pedido externo
     */
    public String getCodigoLineaPedidoExterno() {
        return codigoLineaPedidoExterno;
    }

    /**
     * Sets the codigo linea pedido externo.
     * 
     * @param codigoLineaPedidoExterno
     *            the new codigo linea pedido externo
     */
    public void setCodigoLineaPedidoExterno(String codigoLineaPedidoExterno) {
        this.codigoLineaPedidoExterno = codigoLineaPedidoExterno;
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
     * Gets the part number.
     * 
     * @return the part number
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * Sets the part number.
     * 
     * @param partNumber
     *            the new part number
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * Gets the numero unidades.
     * 
     * @return the numero unidades
     */
    public Integer getNumeroUnidades() {
        return numeroUnidades;
    }

    /**
     * Sets the numero unidades.
     * 
     * @param numeroUnidades
     *            the new numero unidades
     */
    public void setNumeroUnidades(Integer numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    /**
     * Gets the numero unidades capturadas.
     * 
     * @return the numero unidades capturadas
     */
    public Integer getNumeroUnidadesCapturadas() {
        return numeroUnidadesCapturadas;
    }

    /**
     * Sets the numero unidades capturadas.
     * 
     * @param numeroUnidadesCapturadas
     *            the new numero unidades capturadas
     */
    public void setNumeroUnidadesCapturadas(Integer numeroUnidadesCapturadas) {
        this.numeroUnidadesCapturadas = numeroUnidadesCapturadas;
    }

    /**
     * Gets the numero unidades devueltas.
     * 
     * @return the numero unidades devueltas
     */
    public Integer getNumeroUnidadesDevueltas() {
        return numeroUnidadesDevueltas;
    }

    /**
     * Sets the numero unidades devueltas.
     * 
     * @param numeroUnidadesDevueltas
     *            the new numero unidades devueltas
     */
    public void setNumeroUnidadesDevueltas(Integer numeroUnidadesDevueltas) {
        this.numeroUnidadesDevueltas = numeroUnidadesDevueltas;
    }

    /**
     * Gets the importe unitario.
     * 
     * @return the importe unitario
     */
    public BigDecimal getImporteUnitario() {
        return importeUnitario;
    }

    /**
     * Sets the importe unitario.
     * 
     * @param importeUnitario
     *            the new importe unitario
     */
    public void setImporteUnitario(BigDecimal importeUnitario) {
        this.importeUnitario = importeUnitario;
    }

    /**
     * Gets the importe total.
     * 
     * @return the importe total
     */
    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    /**
     * Sets the importe total.
     * 
     * @param importeTotal
     *            the new importe total
     */
    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idLineaPedido == null) ? 0 : idLineaPedido.hashCode());
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
        LineaDescuentoDto other = (LineaDescuentoDto) obj;
        if (idLineaPedido == null) {
            if (other.idLineaPedido != null) {
                return false;
            }
        } else if (!idLineaPedido.equals(other.idLineaPedido)) {
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

        builder.append("[PRIMARY_KEY_LINEA_PEDIDO=");
        builder.append(idLineaPedido.toString());
        builder.append(", ID_DESCUENTO=");
        builder.append(idDescuento);
        builder.append(", CODIGO_LINEA_PEDIDO_EXTERNO=");
        builder.append(codigoLineaPedidoExterno);
        builder.append(", ID_TIPO_LINEA_PEDIDO=");
        builder.append(idTipoLineaPedido);
        builder.append(", PARTNUMBER=");
        builder.append(partNumber);
        builder.append(", NUMERO_UNIDADES=");
        builder.append(numeroUnidades);
        
        if (numeroUnidadesCapturadas != 0) {
            builder.append(", NUMERO_UNIDADES_CAPTURADAS=");
            builder.append(numeroUnidadesCapturadas);
        }
        
        if (numeroUnidadesDevueltas != 0) {
            builder.append(", NUMERO_UNIDADES_DEVUELTAS=");
            builder.append(numeroUnidadesDevueltas);
        }
        
        builder.append(", IMPORTE_UNITARIO=");
        builder.append(importeUnitario);
        builder.append(", IMPORTE_TOTAL=");
        builder.append(importeTotal);
        builder.append(", ID_ESTADO_DESCUENTO=");
        builder.append(idEstadoDescuento);

        if (idDetalleDescuento != null) {
            builder.append(", ID_DETALLE_DESCUENTO=");
            builder.append(idDetalleDescuento.toString());
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

        builder.append(Comunes.RETURN);
        builder.append("LineaDescuentoDTO");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"primaryKeyLineaPedido\": ");
        builder.append(idLineaPedido.toStringJSON());
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idDescuento\": ");
        builder.append(idDescuento);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"codigoLineaPedidoExterno\": ");
        builder.append(codigoLineaPedidoExterno);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idTipoLineaPedido\": ");
        builder.append(idTipoLineaPedido);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"partNumber\": ");
        builder.append(partNumber);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"numeroUnidades\": ");
        builder.append(numeroUnidades);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);
        
        if (numeroUnidadesCapturadas != 0) {
            builder.append("\t\"numeroUnidadesCapturadas\": ");
            builder.append(numeroUnidadesCapturadas);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }
        
        if (numeroUnidadesDevueltas != 0) {
            builder.append("\t\"numeroUnidadesDevueltas\": ");
            builder.append(numeroUnidadesDevueltas);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

        builder.append("\t\"importeUnitario\": ");
        builder.append(importeUnitario);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"importeTotal\": ");
        builder.append(importeTotal);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idEstadoDescuento\": ");
        builder.append(idEstadoDescuento);

        if (idDescuento != null) {
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
            builder.append("\t\"idDetalleDescuento\": ");
            builder.append(idDetalleDescuento.toStringJSON());
        }

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
