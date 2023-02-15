package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.NumeroSerieException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.EstadoCliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Secuencias;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoOperacionRecurso;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.Errores;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionClientesDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionMaestrosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionRecursosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyOperacionRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.sequence.dao.SequencesDao;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion de Hibernate de
 * GestionClientesDao: nos permitira realizar todas las operaciones relacionadas
 * con las tablas de CLIENTE (CLIENTE, TIPO_CLIENTE, ESTADO_CLIENTE,
 * TARJETA_CLIENTE).
 */
@Repository("gestionClientesDao")
@Transactional(propagation = Propagation.MANDATORY)
public class GestionClientesDaoImpl implements GestionClientesDao {
	
	/** The Constant LOG. */
    private final Logger LOGGER = LoggerFactory.getLogger(GestionClientesDaoImpl.class);
    
    /** The id instalacion. */
    private @Value("${ID_INSTALACION}")Integer idInstalacion;
    
	/** The session. */
	private Session session;

	/** The gestion recursos dao. */
	@Autowired
	private GestionRecursosDao gestionRecursosDao;
	
	/** The gestion maestros dao. */
	@Autowired
	private GestionMaestrosDao gestionMaestrosDao;
	
	/** The sequences dao. */
    @Autowired
    private SequencesDao sequencesDao;

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/******************************************************************************************************************************
	 * 
	 * ACCIONES SOBRE CLIENTES
	 * 
	 ******************************************************************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void altaCliente(ClienteDto datosCliente, Calendar fechaHoraAlta, RecursoDto recurso) {
		try {
			session = sessionFactory.getCurrentSession();

			session.persist(datosCliente);
			session.persist(recurso);

			session.persist(new OperacionRecursoDto(new PrimaryKeyOperacionRecursoDto(new Long(sequencesDao.getNextValue(Secuencias.SQ_OPERACION_RECURSO)), idInstalacion), TipoOperacionRecurso.ALTA.getValue(), recurso.getIdRecurso(), new Timestamp(fechaHoraAlta.getTimeInMillis())));

			session.persist(new RecursoClienteDto(recurso.getIdRecurso(), datosCliente.getIdCliente(), new Timestamp(fechaHoraAlta.getTimeInMillis())));
		} catch (HibernateException e) {
			throw new HibernateException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void bajaCliente(ClienteDto clienteDto) throws TimeoutException {
		// Recuperamos los RECURSO que se encuentran en estado ACTIVO
		List<RecursoDto> recursoList = gestionRecursosDao.obtenerRecursosEstado(clienteDto.getIdCliente());

		Calendar fechaHoraBaja = Calendar.getInstance();
		
		// Generamos objeto CLIENTE
		actualizarCliente(clienteDto, fechaHoraBaja);

		// Generamos objeto RECURSO
		gestionRecursosDao.darBajaRecurso(recursoList, fechaHoraBaja);

		// Generamos objeto RECURSO-CLIENTE
		actualizarRecursoCliente(recursoList, fechaHoraBaja);

		// Generamos objeto OPERACION_RECURSO
		session = sessionFactory.getCurrentSession();
		for (RecursoDto recurso : recursoList) {
			session.persist(new OperacionRecursoDto(new PrimaryKeyOperacionRecursoDto(new Long(sequencesDao.getNextValue(Secuencias.SQ_OPERACION_RECURSO)), idInstalacion), TipoOperacionRecurso.BAJA.getValue(), recurso.getIdRecurso(), new Timestamp(fechaHoraBaja.getTimeInMillis())));
		}
		
		clienteDto.setFechaHoraBaja(new Timestamp(fechaHoraBaja.getTimeInMillis()));
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
    @SuppressWarnings("deprecation")
	public ClienteDto verificarCliente(Integer cadena, String pais, String idExterno) {
	    LOGGER.trace("GestionClientesDaoImpl.verificarCliente");
	    
	    StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_CLIENTE, ID_INSTALACION, COD_CLIENTE_WALLET_MARCA, ID_CADENA, ID_PAIS, ID_ESTADO_CLIENTE,                      ");
        sql.append("        ID_TIPO_CLIENTE, FECHA_HORA_ALTA, FECHA_HORA_BAJA, ROW CHANGE TOKEN FOR CLIENTE AS SIS_MARCA_TIEMPO              ");
        sql.append("    FROM CLIENTE                                                                                                         ");
        sql.append("    WHERE ID_CADENA = ? AND ID_PAIS = ? AND COD_CLIENTE_WALLET_MARCA = ? AND ID_ESTADO_CLIENTE = ?                       ");
        sql.append("WITH UR                                                                                                                  ");
        
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ClienteDto cliente = null;
        
        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setShort(1, cadena.shortValue());
            ps.setShort(2, gestionMaestrosDao.obtenerIdPais(pais));
            ps.setString(3, idExterno);
            ps.setShort(4, EstadoCliente.ACTIVO.getValue());
            rs = ps.executeQuery();

            while (rs.next()) {
                cliente = new ClienteDto();
                
                cliente.setIdCliente(new PrimaryKeyClienteDto(rs.getLong("ID_CLIENTE"), rs.getInt("ID_INSTALACION")));
                
                if (rs.getString("COD_CLIENTE_WALLET_MARCA") != null) {
                    cliente.setCodClienteWalletMarca(rs.getString("COD_CLIENTE_WALLET_MARCA"));
                }
                
                if (rs.getShort("ID_CADENA") != 0) {
                    cliente.setIdCadena(rs.getShort("ID_CADENA"));
                }
                
                if (rs.getShort("ID_PAIS") != 0) {
                    cliente.setIdPais(rs.getShort("ID_PAIS"));
                }
                
                cliente.setIdEstadoCliente(rs.getShort("ID_ESTADO_CLIENTE"));
                cliente.setIdTipoCliente(rs.getShort("ID_TIPO_CLIENTE"));
                cliente.setFechaHoraAlta(rs.getTimestamp("FECHA_HORA_ALTA"));
                
                if (rs.getTimestamp("FECHA_HORA_BAJA") != null) {
                    cliente.setFechaHoraBaja(rs.getTimestamp("FECHA_HORA_BAJA"));
                }
                
                cliente.setSisMarcaTiempo(rs.getLong("SIS_MARCA_TIEMPO"));
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
        
        return cliente;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("deprecation")
	public ClienteDto obtenerCliente(PrimaryKeyClienteDto clienteDto) {
		LOGGER.trace("GestionClientesDaoImpl.obtenerCliente");
		
		StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_CLIENTE, ID_INSTALACION, COD_CLIENTE_WALLET_MARCA, ID_CADENA, ID_PAIS, ID_ESTADO_CLIENTE,                      ");
        sql.append("        ID_TIPO_CLIENTE, FECHA_HORA_ALTA, FECHA_HORA_BAJA, ROW CHANGE TOKEN FOR CLIENTE AS SIS_MARCA_TIEMPO              ");
        sql.append("    FROM CLIENTE                                                                                                         ");
        sql.append("    WHERE ID_CLIENTE = ? AND ID_INSTALACION = ?                                                                          ");
        sql.append("WITH UR                                                                                                                  ");
		
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ClienteDto cliente = null;
        
        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setLong(1, clienteDto.getIdCliente());
            ps.setInt(2, clienteDto.getIdInstalacion());
            rs = ps.executeQuery();

            while (rs.next()) {
            	cliente = new ClienteDto();
            	
            	cliente.setIdCliente(new PrimaryKeyClienteDto(rs.getLong("ID_CLIENTE"), rs.getInt("ID_INSTALACION")));
            	
            	if (rs.getString("COD_CLIENTE_WALLET_MARCA") != null) {
            		cliente.setCodClienteWalletMarca(rs.getString("COD_CLIENTE_WALLET_MARCA"));
            	}
            	
            	if (rs.getShort("ID_CADENA") != 0) {
            		cliente.setIdCadena(rs.getShort("ID_CADENA"));
            	}
            	
            	if (rs.getShort("ID_PAIS") != 0) {
            		cliente.setIdPais(rs.getShort("ID_PAIS"));
            	}
            	
            	cliente.setIdEstadoCliente(rs.getShort("ID_ESTADO_CLIENTE"));
            	cliente.setIdTipoCliente(rs.getShort("ID_TIPO_CLIENTE"));
            	cliente.setFechaHoraAlta(rs.getTimestamp("FECHA_HORA_ALTA"));
            	
            	if (rs.getTimestamp("FECHA_HORA_BAJA") != null) {
                    cliente.setFechaHoraBaja(rs.getTimestamp("FECHA_HORA_BAJA"));
                }
                
            	cliente.setSisMarcaTiempo(rs.getLong("SIS_MARCA_TIEMPO"));
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
		
		return cliente;
	}
	
	 /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("deprecation")
    public ClienteDto obtenerClienteWallet(String idCliente) {
        LOGGER.trace("GestionClientesDaoImpl.obtenerClienteWallet");
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_CLIENTE, ID_INSTALACION, COD_CLIENTE_WALLET_MARCA, ID_CADENA, ID_PAIS, ID_ESTADO_CLIENTE,                      ");
        sql.append("        ID_TIPO_CLIENTE, FECHA_HORA_ALTA, FECHA_HORA_BAJA, ROW CHANGE TOKEN FOR CLIENTE AS SIS_MARCA_TIEMPO              ");
        sql.append("    FROM CLIENTE                                                                                                         ");
        sql.append("    WHERE COD_CLIENTE_WALLET_MARCA = ?                                                                                   ");
        sql.append("WITH UR                                                                                                                  ");
        
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ClienteDto cliente = null;
        
        try {
            connection = sessionFactory.getCurrentSession().connection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                cliente = new ClienteDto();
                
                cliente.setIdCliente(new PrimaryKeyClienteDto(rs.getLong("ID_CLIENTE"), rs.getInt("ID_INSTALACION")));
                
                if (rs.getString("COD_CLIENTE_WALLET_MARCA") != null) {
                    cliente.setCodClienteWalletMarca(rs.getString("COD_CLIENTE_WALLET_MARCA"));
                }
                
                if (rs.getShort("ID_CADENA") != 0) {
                    cliente.setIdCadena(rs.getShort("ID_CADENA"));
                }
                
                if (rs.getShort("ID_PAIS") != 0) {
                    cliente.setIdPais(rs.getShort("ID_PAIS"));
                }
                
                cliente.setIdEstadoCliente(rs.getShort("ID_ESTADO_CLIENTE"));
                cliente.setIdTipoCliente(rs.getShort("ID_TIPO_CLIENTE"));
                cliente.setFechaHoraAlta(rs.getTimestamp("FECHA_HORA_ALTA"));
                
                if (rs.getTimestamp("FECHA_HORA_BAJA") != null) {
                    cliente.setFechaHoraBaja(rs.getTimestamp("FECHA_HORA_BAJA"));
                }
                
                cliente.setSisMarcaTiempo(rs.getLong("SIS_MARCA_TIEMPO"));
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
        
        return cliente;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void actualizarCliente(ClienteDto cliente) throws TimeoutException {
	    LOGGER.trace("GestionClientesDaoImpl.obtenerCliente");
	    
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CLIENTE SET ID_ESTADO_CLIENTE = ?                                                                             ");
		sql.append("       WHERE ID_CLIENTE = ? AND ID_INSTALACION = ? AND ROW CHANGE TOKEN FOR CLIENTE = ?                              ");
		sql.append("WITH RS                                                                                                              ");

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = sessionFactory.getCurrentSession().connection();
			ps = connection.prepareStatement(sql.toString());
			ps.setShort(1, cliente.getIdEstadoCliente());
			ps.setLong(2, cliente.getIdCliente().getIdCliente());
			ps.setInt(3, cliente.getIdCliente().getIdInstalacion());
			ps.setLong(4, cliente.getSisMarcaTiempo());

			int nFilas = ps.executeUpdate();

			if (nFilas != 1) {
				throw new NumeroSerieException(String.format(Mensajes.INSTALACION, cliente.getIdCliente().getIdCliente(), cliente.getIdCliente().getIdInstalacion()));
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
	public void actualizarCliente(ClienteDto clienteDto, Calendar fechaHoraBaja) throws TimeoutException {
	    LOGGER.trace("GestionClientesDaoImpl.actualizarCliente");
	    
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CLIENTE SET ID_ESTADO_CLIENTE = ?, FECHA_HORA_BAJA = ?                                                        ");
		sql.append("       WHERE ID_CLIENTE = ? AND ID_INSTALACION = ? AND ROW CHANGE TOKEN FOR CLIENTE = ?                              ");
		sql.append("WITH RS                                                                                                              ");

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = sessionFactory.getCurrentSession().connection();
			ps = connection.prepareStatement(sql.toString());
			ps.setShort(1, EstadoCliente.INACTIVO.getValue());
			ps.setTimestamp(2, new Timestamp(fechaHoraBaja.getTimeInMillis()));
			ps.setLong(3, clienteDto.getIdCliente().getIdCliente());
			ps.setInt(4, clienteDto.getIdCliente().getIdInstalacion());
			ps.setLong(5, clienteDto.getSisMarcaTiempo());
			
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

	/******************************************************************************************************************************
	 * 
	 * ACCIONES SOBRE RECURSO-CLIENTE
	 * 
	 ******************************************************************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void actualizarRecursoCliente(List<RecursoDto> recursoList, Calendar fechaHoraBaja) throws TimeoutException {
	    LOGGER.trace("GestionClientesDaoImpl.actualizarRecursoCliente");
	    
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE RECURSO_CLIENTE SET FECHA_HORA_BAJA = ?                                                                       ");
		sql.append("       WHERE ID_RECURSO = ? AND ID_INSTALACION_RECURSO = ?                                                           ");
		sql.append("WITH RS                                                                                                              ");

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = sessionFactory.getCurrentSession().connection();
			ps = connection.prepareStatement(sql.toString());
			ps.setTimestamp(1, new Timestamp(fechaHoraBaja.getTimeInMillis()));

			for (RecursoDto recurso : recursoList) {
				ps.setLong(2, recurso.getIdRecurso().getIdRecurso());
				ps.setInt(3, recurso.getIdRecurso().getIdInstalacion());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			throw new TimeoutException("FILAS BLOQUEADAS POR OTRA CONEXION ", e);
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
