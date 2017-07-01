package com.hps.april.auth.service.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hps.april.auth.object.Operator;
import com.hps.april.auth.object.Person;

/**
 * @author Dimitry Krivenko 
 * 26.02.2006
 */
public class OperatorDAOHibernate extends HibernateDaoSupport implements
        OperatorDAO {

    public Operator load(String operatorLogin) {
        List operatorList = getHibernateTemplate().find(
                "From Operator as operator Where operator.login = ?",
                new Object[] { operatorLogin });

        if (!operatorList.isEmpty()) return (Operator) operatorList.get(0);
        return null;
    }

    public Operator load(Integer operatorId) {
        try {
            return (Operator) getHibernateTemplate().load(Operator.class, operatorId);
        } catch (DataAccessException e){
            logger.warn(e);
            return null;
        }
    }

    public Person loadPerson(Integer personId) {
        try {
            return (Person)getHibernateTemplate().load(Person.class, personId);
        } catch (DataAccessException e){
            logger.warn(e);
            return null;
        }
    }

}
