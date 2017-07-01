/*
 * Created on 07.02.2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.rts.service.impl.dao;

import java.util.List;

import com.hps.july.rts.object.comment.Comment;

/**
 * @author dkrivenko
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface CommentDAO {

	/**
	 * @param resourceId
	 * @return
	 */
	List getCommentList(String requestId);

	/**
	 * @param comment
	 */
	void save(Comment comment);

}
