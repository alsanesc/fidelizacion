package com.inditex.ecom.appwebcustomerloyaltyengine.model.common.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Test {
    
    public static void main(String[] args) {
//        int o[] = { 49, 398, 398, 378, 378, 398, 398, 378, 378, 189, 278, 278, 278, 278, 398, 398, 398, 398, 98, 98, 49, 98, 98, 118, 118, 158, 158, 158, 158, 158, 158, 158, 158, 158, 158, 158, 158, 79, 79, 158, 158, 158, 158, 158, 158, 118, 118, 118, 118, 138, 138, 138, 138, 138, 138, 138, 138, 178, 178, 178, 178, 178, 178, 178, 178, 78, 78, 78, 78, 78, 78, 39, 158, 158, 158, 158, 158, 158, 78, 78, 78, 78, 278, 278, 139, 278, 278, 139, 378, 378, 567, 567, 567, 378, 378 };
//        int punt = 147;
        
        List<BigDecimal[]> lista = new ArrayList<BigDecimal[]>();
        BigDecimal v[] = { new BigDecimal(20), new BigDecimal(11), new BigDecimal(10), new BigDecimal(7), new BigDecimal(5), new BigDecimal(3) };
        BigDecimal m[] = new BigDecimal[v.length];
        BigDecimal puntos = new BigDecimal(28);
        BigDecimal suma = BigDecimal.ZERO;
        DecimalFormat df = new DecimalFormat("#,###.00");

        for (int i = 0; i < v.length; i++) {
            if (v[i].compareTo(puntos) <= 0) {
                suma = suma.add(v[i]);
                m[i] = v[i];
                
                int p = 0;
                BigDecimal parcial = BigDecimal.ZERO;
                for (int j = i + 1; j <= v.length - 1; j++) {
                    if (suma.add(v[j]).compareTo(puntos) <= 0) {
                        p = j;
                        while (p <= v.length - 1) {
                            if (suma.add(parcial).add(v[p]).compareTo(puntos) <= 0) {
                                parcial = parcial.add(v[p]);
                                m[p] = v[p];
                            }
                            p++;
                        }
                        
                        lista.add(m);
                        parcial = BigDecimal.ZERO;
                        m = new BigDecimal[v.length];
                        m[i] = v[i];
                    }
                }
                
            }
            lista.add(m);
            
            suma = BigDecimal.ZERO;
            m = new BigDecimal[v.length];
        }
        
        suma = BigDecimal.ZERO;
        int pos = 0;
        for (int j = 0; j <= lista.size() - 1; j++) {
            BigDecimal[] p = lista.get(j);
            for (int i = 0; i < p.length; i++) {
                if (p[i] == null) {
                    System.out.print(" " + df.format(0.00));
                } else {
                    System.out.print(" " + df.format(p[i].doubleValue()));
                    suma = suma.add(p[i]);
                }
            }
            
            if (suma == puntos) {
                pos = j;
                break;
            }
            suma = BigDecimal.ZERO;
            System.out.println();
        }
        
        System.out.println();
        System.out.println("Fila: " + pos);
    }

}
