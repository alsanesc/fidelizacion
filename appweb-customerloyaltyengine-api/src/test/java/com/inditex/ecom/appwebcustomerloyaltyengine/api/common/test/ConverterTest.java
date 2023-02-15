package com.inditex.ecom.appwebcustomerloyaltyengine.api.common.test;

import java.math.BigDecimal;


public class ConverterTest {
	
	public static void main(String[] args) {

		String c = "";
		BigDecimal bd = null;
		
		if (c.length() > 0) {
			bd = new BigDecimal(c);
		} else {
			bd = BigDecimal.ZERO;
		}
		
		System.out.println(bd);
	}

}
