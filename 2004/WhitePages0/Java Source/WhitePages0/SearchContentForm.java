package WhitePages0;
import org.apache.struts.action.ActionForm;
import java.sql.*;

///****** This code is generated automatically by IBM WebSphere Studio ******/

public class SearchContentForm extends ActionForm {

	private PeopleObject dataObject;
	private String _$action;
	private Integer selectedIndex;

	public PeopleObject getDataObject() {
		if (dataObject == null)
			dataObject = new PeopleObject();
		return dataObject;
	}

	public void setDataObject(PeopleObject value) {
		this.dataObject = value;
	}

	public String getLast_name() {
		return getDataObject().getLast_name();
	}

	public void setLast_name(String value) {
		getDataObject().setLast_name(value);
	}

	public String getLocation() {
		return getDataObject().getLocation();
	}

	public void setLocation(String value) {
		getDataObject().setLocation(value);
	}

	public String getCategory() {
		return getDataObject().getCategory();
	}

	public void setCategory(String value) {
		getDataObject().setCategory(value);
	}

	public String getEmail() {
		return getDataObject().getEmail();
	}

	public void setEmail(String value) {
		getDataObject().setEmail(value);
	}

	public String getFaxnumber() {
		return getDataObject().getFaxnumber();
	}

	public void setFaxnumber(String value) {
		getDataObject().setFaxnumber(value);
	}

	public String getPhonenumber() {
		return getDataObject().getPhonenumber();
	}

	public void setPhonenumber(String value) {
		getDataObject().setPhonenumber(value);
	}

	public String get_$action() {
		return _$action;
	}

	public void set_$action(String val) {
		this._$action = val;
	}

	public SearchContentForm() {
		super();
		dataObject = new PeopleObject();
	}

	public Integer getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(Integer val) {
		this.selectedIndex = val;
	}

}