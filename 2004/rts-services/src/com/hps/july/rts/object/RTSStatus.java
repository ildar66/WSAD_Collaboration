package com.hps.july.rts.object;

import com.hps.april.common.object.ExtensibleObject;

/**
 * RTS ("������ �� ���������� ������������ ��")
 *  ������ ������ � �������� ������! ������� RTS
 * 
 * ��������
 * �� ���������� ������������
 * �� ������������
 * �� ��������������
 * �� �����������
 * ������������
 * �� �����������
 * �� ����������
 * ���������
 * �� ������������
 * �� ���������
 * ������� � ������
 * �������
 * ������������
 *  
 *  @author abaykov
 *  Created on 23.12.2005
 */
public class RTSStatus extends ExtensibleObject {

	public final static String status_0 = "��������";
	public final static String status_1 = "�� ���������� ������������";
	public final static String status_2 = "�� ������������";
	public final static String status_3 = "�� ��������������";
	public final static String status_4 = "�� �����������";
	public final static String status_5 = "������������";
	public final static String status_6 = "�� �����������";
	public final static String status_7 = "�� ����������";
	public final static String status_8 = "������ � ������������";
	public final static String status_9 = "���������";
	public final static String status_10 = "�� ������������";
	public final static String status_11 = "�� ���������";
	public final static String status_12 = "������� � ������";
	public final static String status_13 = "�������";
	public final static String status_14 = "������������";

	public final static int statusId_0 = 0; // ��������
	public final static int statusId_1 = 1; // �� ���������� ������������
	public final static int statusId_2 = 2; // �� ������������
	public final static int statusId_3 = 3; // �� ��������������
	public final static int statusId_4 = 4; // �� �����������
	public final static int statusId_5 = 5; // ������������
	public final static int statusId_6 = 6; // �� �����������
	public final static int statusId_7 = 7; // �� ����������
	public final static int statusId_8 = 8; // ������ � �����������
	public final static int statusId_9 = 9; // ���������
	public final static int statusId_10 = 10; // �� ������������
	public final static int statusId_11 = 11; // �� ���������
	public final static int statusId_12 = 12; // ������� � ������
	public final static int statusId_13 = 13; // �������
	public final static int statusId_14 = 14; // ������������
	
	/**
	 * ��������
	 */
	public final static RTSStatus DRAFT = new RTSStatus(new Integer(statusId_0), status_0);
	
	// TODO add other status definition ?
	
	
	public RTSStatus(Integer Id, String name){
		setId(Id);
		setName(name);
	}
	// getName() & setName from ValueObject
	// getId() & setId() from ValueObject
	
	public String toString(){
		return "status id ="+ getId()+"; status name = "+getName()+";";
	}
}
