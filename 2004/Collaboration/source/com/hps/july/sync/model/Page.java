package com.hps.july.sync.model;

import java.util.*;
import java.io.Serializable;

/**
 * Represents a page of results (for page-by-page iteration).
 */
public class Page implements Serializable{
	// XXX
	/*
	  public static final Page EMPTY_PAGE =
	      new Page(Collections.EMPTY_LIST, 0, 0);
    */
	public static final Page EMPTY_PAGE = new Page(Collections.EMPTY_LIST, 0, false);

	List objects;
	List headers;
	int start;
	// XXX
	// int containingListSize;
	boolean hasNext;

	public Page(List headers, List l, int s, boolean hasNext) {
		this.headers = new ArrayList(headers);
		objects = new ArrayList(l);
		start = s;
		// XXX
		//containingListSize = cls;
		this.hasNext = hasNext;
	}
	// XXX
	/*
	  public Page(List l, int s, int cls) {
	  */
	public Page(List l, int s, boolean hasNext) {
		objects = new ArrayList(l);
		start = s;
		// XXX
		//containingListSize = cls;
		this.hasNext = hasNext;
	}

	public List getList() {
		return objects;
	}

	public boolean isNextPageAvailable() {
		// XXX
		/*
		  return (start + objects.size()) < containingListSize;
		  */
		return hasNext;
	}

	public boolean isPreviousPageAvailable() {
		return start > 0;
	}

	public int getStartOfNextPage() {
		return start + objects.size();
	}

	public int getStartOfPreviousPage() {
		return Math.max(start - objects.size(), 0);
	}

	public int getSize() {
		return objects.size();
	}

	/**
	 * @return
	 */
	public List getHeaders() {
		return headers;
	}

}
