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

public class DetailsAction extends Action {

	public ActionForward perform(
		ActionMapping actionMapping,
		ActionForm actionForm,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {
		HttpSession session = request.getSession();
		ActionForward forward = null;
		String _$action = ((DetailsForm) actionForm).get_$action();
		if (((DetailsForm) actionForm).getLast_name() != null) {
			request.setAttribute(
				"last_name",
				((DetailsForm) actionForm).getLast_name());
		}
		if (((DetailsForm) actionForm).getLocation() != null) {
			request.setAttribute(
				"location",
				((DetailsForm) actionForm).getLocation());
		}
		if (((DetailsForm) actionForm).getCategory() != null) {
			request.setAttribute(
				"category",
				((DetailsForm) actionForm).getCategory());
		}
		return forward;
	}

}