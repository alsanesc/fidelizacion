package com.inditex.ecom.appwebcustomerloyaltyengine.web.i18n;

import java.util.Locale;

import com.inditex.seguridad.sso.UserInformation;

/**
 * Obtiene la Locale de un Principal de tipo UserInformation, empleando para ello el valor de un atributo. Si no se especifica el valor de
 * "localeAttributeName" se emplea como origen de la informacion el atributo con nombre "locale".
 * 
 * @since 30/05/11 12:00
 */
public class UserInformationLocaleStrategy implements PrincipalLocaleStrategy {

    /**
     * Nombre por defecto del atributo que almacena la locale.
     */
    public static final String DEFAULT_LOCALE_ATTRIBUTE = "locale";

    /**
     * Nombre del atributo que almacena la locale.
     */
    private String localeAttributeName;

    /**
     * Constructor.
     */
    public UserInformationLocaleStrategy() {
        localeAttributeName = DEFAULT_LOCALE_ATTRIBUTE;
    }

    /**
     * Set locale attribute name.
     * 
     * @param localeAttributeName
     *            locale attribute name.
     */
    public void setLocaleAttributeName(final String localeAttributeName) {
        this.localeAttributeName = localeAttributeName;
    }

    /**
     * Determina la locale del objeto principal.
     * 
     * @param principal
     *            objeto principal.
     * @return locale determinada o null.
     */
    @Override
    public Locale determineLocale(Object principal) {

        Locale result = null;

        if (principal instanceof UserInformation) {

            UserInformation userInformation = (UserInformation) principal;
            String localeString = userInformation.getAtributo(localeAttributeName);
            if (localeString != null) {
                result = parseLocaleString(localeString);
            }

        }

        return result;
    }

    /**
     * Assemble locale.
     * 
     * @param parts
     *            the parts
     * @return the locale
     */
    private Locale assembleLocale(final String[] parts) {
        Locale result = null;
        switch (parts.length) {
        case 1:
            result = new Locale(parts[0]);
            break;
        case 2:
            result = new Locale(parts[0], parts[1]);
            break;
        case 3:
            result = new Locale(parts[0], parts[1], parts[2]);
            break;
        default:
            break;
        }
        return result;
    }

    /**
     * Parse locale string.
     * 
     * @param localeString
     *            the locale string
     * @return the locale
     */
    protected Locale parseLocaleString(final String localeString) {
        int index = localeString.indexOf("_");
        if (index > -1) {
            String[] parts = localeString.split("_");
            return assembleLocale(parts);
        } else {
            index = localeString.indexOf("-");
            if (index > -1) {
                String[] parts = localeString.split("-");
                return assembleLocale(parts);
            }
        }
        return null;
    }
}
