package com.hps.july.rts.object;

import com.hps.april.common.object.ExtensibleObject;

/**
 * RTS ("Заявки на расширение региональной ТС")
 *  Статус Заявки в понятиях только! проекта RTS
 * 
 * Черновик
 * На заполнении исполнителем
 * На рассмотрении
 * На редактировании
 * На визировании
 * Завизирована
 * На утверждении
 * На исполнении
 * Исполнена
 * На тестировании
 * На доработке
 * Принята в работу
 * Закрыта
 * Аннулирована
 *  
 *  @author abaykov
 *  Created on 23.12.2005
 */
public class RTSStatus extends ExtensibleObject {

	public final static String status_0 = "Черновик";
	public final static String status_1 = "На заполнении исполнителем";
	public final static String status_2 = "На рассмотрении";
	public final static String status_3 = "На редактировании";
	public final static String status_4 = "На визировании";
	public final static String status_5 = "Завизирована";
	public final static String status_6 = "На утверждении";
	public final static String status_7 = "На исполнении";
	public final static String status_8 = "Готова к тестированию";
	public final static String status_9 = "Исполнена";
	public final static String status_10 = "На тестировании";
	public final static String status_11 = "На доработке";
	public final static String status_12 = "Принята в работу";
	public final static String status_13 = "Закрыта";
	public final static String status_14 = "Аннулирована";

	public final static int statusId_0 = 0; // Черновик
	public final static int statusId_1 = 1; // На заполнении исполнителем
	public final static int statusId_2 = 2; // На рассмотрении
	public final static int statusId_3 = 3; // На редактировании
	public final static int statusId_4 = 4; // На визировании
	public final static int statusId_5 = 5; // Завизирована
	public final static int statusId_6 = 6; // На утверждении
	public final static int statusId_7 = 7; // На исполнении
	public final static int statusId_8 = 8; // Готова к тестирванию
	public final static int statusId_9 = 9; // Исполнена
	public final static int statusId_10 = 10; // На тестировании
	public final static int statusId_11 = 11; // На доработке
	public final static int statusId_12 = 12; // Принята в работу
	public final static int statusId_13 = 13; // Закрыта
	public final static int statusId_14 = 14; // Аннулирована
	
	/**
	 * Черновик
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
