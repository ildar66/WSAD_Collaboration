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

public class FillDetailsJavaBean {

	protected static String _$state;
	protected static String _$errorMessage;
	protected PeopleObject selectedRow;
	private PeopleObject selected = new PeopleObject();
	protected String nickname;
	protected String last_name;
	protected String location;
	protected String category;
	protected String email;
	protected String faxnumber;
	protected String phonenumber;
	private PeopleObject peopleObject;

	public FillDetailsJavaBean() {
		super();
		initialize();
	}

	public void initialize() {
		_$state = new String("OK");
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

	public PeopleObject getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(PeopleObject newVal) {
		this.selectedRow = newVal;
	}

	public PeopleObject getSelected() {
		return selected;
	}

	public void setSelected(PeopleObject value) {
		this.selected = value;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaxnumber() {
		return faxnumber;
	}

	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public PeopleObject getPeopleObject() {
		if (peopleObject == null)
			peopleObject = new PeopleObject();
		return peopleObject;
	}

	public void setPeopleObject(PeopleObject value) {
		this.peopleObject = value;
	}

	public void execute() {
		try {
			setSelected(getSelectedRow());
			setNickname(new String(getSelected().getNickname()));
			PeopleDataAccessBean Peopledab = new PeopleDataAccessBean();
			Peopledab.setTraceOn(true);
			Peopledab.setNickname(getNickname());
			Peopledab.executeRead();
			PeopleObject targetObject = new PeopleObject();
			if (Peopledab.readNextRow()) {
				PropertyUtils.copyProperties(targetObject, Peopledab);
				PropertyUtils.copyProperties(this, targetObject);
				setPeopleObject(targetObject);
			} else {
				set_$State("Error");
			}
			Peopledab.close();
		} catch (Exception exc) {
			set_$ErrorMessage(exc.toString());
			set_$State("Error");
		}
	}

}