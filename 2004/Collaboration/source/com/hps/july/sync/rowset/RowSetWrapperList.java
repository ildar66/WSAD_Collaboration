package com.hps.july.sync.rowset;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.sql.*;

/**
 * @author 
 *
 * Read only implementation of the List interface. 
 * It supports iterators and absolute positioning.
 */
public class RowSetWrapperList implements List, Serializable {
	// variable to hold a RowSet instance
	private RowSet rowSet;

	public RowSetWrapperList(RowSet rowSet) {
		this.rowSet = rowSet;
		//. . .
	}

	private class DataRowListIterator implements ListIterator {
		int currentRow = 0;
		// sets the rowset cursor to next and returns
		// the Transfer object
		public Object next() {
			// get transfer object for next row of RowSetWrapperList
			currentRow++;
			return get(currentRow);
		}
		/**
		 * @see java.util.ListIterator#add(Object)
		 */
		public void add(Object o) {
		}

		/**
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			// Check the cursor position in the rowSet using
			// isLast, isAfterLast, isEmpty methods and
			// return true or false accordingly
			return false; //temp
		}

		/**
		 * @see java.util.ListIterator#hasPrevious()
		 */
		public boolean hasPrevious() {
			// Check the cursor position in the rowSet using
			// isFirst, isBeforeFirst, isEmpty methods and
			// return true or false accordingly
			return false; //temp
		}

		/**
		 * @see java.util.ListIterator#nextIndex()
		 */
		public int nextIndex() {
			return 0;
		}

		/**
		 * @see java.util.ListIterator#previous()
		 */
		public Object previous() {
			// get transfer object for previous row of
			// RowSetWrapperList  
			currentRow--;
			return get(currentRow);
		}

		/**
		 * @see java.util.ListIterator#previousIndex()
		 */
		public int previousIndex() {
			return 0;
		}

		/**
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
		}

		/**
		 * @see java.util.ListIterator#set(Object)
		 */
		public void set(Object o) {
		}

	}

	// implement the List interface methods

	/**
	 * @see java.util.Collection#size()
	 */
	public int size() {
		return 0;
	}

	/**
	 * @see java.util.Collection#isEmpty()
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * @see java.util.Collection#contains(Object)
	 */
	public boolean contains(Object o) {
		return false;
	}

	/**
	 * @see java.util.Collection#iterator()
	 */
	// Returns an iterator over the elements in this list in 
	// proper sequence. It is possible to define multiple 
	// independent iterators for the same RowSetWrapperList
	// object.

	public Iterator iterator() {
		try {
			rowSet.beforeFirst();
		} catch (SQLException anException) {
			System.out.println("Error moving RowSet before first row." + anException);
		}

		return this.listIterator();
	}

	/**
	 * @see java.util.Collection#toArray()
	 */
	public Object[] toArray() {
		return null;
	}

	/**
	 * @see java.util.Collection#toArray(Object[])
	 */
	public Object[] toArray(Object[] a) {
		return null;
	}

	/**
	 * @see java.util.Collection#add(Object)
	 */
	public boolean add(Object o) {
		return false;
	}

	/**
	 * @see java.util.Collection#remove(Object)
	 */
	public boolean remove(Object o) {
		return false;
	}

	/**
	 * @see java.util.Collection#containsAll(Collection)
	 */
	public boolean containsAll(Collection c) {
		return false;
	}

	/**
	 * @see java.util.Collection#addAll(Collection)
	 */
	public boolean addAll(Collection c) {
		return false;
	}

	/**
	 * @see java.util.List#addAll(int, Collection)
	 */
	public boolean addAll(int index, Collection c) {
		return false;
	}

	/**
	 * @see java.util.Collection#removeAll(Collection)
	 */
	public boolean removeAll(Collection c) {
		return false;
	}

	/**
	 * @see java.util.Collection#retainAll(Collection)
	 */
	public boolean retainAll(Collection c) {
		return false;
	}

	/**
	 * @see java.util.Collection#clear()
	 */
	public void clear() {
	}

	/**
	 * @see java.util.List#get(int)
	 */
	// return the current row as a transfer object
	public Object get(int index) {
		try {
			rowSet.absolute(index);
		} catch (SQLException anException) {
			// handle exception
		}
		// create a new transfer object and return
		return TORowMapper.createCustomerTO(rowSet);
	}

	/**
	 * @see java.util.List#set(int, Object)
	 */
	public Object set(int index, Object element) {
		return null;
	}

	/**
	 * @see java.util.List#add(int, Object)
	 */
	public void add(int index, Object element) {
	}

	/**
	 * @see java.util.List#remove(int)
	 */
	public Object remove(int index) {
		return null;
	}

	/**
	 * @see java.util.List#indexOf(Object)
	 */
	public int indexOf(Object o) {
		return 0;
	}

	/**
	 * @see java.util.List#lastIndexOf(Object)
	 */
	public int lastIndexOf(Object o) {
		return 0;
	}

	/**
	 * @see java.util.List#listIterator()
	 */
	// Create a List Iterator that can iterate over the
	// rowset 
	public ListIterator listIterator() {
		// ListResultIterator is implemented as an inner class 
		return new DataRowListIterator();
	}

	/**
	 * @see java.util.List#listIterator(int)
	 */
	public ListIterator listIterator(int index) {
		return null;
	}

	/**
	 * @see java.util.List#subList(int, int)
	 */
	// Returns a Sub List of the current list.
	public List subList(int fromIndex, int toIndex){
		try {
			// Create a new RowSet with the required rows
			ReadOnlyRowSet roRowSet = new ReadOnlyRowSet();
			roRowSet.populate(this.rowSet, fromIndex, toIndex);

			// Create a new RowSetWrapperList instance and
			// return it
			return new RowSetWrapperList(roRowSet);
		} catch (SQLException e) {
			return null;//temp
		}

	}

}
