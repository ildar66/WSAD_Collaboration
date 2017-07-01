package com.hps.july.util;

/**
 * Класс для профилирования времени выполнения частей программы.
 * Creation date: (26.09.2003 17:23:33)
 * @author: Dmitry Dneprov
 */
public class TimeCounter {
	private long starttime;
	private java.lang.String counterid;
/**
 * TimeCounter constructor.
 */
public TimeCounter(String ACounterId) {
	super();
	counterid = ACounterId;
    start();
}
/**
 * End measuring of time & print it to log.
 * Creation date: (26.09.2003 17:24:50)
 */
public void finish(String message) {
	long endtime = System.currentTimeMillis();
	System.out.println(counterid + " " + message + " time = " + ((endtime-starttime)/1000.0) + " s." );
}
/**
 * Start measuring of time.
 * Creation date: (26.09.2003 17:24:01)
 */
public void start() {
	starttime = System.currentTimeMillis();
}
}

