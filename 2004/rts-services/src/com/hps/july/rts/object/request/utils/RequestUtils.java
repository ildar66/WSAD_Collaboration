/*
 * Created on 30.01.2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.rts.object.request.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.regexp.RE;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.RTSContextProvider;
import com.hps.july.rts.auth.service.RTSAuthService;

/**
 * @author ABaykov
 *
 */
public class RequestUtils {

	private static RequestUtils instance;
	
	private RTSAuthService authService;
	
    private RequestUtils() { 
    	authService = (RTSAuthService) RTSContextProvider.getBean(RTSAuthService.SERVICE_NAME);
    }

    public Person getPerson(HttpServletRequest request){
    	AuthInfo authInfo = authService.getAuthInfo(request);
    	Person person = authService.getPerson(authInfo);
    	return person;
    }
    
    public static RequestUtils getInstance(){
    	if (instance == null) instance = new RequestUtils();
    	return instance;
    }
    
	public static java.sql.Date getSqlDateFromUtilDate(java.util.Date date) {
		return (date!=null?(new java.sql.Date(date.getTime())):null); 
	}

	public static java.sql.Timestamp getTimestampFromUtilDate(java.util.Date date) {
		return (date!=null?(new java.sql.Timestamp(date.getTime())):null); 
	}

    /**
     *
     * @param requestNumber
     * @return int (1 - initiator request; 2 - consolidated request; 3 - elementary reuqest)
     */
    public static int getRequestTypeByNumber(String requestNumber) {
        RE re = new RE("^(\\d*)-(\\d{2})$");
        if(re.match(requestNumber)) return 1;
        else {
            re = new RE("^K(\\d{4})-(\\d{2})$");
            if(re.match(requestNumber)) return 2;
            else {
                re = new RE("^K(\\d{4}).(\\d{2})-(\\d{2})$");
                if(re.match(requestNumber)) return 3;
            }
        }
        return 0;
    }

}
