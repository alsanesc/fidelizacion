package com.inditex.ecom.appwebcustomerloyaltyengine.model.api.impl.cliente.test;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Cliente;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.GestionClientesService;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.impl.GestionClientesServiceImpl;

/**
 * The Class GestionClientesTest.
 */
@ContextConfiguration(locations = { "classpath*:/spring-test.xml, classpath*:/spring-validator-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class GestionClientesTest extends TestCase {

	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger(GestionClientesTest.class);
	
	/** The gestion clientes service. */
	private GestionClientesService gestionClientesService;
	
	/** The application context. */
	private ApplicationContext applicationContext = null;
	
	@Before
	public void setUp(){
		applicationContext = new ClassPathXmlApplicationContext("spring-test.xml");
		
		gestionClientesService = applicationContext.getBean("gestionClientesService", GestionClientesServiceImpl.class);
	}
	
//	@Test
	public void altaClienteTest() {
		LOGGER.trace("Empezamos el metodo: altaClienteTest");
		Cliente cliente = createCliente();
		LOGGER.trace("Cliente creado: ".concat(cliente.toStringJSON()));
		
		
		Assert.assertEquals(gestionClientesService.altaCliente(cliente, "UUID").getCodResultado().toString(), "0");
	}
	
//	@Test
	public void bajaClienteNoExistenteTest() {
		LOGGER.trace("Empezamos el metodo: bajaClienteTest --> Cliente No Existente <--");
		try {
			Assert.assertNotNull(gestionClientesService.bajaCliente("86"));
		} catch (TimeoutException e) {
			LOGGER.error("Error debido a: ".concat(e.getMessage()));
		}
	}
	
	@Test
	public void bajaClienteDadoBajaOKTest() {
		LOGGER.trace("Empezamos el metodo bajaClienteDadoBajaTest --> OK");
		try {
			Assert.assertNotNull(gestionClientesService.bajaCliente("93").getFechaBaja());
		} catch (TimeoutException e) {
			LOGGER.error("Error debido a: ".concat(e.getMessage()));
		}
	}
	
//	@Test
	public void bajaClienteDadoBajaNotOKTest() {
		LOGGER.trace("Empezamos el metodo bajaClienteDadoBajaTest --> NOT OK");
		try {
			Assert.assertNull(gestionClientesService.bajaCliente("92").getFechaBaja());
		} catch (TimeoutException e) {
			LOGGER.error("Error debido a: ".concat(e.getMessage()));
		}
	}

//	@Test
	public void movimientosTest() {
		LOGGER.trace("Empezamos el metodo: movimientosTest");
		 Assert.assertNotNull(gestionClientesService.movimientos("28"));
	}
	
	/**
     * ****************************************************************************************************************************
     * 
     * ACCIONES PRIVADAS
     * 
     * ****************************************************************************************************************************.
     */
	
	private Cliente createCliente() {
		Cliente cliente = new Cliente();
		
		cliente.setIdCadena(1);
		cliente.setIdPais(11);
		cliente.setCodPais("ES");
		cliente.setIdClienteEcommerce("123456789");
		
		
		return cliente;
	}

}
