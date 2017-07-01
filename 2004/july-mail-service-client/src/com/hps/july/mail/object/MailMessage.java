/*
 *  $Id: MailMessage.java,v 1.3 2007/03/26 17:24:43 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.mail.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>  
 * @version $Revision: 1.3 $ $Date: 2007/03/26 17:24:43 $
 */
public class MailMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	protected static String DEFAULT_MESSAGE_MIME_TYPE = "text/plain";
	protected static String DEFAULT_MESSAGE_ENCODING = "ISO-8859-1";

    private Integer id;
    private String from;
	private String subject;

    private List recipients;
    private List recipientsCC;
    private List recipientsBCC;
    
    private List replyTo;

	private String body;
	
	private String messageMimeType;
	private String messageEncoding;
	
	private List attachmentList;

    public MailMessage() {
        id = null;
        from = null;
        subject = null;
        body = null;
        messageMimeType = DEFAULT_MESSAGE_MIME_TYPE;
        messageEncoding = DEFAULT_MESSAGE_ENCODING;

        recipients = null;
        recipientsCC = null;
        recipientsBCC = null;
        attachmentList = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
	 * @return List of String
	 */
	public List getAttachmentList() {
		return attachmentList;
	}

	/**
	 * @param attachmentList - List of String
	 */
	public void setAttachmentList(List attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return List of String
	 */
	public List getRecipients() {
		return recipients;
	}

	/**
	 * @param recipients - List of String
	 */
	public void setRecipients(List recipients) {
		this.recipients = recipients;
	}

    public List getRecipientsCC() {
        return recipientsCC;
    }

    public void setRecipientsCC(List recipientsCC) {
        this.recipientsCC = recipientsCC;
    }

	public List getRecipientsBCC() {
		return recipientsBCC;
	}

	public void setRecipientsBCC(List recipientsBCC) {
		this.recipientsBCC = recipientsBCC;
	}

	public List getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(List replyTo) {
		this.replyTo = replyTo;
	}

    public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void addRecipient(String recipientEmail){
		if (recipients == null) recipients = new ArrayList();
		if (!recipients.contains(recipientEmail))
			recipients.add(recipientEmail);
	}
	
	public void addRecipientCC(String recipientEmail){
		if (recipientsCC == null) recipientsCC = new ArrayList();
		if (!recipientsCC.contains(recipientEmail))
			recipientsCC.add(recipientEmail);
	}

	public void addRecipientBCC(String recipientEmail){
		if (recipientsBCC == null) recipientsBCC = new ArrayList();
		if (!recipientsBCC.contains(recipientEmail))
			recipientsBCC.add(recipientEmail);
	}

	public void addReplyTo(String replyToEmail){
		if (replyTo == null) replyTo = new ArrayList();
		if (!replyTo.contains(replyToEmail)) replyTo.add(replyToEmail);
	}

	public void addAttachmentFileName(String fileName){
		if(attachmentList == null) attachmentList = new ArrayList();
		attachmentList.add(fileName);
	}
	
	public String getMessageEncoding() {
		return messageEncoding;
	}
	public void setMessageEncoding(String messageEncoding) {
		this.messageEncoding = messageEncoding;
	}
	public String getMessageMimeType() {
		return messageMimeType;
	}
	public void setMessageMimeType(String messageMimeType) {
		this.messageMimeType = messageMimeType;
	}

	public String toString(){
		return "\nMailMessage:\n" +
				"\tFrom=["+getFrom()+"]\n" +
				"\tTo="+getRecipients()+"\n" +
				"\tCC="+getRecipientsCC()+"\n" +
				"\tBCC="+getRecipientsBCC()+"\n" +
				"\tSubject="+getSubject()+"";
	}
	
}
