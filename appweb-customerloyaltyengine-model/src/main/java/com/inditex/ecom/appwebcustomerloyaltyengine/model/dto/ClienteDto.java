package com.inditex.ecom.appwebcustomerloyaltyengine.model.dto;

import java.sql.Timestamp;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;

/**
 * The Class ClienteDto.
 */
public class ClienteDto extends BaseObject {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The primary key cliente. */
    private PrimaryKeyClienteDto idCliente = new PrimaryKeyClienteDto();

    /** The id cliente ecommerce. */
    private String codClienteWalletMarca;

    /** The id cadena. */
    private Short idCadena;

    /** The id pais. */
    private Short idPais;

    /** The id estado cliente. */
    private Short idEstadoCliente;

    /** The id tipo cliente. */
    private Short idTipoCliente;

    /** The fecha hora alta. */
    private Timestamp fechaHoraAlta;

    /** The fecha hora baja. */
    private Timestamp fechaHoraBaja;

    /** The sis marca tiempo. */
    private Long sisMarcaTiempo;

    /**
     * Instantiates a new cliente dto.
     */
    public ClienteDto() {
    }

    /**
     * Instantiates a new cliente dto.
     * 
     * @param primaryKeyCliente
     *            the primary key cliente
     */
    public ClienteDto(PrimaryKeyClienteDto primaryKeyCliente) {
        this.idCliente = primaryKeyCliente;
    }

    /**
     * Instantiates a new cliente dto.
     * 
     * @param primaryKeyCliente
     *            the primary key cliente
     * @param idEstadoCliente
     *            the id estado cliente
     * @param idTipoCliente
     *            the id tipo cliente
     * @param fechaHoraAlta
     *            the fecha hora alta
     */
    public ClienteDto(PrimaryKeyClienteDto primaryKeyCliente, Short idEstadoCliente, Short idTipoCliente, Timestamp fechaHoraAlta) {
        this.idCliente = primaryKeyCliente;
        this.idEstadoCliente = idEstadoCliente;
        this.idTipoCliente = idTipoCliente;
        this.fechaHoraAlta = fechaHoraAlta;
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
     * Gets the cod cliente wallet marca.
     * 
     * @return the cod cliente wallet marca
     */
    public String getCodClienteWalletMarca() {
        return codClienteWalletMarca;
    }

    /**
     * Sets the cod cliente wallet marca.
     * 
     * @param codClienteWalletMarca
     *            the new cod cliente wallet marca
     */
    public void setCodClienteWalletMarca(String codClienteWalletMarca) {
        this.codClienteWalletMarca = codClienteWalletMarca;
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
     * Gets the id estado cliente.
     * 
     * @return the id estado cliente
     */
    public Short getIdEstadoCliente() {
        return idEstadoCliente;
    }

    /**
     * Sets the id estado cliente.
     * 
     * @param idEstadoCliente
     *            the new id estado cliente
     */
    public void setIdEstadoCliente(Short idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    /**
     * Gets the id tipo cliente.
     * 
     * @return the id tipo cliente
     */
    public Short getIdTipoCliente() {
        return idTipoCliente;
    }

    /**
     * Sets the id tipo cliente.
     * 
     * @param idTipoCliente
     *            the new id tipo cliente
     */
    public void setIdTipoCliente(Short idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    /**
     * Gets the fecha hora alta.
     * 
     * @return the fecha hora alta
     */
    public Timestamp getFechaHoraAlta() {
        return fechaHoraAlta;
    }

    /**
     * Sets the fecha hora alta.
     * 
     * @param fechaHoraAlta
     *            the new fecha hora alta
     */
    public void setFechaHoraAlta(Timestamp fechaHoraAlta) {
        this.fechaHoraAlta = fechaHoraAlta;
    }

    /**
     * Gets the fecha hora baja.
     * 
     * @return the fecha hora baja
     */
    public Timestamp getFechaHoraBaja() {
        return fechaHoraBaja;
    }

    /**
     * Sets the fecha hora baja.
     * 
     * @param fechaHoraBaja
     *            the new fecha hora baja
     */
    public void setFechaHoraBaja(Timestamp fechaHoraBaja) {
        this.fechaHoraBaja = fechaHoraBaja;
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
        result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
        ClienteDto other = (ClienteDto) obj;
        if (idCliente == null) {
            if (other.idCliente != null) {
                return false;
            }
        } else if (!idCliente.equals(other.idCliente)) {
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

        builder.append("[PRIMARY_KEY_CLIENTE=");
        builder.append(idCliente.toString());

        if (codClienteWalletMarca != null) {
            builder.append(", COD_CLIENTE_WALLET_MARCA=");
            builder.append(codClienteWalletMarca);
        }

        if (idCadena != null) {
            builder.append(", ID_CADENA=");
            builder.append(idCadena);
        }

        if (idPais != null) {
            builder.append(", ID_PAIS=");
            builder.append(idPais);
        }

        builder.append(", ID_ESTADO_CLIENTE=");
        builder.append(idEstadoCliente);
        builder.append(", ID_TIPO_CLIENTE=");
        builder.append(idTipoCliente);
        builder.append(", FECHA_HORA_ALTA=");
        builder.append(fechaHoraAlta);

        if (fechaHoraBaja != null) {
            builder.append(", FECHA_HORA_BAJA=");
            builder.append(fechaHoraBaja);
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
        builder.append("ClienteDTO");
        builder.append(Comunes.RETURN);
        builder.append(Comunes.LEFT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        builder.append("\t\"primaryKeyCliente\": ");
        builder.append(idCliente.toStringJSON());
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        if (codClienteWalletMarca != null) {
            builder.append("\t\"codClienteWalletMarca\": ");
            builder.append(codClienteWalletMarca);
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
        }

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

        builder.append("\t\"idEstadoCliente\": ");
        builder.append(idEstadoCliente);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"idTipoCliente\": ");
        builder.append(idTipoCliente);
        builder.append(Comunes.COMMA);
        builder.append(Comunes.RETURN);

        builder.append("\t\"fechaHoraAlta\": ");
        builder.append(fechaHoraAlta);

        if (fechaHoraBaja != null) {
            builder.append(Comunes.COMMA);
            builder.append(Comunes.RETURN);
            builder.append("\t\"fechaHoraBaja\": ");
            builder.append(fechaHoraBaja);
        }

        builder.append(Comunes.RETURN);
        builder.append(Comunes.RIGHT_CURLY_BRACKETS);
        builder.append(Comunes.RETURN);

        return builder.toString();
    }

}
