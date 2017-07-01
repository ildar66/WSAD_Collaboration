/*
 * Created on 07.02.2006
 *
 */
package com.hps.july.rts.service.impl;

import java.util.Date;
import java.util.List;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.july.rts.service.CommentService;
import com.hps.july.rts.service.impl.dao.CommentDAO;
import com.hps.july.rts.object.comment.Comment;

/**
 * @author dkrivenko
 */
public class CommentServiceImpl implements CommentService {
	
	protected CommentDAO commentDAO;
	protected RTSAuthService authService;
	
	/**
     *  Метод получает все комментарии к данной заявки
     * @return список комментариев
	 */
	public List getCommentList(Person person, String requestId) {
		return commentDAO.getCommentList(requestId);
	}

    /**
     * Добавляет системное сообщение
     * @param comment
     */
    public void addInfoComment(AuthInfo authInfo, String requestNumber, String comment) {
        Comment commentObj = createComment(requestNumber, comment);
        commentObj.setType(Comment.COMMENT_INFO);
        saveComment(authInfo, commentObj);
    }

    /**
     * Добавляет сообщение пользователя (комментарий)
     * @param comment
     */
    public void addUserComment(AuthInfo authInfo, String requestNumber, String comment) {
        Comment commentObj = createComment(requestNumber, comment);
        commentObj.setType(Comment.COMMENT_USER);
        saveComment(authInfo, commentObj);
    }

    /**
     * Добавляет предупреждающие сообщение (некорректное)
     * @param warning
     */
    public void addWarning(AuthInfo authInfo, String requestNumber, String warning) {
        Comment commentObj = createComment(requestNumber, warning);
        commentObj.setType(Comment.COMMENT_WARN);
        saveComment(authInfo, commentObj);
    }

    /**
     * Добавляет сообщение об ошибке
     * @param error
     */
    public void addError(AuthInfo authInfo, String requestNumber, String error) {
        Comment commentObj = createComment(requestNumber, error);
        commentObj.setType(Comment.COMMENT_ERROR);
        saveComment(authInfo, commentObj);
    }

	public RTSAuthService getAuthService() {
		return authService;
	}
	public void setAuthService(RTSAuthService authService) {
		this.authService = authService;
	}

	/**
	 * @return CommentDAO
	 */
	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	/**
	 * @param commentDAO
	 */
	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

    //-- private methods

    /**
     *  создание обхекта comment
     * @param requestNumber
     * @param text
     * @return объект Comment
     */
    private Comment createComment(String requestNumber, String text) {
        Comment comment = new Comment();
        comment.setRequestId(requestNumber);
        comment.setText(text);
        return comment;
    }

    /**
     *  Сохранение объекта Comment в БД
     */
	private void saveComment(AuthInfo authInfo, Comment comment) {
		if (comment == null)
			throw new IllegalArgumentException("Comment to save can not be null");

		if (authInfo == null)
			throw new IllegalArgumentException("AuthInfo can not be null");

		Person person = authService.getPerson(authInfo);

		comment.setDate(new Date());
		comment.setPerson(person);

		commentDAO.save(comment);
	}

}
