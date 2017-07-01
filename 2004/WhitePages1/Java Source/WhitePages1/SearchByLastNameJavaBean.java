package WhitePages1;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.sql.*;
import org.apache.commons.beanutils.PropertyUtils;
import java.lang.reflect.InvocationTargetException;

///****** This code is generated automatically by IBM WebSphere Studio ******/

public class SearchByLastNameJavaBean {

	protected static String _$state;
	protected static String _$errorMessage;
	protected String last_name;
	protected Vector peopleSearchContentList = new Vector();
	protected Vector peopleSearchCriteriaList = new Vector();

	public SearchByLastNameJavaBean() {
		super();
		initialize();
	}

	public void initialize() {
		_$state = new String("SearchByLastNameOK");
	}

	public String get_$State() {
		return _$state;
	}

	public void set_$State(String newVal) {
		_$state = newVal;
	}

	public String get_$ErrorMessage() {
		return _$errorMessage;
	}

	public void set_$ErrorMessage(String newVal) {
		_$errorMessage = newVal;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Vector getPeopleSearchContentList() {
		return peopleSearchContentList;
	}

	public void setPeopleSearchContentList(Vector peopleSearchContentList) {
		this.peopleSearchContentList = peopleSearchContentList;
	}

	public Vector getPeopleSearchCriteriaList() {
		return peopleSearchCriteriaList;
	}

	public void setPeopleSearchCriteriaList(Vector peopleSearchCriteriaList) {
		this.peopleSearchCriteriaList = peopleSearchCriteriaList;
	}

	public void execute() {
		try {
			PeopleDataAccessBean Peopledab = new PeopleDataAccessBean();
			Peopledab.setTraceOn(true);
			Peopledab.executeReadByFieldForAction_1(getLast_name());
			while (Peopledab.readNextRow()) {
				PeopleObject targetObject = new PeopleObject();
				PropertyUtils.copyProperties(targetObject, Peopledab);
				peopleSearchContentList.add(targetObject);
			}
			Peopledab.executeSelectAll();
			while (Peopledab.readNextRow()) {
				PeopleObject targetObject = new PeopleObject();
				PropertyUtils.copyProperties(targetObject, Peopledab);
				peopleSearchCriteriaList.add(targetObject);
			}
			Peopledab.close();
		} catch (Exception exc) {
			set_$ErrorMessage(exc.toString());
			set_$State("SearchByLastNameError");
		}
	}

}