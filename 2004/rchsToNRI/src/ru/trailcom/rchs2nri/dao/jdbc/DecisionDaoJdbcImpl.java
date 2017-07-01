package ru.trailcom.rchs2nri.dao.jdbc;

import ru.trailcom.rchs2nri.dao.DecisionDao;
import ru.trailcom.rchs2nri.domain.Decision;
import ru.trailcom.rchs2nri.domain.License;

import java.sql.*;
import java.util.List;

public class DecisionDaoJdbcImpl  extends JdbcDaoSupport implements DecisionDao {

	protected final static String TABLE = "rchs_decisions";


    public DecisionDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public DecisionDaoJdbcImpl() {
    }

    public Decision insertDecision(final Decision decision) {

		//JdbcTemplate template = new JdbcTemplate(dataSource);
		return (Decision)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sql = "INSERT INTO " + DecisionDaoJdbcImpl.TABLE + " ( requestid, docnumber, docdate, outnumber_rossv, outdate_rossv, indate_rossv, prognozlicdate, decistype, decisionid, innumber_rossv)"+
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement  preparedStatement = connection.prepareStatement(sql);

                decision.setId(new Integer(getNextId(connection, TABLE)));

                logger.info("decision.getDeclaration().getSeqId()="+decision.getDeclaration().getSeqId());
                preparedStatement.setInt(1, decision.getDeclaration().getSeqId().intValue());
                preparedStatement.setString(2, decision.getNumber());
                if(decision.getDate()!=null){
                    preparedStatement.setDate(3, new java.sql.Date(decision.getDate().getTime()));
                }else{
                    preparedStatement.setNull(3, Types.DATE);
                }
                preparedStatement.setString(4, decision.getNumberRossvOut());
                if(decision.getDateRossvOut()!=null){
                    preparedStatement.setDate(5, new java.sql.Date(decision.getDateRossvOut().getTime()));
                }else{
                    preparedStatement.setNull(5, Types.DATE);
                }
                if(decision.getDateRossvIn()!=null){
                    preparedStatement.setDate(6, new java.sql.Date(decision.getDateRossvIn().getTime()));
                }else{
                    preparedStatement.setNull(6, Types.DATE);
                }
                if(decision.getDateLicensePrognoses()!=null){
                    preparedStatement.setDate(7, new java.sql.Date(decision.getDateLicensePrognoses().getTime()));
                }else{
                    preparedStatement.setNull(7, Types.DATE);
                }
                preparedStatement.setString(8, decision.getType()+"");
                preparedStatement.setInt(9, decision.getId().intValue());
                if(decision.getNumberRossvIn()!=null){
                    preparedStatement.setString(10, decision.getNumberRossvIn());
                }else{
                    preparedStatement.setNull(10, Types.VARCHAR);
                }
                preparedStatement.execute();

                closePreparedStatement(preparedStatement);
                return decision;
			}}
		);

		//return false;
	}

	public Decision getDecision(Integer idDecision) {
		return null;
	}

	public List getDecisionList() {
		return null;
	}


	License fetchLicense(ResultSet resultSet) throws SQLException{
		License license = new License();
		license.setId(new Integer(resultSet.getInt("licenseid")));
		license.setNumber(resultSet.getString("docnumber"));
		license.setExpireDate(resultSet.getDate("expiredate"));
		license.setReceiveDate(resultSet.getDate("docdate"));
		license.setRealiseDate(resultSet.getDate("realizedate"));
		return license;
	}

	public void joinLicense(final Decision decision) {
		getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
                String sql = "SELECT * FROM " + LicenseDaoJdbcImpl.TABLE + " WHERE decisionid = ? AND lictype= ?";

                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, decision.getId().intValue());
                st.setString(2, new String(new char[]{License.TYPE_NORMAL}));
                ResultSet  resultSet = st.executeQuery(sql);
                if(resultSet.next()){
                    License license = fetchLicense(resultSet);
                    license.setType(License.TYPE_NORMAL);
                    decision.getLicenses().add(license);
                }
                closeResultSet(resultSet);
                closePreparedStatement(st);

				return null;
			}}
		);
	}

	public void insertDecisionBaseStation(final Integer decisionId, final Integer baseStationId) {
		getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sql = "INSERT INTO rchs_decision2BS (decisionid, idbsrchs) " +
                             "VALUES (?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, decisionId.intValue());
                preparedStatement.setInt(2, baseStationId.intValue());
                preparedStatement.execute();
                closePreparedStatement(preparedStatement);

                return null;
			}}
		);
	}
}
