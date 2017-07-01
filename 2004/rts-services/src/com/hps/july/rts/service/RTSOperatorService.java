package com.hps.july.rts.service;

import com.hps.april.auth.object.Person;
import com.hps.april.common.Service;
import com.hps.april.common.CreateObjectException;
import com.hps.april.common.RemoveObjectException;
import com.hps.april.common.StoreObjectException;
import com.hps.july.rts.auth.object.RTSGroup;
import com.hps.july.rts.auth.object.RTSRole;

import java.util.Collection;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @author <a href="mailto:abaykov@beeline.ru">Albert Baykov</a>
 * Date: 07.12.2005
 * Time: 19:58:01
 */
public interface RTSOperatorService extends Service {

    public final String SERVICE_NAME = "july.rts.service.operatorService";

	public Collection findPerformersByName(String name, Integer sortType, Integer asc);

	public Collection findRegionManagersByName(String name, Integer regionId, Integer sortType, Integer asc);

    public Collection findFilialManagersByName(String name, Integer regionId, Integer sortType, Integer asc);

	public Collection findInitiatorsByName(String name, Integer sortType, Integer asc);

	public Collection findGroupsByName(String name, Integer regionId, Integer filialId, Integer roleId, Integer sortType, Integer asc);

	public Collection findGroupsPerson(Integer groupId, Integer sortType, Integer asc);

	public void saveGroupsPerson(Integer groupId, Collection persons) throws StoreObjectException;

	public void saveRolesPerson(Integer roleId, Collection persons) throws StoreObjectException;

	public Collection findPersonByRole(Integer roleId, Integer sortType, Integer asc);

	public Collection findPersonByRoleAndName(Integer roleId, String name, Integer sortType, Integer asc);

	public RTSGroup findGroup(Integer ident);

    public RTSRole findRTSRole(Integer ident);
    
    public Collection findRTSRoles();

	public void saveGroup(RTSGroup group) throws CreateObjectException;

	public void updateGroup(RTSGroup group) throws StoreObjectException;

	public void removeGroup(Integer ident) throws RemoveObjectException;

	public void removeGroupsPerson(Integer groupId, Integer peopleId) throws RemoveObjectException;

	public void removeRolesPerson(Integer roleId, Integer peopleId) throws RemoveObjectException;
	
	public Collection findPersonsByName(String name);

    public Person findPersonsById(Integer id);

    public Collection findGroupByPerson(Person person);

    public Collection findRTSPersonByName(String name, Integer sortType, Integer asc);

    public String findPersonByRoleAndGroup(RTSRole role, RTSGroup group);

//    public Collection findGroupsByRole(String name, Integer sortType, Integer asc);

}
