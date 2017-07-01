package com.hps.july.collaboration.formbean;

import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hps.july.collaboration.helper.GenericHelper;
import com.hps.july.sync.model.Page;
import com.hps.july.sync.model.dao.DAOFactory;
import com.hps.july.sync.util.tracer.Debug;

import java.util.*;

/**
 * Form bean for a Struts application.
 * Users may access 2 fields on this form:
 * <ul>
 * <li>result - [your comment here]
 * <li>comand - [your comment here]
 * </ul>
 * @version 	1.0
 * @author  Shafigullin Ildar
 */
public class GenericForm extends ActionForm {
	private GenericHelper refHelper;
	private String comand = null;
	private String school = "";
	private int count = 2;
	private int start = 0;
	private String sortBy = null;
	private Page page = null;
	private boolean useFastLane = true;	

	/**
	 * Get comand
	 * @return String
	 */
	public String getComand() {
		return comand;
	}

	/**
	 * Set comand
	 * @param <code>String</code>
	 */
	public void setComand(String c) {
		comand = c;
	}

	/**
	* Constructor
	*/
	public GenericForm() {
		super();
		init();
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// Reset values are provided as samples only. Change as appropriate.
		comand = null;
		sortBy = null;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new ActionError("error.field.required"));
		// }
		return errors;
	}
	/**
	 * 
	 * @param errors
	 */
	
	public void doTask(ActionErrors errors) {
		try {
			setStart();
			page = refHelper.searchPage(getComand(), start, count, getSortBy());
		} catch (Exception e) {
			//add error.
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.info", e));
			if (e.getMessage() != null)
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.info", e.getMessage()));
			e.printStackTrace(System.out);
		}
	}

	private void init() throws EJBException {
		refHelper = new GenericHelper(useFastLane);
	}
	
	private void setStart() {
		if (page != null) {
			if (getSchool().equalsIgnoreCase("next"))
				start = page.getStartOfNextPage();
			else if (getSchool().equalsIgnoreCase("previous"))
				start = page.getStartOfPreviousPage();
		} else
			start = 0;
	}
	/**
	 * Returns the sortBy.
	 * @return String
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * Sets the sortBy.
	 * @param sortBy The sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @return
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * @param string
	 */
	public void setSchool(String string) {
		school = string;
	}

	/**
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param i
	 */
	public void setCount(int i) {
		count = i;
	}

	/**
	 * @param i
	 */
	public void setStart(int i) {
		start = i;
	}

	/**
	 * @return
	 */
	public boolean isUseFastLane() {
		return useFastLane;
	}

	/**
	 * @param b
	 */
	public void setUseFastLane(boolean b) {
		useFastLane = b;
		Debug.println("from GenericForm setUseFastLane="+b);
		init();
	}

}
