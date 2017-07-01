package com.hps.july.sync.kladr.model;

/**
 * Date: 11.04.2007
 * Time: 17:26:12
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class Position {
    private String addressSteet;
    private Integer storagePlace;
    private Integer adminRegionId;
    private String adminRegionKladrCode;


    public Integer getAdminRegionId() {
        return adminRegionId;
    }

    public void setAdminRegionId(Integer adminRegionId) {
        this.adminRegionId = adminRegionId;
    }

    public String getAdminRegionKladrCode() {
        return adminRegionKladrCode;
    }

    public void setAdminRegionKladrCode(String adminRegionKladrCode) {
        this.adminRegionKladrCode = adminRegionKladrCode;
    }

    public String getAddressSteet() {
        return addressSteet;
    }

    public void setAddressSteet(String addressSteet) {
        this.addressSteet = addressSteet;
    }

    public Integer getStoragePlace() {
        return storagePlace;
    }

    public void setStoragePlace(Integer storagePlace) {
        this.storagePlace = storagePlace;
    }
}
