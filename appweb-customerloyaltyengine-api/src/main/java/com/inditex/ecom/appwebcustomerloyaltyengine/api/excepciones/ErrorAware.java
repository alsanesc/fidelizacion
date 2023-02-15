package com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones;

import org.springframework.validation.Errors;


/**
 * Interfaz para excepciones u operaciones que producen errores.
 * 
 * @since 25/01/12 13:58
 */
public interface ErrorAware {

    /**
     * Recupera los errores.
     * 
     * @return los errores.
     */
    Errors getErrors();

}
