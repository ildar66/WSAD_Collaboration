package com.hps.july.rts.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.hps.july.rts.core.hibernate.SessionFactoryProvider;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.core.process.DefaultProcess;
import com.hps.april.common.CreateObjectException;
import com.hps.april.common.RemoveObjectException;
import com.hps.april.common.StoreObjectException;
import com.hps.july.rts.RTSContextProvider;
import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.july.rts.auth.object.RTSGroup;
import com.hps.july.rts.auth.object.RTSRole;
import com.hps.july.rts.service.RTSOperatorService;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @author <a href="mailto:abaykov@beeline.ru">Albert Baykov</a>
 * Date: 13.12.2005
 * Time: 14:57:53
 */
public class RTSOperatorServiceImpl
        extends DefaultProcess implements RTSOperatorService {

    protected RTSAuthService authService;

    public Collection findPerformersByName(String name, Integer sortType, Integer asc) {
        Collection groups = findGroupsByName(name, null, null, RTSRole.PERFORMER, sortType, asc);
        return groups;
    }

    public Collection findFilialManagersByName(String name, Integer regionId, Integer sortType, Integer asc) {
        Collection groups = findGroupsByName(name, regionId, new Integer(1), RTSRole.REGION_MANAGER, sortType, asc);
        return groups;
    }

    public Collection findRegionManagersByName(String name, Integer regionId, Integer sortType, Integer asc) {
        Collection groups = findGroupsByName(name, new Integer(1), null, RTSRole.REGION_MANAGER, sortType, asc);
        return groups;
    }

    public Collection findInitiatorsByName(String name, Integer sortType, Integer asc) {
        Collection groups = findGroupsByName(name, null, null, RTSRole.INITIATOR, sortType, asc);
        return groups;
    }

    // TODO move to hibernate DAO
    public Collection findGroupsByName(String name, Integer regionId, Integer filialId, Integer roleId, Integer sortType, Integer asc) {
        ArrayList groups = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM rts_groups ");
            sql.append("WHERE roleid = ? ");
            if(name != null) sql.append(" AND UPPER(name) MATCHES UPPER(?) ");
            if(regionId != null && filialId == null) sql.append(" AND regionId IS NOT NULL AND filialId IS NULL");
            else if(regionId != null && filialId != null) sql.append(" AND regionId = ? AND filialId IS NOT NULL");
            pstmt = connection.prepareStatement(sql.toString());
            sql.append(" ORDER BY groupid ASC");
            //System.out.println("SQL["+sql.toString()+"]");
            setInteger(pstmt, 1, roleId);
            int count = 1;
            if(name != null) {
                StringBuffer search = new StringBuffer(name);
                int star = name.indexOf('*');
                //int percent = name.indexOf('%');
                if(star == -1) {
                    search.insert(0, '*');
                    search.append('*');
                }
                setString(pstmt, ++count, search.toString());
            }

            if(regionId != null && filialId != null) {
                setInteger(pstmt, ++count, regionId);
            }

            rs = pstmt.executeQuery();
            while(rs.next()) {
                RTSGroup group = new RTSGroup();
                group.setId(getInteger(rs, "groupid"));
                group.setRoleId(getInteger(rs, "roleId"));
                group.setRegionId(getInteger(rs, "regionId"));
                group.setFilialId(getInteger(rs, "filialId"));
                String groupName = getString(rs, "name");
                if(groupName != null) groupName = groupName.trim();
                group.setName(groupName);
                groups.add(group);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return groups;
    }

    // TODO move to hibernate DAO
    public Collection findGroupsPerson(Integer groupId, Integer sortType, Integer asc) {
        ArrayList persons = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT ");
            sql.append(" p.man, getNamePeople(p.man) name, p.firstname, p.lastname, p.middlename, w.email, w.mobilephone, o.operator operatorId ");
            sql.append(" FROM people p, rts_groups2people gp, outer(workers w), operators o ");
            sql.append(" WHERE ");
            sql.append(" gp.man = p.man AND p.man = w.man AND o.man = p.man AND gp.groupid = ? ");
            if(sortType != null && asc != null) {
                switch(sortType.intValue()) {
                    case 1 : {
                        sql.append(" ORDER BY p.lastname ");
                        sql.append((asc.intValue() == 1)?" ASC ":" DESC");
                        break;
                    }
                    case 2 : {
                        sql.append(" ORDER BY p.firstname ");
                        sql.append((asc.intValue() == 1)?" ASC ":" DESC");
                        break;
                    }
                    case 3 : {
                        sql.append(" ORDER BY p.middlename ");
                        sql.append((asc.intValue() == 1)?" ASC ":" DESC");
                        break;
                    }
                }
            }
            pstmt = connection.prepareStatement(sql.toString());
            setInteger(pstmt, 1, groupId);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Person person = new Person();
                person.setId(new Integer(rs.getInt("man")));
                String peopleName = getString(rs, "name");
                if(peopleName != null) peopleName = peopleName.trim();
                //person.setName(peopleName);
                String firstName = getString(rs, "firstName");
                if(firstName != null) firstName = firstName.trim();
                person.setFirstName(firstName);
                String lastName = getString(rs, "lastName");
                if(lastName != null) lastName = lastName.trim();
                person.setLastName(lastName);
                String middleName = getString(rs, "middleName");
                if(middleName != null) middleName = middleName.trim();
                person.setMiddleName(middleName);
                String email = getString(rs, "email");
                if(email != null) email = email.trim();
                person.setEmail(email);
                String phone = getString(rs, "mobilephone");
                if(phone != null) phone = phone.trim();
                person.setPhone(phone);
// dimitry: remove
//                person.setOperatorId(new Integer(rs.getInt("operatorId")));
                persons.add(person);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("---Code ["+e.getErrorCode()+"]");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
        return persons;
    }

    // TODO move to hibernate DAO 
    public void saveGroupsPerson(Integer groupId, Collection persons) throws StoreObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO rts_groups2people (groupid, man) VALUES (? , ?)");
            pstmt1 = connection.prepareStatement("DELETE FROM rts_groups2people WHERE groupid = ? ");
            pstmt = connection.prepareStatement(sql.toString());
            setInteger(pstmt1, 1, groupId);
            pstmt1.executeUpdate();
            for(Iterator iterator = persons.iterator(); iterator.hasNext();) {
                Person person = (Person) iterator.next();
                if(person != null && person.getId() != null) {
                    pstmt.clearParameters();
                    setInteger(pstmt, 1, groupId);
                    setInteger(pstmt, 2, person.getId());
                    pstmt.executeUpdate();
                }
            }
            connection.commit();
        } catch(Exception e) {
            try {
                connection.rollback();
            } catch (Exception e1) {}
            e.printStackTrace();
            throw new StoreObjectException(new Integer(-2), e);
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt1 != null) pstmt1.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
    }

    // TODO move to hibernate DAO
    public void saveRolesPerson(Integer roleId, Collection persons) throws StoreObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO rts_roles2people (roleid, man) VALUES (?, ?) ");
            pstmt1 = connection.prepareStatement("DELETE FROM rts_roles2people WHERE roleid = ? ");
            pstmt = connection.prepareStatement(sql.toString());
            setInteger(pstmt1, 1, roleId);
            pstmt1.executeUpdate();
            for(Iterator iterator = persons.iterator(); iterator.hasNext();) {
                Person person = (Person) iterator.next();
                if(person != null && person.getId() != null) {
                    pstmt.clearParameters();
                    setInteger(pstmt, 1, roleId);
                    setInteger(pstmt, 2, person.getId());
                    pstmt.executeUpdate();
                }
            }
            connection.commit();
        } catch(Exception e) {
            try {
                connection.rollback();
            } catch (Exception e1) {}
            e.printStackTrace();
            throw new StoreObjectException(new Integer(-2), e);
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt1 != null) pstmt1.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
    }

    // TODO move to hibernate DAO
    public Collection findPersonByRole(Integer roleId, Integer sortType, Integer asc) {
        ArrayList persons = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            StringBuffer sql = new StringBuffer();
            boolean isGroup = ((roleId.intValue() == 1) || (roleId.intValue() == 2) || roleId.intValue() == 3);
            if(!isGroup) {
                sql.append(" SELECT ");
                sql.append(" p.man, getNamePeople(p.man) name, p.firstname, p.lastname, p.middlename, w.email, w.mobilephone, o.operator operatorId ");
                sql.append(" FROM people p, rts_roles2people rp, outer(workers w), operators o ");
                sql.append(" WHERE ");
                sql.append(" rp.man = p.man AND p.man = w.man AND p.man = o.man AND rp.roleid = ? ");
            } else {
                sql.append(" SELECT ");
                sql.append(" p.man, getNamePeople(p.man) name, p.firstname, p.lastname, p.middlename, w.email, w.mobilephone, o.operator operatorId ");
                sql.append(" FROM rts_groups g, rts_groups2people gp, people p, outer(workers w), operators o ");
                sql.append(" WHERE ");
                sql.append(" p.man = w.man AND gp.man = p.man AND p.man = o.man AND g.groupid = gp.groupid AND g.roleid = ? ");
            }
            if(sortType != null && asc != null) {
                switch(sortType.intValue()) {
                    case 1 : {
                        sql.append(" ORDER BY p.lastname ");
                        sql.append((asc.intValue() == 1)?" ASC ":" DESC");
                        break;
                    }
                    case 2 : {
                        sql.append(" ORDER BY p.firstname ");
                        sql.append((asc.intValue() == 1)?" ASC ":" DESC");
                        break;
                    }
                    case 3 : {
                        sql.append(" ORDER BY p.middlename ");
                        sql.append((asc.intValue() == 1)?" ASC ":" DESC");
                        break;
                    }
                }
            }
            pstmt = connection.prepareStatement(sql.toString());
            setInteger(pstmt, 1, roleId);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Person person = new Person();
                person.setId(new Integer(rs.getInt("man")));
                String peopleName = getString(rs, "name");
                //if(peopleName != null) peopleName = peopleName.trim();
                //person.setName(peopleName);
                String firstName = getString(rs, "firstName");
                if(firstName != null) firstName = firstName.trim();
                person.setFirstName(firstName);
                String lastName = getString(rs, "lastName");
                if(lastName != null) lastName = lastName.trim();
                person.setLastName(lastName);
                String middleName = getString(rs, "middleName");
                if(middleName != null) middleName = middleName.trim();
                person.setMiddleName(middleName);
                String email = getString(rs, "email");
                if(email != null) email = email.trim();
                person.setEmail(email);
                String phone = getString(rs, "mobilephone");
                if(phone != null) phone = phone.trim();
                person.setPhone(phone);
// dimitry: remove 
//                person.setOperatorId(new Integer(rs.getInt("operatorId")));
                persons.add(person);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("---Code ["+e.getErrorCode()+"]");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
        return persons;
    }

    // TODO: move to hibernate DAO
    public Collection findPersonByRoleAndName(Integer roleId, String name, Integer sortType, Integer asc) {
        ArrayList persons = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            StringBuffer sql = new StringBuffer();
                boolean isGroup = ((roleId.intValue() == 1) || (roleId.intValue() == 2) || roleId.intValue() == 3);
                if(!isGroup) {
                    sql.append(" SELECT ");
                    sql.append(" p.man, getNamePeople(p.man) name, p.firstname, p.lastname, p.middlename, w.email, w.mobilephone, o.operator operatorId ");
                    sql.append(" FROM people p, rts_roles2people rp, outer(workers w), operators o ");
                    sql.append(" WHERE ");
                    sql.append(" rp.man = p.man AND p.man = w.man AND p.man = o.man AND rp.roleid = ? AND  UPPER(p.lastname) MATCHES UPPER(?) ");
                } else {
                    sql.append(" SELECT ");
                    sql.append(" p.man, getNamePeople(p.man) name, p.firstname, p.lastname, p.middlename, w.email, w.mobilephone, o.operator operatorId ");
                    sql.append(" FROM rts_groups g, rts_groups2people gp, people p, outer(workers w), operators o ");
                    sql.append(" WHERE ");
                    sql.append(" p.man = w.man AND gp.man = p.man AND p.man = o.man AND g.groupid = gp.groupid AND g.roleid = ?  AND UPPER(p.lastname) MATCHES UPPER(?) ");
                }
            if(sortType != null && asc != null) {
                switch(sortType.intValue()) {
                    case 1 : {
                        sql.append(" ORDER BY p.lastname ");
                        sql.append((asc.intValue() == 1)?" ASC ":" DESC");
                        break;
                    }
                    case 2 : {
                        sql.append(" ORDER BY p.firstname ");
                        sql.append((asc.intValue() == 1)?" ASC ":" DESC");
                        break;
                    }
                    case 3 : {
                        sql.append(" ORDER BY p.middlename ");
                        sql.append((asc.intValue() == 1)?" ASC ":" DESC");
                        break;
                    }
                }
            }
            pstmt = connection.prepareStatement(sql.toString());
            setInteger(pstmt, 1, roleId);
            if(!isValidString(name)) name = "*";
            else {
                StringBuffer temp = new StringBuffer(name);
                temp.append("*");
                name = temp.toString();
            }
            setString(pstmt, 2, name);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Person person = new Person();
                person.setId(new Integer(rs.getInt("man")));
                String peopleName = getString(rs, "name");
                if(peopleName != null) peopleName = peopleName.trim();
                //person.setName(peopleName);
                String firstName = getString(rs, "firstName");
                if(firstName != null) firstName = firstName.trim();
                person.setFirstName(firstName);
                String lastName = getString(rs, "lastName");
                if(lastName != null) lastName = lastName.trim();
                person.setLastName(lastName);
                String middleName = getString(rs, "middleName");
                if(middleName != null) middleName = middleName.trim();
                person.setMiddleName(middleName);
                String email = getString(rs, "email");
                if(email != null) email = email.trim();
                person.setEmail(email);
                String phone = getString(rs, "mobilephone");
                if(phone != null) phone = phone.trim();
                person.setPhone(phone);
// dimitry: remove
//                person.setOperatorId(new Integer(rs.getInt("operatorId")));
                persons.add(person);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("---Code ["+e.getErrorCode()+"]");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
        return persons;
    }

    // TODO: move to hibernate DAO
    public Collection findRTSPersonByName(String name, Integer sortType, Integer asc) {
        ArrayList persons = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT ");
            sql.append(" p.man, getNamePeople(p.man) name, p.firstname, p.lastname, p.middlename, w.email, w.mobilephone ");
            sql.append(" FROM people p, rts_roles2people rp, outer(workers w), operators o ");
            sql.append(" WHERE ");
            sql.append(" rp.man = p.man AND p.man = w.man AND p.man = o.man AND UPPER(p.lastname) MATCHES UPPER(?) ");
            sql.append(" UNION ");
            sql.append(" SELECT ");
            sql.append(" p.man, getNamePeople(p.man) name, p.firstname, p.lastname, p.middlename, w.email, w.mobilephone ");
            sql.append(" FROM rts_groups g, rts_groups2people gp, people p, outer(workers w), operators o ");
            sql.append(" WHERE ");
            sql.append(" p.man = w.man AND gp.man = p.man AND p.man = o.man AND g.groupid = gp.groupid AND UPPER(p.lastname) MATCHES UPPER(?) ");

/*
            if(sortType != null && asc != null) {
                switch(sortType.intValue()) {
                    case 1 : {
                        sql.append(" ORDER BY p.lastname ");
                        sql.append((asc.intValue() == 1)?" ASC ":" DESC");
                        break;
                    }
                    case 2 : {
                        sql.append(" ORDER BY p.firstname ");
                        sql.append((asc.intValue() == 1)?" ASC ":" DESC");
                        break;
                    }
                    case 3 : {
                        sql.append(" ORDER BY p.middlename ");
                        sql.append((asc.intValue() == 1)?" ASC ":" DESC");
                        break;
                    }
                }
            }
*/
            pstmt = connection.prepareStatement(sql.toString());
            if(!isValidString(name)) name = "*";
            else {
                StringBuffer temp = new StringBuffer(name);
                temp.append("*");
                name = temp.toString();
            }
            setString(pstmt, 1, name);
            setString(pstmt, 2, name);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Person person = new Person();
                person.setId(new Integer(rs.getInt("man")));
                String peopleName = getString(rs, "name");
                if(peopleName != null) peopleName = peopleName.trim();
                //person.setName(peopleName);
                String firstName = getString(rs, "firstName");
                if(firstName != null) firstName = firstName.trim();
                person.setFirstName(firstName);
                String lastName = getString(rs, "lastName");
                if(lastName != null) lastName = lastName.trim();
                person.setLastName(lastName);
                String middleName = getString(rs, "middleName");
                if(middleName != null) middleName = middleName.trim();
                person.setMiddleName(middleName);
                String email = getString(rs, "email");
                if(email != null) email = email.trim();
                person.setEmail(email);
                String phone = getString(rs, "mobilephone");
                if(phone != null) phone = phone.trim();
                person.setPhone(phone);
                persons.add(person);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("---Code ["+e.getErrorCode()+"]");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
        return persons;
    }

    // TODO move to hibernate DAO
    public RTSGroup findGroup(Integer ident) {
        Session session = SessionFactoryProvider.currentSession();
        RTSGroup group = (RTSGroup)session.createQuery("FROM RTSGroup WHERE id = ? ")
                .setInteger(0, ident.intValue()).uniqueResult();
        return group;
    }

    public Integer findMaxInitiatorCode() {
        Session session = SessionFactoryProvider.currentSession();
        List list = session.createQuery("SELECT max(initiatorCode) FROM RTSGroup WHERE roleId = 1 ").list();
        Integer result = null;
        for (int i = 0; i < list.size(); i++) {
            result = (Integer)list.get(i);
            break;
        }
        if(result == null) result = new Integer(0);
        return result;
    }

    // TODO move to hibernate DAO
    public void saveGroup(RTSGroup group) throws CreateObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            if(group.getRoleId().intValue() == 1) {
                group.setInitiatorCode(new Integer(findMaxInitiatorCode().intValue() + 1));
            }
            pstmt = connection.prepareStatement("INSERT INTO rts_groups (name, regionid, roleid, initiatorcode) VALUES (?, ? , ?, ?)");
            setString(pstmt, 1, group.getName());
            setInteger(pstmt, 2, group.getRegionId());
            setInteger(pstmt, 3, group.getRoleId());
            setInteger(pstmt, 4, group.getInitiatorCode());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new CreateObjectException("Cannot insert Group ["+group.getName()+"] ["+group.getRoleId()+"]", new Integer(-1));
        } catch (CreateObjectException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CreateObjectException(new Integer(-254), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CreateObjectException(new Integer(-255), e);
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
    }

    // TODO move to hibernate DAO
    public void updateGroup(RTSGroup group) throws StoreObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("UPDATE rts_groups SET name = ?, regionid = ?, roleid = ? WHERE groupid = ? ");
            setString(pstmt, 1, group.getName());
            setInteger(pstmt, 2, group.getRegionId());
            setInteger(pstmt, 3, group.getRoleId());
            setInteger(pstmt, 4, group.getId());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new StoreObjectException("Cannot update Group ["+group.getName()+"] ["+group.getRoleId()+"]", new Integer(-2));
        } catch (StoreObjectException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StoreObjectException(new Integer(-254), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new StoreObjectException(new Integer(-255), e);
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
    }

    // TODO move to hibernate DAO
    public void removeGroup(Integer ident) throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM rts_groups WHERE groupid = ?");
            setInteger(pstmt, 1, ident);
            int result = pstmt.executeUpdate();
            if(result != 1) throw new RemoveObjectException("Cannot delete Group ["+ident+"] ", new Integer(-3));
        } catch (RemoveObjectException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoveObjectException(new Integer(-254), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoveObjectException(new Integer(-255), e);
        } finally {
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
    }

    // TODO move to hibernate DAO
    public void removeGroupsPerson(Integer groupId, Integer peopleId) throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM rts_groups2people WHERE groupid = ? AND man = ?");
            setInteger(pstmt, 1, groupId);
            setInteger(pstmt, 2, peopleId);
            int result = pstmt.executeUpdate();
            if(result != 1) throw new RemoveObjectException("Cannot delete relation between poeple and group ["+groupId+"] ["+peopleId+"] ", new Integer(-3));
        } catch (RemoveObjectException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoveObjectException(new Integer(-254), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoveObjectException(new Integer(-255), e);
        } finally {
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
    }

    // TODO move to hibernate DAO
    public void removeRolesPerson(Integer roleId, Integer peopleId) throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM rts_roles2people WHERE roleid = ? AND man = ?");
            setInteger(pstmt, 1, roleId);
            setInteger(pstmt, 2, peopleId);
            int result = pstmt.executeUpdate();
            if(result != 1) throw new RemoveObjectException("Cannot delete relation between poeple and role ["+roleId+"] ["+peopleId+"] ", new Integer(-3));
        } catch (RemoveObjectException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoveObjectException(new Integer(-254), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoveObjectException(new Integer(-255), e);
        } finally {
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
    }

    // TODO move to hibernate DAO
    public Collection findPersonsByName(String name) {
        ArrayList persons = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT p.man, getNamePeople(p.man) name, p.firstname, p.lastname, p.middlename, w.email, w.mobilephone, o.operator operatorId ");
            sql.append(" FROM people p, outer(workers w), operators o ");
            sql.append(" WHERE ");
            sql.append(" UPPER(p.lastname) MATCHES UPPER(?) AND p.man = w.man AND o.man = p.man ");
            pstmt = connection.prepareStatement(sql.toString());
            if(isValidString(name)) {
                StringBuffer s = new StringBuffer(name);
                s.insert(0, '*');
                s.append('*');
                name = s.toString();
            } else name = "";
            setString(pstmt, 1, name);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Person person = new Person();
                person.setId(new Integer(rs.getInt("man")));
                String peopleName = getString(rs, "name");
                if(peopleName != null) peopleName = peopleName.trim();
                //person.setName(peopleName);
                String firstName = getString(rs, "firstName");
                if(firstName != null) firstName = firstName.trim();
                person.setFirstName(firstName);
                String lastName = getString(rs, "lastName");
                if(lastName != null) lastName = lastName.trim();
                person.setLastName(lastName);
                String middleName = getString(rs, "middleName");
                if(middleName != null) middleName = middleName.trim();
                person.setMiddleName(middleName);
                String email = getString(rs, "email");
                if(email != null) email = email.trim();
                person.setEmail(email);
                String phone = getString(rs, "mobilephone");
                if(phone != null) phone = phone.trim();
                person.setPhone(phone);
// dimitry: remove
//                person.setOperatorId(new Integer(rs.getInt("operatorId")));
                persons.add(person);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("---Code ["+e.getErrorCode()+"]");
            //throw new DeliveryListException("Error working to DataBase", new Integer(-254), e);
        } catch(Exception e) {
            e.printStackTrace();
            //throw new DeliveryListException("Unexpected error", new Integer(-255), e);
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
        return persons;
    }

    // TODO move to hibernate DAO
    public RTSRole findRTSRole(Integer ident) {
        Session session = SessionFactoryProvider.currentSession();
        RTSRole role = (RTSRole)session.createQuery("FROM RTSRole WHERE id = ?").setInteger(0, ident.intValue()).uniqueResult();
        return role;
    }

    // TODO move to hibernate DAO
    public Collection findRTSRoles() {
        Session session = SessionFactoryProvider.currentSession();
        Collection roles = session.createQuery("FROM RTSRole").list();
        return roles;
/*
        ArrayList roles = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM rts_roles ORDER BY roleid");
            pstmt = connection.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            while(rs.next()) {
                RTSRole role = new RTSRole();
                role.setId(new Integer(rs.getInt("roleid")));
                String roleName = getString(rs, "rolename");
                if(roleName != null) roleName = roleName.trim();
                role.setName(roleName);
                roles.add(role);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("---Code ["+e.getErrorCode()+"]");
            //throw new DeliveryListException("Error working to DataBase", new Integer(-254), e);
        } catch(Exception e) {
            e.printStackTrace();
            //throw new DeliveryListException("Unexpected error", new Integer(-255), e);
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
        return roles;
*/
    }

    public Person findPersonsById(Integer personId) {
        return getAuthService().getPerson(personId);

// :commented by dimitry     	
//        Connection connection = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Person person = null;
//        try {
//            connection = getConnection();
//            StringBuffer sql = new StringBuffer();
//            sql.append(" SELECT p.man, getNamePeople(p.man) name, p.firstname, p.lastname, p.middlename, w.email, w.mobilephone, o.operator operatorId ");
//            sql.append(" FROM people p, outer(workers w), operators o");
//            sql.append(" WHERE ");
//            sql.append(" p.man = (?) AND p.man = w.man AND o.man = p.man ");
//            pstmt = connection.prepareStatement(sql.toString());
//            setInteger(pstmt, 1, id);
//            rs = pstmt.executeQuery();
//            if(rs.next()) {
//                person = new Person();
//                person.setId(new Integer(rs.getInt("man")));
//                String peopleName = getString(rs, "name");
//                if(peopleName != null) peopleName = peopleName.trim();
//                person.setName(peopleName);
//                String firstName = getString(rs, "firstName");
//                if(firstName != null) firstName = firstName.trim();
//                person.setFirstName(firstName);
//                String lastName = getString(rs, "lastName");
//                if(lastName != null) lastName = lastName.trim();
//                person.setLastName(lastName);
//                String middleName = getString(rs, "middleName");
//                if(middleName != null) middleName = middleName.trim();
//                person.setMiddleName(middleName);
//                String email = getString(rs, "email");
//                if(email != null) email = email.trim();
//                person.setEmail(email);
//                String phone = getString(rs, "mobilephone");
//                if(phone != null) phone = phone.trim();
//                person.setPhone(phone);
//                person.setOperatorId(new Integer(rs.getInt("operatorId")));
//            }
//        } catch(SQLException e) {
//            e.printStackTrace();
//            System.out.println("---Code ["+e.getErrorCode()+"]");
//            //throw new DeliveryListException("Error working to DataBase", new Integer(-254), e);
//        } catch(Exception e) {
//            e.printStackTrace();
//            //throw new DeliveryListException("Unexpected error", new Integer(-255), e);
//        } finally {
//            try { if(rs != null) rs.close(); } catch(Exception e) {}
//            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
//            try { if(connection != null) connection.close(); } catch(Exception e) {}
//        }
//        return person;
    }


    // TODO move to hibernate DAO
    public Collection findGroupByPerson(Person person) {
        Collection groups = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT g.groupid, g.name, g.regionid, g.roleid, g.filialid, g.initiatorCode " +
                    " FROM rts_groups g, rts_groups2people g2p ");
            sql.append("WHERE g.groupid = g2p.groupid AND g2p.man = ? ");
            pstmt = connection.prepareStatement(sql.toString());
            Integer personId = person.getId();
            setInteger(pstmt, 1, personId);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                RTSGroup group = new RTSGroup();
                group.setId(getInteger(rs, "groupid"));
                group.setRegionId(getInteger(rs, "regionId"));
                group.setFilialId(getInteger(rs, "filialId"));
				group.setRoleId(getInteger(rs, "roleId"));
				group.setInitiatorCode(getInteger(rs, "initiatorCode"));
                String groupName = getString(rs, "name");
                if(groupName != null) groupName = groupName.trim();
                group.setName(groupName);
                groups.add(group);
            }
            if(!groups.isEmpty()) return groups;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return null;
    }

    public RTSAuthService getAuthService() {
        if (authService == null)
            authService = (RTSAuthService) RTSContextProvider.getBean(RTSAuthService.SERVICE_NAME);
        return authService;
    }

    public void setAuthService(RTSAuthService authService) {
        this.authService = authService;
    }

    public String findPersonByRoleAndGroup(RTSRole role, RTSGroup group) {
        StringBuffer str = new StringBuffer();
        String sql;
        if( group == null || group.getRegionId() == null || role == null) return null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            if(group.getFilialId() == null && group.getRegionId() != null) {
                sql = "select * from rts_groups2people where groupid in ( " +
                        "    select groupid from rts_groups where (filialid IS NOT NULL and " +
                        "        regionid = ?" +
                        " and " +
                        "        roleid = ? ) or groupid = ?" +
                        ")";
                pstmt = connection.prepareStatement(sql);
                setInteger(pstmt, 1, group.getRegionId());
                setInteger(pstmt, 2, role.getId());
                setInteger(pstmt, 3, group.getId());
            } else {
                sql = "select * from rts_groups2people where groupid in ( " +
                        "    select groupid from rts_groups where filialid IS NOT NULL and " +
                        "        groupid = ? " +
                        " and " +
                        "        roleid = ? " +
                        ")";
                pstmt = connection.prepareStatement(sql);
                setInteger(pstmt, 1, group.getId());
                setInteger(pstmt, 2, role.getId());
            }
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if(str.length() != 0) {
                    str.append(", ");
                }
                str.append(rs.getString("man"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }

        if (str.length()!=0)
            return str.toString();
        else return null;
    }


}
