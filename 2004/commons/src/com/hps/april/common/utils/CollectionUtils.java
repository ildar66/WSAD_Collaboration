package com.hps.april.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CollectionUtils {

	public static List toList(Set value){
		if (value == null) return null;
		return toList(value.toArray());
	}

	public static List toList(Object[] value){
		if (value == null) return null;
		List list = new ArrayList();
		org.apache.commons.collections.CollectionUtils.addAll(list, value);
		return list;
	}
	
}
