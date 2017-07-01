/*
 * Created on 21.02.2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.trailcom.common.math;

import java.math.BigDecimal;

/**
 * @author AProkofev
 */
public class Deflections {
	private BigDecimal oldBDValue;
	private BigDecimal newBDValue;

	public Deflections(BigDecimal oldBDValue, BigDecimal newBDValue) {
		if (oldBDValue==null) oldBDValue = new BigDecimal(0);
		if (newBDValue==null) newBDValue = new BigDecimal(0);
		this.oldBDValue = oldBDValue;
		this.newBDValue = newBDValue;
	}

	/**
	 * ѕроверить разрешенное отклонение дл€ 2-х чисел BigDecimal
	 * @param percent
	 * @param includedPercent
	 * @param type - 1 - только вниз, 2 - только вверх, 0 - в обе стороны 
	 * @return true - отклонение находитс€ в заданных границах
	 * ≈сли вызван неправильный конструктор, возвращает false
	 */
	public boolean checkDeflection(int percent, int dir, boolean includedPercent) {
		if (oldBDValue==null || newBDValue==null) return false;
		if (oldBDValue.compareTo(new BigDecimal(0))==0) return false;
		//System.out.println("defl = "+getDeflection(percent)[0].toString()+" "+getDeflection(percent)[1].toString());

		int codeDiffMin = newBDValue.compareTo(getPercentDeflection(percent)[0]);
		int codeDiffMax = newBDValue.compareTo(getPercentDeflection(percent)[1]);
		//System.out.println("codeDiffMin = "+codeDiffMin);
		//System.out.println("codeDiffMax = "+codeDiffMax);

		if (includedPercent) {
			switch(dir) {
				case 0: if (codeDiffMin>=0 && codeDiffMax<=0) return true; else return false;
				case 1: if (codeDiffMin>=0) return true; else return false;
				case 2: if (codeDiffMax<=0) return true; else return false;
			}
			/*if (getDeflection(percent, 0)<=newIntValue && newIntValue<=getDeflection(percent, 1))
			return true;
			else return false;*/
		}
		else {
			switch(dir) {
				case 0: if (codeDiffMin>0 && codeDiffMax<0) return true; else return false;
				case 1: if (codeDiffMin>0) return true; else return false;
				case 2: if (codeDiffMax<0) return true; else return false;
			}
		}
		return false;
	}

	/**
	 * ќтклонение значени€ в процентах от заданного  
	 * @param percent - процент отклонени€
	 * @return
	 */
	private BigDecimal[] getPercentDeflection(int percent) {
		BigDecimal percentBD = new BigDecimal(percent);
		BigDecimal percentValue = oldBDValue.multiply(percentBD).divide(new BigDecimal(100),6,BigDecimal.ROUND_HALF_UP);
		
		//System.out.println("percentValue = "+percentValue.toString());
		//System.out.println("oldBDValue = "+oldBDValue.toString());
		BigDecimal[] newvalue = {oldBDValue,oldBDValue};
		newvalue[0]=newvalue[0].subtract(percentValue);
		newvalue[1]=newvalue[1].add(percentValue);
		if (newvalue[0].compareTo(newvalue[1])==1)
		{
			BigDecimal tempBD = newvalue[0];
			newvalue[0]=newvalue[1];
			newvalue[1]=tempBD;
		}

		return newvalue;
	}
}
