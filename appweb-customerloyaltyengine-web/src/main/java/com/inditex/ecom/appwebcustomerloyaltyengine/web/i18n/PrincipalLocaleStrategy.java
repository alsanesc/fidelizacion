package com.inditex.ecom.appwebcustomerloyaltyengine.web.i18n;

import java.util.Locale;

/**
 * Estrategia para obtener el objeto Locale a partir del objeto Principal.
 * 
 * @since 30/05/11 11:59
 */
public interface PrincipalLocaleStrategy {

    /**
     * Determina la locale del objeto principal.
     * 
     * @param principal
     *            objeto principal.
     * @return locale determinada o null.
     */
    Locale determineLocale(Object principal);

}
