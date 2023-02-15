package com.inditex.ecom.appwebcustomerloyaltyengine.model.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.GestionRecursosService;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionRecursosDao;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion del Service de GestionRecursosService: nos permitira realizar todas las operaciones
 * relacionadas con RECURSOS
 */
@Service("gestionRecursosService")
@Transactional(propagation = Propagation.REQUIRED)
public class GestionRecursosServiceImpl implements GestionRecursosService {

    /** The Constant LOG. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestionRecursosServiceImpl.class);
    
    /** The gestion recursos dao. */
    @Autowired
    private GestionRecursosDao gestionRecursosDao;
    
    /**
     * ****************************************************************************************************************************
     * 
     * ACCIONES SOBRE RECURSOS
     * 
     * ****************************************************************************************************************************.
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer obtenerTipoRecurso(String recurso) {
        LOGGER.trace("GestionRecursosServiceImpl.obtenerTipoRecurso");
        
        return gestionRecursosDao.obtenerTipoRecurso(recurso);
    }

}
