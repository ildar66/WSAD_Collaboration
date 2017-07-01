package com.hps.july.rts.auth.service.dao;

import org.apache.log4j.Logger;

import com.hps.april.common.utils.dao.HibernateJdbcDaoSupport;
import com.hps.april.auth.object.Person;

public class RTSPersonDAOHibernate extends HibernateJdbcDaoSupport implements RTSPersonDAO {

	protected Logger logger = Logger.getLogger(RTSPersonDAOHibernate.class);
	
	public Person load(Integer personId) {
		if (personId == null) return null;
		return (Person) getHibernateTemplate().load(Person.class, personId);
	}

	public Person loadPersonByLogin(String personLogin) {
		if (personLogin == null) return null;
		
		// SQL operation
		Integer personId = new Integer(getJdbcTemplate().queryForInt("Select person.man " +
				"From people as person, operators as operator " +
				"Where operator.loiginid = ? and operator.man = person.man", 
				new Object[] { personLogin }));		
		
		return load(personId);
	}

}
