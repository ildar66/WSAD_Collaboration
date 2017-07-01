package ru.trailcom.rchs2nri.dao.jdbc;

import ru.trailcom.rchs2nri.dao.LicenseDao;
import ru.trailcom.rchs2nri.domain.License;

import java.sql.*;
import java.util.List;

public class LicenseDaoJdbcImpl  extends JdbcDaoSupport implements LicenseDao {

	protected final static String TABLE = "rchs_licenses";


    public LicenseDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public LicenseDaoJdbcImpl() {
    }

    public License insertLicense(final License license) {
		
		//JdbcTemplate template = new JdbcTemplate(dataSource);
		return (License)getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sql = "INSERT INTO "+TABLE+" ( decisionid, docnumber, docdate, realizedate, expiredate, lictype, licenseid)"+
                             "VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                Statement st = connection.createStatement();
                license.setId(new Integer(getNextId(connection, TABLE)));

                preparedStatement.setInt(1, license.getDecision().getId().intValue());
                preparedStatement.setString(2, license.getNumber());
                if(license.getReceiveDate()!=null){
                    preparedStatement.setDate(3, new java.sql.Date(license.getReceiveDate().getTime()));
                }else{
                    preparedStatement.setNull(3, Types.DATE);
                }
                if(license.getRealiseDate()!=null){
                    preparedStatement.setDate(4, new java.sql.Date(license.getRealiseDate().getTime()));
                }else{
                    preparedStatement.setNull(4, Types.DATE);
                }
                if(license.getExpireDate()!=null){
                    preparedStatement.setDate(5, new java.sql.Date(license.getExpireDate().getTime()));
                }else{
                    preparedStatement.setNull(5, Types.DATE);
                }
                preparedStatement.setString(6, license.getType()+"");
                preparedStatement.setInt(7, license.getId().intValue());
                preparedStatement.execute();

                closePreparedStatement(preparedStatement);
                closePreparedStatement(st);

				return license;
			}}
		
		);
		
		//return false;
	}


	public License getLicense(Integer idLicense) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getLicenseList() {
		// TODO Auto-generated method stub
		return null;
	}


	public void insertLicenseBaseStation(final Integer licenseId, final Integer baseStationId) {
		getJdbcTemplate().execute(new JdbcCallback(){
			public Object doWithConnection(Connection connection) throws java.sql.SQLException {
				String sql = "INSERT INTO rchs_license2bs (licenseid, idbsrchs) " +
                             "VALUES (?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, licenseId.intValue());
                preparedStatement.setInt(2, baseStationId.intValue());
                preparedStatement.execute();

                if(preparedStatement!=null){
                    preparedStatement.close();
                }

                return null;
			}}
		);
	}
}
