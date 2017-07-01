package ru.trailcom.rchs2nri.domain;

import java.math.BigDecimal;

public class BaseStation implements Comparable{
	
	private Integer idBsZaj;
	private Integer number;
	private Integer number5;
	
	private String name;
	private String address;
	private BigDecimal latitude;
	private BigDecimal longitude;

    private Integer idBsBd;
    private Integer bsBdNumber;

    private Integer storageplace;

    public String toString() {
        return "BaseStation{" +
                "idBsZaj=" + idBsZaj +
                ", number=" + number +
                ", number5=" + number5 +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", idBsBd=" + idBsBd +
                ", bsBdNumber=" + bsBdNumber +
                ", storageplace=" + storageplace +
                '}';
    }

    public int compareTo(Object o){
		BaseStation item = (BaseStation)o;
		if(this.getIdBsZaj().intValue()<item.getIdBsZaj().intValue()){
            return -1;
        } else {
			if(this.getIdBsZaj().intValue()>item.getIdBsZaj().intValue()){
                return 1;
            }else{
                return 0;
            }
		}
	}

	public boolean equals(Object obj){
		if(obj==null){
			return false;
		}
		if (!(obj instanceof BaseStation)) {
			return false;
		}
		BaseStation item = (BaseStation)obj;
		return this.getIdBsZaj().equals(item.getIdBsZaj());
	}
	
	public Integer getIdBsZaj() {
		return idBsZaj;
	}
	public void setIdBsZaj(Integer idBsZaj) {
		this.idBsZaj = idBsZaj;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getStorageplace() {
		return storageplace;
	}

	public void setStorageplace(Integer storageplace) {
		this.storageplace = storageplace;
	}

	public Integer getNumber5() {
		return number5;
	}

	public void setNumber5(Integer number6) {
		this.number5 = number6;
	}

    public Integer getIdBsBd() {
        return idBsBd;
    }

    public void setIdBsBd(Integer idBsBd) {
        this.idBsBd = idBsBd;
    }

    public Integer getBsBdNumber() {
        return bsBdNumber;
    }

    public void setBsBdNumber(Integer bsBdNumber) {
        this.bsBdNumber = bsBdNumber;
    }

    public void existsBs2Nri(Integer idBsBd) {
    }
}
