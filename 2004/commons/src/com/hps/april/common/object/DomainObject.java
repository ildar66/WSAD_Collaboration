/*
 *  $Id: DomainObject.java,v 1.1 2006/10/10 15:03:11 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.april.common.object;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2006/10/10 15:03:11 $
 */
public class DomainObject {

    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if(!this.getClass().isInstance(o)) return false;
        DomainObject domainObject = (DomainObject) o;
        boolean result = true;
        boolean result1 = true;
        if(getId() != null ? !getId().equals(domainObject.getId()) : domainObject.getId() != null) result = false;
        if(getName() != null ? !getName().equals(domainObject.getName()) : domainObject.getName() != null) result1 = false;
        return (result && result1);
    }

    public int hashCode() {
        int result;
        result = (getId() != null) ? getId().hashCode() : 0;
        result = 19 * result + ((getName() != null) ? getName().hashCode() : 0);
        return result;
    }

}
