package com.hps.july.sync.model.valueobject;

import java.io.Serializable;

/**
 * @author Shafigullin Ildar
 * 
 * Места размещения антенн в базе данных Делибаша
 */
public class DbsAntennaPlaceVO implements Serializable {
	public static final int ID_DBC = 1;
	private Integer idDbs; //код место размещения антены
	public static final int NAME = 2;
	private String name; //наименование места размещения антены
	public static final int IS_USE_RECODE = 3;
	private String isUseRecode; //применять перекодировку
	public static final int ID_NRI = 4;
	private Integer idNri; //код места размещения антен в NRI

	/**
	 * Constructor for DbsAntennaPlaceVO.
	 */
	public DbsAntennaPlaceVO(Integer idDbs, String name, String isUseRecode, Integer idNri) {
		this.idDbs = idDbs;
		this.name = name;
		this.isUseRecode = isUseRecode;
		this.idNri = idNri;
	}

	/**
	 * Returns the idDbs.
	 * @return Integer
	 */
	public Integer getIdDbs() {
		return idDbs;
	}

	/**
	 * Returns the idNri.
	 * @return Integer
	 */
	public Integer getIdNri() {
		return idNri;
	}

	/**
	 * Returns the isUseRecode.
	 * @return String
	 */
	public String getIsUseRecode() {
		return isUseRecode;
	}

	/**
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the idDbs.
	 * @param idDbs The idDbs to set
	 */
	public void setIdDbs(Integer idDbs) {
		this.idDbs = idDbs;
	}

	/**
	 * Sets the idNri.
	 * @param idNri The idNri to set
	 */
	public void setIdNri(Integer idNri) {
		this.idNri = idNri;
	}

	/**
	 * Sets the isUseRecode.
	 * @param isUseRecode The isUseRecode to set
	 */
	public void setIsUseRecode(String isUseRecode) {
		this.isUseRecode = isUseRecode;
	}

	/**
	 * Sets the name.
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "DbsAntennaPlaceVO: id=" + getIdDbs() + "; name=" + getName();
	}
}
