package com.hps.july.core;

import java.util.*;
/**
 * @author Shafigullin Ildar
 * Содержит ключи и поля объекта DB
 */
public class RowDBObject {
	private HashMap keysColumns = new HashMap();
	private HashMap columns = new HashMap();

	/**
	 * Returns the columns.
	 * @return HashMap
	 */
	public HashMap getColumns() {
		return columns;
	}

	/**
	 * Returns the keysColumns
	 * @return HashMap
	 */
	public HashMap getKeysColumns() {
		return keysColumns;
	}

	public void addKeyColumn(String argKey, Object argValue) {
		if (argKey != null) {
			keysColumns.put(argKey, argValue);
		}
	}

    public void addColumn(String argKey, Object argValue) {
		if (argKey != null) {
			columns.put(argKey, argValue);
		}
	}

    public String dump() {
		return "keysColumns="
			+ keysColumns.toString()
			+ "columns="
			+ columns.toString();
	}
    
    public String toString() {
		return "RowDBObject keysColumns=" + keysColumns.toString();
	}
}
