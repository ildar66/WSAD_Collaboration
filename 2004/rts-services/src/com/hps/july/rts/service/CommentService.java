/*
 * Created on 07.02.2006
 */
package com.hps.july.rts.service;

import java.util.List;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.Person;
import com.hps.april.common.Service;

/**
 * @author dkrivenko
 *
 */
public interface CommentService extends Service {

    public String SERVICE_NAME = "july.rts.service.commentService";

    /**
     *
     * @param resourceId
     * @return
     */
    public List getCommentList(Person person, String requestId);

    /**
     * ��������� ��������� ���������
     * @param comment
     */
    public void addInfoComment(AuthInfo authInfo, String requestNumber, String comment);

    /**
     * ��������� ��������� ������������ (�����������)
     * @param comment
     */
    public void addUserComment(AuthInfo authInfo, String requestNumber, String comment);

    /**
     * ��������� ��������������� ��������� (������������)
     * @param warning
     */
    public void addWarning(AuthInfo authInfo, String requestNumber, String warning);

    /**
     * ��������� ��������� �� ������
     * @param error
     */
    public void addError(AuthInfo authInfo, String requestNumber, String error);

}
