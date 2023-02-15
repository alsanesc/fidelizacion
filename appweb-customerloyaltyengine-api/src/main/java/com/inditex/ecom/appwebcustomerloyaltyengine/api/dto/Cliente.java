package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import java.util.List;

import net.sf.oval.constraint.NotNull;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class ClienteDto.
 */
public class Cliente extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id cadena. */
    @NotNull
    private Integer idCadena;

    /** The cod pais. */
    @NotNull
    private String codPais;

    /** The id cliente ecommerce. */
    @NotNull
    private String idClienteEcommerce;

    /** The id cliente descuentos. */
    private String idClienteDescuentos;

    /** The tipo tarjeta inicial. */
    private String tipoTarjetaInicial;

    /** The saldo puntos inicial. */
    private Integer saldoPuntosInicial;

    /** The tarjetas. */
    private List<Tarjeta> tarjetas;

    /** The activo. */
    private Boolean activo;

    /** The fecha alta. */
    private String fechaAlta;

    /** The fecha baja. */
    private String fechaBaja;

    /**
     * Instantiates a new cliente dto.
     */
    public Cliente() {
    }

    /**
     * Gets the id cadena.
     * 
     * @return the id cadena
     */
    public Integer getIdCadena() {
        return idCadena;
    }

    /**
     * Sets the id cadena.
     * 
     * @param idCadena
     *            the new id cadena
     */
    public void setIdCadena(Integer idCadena) {
        this.idCadena = idCadena;
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
     * Gets the tipo tarjeta inicial.
     * 
     * @return the tipo tarjeta inicial
     */
    public String getTipoTarjetaInicial() {
        return tipoTarjetaInicial;
    }

    /**
     * Sets the tipo tarjeta inicial.
     * 
     * @param tipoTarjetaInicial
     *            the new tipo tarjeta inicial
     */
    public void setTipoTarjetaInicial(String tipoTarjetaInicial) {
        this.tipoTarjetaInicial = tipoTarjetaInicial;
    }

    /**
     * Gets the saldo puntos inicial.
     * 
     * @return the saldo puntos inicial
     */
    public Integer getSaldoPuntosInicial() {
        return saldoPuntosInicial;
    }

    /**
     * Sets the saldo puntos inicial.
     * 
     * @param saldoPuntosInicial
     *            the new saldo puntos inicial
     */
    public void setSaldoPuntosInicial(Integer saldoPuntosInicial) {
        this.saldoPuntosInicial = saldoPuntosInicial;
    }

    /**
     * Gets the tarjetas.
     * 
     * @return the tarjetas
     */
    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    /**
     * Sets the tarjetas.
     * 
     * @param tarjetas
     *            the new tarjetas
     */
    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    /**
     * Gets the activo.
     * 
     * @return the activo
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * Sets the activo.
     * 
     * @param activo
     *            the new activo
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * Gets the fecha alta.
     * 
     * @return the fecha alta
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the fecha alta.
     * 
     * @param fechaAlta
     *            the new fecha alta
     */
    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Gets the fecha baja.
     * 
     * @return the fecha baja
     */
    public String getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Sets the fecha baja.
     * 
     * @param fechaBaja
     *            the new fecha baja
     */
    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codPais == null) ? 0 : codPais.hashCode());
        result = prime * result + ((idCadena == null) ? 0 : idCadena.hashCode());
        result = prime * result + ((idClienteEcommerce == null) ? 0 : idClienteEcommerce.hashCode());
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

        Cliente other = (Cliente) obj;
        if (codPais == null) {
            if (other.codPais != null) {
                return false;
            }
        } else if (!codPais.equals(other.codPais)) {
            return false;
        }

        if (idCadena == null) {
            if (other.idCadena != null) {
                return false;
            }
        } else if (!idCadena.equals(other.idCadena)) {
            return false;
        }
        if (idClienteEcommerce == null) {
            if (other.idClienteEcommerce != null) {
                return false;
            }
        } else if (!idClienteEcommerce.equals(other.idClienteEcommerce)) {
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

        builder.append("[ID_CADENA=");
        builder.append(idCadena);
        builder.append(", COD_PAIS=");
        builder.append(codPais);
        builder.append(", ID_CLIENTE_ECOMMERCE=");
        builder.append(idClienteEcommerce);

        if (idClienteDescuentos != null) {
            builder.append(", ID_CLIENTE_DESCUENTOS=");
            builder.append(idClienteDescuentos);
        }

        if (tipoTarjetaInicial != null) {
            builder.append(", TIPO_TARJETA_INICIAL=");
            builder.append(tipoTarjetaInicial);
        }

        if (saldoPuntosInicial != null) {
            builder.append(", SALDO_PUNTOS_INICIAL=");
            builder.append(saldoPuntosInicial);
        }

        builder.append(", TARJETAS={");
        if (tarjetas != null) {
            for (Tarjeta tarjeta : tarjetas) {
                builder.append(tarjeta.toString());
            }
        }
        builder.append("}");

        if (activo != null) {
            builder.append(", ACTIVO=");
            builder.append(activo);
        }

        if (fechaAlta != null) {
            builder.append(", FECHA_ALTA=");
            builder.append(fechaAlta);
        }

        if (fechaBaja != null) {
            builder.append(", FECHA_BAJA=");
            builder.append(fechaBaja);
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

        builder.append("Cliente");
        builder.append(saltoDeLinea);
        builder.append("{");
        builder.append(saltoDeLinea);

        builder.append("\t\"idCadena\": ");
        builder.append(idCadena);
        builder.append(separator);
        builder.append(saltoDeLinea);
        
        builder.append("\t\"codPaid\": ");
        builder.append(codPais);
        builder.append(separator);
        builder.append(saltoDeLinea);

        builder.append("\t\"idClienteEcommerce\": ");
        builder.append(idClienteEcommerce);

        if (idClienteDescuentos != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"idClienteDescuentos\": ");
            builder.append(idClienteDescuentos);
        }

        if (tipoTarjetaInicial != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"tipoTarjetaInicial\": ");
            builder.append(tipoTarjetaInicial);
        }

        if (saldoPuntosInicial != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"saldoPuntosInicial\": ");
            builder.append(saldoPuntosInicial);
        }

        builder.append(separator);
        builder.append(saltoDeLinea);
        builder.append("\t\"tarjetas\": [");
        if (tarjetas != null) {
            for (int i = 0; i < tarjetas.size(); i++) {
                builder.append(tarjetas.get(i).toStringJSON());
                if (i != tarjetas.size() - 1) {
                    builder.append(separator);
                    builder.append(saltoDeLinea);
                }
            }
        }
        builder.append(saltoDeLinea);
        builder.append("\t]");

        if (activo != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"activo\": ");
            builder.append(activo);
        }

        if (fechaAlta != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"fechaAlta\": ");
            builder.append(fechaAlta);
        }

        if (fechaBaja != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"fechaBaja\": ");
            builder.append(fechaBaja);
        }

        builder.append(saltoDeLinea);
        builder.append("}");
        builder.append(saltoDeLinea);

        return builder.toString();
    }

}
