package com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones;

/**
 * The Class EstadoIncorrectoException.
 */
public class EstadoIncorrectoException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new estado incorrecto exception.
     * 
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public EstadoIncorrectoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new estado incorrecto exception.
     * 
     * @param message
     *            the message
     */
    public EstadoIncorrectoException(String message) {
        super(message);
    }

    /**
     * Instantiates a new estado incorrecto exception.
     * 
     * @param cause
     *            the cause
     */
    public EstadoIncorrectoException(Throwable cause) {
        super(cause);
    }

}
