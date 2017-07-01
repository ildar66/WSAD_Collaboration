package com.hebrid.notification;

import java.util.List;
import java.util.Map;


public interface DefaultMailTemplateFactory {

	MailTemplate getMailTemplate(Map context, List recipientList);
	
}
