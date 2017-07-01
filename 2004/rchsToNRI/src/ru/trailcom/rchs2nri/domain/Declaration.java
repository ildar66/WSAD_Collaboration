package ru.trailcom.rchs2nri.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Declaration {
	//requestId
	private Integer seqId;
	
	private Integer id;
	private Integer filialId;
	private Integer regionid;
	private String definition;
	private Integer diap;
	private String company;
	private String region;
	private Date projectDate;
	private String numberGRCCout;
	private Date dateGRCCout;
	private String numberGRCCin;
	private Date dateGRCCin;
	private Date dateDecisionPrognosis;
	
	private List baseStations = new ArrayList();
	//private List licensedBaseStations = new ArrayList();
	
	private List decisions = new ArrayList();
	
	
	//private List licensesTemp = new ArrayList();
	
	public static char TYPE_NORMAL = 'R';
	public static char TYPE_TEMP = 'T';
	
	//private char type = Decision.TYPE_NORMAL;
	
//	public char getType() {
//		return type;
//	}
//
//	public void setType(char type) {
//		this.type = type;
//	}
	
	public String toString(){
		return "Declaration["+id+", "+definition+", bsCount="+baseStations.size()+", decisions="+decisions.size()+"]";
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Date getDateDecisionPrognosis() {
		return dateDecisionPrognosis;
	}
	public void setDateDecisionPrognosis(Date dateDecisionPrognosis) {
		this.dateDecisionPrognosis = dateDecisionPrognosis;
	}
	public Date getDateGRCCin() {
		return dateGRCCin;
	}
	public void setDateGRCCin(Date dateGRCCin) {
		this.dateGRCCin = dateGRCCin;
	}
	public Date getDateGRCCout() {
		return dateGRCCout;
	}
	public void setDateGRCCout(Date dateGRCCout) {
		this.dateGRCCout = dateGRCCout;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public Integer getDiap() {
		return diap;
	}
	public void setDiap(Integer diap) {
		this.diap = diap;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumberGRCCin() {
		return numberGRCCin;
	}
	public void setNumberGRCCin(String numberGRCCin) {
		this.numberGRCCin = numberGRCCin;
	}
	public String getNumberGRCCout() {
		return numberGRCCout;
	}
	public void setNumberGRCCout(String numberGRCCout) {
		this.numberGRCCout = numberGRCCout;
	}
	public Date getProjectDate() {
		return projectDate;
	}
	public void setProjectDate(Date projectDate) {
		this.projectDate = projectDate;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

	public List getBaseStations() {
		return baseStations;
	}

	public void setBaseStations(List baseStations) {
		this.baseStations = baseStations;
	}

	public List getDecisions() {
		return decisions;
	}

	public void setDecisions(List decisions) {
		this.decisions = decisions;
	}

//	public List getLicenses() {
//		return licenses;
//	}
//
//	public void setLicenses(List licenses) {
//		this.licenses = licenses;
//	}

//	public List getLicensedBaseStations() {
//		return licensedBaseStations;
//	}
//
//	public void setLicensedBaseStations(List licensedBaseStations) {
//		this.licensedBaseStations = licensedBaseStations;
//	}

//	public List getLicensesTemp() {
//		return licensesTemp;
//	}
//
//	public void setLicensesTemp(List licensesTemp) {
//		this.licensesTemp = licensesTemp;
//	}

	public Integer getFilialId() {
		return filialId;
	}
//
	public void setFilialId(Integer filialId) {
		this.filialId = filialId;
	}

	public Integer getRegionid() {
		return regionid;
	}

	public void setRegionid(Integer regionid) {
		this.regionid = regionid;
	}

	public Integer getSeqId() {
		return seqId;
	}

	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}

	
	
	

}
