package com.inditex.ecom.appwebcustomerloyaltyengine.model.api;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.AltaCliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Cliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;

/**
 * The Interface GestionClientesService.
 */
public interface GestionClientesService {

    /**
     * Alta.
     * 
     * @param datosCliente
     *            the datos cliente
     * @param UUID
     *            the uuid
     * @return the cliente dto
     */
    AltaCliente altaCliente(Cliente datosCliente, String UUID);

    /**
     * Baja cliente.
     * 
     * @param idCliente
     *            the id cliente
     * @return the cliente dto
     * @throws TimeoutException
     *             the timeout exception
     */
    Cliente bajaCliente(String idCliente) throws TimeoutException;

    /**
     * Movimientos.
     * 
     * @param idClient
     *            the id client
     * @param UUID
     *            the uuid
     * @return the cliente dto
     */
    Cliente movimientos(String idClient);

    /**
     * Obtener cliente wallet.
     * 
     * @param idCliente
     *            the id cliente
     * @return the cliente dto
     */
    ClienteDto obtenerClienteWallet(String idCliente);

    /**
     * Recargar puntos.
     * 
     * @param idTipo
     *            the id tipo
     * @param cliente
     *            the id cliente
     * @param puntos
     *            the puntos
     * @param maxPuntos
     *            the max puntos
     * @param numPuntos
     *            the num puntos
     * @return the alta cliente
     * @throws TimeoutException
     *             the timeout exception
     */
    AltaCliente recargarPuntos(Integer idTipo, ClienteDto cliente, Integer puntos, Integer maxPuntos, Integer numPuntos) throws TimeoutException;

}
