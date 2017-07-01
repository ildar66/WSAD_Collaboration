/*
 * Created on 26.02.2007
 */
package com.trailcom.common.math;

import java.math.BigDecimal;

/**
 * @author AProkofev
 */
public class DeflectionsDegrees {
	private BigDecimal lat1;
	private BigDecimal lon1;
	private BigDecimal lat2;
	private BigDecimal lon2;

	public DeflectionsDegrees(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
		if (lat1==null) lat1 = new BigDecimal(0);
		if (lon1==null) lon1 = new BigDecimal(0);
		if (lat2==null) lat2 = new BigDecimal(0);
		if (lon2==null) lon2 = new BigDecimal(0);

		this.lat1 = lat1;
		this.lon1 = lon1;
		this.lat2 = lat2;
		this.lon2 = lon2;
	}

	/**
	 * 
	 * @param radiusM - радиус в метрах
	 * @param includedRadius - включая значение радиуса
	 * @return true - отклонение находится в заданных границах
	 * Если вызван неправильный конструктор, возвращает false
	 */
	public boolean checkDistanceDegreeDeflection(int radiusM, boolean includedRadius) {
		if (radiusM==0) return false;
		if (lat1==null || lon1==null || lat2==null || lon2==null) return false;
		BigDecimal distance = DegreeDistance.calcDistanceBD(lat1, lat2, lon1, lon2, 3).multiply(new BigDecimal(1000));
		//distance = distance.multiply(new BigDecimal(1000)); // в метры

		//System.out.println("distance = "+distance);
		//System.out.println("radiusM = "+radiusM);
		BigDecimal radius = new BigDecimal(radiusM);
		if (distance==null) return false;

		//System.out.println("diff = "+distance.compareTo(radius));

		if (includedRadius) {
			if (distance.compareTo(radius)<=0) return true; else return false;
		}
		else {
			if (distance.compareTo(radius)<0) return true; else return false;
		}
	}
}
