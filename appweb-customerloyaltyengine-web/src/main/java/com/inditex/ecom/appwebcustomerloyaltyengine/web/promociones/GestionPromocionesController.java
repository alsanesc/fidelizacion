package com.inditex.ecom.appwebcustomerloyaltyengine.web.promociones;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.dto.Recarga;
import com.inditex.ecom.appwebcustomerloyaltyengine.api.promociones.GestionPromociones;
import com.inditex.ecom.appwebcustomerloyaltyengine.web.common.BaseController;

/**
 * The Class GestionPromocionesController.
 */
@Controller("gestionPromocionesController")
public class GestionPromocionesController extends BaseController implements GestionPromociones {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestionPromocionesController.class);

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/api/rest/v{version}/promociones", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    Recarga cargarPromociones(final @PathVariable("version") String version, @RequestBody Recarga recarga) {
        LOGGER.trace("GestionPromocionesController.cargarPromociones");

        Recarga respuesta = new Recarga();

        return respuesta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/api/rest/v{version}/promociones", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Recarga> buscarPromociones(final @PathVariable("version") String version,
            final @RequestParam(value = "idRecarga", required = false) Integer idRecarga,
            final @RequestParam(value = "fechaHasta", required = false) Timestamp fechaHasta,
            final @RequestParam(value = "fechaDesde", required = false) Timestamp fechaDesde,
            final @RequestParam(value = "idCadena", required = false) Integer idCadena,
            final @RequestParam(value = "idPais", required = false) Integer idPais,
            final @RequestParam(value = "idTipoTienda", required = false) Integer idTipoTienda,
            final @RequestParam(value = "idTienda", required = false) Integer idTienda,
            final @RequestParam(value = "activa", required = false) Boolean activa,
            final @RequestParam(value = "procesada", required = false) Boolean procesada) {
        LOGGER.trace("GestionPromocionesController.buscarPromociones");

        List<Recarga> listaRecarga = new ArrayList<Recarga>();

        return listaRecarga;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/api/rest/v{version}/deletepromociones/{idCargaPromocion}", method = RequestMethod.GET)
    public void desactivarPromocion(final @PathVariable("version") String version, final @PathVariable("idCargaPromocion") Integer idCargaPromocion) {
        LOGGER.trace("GestionPromocionesController.desactivarPromocion");

    }

}
