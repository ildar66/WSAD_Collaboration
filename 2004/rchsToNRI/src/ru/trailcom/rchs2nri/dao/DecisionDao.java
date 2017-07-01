package ru.trailcom.rchs2nri.dao;

import ru.trailcom.rchs2nri.domain.Decision;

import java.util.List;

public interface DecisionDao {
	
	Decision insertDecision(Decision decision);
	Decision getDecision(Integer idDecision);
	
	List getDecisionList();
	
	void joinLicense(Decision decision);
	
	void insertDecisionBaseStation(final Integer decisionId, final Integer baseStationId); 

}
