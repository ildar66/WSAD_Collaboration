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

public class SearchContentAction extends Action {

	public ActionForward perform(
		ActionMapping actionMapping,
		ActionForm actionForm,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {
		HttpSession session = request.getSession();
		ActionForward forward = null;
		String _$action = ((SearchContentForm) actionForm).get_$action();
		if ((_$action == null) || (_$action.equals("")))
			_$action = new String("SearchContentSubmit");
		if (((SearchContentForm) actionForm).getSelectedIndex() != null) {
			java.util.Vector PeopleSearchContentList =
				(java.util.Vector) request.getSession().getAttribute(
					"PeopleSearchContentList");
			request.setAttribute(
				"selectedRow",
				PeopleSearchContentList.elementAt(
					((SearchContentForm) actionForm)
						.getSelectedIndex()
						.intValue()));
		}
		if (_$action.equals("SearchContentSubmit")) {
			forward = actionMapping.findForward("SearchContentSubmit");
		}
		return forward;
	}

}