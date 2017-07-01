package com.hps.july.mail.service.impl;

import com.hps.april.auth.object.AuthInfo;
import com.hps.july.mail.object.MailMessage;
import com.hps.july.mail.service.JulyMailService;
import com.hps.july.mail.service.MailService;
import com.hps.july.mail.template.JulyHTMLMailTemplate;
import com.hps.july.mail.template.JulyMailTemplate;
import com.hps.july.mail.template.MailTemplate;
import com.hps.july.mail.template.NRIMailTemplate;
import com.hps.july.mail.template.SimpleJulyMailTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 05.07.2006
 * Time: 16:11:48
 */
public class JulyMailServiceImpl implements JulyMailService {

	private MailService mailService;
    private ArrayList recipients;
    private ArrayList recipientsCC;
    private ArrayList recipientsBCC;

    private boolean isTest = false;

    private String from;

    public JulyMailServiceImpl(boolean isTest) {
        this.recipients = null;
        this.recipientsCC = null;
        this.recipientsBCC = null;
        this.isTest = isTest;
        this.from = null;
    }

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void sendDirectMail(AuthInfo authInfo, String serviceName, MailMessage mailMessage) {
		if(isTest) {
			mailMessage.setRecipients(null);
			mailMessage.setRecipientsCC(null);
			mailMessage.setRecipientsBCC(null);
			if(recipients != null) {
				for(Iterator i = recipients.iterator(); i.hasNext();) {
					String email = (String) i.next();
					mailMessage.addRecipient(email);
				}
			}
			if(recipientsCC != null) {
				for(Iterator i = recipientsCC.iterator(); i.hasNext();) {
					String email = (String) i.next();
					mailMessage.addRecipientCC(email);
				}
			}
			if(recipientsBCC != null) {
				for(Iterator i = recipientsBCC.iterator(); i.hasNext();) {
					String email = (String) i.next();
					mailMessage.addRecipientCC(email);
				}
			}
			if(from == null) mailMessage.setFrom(NRIMailTemplate.FROM_NRI_TECHNICAL_SUPPORT_ADDRESS_TEST);
			else mailMessage.setFrom(from);
		} else {
			if(recipients != null) {
				for(Iterator i = recipients.iterator(); i.hasNext();) {
					String email = (String) i.next();
					mailMessage.addRecipient(email);
				}
			}
			if(recipientsCC != null) {
				for(Iterator i = recipientsCC.iterator(); i.hasNext();) {
					String email = (String) i.next();
					mailMessage.addRecipientCC(email);
				}
			}
			if(recipientsBCC != null) {
				for(Iterator i = recipientsBCC.iterator(); i.hasNext();) {
					String email = (String) i.next();
					mailMessage.addRecipientCC(email);
				}
			}
			if(from == null) mailMessage.setFrom(NRIMailTemplate.FROM_NRI_TECHNICAL_SUPPORT_ADDRESS);
			else mailMessage.setFrom(from);
		}
		mailService.sendMail(authInfo, serviceName, mailMessage);
	}


	public void sendBaseMail(AuthInfo authInfo, JulyMailTemplate mailTemplate, String serviceName) {
		MailMessage mailMessage = mailTemplate.createMailMessage();
		if(isTest) {
			mailMessage.setRecipients(null);
			mailMessage.setRecipientsCC(null);
			mailMessage.setRecipientsBCC(null);
            if(recipients != null) {
                for(Iterator i = recipients.iterator(); i.hasNext();) {
                    String email = (String) i.next();
                    mailMessage.addRecipient(email);
                }
            }
            if(recipientsCC != null) {
                for(Iterator i = recipientsCC.iterator(); i.hasNext();) {
                    String email = (String) i.next();
                    mailMessage.addRecipientCC(email);
                }
            }
            if(recipientsBCC != null) {
                for(Iterator i = recipientsBCC.iterator(); i.hasNext();) {
                    String email = (String) i.next();
                    mailMessage.addRecipientCC(email);
                }
            }
            if(from == null) mailMessage.setFrom(NRIMailTemplate.FROM_NRI_TECHNICAL_SUPPORT_ADDRESS_TEST);
            else mailMessage.setFrom(from);
        } else {
            if(recipients != null) {
                for(Iterator i = recipients.iterator(); i.hasNext();) {
                    String email = (String) i.next();
                    mailMessage.addRecipient(email);
                }
            }
            if(recipientsCC != null) {
                for(Iterator i = recipientsCC.iterator(); i.hasNext();) {
                    String email = (String) i.next();
                    mailMessage.addRecipientCC(email);
                }
            }
            if(recipientsBCC != null) {
                for(Iterator i = recipientsBCC.iterator(); i.hasNext();) {
                    String email = (String) i.next();
                    mailMessage.addRecipientCC(email);
                }
            }
			if(from == null) mailMessage.setFrom(NRIMailTemplate.FROM_NRI_TECHNICAL_SUPPORT_ADDRESS);
            else mailMessage.setFrom(from);
		}
		mailService.sendMail(authInfo, serviceName, mailMessage);
	}

	public Integer sendIdentifiedBaseMail(AuthInfo authInfo, JulyMailTemplate mailTemplate, String serviceName) {
		MailMessage mailMessage = mailTemplate.createMailMessage();
		if(isTest) {
			mailMessage.setRecipients(null);
			mailMessage.setRecipientsCC(null);
			mailMessage.setRecipientsBCC(null);
            if(recipients != null) {
                for(Iterator i = recipients.iterator(); i.hasNext();) {
                    String email = (String) i.next();
                    mailMessage.addRecipient(email);
                }
            }
            if(recipientsCC != null) {
                for(Iterator i = recipientsCC.iterator(); i.hasNext();) {
                    String email = (String) i.next();
                    mailMessage.addRecipientCC(email);
                }
            }
            if(recipientsBCC != null) {
                for(Iterator i = recipientsBCC.iterator(); i.hasNext();) {
                    String email = (String) i.next();
                    mailMessage.addRecipientCC(email);
                }
            }
            if(from == null) mailMessage.setFrom(NRIMailTemplate.FROM_NRI_TECHNICAL_SUPPORT_ADDRESS_TEST);
            else mailMessage.setFrom(from);
        } else {
            if(recipients != null) {
                for(Iterator i = recipients.iterator(); i.hasNext();) {
                    String email = (String) i.next();
                    mailMessage.addRecipient(email);
                }
            }
            if(recipientsCC != null) {
                for(Iterator i = recipientsCC.iterator(); i.hasNext();) {
                    String email = (String) i.next();
                    mailMessage.addRecipientCC(email);
                }
            }
            if(recipientsBCC != null) {
                for(Iterator i = recipientsBCC.iterator(); i.hasNext();) {
                    String email = (String) i.next();
                    mailMessage.addRecipientCC(email);
                }
            }
			if(from == null) mailMessage.setFrom(NRIMailTemplate.FROM_NRI_TECHNICAL_SUPPORT_ADDRESS);
            else mailMessage.setFrom(from);
		}
		return mailService.sendIdentifiedMail(authInfo, serviceName, mailMessage);
	}

	public void sendMail(AuthInfo authInfo, java.util.List recipientList, String templateName, String subject, HashMap prop, String serviceName) {
		sendBaseMail(authInfo, new JulyHTMLMailTemplate(recipientList, templateName, subject, prop), serviceName);
	}

	public void sendHTMLMailByTemplate(AuthInfo authInfo, java.util.List recipientList, String subject, String templateName, HashMap prop, String serviceName) {
		sendBaseMail(authInfo, new JulyHTMLMailTemplate(recipientList, templateName, subject, prop), serviceName);
	}

	public Integer sendHTMLMail(AuthInfo authInfo, java.util.List recipientList, String subject, String body, HashMap prop, String serviceName) {
        SimpleJulyMailTemplate template = new SimpleJulyMailTemplate(recipientList, body, subject, prop);
        template.setMessageMimeType(MailTemplate.MIME_TYPE_TEXT_HTML);
        template.setMessageEncoding(MailTemplate.ENCODING_CP1251);
		return sendIdentifiedBaseMail(authInfo, template, serviceName);
	}

	public Integer sendTextMail(AuthInfo authInfo, java.util.List recipientList, String subject, String body, HashMap prop, String serviceName) {
        SimpleJulyMailTemplate template = new SimpleJulyMailTemplate(recipientList, body, subject, prop);
        template.setMessageMimeType(MailTemplate.MIME_TYPE_TEXT_PLAIN);
        template.setMessageEncoding(MailTemplate.ENCODING_CP1251);
        return sendIdentifiedBaseMail(authInfo, template, serviceName);
	}

    public void addRecipient(String recipientEmail) {
        if(recipientEmail == null) return;
        if(recipients == null) recipients = new ArrayList();
        if(!recipients.contains(recipientEmail))
            recipients.add(recipientEmail);
    }

    public void addRecipientCC(String recipientEmail) {
        if(recipientEmail == null) return;
        if(recipientsCC == null) recipientsCC = new ArrayList();
        if(!recipientsCC.contains(recipientEmail))
            recipientsCC.add(recipientEmail);
    }

    public void addRecipientBCC(String recipientEmail) {
        if(recipientEmail == null) return;
        if(recipientsBCC == null) recipientsBCC = new ArrayList();
        if(!recipientsBCC.contains(recipientEmail))
            recipientsBCC.add(recipientEmail);
    }

    public void setRecipients(ArrayList recipients) {
        this.recipients = recipients;
    }

    public void setRecipientsCC(ArrayList recipientsCC) {
        this.recipientsCC = recipientsCC;
    }

    public void setRecipientsBCC(ArrayList recipientsBCC) {
        this.recipientsBCC = recipientsBCC;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
