package ru.trailcom.rchs2nri.sax;

import ru.trailcom.rchs2nri.domain.BaseStation;

import java.util.Arrays;
import java.util.List;

public class BaseStationList {
	/**
	 * Добавляет бс в список subListBs, если базовая станция есть в основном списке - listBaseStation
	 * @param id
	 * @param listBaseStation
	 * @param subListBs
	 */
	public static void addBaseStation(String id, List listBaseStation, List subListBs){
		BaseStation aBaseStation = new BaseStation();
        aBaseStation.setIdBsZaj(new Integer(id));
        Object[] arr = listBaseStation.toArray();
        int idx = Arrays.binarySearch(arr, aBaseStation);
        if(idx > -1){
        	aBaseStation = (BaseStation)arr[idx];
        	subListBs.add(aBaseStation);
        }
		
	}

}
