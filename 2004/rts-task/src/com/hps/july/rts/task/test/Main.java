/*
 *  $Id: Main.java,v 1.2 2007/04/29 17:22:03 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.task.test;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/04/29 17:22:03 $
 */
public class Main {

	public static void main(String[] args) {
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date lastSendingTime = sdf.parse("9.05.2007 23:35", new ParsePosition(0));
		calendar.setTime(lastSendingTime);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		System.out.println("d=" + calendar.getTime());
		System.out.println("d1=" + calendar.getTime().getTime());

		GregorianCalendar current = new GregorianCalendar();
		current.setTime(new Date());
		current.set(Calendar.HOUR_OF_DAY, 0);
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 0);
		current.set(Calendar.MILLISECOND, 0);
		System.out.println("c=" + current.getTime());
		System.out.println("c1=" + current.getTime().getTime());

		System.out.println("current.equals(calendar)="+current.getTime().equals(calendar.getTime()));
		System.out.println("current.long(calendar)="+(current.getTime().getTime() == calendar.getTime().getTime()));
		System.out.println("current.long(calendar)="+(current.getTime().getTime() == calendar.getTime().getTime()));


		System.out.println("res=" +checkLimitTimeToDate(7, lastSendingTime));

	}

	private static boolean checkLimitTimeToDate(int countDay, Date limit) {
		if(System.currentTimeMillis() > limit.getTime()) {
			//текущая дата первышает лимит значит лимит исчерпан, можно сразу посылать письмо
			return true;
		}

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(limit);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		int i = 0;
		//учитываем только рабочие дени
		//по одному дню, до нужного количества
		while(i < countDay) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
			//Проверяем если не выходные то увеличиваем счет иначе пропускаем
			if(!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
					|| calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
				i++;
			}
		}
		if(System.currentTimeMillis() > calendar.getTime().getTime()) {
			//текущая дата первышает (требумая дата минус количество рабочих дней)
			// значит текущая дата находится в интервале, можно посылать письмо
			return true;
		}
		//граница (требумая дата минус количество рабочих дней)
		//не достигнута
		return false;



	}


}
