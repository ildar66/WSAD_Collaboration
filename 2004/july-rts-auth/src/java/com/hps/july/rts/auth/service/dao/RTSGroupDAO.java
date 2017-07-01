package com.hps.july.rts.auth.service.dao;

import com.hps.april.auth.object.Person;
import com.hps.april.common.FinderException;

import java.util.Collection;

public interface RTSGroupDAO {

    public Collection findGroupByPerson(final Person person) throws FinderException;

}
