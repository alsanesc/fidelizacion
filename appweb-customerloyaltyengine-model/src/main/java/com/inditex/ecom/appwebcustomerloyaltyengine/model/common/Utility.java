package com.inditex.ecom.appwebcustomerloyaltyengine.model.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.BadRequestException;

/**
 * The Class Utility.
 */
public class Utility {

    /** The Constant LOG. */
    public static final Logger LOG = LoggerFactory.getLogger(Utility.class);

    /**
     * Calcula un UUID para cada llamada que se hace a alguno de los metodos del webservice. Lo calcula haciendo un hash con el algorimo MD5 y
     * codificandolo despues en Base64. Se devuelve el resultado truncado a una longitud determinada.
     * 
     * @return the string
     */
    public static String calcularUUID() {
        String UUID = Thread.currentThread().getName() + Calendar.getInstance().getTimeInMillis() + Math.random();
        MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(UUID.getBytes());
            UUID = new String(Base64.encode(messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            LOG.error(String.format("NO SE PUEDE CALCULAR EL IDENTIFICADOR DEL CLIENTE."));
        }

        return UUID.replaceAll("\\+", "").replaceAll("/", "").substring(0, 10);
    }

    /**
     * Valida el objeto JSON mediante anotaciones OVal y mediante Java
     * 
     * @param objeto
     *            the objeto
     * @throws BadRequestException
     *             the bad request exception
     */
    public static void validarJSON(BaseObject objeto) throws BadRequestException {
        Validator validator = new Validator();

        // Se valida el objeto JSON recibido mediante anotacion OVal
        List<ConstraintViolation> violations = validator.validate(objeto);
        String mensajeError = "";

        for (ConstraintViolation cv : violations) {
            mensajeError = cv.getMessage().concat(" [");
            if (cv.getCauses() != null) {
                for (ConstraintViolation cv2 : cv.getCauses()) {
                    mensajeError = mensajeError.concat(cv2.getMessage()).concat(";");
                }
            }
            mensajeError.concat("]");
        }

        if (violations.size() > 0) {
            throw new BadRequestException(mensajeError);
        }
    }

    /**
     * Truncar.
     * 
     * @return the integer
     */
    public static Integer truncar(BigDecimal puntos) {
        NumberFormat numberFormat = NumberFormat.getInstance();

        // trunca a dos digitos
        numberFormat.setMaximumFractionDigits(0);

        // le decimos al NumberFormat que el redondeado sea mediante truncamiento.
        numberFormat.setRoundingMode(RoundingMode.DOWN);

        return Integer.parseInt(numberFormat.format(puntos));
    }

}
