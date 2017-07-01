package WhitePages1;
import java.lang.*;

///****** This code is generated automatically by IBM WebSphere Studio ******/

public class PeopleObject {

	private String nickname;
	private String last_name;
	private String location;
	private String category;
	private Integer client_id;
	private String bill;
	private String comments;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String value) {
		this.nickname = value;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String value) {
		this.last_name = value;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String value) {
		this.location = value;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String value) {
		this.category = value;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public String getClient_idString() {
		if (getClient_id() == null)
			return null;
		return getClient_id().toString();
	}

	public void setClient_id(Integer value) {
		this.client_id = value;
	}

	public void setClient_idString(String value) {
		if ((value != null) && (!value.equals("")))
			setClient_id(new Integer(value));
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String value) {
		this.bill = value;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String value) {
		this.comments = value;
	}

}