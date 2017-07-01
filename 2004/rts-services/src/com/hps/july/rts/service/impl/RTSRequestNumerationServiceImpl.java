package com.hps.july.rts.service.impl;

import java.text.ParseException;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.regexp.RE;

import com.hps.april.auth.object.Person;
import com.hps.april.common.FinderException;
import com.hps.april.common.utils.FormatUtils;
import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.july.rts.auth.service.dao.RTSGroupDAO;
import com.hps.july.rts.auth.service.dao.RTSRoleDAO;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.service.RTSOperatorService;
import com.hps.july.rts.service.RTSRequestNumerationService;
import com.hps.july.rts.service.impl.dao.RTSRequestNumerationDAO;
import com.hps.july.rts.auth.object.RTSGroup;
import com.hps.july.rts.auth.object.RTSRole;
import com.hps.july.rts.object.numeration.ConsolidatedRequestNumerationBean;
import com.hps.july.rts.object.numeration.ElementaryRequestNumerationBean;
import com.hps.july.rts.object.numeration.InitiatorRequestNumerationBean;
import com.hps.july.rts.object.numeration.RequestNumerationBean;

/**
 * @author Dimitry Krivenko 
 * 16.02.2006
 */
public class RTSRequestNumerationServiceImpl implements
		RTSRequestNumerationService {

	protected Logger logger = Logger.getLogger(getClass());
	
	protected RTSRequestNumerationDAO requestNumerationDAO;
	protected RTSRoleDAO rtsRoleDAO;
	protected RTSAuthService authService;
	protected RTSGroupDAO rtsGroupDAO;
	protected RTSOperatorService operatorService;
	
	public String generateInitiatorRequestNumber(Person person) {
		if (person == null)
			throw new IllegalArgumentException("Person can not be null");
		
		if (person.getId() == null)
			throw new IllegalArgumentException("Person id not defined");
		
		// (person.getRtsGroupList() == null)
		person = authService.getPerson(person.getId());
		long personInitiatorCode = getPersonInitiatorCode(person);
		InitiatorRequestNumerationBean bean = 
			requestNumerationDAO.getInitiatorRequestNumerationBean(personInitiatorCode);
		// Данный пользователь первый раз делает заявку
		if (bean == null) {
			bean = new InitiatorRequestNumerationBean();
			bean.setRequestDate(new Date());
			bean.setRequestCode(0);
			bean.setInitiatorCode(personInitiatorCode);
		} else {
			// Если наступил другой год, нумерация сбрасывается
			 
			if (isNumerationReboot(bean)) { 
				bean.setRequestCode(0);
			}
		}
		bean.setRequestDate(new Date());
		bean.incrementRequestCode();
		requestNumerationDAO.saveOrUpdate(bean);
		return bean.toString();
	}
	
	
	protected boolean isNumerationReboot(RequestNumerationBean bean) {
		Calendar calendar = Calendar.getInstance();
		Date requestDate = bean.getRequestDate();
		
		calendar.setTime(requestDate);
		int requestYear = calendar.get(Calendar.YEAR);
		
		calendar.setTime(new Date());
		int nowYear = calendar.get(Calendar.YEAR);
		
		return requestYear < nowYear;
	}

	protected long getPersonInitiatorCode(Person person) {
		Collection groups = operatorService.findGroupByPerson(person);

		RTSGroup group = null;
		for(Iterator it = groups.iterator(); it.hasNext();){ // Ищем нужную группу данного инициатора
			RTSGroup curGroup = (RTSGroup) it.next();
			if (curGroup.getRoleId()!= null &&
				curGroup.getRoleId().intValue() == RTSRole.INITIATOR.intValue()) {
				group = curGroup; 
			}
		}
		if (group == null) return 0;
		if(group.getInitiatorCode()!=null) 
			return group.getInitiatorCode().intValue(); 

//		 FIXME: cannot found initiatorCode for person
		logger.warn("cannot found initiatorCode for person");
		return 0;
	}


	public String generateConsolidatedRequestNumber() {
		ConsolidatedRequestNumerationBean bean = 
			requestNumerationDAO.getConsolidatedRequestNumerationBean();
		// Данный пользователь первый раз делает заявку
		if (bean == null) {
			bean = new ConsolidatedRequestNumerationBean();
			bean.setRequestDate(new Date());
			bean.setRequestCode(0);
		}
	
		else {
			// Если наступил другой год, нумерация сбрасывается 
			if (isNumerationReboot(bean)) 
				bean.setRequestCode(0);
		}
		bean.setRequestDate(new Date());
		bean.incrementRequestCode();
		requestNumerationDAO.saveOrUpdate(bean);

		return bean.toString();
	}

    public String generateElementaryRequestNumber(ConsolidatedRequest consolidatedRequest) {
        ConsolidatedRequestNumerationBean consolidatedBean =
            parseConsolidatedNumeration(consolidatedRequest);

        ElementaryRequestNumerationBean bean =
            requestNumerationDAO.getElementaryRequestNumerationBean(consolidatedBean);
        // Данный пользователь первый раз делает заявку
        if (bean == null) {
            bean = new ElementaryRequestNumerationBean();
        }

        bean.setConsolidatedNumetationBean(consolidatedBean);
        bean.setNumber(consolidatedRequest.getNumber());
        bean.incrementElementaryCode();
        requestNumerationDAO.saveOrUpdate(bean);

        return bean.toString();
    }

	private ConsolidatedRequestNumerationBean parseConsolidatedNumeration(ConsolidatedRequest consolidatedRequest) {
		// number is NNNN-YY
		String number = consolidatedRequest.getNumber();
		RE re = new RE("(\\d*)-(\\d{2})");
		if (re.match(number)){
			String requestCode = re.getParen(1);
			
			try {
				ConsolidatedRequestNumerationBean bean = new ConsolidatedRequestNumerationBean();
				bean.setRequestCode(FormatUtils.toInteger(requestCode).longValue());
				bean.setRequestDate(consolidatedRequest.getCreated());
				return bean;
			} catch (Exception e) {
				logger.error(e,e);
			}
		}
		throw new IllegalArgumentException("Coud'n parse consolidatedRequestNumber :" + number);
	}

	public RTSRequestNumerationDAO getRequestNumerationDAO() {
		return requestNumerationDAO;
	}


	public void setRequestNumerationDAO(RTSRequestNumerationDAO requestNumerationDAO) {
		this.requestNumerationDAO = requestNumerationDAO;
	}


	public RTSRoleDAO getRtsRoleDAO() {
		return rtsRoleDAO;
	}
	public void setRtsRoleDAO(RTSRoleDAO rtsRoleDAO) {
		this.rtsRoleDAO = rtsRoleDAO;
	}
	
	public RTSAuthService getAuthService() {
		return authService;
	}
	public void setAuthService(RTSAuthService authService) {
		this.authService = authService;
	}

	public RTSOperatorService getOperatorService() {
		return operatorService;
	}

	public void setOperatorService(RTSOperatorService operatorService) {
		this.operatorService = operatorService;
	}

	public String generateRentElementaryRequestNumber(ElementaryRequest elementaryRequest) {
		ConsolidatedRequestNumerationBean consolidatedBean = 
			parseElementaryNumeration(elementaryRequest);
		
		ElementaryRequestNumerationBean bean = 
			requestNumerationDAO.getElementaryRequestNumerationBean(consolidatedBean);
		// Данный пользователь первый раз делает заявку
		if (bean == null) {
			bean = new ElementaryRequestNumerationBean();
		} 

		bean.setConsolidatedNumetationBean(consolidatedBean);
		bean.setNumber(elementaryRequest.getNumber());
		bean.incrementElementaryCode();
		requestNumerationDAO.saveOrUpdate(bean);

		return bean.toString();
	}

	private ConsolidatedRequestNumerationBean parseElementaryNumeration(ElementaryRequest elementaryRequest) {
		// number is NNNN-YY
		String number = elementaryRequest.getNumber();
		RE re = new RE("(\\d*)-(\\d{2})");
		if (re.match(number)){
			String requestCode = re.getParen(1);
			
			try {
				ConsolidatedRequestNumerationBean bean = new ConsolidatedRequestNumerationBean();
				bean.setRequestCode(FormatUtils.toInteger(requestCode).longValue());
				bean.setRequestDate(elementaryRequest.getCreated());
				return bean;
			} catch (Exception e) {
				logger.error(e,e);
			}
		}
		throw new IllegalArgumentException("Coud'n parse consolidatedRequestNumber :" + number);
	}

	public String getInfoAboutBean() {
		String returnValue = "requestNumerationDAO = "+ requestNumerationDAO.toString() + 
								";  requestNumerationDAO = "+requestNumerationDAO.toString()+";";
		return returnValue;  
	}

    public String generateElementaryRequestNumber(long elementaryCode, ConsolidatedRequest consolidatedRequest) {
        ConsolidatedRequestNumerationBean consolidatedBean =
            parseConsolidatedNumeration(consolidatedRequest);

        ElementaryRequestNumerationBean bean =
            requestNumerationDAO.getElementaryRequestNumerationBean(consolidatedBean);

        // Данный пользователь первый раз делает заявку
        if (bean == null) {
            bean = new ElementaryRequestNumerationBean();
        }

        bean.setConsolidatedNumetationBean(consolidatedBean);
        bean.setNumber(consolidatedRequest.getNumber());
        bean.setElementaryCode(elementaryCode);
        requestNumerationDAO.saveOrUpdate(bean);
        return bean.toString();
    }



}
