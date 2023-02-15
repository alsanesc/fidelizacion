package com.inditex.ecom.appwebcustomerloyaltyengine.web.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


/**
 * Clase para extraer la informacion de localizacion / internacionalizacion a partir del usuario autenticado.
 * 
 * @since 30/05/11 11:45
 */
public class AuthenticationLocaleResolver extends SessionLocaleResolver {

    /**
     * Estrategia para extraer el objeto Locale del usuario actual.
     */
    private PrincipalLocaleStrategy principalLocaleStrategy;

    /**
     * Constructor.
     */
    public AuthenticationLocaleResolver() {
        principalLocaleStrategy = new UserInformationLocaleStrategy();
    }

    /**
     * Fija la estrategia para extraer el objeto Locale del usuario actual.
     * 
     * @param principalLocaleStrategy
     *            estrategia para extraer el objeto Locale del usuario actual.
     */
    public void setPrincipalLocaleStrategy(final PrincipalLocaleStrategy principalLocaleStrategy) {
        this.principalLocaleStrategy = principalLocaleStrategy;
    }

    /**
     * Determine the default locale for the given request, Called if no locale session attribute has been found.
     * <p>
     * The default implementation returns the specified default locale, if any, else falls back to the request's accept-header locale.
     * 
     * @param request
     *            the request to resolve the locale for
     * @return the default locale (never <code>null</code>)
     * @see #setDefaultLocale
     * @see javax.servlet.http.HttpServletRequest#getLocale()
     */
    protected Locale determineDefaultLocale(HttpServletRequest request) {
        Locale defaultLocale = getDefaultLocale();
        if (defaultLocale == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal != null) {
                    defaultLocale = principalLocaleStrategy.determineLocale(principal);
                }
            }
        }
        if (defaultLocale == null) {
            defaultLocale = request.getLocale();
        }
        return defaultLocale;
    }

}
