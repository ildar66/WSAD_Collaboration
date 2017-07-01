/*
 * Created on 27.03.2007
 */
package com.trailcom.common.math;

import java.math.BigDecimal;

/**
 * Расстояние между двумя точками
 * @author AProkofev
 */
public class DegreeDistance {

	public static BigDecimal calcDistanceBD(BigDecimal latitude1, BigDecimal latitude2, BigDecimal longitude1, BigDecimal longitude2, int scale ){
		double lat1 = latitude1.doubleValue();
		double lat2 = latitude2.doubleValue();
		double lon1 = longitude1.doubleValue();
		double lon2 = longitude2.doubleValue();
		return calcDistance(lat1, lon1, lat2, lon2, scale);
	}

	public static BigDecimal calcDistance(double lat1, double lon1, double lat2, double lon2, int scale ){
		try {
			double x1 = Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lon1));
			double y1 = Math.cos(Math.toRadians(lat1))*Math.sin(Math.toRadians(lon1));
			double z1 = Math.sin(Math.toRadians(lat1));
			double x2 = Math.cos(Math.toRadians(lat2))*Math.cos(Math.toRadians(lon2));
			double y2 = Math.cos(Math.toRadians(lat2))*Math.sin(Math.toRadians(lon2));
			double z2 = Math.sin(Math.toRadians(lat2));
			double sum = x1*x2+y1*y2+z1*z2;
			double phi = Math.acos(x1*x2+y1*y2+z1*z2);
			double lz = (double)6371032*phi/1000; //км
			//System.err.println("calculating distance!!! " +lat1+" "+lon1+" "+lat2+" "+lon2+"="+lz);
			return new BigDecimal(lz).setScale(scale, BigDecimal.ROUND_UP);			
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
