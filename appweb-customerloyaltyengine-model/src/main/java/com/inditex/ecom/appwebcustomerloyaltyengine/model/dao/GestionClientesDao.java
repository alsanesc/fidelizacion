package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao;

import java.util.Calendar;
import java.util.List;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;

/**
 * <b>Descripcion:</b> Esta Interface determina la declaracion de Hibernate de GestionClientesDao: definira todas las operaciones relacionadas con las
 * tablas de CLIENTE.
 */
public interface GestionClientesDao {

    /**
     * Actualizar cliente.
     * 
     * @param cliente
     *            the cliente
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarCliente(ClienteDto cliente) throws TimeoutException;

    /**
     * Alta cliente.
     * 
     * @param datosCliente
     *            the datos cliente
     * @param fechaHoraAlta
     *            the fecha hora alta
     * @param tarjeta
     *            the tarjeta
     */
    void altaCliente(ClienteDto datosCliente, Calendar fechaHoraAlta, RecursoDto tarjeta);

    /**
     * Baja cliente.
     * 
     * @param clienteDto
     *            the cliente dto
     * @throws TimeoutException
     *             the timeout exception
     */
    void bajaCliente(ClienteDto clienteDto) throws TimeoutException;

    /**
     * Verificar cliente.
     * 
     * @param cadena
     *            the cadena
     * @param pais
     *            the pais
     * @param idExterno
     *            the id externo
     * @return the cliente dto
     */
    ClienteDto verificarCliente(Integer cadena, String pais, String idExterno);

    /**
     * Obtener cliente.
     * 
     * @param cliente
     *            the cliente
     * @return the cliente dto
     */
    ClienteDto obtenerCliente(PrimaryKeyClienteDto cliente);

    /**
     * Obtener cliente wallet.
     * 
     * @param idCliente
     *            the id cliente
     * @return the cliente dto
     */
    ClienteDto obtenerClienteWallet(String idCliente);

    /**
     * Actualizar cliente.
     * 
     * @param clienteDto
     *            the cliente dto
     * @param fechaHoraBaja
     *            the fecha hora baja
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarCliente(ClienteDto clienteDto, Calendar fechaHoraBaja) throws TimeoutException;

    /**
     * Actualizar tarjeta cliente.
     * 
     * @param recursoList
     *            the recurso list
     * @param fechaHoraBaja
     *            the fecha hora baja
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarRecursoCliente(List<RecursoDto> recursoList, Calendar fechaHoraBaja) throws TimeoutException;

}
