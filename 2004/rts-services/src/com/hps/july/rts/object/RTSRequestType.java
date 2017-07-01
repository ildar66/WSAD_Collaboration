package com.hps.july.rts.object;

import com.hps.april.common.object.ExtensibleObject;

/**
 * RTS ("Заявки на расширение региональной ТС")
 * Тип Заявки в понятиях только! проекта RTS
 * 
 * - Включение нового канала - 1
 * - Изменение существующего канала - 2
 * - Отключение существующего канала - 3
 * 
 *  @author abaykov
 *  Created on 26.12.2005
 */
public class RTSRequestType extends ExtensibleObject {
	private static final long serialVersionUID = 1L;

	/**
	 * Включение нового канала
	 */
	
	public RTSRequestType(Integer Id, String name){
		setId(Id);
		setName(name);
	}
}
