package com.inditex.ecom.appwebcustomerloyaltyengine.model.sequence.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.DBSchema;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.sequence.dao.SequencesDao;

/**
 * The Class SequencesDaoImpl.
 */
@Repository("sequencesDao")
@Transactional(propagation = Propagation.MANDATORY)
public class SequencesDaoImpl implements SequencesDao {
    
    /** The Constant LOG. */
    private final Logger LOGGER = LoggerFactory.getLogger(SequencesDaoImpl.class);

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
    
    /******************************************************************************************************************************
     * 
     * METODOS PARA LA OBTENCION DE SECUENCIAS
     * 
     ******************************************************************************************************************************/

    @Override
    public Long getNextValue(String sequence) {
        LOGGER.trace("SequencesDaoImpl.getNextValuePedido");

        String sql = String.format(Mensajes.SEQUENCE, DBSchema.DB_MAIN_SCHEMA, sequence, DBSchema.DB_SYSTEM_SCHEMA); 
            
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createSQLQuery(sql.toString());
        Long key = new Long(query.uniqueResult().toString());

        return key;
    }

}
