package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Constantes.TipoOperacionDescuento;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.CalculoImportesDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionDescuentosDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dao.GestionOperacionesDao;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.OperacionRecursoDto;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.dto.PrimaryKeyDescuentoDto;

/**
 * <b>Descripcion:</b> Esta Clase determina la implementacion de Hibernate de CalculoImportesDao: nos permitira calcular los valores asociados al
 * descuento (autorizado, capturado, pendiente_capturar, devuelto, pendiente_devolver).
 */
@Repository("calculoImportesDao")
public class CalculoImportesDaoImpl implements CalculoImportesDao {

    /** The gestion operaciones dao. */
    @Autowired
    private GestionOperacionesDao gestionOperacionesDao;

    /** The gestion descuentos dao. */
    @Autowired
    private GestionDescuentosDao gestionDescuentosDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calcularAutorizado(PrimaryKeyDescuentoDto idDescuento) {
        List<Long> operacionDescuentoList = gestionDescuentosDao.obtenerIdOperacionDescuento(idDescuento, TipoOperacionDescuento.AUTORIZACION.getValue());
        BigDecimal autorizado = BigDecimal.ZERO;
        
        if (operacionDescuentoList.size() > 0) {
            List<OperacionRecursoDto> operacionRecursoList = gestionOperacionesDao.obtenerOperacionRecurso(operacionDescuentoList);
            
            for (OperacionRecursoDto operacionRecurso : operacionRecursoList) {
                autorizado = autorizado.add(operacionRecurso.getValorOperacion());
            }
        }

        return autorizado;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calcularCapturado(PrimaryKeyDescuentoDto idDescuento) {
        List<Long> operacionDescuentoList = gestionDescuentosDao.obtenerIdOperacionDescuento(idDescuento, TipoOperacionDescuento.CAPTURA.getValue());
        BigDecimal capturado = BigDecimal.ZERO;
        
        if (operacionDescuentoList.size() > 0) {
            List<OperacionRecursoDto> operacionRecursoList = gestionOperacionesDao.obtenerOperacionRecurso(operacionDescuentoList);
            
            for (OperacionRecursoDto operacionRecurso : operacionRecursoList) {
                capturado = capturado.add(operacionRecurso.getValorOperacion());
            }
        }

        return capturado;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calcularDevuelto(PrimaryKeyDescuentoDto idDescuento) {
        List<Long> operacionDescuentoList = gestionDescuentosDao.obtenerIdOperacionDescuento(idDescuento, TipoOperacionDescuento.DEVOLUCION.getValue());
        BigDecimal devuelto = BigDecimal.ZERO;

        if (operacionDescuentoList.size() > 0) {
            List<OperacionRecursoDto> operacionTarjetaList = gestionOperacionesDao.obtenerOperacionRecurso(operacionDescuentoList);
            
            for (OperacionRecursoDto operacionTarjeta : operacionTarjetaList) {
                devuelto.add(operacionTarjeta.getValorOperacion());
            }
        }

        return devuelto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calcularPendienteDevolver(PrimaryKeyDescuentoDto idDescuento) {
        return calcularCapturado(idDescuento).subtract(calcularDevuelto(idDescuento));
    }

}
