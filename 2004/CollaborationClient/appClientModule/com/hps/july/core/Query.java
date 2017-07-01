package com.hps.july.core;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * ������ �� ������������� ������.
 * @author Shafigullin Ildar
 */
public class Query {
	private String reqType;
	private int queryId;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private int idApp;
	public final static String ALL = "ALL_";
	public final static String DEL = "DEL_";
	public final static String TASK = "TASK_";
	private boolean isDelQuery = false;
	private boolean isAllQuery = false;
	private boolean isTaskQuery = false;

	//��������� ������������
    private Map parameters = new HashMap();

	/// �����������
    /**
     * �������������� ���� reqType.
     * � �� � ������� join_db_querytype �������� �������(TASKNAME) ������ ������ � ��������� ���� ������
     * <UL>
     * <LI><b>ALL_</b>TASKNAME</LI>
     * <LI><b>DEL_</b>TASKNAME</LI>
     * <LI><b>TASK_</b>TASKNAME</LI>
     * </UL>
     * � ����������� �� ����� ���������� ������ ������ ��������
     * @see Collaboration
     * @param argReqType
     */
    public Query(String argReqType) {
		if (argReqType.indexOf(ALL) == 0) {
			isAllQuery = true;
			reqType = argReqType.substring(4);
		} else if (argReqType.indexOf(DEL) == 0) {
			isDelQuery = true;
			reqType = argReqType.substring(4);
		} else if (argReqType.indexOf(TASK) == 0) {
			isTaskQuery = true;
			reqType = argReqType.substring(5);
		} else {
			reqType = argReqType;
			System.err.println("�������� ��� Query reqType = " + reqType);
		}
	}

	public boolean isQueryType(String argQueryType) {
		return getReqType().equalsIgnoreCase(argQueryType);
	}

	public boolean isDelQuery() {
		return isDelQuery;
	}

	public boolean isAllQuery() {
		return isAllQuery;
	}

	public boolean isTaskQuery() {
		return isTaskQuery;
	}

	public String getReqType() {
		return reqType;
	}

	public void setQueryId(int argQueryId) {
		queryId = argQueryId;
	}

	public int getQueryId() {
		return queryId;
	}

	/// ���� ������ ��������� ��������� �������
	public void setStartDate(java.sql.Date argStartDate) {
		startDate = argStartDate;
	}

	/// ���� ������ ��������� ��������� �������
	public java.sql.Date getStartDate() {
		return startDate;
	}

	/// ���� ��������� ��������� ��������� �������
	public void setEndDate(java.sql.Date argEndDate) {
		endDate = argEndDate;
	}

	/// ���� ��������� ��������� ��������� �������
	public java.sql.Date getEndDate() {
		return endDate;
	}

	/**
	 * Returns the idApp.
	 * @return int
	 */
	public int getIdApp() {
		return idApp;
	}

	/**
	 * Sets the idApp.
	 * @param idApp The idApp to set
	 */
	public void setIdApp(int idApp) {
		this.idApp = idApp;
	}

	public String getParameter(String name) {
		if (this.parameters != null && name != null) {
			return (String) this.parameters.get(name);
		}
		return null;
	}

	protected void setParameters(Map param) {
		this.parameters = param;
	}

    /**
     * fullfill parameters map.
     * format of string is <code>key1=value1&key2=value2</code>
     * @param parameters
     */
    public void setParameters(String parametersString) {
        if (null == parametersString || "".equals(parametersString)) {
            return;
        }

        StringTokenizer ampTokenizer = new StringTokenizer(parametersString, "&");
        while(ampTokenizer.hasMoreTokens()) {
            String keyValue = ampTokenizer.nextToken();
            StringTokenizer equalTokenizer = new StringTokenizer(keyValue, "=");

            parameters.put(equalTokenizer.nextToken(), equalTokenizer.nextToken());
        }
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		//return super.toString();
		return "Query id="+ getQueryId() + "; idApp=" + getIdApp() + "; type=" + getReqType();
	}

}
