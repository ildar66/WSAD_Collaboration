package com.hps.april.common.utils.format;

import java.util.ArrayList;
import java.util.List;

public class CategoryItem {

	private Object category;
	private List children = new ArrayList();
	
	public Object getCategory() {
		return category;
	}
	public void setCategory(Object category) {
		this.category = category;
	}
	public List getChildren() {
		return children;
	}
	public void setChildren(List children) {
		this.children = children;
	}
	public List getList() {
		return children;
	}
	public void setList(List children) {
		this.children = children;
	}
	public Object getObject() {
		return category;
	}
	public void setObject(Object category) {
		this.category = category;
	}
	
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((category == null) ? 0 : category.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		if (getClass() != obj.getClass()) return false;
		
		final CategoryItem other = (CategoryItem) obj;
		if (category == null) {
			if (other.category != null)	return false;
		} else if (!category.equals(other.category)) return false;
		
		return true;
	}
	public String toString() {
		return "CategoryItem{obj="+category+",children="+children+"}";
	}
	
	
	
}
