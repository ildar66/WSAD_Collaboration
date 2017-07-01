package com.hps.july.rts.mail.template;

import java.util.List;

import com.hps.july.rts.object.request.ConsolidatedRequest;

/**
 * @author Dimitry Krivenko 
 * 09.03.2006
 */
public class ConsolidatedRequestDraftTemplate extends ConsolidatedRequestTemplate {
	
	public ConsolidatedRequestDraftTemplate() {
		super();
	}

	public ConsolidatedRequestDraftTemplate(ConsolidatedRequest request) {
		super(request);
	}

	public String getFrom(){
		return FROM_NRI_TECHNICAL_SUPPORT_ADDRESS;
	}
	
	/**
	 * всем участникам процесса вместе с Инициатором и сотрудникам из 
	 * списка оповещения, указанном в  конс. заявке
	 * @param request
	 * @return
	 */
	public List getTo(){
		// FIXME getTO
		return null;
	}
	
	public String getSubject(){
		return "Заявка ТС отозвана";
	}
	
	public String getBody(){
		return "Заявка ТС N "+request.getNumber()+", " +
				"созданная "+request.getCreated()+", отозвана. ";	
	}
	
	public List getAttachmentList() {
		return null;
	}
}
