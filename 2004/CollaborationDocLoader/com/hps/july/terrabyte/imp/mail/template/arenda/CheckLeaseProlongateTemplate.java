/*
 *  $Id: CheckLeaseProlongateTemplate.java,v 1.5 2007/06/18 17:09:32 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.terrabyte.imp.mail.template.arenda;

import com.hps.july.mail.template.JulyHTMLMailTemplate;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.5 $ $Date: 2007/06/18 17:09:32 $
 */
public class CheckLeaseProlongateTemplate extends JulyHTMLMailTemplate {

	public CheckLeaseProlongateTemplate(List recipientList, String templateName, String subject, HashMap prop) {
		super(recipientList, templateName, subject, prop);
		StringBuffer templateNameBuffer = new StringBuffer();
		StringTokenizer st = new StringTokenizer(this.getClass().getName(), ".");
		while(st.hasMoreTokens()) {
			templateNameBuffer.append(st.nextToken());
			if(st.hasMoreTokens()) templateNameBuffer.append("/");
		}
		templateNameBuffer.append(".message");

		setTemplateName(templateNameBuffer.toString());
	}

	public List getTo() {
		String email = (String)getProp().get("emailEconomist");
		if(email != null) {
			ArrayList recipList = new ArrayList();
			recipList.add(email);
			return recipList;
		}
		return null;
	}

	public List getCC() {
		String email = (String)getProp().get("emailHunter");
		if(email != null) {
			ArrayList recipList = new ArrayList();
			recipList.add(email);
			return recipList;
		}
		return null;
	}

	public List getBCC() {
		ArrayList recipList = new ArrayList();
		recipList.add("ABochkarev@beeline.ru");
		return recipList;

	}




}
