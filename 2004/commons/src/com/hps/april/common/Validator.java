/*
 *  $Id: Validator.java,v 1.1 2006/11/20 14:01:01 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������    
 */
package com.hps.april.common;

import java.util.List;

/**
 * ��������� ��� ���������� �������� ��������� �������
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2006/11/20 14:01:01 $
 */
public interface Validator {

    /**
     * ����� ���������� �������� ����������� �������
     * @param object - ������ ��� ��������
     * @param errors - ������ ������  
     */
    public void validate(Object object, List errors);

}
