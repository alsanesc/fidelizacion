package com.inditex.ecom.appwebcustomerloyaltyengine.model.api.impl.pedidos.test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.DatosDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.LineaPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Pedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.impl.GestionPedidosServiceImpl;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;

/**
 * The Class GestionPedidosTest.
 */
@ContextConfiguration(locations = { "classpath*:/spring-test.xml, classpath*:/spring-validator-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class GestionPedidosTest extends TestCase {

	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger(GestionPedidosTest.class);

	/** The gestion pedidos service. */
	private GestionPedidosServiceImpl gestionPedidosService;
	
	/** The application context. */
	private ApplicationContext applicationContext = null;
	
	@Before
	public void setUp(){
		applicationContext = new ClassPathXmlApplicationContext("spring-test.xml");
		
		gestionPedidosService = applicationContext.getBean("gestionPedidosService", GestionPedidosServiceImpl.class);
	}
	
//	@Test
	public void generarDescuentoClienteNoExistenteTest() {
		LOGGER.trace("Empezamos el metodo generarDescuentoClienteNoExistenteTest");
		
		Pedido pedido = createPedidoClienteNoExistente();
		
		LOGGER.trace("Datos del pedido".concat(pedido.toStringJSON()));
		
		Assert.assertEquals("5", gestionPedidosService.calcularDescuento(pedido, "12U56O").getCodResultado().toString());
	}
	
//	@Test
	public void generarDescuentoConfiguracionNoValidaTest() {
		LOGGER.trace("Empezamos el metodo generarDescuentoConfiguracionNoValidaTest");
		
		Pedido pedido = createPedidoConfiguracionNoValida();
		
		LOGGER.trace("Datos del pedido".concat(pedido.toStringJSON()));
		
		Assert.assertEquals("3", gestionPedidosService.calcularDescuento(pedido, "12U56O").getCodResultado().toString());
	}
	
//	@Test
	public void generarDescuento() {
		LOGGER.trace("Empezamos el metodo generarDescuento");
		
		Pedido pedido = createPedido();
		
		LOGGER.trace("Datos del pedido".concat(pedido.toStringJSON()));
		
		Assert.assertEquals("0", gestionPedidosService.calcularDescuento(pedido, "12U56O").getCodResultado().toString());
	}

//	@Test
	public void autorizarDescuento() {
		LOGGER.trace("Empezamos el metodo autorizarDescuento");
		
		try {
			Assert.assertEquals("2", gestionPedidosService.autorizarDescuentos("1", "PRUEBA", "U45O98P").getCodResultado().toString());
		} catch (TimeoutException e) {
			LOGGER.error("Error debido a: ".concat(e.getMessage()));
		}
	}
	
//	@Test
	public void consultarDescuentoDescuentoTest() {
		LOGGER.trace("Empezamos el metodo consultarDescuentoDescuentoTest");
		
		DescuentoDto descuento = gestionPedidosService.obtenerDescuento("100");
        List<LineaDescuentoDto> lineaDescuento = gestionPedidosService.obtenerLineaDescuento(new BigInteger("100"));
        ClienteDto cliente = gestionPedidosService.obtenerCliente(descuento.getIdCliente().getIdCliente().toString());
        
        Assert.assertNotNull(gestionPedidosService.consultaEstadoDescuento(lineaDescuento, descuento, cliente));
	}
	
	@Test
	public void consultarDescuentoPedidoTest() {
		LOGGER.trace("Empezamos el metodo consultarDescuentoPedidoTest");
		
		List<LineaDescuentoDto> lineaDescuento = gestionPedidosService.obtenerLineaDescuento("159");
        DescuentoDto descuento = gestionPedidosService.obtenerDescuento(lineaDescuento.get(0).getIdDescuento().getIdDescuento().toString());
        ClienteDto cliente = gestionPedidosService.obtenerCliente(descuento.getIdCliente().getIdCliente().toString());
        
        Assert.assertNotNull(gestionPedidosService.consultaEstadoDescuento(lineaDescuento, descuento, cliente));
	}
	
	/**
     * ****************************************************************************************************************************
     * 
     * ACCIONES PRIVADAS
     * 
     * ****************************************************************************************************************************.
     */
	
	private Pedido createPedidoConfiguracionNoValida() {
		Pedido pedido = new Pedido();
		
		pedido.setIdPedido(366);
		pedido.setIdClienteDescuentos("123456789");
		pedido.setIdClienteEcommerce("28");
		pedido.setIdTienda(144);
		pedido.setIdTiendaAS400(144);
		pedido.setIdCadena(1);
		pedido.setIdPais(11);
		pedido.setCodPais("PT");
		pedido.setLineasPedido(createLineaPedido());
		pedido.setImporteTotalPedido(new BigDecimal(12.30));
		
		return pedido;
	}
	
	private Pedido createPedidoClienteNoExistente() {
		Pedido pedido = new Pedido();
		
		pedido.setIdPedido(366);
		pedido.setIdClienteDescuentos("123456789");
		pedido.setIdClienteEcommerce("123");
		pedido.setIdTienda(144);
		pedido.setIdTiendaAS400(144);
		pedido.setIdCadena(1);
		pedido.setIdPais(11);
		pedido.setCodPais("ES");
		pedido.setLineasPedido(createLineaPedido());
		pedido.setImporteTotalPedido(new BigDecimal(12.30));
		
		return pedido;
	}
	
	private Pedido createPedido() {
		Pedido pedido = new Pedido();
		
		pedido.setIdPedido(366);
		pedido.setIdClienteDescuentos("28");
		pedido.setIdClienteEcommerce("cliente@example.com");
		pedido.setIdTienda(144);
		pedido.setIdTiendaAS400(144);
		pedido.setIdCadena(1);
		pedido.setIdPais(11);
		pedido.setCodPais("ES");
		pedido.setLineasPedido(createLineaPedido());
		pedido.setImporteTotalPedido(new BigDecimal(12.30));
		pedido.setDescuentosSolicitados(createDescuentosSolicitados());
		
		return pedido;
	}

	private List<DatosDescuento> createDescuentosSolicitados() {
		List<DatosDescuento> datosDescuentosList = new ArrayList<DatosDescuento>();
		DatosDescuento datosDescuento = new DatosDescuento();
		
		datosDescuento.setIdDescuento(1);
		datosDescuento.setPorcentajeDescuento(new BigDecimal(12.52));
		
		datosDescuentosList.add(datosDescuento);
		
		return datosDescuentosList;
	}

	private List<LineaPedido> createLineaPedido() {
		List<LineaPedido> lineaPedidoList = new ArrayList<LineaPedido>();
		LineaPedido lineaPedido = new LineaPedido();
		
		lineaPedido.setIdLineaPedido(new BigInteger("2"));
		lineaPedido.setPartNumber("A12F56T89");
		lineaPedido.setCantidadSolicitada(2);
		lineaPedido.setImporteUnitario(new BigDecimal(25.95));
		lineaPedido.setImporteTotal(new BigDecimal(51.9));
		lineaPedido.setEsGastosEnvio(true);
		
		lineaPedidoList.add(lineaPedido);
		
		return lineaPedidoList;
	}
	
}
