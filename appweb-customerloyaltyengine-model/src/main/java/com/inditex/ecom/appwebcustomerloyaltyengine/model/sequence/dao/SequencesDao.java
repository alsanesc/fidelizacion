package com.inditex.ecom.appwebcustomerloyaltyengine.model.sequence.dao;

/**
 * The Interface SequencesDao.
 */
public interface SequencesDao {

    /**
     * Gets the next value.
     * 
     * @param sequence
     *            the sequence
     * @return the next value
     */
    Long getNextValue(String sequence);

}
