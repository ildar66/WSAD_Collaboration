package com.hps.beeline.initialDataLoader.data;

import com.hps.framework.sql.StoredProc;
import com.hps.framework.exception.BaseException;
import com.hps.framework.utils.AdvHashMap;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 01.04.2005
 * Time: 14:01:19
 * To change this template use File | Settings | File Templates.
 */
public class SiteDocTypeInfo {
    private String publicName;
    private String internalAlias;
    private Integer typeCode=null;

    public SiteDocTypeInfo(String publicName, String internalAlias) {
        this.publicName = publicName;
        this.internalAlias = internalAlias;
    }

    public String getPublicName() {
        return publicName;
    }

    public String getInternalAlias() {
        return internalAlias;
    }

    public Integer getTypeCode() throws BaseException {
        if(typeCode==null)
            typeCode = AbstractStoragePlaceHelper.getNamedValueAsInteger(internalAlias);
        return typeCode;
    }

}
