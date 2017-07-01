package com.hps.july.rts.service;

import com.hps.april.common.Service;
import com.hps.july.rts.object.counteragent.CounterAgent;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: ABaykov
 * Date: 11.05.2006
 * Time: 16:58:01
 * To change this template use File | Settings | File Templates.
 */
public interface CounterAgentService extends Service {

    String SERVICE_NAME = "july.rts.service.counterAgentService";

    public Collection findAllCounterAgents();

	public CounterAgent findCounterAgent(Integer ident);

	public CounterAgent findCounterAgent(CounterAgent counterAgent);

// Надо будет доделать потом! Пока перед запуском в тестовую эксплуатацию решено время не тратить!!! А. Байков 
//    public void saveCounterAgent(CounterAgent counterAgent) throws CreateObjectException;

//    public void updateCounterAgent(CounterAgent counterAgent) throws StoreObjectException;

//    public void removeCounterAgent(CounterAgent counterAgent) throws RemoveObjectException;

//    public void removeCounterAgent(Integer ident) throws RemoveObjectException;

}
