/*
 *  $Id: Person.java,v 1.4 2007/01/12 16:19:14 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.april.auth.object;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.4 $ $Date: 2007/01/12 16:19:14 $
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;

    private Collection operators;
    private Collection workers;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        StringBuffer name = new StringBuffer("");
        if(lastName != null) name.append(lastName.trim()+ " ");
        if(firstName != null && firstName.trim().length() >= 1) name.append(firstName.substring(0, 1).toUpperCase()).append(".");
        if(middleName != null && middleName.trim().length() >= 1) name.append(middleName.substring(0, 1).toUpperCase()).append(".");
        return name.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection getWorkers() {
        return workers;
    }

    public void setWorkers(Collection workers) {
        this.workers = workers;
    }

    public Collection getOperators() {
        return operators;
    }

    public void setOperators(Collection operators) {
        this.operators = operators;
    }

    public String getPhone() {
        if(phone == null) {
            if(workers != null) {
                for (Iterator i = workers.iterator(); i.hasNext();) {
                    Worker worker = (Worker)i.next();
                    phone = worker.getPhone();
                    email = worker.getEmail();
                }
            }
        }
        return phone;
    }

    public String getEmail() {
        if(email == null) {
            if(workers != null) {
                for (Iterator i = workers.iterator(); i.hasNext();) {
                    Worker worker = (Worker)i.next();
                    phone = worker.getPhone();
                    email = worker.getEmail();
                }
            }
        }
        return email;
    }



    /**
     * @param string
     */
    public void setEmail(String string) {
        email = string;
    }

    /**
     * @param string
     */
    public void setPhone(String string) {
        phone = string;
    }

    public String toString(){
        return getName()+ ((getPhone()!=null)?("  " + getPhone()):"");
    }

    public int hashCode() {
        int result;
        result = ((this.id != null)?this.id.hashCode():0);
        result = 17 * result + ((this.getName() != null)?this.getName().hashCode():0);
        return result;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person person = (Person)obj;
            if(person.getId() != null && person.getId().equals(getId())
                    && person.getName() != null && person.getName().trim().equals(getName().trim())) return true;
        }
        return false;
    }



}
