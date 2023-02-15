package com.inditex.ecom.appwebcustomerloyaltyengine.model.common.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PruebaTest {

    public static void main(String[] args) {
        List<BigDecimal[]> lista = new ArrayList<BigDecimal[]>();
//        int v[] = { 49, 398, 398, 378, 378, 398, 398, 378, 378, 189, 278, 278, 278, 278, 398, 398, 398, 398, 98, 98, 49, 98, 98, 118, 118, 158, 158, 158, 158, 158, 158, 158, 158, 158, 158, 158, 158, 79, 79, 158, 158, 158, 158, 158, 158, 118, 118, 118, 118, 138, 138, 138, 138, 138, 138, 138, 138, 178, 178, 178, 178, 178, 178, 178, 178, 78, 78, 78, 78, 78, 78, 39, 158, 158, 158, 158, 158, 158, 78, 78, 78, 78, 278, 278, 139, 278, 278, 139, 378, 378, 567, 567, 567, 378, 378 };
        BigDecimal v[] = { new BigDecimal("20.95"), new BigDecimal("11.95"), new BigDecimal("10.95"), new BigDecimal("7.95"), new BigDecimal("5.95"), new BigDecimal("3.95") };
        BigDecimal puntos = new BigDecimal("31.00");
        
        calculamos(v, puntos, lista);
    }

    public static void calculamos(BigDecimal v[], BigDecimal puntos, List<BigDecimal[]> lista) {
        BigDecimal m[] = new BigDecimal[v.length];
        Arrays.fill(m, BigDecimal.ZERO);
        BigDecimal suma = BigDecimal.ZERO;
        
        long time_start = System.currentTimeMillis();

        for (int i = 0; i < v.length; i++) {
            if (v[i].compareTo(puntos) <= 0) {
                m[i] = v[i];
                suma = suma.add(v[i]);
                
                int p = 0;
                BigDecimal parcial = BigDecimal.ZERO;

                for (int j = i + 1; j <= v.length - 1; j++) {
                    if (suma.add(v[j]).compareTo(puntos) <= 0) {
                        p = j;
                        
                        while(p <= v.length - 1) {
                            if (suma.add(parcial).add(v[p]).compareTo(puntos) <= 0) {
                                parcial = parcial.add(v[p]);
                                m[p] = v[p];
                            }
                            p++;
                        }
                        lista.add(m);
                        m = new BigDecimal[v.length];
                        Arrays.fill(m, BigDecimal.ZERO);
                        m[i] = v[i];
                        
                        if (suma.add(parcial).compareTo(puntos) == 0) {
                            suma = suma.add(parcial);
                            break;
                        } else {
                            parcial = BigDecimal.ZERO;
                        }
                    }
                }
                
                if (suma == puntos) {
                    lista.add(m);
                    break;
                }
            } else {
                m[i] = BigDecimal.ZERO;
                Arrays.fill(m, BigDecimal.ZERO);
            }

            lista.add(m);
            m = new BigDecimal[v.length];
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

            if ((parcial.compareTo(puntos) <= 0) && (parcial.compareTo(suma) > 0)) {
                col = i;
                suma = parcial;
            }

            if (suma == puntos) {
                col = i;
                break;
            }
            
            parcial = BigDecimal.ZERO;
        }
        
        long time_finish = System.currentTimeMillis();
        
//        for (int i = 0; i <= lista.size() - 1; i++) {
//            int[] p = lista.get(i);
//            for (int j = 0; j < p.length; j++) {
//                System.out.print(String.format("%5d", p[j]));
//            }
//            System.out.println();
//        }

        System.out.println("-----------------------------");
        System.out.println("Fila: " + col + " con " + suma);
        BigDecimal[] p = lista.get(col);
        for (int j = 0; j < p.length; j++) {
            System.out.print(" " + p[j]);
        }
        System.out.println();
        
        System.out.println(time_finish - time_start);

    }
    
}
