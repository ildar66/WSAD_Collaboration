package WhitePages1;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import java.util.HashMap;
import java.util.Enumeration;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.BeanUtils;

///****** This code is generated automatically by IBM WebSphere Studio ******/

public class SearchByCityAction extends Action {

	public ActionForward perform(
		ActionMapping actionMapping,
		ActionForm actionForm,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {
		SearchByCityJavaBean bean = new SearchByCityJavaBean();
		bean.setLocation((String) request.getAttribute("location"));
		bean.execute();
		if (bean.getLocation() != null) {
			request.setAttribute("location", bean.getLocation());
		}
		request.getSession().setAttribute(
			"PeopleSearchContentList",
			bean.getPeopleSearchContentList());
		request.getSession().setAttribute(
			"PeopleSearchCriteriaList",
			bean.getPeopleSearchCriteriaList());
		String _$state = bean.get_$State();
		HashMap map = new HashMap();
		Enumeration names = request.getSession().getAttributeNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			map.put(name, request.getSession().getAttribute(name));
		}
		if (_$state.equals("SearchByCityOK")) {
			try {
				SearchCriteriaForm form = new SearchCriteriaForm();
				BeanUtils.populate(form, map);
				PropertyUtils.copyProperties(form, bean);
				request.getSession().setAttribute("SearchCriteriaForm", form);
			} catch (Exception exc) {
			}
		}
		if (_$state.equals("SearchByCityOK")) {
			try {
				SearchContentForm form = new SearchContentForm();
				BeanUtils.populate(form, map);
				PropertyUtils.copyProperties(form, bean);
				request.getSession().setAttribute("SearchContentForm", form);
			} catch (Exception exc) {
			}
		}
		if (_$state.endsWith("Error")) {
			request.getSession().setAttribute(
				"FatalErrorMessage",
				bean.get_$ErrorMessage());
			request.getSession().setAttribute(
				"ErrorInBean",
				"SearchByCityJavaBean");
		}
		return actionMapping.findForward(_$state);
	}

}