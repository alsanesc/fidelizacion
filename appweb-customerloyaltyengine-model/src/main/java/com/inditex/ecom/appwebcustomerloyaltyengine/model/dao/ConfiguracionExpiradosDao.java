package com.inditex.ecom.appwebcustomerloyaltyengine.model.dao;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.excepciones.TimeoutException;
import com.inditex.ecom.appwebcustomerloyaltyengine.model.common.Cadenas;

/**
 * <b>Descripcion:</b> Esta Interface determina la declaracion de Hibernate de ConfiguracionExpiradosDao: definira todas las operaciones relacionadas
 * con la expiracion de DESCUENTOS.
 */
public interface ConfiguracionExpiradosDao {

    /**
     * Actualizar descuento: pasamos el estado del descuento de PENDIENTE a EXPIRADO, e incrementamos el NUMERO_SERIE_OPERACION_DESCUENTO.
     * 
     * @param estadoDescuentoBuscar
     *            the estado descuento buscar
     * @param cadenas
     *            the cadenas
     * @param estadoDescuentoActualizar
     *            the estado descuento actualizar
     * @throws TimeoutException
     *             the timeout exception
     */
    void actualizarDescuento(Short estadoDescuentoBuscar, Cadenas cadenas, Short estadoDescuentoActualizar) throws TimeoutException;
    
    /**
     * Actualizar linea descuento.
     * 
     * @param idEstadoDescuento
     *            the id estado descuento
     */
    void actualizarLineaDescuento(Short idEstadoDescuento)  throws TimeoutException;

    /**
     * Eliminar descuentos: eliminamos de la tabla todos los descuentos que tengan el estado EXPIRADO.
     * 
     * @param idEstadoDescuento
     *            the id estado descuento
     * @throws TimeoutException
     *             the timeout exception
     */
    void eliminarDescuentos(Short idEstadoDescuento);

}
