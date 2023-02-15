package com.inditex.ecom.appwebcustomerloyaltyengine.model.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class Constantes.
 */
public class Constantes {

    /**
     * The Class Comunes.
     */
    public static class Comunes {

        /** The Constant RETURN. */
        public static final String RETURN = "\n";

        /** The Constant COMMA. */
        public static final String COMMA = ",";

        /** The Constant RIGHT_CURLY_BRACKETS. */
        public static final String RIGHT_CURLY_BRACKETS = "}";

        /** The Constant LEFT_CURLY_BRACKETS. */
        public static final String LEFT_CURLY_BRACKETS = "{";
        
        /** The Constant SLASH. */
        public static final String COLON = ":";
        
        /** The Constant UUID. */
        public static final String UUID = "UUID";
        
        /** The Constant CANCEL_OPERATION_TYPE. */
        public static final String CANCEL_OPERATION_TYPE = "CANCEL";

        /** The Constant REFUND_OPERATION_TYPE. */
        public static final String REFUND_OPERATION_TYPE = "REFUND";

        /** The Constant CAPTURE_OPERATION_TYPE. */
        public static final String CAPTURE_OPERATION_TYPE = "CAPTURE";

        /** The Constant AUTHORIZE_OPERATION_TYPE. */
        public static final String AUTHORIZE_OPERATION_TYPE = "AUTHORIZE";
    }
    
    /**
     * ******************************************************************************************************************************************
     * CLASE CON LOS VALORES BASE DE DATOS.
     * ******************************************************************************************************************************************
     */
    public static final class DBSchema {

        /** The Constant DB_MAIN_SCHEMA. */
        public static final String DB_MAIN_SCHEMA = "DESCUENTOS";

        /** The Constant DB_SYSTEM_SCHEMA. */
        public static final String DB_SYSTEM_SCHEMA = "SYSIBM";
    }
    
    /**
     * ******************************************************************************************************************************************
     * CLASE CON LOS NOMBRES DE LAS SECUENCIAS.
     * ******************************************************************************************************************************************
     */
    public static final class Secuencias {

        /** The Constant SQ_DATOS_PAGO. */
        public static final String SQ_CLIENTE = "SQ_CLIENTE";
        
        /** The Constant SQ_DESCUENTO. */
        public static final String SQ_DESCUENTO = "SQ_DESCUENTO";
        
        /** The Constant SQ_DETALLE_DESCUENTO. */
        public static final String SQ_DETALLE_DESCUENTO = "SQ_DETALLE_DESCUENTO";
        
        /** The Constant SQ_LINEA_DESCUENTO. */
        public static final String SQ_LINEA_DESCUENTO = "SQ_LINEA_DESCUENTO";
        
        /** The Constant SQ_OPERACION_DESCUENTO_STRING. */
        public static final String SQ_OPERACION_DESCUENTO = "SQ_OPERACION_DESCUENTO";
        
        /** The Constant SQ_OPERACION_RECURSO. */
        public static final String SQ_OPERACION_RECURSO = "SQ_OPERACION_RECURSO";
        
        /** The Constant SQ_RECURSO. */
        public static final String SQ_RECURSO = "SQ_RECURSO";
    }

    /**
     * ******************************************************************************************************************************************
     * VALORES DE ESTADO_CLIENTE.
     * ******************************************************************************************************************************************
     */
    public enum EstadoCliente {

        /** The estado cliente inactivo. */
        INACTIVO((short) 0),

        /** The estado cliente activo. */
        ACTIVO((short) 1);

        /** The value. */
        private final short value;

        /**
         * Gets the value.
         * 
         * @return the value
         */
        public short getValue() {
            return value;
        }

        /**
         * Instantiates a new tipo datos pago.
         * 
         * @param value
         *            the value
         */
        private EstadoCliente(short value) {
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return name();
        }

        /** The Constant lookup. */
        private static final Map<Short, EstadoCliente> lookup = new HashMap<Short, EstadoCliente>();

        static {
            for (EstadoCliente pc : EnumSet.allOf(EstadoCliente.class)) {
                lookup.put(pc.getValue(), pc);
            }
        }

        /**
         * Get.
         * 
         * @param value
         *            the value
         * @return the tipo operacion pedido
         */
        public static EstadoCliente get(Short value) {
            return lookup.get(value);
        }
    }

    /**
     * ******************************************************************************************************************************************
     * VALORES DE ESTADO_DESCUENTO.
     * ******************************************************************************************************************************************
     */
    public enum EstadoDescuento {

        /** The estado descuento pendiente. */
        PENDIENTE((short) 0),

        /** The estado descuento autorizado. */
        AUTORIZADO((short) 1),

        /** The estado descuento capturado. */
        CAPTURADO((short) 2),

        /** The estado descuento cancelado. */
        CANCELADO((short) 3),

        /** The estado descuento finalizado. */
        FINALIZADO((short) 4),

        /** The estado descuento expirado. */
        EXPIRADO((short) 5);

        /** The value. */
        private final short value;

        /**
         * Gets the value.
         * 
         * @return the value
         */
        public short getValue() {
            return value;
        }

        /**
         * Instantiates a new tipo datos pago.
         * 
         * @param value
         *            the value
         */
        private EstadoDescuento(short value) {
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return name();
        }

        /** The Constant lookup. */
        private static final Map<Short, EstadoDescuento> lookup = new HashMap<Short, EstadoDescuento>();

        static {
            for (EstadoDescuento pc : EnumSet.allOf(EstadoDescuento.class)) {
                lookup.put(pc.getValue(), pc);
            }
        }

        /**
         * Get.
         * 
         * @param value
         *            the value
         * @return the tipo operacion pedido
         */
        public static EstadoDescuento get(Short value) {
            return lookup.get(value);
        }
    }

    /**
     * ******************************************************************************************************************************************
     * VALORES DE ESTADO_RECURSO.
     * ******************************************************************************************************************************************
     */
    public enum EstadoRecurso {

        /** The estado recurso inactivo. */
        INACTIVO((short) 0),

        /** The estado recurso activo. */
        ACTIVO((short) 1);

        /** The value. */
        private final short value;

        /**
         * Gets the value.
         * 
         * @return the value
         */
        public short getValue() {
            return value;
        }

        /**
         * Instantiates a new tipo datos pago.
         * 
         * @param value
         *            the value
         */
        private EstadoRecurso(short value) {
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return name();
        }

        /** The Constant lookup. */
        private static final Map<Short, EstadoRecurso> lookup = new HashMap<Short, EstadoRecurso>();

        static {
            for (EstadoRecurso pc : EnumSet.allOf(EstadoRecurso.class)) {
                lookup.put(pc.getValue(), pc);
            }
        }

        /**
         * Get.
         * 
         * @param value
         *            the value
         * @return the tipo operacion pedido
         */
        public static EstadoRecurso get(Short value) {
            return lookup.get(value);
        }
    }

    /**
     * ******************************************************************************************************************************************
     * VALORES DE TIPO_CLIENTE.
     * ******************************************************************************************************************************************
     */
    public enum TipoCliente {

        /** The tipo cliente ecommerce. */
        CLIENTE_ECOMMERCE((short) 0);

        /** The value. */
        private final short value;

        /**
         * Gets the value.
         * 
         * @return the value
         */
        public short getValue() {
            return value;
        }

        /**
         * Instantiates a new tipo datos pago.
         * 
         * @param value
         *            the value
         */
        private TipoCliente(short value) {
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return name();
        }

        /** The Constant lookup. */
        private static final Map<Short, TipoCliente> lookup = new HashMap<Short, TipoCliente>();

        static {
            for (TipoCliente pc : EnumSet.allOf(TipoCliente.class)) {
                lookup.put(pc.getValue(), pc);
            }
        }

        /**
         * Get.
         * 
         * @param value
         *            the value
         * @return the tipo operacion pedido
         */
        public static TipoCliente get(Short value) {
            return lookup.get(value);
        }
    }

    /**
     * ******************************************************************************************************************************************
     * VALORES DE TIPO_LINEA_PEDIDO.
     * ******************************************************************************************************************************************
     */
    public enum TipoLineaPedido {

        /** The tipo linea pedido articulo. */
        ARTICULO((short) 0),

        /** The tipo linea pedido gastos envio. */
        GASTOS_DE_ENVIO((short) 1);

        /** The value. */
        private final short value;

        /**
         * Gets the value.
         * 
         * @return the value
         */
        public short getValue() {
            return value;
        }

        /**
         * Instantiates a new tipo datos pago.
         * 
         * @param value
         *            the value
         */
        private TipoLineaPedido(short value) {
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return name();
        }

        /** The Constant lookup. */
        private static final Map<Short, TipoLineaPedido> lookup = new HashMap<Short, TipoLineaPedido>();

        static {
            for (TipoLineaPedido pc : EnumSet.allOf(TipoLineaPedido.class)) {
                lookup.put(pc.getValue(), pc);
            }
        }

        /**
         * Get.
         * 
         * @param value
         *            the value
         * @return the tipo operacion pedido
         */
        public static TipoLineaPedido get(Short value) {
            return lookup.get(value);
        }
    }

    /**
     * ******************************************************************************************************************************************
     * VALORES DE TIPO_OPERACION_DESCUENTO.
     * ******************************************************************************************************************************************
     */
    public enum TipoOperacionDescuento {

        /** The tipo operacion descuento autorizacion. */
        AUTORIZACION((short) 0),

        /** The tipo operacion descuento captura. */
        CAPTURA((short) 1),

        /** The tipo operacion descuento devolucion. */
        DEVOLUCION((short) 2),

        /** The tipo operacion descuento cancelacion. */
        CANCELACION((short) 3);

        /** The value. */
        private final short value;

        /**
         * Gets the value.
         * 
         * @return the value
         */
        public short getValue() {
            return value;
        }

        /**
         * Instantiates a new tipo datos pago.
         * 
         * @param value
         *            the value
         */
        private TipoOperacionDescuento(short value) {
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return name();
        }

        /** The Constant lookup. */
        private static final Map<Short, TipoOperacionDescuento> lookup = new HashMap<Short, TipoOperacionDescuento>();

        static {
            for (TipoOperacionDescuento pc : EnumSet.allOf(TipoOperacionDescuento.class)) {
                lookup.put(pc.getValue(), pc);
            }
        }

        /**
         * Get.
         * 
         * @param value
         *            the value
         * @return the tipo operacion pedido
         */
        public static TipoOperacionDescuento get(Short value) {
            return lookup.get(value);
        }
    }

    /**
     * ******************************************************************************************************************************************
     * VALORES DE TIPO_OPERACION_RECURSO.
     * ******************************************************************************************************************************************
     */
    public enum TipoOperacionRecurso {

        /** The tipo operacion recurso autorizacion. */
        AUTORIZACION((short) 0),

        /** The tipo operacion recurso captura. */
        CAPTURA((short) 1),

        /** The tipo operacion recurso devolucion. */
        DEVOLUCION((short) 2),

        /** The tipo operacion recurso cancelacion. */
        CANCELACION((short) 3),

        /** The tipo operacion recurso alta. */
        ALTA((short) 4),

        /** The tipo operacion recurso baja. */
        BAJA((short) 5),

        /** The tipo operacion recurso modificacion valor. */
        MODIFICACION_VALOR((short) 6),

        /** The operacion no definida. */
        OPERACION_NO_DEFINIDA((short) 7);

        /** The value. */
        private final short value;

        /**
         * Gets the value.
         * 
         * @return the value
         */
        public short getValue() {
            return value;
        }

        /**
         * Instantiates a new tipo datos pago.
         * 
         * @param value
         *            the value
         */
        private TipoOperacionRecurso(short value) {
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return name();
        }

        /** The Constant lookup. */
        private static final Map<Short, TipoOperacionRecurso> lookup = new HashMap<Short, TipoOperacionRecurso>();

        static {
            for (TipoOperacionRecurso pc : EnumSet.allOf(TipoOperacionRecurso.class)) {
                lookup.put(pc.getValue(), pc);
            }
        }

        /**
         * Get.
         * 
         * @param value
         *            the value
         * @return the tipo operacion pedido
         */
        public static TipoOperacionRecurso get(Short value) {
            return lookup.get(value);
        }
    }

    /**
     * ******************************************************************************************************************************************
     * VALORES DE TIPO_RECURSO.
     * ******************************************************************************************************************************************
     */
    public enum TipoRecurso {

        /** The tarjeta puntos pullbear. */
        TARJETA_PUNTOS_PULLBEAR((short) 0);

        /** The value. */
        private final short value;

        /**
         * Gets the value.
         * 
         * @return the value
         */
        public short getValue() {
            return value;
        }

        /**
         * Instantiates a new tipo datos pago.
         * 
         * @param value
         *            the value
         */
        private TipoRecurso(short value) {
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return name();
        }

        /** The Constant lookup. */
        private static final Map<Short, TipoRecurso> lookup = new HashMap<Short, TipoRecurso>();

        static {
            for (TipoRecurso pc : EnumSet.allOf(TipoRecurso.class)) {
                lookup.put(pc.getValue(), pc);
            }
        }

        /**
         * Get.
         * 
         * @param value
         *            the value
         * @return the tipo operacion pedido
         */
        public static TipoRecurso get(Short value) {
            return lookup.get(value);
        }
    }
    
    /**
     * ******************************************************************************************************************************************
     * VALORES DE TIPO_VALOR_RECURSO.
     * ******************************************************************************************************************************************
     */
    public enum TipoValorRecurso {
        
        /** The leuros. */
        PUNTOS ((short) 0);
        
        /** The value. */
        private final short value;

        /**
         * Gets the value.
         * 
         * @return the value
         */
        public short getValue() {
            return value;
        }

        /**
         * Instantiates a new tipo datos pago.
         * 
         * @param value
         *            the value
         */
        private TipoValorRecurso(short value) {
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return name();
        }

        /** The Constant lookup. */
        private static final Map<Short, TipoValorRecurso> lookup = new HashMap<Short, TipoValorRecurso>();

        static {
            for (TipoValorRecurso pc : EnumSet.allOf(TipoValorRecurso.class)) {
                lookup.put(pc.getValue(), pc);
            }
        }

        /**
         * Get.
         * 
         * @param value
         *            the value
         * @return the tipo operacion pedido
         */
        public static TipoValorRecurso get(Short value) {
            return lookup.get(value);
        }
    }

    // /////////////////////////////////////////////////////////////////////////////
    // CODIGOS DE RESULTADO
    // ///////////////////////////////////////////////////////////////////////////////

    /** The Constant COD_RESULTADO_CERO. */
    public static final Integer COD_RESULTADO_CERO = 0;

    /** The Constant TEXTO_RESULTADO_CERO. */
    public static final String TEXTO_RESULTADO_CERO = "Operacion realizada correctamente.";

    /** The Constant COD_RESULTADO_UNO. */
    public static final Integer COD_RESULTADO_UNO = 1;

    /** The Constant TEXTO_RESULTADO_UNO. */
    public static final String TEXTO_RESULTADO_UNO = "Saldo de puntos insuficiente.";

    /** The Constant COD_RESULTADO_DOS. */
    public static final Integer COD_RESULTADO_DOS = 2;

    /** The Constant TEXTO_RESULTADO_DOS. */
    public static final String TEXTO_RESULTADO_DOS = "Descuento indicado no esta registrado.";

    /** The Constant COD_RESULTADO_TRES. */
    public static final Integer COD_RESULTADO_TRES = 3;

    /** The Constant TEXTO_RESULTADO_TRES. */
    public static final String TEXTO_RESULTADO_TRES = "No existe configuracion para la cadena/pais del pedido.";

    /** The Constant COD_RESULTADO_CUATRO. */
    public static final Integer COD_RESULTADO_CUATRO = 4;

    /** The Constant TEXTO_RESULTADO_CUATRO. */
    public static final String TEXTO_RESULTADO_CUATRO = "Ya existe el cliente indicado en la peticion.";

    /** The Constant COD_RESULTADO_CINCO. */
    public static final Integer COD_RESULTADO_CINCO = 5;

    /** The Constant TEXTO_RESULTADO_CINCO. */
    public static final String TEXTO_RESULTADO_CINCO = "Cliente no existente en plataforma de descuentos.";

    /** The Constant COD_RESULTADO_SEIS. */
    public static final Integer COD_RESULTADO_SEIS = 6;

    /** The Constant TEXTO_RESULTADO_SEIS. */
    public static final String TEXTO_RESULTADO_SEIS = "Cliente dado de baja en plataforma de descuentos.";

    /** The Constant COD_RESULTADO_SIETE. */
    public static final Integer COD_RESULTADO_SIETE = 7;

    /** The Constant TEXTO_RESULTADO_SIETE. */
    public static final String TEXTO_RESULTADO_SIETE = "Cliente no dispone de tarjeta adecuada en plataforma de descuentos.";

    /** The Constant COD_RESULTADO_OCHO. */
    public static final Integer COD_RESULTADO_OCHO = 8;

    /** The Constant TEXTO_RESULTADO_OCHO. */
    public static final String TEXTO_RESULTADO_OCHO = "Codigo de pedido incorrecto para el codigo de descuento indicado.";

    /** The Constant COD_RESULTADO_NUEVE. */
    public static final Integer COD_RESULTADO_NUEVE = 9;

    /** The Constant TEXTO_RESULTADO_NUEVE. */
    public static final String TEXTO_RESULTADO_NUEVE = "Descuento expirado.";

    /** The Constant COD_RESULTADO_DIEZ. */
    public static final Integer COD_RESULTADO_DIEZ = 10;

    /** The Constant TEXTO_RESULTADO_DIEZ. */
    public static final String TEXTO_RESULTADO_DIEZ = "Recurso externo %s del descuento agotado.";

    /** The Constant COD_RESULTADO_ONCE. */
    public static final Integer COD_RESULTADO_ONCE = 11;

    /** The Constant TEXTO_RESULTADO_ONCE. */
    public static final String TEXTO_RESULTADO_ONCE = "Recurso no especificado";

    /** The Constant COD_RESULTADO_DOCE. */
    public static final Integer COD_RESULTADO_DOCE = 12;

    /** The Constant TEXTO_RESULTADO_DOCE. */
    public static final String TEXTO_RESULTADO_DOCE = "Numero de puntos no especificado";

}
