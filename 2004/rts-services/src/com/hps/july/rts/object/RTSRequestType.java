package com.hps.july.rts.object;

import com.hps.april.common.object.ExtensibleObject;

/**
 * RTS ("������ �� ���������� ������������ ��")
 * ��� ������ � �������� ������! ������� RTS
 * 
 * - ��������� ������ ������ - 1
 * - ��������� ������������� ������ - 2
 * - ���������� ������������� ������ - 3
 * 
 *  @author abaykov
 *  Created on 26.12.2005
 */
public class RTSRequestType extends ExtensibleObject {
	private static final long serialVersionUID = 1L;

	/**
	 * ��������� ������ ������
	 */
	
	public RTSRequestType(Integer Id, String name){
		setId(Id);
		setName(name);
	}
}
