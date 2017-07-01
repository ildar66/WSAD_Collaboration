/*
 * Created on 01.02.2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.trailcom.hibernate.id;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.exception.JDBCExceptionHelper;
import org.hibernate.id.IdentifierGeneratorFactory;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.hibernate.util.PropertiesHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author mmorev
 * Mapping parameters supported: sequence
 */
public class JullySequenceGenerator extends SequenceGenerator {
	
	//execute procedure getsequence('tables." + table + "')
	
	private String sequenceName;
	private String parameters;
	private Type identifierType = new LongType();
	private String sql;
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	private static final Log log = LogFactory.getLog(SequenceGenerator.class);
	
	public void configure(Type type, Properties params, Dialect dialect) throws MappingException {
			sequenceName = PropertiesHelper.getString(SEQUENCE, params, "hibernate_sequence");
			parameters = params.getProperty(PARAMETERS);
			String schemaName = params.getProperty(SCHEMA);
			String catalogName = params.getProperty(CATALOG);

			if ( log.isInfoEnabled() ) {
				log.debug("sequenceName="+sequenceName);
				logger.info("sequenceName="+sequenceName);
			}
			

			//this.identifierType = type;
			this.sql = "execute procedure getsequence('tables." + sequenceName + "')";
	}
	
	public Serializable generate(SessionImplementor session, Object obj) 
		throws HibernateException {
		
			try {

				PreparedStatement st = session.getBatcher().prepareSelectStatement(sql);
				try {
					ResultSet rs = st.executeQuery();
					try {
						rs.next();
						Serializable result = IdentifierGeneratorFactory.get(
								rs, identifierType
							);
						if ( log.isInfoEnabled() ) {
							log.debug("Sequence identifier generated: " + result);
							logger.info("Sequence identifier generated: " + result);
						}
						return result;
					}
					finally {
						rs.close();
					}
				}
				finally {
					session.getBatcher().closeStatement(st);
				}
			
			}
			catch (SQLException sqle) {
				throw JDBCExceptionHelper.convert(
						session.getFactory().getSQLExceptionConverter(),
						sqle,
						"could not get next sequence value",
						sql
					);
			}

		}

}
