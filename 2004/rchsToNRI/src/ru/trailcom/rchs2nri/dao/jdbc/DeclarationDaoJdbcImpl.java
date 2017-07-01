package ru.trailcom.rchs2nri.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.trailcom.rchs2nri.dao.DeclarationDao;
import ru.trailcom.rchs2nri.domain.Declaration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class DeclarationDaoJdbcImpl extends JdbcDaoSupport implements DeclarationDao {
	protected Log logger = LogFactory.getLog(this.getClass());
	
	protected static final String TABLE = "rchs_requests";

    public DeclarationDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public DeclarationDaoJdbcImpl() {
        super();
    }

    public Declaration insertDeclaration(final Declaration declaration) {
        return (Declaration)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
                String sql = "INSERT INTO " + TABLE + " (diap, definition, equipcount, companyname, rchsregionname, projectdate, outnumber_grch, outdate_grch, prognozdecisdate, rchsdocid, innumber_grch, indate_grch, requestid) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);

                declaration.setSeqId(new Integer(getNextId(connection, TABLE)));

                preparedStatement.setInt(1, declaration.getDiap().intValue());
                preparedStatement.setString(2, declaration.getDefinition());
                preparedStatement.setInt(3, declaration.getBaseStations().size());
                preparedStatement.setString(4, declaration.getCompany());
                preparedStatement.setString(5, declaration.getRegion());
                if(declaration.getProjectDate()!=null){
                    preparedStatement.setDate(6, new java.sql.Date(declaration.getProjectDate().getTime()));
                }else{
                    preparedStatement.setNull(6, Types.DATE);
                }

                preparedStatement.setString(7, declaration.getNumberGRCCout());
                if(declaration.getDateGRCCout()!=null){
                    preparedStatement.setDate(8, new java.sql.Date(declaration.getDateGRCCout().getTime()));
                }else{
                    preparedStatement.setNull(8, Types.DATE);
                }
                if(declaration.getDateDecisionPrognosis()!=null){
                    preparedStatement.setDate(9, new java.sql.Date(declaration.getDateDecisionPrognosis().getTime()));
                }else{
                    preparedStatement.setNull(9, Types.DATE);
                }
                preparedStatement.setInt(10, declaration.getId().intValue());
                preparedStatement.setString(11, declaration.getNumberGRCCin());
                if(declaration.getDateGRCCin()!=null){
                    preparedStatement.setDate(12, new java.sql.Date(declaration.getDateGRCCin().getTime()));
                }else{
                    preparedStatement.setNull(12, java.sql.Types.DATE);
                }
                preparedStatement.setInt(13, declaration.getSeqId().intValue());
                preparedStatement.execute();
                
                closePreparedStatement(preparedStatement);

				return declaration;
			}}
		
		);
		
		//return false;
	}

	public Declaration getDeclaration(Integer idDeclaration) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getDeclarationList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * многие ко многим
	 * rchs_request2bs
	 * ID заявки requestid
	 * ID БС idbs
	 */
	public void insertDeclarationBaseStation(final Integer declarationId, final Integer baseStationId) {
        getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws SQLException {
				final String sql = "INSERT INTO rchs_request2BS (requestid, idbsrchs) VALUES (?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, declarationId.intValue());
                preparedStatement.setInt(2, baseStationId.intValue());
                preparedStatement.execute();
                closePreparedStatement(preparedStatement);

                return null;
			}}
		);
	}

	public List getDeclarationFilialIdIsNullList() {
		//JdbcTemplate template = new JdbcTemplate(dataSource);
		return (List)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sql = "SELECT * FROM "+TABLE+" WHERE regionid IS NULL";
				logger.info(sql);
				Statement st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(sql);
                
                List list = new ArrayList();
                while(resultSet.next()){
                    list.add(fetchRow(resultSet));
                }

                closeResultSet(resultSet);
                closePreparedStatement(st);
                
                return list;
			}}
		);
	}
	
	protected Declaration fetchRow(ResultSet resultSet) throws SQLException{
		Declaration declaration = new Declaration();
		declaration.setSeqId(new Integer(resultSet.getInt("requestid")));
		declaration.setId(new Integer(resultSet.getInt("rchsdocid")));
		declaration.setDiap(new Integer(resultSet.getInt("diap")));	
		declaration.setDefinition(resultSet.getString("definition"));
		declaration.setCompany(resultSet.getString("companyname"));
		declaration.setRegion(resultSet.getString("rchsregionname"));
		declaration.setProjectDate(resultSet.getDate("projectdate"));
		declaration.setNumberGRCCout(resultSet.getString("outnumber_grch"));
		declaration.setDateGRCCout(resultSet.getDate("outdate_grch"));
		declaration.setDateDecisionPrognosis(resultSet.getDate("prognozdecisdate"));
		declaration.setRegionid(new Integer(resultSet.getInt("regionid")));
//		if(resultSet.getString("type")!=null && resultSet.getString("type").length()>0){
//			declaration.setType(resultSet.getString("type").charAt(0));
//		}
		return declaration;
	}

	public void findFilialIdByRegionInReference(final Declaration declaration) {
		getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sql = "SELECT n_site_nri AS filialid, idfilial AS regionid FROM smss_label_to_nri_filial WHERE UPPER(filname) = ?";
				logger.info(sql);
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, declaration.getRegion().trim().toUpperCase());
                
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    declaration.setRegionid(new Integer(resultSet.getInt("regionid")));
                    declaration.setFilialId(new Integer(resultSet.getInt("filialid")));
                }

                closeResultSet(resultSet);
                closePreparedStatement(preparedStatement);
                
                return declaration;
			}}
		);
	}

	public void update(final Declaration declaration) {
		getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
                    String sql = "UPDATE " + TABLE + " " +
                                 "SET diap=?, " +
                                     "definition=?, " +
                                     "equipcount=?, " +
                                     "companyname=?, " +
                                     "rchsregionname=?, " +
                                     "projectdate=?, " +
                                     "outnumber_grch=?, " +
                                     "outdate_grch=?, " +
                                     "prognozdecisdate=?, " +
                                     "regionid=? " +
                                 "WHERE requestid=?";

                    PreparedStatement  preparedStatement = connection.prepareStatement(sql);

                    if (declaration.getDiap() != null) { 
                        preparedStatement.setInt(1, declaration.getDiap().intValue());
                    } else {
                        preparedStatement.setNull(1, Types.INTEGER);
                    }

                    preparedStatement.setString(2, declaration.getDefinition());
                    preparedStatement.setInt(3, declaration.getBaseStations().size());
                    preparedStatement.setString(4, declaration.getCompany());
                    preparedStatement.setString(5, declaration.getRegion());

                    if (declaration.getProjectDate() != null) {
                        preparedStatement.setDate(6, new java.sql.Date(declaration.getProjectDate().getTime()));
                    } else {
                        preparedStatement.setNull(6, Types.DATE);
                    }

                    preparedStatement.setString(7, declaration.getNumberGRCCout());

                    if (declaration.getDateGRCCout() == null) {
                        preparedStatement.setNull(8, Types.DATE);
                    } else {
                        preparedStatement.setDate(8, new java.sql.Date((declaration.getDateGRCCout().getTime())));
                    }

                    if (declaration.getDateDecisionPrognosis() != null) {
                        preparedStatement.setDate(9, new java.sql.Date(declaration.getDateDecisionPrognosis().getTime()));
                    } else {
                        preparedStatement.setNull(9, Types.DATE);
                    }

                    if (declaration.getRegionid() != null) {
                        preparedStatement.setInt(10, declaration.getRegionid().intValue());
                    } else {
                        preparedStatement.setNull(10, Types.INTEGER);
                    }

                    if (declaration.getSeqId() != null) {
                        preparedStatement.setInt(11, declaration.getSeqId().intValue());
                    } else {
                        preparedStatement.setNull(11, Types.INTEGER);
                    }

                    preparedStatement.execute();
                    closePreparedStatement(preparedStatement);

                    return declaration;
			}}
		
		);
		
	}

	public void clearPermits(final List errors) {
		getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sqlDeclaration = "delete FROM "+DeclarationDaoJdbcImpl.TABLE;
				String sqlDecision = "delete FROM "+DecisionDaoJdbcImpl.TABLE;
				String sqlLicense = "delete FROM "+LicenseDaoJdbcImpl.TABLE;
				
				String sqlHistory = "delete FROM "+BaseStationDaoJdbcImpl.TABLE;
                String sqlBaseStations = "delete FROM rchs_basestations";

                String sqlBaseStation2nri = "delete FROM rchs_bs2nribs";
				
				String sqlDeclaration2BaseStation = "delete FROM rchs_request2bs";
				String sqlDecision2BaseStation = "delete FROM rchs_decision2bs";
				String sqlLicense2BaseStation = "delete FROM rchs_license2bs";
				
				String sqlPermits = "delete FROM rchs_actualpermits";
                
                Statement st = connection.createStatement();

                st.execute(sqlPermits);
                logger.info(sqlPermits);

                st.execute(sqlLicense2BaseStation);
                logger.info(sqlLicense2BaseStation);

                st.execute(sqlDeclaration2BaseStation);
                logger.info(sqlDeclaration2BaseStation);

                st.execute(sqlDecision2BaseStation);
                logger.info(sqlDecision2BaseStation);

                st.execute(sqlLicense);
                logger.info(sqlLicense);

                st.execute(sqlDecision);
                logger.info(sqlDecision);

                st.execute(sqlDeclaration);
                logger.info(sqlDeclaration);

                st.execute(sqlHistory);
                logger.info(sqlHistory);

                st.execute(sqlBaseStation2nri);
                logger.info(sqlBaseStation2nri);

                st.execute(sqlBaseStations);
                logger.info(sqlBaseStations);

                closePreparedStatement(st);

                return null;
			}}
		);
	}
}
