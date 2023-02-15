package com.inditex.ecom.appwebcustomerloyaltyengine.web.recursos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Tarjeta;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.recursos.GestionRecursos;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.api.impl.GestionRecursosServiceImpl;
import com.inditex.ecom.appwebcustomerloyaltyengine.web.common.BaseController;

/**
 * The Class GestionRecursosController.
 */
@Controller("gestionRecursosController")
public class GestionRecursosController extends BaseController implements GestionRecursos {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory.getLogger(GestionRecursosController.class);

    /** The gestion recursos service. */
    @Autowired
    private GestionRecursosServiceImpl gestionRecursosService;

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/api/rest/v{version}/recurso/{recurso}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Tarjeta consultarTipoRecurso(final @PathVariable("version") String version, final @PathVariable("recurso") String recurso) {
        LOGGER.trace("GestionRecursosController.consultarTipoRecurso");

        return new Tarjeta(gestionRecursosService.obtenerTipoRecurso(recurso).toString(), recurso);
    }

}
