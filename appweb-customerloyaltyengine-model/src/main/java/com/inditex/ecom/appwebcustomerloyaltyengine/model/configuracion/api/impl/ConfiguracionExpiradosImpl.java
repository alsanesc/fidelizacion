package com.inditex.ecom.appwebcustomerloyaltyengine.model.configuracion.api.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Cadenas;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.EstadoDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.ConfiguracionCadenasDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.ConfiguracionExpiradosDao;

/**
 * Implementacion de {@link ConfiguracionExpirados}.
 */
@Scope("singleton")
public class ConfiguracionExpiradosImpl implements Runnable {

    /** The configuracion expirados dao. */
    @Autowired
    private ConfiguracionExpiradosDao configuracionExpiradosDao;

    /** The configuracion cadenas dao. */
    @Autowired
    private ConfiguracionCadenasDao configuracionCadenasDao;
    
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
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void run() {
        try {
            List<Cadenas> cadenasList = configuracionCadenasDao.obtenerCadenas();
            
            for (Cadenas cadenas : cadenasList) {
                // Actualizamos el estado de los DESCUENTOS a EXPIRADO
                configuracionExpiradosDao.actualizarDescuento(EstadoDescuento.PENDIENTE.getValue(), cadenas, EstadoDescuento.EXPIRADO.getValue());
                
                // Actualizamos el estado de los LINEA_DESCUENTO a EXPIRADO
                configuracionExpiradosDao.actualizarLineaDescuento(EstadoDescuento.EXPIRADO.getValue());
            }
            
            // Hacemos commit de la transaccion para evitar acaparar recursos durante la espera
            Session session = sessionFactory.getCurrentSession();
            Transaction existingTransaction = session.getTransaction();
            existingTransaction.commit();
            
            // INICIO DE LA TRANSACCION T2 DE COMPONENTE DE PAGOS
            Transaction newTransaction = session.beginTransaction();
            // Now need to update Spring transaction infrastructure with new Hibernate transaction.
            // The logic for this is based on SessionFactoryUtils.doGetSession
            SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
            sessionHolder.setTransaction(newTransaction);
            
            // Eliminamos entradas de DESCUENTOS expirados
            configuracionExpiradosDao.eliminarDescuentos(EstadoDescuento.EXPIRADO.getValue());
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
