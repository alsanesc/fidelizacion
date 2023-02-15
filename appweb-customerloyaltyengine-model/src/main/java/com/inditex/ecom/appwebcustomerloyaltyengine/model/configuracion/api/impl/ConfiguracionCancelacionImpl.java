package com.inditex.ecom.appwebcustomerloyaltyengine.model.configuracion.api.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.Converter;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.LineaPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.OperacionPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.GestionPedidosService;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Cadenas;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Utility;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Comunes;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.EstadoDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoLineaPedido;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoOperacionDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Mensajes.Errores;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.CalculoImportesDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.ConfiguracionCadenasDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionDescuentosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.DescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.LineaDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionDescuentoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyDescuentoDto;

/**
 * Implementacion de {@link ConfiguracionCancelacion}.
 */
@Scope("singleton")
public class ConfiguracionCancelacionImpl implements Runnable {
    
    /** The Constant logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracionCancelacionImpl.class);

    /** The gestion descuentos dao. */
    @Autowired
    private GestionDescuentosDao gestionDescuentosDao;

    /** The configuracion cadenas dao. */
    @Autowired
    private ConfiguracionCadenasDao configuracionCadenasDao;

    /** The gestion pedidos dao. */
    @Autowired
    private GestionPedidosService gestionPedidosService;

    /** The calculo importes dao. */
    @Autowired
    private CalculoImportesDao calculoImportesDao;

    /** The connection factory. */
    @Resource(mappedName = "jms/ECOMAPPWEB-CustomerLoyaltyEngineCF")
    private ConnectionFactory connectionFactory;

    /** The queue. */
    @Resource(mappedName = "jms/ECOMAPPWEB-CustomerLoyaltyEngineQ1")
    private Queue queue;

    /******************************************************************************************************************************
     * 
     * ACCIONES DE CONFIGURACION CANCELACIONES
     * 
     ******************************************************************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void run() {
        List<Cadenas> cadenasList = configuracionCadenasDao.obtenerCadenas();

        for (Cadenas cadena : cadenasList) {
            // Buscamos DESCUENTOS en estado AUTORIZADO o CAPTURADO
            List<DescuentoDto> descuentos = buscarDescuentos(cadena);

            // Buscamos OPERACION_DESCUENTOS en estado AUTORIZADO
            Calendar currentTimeStamp = Calendar.getInstance();

            List<OperacionDescuentoDto> operacionDescuentoList = new ArrayList<OperacionDescuentoDto>();

            for (DescuentoDto descuento : descuentos) {
                operacionDescuentoList = gestionDescuentosDao.obtenerOperacionDescuento(descuento.getIdDescuento(), TipoOperacionDescuento.AUTORIZACION.getValue());
            }
            
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
                
                // Generamos para cada pedido una MQ por el importe PENDIENTE_DE_CAPTURAR
                for (OperacionDescuentoDto operDesc : operacionDescuentoList) {
                    if ((operDesc.getFechaHoraOperacion().getTime() + cadena.getTiempoCancelacion()) < currentTimeStamp.getTimeInMillis()) {
                        // Calculamos el PENDIENTE_DE_CAPTURAR
                        BigDecimal pendienteCapturar = calculoImportesDao.calcularAutorizado(operDesc.getIdDescuento()).subtract(calculoImportesDao.calcularCapturado(operDesc.getIdDescuento()));
                        
                        if (pendienteCapturar.compareTo(BigDecimal.ZERO) > 0) {
                            OperacionPedido operPedido = generarOperacionPedido(operDesc, pendienteCapturar);
                            
                            final String json = Converter.convertToJSON(operPedido);
                            
                            // Creamos un mensaje sencillo de texto
                            message = session.createTextMessage(json);
                            message.setStringProperty(Comunes.UUID, Utility.calcularUUID());
                            // Mediante el productor, enviamos el mensaje
                            producer.send(message);
                        }
                    }
                }
            } catch (JMSException e) {
                LOGGER.error(String.format(Errores.ERROR_EN_COLA, e.getMessage()));
            } finally {
                try {
                    // Cerramos los recursos
                    producer.close();
                    session.close();
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /******************************************************************************************************************************
     * 
     * ACCIONES PRIVADAS DE CONFIGURACION CANCELACIONES
     * 
     ******************************************************************************************************************************/

    /**
     * Buscar descuentos.
     * 
     * @param cadena
     *            the cadena
     * @return the list
     */
    private List<DescuentoDto> buscarDescuentos(Cadenas cadena) {
        List<DescuentoDto> listaDescuentos = new ArrayList<DescuentoDto>();

        listaDescuentos.addAll(gestionDescuentosDao.obtenerDescuentos(EstadoDescuento.AUTORIZADO.getValue(), cadena));

        listaDescuentos.addAll(gestionDescuentosDao.obtenerDescuentos(EstadoDescuento.CAPTURADO.getValue(), cadena));

        return listaDescuentos;
    }

    /**
     * Generar operacion pedido.
     * 
     * @param operDesc
     *            the oper desc
     * @param pendienteCapturar
     *            the pendiente capturar
     * @return the operacion pedido
     */
    private OperacionPedido generarOperacionPedido(OperacionDescuentoDto operDesc, BigDecimal pendienteCapturar) {
        OperacionPedido operPedido = new OperacionPedido();

        operPedido.setTipoOperacion(Comunes.CANCEL_OPERATION_TYPE);
        operPedido.setCodOperacionExterno(operDesc.getCodigoOperacionExterno());

        operPedido.setLineasPedidoAfectadas(generarLineaPedido(operDesc.getIdDescuento()));

        operPedido.setImporteOperacion(pendienteCapturar);

        return operPedido;
    }

    /**
     * Generar linea pedido.
     * 
     * @param idDescuento
     *            the id descuento
     * @return the list
     */
    private List<LineaPedido> generarLineaPedido(PrimaryKeyDescuentoDto idDescuento) {
        List<LineaPedido> lineaPedidoList = new ArrayList<LineaPedido>();

        List<LineaDescuentoDto> lineaDescuentoList = gestionPedidosService.obtenerLineaDescuento(idDescuento, EstadoDescuento.AUTORIZADO.getValue());

        for (LineaDescuentoDto lineaDescuento : lineaDescuentoList) {
            LineaPedido lineaPedido = new LineaPedido();

            lineaPedido.setIdLineaPedido(lineaDescuento.getCodigoLineaPedidoExterno());
            lineaPedido.setPartNumber(lineaDescuento.getPartNumber());
            lineaPedido.setCantidadSolicitada(lineaDescuento.getNumeroUnidades());
            lineaPedido.setImporteUnitario(lineaDescuento.getImporteUnitario());
            lineaPedido.setImporteTotal(lineaDescuento.getImporteTotal());

            if (lineaDescuento.getIdTipoLineaPedido().compareTo(TipoLineaPedido.GASTOS_DE_ENVIO.getValue()) == 0) {
                lineaPedido.setEsGastosEnvio(true);
            } else {
                lineaPedido.setEsGastosEnvio(false);
            }

            lineaPedidoList.add(lineaPedido);
        }

        return lineaPedidoList;
    }

}
