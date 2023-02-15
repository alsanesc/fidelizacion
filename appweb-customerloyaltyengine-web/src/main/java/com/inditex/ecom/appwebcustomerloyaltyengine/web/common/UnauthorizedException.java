package com.inditex.ecom.appwebcustomerloyaltyengine.web.common;

import org.springframework.validation.Errors;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.ErrorAware;


/**
 * Conflict exception.
 * 
 * @since 19/01/12 17:21
 */
public class UnauthorizedException extends Exception implements ErrorAware {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * The errors.
     */
    private final Errors errors;

    /**
     * Constructor.
     */
    public UnauthorizedException() {
        super();
        this.errors = null;
    }

    /**
     * Constructor.
     * 
     * @param message
     *            the message
     */
    public UnauthorizedException(final String message) {
        super(message);
        this.errors = null;
    }

    /**
     * Constructor.
     * 
     * @param errors
     *            the errors.
     */
    public UnauthorizedException(final Errors errors) {
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
    public UnauthorizedException(final String message, final Errors errors) {
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
