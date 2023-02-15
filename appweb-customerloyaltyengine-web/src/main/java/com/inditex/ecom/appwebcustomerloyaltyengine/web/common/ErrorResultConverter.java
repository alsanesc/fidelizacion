package com.inditex.ecom.appwebcustomerloyaltyengine.web.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * The Class ErrorResultConverter.
 */
public class ErrorResultConverter {

    /**
     * Instantiates a new error result converter.
     */
    private ErrorResultConverter() {
    }

    /**
     * To error result.
     * 
     * @param message
     *            the message
     * @return the error result
     */
    public static ErrorResult toErrorResult(String message) {
        return new ErrorResult(message);
    }

    /**
     * To error result.
     * 
     * @param errors
     *            the errors
     * @return the error result
     */
    public static ErrorResult toErrorResult(Errors errors) {

        return new ErrorResult(toGlobalErrors(errors),
                toFieldErrorsMap(errors));
    }

    /**
     * To global errors.
     * 
     * @param errors
     *            the errors
     * @return the list
     */
    private static List<String> toGlobalErrors(Errors errors) {

        List<String> globalErrors = new ArrayList<String>();

        if (errors != null) {
            for (ObjectError error : errors.getGlobalErrors()) {
                globalErrors.add(error.getDefaultMessage());
            }
        }
        return globalErrors;
    }

    /**
     * To field errors map.
     * 
     * @param errors
     *            the errors
     * @return the map
     */
    private static Map<String, List<String>> toFieldErrorsMap(Errors errors) {

        Map<String, List<String>> fieldErrorsMap = new HashMap<String, List<String>>();

        if (errors != null) {
            for (FieldError error : errors.getFieldErrors()) {

                List<String> fieldErrors = fieldErrorsMap.get(error.getField());

                if (fieldErrors == null) {
                    fieldErrors = new ArrayList<String>();
                    fieldErrorsMap.put(error.getField(), fieldErrors);
                }

                fieldErrors.add(error.getDefaultMessage());

            }
        }
        return fieldErrorsMap;
    }
}
