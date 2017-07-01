package com.hps.july.collaboration.formbean;

import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hps.july.sync.session.ReferencesFinder;
import com.hps.july.sync.session.ReferencesFinderHome;
import com.hps.july.sync.session.dao.ReferencesFinderPersistence;
import com.hps.july.sync.utility.HomeFactory;
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
public class ReferencesForm_1 extends ActionForm {
	private ReferencesFinderHome refHome;
	private java.util.Collection result = null;
	private String comand = null;
	private String sortBy = null;
	private int pagesize = 20;
	private int pagenumber = 1;
	private boolean isNextPage = false;

	/**
	 * Get result
	 * @return java.util.Collection
	 */
	public java.util.Collection getResult() {
		return result;
	}

	/**
	 * Set result
	 * @param <code>java.util.Collection</code>
	 */
	public void setResult(java.util.Collection r) {
		result = r;
	}
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
	public ReferencesForm_1() {
		super();
		initRefHome();
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// Reset values are provided as samples only. Change as appropriate.
		//result = null;
		comand = null;
		sortBy = null;
		//pagesize = 20;
		pagenumber = 1;
		isNextPage = false;
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
			ReferencesFinder ref = refHome.create();
			if (getComand().equalsIgnoreCase("dbsAntennaPlaces")) {
				if (!isNextPage)
					result = ref.findDbsAntennaPlaces(getSortBy());
			} else if (getComand().equalsIgnoreCase("dbsPositions")) {
				if (!isNextPage)
					result = ref.findDbsPositions(getSortBy());
			} else if (getComand().equalsIgnoreCase("dbs...")) {
				//result = new Vector();
			}
		} catch (Exception e) {
			//add error.
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.info", e));
			if (e.getMessage() != null)
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.info", e.getMessage()));
			e.printStackTrace(System.out);
		}
	}

	private void initRefHome() throws EJBException {
		try {
			if (refHome == null)
				refHome = (ReferencesFinderHome) HomeFactory.singleton().getHome("ejb/ReferencesFinder");
		} catch (NamingException ex) {
			ex.printStackTrace();
			throw new EJBException("Error looking up ReferencesFinderHome: " + ex.getMessage());
		}
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
	 * Returns the pagenumber.
	 * @return int
	 */
	public int getPagenumber() {
		return pagenumber;
	}

	/**
	 * Returns the pagesize.
	 * @return int
	 */
	public int getPagesize() {
		return pagesize;
	}

	/**
	 * Sets the pagenumber.
	 * @param pagenumber The pagenumber to set
	 */
	public void setPagenumber(int pagenumber) {
		this.pagenumber = Math.max(1, Math.min(pagenumber, getPagecount()));
	}

	/**
	 * Sets the pagesize.
	 * @param pagesize The pagesize to set
	 */
	public void setPagesize(int pagesize) {
		if (pagesize <= 0)
			this.pagesize = 20;
		else
			this.pagesize = pagesize;
	}

	public int getPagecount() {
		if (result != null) {
			int listsize = result.size();
			return (listsize % pagesize == 0) ? listsize / pagesize : (listsize / pagesize + 1);
		} else
			return 0;
	}

	/**
	 * Returns the isNextPage.
	 * @return boolean
	 */
	public boolean getIsNextPage() {
		return isNextPage;
	}

	/**
	 * Sets the isNextPage.
	 * @param isNextPage The isNextPage to set
	 */
	public void setIsNextPage(boolean isNextPage) {
		this.isNextPage = isNextPage;
	}

}
