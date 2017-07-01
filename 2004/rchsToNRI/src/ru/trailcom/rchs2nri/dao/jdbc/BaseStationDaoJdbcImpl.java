package ru.trailcom.rchs2nri.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.trailcom.rchs2nri.dao.BaseStationDao;
import ru.trailcom.rchs2nri.domain.BaseStation;
import ru.trailcom.rchs2nri.domain.Declaration;

import java.sql.*;
import java.util.*;

public class BaseStationDaoJdbcImpl extends JdbcDaoSupport implements BaseStationDao {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	protected static final String TABLE = "rchs_bshistory";


    public BaseStationDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public BaseStationDaoJdbcImpl() {
        super();
    }

    public boolean existsSuchBaseStations(final Integer idbs) {
        return ((Boolean)getJdbcTemplate().execute(new JdbcCallback(){
            public Object doWithConnection(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM rchs_basestations WHERE idbs = ?");
                preparedStatement.setInt(1, idbs.intValue());
                ResultSet resultSet = preparedStatement.executeQuery();
                Boolean result = Boolean.valueOf(resultSet.next());
                
                closeResultSet(resultSet);
                closePreparedStatement(preparedStatement);

                return result;
            }
        })).booleanValue();
    }


    public boolean existsBs2Nri(final Integer idBsBd) {
        return ((Boolean)getJdbcTemplate().execute(new JdbcCallback(){
            public Object doWithConnection(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM rchs_bs2nribs WHERE idbs = ?");
                preparedStatement.setInt(1, idBsBd.intValue());

                ResultSet resultSet = preparedStatement.executeQuery();
                Boolean result = Boolean.valueOf(resultSet.next());

                closeResultSet(resultSet);
                closePreparedStatement(preparedStatement);

                return result;
            }
        })).booleanValue();
    }

    public BaseStation insertBaseStation(final BaseStation baseStation) {
        //JdbcTemplate template = new JdbcTemplate(dataSource);
		return (BaseStation)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws SQLException {
                if (!existsSuchBaseStations(baseStation.getIdBsBd())) {
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO rchs_basestations(idbs, number) VALUES(?, ?)");

                    preparedStatement.setInt(1, baseStation.getIdBsBd().intValue());
                    preparedStatement.setInt(2, baseStation.getNumber().intValue());
                    //preparedStatement.setInt(2, baseStation.getBsBdNumber().intValue());

                    preparedStatement.execute();
                    closePreparedStatement(preparedStatement);
                }

                String sql = "INSERT INTO rchs_bshistory(idbsrchs, number, name, address, latitude, longitude, idbs) "+
                             "VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                
                preparedStatement.setInt(1, baseStation.getIdBsZaj().intValue());
                preparedStatement.setInt(2, baseStation.getNumber().intValue());
                preparedStatement.setString(3, baseStation.getName());
                preparedStatement.setString(4, baseStation.getAddress());
                preparedStatement.setBigDecimal(5, baseStation.getLatitude());
                preparedStatement.setBigDecimal(6, baseStation.getLongitude());
                preparedStatement.setInt(7, baseStation.getIdBsBd().intValue());

                preparedStatement.execute();
                closePreparedStatement(preparedStatement);

                return baseStation;
			}}
		);
	}

    public BaseStation getBaseStation(final Integer id) {
//		JdbcTemplate template = new JdbcTemplate(dataSource);
		return (BaseStation)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sql = "SELECT * FROM " + TABLE + " WHERE idbsrchs = ?";
                BaseStation baseStation = null;
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id.intValue());

                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    baseStation = fetchRow(resultSet);
                }
                
                closePreparedStatement(preparedStatement);
                closeResultSet(resultSet);

				return baseStation;
			}}
		
		);
	}

	public List getBaseStationList() {
		return (List)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sql = "SELECT * FROM " + TABLE;
				List items = new ArrayList();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    items.add(fetchRow(resultSet));
                }

                closeResultSet(resultSet);
                closePreparedStatement(preparedStatement);

                return items;
			}}
		);
	}

    /**
     * @deprecated after DB change I don't update this method
     * @param baseStation
     */
    public void update(final BaseStation baseStation) {
//		JdbcTemplate template = new JdbcTemplate(dataSource);
		getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sql = "UPDATE " + TABLE + " " +
                             "SET number = ?," +
                                " name = ?," +
                                " address = ?," +
                                " latitude = ?," +
                                " longitude = ?," +
                                " storageplace = ? "+
 						     "WHERE idbsrchs = ?";
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, baseStation.getNumber().intValue());
                preparedStatement.setString(2, baseStation.getName());
                preparedStatement.setString(3, baseStation.getAddress());
                preparedStatement.setBigDecimal(4, baseStation.getLatitude());
                preparedStatement.setBigDecimal(5, baseStation.getLongitude());
                preparedStatement.setInt(6, baseStation.getStorageplace().intValue());
                preparedStatement.setInt(7, baseStation.getIdBsZaj().intValue());
                preparedStatement.execute();

                closePreparedStatement(preparedStatement);
                
                return baseStation;
			}}
		);
	}

    /**
     * @deprecated after modification of DB I don't update query string
     * @return
     */
    public List getBaseStationStorageplaceIsNotNullList() {
		return (List)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sql = "SELECT * FROM " + TABLE + " JOIN rchs_bs2nribs "+
						     "WHERE rchs_bs2nribs.equipment IS NOT NULL";
                
                PreparedStatement  preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                List items = new ArrayList();
                while(resultSet.next()){
                    BaseStation baseStation = fetchRow(resultSet);
                    baseStation.setStorageplace(new Integer(resultSet.getInt("equipment")));
                    items.add(baseStation);
                }

                closePreparedStatement(preparedStatement);
                closeResultSet(resultSet);
				return items;
			}}
		
		);
	}

    public List getBaseStationByDeclarationStorageplaceIsNullList(final Declaration declaration) {
		return (List)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sql = "SELECT bs.* FROM rchs_request2bs as dec2bs " +
                             "LEFT JOIN " + TABLE + " AS bs ON bs.idbsrchs = dec2bs.idbsrchs " +
                             "LEFT JOIN rchs_basestations rbs ON rbs.idbs = bs.idbs " +
                             "LEFT JOIN rchs_bs2nribs AS nribs ON nribs.idbs = bs.idbs "+
                             "WHERE dec2bs.requestid = ? " +
                               "AND nribs.equipment IS NULL";
				logger.info(sql);
                List items = new ArrayList();
				PreparedStatement  preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, declaration.getSeqId().intValue());
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    items.add(fetchRow(resultSet));
                }

                closePreparedStatement(preparedStatement);
                closeResultSet(resultSet);
                
                return items;
			}}
		
		);
	}
	
	private BaseStation fetchRow(ResultSet resultSet) throws SQLException {
		BaseStation baseStation = new BaseStation();
		baseStation.setNumber(new Integer(resultSet.getInt("number")));
		baseStation.setName(resultSet.getString("name"));
		baseStation.setAddress(resultSet.getString("address"));
		baseStation.setLatitude(resultSet.getBigDecimal("latitude"));
		baseStation.setLongitude(resultSet.getBigDecimal("longitude"));
		baseStation.setIdBsZaj(new Integer(resultSet.getInt("idbsrchs")));
        baseStation.setIdBsBd(new Integer(resultSet.getInt("idbs")));
        baseStation.setBsBdNumber(new Integer(resultSet.getInt("number")));

        return baseStation;
	}

	public List findStorageplaceByNumberAndFilialIdInNRI(final Integer filialId, final Integer baseStationNumber, final Integer diap) {
		return (List)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {	
				String sql = "SELECT * FROM basestations " +
                             "JOIN equipment ON equipment.equipment=basestations.equipment " +
                             "JOIN positions on positions.storageplace=equipment.position "+
                             "WHERE positions.regionid= ?";
						if(diap.intValue()==900){
							sql += " AND basestations.numgsmpermit=? ";
						}else{
							sql += " AND basestations.numdcspermit=? ";
						}
						if(diap.intValue()==900){
							sql += " AND (basestations.type='S' OR basestations.type='G') ";
						}
						if(diap.intValue()==1800){
							sql += " AND (basestations.type='C' OR basestations.type='G') ";
						}
				
				logger.info(sql);
				List items = new ArrayList();
                
                PreparedStatement  preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, filialId.intValue());
                preparedStatement.setString(2, baseStationNumber.toString());
                
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    int equipment = resultSet.getInt("equipment");
                    items.add(new Integer(equipment));
                }

                closePreparedStatement(preparedStatement);
                closeResultSet(resultSet);

				return items;
			}}
		
		);
	}
	
	
	public List findStorageplaceByNumber5AndFilialIdInNRI(final Integer filialId, final Integer baseStationNumber, final Integer diap) {
		return (List)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {	
				String sql = "SELECT * FROM basestations " +
                             "JOIN equipment ON equipment.equipment = basestations.equipment " +
                             "JOIN positions ON positions.storageplace = equipment.position " +
                             "WHERE positions.regionid = ?";
						
				sql += " AND basestations.number = ?";
				if(diap.intValue()==900){
					sql += " AND (basestations.type = 'S' OR basestations.type = 'G')";
				}
				if(diap.intValue()==1800){
					sql += " AND (basestations.type = 'C' OR basestations.type = 'G')";
				}
				
				logger.info(sql);
				List items = new ArrayList();
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, filialId.intValue());
                preparedStatement.setString(2, baseStationNumber.toString());

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    int equipment = resultSet.getInt("equipment");
                    items.add(new Integer(equipment));
                }
                
                closePreparedStatement(preparedStatement);
                closeResultSet(resultSet);

				return items;
			}}
		
		);
	}

	public List findStorageplaceByNumberInMissingEquipment(final Integer baseStationNumber) {
		return (List)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {	
				String sql = "SELECT * FROM equipmentMissingPermit WHERE typeEqp = 1 and number = ?"; 

				logger.info(sql);
                List items = new ArrayList();

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, baseStationNumber.intValue());

                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    items.add(new Integer(resultSet.getInt("equipment")));
                }

                closeResultSet(resultSet);
                closePreparedStatement(statement);

                return items;
			}}
		);
	}

	public Integer saveInMissingEquipment(final BaseStation baseStation, final Integer filialId) {
		return (Integer)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
                Statement st = connection.createStatement();
                int equipment = getNextId(connection, "storageplaces");
                closePreparedStatement(st);

                String sqlInsertStorageplace = "INSERT INTO storageplaces (storageplace, name, address, type, operator, modified, created)" +
                                               "VALUES (?, ?, ?, 'M', 1227, current, current)";

				PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertStorageplace);

                preparedStatement.setInt(1, equipment);
                preparedStatement.setString(2, baseStation.getName()!=null?baseStation.getName():""+" --rch--");
                preparedStatement.setString(3, baseStation.getAddress());
                
                //insert into storageplaces
                preparedStatement.execute();
                closePreparedStatement(preparedStatement);
                
//				equipmentMissingPermit
				String sql = "INSERT INTO equipmentMissingPermit (equipment, typeEqp, ftype, number, name, address, lat, lon, status, regionid)"+
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				preparedStatement = connection.prepareStatement(sql);
				logger.info(sql);
                preparedStatement.setInt(1, equipment);
                preparedStatement.setInt(2, 1);//BS
                preparedStatement.setInt(3, 0);//ftype 900,1800,0(нет)
                preparedStatement.setInt(4, baseStation.getNumber().intValue());
                preparedStatement.setString(5, baseStation.getName());
                preparedStatement.setString(6, baseStation.getAddress());
                preparedStatement.setBigDecimal(7, baseStation.getLatitude());
                preparedStatement.setBigDecimal(8, baseStation.getLongitude());
                preparedStatement.setInt(9, 0);//status не обработан
                preparedStatement.setInt(10, filialId.intValue());//status не обработан
                
                preparedStatement.execute();
                closePreparedStatement(preparedStatement);

                return new Integer(equipment);
			}}
		
		);
	}

	public Declaration getLastDeclaration(BaseStation baseStation) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Этот код реализован в худших традициях. 
	 * его ждет рефакторинг!
	 * XP нужно чтобы работало!!
	 *  (non-Javadoc)
	 * @see ru.trailcom.rchs2nri.dao.BaseStationDao#makeActualPerimt(java.util.List)
	 */
	public void makeActualPerimt(final List baseStations,final List errors) {
		logger.info("makeActualPerimt");
		getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				Set permitsIds = new HashSet();

                String clearActualPermits = "DELETE FROM rchs_actualpermits";
				Statement statement = connection.createStatement();
				
				//TODO нужно разкомментировать для удаления на боевой
				statement.execute(clearActualPermits);
                closePreparedStatement(statement);
                logger.info(clearActualPermits);

                Map actualPermitsFull = getFullSequence(connection);
                Map actualPermitsLast = getLastSequence(connection);

                PreparedStatement  preparedStatementIns = connection.prepareStatement(
                    "INSERT INTO rchs_actualpermits " +
                    "(requestidfull, decisionidfull, licenseidfull, requestidlast, decisionidlast, licenseidlast, idbs) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?)");
				//итерация по базовым станцием. формирование отчета rch_actual_permits
                int count = 0;
                for(Iterator iterator=baseStations.iterator(); iterator.hasNext(); count++){
                    BaseStation baseStation = (BaseStation)iterator.next();

                    if (!permitsIds.contains(baseStation.getIdBsBd())) {
                        logger.info("baseStation.getIdBsZaj().intValue() = "+baseStation.getIdBsZaj().intValue());
                        logger.info((baseStations.size() - count) +  " more;");

                        int idRequestFull = 0;
                        int idDecisionFull = 0;
                        int idLicenceFull = 0;
                        int idRequestLast = 0;
                        int idDecisionLast = 0;
                        int idLicenceLast = 0;
                        if (actualPermitsFull.containsKey(baseStation.getIdBsBd())) {
                            int[] values = (int[]) actualPermitsFull.get(baseStation.getIdBsBd());
                            idRequestFull = values[0];
                            idDecisionFull = values[1];
                            idLicenceFull = values[2];
                        };

                        if (actualPermitsLast.containsKey(baseStation.getIdBsBd())) {
                            int[] values = (int[]) actualPermitsLast.get(baseStation.getIdBsBd());
                            idRequestLast = values[0];
                            idDecisionLast = values[1];
                            idLicenceLast = values[2];
                        }

                        logger.info("storageplace "+baseStation.getStorageplace()+" id_decl_full="+idRequestFull);
                        if((idRequestFull>0 || idRequestLast>0)){
                            permitsIds.add(baseStation.getIdBsBd());
                            if(idRequestFull>0){
                                preparedStatementIns.setInt(1, idRequestFull);
                            }else{
                                preparedStatementIns.setNull(1, java.sql.Types.INTEGER);
                            }
                            if(idDecisionFull>0){
                                preparedStatementIns.setInt(2, idDecisionFull);
                            }else{
                                preparedStatementIns.setNull(2, java.sql.Types.INTEGER);
                            }
                            if(idLicenceFull>0){
                                preparedStatementIns.setInt(3, idLicenceFull);
                            }else{
                                preparedStatementIns.setNull(3, java.sql.Types.INTEGER);
                            }

                            if(idRequestLast>0){
                                preparedStatementIns.setInt(4, idRequestLast);
                            }else{
                                preparedStatementIns.setNull(4, java.sql.Types.INTEGER);
                            }
                            if(idDecisionLast>0){
                                preparedStatementIns.setInt(5, idDecisionLast);
                            }else{
                                preparedStatementIns.setNull(5, java.sql.Types.INTEGER);
                            }
                            if(idLicenceLast>0){
                                preparedStatementIns.setInt(6, idLicenceLast);
                            }else{
                                preparedStatementIns.setNull(6, java.sql.Types.INTEGER);
                            }
                            preparedStatementIns.setInt(7, baseStation.getIdBsBd().intValue());
                            try{
                                preparedStatementIns.execute();
                            }catch (SQLException e) {
                                e.printStackTrace();
                                logger.error("Добавление уже существующей записи. Игнорируем. storageplace="+baseStation.getStorageplace() + " error code ["+e.getErrorCode()+"]");
                                errors.add("Не добавилась запись. storageplace="+baseStation.getStorageplace() + " error code ["+e.getErrorCode()+"]");
                            }
                        }
                    }
                }

                closePreparedStatement(preparedStatementIns);

                return null;
			}}
		);
	}

    private Map getFullSequence(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "             SELECT\n" +
                "                 bh.idbs," +
                "                 r2bs.idbsrchs," +
                "                 r.requestid," +
                "                 d.decisionid," +
                "                 l.licenseid," +
                "                 r.outdate_grch" +
                "             FROM rchs_bshistory as bh \n" +
                "                  JOIN rchs_request2bs as r2bs  ON bh.idbsrchs = r2bs.idbsrchs \n" +
                "                  JOIN rchs_requests as r on r2bs.requestid = r.requestid \n" +
                "                  JOIN rchs_decision2bs d2bs on  d2bs.idbsrchs = bh.idbsrchs\n" +
                "                  JOIN rchs_decisions as d on d.requestid = r.requestid AND d2bs.decisionid = d.decisionid\n" +
                "                  JOIN rchs_license2bs as l2bs on l2bs.idbsrchs = bh.idbsrchs\n" +
                "                  JOIN rchs_licenses as l on l.decisionid = d.decisionid AND l2bs.licenseid = l.licenseid\n" +
                "             ORDER BY idbs, r.outdate_grch DESC, d.docdate DESC, l.docdate DESC");
        ResultSet resultSet = preparedStatement.executeQuery();

        Map actualPermitsFull = new HashMap();
        while(resultSet.next()) {
            Integer idBsRchs = new Integer(resultSet.getInt("idbs"));
            if (actualPermitsFull.get(idBsRchs) == null) {
                actualPermitsFull.put(idBsRchs, new int[]{resultSet.getInt("requestid"), resultSet.getInt("decisionid"), resultSet.getInt("licenseid")});
            }
        }
        closeResultSet(resultSet);
        closePreparedStatement(preparedStatement);
        return actualPermitsFull;
    }

    private Map getLastSequence(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "             SELECT\n" +
                "                 bh.idbs," +
                "                 r2bs.idbsrchs," +
                "                 r.requestid," +
                "                 d.decisionid," +
                "                 l.licenseid," +
                "                 r.outdate_grch" +
                "             FROM rchs_bshistory as bh" +
                "                  JOIN rchs_request2bs as r2bs  ON bh.idbsrchs = r2bs.idbsrchs \n" +
                "                  JOIN rchs_requests as r on r2bs.requestid = r.requestid \n" +
                "                  LEFT JOIN rchs_decision2bs d2bs on  d2bs.idbsrchs = bh.idbsrchs\n" +
                "                  LEFT JOIN rchs_decisions as d on d.requestid = r.requestid AND d2bs.decisionid = d.decisionid\n" +
                "                  LEFT JOIN rchs_license2bs as l2bs on l2bs.idbsrchs = bh.idbsrchs\n" +
                "                  LEFT JOIN rchs_licenses as l on l.decisionid = d.decisionid AND l2bs.licenseid = l.licenseid\n" +
                "             ORDER BY idbs, r.outdate_grch DESC, d.docdate DESC, l.docdate DESC");
        ResultSet resultSet = preparedStatement.executeQuery();

        Map actualPermitsFull = new HashMap();
        while(resultSet.next()) {
            Integer idBsRchs = new Integer(resultSet.getInt("idbs"));
            if (actualPermitsFull.get(idBsRchs) == null) {
                actualPermitsFull.put(idBsRchs, new int[]{resultSet.getInt("requestid"), resultSet.getInt("decisionid"), resultSet.getInt("licenseid")});
            }
        }
        closeResultSet(resultSet);
        closePreparedStatement(preparedStatement);
        return actualPermitsFull;
    }

    //сохраняем данные о связи БС из РЧС с БС из NRI
	public void saveInBs2Nri(final BaseStation baseStation, final Integer regionid, final Integer nriCount, final Integer diap) {
		getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sql = "INSERT INTO rchs_bs2nribs (idbs, equipment, number5, nricount, regionId) "+
                             "VALUES (?, ?, ?, ?, ?)";
				logger.info(sql);
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, baseStation.getIdBsBd().intValue());
                if( baseStation.getStorageplace()!=null){
                    preparedStatement.setInt(2, baseStation.getStorageplace().intValue());
                }else{
                    preparedStatement.setNull(2, java.sql.Types.INTEGER);
                }
                preparedStatement.setInt(3, baseStation.getNumber5().intValue());
                preparedStatement.setInt(4, nriCount.intValue());
                preparedStatement.setInt(5, regionid.intValue());
                preparedStatement.execute();
                closePreparedStatement(preparedStatement);

                if(baseStation.getStorageplace()!=null){
                    saveDataIntoBaseStation(diap, baseStation);
                }

                closePreparedStatement(preparedStatement);
                return baseStation;
			}}
		);
	}

    private void saveDataIntoBaseStation(final Integer diap, final BaseStation baseStation) throws SQLException {
        getJdbcTemplate().execute(new JdbcCallback(){
            public Object doWithConnection(Connection connection) throws java.sql.SQLException {
                String sqlBs = "UPDATE basestations SET ";
                if(diap.intValue()==900){
                    sqlBs += " numgsmpermit=? ";
                }else{
                    sqlBs += " numdcspermit=? ";
                }
                sqlBs+= "WHERE equipment=?";
                logger.info(sqlBs);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlBs);
                preparedStatement.setInt(1, baseStation.getNumber().intValue());
                preparedStatement.setInt(2, baseStation.getStorageplace().intValue());
                preparedStatement.execute();

                closePreparedStatement(preparedStatement);

                return null;
            }
        });       
    }


    public void updateStatictics() {
        getJdbcTemplate().execute(new JdbcCallback(){
            public Object doWithConnection(Connection connection) throws SQLException {
                executeSql("UPDATE STATISTICS FOR TABLE rchs_actualpermits", connection);
                executeSql("UPDATE STATISTICS FOR TABLE rchs_basestations", connection);
                executeSql("UPDATE STATISTICS FOR TABLE rchs_bs2nribs", connection);
                executeSql("UPDATE STATISTICS FOR TABLE rchs_bshistory", connection);
                executeSql("UPDATE STATISTICS FOR TABLE rchs_decision2bs", connection);
                executeSql("UPDATE STATISTICS FOR TABLE rchs_decisions", connection);
                executeSql("UPDATE STATISTICS FOR TABLE rchs_license2bs", connection);
                executeSql("UPDATE STATISTICS FOR TABLE rchs_licenses", connection);
                executeSql("UPDATE STATISTICS FOR TABLE rchs_request2bs", connection);
                executeSql("UPDATE STATISTICS FOR TABLE rchs_requests", connection);
                return null;
            }

            private void executeSql(String query, Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
                closePreparedStatement(preparedStatement);
            }
        });
    }
}

