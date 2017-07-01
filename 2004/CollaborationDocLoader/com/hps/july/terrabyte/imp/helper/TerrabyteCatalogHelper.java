package com.hps.july.terrabyte.imp.helper;

import com.hps.framework.exception.BaseException;
import com.hps.july.terrabyte.imp.AppLog;

import java.sql.*;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 17:51:11
 */
public class TerrabyteCatalogHelper extends DatabaseHelper {

    public static Node findCatalogForPosition(Connection connection, Integer objectId) {
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {
            pstmt1 = connection.prepareStatement(" SELECT catalogid FROM tb_pos2catalog WHERE positionid = ?");
            setInteger(pstmt1, 1, objectId);
            rs = pstmt1.executeQuery();
            if(rs.next()) {
                Integer catalogId = getInteger(rs, "catalogid");
                Node node = findNode(connection, catalogId);
                return node;
            }
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
            //AppLog.log(e);
            
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt1 != null) pstmt1.close(); } catch(Exception e) {}
        }
        return null;
    }

    public static Node findNode(Connection connection, Integer ident) {
        Node root = null;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement("SELECT * FROM tb_catalog c WHERE c.id = ?");
            if(ident != null) pstmt.setInt(1, ident.intValue());
            else pstmt.setNull(1, Types.INTEGER);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                root = new Node();
                root.setIdent(getInteger(rs, "id"));
                String name = getString(rs, "name");
                if(name != null) name = name.trim();
                root.setName(name);
                root.setDescription(getString(rs, "descr"));
                root.setLevel(getInteger(rs, "level"));
                root.setN1(getInteger(rs, "n1"));
                root.setN2(getInteger(rs, "n2"));
                root.setN3(getInteger(rs, "n3"));
                root.setN4(getInteger(rs, "n4"));
                root.setN5(getInteger(rs, "n5"));
                root.setN6(getInteger(rs, "n6"));
                root.setN7(getInteger(rs, "n7"));
                root.setN8(getInteger(rs, "n8"));
                root.setN9(getInteger(rs, "n9"));
                root.setN10(getInteger(rs, "n10"));
                root.setN11(getInteger(rs, "n11"));
                root.setN12(getInteger(rs, "n12"));
                root.setN13(getInteger(rs, "n13"));
                root.setN14(getInteger(rs, "n14"));
                root.setN15(getInteger(rs, "n15"));
                root.setN16(getInteger(rs, "n16"));
                root.setParentId(getInteger(rs, "parentid"));
                root.setCanDeleted(getInteger(rs, "candeleted"));
            }
            if(rs != null) rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }

        return root;
    }

    public static Node findChildNodeByName(Connection connection, Integer nodeId, String name) {
        Node node = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtLevel = null;
        ResultSet rs = null;
        try {
            //получить уровень узла дерева
            String sqlLevel = "SELECT level FROM tb_catalog WHERE id = ?";
            pstmtLevel = connection.prepareStatement(sqlLevel);
            Integer level = null;
            setInteger(pstmtLevel, 1, nodeId);
            rs = pstmtLevel.executeQuery();
            if(rs.next()) {
                level = getInteger(rs, "level");
            }
            if(rs != null) rs.close();
            pstmt = connection.prepareStatement("SELECT * FROM tb_catalog WHERE n" + level + " = ? AND name = ?");
            setInteger(pstmt, 1, nodeId);
            setString(pstmt, 2, name);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                node = new Node();
                node.setIdent(getInteger(rs, "id"));
                node.setName(getString(rs, "name"));
                node.setDescription(getString(rs, "descr"));
                node.setLevel(getInteger(rs, "level"));
                node.setN1(getInteger(rs, "n1"));
                node.setN2(getInteger(rs, "n2"));
                node.setN3(getInteger(rs, "n3"));
                node.setN4(getInteger(rs, "n4"));
                node.setN5(getInteger(rs, "n5"));
                node.setN6(getInteger(rs, "n6"));
                node.setN7(getInteger(rs, "n7"));
                node.setN8(getInteger(rs, "n8"));
                node.setN9(getInteger(rs, "n9"));
                node.setN10(getInteger(rs, "n10"));
                node.setN11(getInteger(rs, "n11"));
                node.setN12(getInteger(rs, "n12"));
                node.setN13(getInteger(rs, "n13"));
                node.setN14(getInteger(rs, "n14"));
                node.setN15(getInteger(rs, "n15"));
                node.setN16(getInteger(rs, "n16"));
            }
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
        } catch(Exception e) {
            e.printStackTrace();
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            //throw new BaseException("Cannot find node [" + nodeId + "], cause : " + e.getMessage());
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmtLevel != null) pstmtLevel.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
        return node;
    }

    public static Node insertNode(Connection connection, Integer parentId, String name, String descr, boolean canDelete) throws BaseException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement("SELECT * FROM tb_catalog WHERE id = ?");
            setInteger(pstmt, 1, parentId);
            rs = pstmt.executeQuery();
            Node parentNode = null;
            if(rs.next()) {
                parentNode = new Node();
                parentNode.setIdent(getInteger(rs, "id"));
                parentNode.setName(getString(rs, "name"));
                parentNode.setDescription(getString(rs, "descr"));
                parentNode.setLevel(getInteger(rs, "level"));
                parentNode.setN1(getInteger(rs, "n1"));
                parentNode.setN2(getInteger(rs, "n2"));
                parentNode.setN3(getInteger(rs, "n3"));
                parentNode.setN4(getInteger(rs, "n4"));
                parentNode.setN5(getInteger(rs, "n5"));
                parentNode.setN6(getInteger(rs, "n6"));
                parentNode.setN7(getInteger(rs, "n7"));
                parentNode.setN8(getInteger(rs, "n8"));
                parentNode.setN9(getInteger(rs, "n9"));
                parentNode.setN10(getInteger(rs, "n10"));
                parentNode.setN11(getInteger(rs, "n11"));
                parentNode.setN12(getInteger(rs, "n12"));
                parentNode.setN13(getInteger(rs, "n13"));
                parentNode.setN14(getInteger(rs, "n14"));
                parentNode.setN15(getInteger(rs, "n15"));
                parentNode.setN16(getInteger(rs, "n16"));
            }
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            Integer serial = getNextKey(connection, "tables.tb_catalog");

            Node node = null;
            if(parentNode != null) {
                node = (Node)parentNode.clone();
                node.setParentId(parentId);
                switch(node.getLevel().intValue()) {
                    case 1: {
                        node.setN2(serial);
                        break;
                    }
                    case 2: {
                        node.setN3(serial);
                        break;
                    }
                    case 3: {
                        node.setN4(serial);
                        break;
                    }
                    case 4: {
                        node.setN5(serial);
                        break;
                    }
                    case 5: {
                        node.setN6(serial);
                        break;
                    }
                    case 6: {
                        node.setN7(serial);
                        break;
                    }
                    case 7: {
                        node.setN8(serial);
                        break;
                    }
                    case 8: {
                        node.setN9(serial);
                        break;
                    }
                    case 9: {
                        node.setN10(serial);
                        break;
                    }
                    case 10: {
                        node.setN11(serial);
                        break;
                    }
                    case 11: {
                        node.setN12(serial);
                        break;
                    }
                    case 12: {
                        node.setN13(serial);
                        break;
                    }
                    case 13: {
                        node.setN14(serial);
                        break;
                    }
                    case 14: {
                        node.setN15(serial);
                        break;
                    }
                    case 15: {
                        node.setN16(serial);
                        break;
                    }
                    case 16: {
                        throw new BaseException("Maximum level '16' was reached");
                    }
                }
                node.setLevel(new Integer(parentNode.getLevel().intValue() + 1));
            }
            else {
                node = new Node();
                node.setN1(serial);
                node.setLevel(new Integer(1));
            }
            node.setIdent(serial);
            node.setDescription(descr);
            node.setName(name);

            StringBuffer sqlInsert = new StringBuffer("INSERT INTO tb_catalog ");
            sqlInsert.append(" (id, parentid, name, descr, level, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, candeleted) ");
            sqlInsert.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
            pstmt = connection.prepareStatement(sqlInsert.toString());
            setInteger(pstmt, 1, node.getIdent());
            setInteger(pstmt, 2, node.getParentId());
            setString(pstmt, 3, name);
            setString(pstmt, 4, descr);
            setInteger(pstmt, 5, node.getLevel());
            setInteger(pstmt, 6, node.getN1());
            setInteger(pstmt, 7, node.getN2());
            setInteger(pstmt, 8, node.getN3());
            setInteger(pstmt, 9, node.getN4());
            setInteger(pstmt, 10, node.getN5());
            setInteger(pstmt, 11, node.getN6());
            setInteger(pstmt, 12, node.getN7());
            setInteger(pstmt, 13, node.getN8());
            setInteger(pstmt, 14, node.getN9());
            setInteger(pstmt, 15, node.getN10());
            setInteger(pstmt, 16, node.getN11());
            setInteger(pstmt, 17, node.getN12());
            setInteger(pstmt, 18, node.getN13());
            setInteger(pstmt, 19, node.getN14());
            setInteger(pstmt, 20, node.getN15());
            setInteger(pstmt, 21, node.getN16());
            setInteger(pstmt, 22, (canDelete)?new Integer(1):new Integer(0));
            pstmt.executeUpdate();
            return node;
        } catch(BaseException e) {
            throw e;
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
            throw new BaseException("Cannot insert node ");
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
    }


    public static Node linkCatalogWithBSProject(Connection connection, Integer objectId, Integer nodeId) {
        PreparedStatement pstmt1 = null;
        try {
            pstmt1 = connection.prepareStatement(" INSERT INTO tb_bsprj2catalog (catalogid, prjid) VALUES  (?, ?) ");
            setInteger(pstmt1, 1, nodeId);
            setInteger(pstmt1, 2, objectId);
            pstmt1.executeUpdate();
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            System.out.println("Error while link bsprojects and catalog");
            e.printStackTrace();
            //AppLog.log(e);

        } finally {
            try { if(pstmt1 != null) pstmt1.close(); } catch(Exception e) {}
        }
        return null;
    }

    public static Node findCatalogForBSProject(Connection connection, Integer nodeId) {
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        Node node = null;
        try {
            pstmt1 = connection.prepareStatement(" SELECT * FROM tb_bsprj2catalog WHERE prjid = ?");
            setInteger(pstmt1, 1, nodeId);
            rs = pstmt1.executeQuery();
            if(rs.next()) {
                Integer id = getInteger(rs, "catalogid");
                if(id != null) node = findNode(connection, id);
                return node;
            }
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            System.out.println("Error while link bsprojects and catalog");
            e.printStackTrace();
            //AppLog.log(e);

        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt1 != null) pstmt1.close(); } catch(Exception e) {}
        }
        return null;
    }

    public static Node linkCatalogWithArendaLease(Connection connection, Integer objectId, Integer nodeId) {
        PreparedStatement pstmt1 = null;
        try {
            pstmt1 = connection.prepareStatement(" INSERT INTO tb_leasedoc2catalog (catalogid, leaseid) VALUES (?, ?) ");
            setInteger(pstmt1, 1, nodeId);
            setInteger(pstmt1, 2, objectId);
            pstmt1.executeUpdate();
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            System.out.println("Error while link lease document and catalog");
            e.printStackTrace();
        } finally {
            try { if(pstmt1 != null) pstmt1.close(); } catch(Exception e) {}
        }
        return null;
    }

    public static Node findCatalogForArendaLease(Connection connection, Integer objectId) {
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        Node node = null;
        try {
            pstmt1 = connection.prepareStatement(" SELECT catalogid FROM tb_leasedoc2catalog WHERE leaseid = ? ");
            setInteger(pstmt1, 1, objectId);
            rs = pstmt1.executeQuery();
            if(rs.next()) {
                Integer id = getInteger(rs, "catalogid");
                if(id != null) node = findNode(connection, id);
                return node;
            }
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            System.out.println("Error while link bsprojects and catalog");
            e.printStackTrace();
            //AppLog.log(e);

        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt1 != null) pstmt1.close(); } catch(Exception e) {}
        }
        return null;
    }
}
