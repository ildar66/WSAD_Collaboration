package com.hps.july.collaboration.formbean;

import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hps.july.collaboration.helper.ReferencesHelper;
import com.hps.july.sync.model.Page;
import com.hps.july.sync.model.dao.DAOFactory;

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
public class ReferencesForm_Page extends ActionForm {
	private ReferencesHelper refHelper;
	private String comand = null;
	private String school = "";
	private int count = 2;
	private int start = 0;
	private String sortBy = null;
	private Page page = null;
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
	public ReferencesForm_Page() {
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
	* Insert the method's description here.
	* Creation date: (11.06.2004 16:34:53)
	*/
	public void doTask(ActionErrors errors) {
		try {
			// create the stateless session bean			
			if (getComand().equalsIgnoreCase("dbsAntennaPlaces")) {
			} else if (getComand().equalsIgnoreCase("dbsPositions")) {
				if (getSchool().equalsIgnoreCase("next"))
					start = page.getStartOfNextPage();
				else if (getSchool().equalsIgnoreCase("previous"))
					start = page.getStartOfPreviousPage();
				page = refHelper.searchDbsPositions_Page(start, count, getSortBy());
			} else if (getComand().equalsIgnoreCase("dbs...")) {
			}
		} catch (Exception e) {
			//add error.
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.info", e));
			if (e.getMessage() != null)
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.info", e.getMessage()));
			e.printStackTrace(System.out);
		}
	}

	private void init() throws EJBException {
		refHelper = new ReferencesHelper(true, DAOFactory.INFORMIX, true);
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

}
