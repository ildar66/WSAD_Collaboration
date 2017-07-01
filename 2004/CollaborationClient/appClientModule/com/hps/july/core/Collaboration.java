package com.hps.july.core;

/**
 * Интерфейс для синхронизации баз данных.
 *
 * @author Shafigullin Ildar
 * @see Query
 * @see DefaultCollaboration
 */
public interface Collaboration{
    /**
     *  Поиск изменений в SourseDB и их отражение в TargerDB:
     *  Тип запроса ALL_
     */
    public abstract void findChangesAndUpdate(Query argQry) throws CollaborationException;

    /**
     * Поиск записей, удаленных в SourseDB и их удаление в TargerDB:
     *  Тип запроса DEL_
     */
    public abstract void findDeletedInSourseDeleteInTarget(Query argQry) throws CollaborationException;

	/**
	 * вызов сторонних процедур и прочее:
     * Тип запроса TASK_
	 */
	public abstract void doTask(Query argQry) throws CollaborationException;

}