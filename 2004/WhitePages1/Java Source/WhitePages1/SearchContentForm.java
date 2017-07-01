package WhitePages1;
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

	public Integer getClient_id() {
		return getDataObject().getClient_id();
	}

	public String getClient_idString() {
		if (getClient_id() == null)
			return null;
		return getClient_id().toString();
	}

	public void setClient_id(Integer value) {
		getDataObject().setClient_id(value);
	}

	public void setClient_idString(String value) {
		if ((value != null) && (!value.equals("")))
			setClient_id(new Integer(value));
	}

	public String getComments() {
		return getDataObject().getComments();
	}

	public void setComments(String value) {
		getDataObject().setComments(value);
	}

	public String getBill() {
		return getDataObject().getBill();
	}

	public void setBill(String value) {
		getDataObject().setBill(value);
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