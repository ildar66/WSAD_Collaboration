package ru.trailcom.rchs2nri.dao;

import ru.trailcom.rchs2nri.domain.Declaration;

import java.util.List;

public interface DeclarationDao {

	Declaration insertDeclaration(Declaration declaration);
	
	void insertDeclarationBaseStation(Integer declarationId, Integer baseStationId);
	
	Declaration getDeclaration(Integer idDeclaration);
	
	List getDeclarationList();
	
	List getDeclarationFilialIdIsNullList();
	
	void update(Declaration declaration);
	
	void findFilialIdByRegionInReference(Declaration declaration);
	
	void clearPermits(List errors);

}
