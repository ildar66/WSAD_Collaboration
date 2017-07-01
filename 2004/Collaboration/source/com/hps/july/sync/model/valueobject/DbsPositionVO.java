package com.hps.july.sync.model.valueobject;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Shafigullin Ildar
 * 
 * Данные о позиции из базы Делибаша
 */
public class DbsPositionVO implements Serializable {
	public static final int ID_RECORD = 1;
	private Integer idRecord; //код записи
	public static final int DAMPS_ID = 2; 
	private Integer dampsID; //номер DAMPS
	public static final int GSM_ID = 3;
	private Integer gsmID; //номер GSM
	public static final int WLAN_ID = 4;
	private Integer wlanID; //номер WLAN
	public static final int NAME = 5;
	private String name; //Наименование
	public static final int NAME2 = 6;
	private String name2; //Наименование2
	public static final int APPARAT_TYPE = 7;
	private String apparatType; //Тип помещения аппаратной БС
	public static final int CONTAINER_TYPE = 8;
	private String containerType; //Изготовитель и марка контейнера
	public static final int PLACE_TYPE = 9;
	private String placeType; //Тип помещения тип контейнера
	public static final int APPARAT_PLACE = 10;
	private String apparatPlace; //Место размещения аппаратной БС
	public static final int OPORA_PLACE = 11;
	private String oporaPlace; //Тип опоры
	public static final int IS_OUR_OPORA = 12;
	private String isOurOpora; //Признак существующнго высотного сооружения
	public static final int OPORA_TYPE = 13;
	private String oporaType; //Производитель опоры
	public static final int ANTENNA_PLACE = 14;
	private String antennaPlace; //Место размещения опоры
	public static final int HEIGHT_OPORA = 15;
	private String heightOpora; //Высота опоры
	public static final int FIO_OTV_EXPL = 16;
	private String fioOtvExpl; //Фамилия ответсвенного за эксплуатацию позиции
	public static final int TAB_NUM_OTV_EXPL = 17;
	private String tabNumOtvExpl; //Табельный номер ответственного за эксплуатацию позиции
	public static final int STATE_BS = 18;
	private String stateBS; //Признак находится ли в эфире БС
	public static final int DATE_DERRICK = 19;
	private Date dateDerrick; //Дата демонтажа
	public static final int DATE_ON_SITE_REVIEW = 20;
	private Date dateOnSiteReview; //Дата выезда на осмотр позиции
	public static final int LAST_UPD_MARSH_KARTA = 21;
	private Date lastUpdMarshKarta; //Дата последнего изменения марш карты
	public static final int LAST_UPD_LIST_PROHOD = 22;
	private Date lastUpdListProhod; //Дата последнего изменения списка прохода
	public static final int LAST_UPD_POSITION = 23;
	private Date lastUpdPosition; //Дата последнего изменения данных по позиции
	public static final int FLAG_WORK_NRI = 24;
	private String flagWorkNri; //Признак обработки в системе NRI
	
	/**
	 * Constructor for DbsPositionVO.
	 */
	public DbsPositionVO(Integer idRecord) {
		this.idRecord = idRecord;
	}

	/**
	 * Returns the antennaPlace.
	 * @return String
	 */
	public String getAntennaPlace() {
		return antennaPlace;
	}

	/**
	 * Returns the apparatPlace.
	 * @return String
	 */
	public String getApparatPlace() {
		return apparatPlace;
	}

	/**
	 * Returns the apparatType.
	 * @return String
	 */
	public String getApparatType() {
		return apparatType;
	}

	/**
	 * Returns the containerType.
	 * @return String
	 */
	public String getContainerType() {
		return containerType;
	}

	/**
	 * Returns the dampsID.
	 * @return Integer
	 */
	public Integer getDampsID() {
		return dampsID;
	}

	/**
	 * Returns the dateDerrick.
	 * @return Date
	 */
	public Date getDateDerrick() {
		return dateDerrick;
	}

	/**
	 * Returns the dateOnSiteReview.
	 * @return Date
	 */
	public Date getDateOnSiteReview() {
		return dateOnSiteReview;
	}

	/**
	 * Returns the fioOtvExpl.
	 * @return String
	 */
	public String getFioOtvExpl() {
		return fioOtvExpl;
	}

	/**
	 * Returns the flagWorkNri.
	 * @return String
	 */
	public String getFlagWorkNri() {
		return flagWorkNri;
	}

	/**
	 * Returns the gsmID.
	 * @return Integer
	 */
	public Integer getGsmID() {
		return gsmID;
	}

	/**
	 * Returns the heightOpora.
	 * @return String
	 */
	public String getHeightOpora() {
		return heightOpora;
	}

	/**
	 * Returns the idRecord.
	 * @return Integer
	 */
	public Integer getIdRecord() {
		return idRecord;
	}

	/**
	 * Returns the isOurOpora.
	 * @return String
	 */
	public String getIsOurOpora() {
		return isOurOpora;
	}

	/**
	 * Returns the lastUpdListProhod.
	 * @return Date
	 */
	public Date getLastUpdListProhod() {
		return lastUpdListProhod;
	}

	/**
	 * Returns the lastUpdMarshKarta.
	 * @return Date
	 */
	public Date getLastUpdMarshKarta() {
		return lastUpdMarshKarta;
	}

	/**
	 * Returns the lastUpdPosition.
	 * @return Date
	 */
	public Date getLastUpdPosition() {
		return lastUpdPosition;
	}

	/**
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the name2.
	 * @return String
	 */
	public String getName2() {
		return name2;
	}

	/**
	 * Returns the oporaPlace.
	 * @return String
	 */
	public String getOporaPlace() {
		return oporaPlace;
	}

	/**
	 * Returns the oporaType.
	 * @return String
	 */
	public String getOporaType() {
		return oporaType;
	}

	/**
	 * Returns the placeType.
	 * @return String
	 */
	public String getPlaceType() {
		return placeType;
	}

	/**
	 * Returns the stateBS.
	 * @return String
	 */
	public String getStateBS() {
		return stateBS;
	}

	/**
	 * Returns the tabNumOtvExpl.
	 * @return String
	 */
	public String getTabNumOtvExpl() {
		return tabNumOtvExpl;
	}

	/**
	 * Returns the wlanID.
	 * @return Integer
	 */
	public Integer getWlanID() {
		return wlanID;
	}

	/**
	 * Sets the antennaPlace.
	 * @param antennaPlace The antennaPlace to set
	 */
	public void setAntennaPlace(String antennaPlace) {
		this.antennaPlace = antennaPlace;
	}

	/**
	 * Sets the apparatPlace.
	 * @param apparatPlace The apparatPlace to set
	 */
	public void setApparatPlace(String apparatPlace) {
		this.apparatPlace = apparatPlace;
	}

	/**
	 * Sets the apparatType.
	 * @param apparatType The apparatType to set
	 */
	public void setApparatType(String apparatType) {
		this.apparatType = apparatType;
	}

	/**
	 * Sets the containerType.
	 * @param containerType The containerType to set
	 */
	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	/**
	 * Sets the dampsID.
	 * @param dampsID The dampsID to set
	 */
	public void setDampsID(Integer dampsID) {
		this.dampsID = dampsID;
	}

	/**
	 * Sets the dateDerrick.
	 * @param dateDerrick The dateDerrick to set
	 */
	public void setDateDerrick(Date dateDerrick) {
		this.dateDerrick = dateDerrick;
	}

	/**
	 * Sets the dateOnSiteReview.
	 * @param dateOnSiteReview The dateOnSiteReview to set
	 */
	public void setDateOnSiteReview(Date dateOnSiteReview) {
		this.dateOnSiteReview = dateOnSiteReview;
	}

	/**
	 * Sets the fioOtvExpl.
	 * @param fioOtvExpl The fioOtvExpl to set
	 */
	public void setFioOtvExpl(String fioOtvExpl) {
		this.fioOtvExpl = fioOtvExpl;
	}

	/**
	 * Sets the flagWorkNri.
	 * @param flagWorkNri The flagWorkNri to set
	 */
	public void setFlagWorkNri(String flagWorkNri) {
		this.flagWorkNri = flagWorkNri;
	}

	/**
	 * Sets the gsmID.
	 * @param gsmID The gsmID to set
	 */
	public void setGsmID(Integer gsmID) {
		this.gsmID = gsmID;
	}

	/**
	 * Sets the heightOpora.
	 * @param heightOpora The heightOpora to set
	 */
	public void setHeightOpora(String heightOpora) {
		this.heightOpora = heightOpora;
	}

	/**
	 * Sets the idRecord.
	 * @param idRecord The idRecord to set
	 */
	public void setIdRecord(Integer idRecord) {
		this.idRecord = idRecord;
	}

	/**
	 * Sets the isOurOpora.
	 * @param isOurOpora The isOurOpora to set
	 */
	public void setIsOurOpora(String isOurOpora) {
		this.isOurOpora = isOurOpora;
	}

	/**
	 * Sets the lastUpdListProhod.
	 * @param lastUpdListProhod The lastUpdListProhod to set
	 */
	public void setLastUpdListProhod(Date lastUpdListProhod) {
		this.lastUpdListProhod = lastUpdListProhod;
	}

	/**
	 * Sets the lastUpdMarshKarta.
	 * @param lastUpdMarshKarta The lastUpdMarshKarta to set
	 */
	public void setLastUpdMarshKarta(Date lastUpdMarshKarta) {
		this.lastUpdMarshKarta = lastUpdMarshKarta;
	}

	/**
	 * Sets the lastUpdPosition.
	 * @param lastUpdPosition The lastUpdPosition to set
	 */
	public void setLastUpdPosition(Date lastUpdPosition) {
		this.lastUpdPosition = lastUpdPosition;
	}

	/**
	 * Sets the name.
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the name2.
	 * @param name2 The name2 to set
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}

	/**
	 * Sets the oporaPlace.
	 * @param oporaPlace The oporaPlace to set
	 */
	public void setOporaPlace(String oporaPlace) {
		this.oporaPlace = oporaPlace;
	}

	/**
	 * Sets the oporaType.
	 * @param oporaType The oporaType to set
	 */
	public void setOporaType(String oporaType) {
		this.oporaType = oporaType;
	}

	/**
	 * Sets the placeType.
	 * @param placeType The placeType to set
	 */
	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	/**
	 * Sets the stateBS.
	 * @param stateBS The stateBS to set
	 */
	public void setStateBS(String stateBS) {
		this.stateBS = stateBS;
	}

	/**
	 * Sets the tabNumOtvExpl.
	 * @param tabNumOtvExpl The tabNumOtvExpl to set
	 */
	public void setTabNumOtvExpl(String tabNumOtvExpl) {
		this.tabNumOtvExpl = tabNumOtvExpl;
	}

	/**
	 * Sets the wlanID.
	 * @param wlanID The wlanID to set
	 */
	public void setWlanID(Integer wlanID) {
		this.wlanID = wlanID;
	}
	
	public String toString() {
		return "DbsPositionsVO: id=" + getIdRecord() + "; name=" + getName();
	}	

}
