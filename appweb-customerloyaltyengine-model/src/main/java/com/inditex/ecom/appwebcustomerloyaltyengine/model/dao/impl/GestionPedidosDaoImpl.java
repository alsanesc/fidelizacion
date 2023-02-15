package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.NumeroSerieException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.EstadoDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionClientesDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionDescuentosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionPedidosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionRecursosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DetalleDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyOperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion de Hibernate de GestionPedidosDao: nos permitira realizar todas las operaciones
 * relacionadas con PEDIDOS (CALCULAR_DESCUENTO, AUTORIZAR_DESCUENTO, CONSULTAR_DESCUENTO).
 * 
 */
@Repository("gestionPedidosDao")
@Transactional(propagation = Propagation.MANDATORY)
public class GestionPedidosDaoImpl implements GestionPedidosDao {

    /** The Constant LOG. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestionPedidosDaoImpl.class);

    /** The session. */
    private Session session;

    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;

    /** The gestion clientes dao. */
    @Autowired
    private GestionClientesDao gestionClientesDao;

    /** The gestion recursos dao. */
    @Autowired
    private GestionRecursosDao gestionRecursosDao;

    /** The gestion descuentos dao. */
    @Autowired
    private GestionDescuentosDao gestionDescuentosDao;

    /**
     * Sets the session factory.
     * 
     * @param sessionFactory
     *            the new session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * ****************************************************************************************************************************
     * 
     * ACCIONES SOBRE PEDIDOS
     * 
     * ****************************************************************************************************************************.
     */

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
    public void calcularDescuento(DescuentoDto descuento, List<DetalleDescuentoDto> detalleDescuentoList, List<LineaDescuentoDto> lineaDescuentoList) {
        LOGGER.trace("GestionPedidosDaoImpl.calcularDescuento");
        
        try {
            session = sessionFactory.getCurrentSession();

            for (DetalleDescuentoDto detalleDescuento : detalleDescuentoList) {
                session.persist(detalleDescuento);
            }

            DetalleDescuentoDto detalleDescuentoDto = detalleDescuentoList.get(detalleDescuentoList.size() - 1);
            descuento.setIdDetalleDescuento(detalleDescuentoDto.getIdDetalleDescuento());
            session.persist(descuento);

            for (LineaDescuentoDto lineaDescuento : lineaDescuentoList) {
                session.persist(lineaDescuento);
            }
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED, readOnly = false)
    public PrimaryKeyOperacionDescuentoDto autorizacionDescuento(OperacionDescuentoDto operDescuento, List<OperacionRecursoDto> operacionesRecursoList, ClienteDto cliente, DescuentoDto descuento, List<RecursoDto> recursoList, DetalleDescuentoDto detalleDescuento, String UUID) throws TimeoutException, NumeroSerieException {
        LOGGER.trace("GestionPedidosDaoImpl.autorizacionDescuento");

        LOGGER.trace(String.format("%s - Realizamos la persistencia del descuento %s, con el pedido %s.", UUID, descuento.getIdDescuento().getIdDescuento(), operDescuento.getCodigoOperacionExterno()));

        try {
            session = sessionFactory.getCurrentSession();

            // Actualizamos numero de serie de CLIENTE
            gestionClientesDao.actualizarCliente(cliente);

            // Actualizamos estado y numero de serie de DESCUENTO
            gestionDescuentosDao.actualizarDescuento(descuento, EstadoDescuento.AUTORIZADO.getValue());
            
            // Actualizamos el estado de las LINEAS_DESCUENTO
            gestionDescuentosDao.actualizarLineasDescuento(descuento, EstadoDescuento.AUTORIZADO.getValue());
            
            // Actualizamos saldos_disponibles en RECURSO
            for (RecursoDto recurso : recursoList) {
                gestionRecursosDao.actualizarValorDisponibleRecurso(recurso, recurso.getValorDisponibleRecurso().subtract(detalleDescuento.getConsumoPuntos()));
            }

            // Persistimos OPERACION_DESCUENTO
            session.persist(operDescuento);

            // Persistimos OPERACION_RECURSO
            for (OperacionRecursoDto operacionRecurso : operacionesRecursoList) {
                session.persist(operacionRecurso);
            }
        } catch (Exception e) {
            throw new NumeroSerieException(UUID);
        }

        return operDescuento.getIdOperacionDescuento();
    }

}
