/**
 * BaseObject.java 16/05/2013
 *
 * Copyright 2013 INDITEX.
 * Departamento de Sistemas
 */
package com.inditex.ecom.appwebcustomerloyaltyengine.api.common;

import java.io.Serializable;

/**
 * The Class BaseObject.
 */
public abstract class BaseObject implements Serializable {

    /** The Constant serialVersionUID. */
    private transient static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    public abstract boolean equals(Object o);

    /**
     * {@inheritDoc}
     */
    public abstract int hashCode();
}
