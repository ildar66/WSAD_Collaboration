package ru.trailcom.rchs2nri.dao;

import ru.trailcom.rchs2nri.domain.BaseStation;
import ru.trailcom.rchs2nri.domain.Declaration;

import java.util.List;

public interface BaseStationDao {
	
	BaseStation insertBaseStation(BaseStation baseStation);
	
	BaseStation getBaseStation(Integer id);
	
	List getBaseStationList();
	
	void update(BaseStation baseStation);
	
	List getBaseStationStorageplaceIsNotNullList();
	
	List getBaseStationByDeclarationStorageplaceIsNullList(Declaration declaration);
	
	List findStorageplaceByNumberAndFilialIdInNRI(Integer filialId, Integer baseStationNumber, Integer diap);
	
	Declaration getLastDeclaration(BaseStation baseStation);
	
	void makeActualPerimt(List baseStations, List errors);
	
	void saveInBs2Nri(BaseStation baseStation, Integer regionid, Integer nriCount, Integer diap);
	
	List findStorageplaceByNumber5AndFilialIdInNRI(final Integer filialId, final Integer baseStationNumber, Integer diap);

   boolean existsBs2Nri(final Integer idBsBd);

   void updateStatictics();
}

