package ru.trailcom.rchs2nri.dao;

import ru.trailcom.rchs2nri.domain.License;

import java.util.List;

public interface LicenseDao {
	
	License insertLicense(License license);
	
	void insertLicenseBaseStation(Integer licenseId, Integer baseStationId);
	
	License getLicense(Integer idLicense);
	
	List getLicenseList();
	
}
