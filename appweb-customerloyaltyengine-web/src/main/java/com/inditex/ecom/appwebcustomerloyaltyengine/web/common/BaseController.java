package com.inditex.ecom.appwebcustomerloyaltyengine.web.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.BadRequestException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.EstadoIncorrectoException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.NumeroSerieException;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;


/**
 * Clase Base para los Controler. Aqui se definiran los manejadores para los distintos errores que puede dar el WebService. Todos devolveran objetos
 * de la clase ErrorResult donde estara el error mas detallado. Tambien se podria enviar los errores con response.sendError(Codigo HTTP, Mensaje);
 */
public class BaseController {

    /** Codigo de error. */
    public final static int SC_UNPROCESSABLE_ENTITY = 422;

    /** The Constant BAD_REQUEST_MESSAGE_CODE. */
    private final static String BAD_REQUEST_MESSAGE_CODE = "Error en la request";

    /** Logger. */
    private final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    /**
     * Metodo que maneja los errores de la peticion.
     * 
     * @param e
     *            the e
     * @param response
     *            the response
     * @return the error result
     */
    @ExceptionHandler({ BadRequestException.class, MissingServletRequestParameterException.class, HttpMessageNotReadableException.class,
            DataIntegrityViolationException.class, NumeroSerieException.class, EstadoIncorrectoException.class, TimeoutException.class })
    public @ResponseBody
    ErrorResult handleBadRequestException(Exception e, HttpServletResponse response) {

        LOG.error("BAD REQUEST 400: ", e);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        return ErrorResultConverter.toErrorResult(BAD_REQUEST_MESSAGE_CODE.concat(": ").concat(e.getMessage()));
    }

    /**
     * Metodo que trata los errores de tipo HttpRequestMethodNotSupportedException.
     * 
     * @param e
     *            error
     * @param response
     *            servlet de respuesta
     * @return ErrorResult
     */
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public @ResponseBody
    void handleNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletResponse response) {

        try {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } catch (IOException e1) {
            LOG.error("FATAL ERROR lanzando error 405");
        }
    }

    /**
     * Metodo que trata los errores de tipo HttpMediaTypeNotAcceptableException.
     * 
     * @param e
     *            error
     * @param response
     *            servlet de respuesta
     * @return ErrorResult
     */
    @ExceptionHandler({ HttpMediaTypeNotAcceptableException.class })
    public @ResponseBody
    void handleNotAcceptableException(HttpMediaTypeNotAcceptableException e, HttpServletResponse response) {

        try {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        } catch (IOException e1) {
            LOG.error("FATAL ERROR lanzando error 406");
        }
    }

}
