package ru.trailcom.rchs2nri.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Decision {
	private Integer id;
	private String number;
	private Date date;
	private Date dateLicensePrognoses;
	
	private String numberRossvIn;
	private Date dateRossvIn;
	private String numberRossvOut;
	private Date dateRossvOut;
	private Declaration declaration;
	
	private List listBaseStation = new ArrayList();
	private List licenses = new ArrayList();
	
	
	public static char TYPE_NORMAL = 'R';
	public static char TYPE_TEMP = 'T';
	
	private char type = Decision.TYPE_NORMAL;
	
	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDateLicensePrognoses() {
		return dateLicensePrognoses;
	}
	public void setDateLicensePrognoses(Date dateLicensePrognoses) {
		this.dateLicensePrognoses = dateLicensePrognoses;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public Date getDateRossvIn() {
		return dateRossvIn;
	}

	public void setDateRossvIn(Date dateRossvIn) {
		this.dateRossvIn = dateRossvIn;
	}

	public Date getDateRossvOut() {
		return dateRossvOut;
	}

	public void setDateRossvOut(Date dateRossvOut) {
		this.dateRossvOut = dateRossvOut;
	}

	public String getNumberRossvIn() {
		return numberRossvIn;
	}

	public void setNumberRossvIn(String numberRossvIn) {
		this.numberRossvIn = numberRossvIn;
	}

	public String getNumberRossvOut() {
		return numberRossvOut;
	}

	public void setNumberRossvOut(String numberRossvOut) {
		this.numberRossvOut = numberRossvOut;
	}

	public Declaration getDeclaration() {
		return declaration;
	}

	public void setDeclaration(Declaration declaration) {
		this.declaration = declaration;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List getListBaseStation() {
		return listBaseStation;
	}

	public void setListBaseStation(List listBsId) {
		this.listBaseStation = listBsId;
	}

	public List getLicenses() {
		return licenses;
	}

	public void setLicenses(List licenses) {
		this.licenses = licenses;
	}

	
	
	

}
