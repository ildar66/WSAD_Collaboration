package com.hps.april.common.utils;

import java.math.BigDecimal;
import java.util.List;

import com.hps.april.common.math.DoubleRange;
//import org.apache.commons.lang.math.DoubleRange;
import org.apache.commons.lang.math.IntRange;
import org.apache.commons.lang.math.LongRange;
import org.apache.commons.lang.math.NumberRange;
//import org.apache.commons.lang.math.Range;

/**
 * @author dimitry
 * Created: 30.03.2006
 */
public class NumberUtils {

	/**
	 * @deprecated use FormatUtils
	 * @param value
	 * @return
	 */
	public static Long toLong(Integer value){
		return FormatUtils.toLong(value);
	}

	/**
	 * @deprecated use FormatUtils
	 * @param value
	 * @return
	 */
	public static Integer toInteger(Long value) {
		return FormatUtils.toInteger(value);
	}
	
	/**
	 * ������� ��� ������� �������� �� ������� 
	 * @author dimitry
	 * 19.10.2006
	 */
	static interface Calculator {
		void calculate(Object value);

	}
	
	/**
	 * ������ �������� ��������
	 * @author dimitry
	 * 19.10.2006
	 */
	static abstract class NumberCalculator implements Calculator {
		protected double result;
		protected boolean firstAccessFlag = true;
		
		public abstract void calculate(Number number);
		
		public void calculate(Object object){
			if (object == null) return;
			
			else if (object instanceof Number)
				calculate((Number)object);
			else 
				throw new ClassCastException("���������� ��������� ����� ��������. " +
						"� ���������� ���������� ��������� " + object.getClass().getName() + ", " +
						"��������� " + Number.class.getName());
		}
		
		public Double getDoubleResult(){
			if (firstAccessFlag) return null;
			return new Double(result);
		}
		
		public Long getLongResult(){
			if (firstAccessFlag) return null;
			return new Long((long)result);
		}
		
		public Integer getIntegerResult(){
			if (firstAccessFlag) return null;
			return new Integer((int)result);
		}
	}
	
	/**
	 * ������ ����� �������� ��������
	 * @author dimitry
	 * 19.10.2006
	 */
	static class NumberCalculatorSum extends NumberCalculator {
				
		public void calculate(Number number) {
			if (number != null) {
				double value = number.doubleValue();
				result += value;
				
				firstAccessFlag = false;
			}

			// TODO throws ?
		}
	}
	
	/**
	 * ������ ������������� �� �������� ��������
	 * @author dimitry
	 * 21.10.2006
	 */
	static class NumberCalculatorMax extends NumberCalculator {
		
		public void calculate(Number number){
			if (number != null) {
				double value = number.doubleValue();
				
				if (firstAccessFlag || result < value)
					result = value;
						
				firstAccessFlag = false;
			}

			// TODO throws ?
		}
	}
	
	/**
	 * ������ ������������ �� �������� ��������
	 * @author dimitry
	 * 21.10.2006
	 */
	static class NumberCalculatorMin extends NumberCalculator {
		protected boolean firstAccessFlag = true;
		
		public void calculate(Number number){
			if (number != null) {
				double value = number.doubleValue();
				
				if (firstAccessFlag || result > value)
					result = value;
						
				firstAccessFlag = false;
			}

			// TODO throws ?
		}
	}
	
	/**
	 * ������ Boolean ��������
	 * @author dimitry
	 * 21.10.2006
	 */
	static abstract class BooleanCalculator implements Calculator {
		protected boolean result;
		protected boolean firstAccessFlag = true;
		
		public Boolean getResult() {
			if (firstAccessFlag) return null;
			return new Boolean(result);
		}
		
		public abstract void calculate(Boolean value);
		
		public void calculate(Object object){
			if (object == null) return;
			else if (object instanceof Boolean)
				calculate((Boolean)object);
			else 
				throw new ClassCastException("���������� ��������� ����� ��������. " +
						"� ���������� ���������� ��������� " + object.getClass().getName() + ", " +
						"��������� " + Boolean.class.getName());
		}
		
	}
	
	/**
	 * ������: ���� �� True � ������ ��������? 
	 * @author dimitry
	 * 21.10.2006
	 */
	static class BooleanCalculatorTrue extends BooleanCalculator {
		
		public void calculate(Boolean value) {
			if (value != null) {
				
				if (firstAccessFlag || value.booleanValue())
					result = value.booleanValue();
						
				firstAccessFlag = false;
			}
				// TODO throws ?
		}
	}

	/**
	 * ������ �������� ����������
	 * @author dimitry
	 * 21.10.2006
	 */
	static abstract class NumberRangeCalculator implements Calculator {
		protected double minResult;
		protected double maxResult;
		
		protected boolean firstAccessFlag = true;
		
		public abstract void calculate(DoubleRange range);
		
		public void calculate(Object object){
			if (object == null) return;
			else if (object instanceof DoubleRange)
					calculate((DoubleRange)object);
			else 
				throw new ClassCastException("���������� ��������� ����� ��������. " +
						"� ���������� ���������� ��������� " + object.getClass().getName() + ", " +
						"��������� " + DoubleRange.class.getName());
		}
		
		public DoubleRange getDoubleResult(){
			if (firstAccessFlag) return null;
			DoubleRange dr = new DoubleRange();
			dr.setMinObject(new BigDecimal(minResult));
			dr.setMaxObject(new BigDecimal(maxResult));
			return dr;
		}
		
		public LongRange getLongResult(){
			if (firstAccessFlag) return null;
			return new LongRange((long)minResult, (long)maxResult);
		}
		
		public IntRange getIntegerResult(){
			if (firstAccessFlag) return null;
			return new IntRange((int)maxResult, (int)minResult);
		}
	}
	
	/**
	 * �������� ��������: 
	 * ������ ������������� �� �������� ���������� (����������� � ���� ���)
	 * @author dimitry
	 * 21.10.2006
	 */
	static class NumberRangeCalculatorMax extends NumberRangeCalculator {
				
		public void calculate(DoubleRange range){
			if (range != null){
				double minValue = (range.getMinObject() != null)?range.getMinObject().doubleValue():0.0d;
				double maxValue = (range.getMaxObject() != null)?range.getMaxObject().doubleValue():0.0d;
				
				if (firstAccessFlag || this.minResult > minValue) this.minResult = minValue;
				if (firstAccessFlag || this.maxResult < maxValue) this.maxResult = maxValue;
				
				firstAccessFlag = false;
			}

			// TODO throws ?
		}
	}
	
	/**
	 * ������������ ���������:
	 * ������ ������������ �� �������� ���������� (����������� � ���� ����� ��������� ��������)
	 * @author dimitry
	 * 21.10.2006
	 */
	static class NumberRangeCalculatorMin extends NumberRangeCalculator {

		public void calculate(DoubleRange range){
			if (range != null){
				double minValue = (range.getMinObject() != null)?range.getMinObject().doubleValue():0.0d;
				double maxValue = (range.getMaxObject() != null)?range.getMaxObject().doubleValue():0.0d;
				
				if (firstAccessFlag || this.minResult < minValue) this.minResult = minValue;
				if (firstAccessFlag || this.maxResult > maxValue) this.maxResult = maxValue;
				
				firstAccessFlag = false;
			}

			// TODO throws ?
		}
	}

	/**
	 * ���������� ������
	 * @param values
	 * @param calculator
	 * @return
	 */
	protected static Calculator calculate(List values, Calculator calculator){
		if (values == null || values.isEmpty()) return null;
		
		for(int i=0; i<values.size(); i++){
			calculator.calculate(values.get(i));
		}

		return calculator;
	}
	
	/**
	 * ���������� ��������� ������
	 * @param values
	 * @param calculator
	 * @return
	 */
	protected static NumberCalculator calculateNumber(List values, NumberCalculator calculator){
		return (NumberCalculator) calculate(values, calculator);
	}

	/**
	 * ���������� Boolean ������
	 * @param values
	 * @param calculator
	 * @return
	 */
	protected static BooleanCalculator calculateBoolean(List values, BooleanCalculator calculator){
		return (BooleanCalculator) calculate(values, calculator);
	}
	
	/**
	 * ���������� ������ �������� ����������
	 * @param values
	 * @param calculator
	 * @return
	 */
	protected static NumberRangeCalculator calculateNumberRange(List values, NumberRangeCalculator calculator){
		return (NumberRangeCalculator) calculate(values, calculator);
	}
	
	/**
	 * ������ ����� Long �������� � ������
	 * @param values
	 * @return
	 */
	public static Long calculateLongSum(List values){
		return calculateNumber(values, new NumberCalculatorSum()).getLongResult();
	}

	/**
	 * ������ ����� Double �������� � ������
	 * @param values
	 * @return
	 */
	public static Double calculateDoubleSum(List values){
		return calculateNumber(values, new NumberCalculatorSum()).getDoubleResult();
	}

	/**
	 * ������ ������������� �������� � Long ������
	 * @param values
	 * @return
	 */
	public static Long calculateLongMax(List values){
		return calculateNumber(values, new NumberCalculatorMax()).getLongResult();
	}

	/**
	 * ������ ������������� �������� � Double ������
	 * @param values
	 * @return
	 */
	public static Double calculateDoubleMax(List values){
		return calculateNumber(values, new NumberCalculatorMax()).getDoubleResult();
	}

	/**
	 * ������: ������ �� True � ������ Boolean ��������?
	 * @param values
	 * @return
	 */
	public static Boolean calculateBooleanTrue(List values) {
		return calculateBoolean(values, new BooleanCalculatorTrue()).getResult();
	}

	/**
	 * ������ ����������� (������������) Long ����������
	 * @param values
	 * @return
	 */
	public static LongRange calculateLongRangeMin(List values){
		return calculateNumberRange(values, new NumberRangeCalculatorMin()).getLongResult(); 
	}

	/**
	 * ������ ����������� (������������) Double ���������� 
	 * @param values
	 * @return
	 */
	public static DoubleRange calculateDoubleRangeMin(List values){
		return calculateNumberRange(values, new NumberRangeCalculatorMin()).getDoubleResult();
	}

}
