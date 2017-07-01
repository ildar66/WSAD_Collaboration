package com.hps.april.auth.service.dao;

import java.util.Collection;
import java.util.List;

import com.hps.april.auth.object.JulyRole;

/**
 * DAO для сущности Роль 
 * @author Dimitry Krivenko 
 * 26.02.2006
 */
public interface RoleDAO {

    String SERVICE_NAME = "april.auth.dao.roleDAO";

    /**
     * Получение роли по имени. Если роль не найдена,
     * возвращает null
     * @param roleName имя роли
     * @return роль
     */
    JulyRole load(String roleName);

    /**
     * Получение списка ролей по идентификатору пользователя
     * @param operatorId
     * @return
     */
    Collection loadRoles(Integer operatorId);
    /**
     * получение списка ролей
     * @return
     */
    List getRoleList();
    
    List getRoleList(String taskName);

}
