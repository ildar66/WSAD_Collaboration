/*
 * Created on 07.02.2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.rts.service.impl.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hps.july.rts.object.comment.Comment;

/**
 * @author dkrivenko
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CommentDAOHibernate extends HibernateDaoSupport implements CommentDAO {

	/* (non-Javadoc)
	 * @see com.hps.july.rts.service.impl.initiatorRequestDAO.CommentDAO#getCommentList(java.lang.Integer)
	 */
	public List getCommentList(final String requestId) {
//		return getHibernateTemplate().find(
//			"From Comment as comment Where comment.requestId = ?",
//			new Object[] { requestId });
			
		return (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("From Comment as comment Where comment.requestId = ?");
				query.setString(0,requestId);
				
				return query.list();
			}});			
	}

	/* (non-Javadoc)
	 * @see com.hps.july.rts.service.impl.initiatorRequestDAO.CommentDAO#save(com.hps.july.rts.object.comment.Comment)
	 */
	public void save(final Comment comment) {
		logger.debug(comment);
	
		getHibernateTemplate().save(comment);
	}
	
	
	
	
	
}
