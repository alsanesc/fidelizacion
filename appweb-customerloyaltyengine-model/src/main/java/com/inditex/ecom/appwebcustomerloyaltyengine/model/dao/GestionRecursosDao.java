package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;

/**
 * <b>Descripcion:</b> Esta Interface determina la declaracion de Hibernate de GestionRecursosDao: definira todas las operaciones relacionadas con las
 * tablas de RECURSO.
 */
public interface GestionRecursosDao {

    /**
     * Obtener recursos.
     * 
     * @param cliente
     *            the id cliente
     * @return the list
     */
    List<RecursoDto> obtenerRecursosEstado(PrimaryKeyClienteDto cliente);

    /**
     * Obtener recursos tipo.
     * 
     * @param cliente
     *            the cliente
     * @param idTipo
     *            the id tipo
     * @return the list
     */
    List<RecursoDto> obtenerRecursosTipo(PrimaryKeyClienteDto cliente, Integer idTipo);

    /**
     * Obtener recursos adecuados.
     * 
     * @param cliente
     *            the cliente
     * @param idCadena
     *            the id cadena
     * @param idPais
     *            the id pais
     * @return the list
     */
    List<RecursoDto> obtenerRecursosAdecuados(PrimaryKeyClienteDto cliente, Integer idCadena, Short idPais);

    /**
     * Obtener recursos.
     * 
     * @param cliente
     *            the cliente
     * @return the list
     */
    List<RecursoDto> obtenerRecursos(PrimaryKeyClienteDto cliente);

    /**
     * Actualizar saldos recurso.
     * 
     * @param recurso
     *            the recurso
     * @param nuevoImporte
     *            the nuevo importe
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarValorDisponibleRecurso(RecursoDto recurso, BigDecimal nuevoImporte) throws TimeoutException;

    /**
     * Actualizar valor recurso.
     * 
     * @param recurso
     *            the recurso
     * @param valorRecurso
     *            the valor recurso
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarValorRecurso(RecursoDto recurso, BigDecimal valorRecurso) throws TimeoutException;

    /**
     * Actualizar valores recurso.
     * 
     * @param recurso
     *            the recurso
     * @param valorRecurso
     *            the valor recurso
     * @param valorDisponibleRecurso
     *            the valor disponible recurso
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarValoresRecurso(RecursoDto recurso, BigDecimal valorRecurso, BigDecimal valorDisponibleRecurso) throws TimeoutException;

    /**
     * Dar baja tarjeta.
     * 
     * @param recursoList
     *            the recurso list
     * @param fechaHoraBaja
     *            the fecha hora baja
     * @throws TimeoutException
     *             the timeout exception
     */
    void darBajaRecurso(List<RecursoDto> recursoList, Calendar fechaHoraBaja) throws TimeoutException;

    /**
     * Obtener tipo recurso.
     * 
     * @param recurso
     *            the recurso
     * @return the integer
     */
    Integer obtenerTipoRecurso(String recurso);

}
