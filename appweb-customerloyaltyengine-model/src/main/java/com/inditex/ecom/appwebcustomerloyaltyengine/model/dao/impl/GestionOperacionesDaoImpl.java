package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionOperacionesDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion de Hibernate de GestionOperacionesDao: nos permitira realizar todas las operaciones
 * relacionadas con las tablas de OPERACIONES_TARJETA (TIPO_OPERACION_TARJETA, OPERACION_TARJETA).
 */
@Repository("gestionOperacionesDao")
@Transactional(propagation = Propagation.MANDATORY)
public class GestionOperacionesDaoImpl implements GestionOperacionesDao {

    /** The session. */
    private Session session;

    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;

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
     * ACCIONES SOBRE OPERACION TARJETA
     * 
     * ****************************************************************************************************************************.
     */

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<OperacionRecursoDto> obtenerOperacionRecurso(List<Long> operacionDescuentoList) {
        session = sessionFactory.getCurrentSession();

        Criteria crit = session.createCriteria(OperacionRecursoDto.class);
        crit.add(Restrictions.in("idOperacionDescuento.idOperacionDescuento", operacionDescuentoList));

        return crit.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<OperacionRecursoDto> obtenerOperacionRecurso(RecursoDto recurso) {
        session = sessionFactory.getCurrentSession();

        Criteria crit = session.createCriteria(OperacionRecursoDto.class);
        crit.add(Restrictions.eq("idRecurso", recurso.getIdRecurso()));
        crit.addOrder(Order.desc("fechaHoraOperacion"));

        return crit.list();
    }

}
