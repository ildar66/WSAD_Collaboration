package com.hps.april.common.utils.format;

import java.util.List;


public class FormatService {

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
	public List categoryList(List list, CategoryCallback callback) {
		return new CategoryFormatExpression(callback).execute(list);
	}

	public List sortList(List target, Object[] sortValues){
		return sortList(target, "", sortValues);		
	}
	
	
	public List sortList(List target, String property, Object[] sortValues){
		return new SortListFormatExpression(property, sortValues).execute(target);
	}
	
	public List filterNotEqual(List list, Object[] objects) {
		return filterNotEqual(list, null,  objects);
	}

	public List filterNotEqual(List list, String property, Object[] objects) {
		return new NotEqualFormatExpression(property, objects).execute(list);
	}
	
	public List filterEqual(List list, Object[] objects){
		return filterEqual(list, null, objects);
	}

	public List filterEqual(List list, String property, Object object){
		return filterEqual(list, property, new Object[] {object});
	}
	
	public List filterEqual(List list, String property, Object[] objects){
		return new EqualFormatExpression(property, objects).execute(list);
	}

	
	
}
