package com.hps.july.rts.service.impl;

import com.hps.july.rts.service.ElementaryRequestService;
import com.hps.july.rts.service.RTSRequestNumerationService;
import com.hps.july.rts.service.RTSOperatorService;
import com.hps.july.rts.service.impl.dao.ElementaryRequestDAO;
import com.hps.july.rts.auth.object.RTSRole;
import com.hps.july.rts.object.request.AbstractRequest;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.object.request.Request;
import com.hps.july.rts.object.request.utils.RequestUtils;
import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.july.rts.SystemException;
import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.StringUtils;
import com.hps.july.rts.core.process.DefaultProcess;

import java.util.*;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.type.Type;
import org.hibernate.HibernateException;
import org.apache.regexp.RE;

/**
 *
 *
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 27.02.2006
 * Time: 10:12:39
 */
public class ElementaryRequestServiceImpl
        extends DefaultProcess implements ElementaryRequestService {

    protected ElementaryRequestDAO elementaryRequestDAO;
    private RTSRequestNumerationService numerationService;
    private RTSOperatorService operatorService;
    private RTSAuthService authService;

    public ElementaryRequestServiceImpl() {
    }

    public RTSAuthService getAuthService() {
        return authService;
    }

    public void setAuthService(RTSAuthService authService) {
        this.authService = authService;
    }

    public ElementaryRequestDAO getElementaryRequestDAO() {
        return elementaryRequestDAO;
    }

    public void setElementaryRequestDAO(ElementaryRequestDAO elementaryRequestDAO) {
        this.elementaryRequestDAO = elementaryRequestDAO;
    }

    public RTSRequestNumerationService getNumerationService() {
        return numerationService;
    }

    public void setNumerationService(RTSRequestNumerationService numerationService) {
        this.numerationService = numerationService;
    }

    public RTSOperatorService getOperatorService() {
        return operatorService;
    }

    public void setOperatorService(RTSOperatorService operatorService) {
        this.operatorService = operatorService;
    }

    public Collection findAllElementaryRequests(Person regManager) throws SystemException {
        Collection requests = null;
        StringBuffer sqlQuery = new StringBuffer("FROM ElementaryRequest WHERE regManager = ?");
        try{
            //TODO: проверка прав польователя ! если админ то видно все заявки !!!
            requests = this.elementaryRequestDAO.findElemenaryRequestsBySQL(sqlQuery.toString(), new Object [] { regManager }, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }

    public ElementaryRequest findElementaryRequest(AuthInfo authInfo, Integer id) throws SystemException {
        if(id == null) throw new SystemException("Cannot find ElementaryRequest, cause id is null");
        if(authInfo == null) throw new SystemException("Cannot find ElementaryRequest, cause regManager is unknown");

        return elementaryRequestDAO.findElementaryRequest(id);
    }

    public Collection findElementaryRequests(AuthInfo executor, Boolean isSerching,
                                             Boolean isNumber, String number,
                                             Boolean isDateType, String dateTypeSelect, Date dateType,
                                             Boolean isRtsRequestType, String rtsRequestType,
                                             Boolean isRtsStatus, String rtsStatus,
                                             Boolean isChannelSetting, String channelSetting,
                                             Boolean isChannelType, String channelType,
                                             String priority,
                                             Boolean isSearchAddrA, String searchAddrA,
                                             Boolean isSearchAddrB, String searchAddrB) throws SystemException {

        Collection requests = null;
        StringBuffer sqlQuery = new StringBuffer(" FROM ElementaryRequest ");
        System.out.println("currentExcecutor ["+executor+"] ");
        boolean isWhere = false;
        sqlQuery.append(" WHERE 1 = 1 ");
        List params = new ArrayList();
        List types = new ArrayList();
        if( isNumber.booleanValue() ) {
            sqlQuery.append(" AND UPPER(number) LIKE ? ");
            params.add(StringUtils.prepareFindString(number));
            //types.add(Hibernate.STRING);
        }
        if( isDateType.booleanValue() ) {
            if(dateTypeSelect.equalsIgnoreCase("s")) {
                sqlQuery.append(" AND created < ? ");
            } else if(dateTypeSelect.equalsIgnoreCase("e")) {
                sqlQuery.append(" AND inWorkDate < ? ");
            }
            params.add(dateType);
            //types.add(Hibernate.DATE);
        }
        if( isRtsRequestType.booleanValue() ) {
            sqlQuery.append(" AND rtsRequestTypeId = ? ");
            params.add(new Integer(rtsRequestType));
            //types.add(Hibernate.STRING);
        }
        if( isRtsStatus.booleanValue() ) {
            sqlQuery.append(" AND rtsStatusId = ? ");
            params.add(new Integer(rtsStatus));
            //types.add(Hibernate.STRING);
        } else {
            sqlQuery.append(" AND rtsStatusId > 3 ");
        }
        if( isChannelSetting.booleanValue() ) {
            sqlQuery.append(" AND channelSetting.id = ? ");
            params.add(new Integer(channelSetting));
            //types.add(Hibernate.STRING);
        }
        if( isChannelType.booleanValue() ) {
            sqlQuery.append(" AND channelType.id = ? ");
            params.add(new Integer(channelType));
            //types.add(Hibernate.STRING);
        }

        //проверка ролей пользователя!
        //если администратор то видно все !
        //упрощаем процедуру поиска роли, роли находятся в идентификационном объекте пользователя
        if(!(executor.isUserInRole(RTSRole.ADMINISTRATOR) || executor.isUserInRole(RTSRole.DEVELOPER))) {
            sqlQuery.append(" AND executor.id = ? ");
            params.add(executor.getPersonId());
        }
        sqlQuery.append(" ORDER BY created DESC ");

        System.out.println("["+sqlQuery+"]");

        try{
            if(elementaryRequestDAO != null) {
                requests = elementaryRequestDAO.findElemenaryRequestsBySQL(sqlQuery.toString(),
                                params.toArray(), (Type[])types.toArray(new Type[]{}));
            } else {
                System.out.println("Empty list ");
                requests = Collections.EMPTY_LIST;
            }
        } catch(HibernateException he ){
            logger.error("Execute query '"+sqlQuery.toString()+"' error!");
            for(Iterator iter = params.iterator();iter.hasNext();){
                logger.error("Execute query '"+sqlQuery.toString()+"' error!");
            }
        }
        return requests;
    }

    public Collection findElementaryRequestsByConsolidatedRequest(Integer requestId, AuthInfo authInfo) throws SystemException {
        Collection requests = null;
        //TODO: if role admin, developer or another moderator - need another sql string !
        StringBuffer sqlQuery = new StringBuffer("FROM ElementaryRequest");
        List params = new ArrayList();
        List types = new ArrayList();
        if( requestId != null ) {
            sqlQuery.append(" WHERE consolidatedRequest.requestId = ? ");
            params.add(requestId);
        }
/*
        if(person != null) {
            sqlQuery.append( "AND executor = ? ");
        }
*/

        sqlQuery.append( " ORDER BY number ASC ");

        System.out.println("["+sqlQuery+"]");
        try{
            if(elementaryRequestDAO != null) {
                requests = elementaryRequestDAO.findElemenaryRequestsBySQL(sqlQuery.toString(),
                                params.toArray(), (Type[])types.toArray(new Type[]{}));
            } else {
                System.out.println("findElementaryRequestsByConsolidatedRequest : Empty list ");
                requests = Collections.EMPTY_LIST;
            }
        } catch(HibernateException he ){
            he.getCause();
            logger.error("Execute query '"+sqlQuery.toString()+"' error!");
            for(Iterator iter = params.iterator();iter.hasNext();){
                logger.error("Execute query '"+sqlQuery.toString()+"' error!");
            }
        }
        return requests;
    }


    public Collection findConsolidatedRequests(Boolean isSerching, Boolean isNumber,
                                               String number, Boolean isDateType,
                                               String dateTypeSelect, Date dateType,
                                               Boolean isRtsRequestType, String rtsRequestType,
                                               Boolean isRtsStatus, String rtsStatus,
                                               Boolean isChannelSetting, String channelSetting,
                                               Boolean isChannelType, String channelType,
                                               String priority, Boolean isInitiator,
                                               String initiator, Boolean isRegMan,
                                               Integer regMan, Boolean isExecutor,
                                               Integer executor, Boolean isSearchAddrA,
                                               String searchAddrA, Boolean isSearchAddrB,
                                               String searchAddrB, AuthInfo regManager) throws SystemException {
        return Collections.EMPTY_LIST;
    }

    public void saveElementaryRequest(AuthInfo authInfo, ElementaryRequest request) throws SystemException {
        if(request == null) throw new SystemException("Cannot save ElementaryRequest, cause null");
        Timestamp currentDate = RequestUtils.getTimestampFromUtilDate(new java.util.Date());
        request.setCreated(currentDate);
        request.setModified(currentDate);

        Person currentPerson = authService.getPerson(authInfo);

        request.setModifiedBy(currentPerson);
        request.setCreatedBy(currentPerson);

        String number = null;
        if((request.getNumber()==null || request.getNumber().equalsIgnoreCase("")) && numerationService != null) {
        		number = numerationService.generateElementaryRequestNumber(request.getConsolidatedRequest());
			request.setNumber(number);
	    } 


        ConsolidatedRequest consRequest = request.getConsolidatedRequest();
/*
        if(consRequest.getElementaryRequests()!=null &&
            !consRequest.getElementaryRequests().isEmpty()) {
//			Collection requests = consRequest.getElementaryRequests();
            Collection requests = findElementaryRequestsByConsolidatedRequest(consRequest.getRequestId(), authInfo);
            if(requests.size()>1) {
                ElementaryRequest  lastElemRequest = (ElementaryRequest)((List) requests).get(requests.size()-1);
                
                if(lastElemRequest.getEquipmentA().getId().intValue() ==
                    consRequest.getTransEquipmentB().getId().intValue() &&
                    lastElemRequest.getEquipmentB().getId().intValue() ==
                    consRequest.getEquipmentB().getId().intValue()) {

						request.setNumber(number);
//						request.setNumber(lastElemRequest.getNumber());
//                        lastElemRequest.setNumber(number);


                        if(elementaryRequestDAO != null) {
//							elementaryRequestDAO.updateElementaryRequest(lastElemRequest);
                        }
                    }
            }
        }
*/
        if(elementaryRequestDAO != null) {
            elementaryRequestDAO.saveElementaryRequest(request);
			this.reOrderElementaryRequestNumbers(consRequest, authInfo);
		}
    }

    public void updateElementaryRequest(AuthInfo authInfo, ElementaryRequest request) throws SystemException {
        if(request == null) throw new SystemException("Cannot update ElementaryRequest, cause null");
        java.sql.Timestamp currentDate = RequestUtils.getTimestampFromUtilDate(new java.util.Date());
        request.setModified(currentDate);

        Person currentPerson = authService.getPerson(authInfo);
        request.setModifiedBy(currentPerson);

        if(elementaryRequestDAO != null) elementaryRequestDAO.updateElementaryRequest(request);
    }

    public void removeElementaryRequest(AuthInfo authInfo, ElementaryRequest request) throws SystemException {
        if(request == null) throw new SystemException("Cannot remove ElementaryRequest, cause null");

        if(elementaryRequestDAO != null) {
            ConsolidatedRequest consRequest = request.getConsolidatedRequest();
            elementaryRequestDAO.removeElementaryRequest(request);
            this.reOrderElementaryRequestNumbers(consRequest, authInfo);
            return;
        }
    }

    public Integer getChannelCodeOrderNumber(String channelCode){
        Integer result = new Integer(1);
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT channelcode FROM rts_elemrequest WHERE channelcode LIKE '"+channelCode+"%' ORDER BY channelcode DESC");
            rs = pstmt.executeQuery();
            rs = pstmt.executeQuery();
            if(rs.next()) {
                String code = rs.getString("channelcode");
                code = code.substring(14);
                int ii = Integer.parseInt(code);
                ii++;
                result = new Integer(ii);
            } else {
                result = new Integer(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return result;
    }

	// Меняет нумерацию всех элементарных заявок при удалении одной из них
	// Также вносится обновление в таблицу rts_numerations для корректной
	// нумерации при создании новых эл. заявок
    public void reOrderElementaryRequestNumbers(ConsolidatedRequest consolidatedRequest, AuthInfo authInfo) throws SystemException {
        Collection requests = findElementaryRequestsByConsolidatedRequest(consolidatedRequest.getRequestId(), authInfo);
        if(requests!=null &&
            !requests.isEmpty()) {
            List reqList = new ArrayList(requests);
			for (Iterator i = reqList.iterator(); i.hasNext();) {
				ElementaryRequest elemReq = (ElementaryRequest) i.next();
			}
            Collections.sort(reqList);
            long k=0;
            for (Iterator i = reqList.iterator(); i.hasNext();) {
                k++;
                String k_str = new String((k<10?"0"+k:""+k));
                ElementaryRequest elemReq = (ElementaryRequest) i.next();
                String number = elemReq.getNumber();
                RE re = new RE("(\\d*)-(\\d{2})");
                if (re.match(number)){
                    String requestCode = re.getParen(1);
                    if (!requestCode.equalsIgnoreCase(k_str)){
                        if(numerationService != null) {
                            number = numerationService.generateElementaryRequestNumber(k, consolidatedRequest);
                        }
                        elemReq.setNumber(number);
                        if(elementaryRequestDAO != null)
                            elementaryRequestDAO.updateElementaryRequest(elemReq);
                    }
                }
            }
			for (Iterator i = reqList.iterator(); i.hasNext();) {
				ElementaryRequest elemReq = (ElementaryRequest) i.next();
			}
        } else {
			if(numerationService != null) {
				String number = numerationService.generateElementaryRequestNumber(0, consolidatedRequest);
			}
        }
        return;
    }

    // Признак, связаны ли последовательно все адреса/позиции (Position, не устройства - Equipment)
    // на всех элементарных заявках данной Консолидированной
    public boolean isRelatedElementaryRequests(ConsolidatedRequest consRequest, AuthInfo authInfo) throws SystemException {
        boolean returnValue = true;
        if(consRequest == null) throw new SystemException("ConsolidatedRequest is null. Please, check parameters ConsolidatedRequestService.isRelatedElementaryRequests()");
        if(consRequest.getElementaryRequests()!=null &&
            !consRequest.getElementaryRequests().isEmpty()) {
            Collection requests = findElementaryRequestsByConsolidatedRequest(consRequest.getRequestId(), authInfo);
            List reqList = new ArrayList(requests);
            Collections.sort(reqList);
            ElementaryRequest prevRequest = null;
            for (Iterator i = reqList.iterator(); i.hasNext();) {
                ElementaryRequest er = (ElementaryRequest) i.next();
                if(prevRequest == null) {
                    if(!isRelatedElementaryRequests(consRequest, er)) returnValue = false;
                    prevRequest = er;
                } else {
                    if(!isRelatedElementaryRequests(prevRequest, er)) returnValue = false;
                    prevRequest = er;
                }
            }
            if(!isRelatedElementaryRequests(prevRequest, consRequest)) returnValue = false;
        }
        return returnValue;
    }

    // Признак, связаны ли последовательно все адреса/позиции (Position, не устройства - Equipment)
    // на всех элементарных заявках данной Консолидированной
    public boolean isExecutorsFilled(ConsolidatedRequest consRequest, AuthInfo authInfo) throws SystemException {
        boolean executorsFilled = true;
        if(consRequest == null) throw new SystemException("ConsolidatedRequest is null. Please, check parameters ConsolidatedRequestService.isRelatedElementaryRequests()");
        if(consRequest.getElementaryRequests()!=null &&
            !consRequest.getElementaryRequests().isEmpty()) {
            Collection requests = findElementaryRequestsByConsolidatedRequest(consRequest.getRequestId(), authInfo);
            for (Iterator i = requests.iterator(); i.hasNext();) {
                ElementaryRequest er = (ElementaryRequest) i.next();
                if(er.getExecutor() == null) {
                    executorsFilled = false;
                }
            }
        }
        return executorsFilled ;
    }

    private boolean isRelatedElementaryRequests(AbstractRequest request1, AbstractRequest request2){
    	boolean returnValue = false;
		if(request1 instanceof ConsolidatedRequest) {
			ConsolidatedRequest req1 = (ConsolidatedRequest) request1; 
			if(req1.getPositionA() != null && request2.getPositionA() != null  &&
				(req1.getPositionA().getId().intValue() == request2.getPositionA().getId().intValue())) {
					returnValue = true;
			}
		} else if(request2 instanceof ConsolidatedRequest) {
			ConsolidatedRequest req2 = (ConsolidatedRequest) request2; 
			if(request1.getPositionB() != null && req2.getPositionB() != null  &&
				(request1.getPositionB().getId().intValue() == req2.getPositionB().getId().intValue())) {
					returnValue = true;
			}
		} else {
		if(request1.getPositionB() != null && request2.getPositionA() != null  &&
			(request1.getPositionB().getId().intValue() == request2.getPositionA().getId().intValue())) {
                returnValue = true;
			}
		}
    	return returnValue;
    }
}
