package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Cadenas;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.EstadoRecurso;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.Secuencias;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoRecurso;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoValorRecurso;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.ConfiguracionCadenasDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionMaestrosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.RecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.sequence.dao.SequencesDao;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion de Hibernate de ConfiguracionCadenasDao: nos permitira realizar todas las operaciones
 * relacionadas con la configuracion para cada cadena.
 */
@Repository("configuracionCadenasDao")
@Transactional(propagation = Propagation.MANDATORY)
public class ConfiguracionCadenasDaoImpl implements ConfiguracionCadenasDao {
    
    /** The id instalacion. */
    private @Value("${ID_INSTALACION}") Integer idInstalacion;
    
    /** The gestion maestros dao. */
    @Autowired
    private GestionMaestrosDao gestionMaestrosDao;
    
    /** The sequences dao. */
    @Autowired
    private SequencesDao sequencesDao;
    
    /******************************************************************************************************************************
     * 
     * ACCIONES SOBRE CONFIGURACION CADENAS
     * 
     ******************************************************************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public boolean comprobarCadenaPais(Cadenas cadenas) {
        if (cadenas.getIdCadena().intValue() == 2 && cadenas.getCodPais().equals("ES")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public RecursoDto obtenerRecurso(Cadenas cadenas, Integer saldoPuntos) {
        RecursoDto recurso = new RecursoDto();

        if (cadenas.getIdCadena().intValue() == 2 && cadenas.getCodPais().equals("ES")) {
            recurso.setIdRecurso(new PrimaryKeyRecursoDto(sequencesDao.getNextValue(Secuencias.SQ_RECURSO), idInstalacion));
            recurso.setIdTipoRecurso(Integer.valueOf(TipoRecurso.TARJETA_PUNTOS_PULLBEAR.getValue()));
            recurso.setIdCadena(cadenas.getIdCadena().shortValue());
            recurso.setIdPais(gestionMaestrosDao.obtenerIdPais(cadenas.getCodPais()));
            recurso.setIdEstadoRecurso(EstadoRecurso.ACTIVO.getValue());
            recurso.setIdTipoValorRecurso(TipoValorRecurso.PUNTOS.getValue());
            
            if (saldoPuntos != null) {
                recurso.setValorRecurso(new BigDecimal(saldoPuntos));
                recurso.setValorDisponibleRecurso(new BigDecimal(saldoPuntos));
            } else {
                recurso.setValorRecurso(new BigDecimal(1000));
                recurso.setValorDisponibleRecurso(new BigDecimal(1000));
            }
            
            recurso.setFechaHoraAlta(new Timestamp(Calendar.getInstance().getTimeInMillis()));

            return recurso;
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Cadenas> obtenerCadenas() {
        List<Cadenas> cadenasList = new ArrayList<Cadenas>();
        Cadenas cadena = new Cadenas();

        cadena.setIdCadena(2);
        cadena.setCodPais("ES");
        cadena.setTiempoCancelacion(30 * 24 * 60 * 60 * 1000);

        cadenasList.add(cadena);

        return cadenasList;
    }

}
