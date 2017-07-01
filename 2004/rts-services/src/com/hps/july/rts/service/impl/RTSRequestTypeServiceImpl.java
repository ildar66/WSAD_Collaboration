package com.hps.july.rts.service.impl;

import com.hps.july.rts.service.RTSRequestTypeService;
import com.hps.july.rts.object.RTSRequestType;

import java.util.*;

/**
 * RTS ("������ �� ���������� ������������ ��")
 * 
 *  @author abaykov
 *  Created on 18.01.2006
 */
public class RTSRequestTypeServiceImpl implements RTSRequestTypeService {

    private final String type_1 = "��������� ������ ������";
    private final String type_2 = "��������� ������������� ������";
    private final String type_3 = "���������� ������������� ������";

    private Map types;

	public RTSRequestTypeServiceImpl() {
		this.types = new HashMap();
		this.types.put(new Integer(1), new RTSRequestType(new Integer(1), type_1));
		this.types.put(new Integer(2), new RTSRequestType(new Integer(2), type_2));
		this.types.put(new Integer(3), new RTSRequestType(new Integer(3), type_3));
	}

	public Collection findAllRTSRequestTypes() {
		return this.types.values();
	}

    public RTSRequestType findRTSRequestType(Integer ident) {
        return (RTSRequestType)this.types.get(ident);
    }
}
