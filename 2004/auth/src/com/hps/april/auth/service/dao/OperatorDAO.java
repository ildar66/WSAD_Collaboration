package com.hps.april.auth.service.dao;

import com.hps.april.auth.object.Operator;
import com.hps.april.auth.object.Person;

/**
 * DAO для сущности Оператор
 * @author Dimitry Krivenko 
 * 26.02.2006
 */
public interface OperatorDAO {

    /**
     * Имя сервиса в контексте Spring
     */
    String SERVICE_NAME = "april.auth.dao.operatorDAO";

    /**
     * Получение оператора по логину. Если оператор не найден возвращает null
     * @param operatorLogin
     * @return
     */
    Operator load(String operatorLogin);

    /**
     * Получение оператора по его id. Если оператор не найден возвращается null
     * @param operatorId
     * @return
     */
    Operator load(Integer operatorId);

    public Person loadPerson(Integer personId);

}
