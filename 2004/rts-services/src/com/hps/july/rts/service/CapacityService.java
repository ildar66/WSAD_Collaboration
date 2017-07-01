package com.hps.july.rts.service;

import com.hps.april.common.Service;
import com.hps.april.common.CreateObjectException;
import com.hps.april.common.StoreObjectException;
import com.hps.april.common.RemoveObjectException;
import com.hps.july.rts.object.capacity.Capacity;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: ABaykov
 * Date: 26.04.2006
 * Time: 12:45:12
 * To change this template use File | Settings | File Templates.
 */
public interface CapacityService extends Service {

    String SERVICE_NAME = "july.rts.service.capacityService";

    public Collection findAllCapacities();

    public Capacity findCapacity(Integer ident);

    public void saveCapacity(Capacity capacity) throws CreateObjectException;

    public void updateCapacity(Capacity capacity) throws StoreObjectException;

    public void removeCapacity(Capacity capacity) throws RemoveObjectException;

    public void removeCapacity(Integer ident) throws RemoveObjectException;
}
