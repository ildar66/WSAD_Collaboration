package com.hps.july.rts.auth.service.dao;

import com.hps.july.rts.auth.object.RTSRole;
import com.hps.april.common.FinderException;
import com.hps.april.auth.object.Person;

import java.util.Collection;

public interface RTSRoleDAO {

    public RTSRole load(String roleName) throws FinderException;

    public RTSRole load(Integer roleId) throws FinderException;

    public RTSRole loadByAlias(String roleAlias) throws FinderException;

    public Collection findRoleByPerson(final Person person) throws FinderException;
}
