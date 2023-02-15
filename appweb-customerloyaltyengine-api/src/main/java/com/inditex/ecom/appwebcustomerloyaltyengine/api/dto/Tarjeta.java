package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import java.math.BigDecimal;
import java.util.List;

import net.sf.oval.constraint.NotNull;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class TarjetaDto.
 */
public class Tarjeta extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id tarjeta. */
    @NotNull
    private String idTarjeta;

    /** The tipo tarjeta. */
    @NotNull
    private String tipoTarjeta;

    /** The saldo. */
    private BigDecimal saldo;

    /** The movimientos. */
    private List<OperacionTarjeta> movimientos;

    /**
     * Instantiates a new tarjeta dto.
     */
    public Tarjeta() {
    }

    /**
     * Instantiates a new tarjeta.
     * 
     * @param idTarjeta
     *            the id tarjeta
     * @param tipoTarjeta
     *            the tipo tarjeta
     */
    public Tarjeta(String idTarjeta, String tipoTarjeta) {
        this.idTarjeta = idTarjeta;
        this.tipoTarjeta = tipoTarjeta;
    }

    /**
     * Gets the id tarjeta.
     * 
     * @return the id tarjeta
     */
    public String getIdTarjeta() {
        return idTarjeta;
    }

    /**
     * Sets the id tarjeta.
     * 
     * @param idTarjeta
     *            the new id tarjeta
     */
    public void setIdTarjeta(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    /**
     * Gets the tipo tarjeta.
     * 
     * @return the tipo tarjeta
     */
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * Sets the tipo tarjeta.
     * 
     * @param tipoTarjeta
     *            the new tipo tarjeta
     */
    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    /**
     * Gets the saldo.
     * 
     * @return the saldo
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * Sets the saldo.
     * 
     * @param saldo
     *            the new saldo
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    /**
     * Gets the movimientos.
     * 
     * @return the movimientos
     */
    public List<OperacionTarjeta> getMovimientos() {
        return movimientos;
    }

    /**
     * Sets the movimientos.
     * 
     * @param movimientos
     *            the new movimientos
     */
    public void setMovimientos(List<OperacionTarjeta> movimientos) {
        this.movimientos = movimientos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idTarjeta == null) ? 0 : idTarjeta.hashCode());
        result = prime * result + ((tipoTarjeta == null) ? 0 : tipoTarjeta.hashCode());
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

        Tarjeta other = (Tarjeta) obj;
        if (idTarjeta == null) {
            if (other.idTarjeta != null) {
                return false;
            }
        } else if (!idTarjeta.equals(other.idTarjeta)) {
            return false;
        }

        if (tipoTarjeta == null) {
            if (other.tipoTarjeta != null) {
                return false;
            }
        } else if (!tipoTarjeta.equals(other.tipoTarjeta)) {
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

        builder.append("[ID_TARJETA=");
        builder.append(idTarjeta);

        builder.append(", TIPO_TARJETA=");
        builder.append(tipoTarjeta);

        if (saldo != null) {
            builder.append(", SALDO=");
            builder.append(saldo);
        }

        builder.append(", MOVIMIENTOS={");
        if (movimientos != null) {
            for (OperacionTarjeta operacionTarjeta : movimientos) {
                builder.append(operacionTarjeta.toString());
            }
        }
        builder.append("}");
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

        builder.append("Tarjeta");
        builder.append(saltoDeLinea);
        builder.append("{");
        builder.append(saltoDeLinea);

        builder.append("\t\"idTarjeta\": ");
        builder.append(idTarjeta);
        builder.append(separator);
        builder.append(saltoDeLinea);

        builder.append("\t\"tipoTarjeta\": ");
        builder.append(tipoTarjeta);

        if (saldo != null) {
            builder.append(separator);
            builder.append(saltoDeLinea);
            builder.append("\t\"saldo\": ");
            builder.append(saldo);
        }

        builder.append(separator);
        builder.append(saltoDeLinea);
        builder.append("\t\"movimientos\": [");
        builder.append(saltoDeLinea);

        if (movimientos != null) {
            for (int i = 0; i < movimientos.size(); i++) {
                builder.append(movimientos.get(i).toStringJSON());
                if (i != movimientos.size() - 1) {
                    builder.append(separator);
                    builder.append(saltoDeLinea);
                }
            }
        }

        builder.append(saltoDeLinea);
        builder.append("\t]");
        builder.append(saltoDeLinea);
        builder.append("}");
        builder.append(saltoDeLinea);

        return builder.toString();
    }

}
