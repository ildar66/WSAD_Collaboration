package WhitePages0;
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

public class SearchCriteriaAction extends Action {

	public ActionForward perform(
		ActionMapping actionMapping,
		ActionForm actionForm,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {
		HttpSession session = request.getSession();
		ActionForward forward = null;
		String _$action = ((SearchCriteriaForm) actionForm).get_$action();
		if ((_$action == null) || (_$action.equals("")))
			_$action = new String("Submit1");
		if (((SearchCriteriaForm) actionForm).getLast_name() != null) {
			request.setAttribute(
				"last_name",
				((SearchCriteriaForm) actionForm).getLast_name());
		}
		if (((SearchCriteriaForm) actionForm).getLocation() != null) {
			request.setAttribute(
				"location",
				((SearchCriteriaForm) actionForm).getLocation());
		}
		if (((SearchCriteriaForm) actionForm).getCategory() != null) {
			request.setAttribute(
				"category",
				((SearchCriteriaForm) actionForm).getCategory());
		}
		if (((SearchCriteriaForm) actionForm).getDummy() != null) {
			session.setAttribute(
				"dummy",
				((SearchCriteriaForm) actionForm).getDummy());
		}
		if (_$action.equals("Submit")) {
			forward = actionMapping.findForward("Submit");
		}
		if (_$action.equals("Submit0")) {
			forward = actionMapping.findForward("Submit0");
		}
		if (_$action.equals("Submit1")) {
			forward = actionMapping.findForward("Submit1");
		}
		return forward;
	}

}