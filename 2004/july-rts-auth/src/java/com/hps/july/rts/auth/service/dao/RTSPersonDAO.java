package com.hps.july.rts.auth.service.dao;

import com.hps.april.auth.object.Person;

public interface RTSPersonDAO {

	String SERVICE_NAME = "july.rts.auth.dao.personDAO";
	
	Person load(Integer personId);

	Person loadPersonByLogin(String personLogin);

}
