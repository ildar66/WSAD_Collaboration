/*
 *  $Id: Operator.java,v 1.3 2007/05/23 14:02:21 mmorev Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.april.auth.object;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.3 $ $Date: 2007/05/23 14:02:21 $
 */
public class Operator implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String login;
	private boolean enabled;
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Operator)) {
			return false;
		}
		Operator item = (Operator)obj;
		return item.getId().equals(this.getId());		
	}

	private Person person;

	private Collection roleList; 
	
	public Collection getRoleList() {
		return roleList;
	}

	public void setRoleList(Collection roleList) {
		this.roleList = roleList;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	
}
