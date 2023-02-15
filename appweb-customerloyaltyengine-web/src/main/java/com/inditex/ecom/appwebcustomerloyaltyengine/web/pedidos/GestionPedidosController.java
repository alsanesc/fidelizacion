package com.inditex.ecom.appwebcustomerloyaltyengine.web.pedidos;

import java.util.List;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.AsignacionPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Pedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.BadRequestException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.pedidos.GestionPedidos;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.GestionPedidosService;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Utility;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.Errores;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.ClienteDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.web.common.BaseController;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion del Controlador GestionPedidosController: nos permitira realizar todas las operaciones
 * relacionadas con PEDIDOS (CALCULAR_DESCUENTO, AUTORIZAR_DESCUENTO, ACTUALIZAR_DESCUENTO).
 */
@Controller("gestionDescuentosController")
public class GestionPedidosController extends BaseController implements GestionPedidos {

    /** The Constant LOG. */
    public static final Logger LOGGER = LoggerFactory.getLogger(GestionPedidosController.class);

    /** The gestion pedidos service. */
    @Autowired
    private GestionPedidosService gestionPedidosService;
    
    /** The connection factory. */
    @Resource(mappedName = "jms/ECOMAPPWEB-CustomerLoyaltyEngineCF")
    private ConnectionFactory connectionFactory;
    
    /** The queue. */
    @Resource(mappedName = "jms/ECOMAPPWEB-CustomerLoyaltyEngineQ1")
    private Queue queue;
    
    /******************************************************************************************************************************
     * 
     * ACCIONES DE GESTION PEDIDOS
     * 
     ******************************************************************************************************************************/

    /**
     * {@inheritDoc}
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/rest/v{version}/pedidos", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody
    AsignacionPedido generarDescuentos(final @PathVariable("version") String version, final @RequestBody Pedido pedido) throws BadRequestException {

        final String UUID = String.format(Mensajes.MENSAJE_UUID, Utility.calcularUUID());

        if (pedido != null) {
            LOGGER.trace(String.format("EL IDENTIFICADOR PARA EL PEDIDO %s SERA %s.", pedido.toString(), UUID));

            // Se valida el objeto json de entrada
            Utility.validarJSON(pedido);

            LOGGER.trace(String.format("%s - Pedido: %s", UUID, pedido.toStringJSON()));

            return gestionPedidosService.generarDescuento(pedido, UUID);
        } else {
            throw new BadRequestException(String.format(Errores.OBJETO_JSON_NULO));
        }
    }
   
    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/api/rest/v{version}/pedidos/{idPedido}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Pedido consultarEstadoPedidos(final @PathVariable("version") String version, final @PathVariable("idPedido") String idPedido) {

        String UUID = String.format(Mensajes.MENSAJE_UUID, Utility.calcularUUID());

        LOGGER.trace(String.format("EL IDENTIFICADOR PARA EL PEDIDO %s SERA %s.", idPedido, UUID));

        // Obtenemos el descuento
        DescuentoDto descuento = gestionPedidosService.obtenerDescuentoPorCodigo(idPedido);
        
        // Obtenemos las lineas de pedido a partir asociadas al pedido
        List<LineaDescuentoDto> lineaDescuento = gestionPedidosService.obtenerLineaDescuento(descuento.getIdDescuento());
        
        // Obtenemos el cliente asociado al cliente
        ClienteDto cliente = gestionPedidosService.obtenerCliente(descuento.getIdCliente());

        return gestionPedidosService.consultaEstadoDescuento(lineaDescuento, descuento, cliente);
    }

    
    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/api/rest/v{version}/descuentos/{idDescuento}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Pedido consultarEstadoDescuentos(final @PathVariable("version") String version, final @PathVariable("idDescuento") String idDescuento) {

        String UUID = String.format(Mensajes.MENSAJE_UUID, Utility.calcularUUID());

        LOGGER.trace(String.format("EL IDENTIFICADOR PARA EL DESCUENTO %s SERA %s.", idDescuento, UUID));

        // Obtenemos el descuento
        DescuentoDto descuento = gestionPedidosService.obtenerDescuento(new PrimaryKeyDescuentoDto(idDescuento));
        
        // Obtenemos las lineas de pedido a partir asociadas al pedido
        List<LineaDescuentoDto> lineaDescuento = gestionPedidosService.obtenerLineaDescuento(descuento.getIdDescuento());
        
        // Obtenemos el cliente asociado al cliente
        ClienteDto cliente = gestionPedidosService.obtenerCliente(descuento.getIdCliente());

        return gestionPedidosService.consultaEstadoDescuento(lineaDescuento, descuento, cliente);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/rest/v{version}/sendMessage", method = RequestMethod.POST)
    public void sendMessage(final @PathVariable("version") String version, final @RequestBody String orderOperation) throws JMSException {
        LOGGER.trace("GestionPedidosController.sendMessage");

        // Declaramos la connection, session, productor de mensaje y mensaje, para enviarselo a la cola correspondiente
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        Message message = null;
        
        try {
            connection = connectionFactory.createConnection();
            // Recordar llamar a start() para permitir el envio de mensajes
            connection.start();
            // Creamos una sesion sin transaccionalidad y con envio de acuse automatico
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Creamos el productor a partir de una cola
            producer = session.createProducer(queue);
            // Creamos un mensaje sencillo de texto
            message = session.createTextMessage(orderOperation);
            // Mediante el productor, enviamos el mensaje
            producer.send(message);
        } finally {
            // Cerramos los recursos
            producer.close();
            session.close();
            connection.close();
        }
    }

}
