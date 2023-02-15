package com.inditex.ecom.appwebcustomerloyaltyengine.web.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * DefaultSessionLocaleResolver
 * 
 * Locale por defecto Necesario definir cada idioma para el que existan traducciones.
 */
public class DefaultSessionLocaleResolver extends SessionLocaleResolver {

    /** Idiomas definidos en la aplicaicon. */
    public final String EN_LANG_CODE = "en";

    /** Idiomas definidos en la aplicaicon. */
    public final String ES_LANG_CODE = "es";

    /** The default locale. */
    private Locale defaultLocale = new Locale("es", "ES");

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.i18n.SessionLocaleResolver#determineDefaultLocale (javax.servlet.http.HttpServletRequest)
     */
    /**
     * {@inheritDoc}
     */
    protected Locale determineDefaultLocale(HttpServletRequest request) {

        String defaultLanguage = request.getLocale().getLanguage();

        // Spanish
        if (ES_LANG_CODE.equals(defaultLanguage)) {
            return request.getLocale();
        }
        // Locale por defecto
        else {
            return getDefaultLocale();
        }
    }

    /**
     * Get del locale.
     * 
     * @return locale
     */
    public Locale getDefaultLocale() {
        return defaultLocale;
    }

    /**
     * Set del locale.
     * 
     * @param locale
     *            the new default locale
     */
    public void setDefaultLocale(Locale locale) {
        defaultLocale = locale;
    }
}