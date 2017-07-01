package com.hps.july.rts.service.impl.dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hps.april.auth.object.Person;
import com.hps.april.common.FinderException;
import com.hps.july.rts.service.impl.dao.RTSRequestNumerationDAO;
import com.hps.july.rts.auth.object.RTSRole;
import com.hps.july.rts.object.numeration.ConsolidatedRequestNumerationBean;
import com.hps.july.rts.object.numeration.ElementaryRequestNumerationBean;
import com.hps.july.rts.object.numeration.InitiatorRequestNumerationBean;
import com.hps.july.rts.object.numeration.RequestNumerationBean;


public class RTSRequestNumerationDAOHibernate extends HibernateDaoSupport implements RTSRequestNumerationDAO {

	public void saveOrUpdate(RequestNumerationBean bean) {
		getHibernateTemplate().saveOrUpdate(bean);
	}

	public InitiatorRequestNumerationBean getInitiatorRequestNumerationBean(long personInitiatorCode) {
		List result = getHibernateTemplate().find(
				"From InitiatorRequestNumerationBean as initiatorBean " +
				"Where initiatorBean.initiatorCode = ? ", 
				new Object[] { new Long(personInitiatorCode) });

		if (!result.isEmpty()) {
			InitiatorRequestNumerationBean bean =
				(InitiatorRequestNumerationBean) result.get(0);
			return bean; 
		} 
		return null;
	}

	public ConsolidatedRequestNumerationBean getConsolidatedRequestNumerationBean() {
		List result = getHibernateTemplate().find(
				"From ConsolidatedRequestNumerationBean as consolidatedBean");

		if (!result.isEmpty()) return (ConsolidatedRequestNumerationBean) result.get(0);
		return null;
	}

	public ElementaryRequestNumerationBean getElementaryRequestNumerationBean(ConsolidatedRequestNumerationBean consolidatedBean) {
		List result = getHibernateTemplate().find("From ElementaryRequestNumerationBean as elementaryBean " +
				"Where elementaryBean.requestCode = ?", 
				new Object[] { new Long(consolidatedBean.getRequestCode()) });
		
		if (!result.isEmpty()) return (ElementaryRequestNumerationBean) result.get(0);
		return null;
	}
}
