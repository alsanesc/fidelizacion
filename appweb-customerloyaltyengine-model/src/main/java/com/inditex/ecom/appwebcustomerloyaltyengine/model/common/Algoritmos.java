package com.inditex.ecom.appwebcustomerloyaltyengine.model.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class KnapSack.
 */
public class Algoritmos {

    /** The Constant LOG. */
    public static final Logger LOG = LoggerFactory.getLogger(Algoritmos.class);

    /**
     * Knap sack: obtain the maximum articles number
     * 
     * @param price
     *            the price
     * @param weights
     *            the weights
     * @param sackCapacity
     *            the sack capacity
     * @return the boolean[][]
     */
    public static boolean[][] knapSack(int[] price, int[] weights, int sackCapacity) {
        int nItems = price.length;
        // dp[i][w] - the maximum value of sub problem with i items and with w sack capacity.
        // no need to initialize with zeros as in java, the defalt values are 0 for int premitive type.
        int[][] dpTable = new int[nItems + 1][sackCapacity + 1];
        boolean[][] keep = new boolean[nItems][sackCapacity + 1];

        // iterate through all of the items
        for (int i = 1; i <= nItems; i++) {
            // calculate sub problem for all weights
            for (int w = 1; w <= sackCapacity; w++) {
                if (weights[i - 1] > w) {
                    // we cannt take this weight as it exceeds sub problem with weight w and i items
                    dpTable[i][w] = dpTable[i - 1][w];
                } else {
                    // Price if we include item i
                    int pYes = price[i - 1] + dpTable[i - 1][w - weights[i - 1]];
                    // Price if we include item i
                    int pNo = dpTable[i - 1][w];
                    if (pYes > pNo) {
                        // this item MAY go into sack
                        keep[i - 1][w] = true;
                        dpTable[i][w] = pYes;
                    } else {
                        dpTable[i][w] = pNo;
                    }
                }
            }
        }

        return keep;
    }

    /**
     * Price maximum.
     * 
     * @param price
     *            the price
     * @param sackCapacity
     *            the sack capacity
     * @return the boolean[]
     */
    public static BigDecimal[] priceMaximum(BigDecimal[] price, int sackCapacity) {
        List<BigDecimal[]> lista = new ArrayList<BigDecimal[]>();
        BigDecimal m[] = new BigDecimal[price.length];
        Arrays.fill(m, BigDecimal.ZERO);
        BigDecimal suma = BigDecimal.ZERO;
        
        for (int i = 0; i < price.length; i++) {
            if (price[i].compareTo(new BigDecimal(sackCapacity)) <= 0) {
                m[i] = price[i];
                suma = suma.add(price[i]);
                
                int p = 0;
                BigDecimal parcial = BigDecimal.ZERO;

                for (int j = i + 1; j <= price.length - 1; j++) {
                    if (suma.add(price[j]).compareTo(new BigDecimal(sackCapacity)) <= 0) {
                        p = j;
                        
                        while(p <= price.length - 1) {
                            if (suma.add(parcial).add(price[p]).compareTo(new BigDecimal(sackCapacity)) <= 0) {
                                parcial = parcial.add(price[p]);
                                m[p] = price[p];
                            }
                            p++;
                        }
                        lista.add(m);
                        m = new BigDecimal[price.length];
                        Arrays.fill(m, BigDecimal.ZERO);
                        m[i] = price[i];
                        
                        if (suma.add(parcial).compareTo(new BigDecimal(sackCapacity)) == 0) {
                            suma = suma.add(parcial);
                            break;
                        } else {
                            parcial = BigDecimal.ZERO;
                        }
                    }
                }
                
                if (suma.compareTo(new BigDecimal(sackCapacity)) == 0) {
                    lista.add(m);
                    break;
                }
            } else {
                m[i] = BigDecimal.ZERO;
                Arrays.fill(m, BigDecimal.ZERO);
            }

            lista.add(m);
            m = new BigDecimal[price.length];
            Arrays.fill(m, BigDecimal.ZERO);
            suma = BigDecimal.ZERO;
        }
        
        BigDecimal parcial = BigDecimal.ZERO;
        int col = 0;
        suma = BigDecimal.ZERO;
        for (int i = 0; i <= lista.size() - 1; i++) {
            BigDecimal[] p = lista.get(i);
            for (int j = 0; j < p.length; j++) {
                parcial = parcial.add(p[j]);
            }

            if ((parcial.compareTo(new BigDecimal(sackCapacity)) <= 0) && (parcial.compareTo(suma) > 0)) {
                col = i;
                suma = parcial;
            }

            if (suma.compareTo(new BigDecimal(sackCapacity)) == 0) {
                col = i;
                break;
            }
            
            parcial = BigDecimal.ZERO;
        }

        return lista.get(col);
    }

}
