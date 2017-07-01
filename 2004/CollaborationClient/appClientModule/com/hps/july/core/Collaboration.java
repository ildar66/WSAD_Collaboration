package com.hps.july.core;

/**
 * ��������� ��� ������������� ��� ������.
 *
 * @author Shafigullin Ildar
 * @see Query
 * @see DefaultCollaboration
 */
public interface Collaboration{
    /**
     *  ����� ��������� � SourseDB � �� ��������� � TargerDB:
     *  ��� ������� ALL_
     */
    public abstract void findChangesAndUpdate(Query argQry) throws CollaborationException;

    /**
     * ����� �������, ��������� � SourseDB � �� �������� � TargerDB:
     *  ��� ������� DEL_
     */
    public abstract void findDeletedInSourseDeleteInTarget(Query argQry) throws CollaborationException;

	/**
	 * ����� ��������� �������� � ������:
     * ��� ������� TASK_
	 */
	public abstract void doTask(Query argQry) throws CollaborationException;

}