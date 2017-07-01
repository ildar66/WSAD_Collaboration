package com.hps.july.rts.service.impl.dao;

import java.util.Collection;

import com.hps.april.auth.object.Person;
import com.hps.april.common.FinderException;
import com.hps.july.rts.object.numeration.ConsolidatedRequestNumerationBean;
import com.hps.july.rts.object.numeration.ElementaryRequestNumerationBean;
import com.hps.july.rts.object.numeration.InitiatorRequestNumerationBean;
import com.hps.july.rts.object.numeration.RequestNumerationBean;

public interface RTSRequestNumerationDAO {

	void saveOrUpdate(RequestNumerationBean bean);

	InitiatorRequestNumerationBean getInitiatorRequestNumerationBean(long personInitiatorCode);

	ConsolidatedRequestNumerationBean getConsolidatedRequestNumerationBean();

	ElementaryRequestNumerationBean getElementaryRequestNumerationBean(ConsolidatedRequestNumerationBean consolidatedBean);
	
}
