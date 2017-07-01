package WhitePages0;
import org.apache.struts.action.ActionForm;
import java.sql.*;

///****** This code is generated automatically by IBM WebSphere Studio ******/

public class DetailsForm extends ActionForm {

	private PeopleObject dataObject;
	private String _$action;

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

	public String get_$action() {
		return _$action;
	}

	public void set_$action(String val) {
		this._$action = val;
	}

	public DetailsForm() {
		super();
		dataObject = new PeopleObject();
	}

}