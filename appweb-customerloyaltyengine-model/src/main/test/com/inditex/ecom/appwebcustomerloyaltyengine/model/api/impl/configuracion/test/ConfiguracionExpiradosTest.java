package com.inditex.ecom.appwebcustomerloyaltyengine.model.api.impl.configuracion.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.impl.ConfiguracionExpiradosDaoImpl;

/**
 * The Class ConfiguracionExpiradosTest.
 */
@ContextConfiguration(locations = { "classpath*:/spring-test.xml, classpath*:/spring-validator-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ConfiguracionExpiradosTest extends TestCase {
	
	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger(ConfiguracionExpiradosTest.class);
	
	/** The application context. */
	private ApplicationContext applicationContext = null;
	
	/** The configuracion expirados dao. */
	private ConfiguracionExpiradosDaoImpl configuracionExpiradosDao;
	
	@Before
	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext("spring-test.xml");
		
		configuracionExpiradosDao = applicationContext.getBean("configuracionExpiradosDao", ConfiguracionExpiradosDaoImpl.class);
	}
	
	@Test
	public void expirados() {
		LOGGER.trace("Empezamos el metodo expirados");
	}

}
