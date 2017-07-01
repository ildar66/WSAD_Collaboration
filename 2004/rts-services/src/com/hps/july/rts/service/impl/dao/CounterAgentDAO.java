package com.hps.july.rts.service.impl.dao;

import java.util.Collection;

import com.hps.april.auth.object.AuthInfo;
import com.hps.july.rts.object.counteragent.CounterAgent;

/**
 * RTS ("������ �� ���������� ������������ ��")
 * �� ����������!!!
 * ���� ����� �������� ����� - ������ ����� �������� � �������� ������������ ������ ����� ����� �� ������!!!
 *
 * @author ABaykov
 * Created on 12.05.2006
 */
public interface CounterAgentDAO {

	public Collection findCounterAgentList(AuthInfo authInfo);

	public void saveCounterAgent(CounterAgent counterAgent);

	public void removeCounterAgent(CounterAgent counterAgent);

	public void removeCounterAgentList(Collection counterAgentList);

}
