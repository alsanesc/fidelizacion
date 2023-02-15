package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.NumeroSerieException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Cadenas;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.Errores;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionDescuentosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionMaestrosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DetalleDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyDetalleDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyLineaPedidoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyOperacionDescuentoDto;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion de Hibernate de GestionDescuentosDao: nos permitira realizar todas las operaciones
 * relacionadas con las tablas de DESCUENTOS (DETALLE_DESCUENTO, LINEA_DESCUENTO, OPERACION_DESCUENTO, DESCUENTO, TIPO_LINEA_PEDIDO, ESTADO_DESCUENTO,
 * TIPO_OPERACION_DESCUENTO).
 */
@Repository("gestionDescuentosDao")
@Transactional(propagation = Propagation.MANDATORY)
public class GestionDescuentosDaoImpl implements GestionDescuentosDao {

    /** The Constant LOG. */
    private final Logger LOGGER = LoggerFactory.getLogger(GestionDescuentosDaoImpl.class);

    /** The gestion maestros dao. */
    @Autowired
    private GestionMaestrosDao gestionMaestrosDao;

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

    /******************************************************************************************************************************
     * 
     * ACCIONES SOBRE DESCUENTOS
     * 
     ******************************************************************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public DescuentoDto obtenerDescuentoPorCodigo(String descuento) {
        LOGGER.trace("GestionDescuentosDaoImpl.obtenerDescuentoPorCodigo");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_DESCUENTO, ID_INSTALACION, CODIGO_PEDIDO_EXTERNO, ID_CADENA, ID_PAIS, ID_LOCALIZACION,                         ");
        sql.append("        ID_ESTADO_DESCUENTO, ID_DETALLE_DESCUENTO, ID_INSTALACION_DETALLE_DESCUENTO, ID_CLIENTE, ID_INSTALACION_CLIENTE, ");
        sql.append("    	IMPORTE_PEDIDO, ID_DIVISA, FECHA_HORA_VALIDEZ_DESCUENTO, ROW CHANGE TOKEN FOR DESCUENTO AS SIS_MARCA_TIEMPO      ");
        sql.append("    FROM DESCUENTO                                                                                                       ");
        sql.append("    WHERE CODIGO_PEDIDO_EXTERNO = ?                                                                                      ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DescuentoDto dto = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, descuento);
            rs = ps.executeQuery();

            while (rs.next()) {
                dto = new DescuentoDto();

                dto.setIdDescuento(new PrimaryKeyDescuentoDto(rs.getLong("ID_DESCUENTO"), rs.getInt("ID_INSTALACION")));
                dto.setCodigoPedidoExterno(rs.getString("CODIGO_PEDIDO_EXTERNO"));

                if (rs.getShort("ID_CADENA") != 0) {
                    dto.setIdCadena(rs.getShort("ID_CADENA"));
                }

                if (rs.getString("ID_PAIS") != null) {
                    dto.setIdPais(rs.getShort("ID_PAIS"));
                }

                if (rs.getInt("ID_LOCALIZACION") != 0) {
                    dto.setIdLocalizacion(rs.getInt("ID_LOCALIZACION"));
                }

                dto.setIdEstadoDescuento(rs.getShort("ID_ESTADO_DESCUENTO"));

                if (rs.getLong("ID_DETALLE_DESCUENTO") != 0) {
                    dto.setIdDetalleDescuento(new PrimaryKeyDetalleDescuentoDto(rs.getLong("ID_DETALLE_DESCUENTO"), rs
                            .getInt("ID_INSTALACION_DETALLE_DESCUENTO")));
                }

                if (rs.getObject("ID_CLIENTE") != null) {
                    dto.setIdCliente(new PrimaryKeyClienteDto(rs.getLong("ID_CLIENTE"), rs.getInt("ID_INSTALACION_CLIENTE")));
                }

                dto.setImportePedido(rs.getBigDecimal("IMPORTE_PEDIDO"));
                dto.setIdDivisa(rs.getShort("ID_DIVISA"));
                dto.setFechaHoraValidezDescuento(rs.getTimestamp("FECHA_HORA_VALIDEZ_DESCUENTO"));
                dto.setSisMarcaTiempo(rs.getLong("SIS_MARCA_TIEMPO"));
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

        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public DescuentoDto obtenerDescuento(PrimaryKeyDescuentoDto descuento) {
        LOGGER.trace("GestionDescuentosDaoImpl.obtenerDescuento");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_DESCUENTO, ID_INSTALACION, CODIGO_PEDIDO_EXTERNO, ID_CADENA, ID_PAIS, ID_LOCALIZACION,                         ");
        sql.append("        ID_ESTADO_DESCUENTO, ID_DETALLE_DESCUENTO, ID_INSTALACION_DETALLE_DESCUENTO, ID_CLIENTE, ID_INSTALACION_CLIENTE, ");
        sql.append("        IMPORTE_PEDIDO, ID_DIVISA, FECHA_HORA_VALIDEZ_DESCUENTO, ROW CHANGE TOKEN FOR DESCUENTO AS SIS_MARCA_TIEMPO      ");
        sql.append("    FROM DESCUENTO                                                                                                       ");
        sql.append("    WHERE ID_DESCUENTO = ? AND ID_INSTALACION = ?                                                                        ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DescuentoDto dto = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, descuento.getIdDescuento());
            ps.setInt(2, descuento.getIdInstalacion());
            rs = ps.executeQuery();

            while (rs.next()) {
                dto = new DescuentoDto();

                dto.setIdDescuento(new PrimaryKeyDescuentoDto(rs.getLong("ID_DESCUENTO"), rs.getInt("ID_INSTALACION")));
                dto.setCodigoPedidoExterno(rs.getString("CODIGO_PEDIDO_EXTERNO"));

                if (rs.getShort("ID_CADENA") != 0) {
                    dto.setIdCadena(rs.getShort("ID_CADENA"));
                }

                if (rs.getString("ID_PAIS") != null) {
                    dto.setIdPais(rs.getShort("ID_PAIS"));
                }

                if (rs.getInt("ID_LOCALIZACION") != 0) {
                    dto.setIdLocalizacion(rs.getInt("ID_LOCALIZACION"));
                }

                dto.setIdEstadoDescuento(rs.getShort("ID_ESTADO_DESCUENTO"));

                if (rs.getLong("ID_DETALLE_DESCUENTO") != 0) {
                    dto.setIdDetalleDescuento(new PrimaryKeyDetalleDescuentoDto(rs.getLong("ID_DETALLE_DESCUENTO"), rs
                            .getInt("ID_INSTALACION_DETALLE_DESCUENTO")));
                }

                if (rs.getObject("ID_CLIENTE") != null) {
                    dto.setIdCliente(new PrimaryKeyClienteDto(rs.getLong("ID_CLIENTE"), rs.getInt("ID_INSTALACION_CLIENTE")));
                }

                dto.setImportePedido(rs.getBigDecimal("IMPORTE_PEDIDO"));
                dto.setIdDivisa(rs.getShort("ID_DIVISA"));
                dto.setFechaHoraValidezDescuento(rs.getTimestamp("FECHA_HORA_VALIDEZ_DESCUENTO"));
                dto.setSisMarcaTiempo(rs.getLong("SIS_MARCA_TIEMPO"));
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

        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public List<DescuentoDto> obtenerDescuentos(Short estadoDescuento, Cadenas cadena) {
        LOGGER.trace("GestionDescuentosDaoImpl.obtenerDescuentos");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_DESCUENTO, ID_INSTALACION, CODIGO_PEDIDO_EXTERNO, ID_CADENA, ID_PAIS, ID_LOCALIZACION,                         ");
        sql.append("        ID_ESTADO_DESCUENTO, ID_DETALLE_DESCUENTO, ID_INSTALACION_DETALLE_DESCUENTO, ID_CLIENTE, ID_INSTALACION_CLIENTE, ");
        sql.append("        IMPORTE_PEDIDO, ID_DIVISA, FECHA_HORA_VALIDEZ_DESCUENTO, ROW CHANGE TOKEN FOR DESCUENTO AS SIS_MARCA_TIEMPO      ");
        sql.append("    FROM DESCUENTO                                                                                                       ");
        sql.append("    WHERE ID_ESTADO_DESCUENTO = ? AND ID_CADENA = ? AND ID_PAIS = ?                                                      ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DescuentoDto> dtoList = new ArrayList<DescuentoDto>();

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, estadoDescuento);
            ps.setShort(2, cadena.getIdCadena().shortValue());
            ps.setShort(3, gestionMaestrosDao.obtenerIdPais(cadena.getCodPais()));
            rs = ps.executeQuery();

            while (rs.next()) {
                DescuentoDto dto = new DescuentoDto();

                dto.setIdDescuento(new PrimaryKeyDescuentoDto(rs.getLong("ID_DESCUENTO"), rs.getInt("ID_INSTALACION")));
                dto.setCodigoPedidoExterno(rs.getString("CODIGO_PEDIDO_EXTERNO"));

                if (rs.getShort("ID_CADENA") != 0) {
                    dto.setIdCadena(rs.getShort("ID_CADENA"));
                }

                if (rs.getString("ID_PAIS") != null) {
                    dto.setIdPais(rs.getShort("ID_PAIS"));
                }

                if (rs.getInt("ID_LOCALIZACION") != 0) {
                    dto.setIdLocalizacion(rs.getInt("ID_LOCALIZACION"));
                }

                dto.setIdEstadoDescuento(rs.getShort("ID_ESTADO_DESCUENTO"));

                if (rs.getLong("ID_DETALLE_DESCUENTO") != 0) {
                    dto.setIdDetalleDescuento(new PrimaryKeyDetalleDescuentoDto(rs.getLong("ID_DETALLE_DESCUENTO"), rs
                            .getInt("ID_INSTALACION_DETALLE_DESCUENTO")));
                }

                if (rs.getObject("ID_CLIENTE") != null) {
                    dto.setIdCliente(new PrimaryKeyClienteDto(rs.getLong("ID_CLIENTE"), rs.getInt("ID_INSTALACION_CLIENTE")));
                }

                dto.setImportePedido(rs.getBigDecimal("IMPORTE_PEDIDO"));
                dto.setIdDivisa(rs.getShort("ID_DIVISA"));
                dto.setFechaHoraValidezDescuento(rs.getTimestamp("FECHA_HORA_VALIDEZ_DESCUENTO"));
                dto.setSisMarcaTiempo(rs.getLong("SIS_MARCA_TIEMPO"));
                
                dtoList.add(dto);
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

        return dtoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public void actualizarDescuento(DescuentoDto descuento, Short estadoDescuento) throws TimeoutException {
        LOGGER.trace("GestionDescuentosDaoImpl.actualizarDescuento");

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE DESCUENTO SET ID_ESTADO_DESCUENTO = ?                                                                                    ");
        sql.append("       WHERE ID_DESCUENTO = ? AND ID_INSTALACION = ? AND ROW CHANGE TOKEN FOR DESCUENTO = ?                                     ");
        sql.append("WITH RS                                                                                                                         ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, estadoDescuento);
            ps.setLong(2, descuento.getIdDescuento().getIdDescuento());
            ps.setInt(3, descuento.getIdDescuento().getIdInstalacion());
            ps.setLong(4, descuento.getSisMarcaTiempo());

            int nFilas = ps.executeUpdate();

            if (nFilas != 1) {
                throw new NumeroSerieException(String.format(Mensajes.INSTALACION, descuento.getIdDescuento().getIdDescuento(), descuento.getIdDescuento().getIdInstalacion()));
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
    public void actualizarLineasDescuento(DescuentoDto descuento, Short estadoDescuento) throws TimeoutException {
        LOGGER.trace("GestionDescuentosDaoImpl.actualizarLineasDescuento");

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE LINEA_DESCUENTO SET ID_ESTADO_DESCUENTO = ?                                                                              ");
        sql.append("       WHERE ID_DESCUENTO = ? AND ID_INSTALACION = ?                                                                            ");
        sql.append("WITH RS                                                                                                                         ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, estadoDescuento);
            ps.setLong(2, descuento.getIdDescuento().getIdDescuento());
            ps.setInt(3, descuento.getIdDescuento().getIdInstalacion());

            ps.executeUpdate();
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
    public void actualizarLineaDescuentoCaptura(DescuentoDto descuento, LineaDescuentoDto linea, Short estadoDescuento) throws TimeoutException {
        LOGGER.trace("GestionDescuentosDaoImpl.actualizarLineaDescuentoCaptura");

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE LINEA_DESCUENTO SET ID_ESTADO_DESCUENTO = ?, NUMERO_UNIDADES_CAPTURADAS = ?                                              ");
        sql.append("       WHERE ID_DESCUENTO = ? AND ID_INSTALACION = ? AND CODIGO_LINEA_PEDIDO_EXTERNO = ? AND PARTNUMBER = ?                     ");
        sql.append("WITH RS                                                                                                                         ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, estadoDescuento);
            ps.setInt(2, linea.getNumeroUnidades());
            ps.setLong(3, descuento.getIdDescuento().getIdDescuento());
            ps.setInt(4, descuento.getIdDescuento().getIdInstalacion());
            ps.setString(5, linea.getCodigoLineaPedidoExterno());
            ps.setString(6, linea.getPartNumber());

            int nFilas = ps.executeUpdate();

            if (nFilas != 1) {
                throw new NumeroSerieException(String.format(Mensajes.INSTALACION, descuento.getIdDescuento().getIdDescuento(), descuento.getIdDescuento().getIdInstalacion()));
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
    public void actualizarLineaDescuentoCaptura(DescuentoDto descuento, Short estadoDescuento) throws TimeoutException {
        LOGGER.trace("GestionDescuentosDaoImpl.actualizarLineaDescuentoCaptura");

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE LINEA_DESCUENTO SET ID_ESTADO_DESCUENTO = ?, NUMERO_UNIDADES_CAPTURADAS = NUMERO_UNIDADES                                ");
        sql.append("       WHERE ID_DESCUENTO = ? AND ID_INSTALACION = ?                                                                            ");
        sql.append("WITH RS                                                                                                                         ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, estadoDescuento);
            ps.setLong(2, descuento.getIdDescuento().getIdDescuento());
            ps.setInt(3, descuento.getIdDescuento().getIdInstalacion());
            ps.executeUpdate();
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
    public void actualizarLineaDescuentoDevolucion(DescuentoDto descuento, LineaDescuentoDto linea, Short estadoDescuento) throws TimeoutException {
        LOGGER.trace("GestionDescuentosDaoImpl.actualizarLineaDescuentoDevolucion");

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE LINEA_DESCUENTO SET ID_ESTADO_DESCUENTO = ?, NUMERO_UNIDADES_DEVUELTAS = ?                                               ");
        sql.append("       WHERE ID_DESCUENTO = ? AND ID_INSTALACION = ? AND CODIGO_LINEA_PEDIDO_EXTERNO = ? AND PARTNUMBER = ?                     ");
        sql.append("WITH RS                                                                                                                         ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, estadoDescuento);
            ps.setInt(2, linea.getNumeroUnidades());
            ps.setLong(3, descuento.getIdDescuento().getIdDescuento());
            ps.setInt(4, descuento.getIdDescuento().getIdInstalacion());
            ps.setString(5, linea.getCodigoLineaPedidoExterno());
            ps.setString(6, linea.getPartNumber());

            int nFilas = ps.executeUpdate();

            if (nFilas != 1) {
                throw new NumeroSerieException(String.format(Mensajes.INSTALACION, descuento.getIdDescuento().getIdDescuento(), descuento.getIdDescuento().getIdInstalacion()));
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
    public void actualizarLineaDescuentoDevolucion(DescuentoDto descuento, Short estadoDescuento) throws TimeoutException {
        LOGGER.trace("GestionDescuentosDaoImpl.actualizarLineaDescuentoDevolucion");

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE LINEA_DESCUENTO SET ID_ESTADO_DESCUENTO = ?, NUMERO_UNIDADES_DEVUELTAS = NUMERO_UNIDADES                                 ");
        sql.append("       WHERE ID_DESCUENTO = ? AND ID_INSTALACION = ?                                                                            ");
        sql.append("WITH RS                                                                                                                         ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, estadoDescuento);
            ps.setLong(2, descuento.getIdDescuento().getIdDescuento());
            ps.setInt(3, descuento.getIdDescuento().getIdInstalacion());
            ps.executeUpdate();
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
    public void actualizarLineaDescuento(DescuentoDto descuento, LineaDescuentoDto linea, Short estadoDescuento) throws TimeoutException {
        LOGGER.trace("GestionDescuentosDaoImpl.actualizarLineaDescuento");

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE LINEA_DESCUENTO SET ID_ESTADO_DESCUENTO = ?                                                                              ");
        sql.append("       WHERE ID_DESCUENTO = ? AND ID_INSTALACION = ? AND CODIGO_LINEA_PEDIDO_EXTERNO = ? AND PARTNUMBER = ?                     ");
        sql.append("WITH RS                                                                                                                         ");

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, estadoDescuento);
            ps.setLong(2, descuento.getIdDescuento().getIdDescuento());
            ps.setInt(3, descuento.getIdDescuento().getIdInstalacion());
            ps.setString(4, linea.getCodigoLineaPedidoExterno());
            ps.setString(5, linea.getPartNumber());

            int nFilas = ps.executeUpdate();

            if (nFilas != 1) {
                throw new NumeroSerieException(String.format(Mensajes.INSTALACION, descuento.getIdDescuento().getIdDescuento(), descuento.getIdDescuento().getIdInstalacion()));
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
    public String obtenerCodigoPedido(PrimaryKeyDescuentoDto idDescuento) {
        LOGGER.trace("GestionDescuentosDaoImpl.obtenerCodigoPedido");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CODIGO_PEDIDO_EXTERNO                                                                                             ");
        sql.append("    FROM DESCUENTO                                                                                                       ");
        sql.append("    WHERE ID_DESCUENTO = ? AND ID_INSTALACION = ?                                                                        ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String codigo = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, idDescuento.getIdDescuento());
            ps.setInt(2, idDescuento.getIdInstalacion());
            rs = ps.executeQuery();

            while (rs.next()) {
                codigo = rs.getString("CODIGO_PEDIDO_EXTERNO");
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

        return codigo;
    }

    /******************************************************************************************************************************
     * 
     * ACCIONES SOBRE DETALLE_DESCUENTO
     * 
     ******************************************************************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public DetalleDescuentoDto obtenerDetalleDescuento(PrimaryKeyDetalleDescuentoDto detalleDescuento) {
        LOGGER.trace("GestionDescuentosDaoImpl.obtenerDetalleDescuento");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_DETALLE_DESCUENTO, ID_INSTALACION, INCREMENTO_PUNTOS, CONSUMO_PUNTOS, PORCENTAJE_DESCUENTO,                    ");
        sql.append("        IMPORTE_DESCUENTO, NUMERO_UNIDADES                                                                               ");
        sql.append("    FROM DETALLE_DESCUENTO                                                                                               ");
        sql.append("    WHERE ID_DETALLE_DESCUENTO = ? AND ID_INSTALACION = ?                                                                ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DetalleDescuentoDto detalle = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, detalleDescuento.getIdDetalleDescuento());
            ps.setInt(2, detalleDescuento.getIdInstalacion());
            rs = ps.executeQuery();

            while (rs.next()) {
                detalle = new DetalleDescuentoDto();

                detalle.setIdDetalleDescuento(new PrimaryKeyDetalleDescuentoDto(rs.getLong("ID_DETALLE_DESCUENTO"), rs.getInt("ID_INSTALACION")));

                if (rs.getBigDecimal("INCREMENTO_PUNTOS").compareTo(BigDecimal.ZERO) > 0) {
                    detalle.setIncrementoPuntos(rs.getBigDecimal("INCREMENTO_PUNTOS"));
                }

                if (rs.getBigDecimal("CONSUMO_PUNTOS").compareTo(BigDecimal.ZERO) > 0) {
                    detalle.setConsumoPuntos(rs.getBigDecimal("CONSUMO_PUNTOS"));
                }

                if (rs.getBigDecimal("PORCENTAJE_DESCUENTO").compareTo(BigDecimal.ZERO) > 0) {
                    detalle.setPorcentajeDescuento(rs.getBigDecimal("PORCENTAJE_DESCUENTO"));
                }

                if (rs.getBigDecimal("IMPORTE_DESCUENTO").compareTo(BigDecimal.ZERO) > 0) {
                    detalle.setImporteDescuento(rs.getBigDecimal("IMPORTE_DESCUENTO"));
                }
                
                if (rs.getInt("NUMERO_UNIDADES") != 0) {
                    detalle.setNumeroUnidades(rs.getInt("NUMERO_UNIDADES"));
                }

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

        return detalle;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public DetalleDescuentoDto obtenerDetalleDescuento(PrimaryKeyDescuentoDto descuento, String partNumber) {
        LOGGER.trace("GestionDescuentosDaoImpl.obtenerDetalleDescuento");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dd.ID_DETALLE_DESCUENTO, dd.ID_INSTALACION, CONSUMO_PUNTOS, IMPORTE_DESCUENTO, dd.NUMERO_UNIDADES                 ");
        sql.append("    FROM DETALLE_DESCUENTO dd                                                                                            ");
        sql.append("    INNER JOIN LINEA_DESCUENTO ld ON ld.ID_DETALLE_DESCUENTO = dd.ID_DETALLE_DESCUENTO AND                               ");
        sql.append("        ld.ID_INSTALACION_DETALLE_DESCUENTO=dd.ID_INSTALACION                                                            ");
        sql.append("    INNER JOIN DESCUENTO d ON ld.ID_DESCUENTO = d.ID_DESCUENTO AND ld.ID_INSTALACION_DESCUENTO = d.ID_INSTALACION        ");
        sql.append("    WHERE d.ID_DESCUENTO = ? AND d.ID_INSTALACION = ? AND PARTNUMBER = ?                                                 ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DetalleDescuentoDto detalle = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, descuento.getIdDescuento());
            ps.setInt(2, descuento.getIdInstalacion());
            ps.setString(3, partNumber);
            rs = ps.executeQuery();

            while (rs.next()) {
                detalle = new DetalleDescuentoDto();

                detalle.setIdDetalleDescuento(new PrimaryKeyDetalleDescuentoDto(rs.getLong("ID_DETALLE_DESCUENTO"), rs.getInt("ID_INSTALACION")));

                if (rs.getBigDecimal("CONSUMO_PUNTOS").compareTo(BigDecimal.ZERO) > 0) {
                    detalle.setConsumoPuntos(rs.getBigDecimal("CONSUMO_PUNTOS"));
                }

                if (rs.getBigDecimal("IMPORTE_DESCUENTO").compareTo(BigDecimal.ZERO) > 0) {
                    detalle.setImporteDescuento(rs.getBigDecimal("IMPORTE_DESCUENTO"));
                }
                
                if (rs.getInt("NUMERO_UNIDADES") !=  0) {
                    detalle.setNumeroUnidades(rs.getInt("NUMERO_UNIDADES"));
                }
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

        return detalle;
    }


    /******************************************************************************************************************************
     * 
     * ACCIONES SOBRE OPERACION_DESCUENTO
     * 
     ******************************************************************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public List<OperacionDescuentoDto> obtenerOperacionDescuento(PrimaryKeyDescuentoDto descuento, Short tipoOperacionDescuento) {
        LOGGER.trace("GestionDescuentosDaoImpl.obtenerOperacionDescuento");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_OPERACION_DESCUENTO, ID_INSTALACION, CODIGO_OPERACION_EXTERNO, ID_DESCUENTO, ID_INSTALACION_DESCUENTO,         ");
        sql.append("        ID_TIPO_OPERACION_DESCUENTO, FECHA_HORA_OPERACION, IMPORTE_OPERACION                                             ");
        sql.append("    FROM OPERACION_DESCUENTO                                                                                             ");
        sql.append("    WHERE ID_DESCUENTO = ? AND ID_INSTALACION_DESCUENTO = ? AND ID_TIPO_OPERACION_DESCUENTO = ?                          ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OperacionDescuentoDto> dtoList = new ArrayList<OperacionDescuentoDto>();

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, descuento.getIdDescuento());
            ps.setInt(2, descuento.getIdInstalacion());
            ps.setShort(3, tipoOperacionDescuento);
            rs = ps.executeQuery();

            while (rs.next()) {
                OperacionDescuentoDto dto = new OperacionDescuentoDto();
                
                dto.setIdOperacionDescuento(new PrimaryKeyOperacionDescuentoDto(rs.getLong("ID_OPERACION_DESCUENTO"), rs.getInt("ID_INSTALACION")));
                dto.setCodigoOperacionExterno(rs.getString("CODIGO_OPERACION_EXTERNO"));
                dto.setIdDescuento(new PrimaryKeyDescuentoDto(rs.getLong("ID_DESCUENTO"), rs.getInt("ID_INSTALACION_DESCUENTO")));
                dto.setIdTipoOperacionDescuento(rs.getShort("ID_TIPO_OPERACION_DESCUENTO"));
                dto.setFechaHoraOperacion(rs.getTimestamp("FECHA_HORA_OPERACION"));
                
                if (rs.getBigDecimal("IMPORTE_OPERACION").compareTo(BigDecimal.ZERO) > 0) {
                    dto.setImporteOperacion(rs.getBigDecimal("IMPORTE_OPERACION"));
                }

                dtoList.add(dto);
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

        return dtoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public List<Long> obtenerIdOperacionDescuento(PrimaryKeyDescuentoDto idDescuento, Short tipoOperacionDescuento) {
        LOGGER.trace("GestionDescuentosDaoImpl.obtenerOperacionDescuento");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_OPERACION_DESCUENTO                                                                                            ");
        sql.append("    FROM OPERACION_DESCUENTO od                                                                                          ");
        sql.append("    INNER JOIN DESCUENTO d ON d.ID_DESCUENTO = od.ID_DESCUENTO AND d.ID_INSTALACION = od.ID_INSTALACION_DESCUENTO        ");
        sql.append("    WHERE d.ID_DESCUENTO = ? AND d.ID_INSTALACION = ? AND ID_TIPO_OPERACION_DESCUENTO = ?                                ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Long> lista = new ArrayList<Long>();

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, idDescuento.getIdDescuento());
            ps.setInt(2, idDescuento.getIdInstalacion());
            ps.setShort(3, tipoOperacionDescuento);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(rs.getLong("ID_OPERACION_DESCUENTO"));
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

        return lista;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public OperacionDescuentoDto obtenerOperacionDescuento(PrimaryKeyOperacionDescuentoDto idOperacionDescuento) {
        LOGGER.trace("GestionDescuentosDaoImpl.obtenerOperacionDescuento");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_OPERACION_DESCUENTO, ID_INSTALACION, CODIGO_OPERACION_EXTERNO, ID_DESCUENTO, ID_INSTALACION_DESCUENTO,         ");
        sql.append("        ID_TIPO_OPERACION_DESCUENTO, FECHA_HORA_OPERACION, IMPORTE_OPERACION                                             ");
        sql.append("    FROM OPERACION_DESCUENTO                                                                                             ");
        sql.append("    WHERE ID_OPERACION_DESCUENTO = ? AND ID_INSTALACION = ?                                                              ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OperacionDescuentoDto operacion = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, idOperacionDescuento.getIdOperacionDescuento());
            ps.setInt(2, idOperacionDescuento.getIdInstalacion());
            rs = ps.executeQuery();

            while (rs.next()) {
                operacion = new OperacionDescuentoDto();

                operacion.setIdOperacionDescuento(new PrimaryKeyOperacionDescuentoDto(rs.getLong("ID_OPERACION_DESCUENTO"), rs.getInt("ID_INSTALACION")));
                operacion.setCodigoOperacionExterno(rs.getString("CODIGO_OPERACION_EXTERNO"));
                operacion.setIdDescuento(new PrimaryKeyDescuentoDto(rs.getLong("ID_DESCUENTO"), rs.getInt("ID_INSTALACION_DESCUENTO")));
                operacion.setIdTipoOperacionDescuento(rs.getShort("ID_TIPO_OPERACION_DESCUENTO"));
                operacion.setFechaHoraOperacion(rs.getTimestamp("FECHA_HORA_OPERACION"));
                
                if (rs.getBigDecimal("IMPORTE_OPERACION").compareTo(BigDecimal.ZERO) > 0) {
                    operacion.setImporteOperacion(rs.getBigDecimal("IMPORTE_OPERACION"));
                }
                
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

        return operacion;
    }

    /******************************************************************************************************************************
     * 
     * ACCIONES SOBRE LINEA_DESCUENTO
     * 
     ******************************************************************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public List<LineaDescuentoDto> obtenerLineaDescuento(PrimaryKeyDescuentoDto descuento, Short estadoDescuento) {
        LOGGER.trace("GestionDescuentosDaoImpl.obtenerLineaDescuento");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_LINEA_DESCUENTO, ID_INSTALACION, ID_DESCUENTO, ID_INSTALACION_DESCUENTO, CODIGO_LINEA_PEDIDO_EXTERNO,          ");
        sql.append("        ID_TIPO_LINEA_PEDIDO, PARTNUMBER, NUMERO_UNIDADES, NUMERO_UNIDADES_CAPTURADAS, NUMERO_UNIDADES_DEVUELTAS,        ");
        sql.append("        IMPORTE_UNITARIO, IMPORTE_TOTAL, ID_ESTADO_DESCUENTO, ID_DETALLE_DESCUENTO, ID_INSTALACION_DETALLE_DESCUENTO     ");
        sql.append("    FROM LINEA_DESCUENTO                                                                                                 ");
        sql.append("    WHERE ID_DESCUENTO = ? AND ID_INSTALACION_DESCUENTO = ? AND                                                          ");
        sql.append("        (ID_ESTADO_DESCUENTO = ? OR NUMERO_UNIDADES_CAPTURADAS + NUMERO_UNIDADES_DEVUELTAS <> NUMERO_UNIDADES)           ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<LineaDescuentoDto> lineaDescuentoList = new ArrayList<LineaDescuentoDto>();

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, descuento.getIdDescuento());
            ps.setInt(2, descuento.getIdInstalacion());
            ps.setShort(3, estadoDescuento);
            rs = ps.executeQuery();

            while (rs.next()) {
                LineaDescuentoDto linea = new LineaDescuentoDto();
                
                linea.setIdLineaPedido(new PrimaryKeyLineaPedidoDto(rs.getLong("ID_LINEA_DESCUENTO"), rs.getInt("ID_INSTALACION")));
                linea.setIdDescuento(new PrimaryKeyDescuentoDto(rs.getLong("ID_DESCUENTO"), rs.getInt("ID_INSTALACION_DESCUENTO")));
                linea.setCodigoLineaPedidoExterno(rs.getString("CODIGO_LINEA_PEDIDO_EXTERNO"));
                linea.setIdTipoLineaPedido(rs.getShort("ID_TIPO_LINEA_PEDIDO"));
                linea.setPartNumber(rs.getString("PARTNUMBER"));
                
                if (rs.getInt("NUMERO_UNIDADES_CAPTURADAS") != 0 || rs.getInt("NUMERO_UNIDADES_DEVUELTAS") != 0) {
                    linea.setNumeroUnidades(rs.getInt("NUMERO_UNIDADES") - (rs.getInt("NUMERO_UNIDADES_CAPTURADAS") + rs.getInt("NUMERO_UNIDADES_DEVUELTAS")));
                } else {
                    linea.setNumeroUnidades(rs.getInt("NUMERO_UNIDADES"));
                }
                
                linea.setImporteUnitario(rs.getBigDecimal("IMPORTE_UNITARIO"));
                linea.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
                linea.setIdEstadoDescuento(rs.getShort("ID_ESTADO_DESCUENTO"));
                
                if (rs.getLong("ID_DETALLE_DESCUENTO") != 0 && rs.getInt("ID_INSTALACION_DETALLE_DESCUENTO") != 0) {
                    linea.setIdDetalleDescuento(new PrimaryKeyDetalleDescuentoDto(rs.getLong("ID_DETALLE_DESCUENTO"), rs.getInt("ID_INSTALACION_DETALLE_DESCUENTO")));
                }
                
                lineaDescuentoList.add(linea);
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

        return lineaDescuentoList;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public List<LineaDescuentoDto> obtenerLineaDescuento(PrimaryKeyDescuentoDto descuento) {
        LOGGER.trace("GestionDescuentosDaoImpl.obtenerLineaDescuento");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_LINEA_DESCUENTO, ID_INSTALACION, ID_DESCUENTO, ID_INSTALACION_DESCUENTO, CODIGO_LINEA_PEDIDO_EXTERNO,          ");
        sql.append("        ID_TIPO_LINEA_PEDIDO, PARTNUMBER, NUMERO_UNIDADES, IMPORTE_UNITARIO, IMPORTE_TOTAL, ID_ESTADO_DESCUENTO,         ");
        sql.append("        ID_DETALLE_DESCUENTO, ID_INSTALACION_DETALLE_DESCUENTO                                                           ");
        sql.append("    FROM LINEA_DESCUENTO                                                                                                 ");
        sql.append("    WHERE ID_DESCUENTO = ? AND ID_INSTALACION_DESCUENTO = ?                                                              ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<LineaDescuentoDto> lineaDescuentoList = new ArrayList<LineaDescuentoDto>();

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, descuento.getIdDescuento());
            ps.setInt(2, descuento.getIdInstalacion());
            rs = ps.executeQuery();

            while (rs.next()) {
                LineaDescuentoDto linea = new LineaDescuentoDto();
                
                linea.setIdLineaPedido(new PrimaryKeyLineaPedidoDto(rs.getLong("ID_LINEA_DESCUENTO"), rs.getInt("ID_INSTALACION")));
                linea.setIdDescuento(new PrimaryKeyDescuentoDto(rs.getLong("ID_DESCUENTO"), rs.getInt("ID_INSTALACION_DESCUENTO")));
                linea.setCodigoLineaPedidoExterno(rs.getString("CODIGO_LINEA_PEDIDO_EXTERNO"));
                linea.setIdTipoLineaPedido(rs.getShort("ID_TIPO_LINEA_PEDIDO"));
                linea.setPartNumber(rs.getString("PARTNUMBER"));
                linea.setNumeroUnidades(rs.getInt("NUMERO_UNIDADES"));
                linea.setImporteUnitario(rs.getBigDecimal("IMPORTE_UNITARIO"));
                linea.setImporteTotal(rs.getBigDecimal("IMPORTE_TOTAL"));
                linea.setIdEstadoDescuento(rs.getShort("ID_ESTADO_DESCUENTO"));
                
                if (rs.getLong("ID_DETALLE_DESCUENTO") != 0 && rs.getInt("ID_INSTALACION_DETALLE_DESCUENTO") != 0) {
                    linea.setIdDetalleDescuento(new PrimaryKeyDetalleDescuentoDto(rs.getLong("ID_DETALLE_DESCUENTO"), rs.getInt("ID_INSTALACION_DETALLE_DESCUENTO")));
                }
                
                lineaDescuentoList.add(linea);
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

        return lineaDescuentoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<LineaDescuentoDto> obtenerLineaDescuento(String idPedido) {
        session = sessionFactory.getCurrentSession();

        Criteria crit = session.createCriteria(LineaDescuentoDto.class);
        crit.add(Restrictions.eq("codigoLineaPedidoExterno", idPedido));

        return crit.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public DescuentoDto obtenerDescuentoOperacion(String codOperacionExterno) {
        LOGGER.trace("GestionDescuentosDaoImpl.obtenerDescuentoOperacion");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT d.ID_DESCUENTO, d.ID_INSTALACION, CODIGO_PEDIDO_EXTERNO, ID_CADENA, ID_PAIS, ID_LOCALIZACION,                     ");
        sql.append("        d.ID_ESTADO_DESCUENTO, d.ID_DETALLE_DESCUENTO, d.ID_INSTALACION_DETALLE_DESCUENTO, ID_CLIENTE,                   ");
        sql.append("        ID_INSTALACION_CLIENTE, IMPORTE_PEDIDO, ID_DIVISA, FECHA_HORA_VALIDEZ_DESCUENTO,                                 ");
        sql.append("        ROW CHANGE TOKEN FOR d AS SIS_MARCA_TIEMPO                                                                       ");
        sql.append("    FROM DESCUENTO d                                                                                                     ");
        sql.append("    INNER JOIN OPERACION_DESCUENTO od ON od.ID_DESCUENTO = d.ID_DESCUENTO                                                ");
        sql.append("    WHERE CODIGO_OPERACION_EXTERNO = ?                                                                                   ");
        sql.append("WITH UR                                                                                                                  ");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DescuentoDto descuento = null;

        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, codOperacionExterno);
            rs = ps.executeQuery();

            while (rs.next()) {
                descuento = new DescuentoDto();

                descuento.setIdDescuento(new PrimaryKeyDescuentoDto(rs.getLong("ID_DESCUENTO"), rs.getInt("ID_INSTALACION")));
                descuento.setCodigoPedidoExterno(rs.getString("CODIGO_PEDIDO_EXTERNO"));

                if (rs.getShort("ID_CADENA") != 0) {
                    descuento.setIdCadena(rs.getShort("ID_CADENA"));
                }

                if (rs.getString("ID_PAIS") != null) {
                    descuento.setIdPais(rs.getShort("ID_PAIS"));
                }

                if (rs.getInt("ID_LOCALIZACION") != 0) {
                    descuento.setIdLocalizacion(rs.getInt("ID_LOCALIZACION"));
                }

                descuento.setIdEstadoDescuento(rs.getShort("ID_ESTADO_DESCUENTO"));

                if (rs.getLong("ID_DETALLE_DESCUENTO") != 0) {
                    descuento.setIdDetalleDescuento(new PrimaryKeyDetalleDescuentoDto(rs.getLong("ID_DETALLE_DESCUENTO"), rs
                            .getInt("ID_INSTALACION_DETALLE_DESCUENTO")));
                }

                if (rs.getObject("ID_CLIENTE") != null) {
                    descuento.setIdCliente(new PrimaryKeyClienteDto(rs.getLong("ID_CLIENTE"), rs.getInt("ID_INSTALACION_CLIENTE")));
                }

                descuento.setImportePedido(rs.getBigDecimal("IMPORTE_PEDIDO"));
                descuento.setIdDivisa(rs.getShort("ID_DIVISA"));
                descuento.setFechaHoraValidezDescuento(rs.getTimestamp("FECHA_HORA_VALIDEZ_DESCUENTO"));
                descuento.setSisMarcaTiempo(rs.getLong("SIS_MARCA_TIEMPO"));
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

        return descuento;
    }

}
