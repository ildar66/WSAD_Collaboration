package com.hps.july.rts.service.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hps.april.common.FinderException;
import com.hps.july.rts.SystemException;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.core.service.ServiceException;
import com.hps.july.rts.core.service.ServiceFactory;
import com.hps.april.common.utils.StringUtils;
import com.hps.july.rts.RTSContextProvider;
import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.july.rts.auth.object.RTSGroup;
import com.hps.july.rts.auth.object.RTSRole;
import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.july.rts.service.RTSOperatorService;
import com.hps.july.rts.service.impl.dao.InitiatorRequestDAO;
import com.hps.april.auth.object.AuthInfo;

/**
 * @author <a href="mailto:abaykov@beeline.ru">Albert Baykov</a>
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 09.02.2006
 * Time: 14:26:03
 */
public class InitiatorRequestDAOHibernate extends HibernateDaoSupport
    implements InitiatorRequestDAO {

	RTSOperatorService operatorService;


	public RTSOperatorService getOperatorService() {
		return operatorService;
	}

	public void setOperatorService(RTSOperatorService operatorService) {
		this.operatorService = operatorService;
	}

    public Collection findInitiatorRequestsBySQL(String sqlQuery,
                                                 Object [] params,
                                                 Type [] types)
                throws SystemException {
        //TODO: is it work ?
        return getHibernateTemplate().find(sqlQuery, params);

    }

    public void saveInitiatorRequests(final InitiatorRequest iRequest) throws SystemException {
        getHibernateTemplate().save(iRequest);
        getSession().flush();
    }

    public void updateInitiatorRequest(final InitiatorRequest iRequest) throws SystemException {
/*
        InitiatorRequest copy = null;
        try {
            copy = (InitiatorRequest)iRequest.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            copy = iRequest;
        }
        getHibernateTemplate().evict(iRequest);
*/
//		System.err.println("---------------->Versions this ["+iRequest.getVersion()+"] ");
		InitiatorRequest iRequest1 = new InitiatorRequest();
        //getHibernateTemplate().load(iRequest1, iRequest.getRequestId());
/*
        System.err.println("---------------->Versions after refersh this ["+iRequest1.getVersion()+"] ");
		System.err.println("---------------->Links this ["+iRequest+"] prev ["+iRequest1+"]");
		System.err.println("---------------->Links == ["+(iRequest == iRequest1)+"] ");
		System.err.println("---------------->Links equals ["+(iRequest.equals(iRequest1))+"] ");
*/
        getHibernateTemplate().saveOrUpdate(iRequest);
        getSession().flush();
    }

    public void removeInitiatorRequest(InitiatorRequest initiatorRequest) throws SystemException {
        getHibernateTemplate().delete(initiatorRequest);
    }

    public Collection findInitiatorRequestsByStatus(Integer rtsStatus) throws SystemException {
        String sql = "FROM InitiatorRequest WHERE statusId = ?";
        List list = getHibernateTemplate().find(sql, new Object [] { rtsStatus });
        return list;
    }

    public Collection findInitiatorRequestsByStatusAndInConsRequest(Integer rtsStatus, AuthInfo authInfo, String currentRole) throws SystemException {
		RTSAuthService authService = (RTSAuthService) RTSContextProvider.getBean(RTSAuthService.SERVICE_NAME);
		StringBuffer isRegMan = new StringBuffer("");
		try{
			RTSRole roleRM = authService.getRTSRole(RTSRole.REGION_MANAGER);
//			if(authService.isUserInRtsRole(authInfo, roleRM)) {
			if(authService.isUserInRtsRole(authInfo, roleRM) && currentRole.equalsIgnoreCase("RM")) {
				Person person = authService.getPerson(authInfo);
				RTSGroup rtsGroup = getGroup(person);
				String str = getOperatorService().findPersonByRoleAndGroup(roleRM, rtsGroup);
				if(str!=null && str.length()>0) {
					isRegMan.append(" AND regManager in ( ");
					isRegMan.append(str);
					isRegMan.append(" ) ");
//					isRegMan.append(" AND keyManager = " + authInfo.getPersonId() + " ");
				}

			}
		} catch (FinderException e) {
			e.printStackTrace();
	    }
        String sql = "FROM InitiatorRequest WHERE statusId = ? " +
			isRegMan +			 
        	"AND consReqId IS NULL ORDER BY created DESC";
		System.out.println("["+sql+"]");
        List list = getHibernateTemplate().find(sql, new Object [] { rtsStatus });
        return list;
    }

    public Collection findInitiatorRequestsByConsolidatedRequest(Integer consolidatedRequest) throws SystemException {
        return null;
    }

    public InitiatorRequest findInitiatorRequestById(final Person currentInitiator, final Integer id) throws SystemException {
        String sql = "FROM InitiatorRequest WHERE initiator = ? AND requestId = ?";
//        RTSGroup group = getGroup(currentInitiator);

        RTSOperatorService rtsOperatorService = null;
        Collection groups = null;
        try{
            rtsOperatorService =
                        (RTSOperatorService)ServiceFactory.getService(ServiceFactory.RTS_OPERATOR);
            groups = rtsOperatorService.findGroupByPerson(currentInitiator);
        } catch(ServiceException se) {
            logger.error("Cannot initialize Initiator (RTSGroup) by Person (id = '"+currentInitiator.getId()+"')! ");
        }

        List list = null;
        if(groups.size()==1) {
            RTSGroup rtsGroup = (RTSGroup)((List)groups).get(0);
            list = getHibernateTemplate().find(sql, new Object [] { rtsGroup.getId(), id });
            if(!list.isEmpty()) return (InitiatorRequest)list.get(0);
        } else if (groups.size()>1) {
            for(Iterator it = groups.iterator(); it.hasNext();){
                RTSGroup rtsGroup = (RTSGroup)it.next();
                list = getHibernateTemplate().find(sql, new Object [] { rtsGroup.getId(), id });
                if(!list.isEmpty()) return (InitiatorRequest)list.get(0);
            }
        }
        return null;
    }

    public InitiatorRequest findInitiatorRequestByRequestId(final Integer id) throws SystemException {
        String sql = "FROM InitiatorRequest WHERE requestId = ?";
        List list = getHibernateTemplate().find(sql, new Object [] { id });
        if(!list.isEmpty()) return (InitiatorRequest)list.get(0);
        return null;
    }

    public InitiatorRequest findInitiatorRequest(final Integer id) throws SystemException {
        return (InitiatorRequest)getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(
                    "FROM InitiatorRequest WHERE requestId = :requestId");
                    query.setInteger("requestId", id.intValue());
                return query.uniqueResult();
            }});
    }
/*
    private Collection getGroup(Person person) {
		if(person==null) return null;
		Collection groups = new ArrayList();
		RTSGroup rtsGroup = null;
    	try{
	        
    	} catch(ServiceException se) {
			rtsGroup = null;
			logger.error("Cannot initialize Initiator (RTSGroup) by Person (id = '"+person.getId()+"')! ");
    	}
        return rtsGroup;
    }
*/

    public Collection findInitiatorRequests(Boolean isSerching,
                                            Boolean isNumber, String number,
                                            Boolean isDateType, String dateTypeSelect, Date dateType,
                                            Boolean isRtsRequestType, String rtsRequestType,
                                            Boolean isRtsStatus, String rtsStatus,
                                            Boolean isChannelSetting, String channelSetting,
                                            Boolean isChannelType, String channelType,
                                            String priority,
                                            Boolean isInitiator, String initiator,
                                            Boolean isRegMan, Integer regMan,
                                            Boolean isExecutor, Integer executor,
                                            Boolean isSearchAddrA, String searchAddrA,
                                            Boolean isSearchAddrB, String searchAddrB,
                                            RTSGroup currentInitiator, Boolean isAdmin) {

        //TODO: if role admin, developer or another moderator - need another sql string !
        String query = "FROM InitiatorRequest WHERE 1 = 1 ";
        List params = new ArrayList();
        //if user is admin ! search by initiator not used
        if(!(isAdmin.booleanValue())) {
            if( currentInitiator != null ) {
                query += " AND initiator = ? ";
                params.add(currentInitiator);
            } else return Collections.EMPTY_LIST;
        }

        if( isNumber.booleanValue() ) {
            query += " AND UPPER(number) LIKE ? ";
            params.add(StringUtils.prepareFindString(number));
        }

        if( isDateType.booleanValue() ) {
            if(dateTypeSelect.equalsIgnoreCase("s")) {
                query += " AND created = ? ";
            } else if(dateTypeSelect.equalsIgnoreCase("e")) {
                query += " AND inWorkDate = ? ";
            }
            params.add(dateType);
        }

        if( isRtsRequestType.booleanValue() ) {
            query += " AND rtsRequestTypeId = ? ";
            params.add(new Integer(rtsRequestType));
        }

        if( isRtsStatus.booleanValue() ) {
            query += " AND statusId = ? ";
            params.add(new Integer(rtsStatus));
        }

        if( isChannelSetting.booleanValue() ) {
            query += " AND channelSetting.id = ? ";
            params.add(new Integer(channelSetting));
        }

        if( isChannelType.booleanValue() ) {
            query += " AND channelType.id = ? ";
            params.add(new Integer(channelType));
        }

        if( !priority.equalsIgnoreCase("a") ) {
            if(priority.equalsIgnoreCase("h")){
                query += " AND priority = ? ";
                params.add(new Integer(2));
            } else if (priority.equalsIgnoreCase("u")){
                query += " AND priority = ? ";
                params.add(new Integer(1));
            }
        }

        if (isSearchAddrA.booleanValue() && isSearchAddrB.booleanValue()){
            query += " AND ( " +
                    "(UPPER(equipmentA.position.address) like ? AND UPPER(equipmentB.position.address) like ?) OR " +
                    "(UPPER(equipmentA.position.address) like ? AND UPPER(equipmentB.position.address) like ?)" +
                ") ";

            String positionAddressA = StringUtils.prepareFindString(searchAddrA);
            String positionAddressB = StringUtils.prepareFindString(searchAddrB);

            params.add(positionAddressA);
            params.add(positionAddressB);
            params.add(positionAddressB);
            params.add(positionAddressA);

        } else

        if (isSearchAddrA.booleanValue()){
            query += "AND UPPER(equipmentA.position.address) like ? ";
            params.add(StringUtils.prepareFindString(searchAddrA));
        } else

        if (isSearchAddrB.booleanValue()){
            query += "AND UPPER(equipmentB.position.address) like ? ";
            params.add(StringUtils.prepareFindString(searchAddrB));
        }

        query += "ORDER BY created DESC ";

        System.out.println("["+"Query: " + query + " --- " + params+"]");
        logger.debug("Query: " + query + " --- " + params);
        return getHibernateTemplate().find(query, params.toArray());

//		
//		DetachedCriteria criteria = DetachedCriteria.forClass(InitiatorRequest.class); 
//
//        //TODO: if role admin, developer or another moderator - need another sql string !
//		if(currentInitiator!=null) 
//			criteria.add( Restrictions.eq( "initiator", currentInitiator ) );
//		else return null;
//		
//		if(isNumber.booleanValue()) 
//			criteria.add( Restrictions.like("number", StringUtils.prepareFindString(number)) );
//		
//		if(isDateType.booleanValue()) { 
//			if(dateTypeSelect.equalsIgnoreCase("s")) {
//				criteria.add( Restrictions.lt("created", dateType ) );
//			} else if(dateTypeSelect.equalsIgnoreCase("e")) {
//				criteria.add( Restrictions.lt("inWorkDate", dateType ) );
//			}
//		}
//
//		if(isRtsRequestType.booleanValue())
//			criteria.add( Restrictions.eq("rtsRequestTypeId", new Integer(rtsRequestType) ) );
//	
//		if( isRtsStatus.booleanValue() ) 
//			criteria.add( Restrictions.eq("statusId", new Integer(rtsStatus) ) );
//		
//		if( isChannelSetting.booleanValue() )
//			criteria.add( Restrictions.eq("channelSetting.id", new Integer(channelSetting)));
//
//		if( isChannelType.booleanValue() ) 
//			criteria.add( Restrictions.eq("channelType.id", new Integer(channelType)));
//		
//		if( !priority.equalsIgnoreCase("a") ) {
//			if(priority.equalsIgnoreCase("h"))
//				criteria.add( Restrictions.eq("priority", new Integer(2)));
//			else if (priority.equalsIgnoreCase("u")) 
//				criteria.add( Restrictions.eq("priority", new Integer(1)));
//		}
//		
//	
//		
////		if (isSearchAddrA.booleanValue())
////			criteria.add( Restrictions.disjunction()
////					.add( Restrictions.like("equipmentA.address", searchAddrA))
////					.add( Restrictions.like("equipmentA.address", searchAddrA)));
//		
//				
//		if (isSearchAddrA.booleanValue() && isSearchAddrB.booleanValue()){
//			criteria.createCriteria("equipmentA").createCriteria("position").createAlias("address","posAddressA");
//			criteria.createCriteria("equipmentB").createCriteria("position").createAlias("address","posAddressB");
//			criteria.add( Restrictions.sqlRestriction(
//					"(((posAddressA like ?) and (posAddressB like ?)) or " +
//					"((posAddressA like ?) and (posAddressB like ?)))", 
//					new Object[] { '%'+searchAddrA+'%', '%'+searchAddrB+'%', '%'+searchAddrB+'%', '%'+searchAddrA+'%' }, 
//					new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) );
//		} else 
////		select this_.requestid as requestid13_8_, this_.status as status13_8_, this_.reqtype as reqtype13_8_, this_.equipmentA as equipmentA13_8_, this_.transequipmentA as transequ5_13_8_, this_.portA as portA13_8_, this_.transportA as transportA13_8_, this_.equipmentB as equipmentB13_8_, this_.transequipmentB as transequ9_13_8_, this_.portB as portB13_8_, this_.transportB as transportB13_8_, this_.channelstlmnt as channel12_13_8_, this_.initlabel as initlabel13_8_, this_.version as version13_8_, this_.number as number13_8_, this_.modified as modified13_8_, this_.created as created13_8_, this_.createdBy as createdBy13_8_, this_.modifiedBy as modifiedBy13_8_, this_.initid as initid13_8_, this_.initman as initman13_8_, this_.created as createdate13_8_, this_.factcompldate as factcom23_13_8_, this_.plancompldate as plancom24_13_8_, this_.completedate as complet25_13_8_, this_.channeltype as channel26_13_8_, this_.interfaceid as interfa27_13_8_, this_.capacity as capacity13_8_, this_.canBeCompressed as canBeCo29_13_8_, this_.reason as reason13_8_, this_.comment as comment13_8_, this_.inworkdate as inworkdate13_8_, this_.priority as priority13_8_, this_.contactnameA as contact34_13_8_, this_.contactphoneA as contact35_13_8_, this_.contactnameB as contact36_13_8_, this_.contactphoneB as contact37_13_8_, this_.keymanager as keymanager13_8_, this_.consReqId as consReqId13_8_, equipment1_.equipment as storagep1_20_0_, equipment1_1_.name as name20_0_, equipment1_1_.address as address20_0_, equipment1_.position as position21_0_, gettypeequipment(equipment1_.equipment) as formula0_0_, positiona2_.storageplace as storagep1_20_1_, positiona2_1_.name as name20_1_, positiona2_1_.address as address20_1_, equipment3_.equipment as storagep1_20_2_, equipment3_1_.name as name20_2_, equipment3_1_.address as address20_2_, equipment3_.position as position21_2_, gettypeequipment(equipment3_.equipment) as formula0_2_, positionb4_.storageplace as storagep1_20_3_, positionb4_1_.name as name20_3_, positionb4_1_.address as address20_3_, person10_.man as man0_4_, person10_.firstName as firstName0_4_, person10_.middleName as middleName0_4_, person10_.lastName as lastName0_4_, person11_.man as man0_5_, person11_.firstName as firstName0_5_, person11_.middleName as middleName0_5_, person11_.lastName as lastName0_5_, person12_.man as man0_6_, person12_.firstName as firstName0_6_, person12_.middleName as middleName0_6_, person12_.lastName as lastName0_6_, person13_.man as man0_7_, person13_.firstName as firstName0_7_, person13_.middleName as middleName0_7_, person13_.lastName as lastName0_7_ from rts_request this_ inner join equipment equipment1_ on this_.equipmentA=equipment1_.equipment left outer join storageplaces equipment1_1_ on equipment1_.equipment=equipment1_1_.storageplace inner join positions positiona2_ on equipment1_.position=positiona2_.storageplace left outer join storageplaces positiona2_1_ on positiona2_.storageplace=positiona2_1_.storageplace inner join equipment equipment3_ on this_.equipmentB=equipment3_.equipment left outer join storageplaces equipment3_1_ on equipment3_.equipment=equipment3_1_.storageplace inner join positions positionb4_ on equipment3_.position=positionb4_.storageplace left outer join storageplaces positionb4_1_ on positionb4_.storageplace=positionb4_1_.storageplace left outer join people person10_ on this_.createdBy=person10_.man left outer join people person11_ on this_.modifiedBy=person11_.man left outer join people person12_ on this_.initman=person12_.man left outer join people person13_ on this_.keymanager=person13_.man where this_.initid=? and ((positionA.address like ?) and (positionB.address like ?) or (positionA.address like ?) and (positionB.address like ?))
//		
//		if (isSearchAddrA.booleanValue()){
//			criteria.createCriteria("equipmentA").createCriteria("position")
//				.add( Restrictions.like("address", searchAddrA, MatchMode.ANYWHERE) );
//		} else 
//		
//		if (isSearchAddrB.booleanValue()){
//			criteria.createCriteria("equipmentB").createCriteria("position")
//				.add( Restrictions.like("address", searchAddrB, MatchMode.ANYWHERE));
//		}
//		
//		
////		if (isSearchAddrA.booleanValue() || isSearchAddrB.booleanValue()) {
////		
////			Disjunction positionADisjunction = Restrictions.disjunction();
////			Disjunction positionBDisjunction = Restrictions.disjunction();
////			if (isSearchAddrA.booleanValue()){
////				positionADisjunction.add( Restrictions.like("address", searchAddrA) );
////				positionBDisjunction.add( Restrictions.like("address", searchAddrA) );
////			}
////			
////			if (isSearchAddrA.booleanValue()){
////				positionADisjunction.add( Restrictions.like("address", searchAddrA) );
////				positionBDisjunction.add( Restrictions.like("address", searchAddrA) );
////			}
////			
////		}
//			
//		
////			criteria.createCriteria("equipmentA")
////				.createCriteria("position")
////					.add( Restrictions.like("address", searchAddrA, MatchMode.ANYWHERE) );
////					.add( Restrictions.like("address", searchAddrB, MatchMode.ANYWHERE) );
//
////				
////				
////				.add( Restrictions.disjunction()
////						.add( Restrictions.like("address", searchAddrA) )
////						.add( Restrictions.like("address", searchAddrB) )
////				);
//			
////			.add( Restrictions.disjunction()
////					.add( Restrictions.like("equipmentB.address", searchAddrB))
////					.add( Restrictions.like("equipmentB.address", searchAddrB)));
////	
// 		return getHibernateTemplate().findByCriteria(criteria);
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
    
}
