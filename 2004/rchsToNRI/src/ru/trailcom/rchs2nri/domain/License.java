package ru.trailcom.rchs2nri.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class License {
	
	public static char TYPE_NORMAL = 'R';
    public static char TYPE_TEMP = 'T';
	
	private Integer id;
	private String number;
	private Date receiveDate;
	private Date realiseDate;
	private Date expireDate;
	private Boolean isTemporary = new Boolean(false);
	private char type = TYPE_NORMAL;
	
	private Decision decision;
	
	private List licensedBaseStations = new ArrayList();
	
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getRealiseDate() {
		return realiseDate;
	}
	public void setRealiseDate(Date realiseDate) {
		this.realiseDate = realiseDate;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public Decision getDecision() {
		return decision;
	}
	public void setDecision(Decision decision) {
		this.decision = decision;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List getLicensedBaseStations() {
		return licensedBaseStations;
	}
	public void setLicensedBaseStations(List licensedBaseStations) {
		this.licensedBaseStations = licensedBaseStations;
	}
	public Boolean getIsTemporary() {
		return isTemporary;
	}
	public void setIsTemporary(Boolean isTemporary) {
		this.isTemporary = isTemporary;
	}
}
