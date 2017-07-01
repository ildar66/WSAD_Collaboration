package com.hps.april.auth.service.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.hps.april.auth.object.Person;


public class PersonDaoHibarnate extends HibernateDaoSupport implements PersonDao{

	public List getPersonBySername(String sername) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Person.class);
        criteria.add(Restrictions.ilike("lastName", sername));
        List personList = getHibernateTemplate().findByCriteria(criteria);
        return personList;

	}
	

}
