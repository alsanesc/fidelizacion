package com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones;

/**
 * Timeout exception para el modelo.
 */
public class TimeoutException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new timeout exception.
     *
     * @param e the e
     */
    public TimeoutException(final Throwable e) {
        super(e);
    }

    /**
     * Instantiates a new timeout exception.
     * 
     * @param message
     *            the message
     */
    public TimeoutException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new timeout exception.
     * 
     * @param message
     *            the message
     * @param e
     *            the e
     */
    public TimeoutException(final String message, final Throwable e) {
        super(message, e);
    }
}
