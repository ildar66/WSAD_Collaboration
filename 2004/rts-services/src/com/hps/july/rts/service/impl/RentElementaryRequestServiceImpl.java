package com.hps.july.rts.service.impl;

import com.hps.july.rts.service.RentElementaryRequestService;
import com.hps.july.rts.service.RTSRequestNumerationService;
import com.hps.july.rts.service.RTSOperatorService;
import com.hps.july.rts.service.impl.dao.RentElementaryRequestDAO;
import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.july.rts.object.request.RentElementaryRequest;
import com.hps.july.rts.object.request.utils.RequestUtils;
import com.hps.july.rts.SystemException;
import com.hps.july.rts.core.process.DefaultProcess;
import com.hps.april.auth.object.Person;
import com.hps.april.auth.object.AuthInfo;

import java.util.*;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import org.hibernate.type.Type;
import org.hibernate.HibernateException;

/*
* RTS ("Заявки на расширение региональной ТС")
* Реализация объекта со служебными методами
*
* Элементарная ПОДзаявка (по аренде)
* Исполнитель может разбивать Эл. заявку на подзаявки
* На один арендуемый канал может оформляться более одного
* доп. соглашения
*
* @author ABaykov
* Created on 24.08.2006
*/
public class RentElementaryRequestServiceImpl
        extends DefaultProcess implements RentElementaryRequestService {

    protected RentElementaryRequestDAO rentElementaryRequestDAO;
    private RTSRequestNumerationService numerationService;
    private RTSOperatorService operatorService;
    private RTSAuthService authService;

    public RentElementaryRequestServiceImpl() {
    }

    public RTSAuthService getAuthService() {
        return authService;
    }

    public void setAuthService(RTSAuthService authService) {
        this.authService = authService;
    }

    public RentElementaryRequestDAO getRentElementaryRequestDAO() {
        return rentElementaryRequestDAO;
    }

    public void setRentElementaryRequestDAO(RentElementaryRequestDAO rentElementaryRequestDAO) {
        this.rentElementaryRequestDAO = rentElementaryRequestDAO;
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

    public Collection findAllRentElementaryRequests(Person regManager) throws SystemException {
        Collection requests = null;
        StringBuffer sqlQuery = new StringBuffer("FROM RentElementaryRequest WHERE regManager = ?");
        try{
            //TODO: проверка прав польователя ! если админ то видно все заявки !!!
            requests = this.rentElementaryRequestDAO.findRentElemenaryRequestsBySQL(sqlQuery.toString(), new Object [] { regManager }, null);
        } catch (Exception e) {
            e.printStackTrace();
		}
        return requests;
    }

    public RentElementaryRequest findRentElementaryRequest(AuthInfo authInfo, Integer id) throws SystemException {
        if(id == null) throw new SystemException("Cannot find ElementaryRequest, cause id is null");
        if(authInfo == null) throw new SystemException("Cannot find ElementaryRequest, cause regManager is unknown");
        return rentElementaryRequestDAO.findRentElementaryRequest(id);
    }

    public void saveRentElementaryRequest(AuthInfo authInfo, RentElementaryRequest request) throws SystemException {
        if(request == null) throw new SystemException("Cannot save RentElementaryRequest, cause null");
        Timestamp currentDate = RequestUtils.getTimestampFromUtilDate(new java.util.Date());
        request.setCreated(currentDate);
        request.setModified(currentDate);
        
        Person currentPerson = authService.getPerson(authInfo);
        
        request.setModifiedBy(currentPerson);
        request.setCreatedBy(currentPerson);
        
        String number = null;
        if(numerationService != null) number = numerationService.generateRentElementaryRequestNumber(request.getElementaryRequest());
        System.out.println("rentElem number ["+number+"]");
		request.setNumber(number);
        if(rentElementaryRequestDAO != null)
            rentElementaryRequestDAO.saveRentElementaryRequest(request);
    }

    public void updateRentElementaryRequest(AuthInfo authInfo, RentElementaryRequest request) throws SystemException {
        if(request == null) throw new SystemException("Cannot update ElementaryRequest, cause null");
        java.sql.Timestamp currentDate = RequestUtils.getTimestampFromUtilDate(new java.util.Date());
        request.setModified(currentDate);

        Person currentPerson = authService.getPerson(authInfo);
        request.setModifiedBy(currentPerson);
              
        if(rentElementaryRequestDAO != null) rentElementaryRequestDAO.updateRentElementaryRequest(request);
    }

    public void removeRentElementaryRequest(AuthInfo authInfo, RentElementaryRequest request) throws SystemException {
        if(request == null) throw new SystemException("Cannot remove ElementaryRequest, cause null");
        if(rentElementaryRequestDAO != null) rentElementaryRequestDAO.removeRentElementaryRequest(request);
    }

    public Integer getChannelCodeOrderNumber(String channelCode){
        Integer result = new Integer(1);
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT channelcode FROM rts_rentelemrequest WHERE channelcode LIKE '"+channelCode+"%' ORDER BY channelcode DESC");
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

    public Collection findRentElementaryRequestsByElementaryRequest(Integer requestId, AuthInfo authInfo) throws SystemException {
        Collection requests = null;
        //TODO: if role admin, developer or another moderator - need another sql string !
        StringBuffer sqlQuery = new StringBuffer("FROM RentElementaryRequest");
        System.out.println("currentPerson ["+authInfo.getPersonId()+"] ");
        List params = new ArrayList();
        List types = new ArrayList();
        
        if( requestId != null ) {
            sqlQuery.append(" WHERE elementaryRequest.id = ? ");
            params.add(requestId);
        }
/*
        if(person != null) {
            sqlQuery.append( "AND executor = ? ");
        }
*/

        sqlQuery.append( " ORDER BY created ASC ");

        System.out.println("["+sqlQuery+"]");
        try{
            if(rentElementaryRequestDAO != null) {
                requests = rentElementaryRequestDAO.findRentElemenaryRequestsBySQL(sqlQuery.toString(),
                                params.toArray(), (Type[])types.toArray(new Type[]{}));
            } else {
                System.out.println("findElementaryRequestsByConsolidatedRequest : Empty list ");
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



}
