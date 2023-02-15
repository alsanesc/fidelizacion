package com.inditex.ecom.appwebcustomerloyaltyengine.web.common;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CacheAutenticacion.
 */
public class CacheAutenticacion implements Runnable {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(CacheAutenticacion.class);

    /** The cache principal. */
    private static Map<String, Principal> cachePrincipal;

    /**
     * Instantiates a new cache autenticacion.
     */
    protected CacheAutenticacion() {
        cachePrincipal = new HashMap<String, Principal>();
    }

    /**
     * Gets the cache principal.
     * 
     * @return the cache principal
     */
    public static Map<String, Principal> getCachePrincipal() {
        return cachePrincipal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {

        synchronized (CacheAutenticacion.class) {
            if (!cachePrincipal.isEmpty()) {
                LOG.info("EXPIRA LA CACHE DE AUTENTICACION: " + cachePrincipal);
                cachePrincipal.clear();
            }
        }
    }
}
