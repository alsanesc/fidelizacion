package com.inditex.ecom.appwebcustomerloyaltyengine.api.cliente;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.AltaCliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Cliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.BadRequestException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;

/**
 * The Interface GestionClientes.
 */
public interface GestionClientes {

    /**
     * Alta cliente.
     * 
     * @param version
     *            the version
     * @param datosCliente
     *            the datos cliente
     * @return the alta cliente
     * @throws BadRequestException
     *             the bad request exception
     */
    AltaCliente altaCliente(final String version, final Cliente datosCliente) throws BadRequestException;

    /**
     * Baja cliente.
     * 
     * @param version
     *            the version
     * @param idCliente
     *            the id cliente
     * @return the alta cliente
     * @throws TimeoutException
     *             the timeout exception
     */
    AltaCliente bajaCliente(final String version, final String idCliente) throws TimeoutException;

    /**
     * Consulta saldo movimientos.
     * 
     * @param version
     *            the version
     * @param idCliente
     *            the id cliente
     * @return the cliente
     */
    Cliente consultaSaldoMovimientos(final String version, final String idCliente);

    /**
     * Recargar puntos.
     * 
     * @param version
     *            the version
     * @param idCliente
     *            the id cliente
     * @param idTipoRecurso
     *            the id tipo recurso
     * @param recurso
     *            the recurso
     * @param puntos
     *            the puntos
     * @param maxPuntos
     *            the max puntos
     * @param numPuntos
     *            the num puntos
     * @return the alta cliente
     */
    AltaCliente recargarPuntos(final String version, final String idCliente, final Integer idTipoRecurso, final Integer recurso,
            final Integer puntos, final Integer maxPuntos, final Integer numPuntos)throws TimeoutException;

}
