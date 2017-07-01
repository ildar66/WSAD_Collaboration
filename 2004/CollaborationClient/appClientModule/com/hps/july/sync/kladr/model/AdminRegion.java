package com.hps.july.sync.kladr.model;

/**
 * Date: 05.04.2007
 * Time: 12:09:11
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class AdminRegion {
    private Integer regionId;
    private String regionName;
    private String kladrCode;
    private Integer parentRegionId;

    public AdminRegion(Integer regionId, String regionName, String kladrCode, Integer parentRegionId) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.kladrCode = kladrCode;
        this.parentRegionId = parentRegionId;
    }

    public AdminRegion() {
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getKladrCode() {
        return kladrCode;
    }

    public void setKladrCode(String kladrCode) {
        this.kladrCode = kladrCode;
    }

    public Integer getParentRegionId() {
        return parentRegionId;
    }

    public void setParentRegionId(Integer parentRegionId) {
        this.parentRegionId = parentRegionId;
    }

    public String toString() {
        return "AdminRegion{" +
                "regionId=" + regionId +
                ", regionName='" + regionName + "'" +
                ", kladrCode='" + kladrCode + "'" +
                ", parentRegionId=" + parentRegionId +
                "}";
    }
}
