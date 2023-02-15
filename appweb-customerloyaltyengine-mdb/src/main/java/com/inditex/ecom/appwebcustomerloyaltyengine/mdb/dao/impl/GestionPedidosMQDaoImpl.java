package com.inditex.ecom.appwebcustomerloyaltyengine.mdb.dao.impl;

import java.math.BigDecimal;
import java.util.List;

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
import com.inditex.ecom.appwebcustomerloyaltyengine.mdb.dao.GestionPedidosMQDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.EstadoDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoOperacionDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionClientesDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionDescuentosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionRecursosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion de Hibernate de GestionDescuentosDao: nos permitira realizar cualquier operacion sobre las
 * tablas relacionadas con descuentos: LINEA_DESCUENTO, TIPO_LINEA_PEDIDO, ESTADO_DESCUENTO, DETALLE_DESCUENTO, DESCUENTO, OPERACION_DESCUENTO,
 * TIPO_OPERACION_DESCUENTO.
 */
@Repository("gestionPedidosMQDao")
@Transactional(propagation = Propagation.MANDATORY)
public class GestionPedidosMQDaoImpl implements GestionPedidosMQDao {

    /** The Constant LOG. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestionPedidosMQDaoImpl.class);
    
	/** The session. */
	private Session session;

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/** The gestion descuentos dao. */
	@Autowired
	private GestionDescuentosDao gestionDescuentosDao;

	/** The gestion clientes dao. */
	@Autowired
	private GestionClientesDao gestionClientesDao;

	/** The gestion tarjetas dao. */
	@Autowired
	private GestionRecursosDao gestionRecursosDao;

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
	 * ACCIONES DE PERSISTIR PEDIDOS MQ
	 * 
	 ******************************************************************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED, readOnly = false)
	public Boolean persistirCaptura(ClienteDto cliente, DescuentoDto descuento, List<RecursoDto> recursoList, OperacionDescuentoDto operacionDescuento, List<OperacionRecursoDto> operacionRecursoList, List<LineaDescuentoDto> lineas, BigDecimal puntos, String UUID, Short estadoDescuento) throws TimeoutException, NumeroSerieException {
	    LOGGER.trace(String.format("Persistimos la captura/cancelacion/devolucion."));
	    
		try {
			session = sessionFactory.getCurrentSession();

			// Si el cliente se encuentra dado de baja, no incrementamos su numero de serie
			if (cliente.getFechaHoraBaja() == null) {
			    gestionClientesDao.actualizarCliente(cliente);
			}
			
			// Actualizamos el estadoDescuento de la LINEA_DESCUENTO y del DESCUENTO
			if (lineas != null && lineas.size() > 0) {
			    gestionDescuentosDao.actualizarDescuento(descuento, estadoDescuento);
			    
			    for (LineaDescuentoDto linea : lineas) {
			        if (operacionDescuento.getIdTipoOperacionDescuento().compareTo(TipoOperacionDescuento.CAPTURA.getValue()) == 0) {
			            gestionDescuentosDao.actualizarLineaDescuentoCaptura(descuento, linea, estadoDescuento);
			            
			        } else if (operacionDescuento.getIdTipoOperacionDescuento().compareTo(TipoOperacionDescuento.DEVOLUCION.getValue()) == 0) {
			            if (gestionDescuentosDao.obtenerDetalleDescuento(descuento.getIdDescuento(), linea.getPartNumber()).getNumeroUnidades() != linea.getNumeroUnidades()) {
			                gestionDescuentosDao.actualizarLineaDescuentoDevolucion(descuento, linea, estadoDescuento);
			            } else {
	                         gestionDescuentosDao.actualizarLineaDescuentoDevolucion(descuento, linea, EstadoDescuento.FINALIZADO.getValue());
			            }
			            
			        } else  {
			            gestionDescuentosDao.actualizarLineaDescuento(descuento, linea, estadoDescuento);
			        }
			    }
			} else {
			    if (operacionDescuento.getIdTipoOperacionDescuento().compareTo(TipoOperacionDescuento.CAPTURA.getValue()) == 0) {
			        gestionDescuentosDao.actualizarDescuento(descuento, EstadoDescuento.CAPTURADO.getValue());
                    gestionDescuentosDao.actualizarLineaDescuentoCaptura(descuento, EstadoDescuento.CAPTURADO.getValue());
                    
                } else if (operacionDescuento.getIdTipoOperacionDescuento().compareTo(TipoOperacionDescuento.DEVOLUCION.getValue()) == 0) {
                    gestionDescuentosDao.actualizarDescuento(descuento, EstadoDescuento.FINALIZADO.getValue());
                    gestionDescuentosDao.actualizarLineaDescuentoDevolucion(descuento, EstadoDescuento.FINALIZADO.getValue());
                    
                } else  {
                    gestionDescuentosDao.actualizarDescuento(descuento, EstadoDescuento.CANCELADO.getValue());
                    gestionDescuentosDao.actualizarLineasDescuento(descuento, EstadoDescuento.CANCELADO.getValue());
                }
			}

			for (RecursoDto recurso : recursoList) {
			    // Si la tarjeta se encuentra dada de baja, no incrementamos su numero de serie
			    if (recurso.getFechaHoraBaja() == null) {
			        if (operacionDescuento.getIdTipoOperacionDescuento().compareTo(TipoOperacionDescuento.CAPTURA.getValue()) == 0) {
                        gestionRecursosDao.actualizarValorRecurso(recurso, recurso.getValorRecurso().subtract(puntos));
                        
                    } else if (operacionDescuento.getIdTipoOperacionDescuento().compareTo(TipoOperacionDescuento.DEVOLUCION.getValue()) == 0) {
			            gestionRecursosDao.actualizarValoresRecurso(recurso, recurso.getValorRecurso().add(puntos), recurso.getValorDisponibleRecurso().add(puntos));
			            
			        } else {
			            gestionRecursosDao.actualizarValorDisponibleRecurso(recurso, recurso.getValorDisponibleRecurso().add(puntos));
			        }
			    }
			}

			session.persist(operacionDescuento);

			for (OperacionRecursoDto operacionRecurso : operacionRecursoList) {
				session.persist(operacionRecurso);
			}
		} catch (Exception e) {
			throw new NumeroSerieException(UUID);
		}

		return true;
	}

}
