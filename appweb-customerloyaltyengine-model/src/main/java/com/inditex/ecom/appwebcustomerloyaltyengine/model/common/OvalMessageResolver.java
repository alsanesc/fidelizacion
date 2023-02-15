package com.inditex.ecom.appwebcustomerloyaltyengine.model.common;

import net.sf.oval.context.OValContext;
import net.sf.oval.localization.context.OValContextRenderer;
import net.sf.oval.localization.message.MessageResolver;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * Internacionaliza mensajes de validacion.
 * 
 * @since 10/06/11 14:04
 */
public class OvalMessageResolver implements OValContextRenderer, MessageResolver {

    /**
     * Message source.
     */
    private MessageSourceAccessor messageSourceAccessor;

    /**
     * Fija el message source.
     * 
     * @param messageSource
     *            el message source.
     */
    public void setMessageSource(final MessageSource messageSource) {
        this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }

    /**
     * Obtiene la etiqueta del contexto de validacion para mostrarla en un mensaje.
     * 
     * @param context
     *            contexto de validacion
     * @return etiqueta.
     */
    public String render(final OValContext context) {
        String key = context.toString();
        return messageSourceAccessor.getMessage(key, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage(String key) {
        return messageSourceAccessor.getMessage(key, key);
    }

}