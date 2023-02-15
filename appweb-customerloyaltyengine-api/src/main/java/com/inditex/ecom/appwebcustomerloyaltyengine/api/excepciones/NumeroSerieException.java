package com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones;

/**
 * The Class NumeroSerieException.
 */
public class NumeroSerieException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new numero serie exception.
     * 
     * @param e
     *            the e
     */
    public NumeroSerieException(Throwable e) {
        super(e);
    }

    /**
     * Instantiates a new numero serie exception.
     * 
     * @param id
     *            the id
     */
    public NumeroSerieException(final String id) {
        super("NUMERO DE SERIE INCORRECTO = ".concat(id));
    }

    /**
     * Instantiates a new numero serie exception.
     * 
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public NumeroSerieException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
