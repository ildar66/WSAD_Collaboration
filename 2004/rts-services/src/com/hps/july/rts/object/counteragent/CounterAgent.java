package com.hps.july.rts.object.counteragent;

import java.util.Collection;

import com.hps.april.common.object.ExtensibleObject;

/**
 * RTS ("Заявки на расширение региональной ТС")
 *
 * @author ABaykov
 * Created on 11.05.2006
 */
public class CounterAgent extends ExtensibleObject {

    private Collection counterAgents;
    private String shortName;
    private String contactName;
    private String renter;


    public CounterAgent() {
        setId(null);
        setName(null);
    }

    public CounterAgent(Integer id, String name) {
        setId(id);
        setName(name);
    }

    public Collection getCounterAgents() {
        return counterAgents;
    }

    public void setCounterAgents(Collection collection) {
        counterAgents = collection;
    }

    public String toString(){
        return getName();
    }

    /**
     * @return
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param string
     */
    public void setShortName(String string) {
        shortName = string;
    }


    /**
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @return
     */
    public String getRenter() {
        return renter;
    }

    /**
     * @param string
     */
    public void setContactName(String string) {
        contactName = string;
    }

    /**
     * @param string
     */
    public void setRenter(String string) {
        renter = string;
    }

}
