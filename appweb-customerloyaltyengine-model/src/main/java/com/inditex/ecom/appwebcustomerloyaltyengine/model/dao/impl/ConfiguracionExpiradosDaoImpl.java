package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Cadenas;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.Errores;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.ConfiguracionExpiradosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionMaestrosDao;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion de Hibernate de ConfiguracionExpiradosDao: nos permitira realizar todas las operaciones
 * relacionadas con la expiracion de todos los descuentos, que aplicara a las tablas: DETALLE_DESCUENTO, LINEA_DESCUENTO, DESCUENTO .
 */
@Repository("configuracionExpiradosDao")
@Transactional(propagation = Propagation.MANDATORY)
public class ConfiguracionExpiradosDaoImpl implements ConfiguracionExpiradosDao {

    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;
    
    /** The gestion maestros dao. */
    @Autowired
    private GestionMaestrosDao gestionMaestrosDao;

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
     * ACCIONES SOBRE EXPIRADOS
     * 
     * ****************************************************************************************************************************.
     */

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public void actualizarDescuento(Short estadoDescuentoBuscar, Cadenas cadenas, Short estadoDescuentoActualizar) throws TimeoutException {
        StringBuilder sql = new StringBuilder();
        
        sql.append("UPDATE DESCUENTO SET ID_ESTADO_DESCUENTO = ?                                                                              ");
        sql.append("       WHERE ID_ESTADO_DESCUENTO = ? AND ID_PAIS = ? AND FECHA_HORA_VALIDEZ_DESCUENTO < ?                                 ");
        // Bloqueamos las filas que se van a actualizar
        sql.append("WITH RS                                                                                                                   ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, estadoDescuentoActualizar);
            ps.setShort(2, estadoDescuentoBuscar);
            ps.setShort(3, gestionMaestrosDao.obtenerIdPais(cadenas.getCodPais()));
            ps.setTimestamp(4, new Timestamp((Calendar.getInstance()).getTimeInMillis()));

            ps.executeUpdate();
        } catch (Exception e) {
            if (e instanceof ConcurrencyFailureException) {
                throw new TimeoutException(Errores.CONEXION_BLOQUEADA, e);
            } else {
                throw new HibernateException(e);
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new HibernateException(e);
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public void actualizarLineaDescuento(Short idEstadoDescuento) throws TimeoutException {
        StringBuilder sql = new StringBuilder();
        
        sql.append("UPDATE LINEA_DESCUENTO SET ID_ESTADO_DESCUENTO = ?                                                                        ");
        sql.append("       WHERE ID_DESCUENTO = (SELECT ID_DESCUENTO FROM DESCUENTO WHERE ID_ESTADO_DESCUENTO = ?)                            ");
        // Bloqueamos las filas que se van a actualizar
        sql.append("WITH RS                                                                                                                   ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, idEstadoDescuento);
            ps.setShort(2, idEstadoDescuento);

            ps.executeUpdate();
        } catch (Exception e) {
            if (e instanceof ConcurrencyFailureException) {
                throw new TimeoutException(Errores.CONEXION_BLOQUEADA, e);
            } else {
                throw new HibernateException(e);
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new HibernateException(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void eliminarDescuentos(Short idEstadoDescuento) {
        Connection connection = null;
        PreparedStatement ps = null;
        
        try {
        	StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM LINEA_DESCUENTO                                                                                               ");
            sql.append("         WHERE ID_ESTADO_DESCUENTO = ?                                                                                    ");
            // Bloqueamos las filas que se van a actualizar
            sql.append("WITH RS                                                                                                                   ");
            
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, idEstadoDescuento);
            ps.executeUpdate();
            
            sql = new StringBuilder();
            sql.append("DELETE FROM DESCUENTO WHERE ID_ESTADO_DESCUENTO = ?                                                                       ");
            // Bloqueamos las filas que se van a actualizar
            sql.append("WITH RS                                                                                                                   ");
            
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, idEstadoDescuento);
            ps.executeUpdate();
            
            sql = new StringBuilder();
			sql.append("DELETE FROM DETALLE_DESCUENTO dd                                                                                           ");
			sql.append("         WHERE NOT EXISTS                                                                                                  ");
			sql.append("         (SELECT ID_DESCUENTO, ID_INSTALACION FROM DESCUENTO d WHERE d.ID_DETALLE_DESCUENTO = dd.ID_DETALLE_DESCUENTO)     ");
			sql.append("         AND NOT EXISTS                                                                                                    ");
			sql.append("         (SELECT ID_LINEA_DESCUENTO, ID_INSTALACION FROM LINEA_DESCUENTO ld                                                ");
			sql.append("             WHERE ld.ID_DETALLE_DESCUENTO = dd.ID_DETALLE_DESCUENTO)                                                      ");
			// Bloqueamos las filas que se van a actualizar
	        sql.append("WITH RS                                                                                                                    ");
	        
			ps = connection.prepareStatement(sql.toString());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new HibernateException(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new HibernateException(e);
			}
		}
    }

}
