package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionMaestrosDao;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion de Hibernate de GestionMaestrosDao: nos permitira realizar todas las operaciones
 * relacionadas con el esquema de MAESTROS y sus tablas CADENA, LOCALIZACION, DIVISA, TIENDA.
 */
@Repository("gestionMaestrosDao")
@Transactional(propagation = Propagation.MANDATORY)
public class GestionMaestrosDaoImpl implements GestionMaestrosDao {

    /** The Constant LOG. */
    private final Logger LOGGER = LoggerFactory.getLogger(GestionMaestrosDaoImpl.class);

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
     * ACCIONES SOBRE MAESTROS.TIENDA
     * 
     ******************************************************************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public Integer obtenerIdCadena(Integer idTienda) {
        LOGGER.trace("GestionMaestrosDaoImpl.obtenerIdCadena");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_CADENA                                                                                                         ");
        sql.append("    FROM MAESTROS.TIENDA                                                                                                 ");
        sql.append("    WHERE ID_TIENDA = ?                                                                                                  ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Short idCadena = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setInt(1, idTienda);
            rs = ps.executeQuery();

            while (rs.next()) {
                idCadena = rs.getShort("ID_CADENA");
            }
        } catch (SQLException e) {
            throw new HibernateException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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

        return idCadena.intValue();
    }
    
    /******************************************************************************************************************************
     * 
     * ACCIONES SOBRE MAESTROS.DIVISA
     * 
     ******************************************************************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public Short obtenerIdDivisa(String divisa) {
        LOGGER.trace("GestionMaestrosDaoImpl.obtenerIdDivisa");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_DIVISA                                                                                                         ");
        sql.append("    FROM MAESTROS.DIVISA                                                                                                 ");
        sql.append("    WHERE SIMBOLO = ?                                                                                                    ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Short idDivisa = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, divisa);
            rs = ps.executeQuery();

            while (rs.next()) {
                idDivisa = rs.getShort("ID_DIVISA");
            }
        } catch (SQLException e) {
            throw new HibernateException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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

        return idDivisa;
    }
    
    /******************************************************************************************************************************
     * 
     * ACCIONES SOBRE MAESTROS.PAIS
     * 
     ******************************************************************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public Short obtenerIdPais(String codPais) {
        LOGGER.trace("GestionMaestrosDaoImpl.obtenerIdDivisa");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_PAIS                                                                                                           ");
        sql.append("    FROM MAESTROS.PAIS                                                                                                   ");
        sql.append("    WHERE PAIS_ISO = ?                                                                                                   ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Short idPais = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, codPais);
            rs = ps.executeQuery();

            while (rs.next()) {
                idPais = rs.getShort("ID_PAIS");
            }
        } catch (SQLException e) {
            throw new HibernateException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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

        return idPais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public String obtenerCodPais(Short idPais) {
        LOGGER.trace("GestionMaestrosDaoImpl.obtenerCodPais");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT PAIS_ISO                                                                                                          ");
        sql.append("    FROM MAESTROS.PAIS                                                                                                   ");
        sql.append("    WHERE ID_PAIS = ?                                                                                                    ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String codPais = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, idPais);
            rs = ps.executeQuery();

            while (rs.next()) {
                codPais = rs.getString("PAIS_ISO");
            }
        } catch (SQLException e) {
            throw new HibernateException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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

        return codPais;
    }
    
}
