package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.NumeroSerieException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.EstadoRecurso;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.Errores;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionRecursosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion de Hibernate de GestionRecursosDao: nos permitira realizar todas las operaciones
 * relacionadas con las tablas de RECURSO (RECURSO, TIPO_RECURSO, ESTADO_RECURSO).
 */
@Repository("gestionRecursosDao")
@Transactional(propagation = Propagation.MANDATORY)
public class GestionRecursosDaoImpl implements GestionRecursosDao {

    /** The Constant LOG. */
    private final Logger LOGGER = LoggerFactory.getLogger(GestionRecursosDaoImpl.class);

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
     * ACCIONES SOBRE RECURSO
     * 
     * ****************************************************************************************************************************.
     */

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public void darBajaRecurso(List<RecursoDto> recursoList, Calendar fechaHoraBaja) throws TimeoutException {
        LOGGER.trace("GestionRecursosDaoImpl.darBajaRecurso");
        
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE RECURSO SET ID_ESTADO_RECURSO = ?, FECHA_HORA_BAJA = ?                                                        ");
        sql.append("       WHERE ID_RECURSO = ? AND ID_INSTALACION = ? AND ROW CHANGE TOKEN FOR RECURSO = ?                              ");
        sql.append("WITH RS                                                                                                              ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setInt(1, EstadoRecurso.INACTIVO.getValue());
            ps.setTimestamp(2, new Timestamp(fechaHoraBaja.getTimeInMillis()));

            for (RecursoDto recurso : recursoList) {
                ps.setLong(3, recurso.getIdRecurso().getIdRecurso());
                ps.setInt(4, recurso.getIdRecurso().getIdInstalacion());
                ps.setLong(5, recurso.getSisMarcaTiempo());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new TimeoutException(String.format(Errores.CONEXION_BLOQUEADA), e);
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
    public List<RecursoDto> obtenerRecursosEstado(PrimaryKeyClienteDto cliente) {
        LOGGER.trace("GestionRecursosDaoImpl.obtenerRecursosEstado");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r.ID_RECURSO, r.ID_INSTALACION, ID_TIPO_RECURSO, ID_CADENA, ID_PAIS, ID_ESTADO_RECURSO, ID_TIPO_VALOR_RECURSO,    ");
        sql.append("        VALOR_RECURSO, VALOR_DISPONIBLE_RECURSO, r.FECHA_HORA_ALTA, r.FECHA_HORA_BAJA,                                   ");
        sql.append("        ROW CHANGE TOKEN FOR r AS SIS_MARCA_TIEMPO                                                                       ");
        sql.append("    FROM RECURSO r                                                                                                       ");
        sql.append("    INNER JOIN RECURSO_CLIENTE rc ON rc.ID_RECURSO = r.ID_RECURSO                                                        ");
        sql.append("    WHERE ID_CLIENTE = ? AND ID_INSTALACION_CLIENTE = ? AND ID_ESTADO_RECURSO = ?                                        ");
        sql.append("WITH UR                                                                                                                  ");
        
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RecursoDto> recursoList = new ArrayList<RecursoDto>();

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, cliente.getIdCliente());
            ps.setInt(2, cliente.getIdInstalacion());
            ps.setShort(3, EstadoRecurso.ACTIVO.getValue());
            rs = ps.executeQuery();

            while (rs.next()) {
                RecursoDto recurso = new RecursoDto();

                recurso.setIdRecurso(new PrimaryKeyRecursoDto(rs.getLong("ID_RECURSO"), rs.getInt("ID_INSTALACION")));
                recurso.setIdTipoRecurso(rs.getInt("ID_TIPO_RECURSO"));

                if (rs.getShort("ID_CADENA") != 0) {
                    recurso.setIdCadena(rs.getShort("ID_CADENA"));
                }

                if (rs.getShort("ID_PAIS") != 0) {
                    recurso.setIdPais(rs.getShort("ID_PAIS"));
                }

                recurso.setIdEstadoRecurso(rs.getShort("ID_ESTADO_RECURSO"));
                recurso.setIdTipoValorRecurso(rs.getShort("ID_TIPO_VALOR_RECURSO"));

                if (rs.getBigDecimal("VALOR_RECURSO").compareTo(BigDecimal.ZERO) > 0) {
                    recurso.setValorRecurso(rs.getBigDecimal("VALOR_RECURSO"));
                }

                if (rs.getBigDecimal("VALOR_DISPONIBLE_RECURSO").compareTo(BigDecimal.ZERO) > 0) {
                    recurso.setValorDisponibleRecurso(rs.getBigDecimal("VALOR_DISPONIBLE_RECURSO"));
                }

                recurso.setFechaHoraAlta(rs.getTimestamp("FECHA_HORA_ALTA"));

                if (rs.getTime("FECHA_HORA_BAJA") != null) {
                    recurso.setFechaHoraBaja(rs.getTimestamp("FECHA_HORA_BAJA"));
                }

                recurso.setSisMarcaTiempo(rs.getLong("SIS_MARCA_TIEMPO"));

                recursoList.add(recurso);
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

        return recursoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public List<RecursoDto> obtenerRecursosTipo(PrimaryKeyClienteDto cliente, Integer idTipo) {
        LOGGER.trace("GestionRecursosDaoImpl.obtenerRecursosTipo");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r.ID_RECURSO, r.ID_INSTALACION, VALOR_DISPONIBLE_RECURSO, ROW CHANGE TOKEN FOR r AS SIS_MARCA_TIEMPO              ");
        sql.append("    FROM RECURSO r                                                                                                       ");
        sql.append("    INNER JOIN RECURSO_CLIENTE rc ON rc.ID_RECURSO = r.ID_RECURSO                                                        ");
        sql.append("    WHERE ID_CLIENTE IN ? AND ID_INSTALACION_CLIENTE = ? AND ID_TIPO_RECURSO = ?                                         ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RecursoDto> recursoList = new ArrayList<RecursoDto>();

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, cliente.getIdCliente());
            ps.setInt(2, cliente.getIdInstalacion());
            ps.setInt(3, idTipo);
            rs = ps.executeQuery();

            while (rs.next()) {
                RecursoDto recurso = new RecursoDto();

                recurso.setIdRecurso(new PrimaryKeyRecursoDto(rs.getLong("ID_RECURSO"), rs.getInt("ID_INSTALACION")));
                recurso.setValorDisponibleRecurso(rs.getBigDecimal("VALOR_DISPONIBLE_RECURSO"));
                recurso.setSisMarcaTiempo(rs.getLong("SIS_MARCA_TIEMPO"));

                recursoList.add(recurso);
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

        return recursoList;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public List<RecursoDto> obtenerRecursosAdecuados(PrimaryKeyClienteDto cliente, Integer idCadena, Short idPais) {
        LOGGER.trace("GestionRecursosDaoImpl.obtenerRecursosAdecuadas");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r.ID_RECURSO, r.ID_INSTALACION, VALOR_DISPONIBLE_RECURSO, ROW CHANGE TOKEN FOR r AS SIS_MARCA_TIEMPO              ");
        sql.append("    FROM RECURSO r                                                                                                       ");
        sql.append("    INNER JOIN RECURSO_CLIENTE rc ON rc.ID_RECURSO = r.ID_RECURSO                                                        ");
        sql.append("    WHERE rc.ID_CLIENTE = ? AND rc.ID_INSTALACION_CLIENTE = ? AND ID_CADENA = ? AND ID_PAIS = ?                          ");
        sql.append("        AND ID_ESTADO_RECURSO = ?                                                                                        ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RecursoDto> recursoList = new ArrayList<RecursoDto>();

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, cliente.getIdCliente());
            ps.setInt(2, cliente.getIdInstalacion());
            ps.setShort(3, idCadena.shortValue());
            ps.setShort(4, idPais);
            ps.setShort(5, EstadoRecurso.ACTIVO.getValue());
            rs = ps.executeQuery();

            while (rs.next()) {
                RecursoDto recurso = new RecursoDto();

                recurso.setIdRecurso(new PrimaryKeyRecursoDto(rs.getLong("ID_RECURSO"), rs.getInt("ID_INSTALACION")));
                recurso.setValorDisponibleRecurso(rs.getBigDecimal("VALOR_DISPONIBLE_RECURSO"));
                recurso.setSisMarcaTiempo(rs.getLong("SIS_MARCA_TIEMPO"));

                recursoList.add(recurso);
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
        
        return recursoList;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public List<RecursoDto> obtenerRecursos(PrimaryKeyClienteDto cliente) {
        LOGGER.trace("GestionRecursosDaoImpl.obtenerRecursos");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r.ID_RECURSO, r.ID_INSTALACION, ID_TIPO_RECURSO, ID_CADENA, ID_PAIS, ID_ESTADO_RECURSO, ID_TIPO_VALOR_RECURSO,    ");
        sql.append("        VALOR_RECURSO, VALOR_DISPONIBLE_RECURSO, r.FECHA_HORA_ALTA, r.FECHA_HORA_BAJA,                                   ");
        sql.append("        ROW CHANGE TOKEN FOR r AS SIS_MARCA_TIEMPO                                                                       ");
        sql.append("    FROM RECURSO r                                                                                                       ");
        sql.append("    INNER JOIN RECURSO_CLIENTE rc ON rc.ID_RECURSO = r.ID_RECURSO                                                        ");
        sql.append("    WHERE ID_CLIENTE = ? AND ID_INSTALACION_CLIENTE = ?                                                                  ");
        sql.append("WITH UR                                                                                                                  ");
        
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RecursoDto> recursoList = new ArrayList<RecursoDto>();

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, cliente.getIdCliente());
            ps.setInt(2, cliente.getIdInstalacion());
            rs = ps.executeQuery();

            while (rs.next()) {
                RecursoDto recurso = new RecursoDto();

                recurso.setIdRecurso(new PrimaryKeyRecursoDto(rs.getLong("ID_RECURSO"), rs.getInt("ID_INSTALACION")));
                recurso.setIdTipoRecurso(rs.getInt("ID_TIPO_RECURSO"));

                if (rs.getShort("ID_CADENA") != 0) {
                    recurso.setIdCadena(rs.getShort("ID_CADENA"));
                }

                if (rs.getShort("ID_PAIS") != 0) {
                    recurso.setIdPais(rs.getShort("ID_PAIS"));
                }

                recurso.setIdEstadoRecurso(rs.getShort("ID_ESTADO_RECURSO"));
                recurso.setIdTipoValorRecurso(rs.getShort("ID_TIPO_VALOR_RECURSO"));

                if (rs.getBigDecimal("VALOR_RECURSO").compareTo(BigDecimal.ZERO) > 0) {
                    recurso.setValorRecurso(rs.getBigDecimal("VALOR_RECURSO"));
                }

                if (rs.getBigDecimal("VALOR_DISPONIBLE_RECURSO").compareTo(BigDecimal.ZERO) > 0) {
                    recurso.setValorDisponibleRecurso(rs.getBigDecimal("VALOR_DISPONIBLE_RECURSO"));
                }

                recurso.setFechaHoraAlta(rs.getTimestamp("FECHA_HORA_ALTA"));

                if (rs.getTime("FECHA_HORA_BAJA") != null) {
                    recurso.setFechaHoraBaja(rs.getTimestamp("FECHA_HORA_BAJA"));
                }

                recurso.setSisMarcaTiempo(rs.getLong("SIS_MARCA_TIEMPO"));

                recursoList.add(recurso);
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

        return recursoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public void actualizarValorDisponibleRecurso(RecursoDto recurso, BigDecimal nuevoImporte) throws TimeoutException {
        LOGGER.trace("GestionRecursosDaoImpl.actualizarSaldosTarjeta");

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE RECURSO SET VALOR_DISPONIBLE_RECURSO = ?                                                                                 ");
        sql.append("       WHERE ID_RECURSO = ? AND ID_INSTALACION = ? AND ROW CHANGE TOKEN FOR RECURSO = ?                                         ");
        sql.append("WITH RS                                                                                                                         ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setBigDecimal(1, nuevoImporte);
            ps.setLong(2, recurso.getIdRecurso().getIdRecurso());
            ps.setInt(3, recurso.getIdRecurso().getIdInstalacion());
            ps.setLong(4, recurso.getSisMarcaTiempo());

            int nFilas = ps.executeUpdate();

            if (nFilas != 1) {
                throw new NumeroSerieException(String.format(Mensajes.INSTALACION, recurso.getIdRecurso().getIdRecurso(), recurso.getIdRecurso().getIdInstalacion()));
            }
        } catch (Exception e) {
            throw new TimeoutException(String.format(Errores.CONEXION_BLOQUEADA), e);
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
    public void actualizarValorRecurso(RecursoDto recurso, BigDecimal valorRecurso) throws TimeoutException {
        LOGGER.trace("GestionRecursosDaoImpl.actualizarValorRecurso");

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE RECURSO SET VALOR_RECURSO = ?                                                                                            ");
        sql.append("       WHERE ID_RECURSO = ? AND ID_INSTALACION = ? AND ROW CHANGE TOKEN FOR RECURSO = ?                                         ");
        sql.append("WITH RS                                                                                                                         ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setBigDecimal(1, valorRecurso);
            ps.setLong(2, recurso.getIdRecurso().getIdRecurso());
            ps.setInt(3, recurso.getIdRecurso().getIdInstalacion());
            ps.setLong(4, recurso.getSisMarcaTiempo());

            int nFilas = ps.executeUpdate();

            if (nFilas != 1) {
                throw new NumeroSerieException(String.format(Mensajes.INSTALACION, recurso.getIdRecurso().getIdRecurso(), recurso.getIdRecurso().getIdInstalacion()));
            }
        } catch (Exception e) {
            throw new TimeoutException(String.format(Errores.CONEXION_BLOQUEADA), e);
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
    public void actualizarValoresRecurso(RecursoDto recurso, BigDecimal valorRecurso, BigDecimal valorDisponibleRecurso) throws TimeoutException {
        LOGGER.trace("GestionRecursosDaoImpl.actualizarValorRecurso");

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE RECURSO SET VALOR_RECURSO = ?, VALOR_DISPONIBLE_RECURSO = ?                                                              ");
        sql.append("       WHERE ID_RECURSO = ? AND ID_INSTALACION = ? AND ROW CHANGE TOKEN FOR RECURSO = ?                                         ");
        sql.append("WITH RS                                                                                                                         ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setBigDecimal(1, valorRecurso);
            ps.setBigDecimal(2, valorDisponibleRecurso);
            ps.setLong(3, recurso.getIdRecurso().getIdRecurso());
            ps.setInt(4, recurso.getIdRecurso().getIdInstalacion());
            ps.setLong(5, recurso.getSisMarcaTiempo());

            int nFilas = ps.executeUpdate();

            if (nFilas != 1) {
                throw new NumeroSerieException(String.format(Mensajes.INSTALACION, recurso.getIdRecurso().getIdRecurso(), recurso.getIdRecurso().getIdInstalacion()));
            }
        } catch (Exception e) {
            throw new TimeoutException(String.format(Errores.CONEXION_BLOQUEADA), e);
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
    public Integer obtenerTipoRecurso(String recurso) {
        LOGGER.trace("GestionRecursosDaoImpl.obtenerTipoRecurso");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_TIPO_RECURSO                                                                                                          ");
        sql.append("       FROM TIPO_RECURSO                                                                                                        ");
        sql.append("       WHERE DESCRIPCION LIKE ?                                                                                                 ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer idTipoRecurso = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, '%' + recurso.toUpperCase() + '%');

            rs = ps.executeQuery();

            while (rs.next()) {
                idTipoRecurso = rs.getInt("ID_TIPO_RECURSO");
            }
        } catch (Exception e) {
            throw new HibernateException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new HibernateException(e);
            }
        }

        return idTipoRecurso;
    }

}
