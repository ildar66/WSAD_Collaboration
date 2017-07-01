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

public class FillDetailsAction extends Action {

	public ActionForward perform(
		ActionMapping actionMapping,
		ActionForm actionForm,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {
		FillDetailsJavaBean bean = new FillDetailsJavaBean();
		bean.setCategory((String) request.getAttribute("category"));
		bean.setPhonenumber((String) request.getAttribute("phonenumber"));
		bean.setLocation((String) request.getAttribute("location"));
		bean.setLast_name((String) request.getAttribute("last_name"));
		bean.setFaxnumber((String) request.getAttribute("faxnumber"));
		bean.setEmail((String) request.getAttribute("email"));
		bean.setNickname((String) request.getAttribute("nickname"));
		bean.setSelectedRow((PeopleObject) request.getAttribute("selectedRow"));
		bean.setSelected((PeopleObject) request.getSession().getAttribute("Selected"));
		bean.execute();
		if (bean.getCategory() != null) {
			request.setAttribute("category", bean.getCategory());
		}
		if (bean.getPhonenumber() != null) {
			request.setAttribute("phonenumber", bean.getPhonenumber());
		}
		if (bean.getLocation() != null) {
			request.setAttribute("location", bean.getLocation());
		}
		if (bean.getLast_name() != null) {
			request.setAttribute("last_name", bean.getLast_name());
		}
		if (bean.getFaxnumber() != null) {
			request.setAttribute("faxnumber", bean.getFaxnumber());
		}
		if (bean.getEmail() != null) {
			request.setAttribute("email", bean.getEmail());
		}
		if (bean.getNickname() != null) {
			request.setAttribute("nickname", bean.getNickname());
		}
		if (bean.getSelected() != null) {
			request.getSession().setAttribute("Selected", bean.getSelected());
		}
		String _$state = bean.get_$State();
		HashMap map = new HashMap();
		Enumeration names = request.getSession().getAttributeNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			map.put(name, request.getSession().getAttribute(name));
		}
		if (_$state.equals("OK")) {
			try {
				DetailsForm form = new DetailsForm();
				BeanUtils.populate(form, map);
				PropertyUtils.copyProperties(form, bean);
				request.getSession().setAttribute("DetailsForm", form);
			} catch (Exception exc) {
			}
		}
		if (_$state.endsWith("Error")) {
			try {
				SearchCriteriaForm form = new SearchCriteriaForm();
				BeanUtils.populate(form, map);
				PropertyUtils.copyProperties(form, bean);
				request.getSession().setAttribute("SearchCriteriaForm", form);
			} catch (Exception exc) {
			}
			try {
				SearchContentForm form = new SearchContentForm();
				BeanUtils.populate(form, map);
				PropertyUtils.copyProperties(form, bean);
				request.getSession().setAttribute("SearchContentForm", form);
			} catch (Exception exc) {
			}
			request.getSession().setAttribute("FatalErrorMessage", bean.get_$ErrorMessage());
			request.getSession().setAttribute("ErrorInBean", "FillDetailsJavaBean");
		}
		return actionMapping.findForward(_$state);
	}

}