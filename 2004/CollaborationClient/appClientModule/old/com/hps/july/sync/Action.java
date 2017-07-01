package old.com.hps.july.sync;

/**
 * @author Dmitry Dneprov
 *
 * Перечень возможных действий по синхронизации объекта
 */
public interface Action {
	public final static int UPDATE = 1;
	public final static int DELETE = 2;
}