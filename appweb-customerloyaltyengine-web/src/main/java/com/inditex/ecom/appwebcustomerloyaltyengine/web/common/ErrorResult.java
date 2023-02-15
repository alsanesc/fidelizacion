package com.inditex.ecom.appwebcustomerloyaltyengine.web.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase para gestionar los errores.
 */
public class ErrorResult {

    /** The global errors. */
    private List<String> globalErrors;

    /** The field errors map. */
    private Map<String, List<String>> fieldErrorsMap;

    /**
     * Constructor.
     */
    public ErrorResult() {

        globalErrors = new ArrayList<String>();
        fieldErrorsMap = new HashMap<String, List<String>>();

    }

    /**
     * Constructor.
     * 
     * @param message
     *            mesaje que indica el error
     */
    public ErrorResult(String message) {

        globalErrors = new ArrayList<String>();
        fieldErrorsMap = new HashMap<String, List<String>>();
        globalErrors.add(message);

    }

    /**
     * Constructor.
     * 
     * @param globalErrors
     *            lista de mensajes
     * @param fieldErrorsMap
     *            mapa con los campos de errores
     */
    public ErrorResult(List<String> globalErrors, Map<String, List<String>> fieldErrorsMap) {
        this.globalErrors = globalErrors;
        this.fieldErrorsMap = fieldErrorsMap;
    }

    /**
     * Get de la propiedad globalErrors.
     * 
     * @return globalErrors
     */
    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    /**
     * Set de la propiedad globalErrors.
     * 
     * @param globalErrors
     *            the new global errors
     */
    public void setGlobalErrors(List<String> globalErrors) {
        this.globalErrors = globalErrors;
    }

    /**
     * Get de la propieddad fieldErrorsMap.
     * 
     * @return map con los fieldErrorsMap
     */
    public Map<String, List<String>> getFieldErrorsMap() {
        return fieldErrorsMap;
    }

    /**
     * Set de la propiedad fieldErrorsMap.
     * 
     * @param fieldErrorsMap
     *            the field errors map
     */
    public void setFieldErrorsMap(Map<String, List<String>> fieldErrorsMap) {
        this.fieldErrorsMap = fieldErrorsMap;
    }

}
