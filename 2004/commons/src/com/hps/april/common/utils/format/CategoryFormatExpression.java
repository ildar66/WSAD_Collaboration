package com.hps.april.common.utils.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CategoryFormatExpression implements FormatExpression {

	private CategoryCallback callback;

	public CategoryFormatExpression() {
	}
	
	public CategoryFormatExpression(CategoryCallback callback) {
		this.callback = callback;
	}

	public CategoryCallback getCallback() {
		return callback;
	}
	public void setCallback(CategoryCallback callback) {
		this.callback = callback;
	}

	/**
	 * Категоризирует список объектов по параметру, определяемому в callback</br>
	 * <pre>
	 * [obj1,obj2,obj3] -> [CategoryItem{categoryObj1,[obj1,obj2]}, 
	 *                       CategoryItem{categoryObj1,[obj3,obj4]}]
	 * </pre>
	 * Если передан CategoryChildrenCallback, тогда вызывается 
	 * CategoryChildCallback.defineChildren(List) для
	 * дочернего списка объектов каждой категории. 
	 *
	 * @param list
	 * @param callback
	 * @return List of CategoryItem
	 */
	public List execute(List target) {
		Map cache = new HashMap();
		for (int i=0; i<target.size(); i++){
			Object object = target.get(i);
			
			Object category = object;
			if (callback != null)
				category = callback.defineCategory(object);
			
			List childrens = (List) cache.get(category);
			if (childrens == null) childrens = new ArrayList();
			childrens.add(object);
			
			cache.put(category, childrens);
		}
		
		Set cacheSet = cache.keySet();
		List result = new ArrayList();
		
		if (cacheSet != null && !cacheSet.isEmpty()){
			Iterator items = cacheSet.iterator();
			while (items.hasNext()) {
				Object category = (Object) items.next();
				List childrens = (List) cache.get(category);
				
				if (callback != null && callback instanceof CategoryChildCallback){
					CategoryChildCallback childCallback = (CategoryChildCallback) callback;
					childrens = childCallback.defineChildren(childrens);
				}
				
				CategoryItem item = new CategoryItem();
				item.setCategory(category);
				item.setChildren(childrens);
				
				result.add(item);
			} 
		}

		return result;
	}

}
