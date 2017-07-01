package com.hps.july.rts;

/**
 * RTS ("Заявки на расширение региональной ТС")
 * Системное исключение, которое должное быть обработано
 * 
 *  @author abaykov
 *  Created on 12.01.2006
 */
public class SystemException extends Exception {
	
	public SystemException(){
		super();
	}
	
	public SystemException(String message){
		super(message);
	}
	
}
