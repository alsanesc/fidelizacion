package com.inditex.ecom.appwebcustomerloyaltyengine.model.common.test;

import java.sql.Timestamp;
import java.util.Calendar;

public class TestPrueba {

    public static void main(String args[]) {
        Calendar currentTimeStamp = Calendar.getInstance();
        Timestamp tiempo = new Timestamp(currentTimeStamp.getTimeInMillis());
        
        if ((tiempo.getTime() - (20 * 60 * 1000)) < currentTimeStamp.getTimeInMillis()) {
            System.out.println("menor");
            System.out.println(tiempo);
        } else {
            System.out.println("mayor");
        }
        
        
//        tiempo = new Timestamp(tiempo.getTime() - (20 * 60 * 1000));
//        System.out.println(tiempo.getTime());
//        System.out.println(tiempo);
        
    }

}
