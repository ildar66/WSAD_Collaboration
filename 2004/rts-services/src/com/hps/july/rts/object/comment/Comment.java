package com.hps.july.rts.object.comment;

import com.hps.april.common.object.ExtensibleObject;
import com.hps.april.auth.object.Person;

import java.util.Date;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 06.02.2006
 * Time: 18:25:39
 */
public class Comment extends ExtensibleObject {

    public static final String COMMENT_INFO = "I";
    public static final String COMMENT_WARN = "W";
    public static final String COMMENT_ERROR = "E";
    public static final String COMMENT_USER = "U";

    private String text;
    private Date date;
    
    // association to Person
    private Person person;
	private Integer personId;

    private String requestId;

    private String type;

    public Comment() {
    }

    /**
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @return
	 */
	public Integer getPersonId() {
		return personId;
	}

	/**
	 * @return
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;
		setPersonId(person.getId());
	}

	/**
	 * @param long1
	 */
	public void setPersonId(Integer long1) {
		personId = long1;
	}

	/**
	 * @param string
	 */
	public void setRequestId(String string) {
		requestId = string;
	}

	/**
	 * @param string
	 */
	public void setText(String string) {
		text = string;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Comment:[id="+ getId() + ",type="+ getType() + ",text="+ getText() + ",requestId="+getRequestId()+",date="+ getDate()+",personId="+getPersonId()+"]";
	}


}
