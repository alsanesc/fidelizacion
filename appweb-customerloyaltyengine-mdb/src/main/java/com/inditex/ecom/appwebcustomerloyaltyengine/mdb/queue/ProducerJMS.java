package com.inditex.ecom.appwebcustomerloyaltyengine.mdb.queue;


import java.util.HashMap;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * The Class ProducerJMS.
 */
@Component("producerJMS")
public class ProducerJMS {
    
    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(ProducerJMS.class);
    
     /** The JMS queue. */
     private List<Queue> listaColas; // Destination
     
     /** The mapa colas. */
     private HashMap<String,Queue> mapaColas = new HashMap<String,Queue>();

    /**
     * Sets the lista colas.
     *
     * @param listaColas the new lista colas
     */
    public void setListaColas(List<Queue> listaColas) {
        
        for (Queue cola : listaColas)
            {
            try {
                logger.info("Alta de cola '" + cola.getQueueName() + "' para envios");
                mapaColas.put(cola.getQueueName(),cola);
                } catch (JMSException e) {
                e.printStackTrace();
                }
            }
    }


    /** The jms template. */
    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;

    /**
     * Sets the jMS factory.
     *
     * @param jmsTemplate the new jMS template
     */
    
    public void setJMSTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    
    /**
     * Send message.
     *
     * @throws JMSException the jMS exception
     */
    public void sendMessage(String nombreCola, String mensaje) throws JMSException {
        
        logger.info("ProducerJMS.sendMessage --> nombreCola: " + nombreCola);
        
        if (mapaColas.get(nombreCola) == null)
        {
            //la cola indicada no esta configurada
            throw new JMSException("La cola indicada no esta configurada: " + nombreCola);
        }
        sendAcrossJmsTemplate(mapaColas.get(nombreCola), mensaje);// Usando jmsTemplate
    }
    
    
    /**
     * Send across jms template.
     *
     * @param queue the queue
     * @throws JMSException the jMS exception
     */
    private void sendAcrossJmsTemplate(Queue queue, final String message) throws JMSException {
        
    logger.info("ProducerJMS.sendMessage --> queue: " + queue);
        
    if (queue != null) {
    jmsTemplate.send(queue, new MessageCreator() {
        public Message createMessage(Session session)
                throws JMSException {
            TextMessage tm = session.createTextMessage();
            tm.setText(message);
            tm.setJMSCorrelationID("001-JMS-C-ID_JMSTEMPLATE");
            //tm.setJMSMessageID("001-JMS-M-ID_JMSTEMPLATE");
            tm.setStringProperty("AppOrigen","EjemploColasMQ_con_jmsTemplate");
            return tm;
        }
    });
     } else {
    throw new JMSException("Destination no found.");
     }
     
    }
}
