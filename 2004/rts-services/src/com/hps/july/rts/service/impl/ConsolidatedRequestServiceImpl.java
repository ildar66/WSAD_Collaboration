package com.hps.july.rts.service.impl;

import com.hps.july.rts.core.process.DefaultProcess;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.SystemException;
import com.hps.july.rts.core.service.ServiceFactory;
import com.hps.july.rts.core.service.ServiceException;
import com.hps.july.rts.core.hibernate.SessionFactoryProvider;
import com.hps.april.common.utils.StringUtils;
import com.hps.july.rts.service.*;
import com.hps.july.rts.service.impl.dao.ConsolidatedRequestDAO;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.rts.object.request.utils.RequestUtils;
import com.hps.july.rts.object.RTSStatus;
import com.hps.july.rts.auth.object.RTSGroup;
import com.hps.july.rts.auth.object.RTSRole;
import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.april.auth.object.AuthInfo;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.type.Type;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @author <a href="mailto:abaykov@beeline.ru">Albert Baykov</a>
 * Date: 31.01.2006
 * Time: 15:58:20
 */
public class ConsolidatedRequestServiceImpl
    extends DefaultProcess implements ConsolidatedRequestService {

    private ConsolidatedRequestDAO consolidatedRequestDAO;
    private RTSRequestNumerationService numerationService;
    private RTSOperatorService operatorService;
    private RTSAuthService authService;
    private Logger logger;

    public ConsolidatedRequestServiceImpl() {
    }

    public RTSAuthService getAuthService() {
        return authService;
    }

    public void setAuthService(RTSAuthService authService) {
        this.authService = authService;
    }

    public ConsolidatedRequestDAO getConsolidatedRequestDAO() {
        return consolidatedRequestDAO;
    }

    public void setConsolidatedRequestDAO(ConsolidatedRequestDAO dao) {
        this.consolidatedRequestDAO = dao;
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

	public ConsolidatedRequest findConsolidatedRequest(Integer id, AuthInfo authInfo) throws SystemException {
        return this.consolidatedRequestDAO.findConsolidatedRequest(id, authInfo);
	}

	public Collection findAllConsolidatedRequests(AuthInfo authInfo) throws SystemException {


		//Session session = SessionFactoryProvider.currentSession();
		Collection consRequests = null;
		StringBuffer sqlQuery = new StringBuffer("FROM ConsolidatedRequest ");
		try{
            //TODO: проверка прав польователя ! если админ то видно все заявки !!!
            consRequests = this.consolidatedRequestDAO.findConsolidatedRequestsBySQL(sqlQuery.toString(), new Object [] {}, null);
/*
			if(keyManager!= null) {
				sqlQuery.append(" WHERE keyManager = ? ");
				consRequests = session.createQuery(sqlQuery.toString()).setInteger(0, keyManager.getId().intValue()).list();
			} else {
*/			
				//consRequests = session.createQuery(sqlQuery.toString()).list();
//			}
		} catch (Exception e) {
			e.printStackTrace();
/*			try {
				String mess = new String(e.getMessage().getBytes("ISO-8859-1"));
				getLogger().error(mess);
			} catch(UnsupportedEncodingException uee) {
			}
*/		}
		return consRequests;
	}

	public Collection findConsolidatedRequests(Boolean isSerching, 
											Boolean isNumber,String number,
											Boolean isDateType,String dateTypeSelect,Date dateType,
											Boolean isRtsRequestType,String rtsRequestType,
											Boolean isRtsStatus,String rtsStatus,
											Boolean isChannelSetting, String channelSetting,
											Boolean isChannelType, String channelType,
											String priority,
											Boolean isInitiator, String initiator,
											Boolean isRegMan, Integer regMan,
											Boolean isExecutor, Integer executor,
											Boolean isSearchAddrA, String searchAddrA,
											Boolean isSearchAddrB, String searchAddrB,
											AuthInfo currentAuthInfo) throws SystemException {

      //TODO: добавить проверку на только свои заявки
//        AuthInfo authInfo = authService.getCurrentPersonAuthInfo(keyManager, getLogin());

        boolean isAdmin = (authService.isUserInRole(currentAuthInfo, "administrator")
                || authService.isUserInRole(currentAuthInfo, "developer"));

		return consolidatedRequestDAO.findConsolidatedRequests(isSerching, 
					isNumber, number,
					isDateType, dateTypeSelect, dateType,
					isRtsRequestType, rtsRequestType,
					isRtsStatus, rtsStatus,
					isChannelSetting, channelSetting,
					isChannelType, channelType,
					priority,
					isInitiator, initiator,
					isRegMan, regMan,
					isExecutor, executor,
					isSearchAddrA, searchAddrA,
					isSearchAddrB, searchAddrB,
					currentAuthInfo, new Boolean(isAdmin));

		
//
//        Collection requests = null;
//        StringBuffer sqlQuery = new StringBuffer("FROM ConsolidatedRequest");
//        System.out.println("currentInitiator ["+keyManager+"] ");

//        boolean isWhere = false;
//        sqlQuery.append(" WHERE 1 = 1 ");
//        List params = new ArrayList();
//        List types = new ArrayList();
//        if( isNumber.booleanValue() ) {
//            sqlQuery.append(" AND UPPER(number) LIKE ? ");
//            params.add(prepareFindString(number));
//            //types.add(Hibernate.STRING);
//        }
//        if( isDateType.booleanValue() ) {
//            if(dateTypeSelect.equalsIgnoreCase("s")) {
//                sqlQuery.append(" AND createDate < ? ");
//            } else if(dateTypeSelect.equalsIgnoreCase("e")) {
//                sqlQuery.append(" AND inWorkDate < ? ");
//            }
//            params.add(dateType);
//            //types.add(Hibernate.DATE);
//        }
//        if( isRtsRequestType.booleanValue() ) {
//            sqlQuery.append(" AND rtsRequestTypeId = ? ");
//            params.add(new Integer(rtsRequestType));
//            //types.add(Hibernate.STRING);
//        }
//        if( isRtsStatus.booleanValue() ) {
//            sqlQuery.append(" AND statusId = ? ");
//            params.add(new Integer(rtsStatus));
//            //types.add(Hibernate.STRING);
//        }
//        if( isChannelSetting.booleanValue() ) {
//            sqlQuery.append(" AND channelSetting.id = ? ");
//            params.add(new Integer(channelSetting));
//            //types.add(Hibernate.STRING);
//        }
//        if( isChannelType.booleanValue() ) {
//            sqlQuery.append(" AND channelType.id = ? ");
//            params.add(new Integer(channelType));
//            //types.add(Hibernate.STRING);
//        }
//        System.out.println("["+sqlQuery+"]");
//
//        try{
//            if(consolidatedRequestDAO != null) {
//                requests = consolidatedRequestDAO.findConsolidatedRequestsBySQL(sqlQuery.toString(),
//                                params.toArray(), (Type[])types.toArray(new Type[]{}));
//            } else {
//                Session session = SessionFactoryProvider.currentSession();
//                requests = session.createQuery(sqlQuery.toString())
//                    .setParameters(params.toArray(),
//                (Type[])types.toArray(new Type[]{})).list();
//            }
//        } catch(HibernateException he ){
//            logger.error("Execute query '"+sqlQuery.toString()+"' error!");
//            for(Iterator iter = params.iterator();iter.hasNext();){
//                logger.error("Execute query '"+sqlQuery.toString()+"' error!");
//            }
//        }
//        return requests;
    }

	public Collection findConsolidatedRequests(Boolean isSerching,
											Boolean isNumber,String number,
											Boolean isDateType,String dateTypeSelect,Date dateType,
											Boolean isRtsRequestType,String rtsRequestType,
											Boolean isRtsStatus,String rtsStatus,
											Boolean isChannelSetting,String channelSetting,
											String priority,
											Boolean isExecutor, Integer executor,
											Boolean isSearchAddrA, String searchAddrA,
											Boolean isSearchAddrB, String searchAddrB,
											AuthInfo person, RTSRole role) throws SystemException {

        Collection requests = null;
        StringBuffer sqlQuery = new StringBuffer("FROM ConsolidatedRequest");
		
		boolean isAdmin = (authService.isUserInRole(person, "administrator")
				|| authService.isUserInRole(person, "developer"));

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
				sqlQuery.append(" AND createDate < ? ");
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
        //TODO в зависимости от роли персоны треба статусы
        if( isRtsStatus.booleanValue() && isValidString(rtsStatus)) {
			sqlQuery.append(" AND statusId in ( ");
            sqlQuery.append(rtsStatus);
            sqlQuery.append(" ) ");
            //types.add(Hibernate.STRING);
        }
//        else {
//            sqlQuery.append(" AND statusId <> 0 ");
//        }
        if( isChannelSetting.booleanValue() ) {
            sqlQuery.append(" AND channelSetting.id = ? ");
            params.add(new Integer(channelSetting));
            //types.add(Hibernate.STRING);
        }
//		if(role!=null) {
		if(role!=null && !isAdmin) {
            switch(role.getId().intValue()){ // 5,6,10
                case 2: // - Региональный менеджер
                    Person p = getAuthService().getPerson(person);
                    RTSGroup rtsGroup = getGroup(p);
                    String str = getOperatorService().findPersonByRoleAndGroup(role, rtsGroup);
                    if(str!= null && str.length()!=0) {
                        sqlQuery.append(" AND regMan.id in ( ");
                        sqlQuery.append(str);
                        sqlQuery.append(" ) ");
                    } else { 
//						sqlQuery.append(" AND 1 = 2 ");
                    }
                    break;
                case 5: //ASSERTER - Утверждающий
                    sqlQuery.append(" AND confirmativeMan.id = ? ");
                    params.add(person.getPersonId());
                    break;
				case 6: //TESTER - Тестирующий
					sqlQuery.append(" AND testedBy.id = ? ");
					params.add(person.getPersonId());
					break;
                case 10: //MANAGER - Руководитель
                    sqlQuery.append(" AND head.id = ? ");
                    params.add(person.getPersonId());
                    break;
                default:
            }
        }
        sqlQuery.append(" ORDER BY created DESC ");
        System.out.println("["+sqlQuery+"]");

        try{
            if(consolidatedRequestDAO != null) {
                requests = consolidatedRequestDAO.findConsolidatedRequestsBySQL(sqlQuery.toString(),
                                params.toArray(), (Type[])types.toArray(new Type[]{}));
            } else {
                Session session = SessionFactoryProvider.currentSession();
                requests = session.createQuery(sqlQuery.toString())
                    .setParameters(params.toArray(),
                (Type[])types.toArray(new Type[]{})).list();
            }
        } catch(HibernateException he ){
            logger.error("Execute query '"+sqlQuery.toString()+"' error!");
            for(Iterator iter = params.iterator();iter.hasNext();){
                logger.error("Execute query '"+sqlQuery.toString()+"' error!");
            }
        }
        return requests;
    }

    public void saveConsolidatedRequest(AuthInfo authInfo, ConsolidatedRequest request) throws SystemException {
        if(request == null) throw new SystemException("Cannot save consolidated request, cause null");
        java.sql.Timestamp currentDate = RequestUtils.getTimestampFromUtilDate(new java.util.Date());
        request.setCreated(currentDate);
        request.setModified(currentDate);
        
        Person currentPerson = authService.getPerson(authInfo);
        request.setModifiedBy(currentPerson);
        request.setCreatedBy(currentPerson);
        
        request.setRtsStatus(RTSStatus.DRAFT);
        
        this.consolidatedRequestDAO.saveConsolidatedRequest(request);
    }

    public void updateConsolidatedRequest(AuthInfo authInfo, ConsolidatedRequest request) throws SystemException {
        if(request == null) throw new SystemException("Cannot save consolidated request, cause null");
        if (request.getNumber()==null
        		&& request.getRtsStatus().
        				getId().intValue() == RTSStatus.statusId_2) {
			String number = numerationService.generateConsolidatedRequestNumber();
			request.setNumber(number);
			getLogger().debug("Generated number of Consolidated Request ("+request.getRequestId()+"): "+ number);
        }
        java.sql.Timestamp currentDate = RequestUtils.getTimestampFromUtilDate(new java.util.Date());
        
        Person currentPerson = authService.getPerson(authInfo);
        request.setModifiedBy(currentPerson);
        
        request.setModified(currentDate);
        this.consolidatedRequestDAO.updateConsolidatedRequest(request);
    }

    public void removeConsolidatedRequest(AuthInfo authInfo, ConsolidatedRequest request) throws SystemException {
        this.consolidatedRequestDAO.removeConsolidatedRequest(request);
    }

	private RTSGroup getGroup(Person person) {
		if(person==null) return null;
		Collection groups = null;		RTSGroup rtsGroup = null;
		try{
			System.out.println("OPERATOR SERVICE ["+operatorService+"]");
			if(operatorService != null) {
				groups = operatorService.findGroupByPerson(person);
				if(groups!=null && groups.size()>0) rtsGroup = (RTSGroup)groups.toArray()[0];
			} else {
				RTSOperatorService rtsOperatorService =
					(RTSOperatorService)ServiceFactory.getService(ServiceFactory.RTS_OPERATOR);
				groups = rtsOperatorService.findGroupByPerson(person);
				if(groups!=null && groups.size()>0) rtsGroup = (RTSGroup)groups.toArray()[0];
			}
		} catch(ServiceException se) {
			rtsGroup = null;
			logger.error("Cannot initialize Initiator (RTSGroup) by Person (id = '"+person.getId()+"')! ");
		}
		return rtsGroup;
	}

	private Logger getLogger(){
		if(logger==null) {
			logger = Logger.getLogger(this.getClass()); 
		}
		return logger;
	}
/*
	public Collection findConsolidatedRequests(Boolean isSerching, 
											Boolean isNumber,String number,
											Boolean isDateType,String dateTypeSelect,Date dateType,
											Boolean isRtsRequestType,String rtsRequestType,
											Boolean isRtsStatus,String rtsStatus,
											Boolean isChannelSetting, String channelSetting,
											Boolean isChannelType, String channelType,
											String priority,
											Boolean isInitiator, String initiator,
											Boolean isRegMan, Integer regMan,
											Boolean isExecutor, Integer executor,
											Boolean isSearchAddrA, String searchAddrA,
											Boolean isSearchAddrB, String searchAddrB,
											AuthInfo authInfo) throws SystemException {

		return findConsolidatedRequests(isSerching, isNumber,
										number,isDateType,
										dateTypeSelect,dateType,
										isRtsRequestType,rtsRequestType,
										isRtsStatus,rtsStatus,
										isChannelSetting,channelSetting,
										isChannelType,channelType,
										priority,isInitiator, 
										initiator,isRegMan, 
										regMan,isExecutor, 
										executor,isSearchAddrA, 
										searchAddrA,isSearchAddrB, 
										searchAddrB,authInfo);
	}
*/
	public ConsolidatedRequest findConsolidatedRequest(Integer integer){
		return null;
	}
	
	public ConsolidatedRequest findConsolidatedRequest(AuthInfo authInfo, Integer integer) {
			return consolidatedRequestDAO.findConsolidatedRequest(integer);
	}

	public Integer getChannelCodeOrderNumber(String channelCode){
		Integer result = new Integer(1);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement("SELECT channelcode " +
				"FROM rts_consrequest " +
				"WHERE channelcode LIKE '"+channelCode+"%' " +
				" ORDER BY channelcode DESC");
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

/*
    private String findPersonByRoleAndGroup(RTSRole role, RTSGroup group) {
        StringBuffer str = new StringBuffer();
        String sql;
        if( group == null || group.getRegionId() == null || role == null) return null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            if(group.getFilialId() == null && group.getRegionId() != null) {
                sql = "select * from rts_groups2people where groupid in ( " +
                        "    select groupid from rts_groups where (filialid IS NOT NULL and " +
                        "        regionid = ?" +
                        " and " +
                        "        roleid = ? ) or groupid = ?" +
                        ")";
                pstmt = connection.prepareStatement(sql);
				setInteger(pstmt, 1, group.getRegionId());
				setInteger(pstmt, 2, role.getId());
				setInteger(pstmt, 3, group.getId());
            } else {
                sql = "select * from rts_groups2people where groupid in ( " +
                        "    select groupid from rts_groups where filialid IS NOT NULL and " +
                        "        groupid = ? " +
                        " and " +
                        "        roleid = ? " +
                        ")";
                pstmt = connection.prepareStatement(sql);
                setInteger(pstmt, 1, group.getId());
				setInteger(pstmt, 2, role.getId());
            }
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if(str.length() != 0) {
                    str.append(", ");
                }
                str.append(rs.getString("man"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }

        if (str.length()!=0)
            return str.toString();
        else return null;
    }
*/

	public String getInfoAboutBean(){
		return numerationService.getInfoAboutBean();
	}

}
