package com.hps.july.rts.service.impl;

import com.hps.july.rts.service.RTSStatusService;
import com.hps.july.rts.auth.object.RTSRole;
import com.hps.july.rts.object.RTSStatus;

import java.util.*;

/**
 * RTS ("Заявки на расширение региональной ТС")
 * 
 *  @author abaykov
 *  Created on 18.01.2006
 */
public class RTSStatusServiceImpl implements RTSStatusService {

    private Map statuses;

    public RTSStatusServiceImpl() {
        this.statuses = new HashMap();
        this.statuses.put(new Integer(RTSStatus.statusId_0), new RTSStatus(new Integer(RTSStatus.statusId_0), RTSStatus.status_0));
        this.statuses.put(new Integer(RTSStatus.statusId_1), new RTSStatus(new Integer(RTSStatus.statusId_1), RTSStatus.status_1));
        this.statuses.put(new Integer(RTSStatus.statusId_2), new RTSStatus(new Integer(RTSStatus.statusId_2), RTSStatus.status_2));
        this.statuses.put(new Integer(RTSStatus.statusId_3), new RTSStatus(new Integer(RTSStatus.statusId_3), RTSStatus.status_3));
        this.statuses.put(new Integer(RTSStatus.statusId_4), new RTSStatus(new Integer(RTSStatus.statusId_4), RTSStatus.status_4));
        this.statuses.put(new Integer(RTSStatus.statusId_5), new RTSStatus(new Integer(RTSStatus.statusId_5), RTSStatus.status_5));
        this.statuses.put(new Integer(RTSStatus.statusId_6), new RTSStatus(new Integer(RTSStatus.statusId_6), RTSStatus.status_6));
        this.statuses.put(new Integer(RTSStatus.statusId_7), new RTSStatus(new Integer(RTSStatus.statusId_7), RTSStatus.status_7));
        this.statuses.put(new Integer(RTSStatus.statusId_8), new RTSStatus(new Integer(RTSStatus.statusId_8), RTSStatus.status_8));
        this.statuses.put(new Integer(RTSStatus.statusId_9), new RTSStatus(new Integer(RTSStatus.statusId_9), RTSStatus.status_9));
        this.statuses.put(new Integer(RTSStatus.statusId_10), new RTSStatus(new Integer(RTSStatus.statusId_10), RTSStatus.status_10));
        this.statuses.put(new Integer(RTSStatus.statusId_11), new RTSStatus(new Integer(RTSStatus.statusId_11), RTSStatus.status_11));
        this.statuses.put(new Integer(RTSStatus.statusId_12), new RTSStatus(new Integer(RTSStatus.statusId_12), RTSStatus.status_12));
		this.statuses.put(new Integer(RTSStatus.statusId_13), new RTSStatus(new Integer(RTSStatus.statusId_13), RTSStatus.status_13));
		this.statuses.put(new Integer(RTSStatus.statusId_14), new RTSStatus(new Integer(RTSStatus.statusId_14), RTSStatus.status_14));
    }

    public Collection findAllRTSStatuses() {
        return this.statuses.values();
//        List statuses = new ArrayList();
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_0), RTSStatus.status_0));
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_1), RTSStatus.status_1));
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_2), RTSStatus.status_2));
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_3), RTSStatus.status_3));
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_4), RTSStatus.status_4));
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_5), RTSStatus.status_5));
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_6), RTSStatus.status_6));
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_7), RTSStatus.status_7));
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_8), RTSStatus.status_8));
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_9), RTSStatus.status_9));
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_10), RTSStatus.status_10));
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_11), RTSStatus.status_11));
//        statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_12), RTSStatus.status_12));
//		statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_13), RTSStatus.status_13));
//		statuses.add(new RTSStatus(new Integer(RTSStatus.statusId_14), RTSStatus.status_14));
//        return statuses;
    }

	// При определении списка заявок для конкретной роли, 
	// отсеиваем все статусы, которые данной роли не доступны
    public Collection findAllRTSStatusesForRole(RTSRole role) {
        Collection statuses = findAllRTSStatuses();
        if(role == null) return statuses;
        Collection newStatuses = new ArrayList();
        RTSStatus rTSStatus;
        switch(role.getId().intValue()){
            case(1): // INITIATOR
                break;
            case(2): // REGION_MANAGER
                break;
            case(3): // PERFORMER
                break;
            case(4): // TOP_MANAGER
                break;
            case(5): // ASSERTOR
                for(Iterator it = statuses.iterator(); it.hasNext();){
                    rTSStatus = (RTSStatus) it.next();
                    if (!(rTSStatus.getId().intValue() < RTSStatus.statusId_6)) // Извлекаем всё, что ниже "На утверждении"
                        newStatuses.add(rTSStatus);
                }
                break;
            case(6): // TESTER
                for(Iterator it = statuses.iterator(); it.hasNext();){
                    rTSStatus = (RTSStatus) it.next();
                    if (!(rTSStatus.getId().intValue() < RTSStatus.statusId_10)) // Извлекаем всё, что ниже "На тестировании"
                    newStatuses.add(rTSStatus);
                }
                break;
            case(7): // SETS_RESPONSIBLE
                break;
            case(8): // ARENDA_RESPONSIBLE
                break;
            case(9): // ARENDA_PERFORMER
                break;
            case(10): // MANAGER (head по-нашему) - Руководитель
                for(Iterator it = statuses.iterator(); it.hasNext();){
                    rTSStatus = (RTSStatus) it.next();
                    if (!(rTSStatus.getId().intValue() < RTSStatus.statusId_0)) // Извлекаем всё, что ниже "Черновик"
                    newStatuses.add(rTSStatus);
                }
                break;
            default:
                break;
        }
        return newStatuses;
    }

    public RTSStatus findRTSStatus(Integer ident) {
//        Collection statuses = findAllRTSStatuses();
//        RTSStatus rTSStatus = null;
//        for(Iterator it = statuses.iterator(); it.hasNext();){
//            Object o = it.next();
//            if(o!=null && o instanceof RTSStatus) {
//                if (((RTSStatus) o).getId().intValue() == ident.intValue())
//                rTSStatus = (RTSStatus) o;
//            }
//        }
        return (RTSStatus)this.statuses.get(ident);
    }

    public String getListOfStatuses(Collection statuses) {
        StringBuffer list = new StringBuffer();
        RTSStatus rtsStatus;
        if(statuses == null) return null;
        boolean isFirst = true;
        for(Iterator it = statuses.iterator(); it.hasNext();){
            rtsStatus = (RTSStatus)it.next();
            if(isFirst){
                list.append(rtsStatus.getId().toString());
                isFirst = false;
            } else {
                list.append(",");
                list.append(rtsStatus.getId().toString());
            }
        }
        return list.toString();
    }
}
