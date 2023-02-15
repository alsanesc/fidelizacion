package com.inditex.ecom.appwebcustomerloyaltyengine.web.common;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.inditex.arquitectura.componentes.webapp.filter.AuthenticationIdx;
import com.inditex.arquitectura.componentes.webapp.filter.AuthenticationProviderIdx;
import com.inditex.arquitectura.componentes.webapp.filter.NamePrincipal;
import com.inditex.arquitectura.componentes.webapp.util.NameToPrincipalProvider;
import com.inditex.arquitectura.componentes.webapp.util.UserNameProvider;
import com.inditex.common.business.retry.Retryable;

/**
 * Esta clase es un proxy de prueba de autenticacion. Se usa la notacion @Retryable de inditex para intentar el metodo de autenticacion 5 veces. Para
 * deshabilitarlo cambiar la configuracion del bean 'authenticationProviderIdx' en el fichero spring-security.xml
 */
public class AuthenticationProviderIdxProxy extends AuthenticationProviderIdx {

    /** The Constant LOGGER. */
    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProviderIdxProxy.class);

    /** The user name provider. */
    private UserNameProvider userNameProvider;

    /** The name to principal provider. */
    private NameToPrincipalProvider principalProvider;

    /** The contador reintentos. */
    private int contadorReintentos = 0;

    /**
     * Set the user name provider.
     * 
     * @param userNameProvider
     *            the user name provider.
     */
    public void setUserNameProvider(final UserNameProvider userNameProvider) {
        this.userNameProvider = userNameProvider;
    }

    /**
     * Set the name to principal provider.
     * 
     * @param principalProvider
     *            the name to principal provider.
     */
    public void setPrincipalProvider(final NameToPrincipalProvider principalProvider) {
        this.principalProvider = principalProvider;
    }

    /**
     * Obtiene el nombre de usuario a traves del proveedor de nombres de usuario o de la implementacion de la clase padre en su defecto.
     * 
     * @param authentication
     *            el elemento de autenticacion.
     * @return the user name.
     */
    protected String getUserName(final Authentication authentication) {
        if (userNameProvider != null) {
            LOGGER.debug("OBTENIENDO USERNAME: " + authentication);
            return userNameProvider.obtainUserName(authentication);
        } else {
            return super.getUserName(authentication);
        }
    }

    /**
     * Obtiene el principal que corresponde a un nombre de usuario dado.
     * 
     * @param userName
     *            el nombre de usuario.
     * @return principal correspondiente.
     */
    protected Principal getPrincipal(final String userName) {

        if (principalProvider != null) {

            Principal principalCacheado = null;
            synchronized (AuthenticationProviderIdxProxy.class) {
                principalCacheado = CacheAutenticacion.getCachePrincipal().get(userName);
            }

            if (principalCacheado != null) {
                LOGGER.debug("DEVOLVIENDO PRINCIPAL CACHEADO");
                return CacheAutenticacion.getCachePrincipal().get(userName);
            }

            LOGGER.debug("OBTENIENDO PRINCIPAL PARA: " + userName);
            Principal principal = principalProvider.obtainPrincipal(userName);
            if (principal != null) {
                synchronized (AuthenticationProviderIdxProxy.class) {
                    CacheAutenticacion.getCachePrincipal().put(userName, principal);
                }
            }
            return principal;
        } else {
            return new NamePrincipal(userName);
        }
    }

    /**
     * Metodo plantilla para permitir a las subclases utilizar distintas clases de usuario y poder anhadirle toda la informacion de contexto deseada.
     * 
     * @param authentication
     *            the source authentication.
     * @return the authentication object.
     */
    protected Authentication crearNuevoAuthentication(final Authentication authentication) {

        final String userName = getUserName(authentication);
        final Principal principal = getPrincipal(userName);
        return new AuthenticationIdx(principal, authentication.getCredentials(), getAuthorities(userName, authentication));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Retryable(value = 5, exceptions = { AuthenticationException.class })
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        try {
            // Si entra aqui es porque el user/pass ya esta validado y con el rol autenticado
            // Spring Security lo valida para las peticiones POST y GET antes de llamar a esta clase para autenticar al
            // usuario. Entonces siempre se guarda el usuario en la cache
            LOGGER.debug("AUTENTICANDO: " + authentication.getPrincipal());
            return super.authenticate(authentication);
        } catch (AuthenticationException e) {
            LOGGER.info("PROXY AUTHENTICACION RETRY: " + contadorReintentos++);
            throw e;
        }
    }
    
}
