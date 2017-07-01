package WhitePages0;
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

public class SearchByPositionJavaBean {

	protected static String _$state;
	protected static String _$errorMessage;
	protected String category;
	protected Vector peopleSearchContentList = new Vector();
	protected Vector peopleSearchCriteriaList = new Vector();

	public SearchByPositionJavaBean() {
		super();
		initialize();
	}

	public void initialize() {
		_$state = new String("SearchByPositionOK");
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
			Peopledab.executeReadByFieldForAction_3(getCategory());
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
			set_$State("SearchByPositionError");
		}
	}

}