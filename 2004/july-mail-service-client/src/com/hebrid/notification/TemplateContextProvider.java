/*
 *  $Id: TemplateContextProvider.java,v 1.1 2007/02/21 17:39:11 mmorev Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������    
 */
package com.hebrid.notification;

import java.util.Map;

/**
 * ��������� ����������� ����� ���������� ��������� ��������� ��� ����������
 * ��������� ������������� ������
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/02/21 17:39:11 $
 */
public interface TemplateContextProvider {

    /**
     * ����� ���������� ������� ���������� ��������� ��� ��������
     * ���� ������
     * @param initParams 
     * @return ������� ����������� ���������� ����������
     */
    public Map findContext(Map initParams);
}
