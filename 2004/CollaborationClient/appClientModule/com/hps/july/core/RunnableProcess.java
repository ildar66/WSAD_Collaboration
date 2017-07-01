/*
 * Created on 16.07.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.core;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RunnableProcess implements Runnable {
	private Query qry;
	private Collaboration adapter;
	/**
	 *
	 */
	public RunnableProcess(Query qry, Collaboration adapter) {
		super();
		this.qry = qry;
		this.adapter = adapter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		if (qry.isDelQuery()) {
			try {
				adapter.findDeletedInSourseDeleteInTarget(qry);
			} catch (CollaborationException e) {
				System.out.println("qry.getReqType= " + qry.getReqType() + " terminated");
                e.printStackTrace();
            }
		}
		else if (qry.isAllQuery()) {
			try {
				adapter.findChangesAndUpdate(qry);
			} catch (CollaborationException e) {
				System.out.println("qry.getReqType= " + qry.getReqType() + " terminated");
                e.printStackTrace();
			}
		}
		else if (qry.isTaskQuery()) {
			try {
				adapter.doTask(qry);
			} catch (CollaborationException e) {
				System.out.println("qry.getReqType= " + qry.getReqType() + " terminated");
                e.printStackTrace();
			}
		}
		else {
			System.err.println("qry.getReqType= " + qry.getReqType());
		}
	}

}
