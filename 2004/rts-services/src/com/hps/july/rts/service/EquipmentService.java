package com.hps.july.rts.service;

import com.hps.april.common.Service;
import com.hps.july.rts.SystemException;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 10.02.2006
 * Time: 21:11:30
 * @deprecated use com.hsp.july.storageplace.service.EquipmentService
 */
public interface EquipmentService extends Service {

    public String getFullEquipmentName(Integer equipmentId, Integer postionId) throws SystemException;

    public String getEquipmentName(Integer equipmentId) throws SystemException;

    public String getPositionAddress(Integer positionId) throws SystemException;

    public String getBeenet(Integer positionId) throws SystemException;
}
