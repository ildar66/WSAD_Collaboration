/*
 *  $Id: JulyRole.java,v 1.5 2007/04/24 13:31:47 mmorev Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.april.auth.object;

import java.io.Serializable;

import com.hps.april.auth.object.Role;

/**
 * @author Dimitry Krivenko
 * @version $Revision: 1.5 $ $Date: 2007/04/24 13:31:47 $
 */
public class JulyRole implements Role, Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	
	private String taskName;
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public JulyRole() {}
	
	public JulyRole(String name) {
		setName(name);
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    public int hashCode() {
        return (name == null) ? (-1) : name.hashCode();
    }

    public final boolean equals(Object obj) {
		if (obj instanceof JulyRole) {
			JulyRole role = (JulyRole) obj;
			if (role.getName() != null && role.getName().equals(getName())) return true;
		}
		return false;
	}
	
}
