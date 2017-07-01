package com.hps.july.rts.service.impl.dao;

import java.util.Collection;

import org.hibernate.type.Type;

import com.hps.july.rts.SystemException;
import com.hps.july.rts.object.request.ElementaryRequest;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 27.02.2006
 * Time: 10:17:53
 */
public interface ElementaryRequestDAO {

    public Collection findElemenaryRequestsBySQL(String sqlQuery, Object [] params, Type [] types) throws SystemException;

    public ElementaryRequest findElementaryRequest(Integer id) throws SystemException;

    public void saveElementaryRequest(ElementaryRequest request) throws SystemException;

    public void updateElementaryRequest(ElementaryRequest request) throws SystemException;

    public void removeElementaryRequest(ElementaryRequest request) throws SystemException;

}
