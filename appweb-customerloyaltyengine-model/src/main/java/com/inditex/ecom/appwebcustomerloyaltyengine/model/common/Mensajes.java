package com.inditex.ecom.appwebcustomerloyaltyengine.model.common;

/**
 * The Class Mensajes.
 */
public class Mensajes {

    /** The Constant MENSAJE_UUID. */
    public static final String MENSAJE_UUID = "[%s]";

    /** The Constant INSTALACION. */
    public static final String INSTALACION = "%s:%s";
    
    /** The Constant SEQUENCE. */
    public static final String SEQUENCE = "SELECT NEXT VALUE FOR %s.%s FROM %s.SYSDUMMY1";

    /**
     * The Class MensajesDescuento.
     */
    public static class MensajesDescuento {

        /** The Constant DESCUENTO_NO_DADO_ALTA. */
        public static final String DESCUENTO_NO_DADO_ALTA = "Descuento no esta dado de Alta";

        /** The Constant DESCUENTO_NO_CAPTURADO. */
        public static final String DESCUENTO_NO_CAPTURADO = "El descuento no esta en estado CAPTURADO";
    }

    /**
     * The Class Errores.
     */
    public static class Errores {

        /** The Constant OBJETO_JSON_NULO. */
        public static final String OBJETO_JSON_NULO = "Object JSON nulo.";

        /** The Constant NUMERO_MAXIMO_REINTENTOS. */
        public static final String NUMERO_MAXIMO_REINTENTOS = "SE HA SUPERADO EL NUMERO MAXIMO DE REINTENTOS POR AUTORIZACION DE DESCUENTO (%s)";

        /** The Constant CONEXION_BLOQUEADA. */
        public static final String CONEXION_BLOQUEADA = "FILAS BLOQUEADAS POR OTRA CONEXION ";

        /** The Constant NO_EXISTE_TIPO_MENSAJE. */
        public static final String NO_EXISTE_TIPO_MENSAJE = "No existe ningun tipo para el mensaje.";

        /** The Constant NO_MENSAJE_TEXTMESSAGE. */
        public static final String NO_MENSAJE_TEXTMESSAGE = "No es un mensaje tipo TextMessage.";

        /** The Constant ERROR_EN_COLA. */
        public static final String ERROR_EN_COLA = "Error en la cola: %s";

        /** The Constant ERROR_MAXIMO_NUMERO_REINTENTOS. */
        public static final String ERROR_MAXIMO_NUMERO_REINTENTOS = "ERROR AL OBTENER EL PARAMETRO 'maximoNumeroReintentos'. SE INICIALIZA A %s.";

        /** The Constant DESCUENTO_NO_AUTORIZADO_CAPTURADO. */
        public static final String DESCUENTO_NO_AUTORIZADO_CAPTURADO = "El descuento no esta en estado AUTORIZADO/CAPTURADO";
        
        /** The Constant DESCUENTO_NO_AUTORIZADO. */
        public static final String DESCUENTO_NO_AUTORIZADO = "El descuento no esta en estado AUTORIZADO";
        
        /** The Constant DESCUENTO_NO_ASOCIADO_PEDIDO. */
        public static final String DESCUENTO_NO_ASOCIADO_PEDIDO = "El Descuento no esta asociado al Pedido";
        
        /** The Constant PENDIENTE_CAPTURAR_MAYOR_AUTORIZADO. */
        public static final String PENDIENTE_CAPTURAR_MAYOR_AUTORIZADO = "El pendiente de capturar es mayor que el autorizado, para el pedido %s, y para el descuento %s.";
    }

    /**
     * The Class Warnings.
     */
    public static class Warnings {

        /** The Constant CLIENTE_DADO_DE_BAJA. */
        public static final String CLIENTE_DADO_DE_BAJA = "El cliente %s con la tarjeta %s:%s se encuentran dados de baja.";

        /** The Constant NO_SE_PUEDE_CUBRIR_CAPTURA. */
        public static final String NO_SE_PUEDE_CUBRIR_CAPTURA = "%s - No se puede cubrir la captura";

        /** The Constant NO_SE_PUEDE_CUBRIR_DEVOLUCION. */
        public static final String NO_SE_PUEDE_CUBRIR_DEVOLUCION = "%s - No se puede cubrir la devolucion.";

        /** The Constant PENDIENTE_CAPTURAR_CERO. */
        public static final String PENDIENTE_CAPTURAR_CERO = "%s - Pendiente de capturar con valor a 0.";

    }

}
