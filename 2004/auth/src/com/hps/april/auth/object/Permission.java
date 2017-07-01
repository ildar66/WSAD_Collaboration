package com.hps.april.auth.object;

/**
 * Интерффейс для описание права пользователя на действия.
 * Должен быть реализован для каждого модуля.    
 * @author dimitry
 * 01.12.2006
 */
public interface Permission {

	boolean equals(Object obj);
}
