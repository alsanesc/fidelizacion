package com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones;

import org.springframework.validation.Errors;

/**
 * Conflict exception.
 * 
 * @since 19/01/12 17:21
 */
public class BadRequestException extends Exception implements ErrorAware {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * The errors.
     */
    private final Errors errors;

    /**
     * Constructor.
     */
    public BadRequestException() {
        super();
        this.errors = null;
    }

    /**
     * Constructor.
     * 
     * @param message
     *            the message
     */
    public BadRequestException(final String message) {
        super(message);
        this.errors = null;
    }

    /**
     * Constructor.
     * 
     * @param errors
     *            the errors.
     */
    public BadRequestException(final Errors errors) {
        this.errors = errors;
    }

    /**
     * Constructor.
     * 
     * @param message
     *            the message
     * @param errors
     *            the errors.
     */
    public BadRequestException(final String message, final Errors errors) {
        super(message);
        this.errors = errors;
    }

    /**
     * Retrieves the errors.
     * 
     * @return the errors.
     */
    public Errors getErrors() {
        return errors;
    }
}
