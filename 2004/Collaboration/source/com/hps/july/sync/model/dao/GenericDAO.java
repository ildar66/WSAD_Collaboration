/*
 * Created on 19.11.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.model.dao;

import java.sql.*;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.naming.*;
import javax.sql.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.hps.july.sync.exceptions.ReferencesDAOSysException;
import com.hps.july.sync.model.Page;
import com.hps.july.sync.model.util.JNDINames;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/**
 * This class implements a generic CatalogDAO which loads the SQL statement
 * descriptions fron an XML configuration file.
 * This class encapsulates all the SQL calls made by Catalog EJB.
 * This layer maps the relational data stored in the database to
 * the objects needed by Catalog EJB.
*/

public class GenericDAO {
	private static final boolean TRACE = true;

	private static final String XML_DAO_CONFIGURATION = "DAOConfiguration";
	private static final String XML_DAO_STATEMENTS = "DAOStatements";
	private static final String XML_DATABASE = "database";
	private static final String XML_SQL_STATEMENT = "SQLStatement";
	private static final String XML_METHOD = "method";
	private static final String XML_SQL_FRAGMENT = "SQLFragment";
	private static final String XML_PARAMETER_NB = "parameterNb";
	private static final String XML_OCCURRENCE = "occurrence";
	private static final String XML_ONCE = "ONCE";
	private static final String XML_VARIABLE = "VARIABLE";

	/**
	 * ��������� � ����������� �����
		private static final String XML_GET_CATEGORY = "GET_CATEGORY";
		private static final String XML_GET_CATEGORIES = "GET_CATEGORIES";
		private static final String XML_GET_PRODUCT = "GET_PRODUCT";
		private static final String XML_GET_PRODUCTS = "GET_PRODUCTS";
		private static final String XML_GET_ITEM = "GET_ITEM";
		private static final String XML_GET_ITEMS = "GET_ITEMS";
		private static final String XML_SEARCH_ITEMS = "SEARCH_ITEMS";
	*/

	private Map sqlStatements = new HashMap();

	public GenericDAO() throws ReferencesDAOSysException {
		/**		
				try {
					InitialContext context = new InitialContext();
					URL daoSQLURL = (URL) context.lookup(JNDINames.DAO_SQL_URL);
					String database = (String) context.lookup(JNDINames.DAO_DATABASE);
					SAXParserFactory parserFactory = SAXParserFactory.newInstance();
					parserFactory.setValidating(true);
					parserFactory.setNamespaceAware(true);
					XMLReader reader = parserFactory.newSAXParser().getXMLReader();
					loadSQLStatements(parserFactory.newSAXParser(), database, new InputSource(daoSQLURL.openStream()));
					if (TRACE) {
						System.err.println("DAO SQL statements used: " + sqlStatements);
					}
				} catch (Exception exception) {
					System.err.println(exception);
					throw new ReferencesDAOSysException(exception.getMessage());
				}
				return;
		*/
		//���� ���:
		this("C:\\workspases\\WSAD_Collaboration\\Collaboration\\DAOSQL.xml", "informix");
	}

	public GenericDAO(String daoSQLFileName, String database) throws ReferencesDAOSysException {
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parserFactory.setValidating(true);
			parserFactory.setNamespaceAware(true);
			XMLReader reader = parserFactory.newSAXParser().getXMLReader();
			loadSQLStatements(parserFactory.newSAXParser(), database, new InputSource(daoSQLFileName));
			if (TRACE) {
				System.err.println("DAO SQL statements used: " + sqlStatements);
			}
		} catch (Exception exception) {
			exception.printStackTrace(System.err);
			System.err.println(exception);
			throw new ReferencesDAOSysException(exception.getMessage());
		}
		return;
	}
	/**
		private static DataSource getDataSource() throws ReferencesDAOSysException {
			try {
				InitialContext context = new InitialContext();
				return (DataSource) context.lookup(JNDINames.CATALOG_DATASOURCE);
			} catch (NamingException exception) {
				throw new ReferencesDAOSysException("NamingException while looking up DB context : " + exception.getMessage());
			}
		}
	*/
	private static void closeAll(Connection connection, PreparedStatement statement, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception exception) {
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception exception) {
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception exception) {
			}
		}
		return;
	}

	// Business methods
	/**
		public Category getCategory(String categoryID, Locale locale) throws ReferencesDAOSysException {
			Connection connection = null;
			ResultSet resultSet = null;
			PreparedStatement statement = null;
			try {
				connection = getDataSource().getConnection();
				String[] parameterValues = new String[] { locale.toString(), categoryID };
				if (TRACE) {
					printSQLStatement(sqlStatements, XML_GET_CATEGORY, parameterValues);
				}
				statement = buildSQLStatement(connection, sqlStatements, XML_GET_CATEGORY, parameterValues);
				resultSet = statement.executeQuery();
				if (resultSet.first()) {
					return new Category(categoryID, resultSet.getString(1), resultSet.getString(2));
				}
				return null;
			} catch (SQLException exception) {
				throw new ReferencesDAOSysException("SQLException: " + exception.getMessage());
			} finally {
				closeAll(connection, statement, resultSet);
			}
		}
	
		public Page getCategories(int start, int count, Locale locale) throws ReferencesDAOSysException {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			try {
				connection = getDataSource().getConnection();
				String[] parameterValues = new String[] { locale.toString()};
				if (TRACE) {
					printSQLStatement(sqlStatements, XML_GET_CATEGORIES, parameterValues);
				}
				statement = buildSQLStatement(connection, sqlStatements, XML_GET_CATEGORIES, parameterValues);
				resultSet = statement.executeQuery();
				if (start >= 0 && resultSet.absolute(start + 1)) {
					boolean hasNext = false;
					List categories = new ArrayList();
					do {
						categories.add(new Category(resultSet.getString(1).trim(), resultSet.getString(2), resultSet.getString(3)));
					} while ((hasNext = resultSet.next()) && (--count > 0));
					return new Page(categories, start, hasNext);
				}
				return Page.EMPTY_PAGE;
			} catch (SQLException exception) {
				throw new ReferencesDAOSysException("SQLException: " + exception.getMessage());
			} finally {
				closeAll(connection, statement, resultSet);
			}
		}
	
		public Product getProduct(String productID, Locale locale) throws ReferencesDAOSysException {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			try {
				connection = getDataSource().getConnection();
				String[] parameterValues = new String[] { locale.toString(), productID };
				if (TRACE) {
					printSQLStatement(sqlStatements, XML_GET_PRODUCT, parameterValues);
				}
				statement = buildSQLStatement(connection, sqlStatements, XML_GET_PRODUCT, parameterValues);
				resultSet = statement.executeQuery();
				if (resultSet.first()) {
					return new Product(productID, resultSet.getString(1), resultSet.getString(2));
				}
				return null;
			} catch (SQLException exception) {
				throw new ReferencesDAOSysException("SQLException: " + exception.getMessage());
			} finally {
				closeAll(connection, statement, resultSet);
			}
		}
	
		public Page getProducts(String categoryID, int start, int count, Locale locale) throws ReferencesDAOSysException {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			try {
				connection = getDataSource().getConnection();
				String[] parameterValues = new String[] { locale.toString(), categoryID };
				if (TRACE) {
					printSQLStatement(sqlStatements, XML_GET_PRODUCTS, parameterValues);
				}
				statement = buildSQLStatement(connection, sqlStatements, XML_GET_PRODUCTS, parameterValues);
				resultSet = statement.executeQuery();
				if (start >= 0 && resultSet.absolute(start + 1)) {
					boolean hasNext = false;
					List products = new ArrayList();
					do {
						products.add(new Product(resultSet.getString(1).trim(), resultSet.getString(2), resultSet.getString(3)));
					} while ((hasNext = resultSet.next()) && (--count > 0));
					return new Page(products, start, hasNext);
				}
				return Page.EMPTY_PAGE;
			} catch (SQLException exception) {
				throw new ReferencesDAOSysException("SQLException: " + exception.getMessage());
			} finally {
				closeAll(connection, statement, resultSet);
			}
		}
	
		public Item getItem(String itemID, Locale locale) throws ReferencesDAOSysException {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			try {
				connection = getDataSource().getConnection();
				String[] parameterValues = new String[] { locale.toString(), itemID };
				if (TRACE) {
					printSQLStatement(sqlStatements, XML_GET_ITEM, parameterValues);
				}
				statement = buildSQLStatement(connection, sqlStatements, XML_GET_ITEM, parameterValues);
				resultSet = statement.executeQuery();
				if (resultSet.first()) {
					int i = 1;
					return new Item(
						resultSet.getString(i++).trim(),
						resultSet.getString(i++).trim(),
						resultSet.getString(i++),
						itemID,
						resultSet.getString(i++).trim(),
						resultSet.getString(i++),
						resultSet.getString(i++),
						resultSet.getString(i++),
						resultSet.getString(i++),
						resultSet.getString(i++),
						resultSet.getString(i++),
						resultSet.getDouble(i++),
						resultSet.getDouble(i++));
				}
				return null;
			} catch (SQLException exception) {
				throw new ReferencesDAOSysException("SQLException: " + exception.getMessage());
			} finally {
				closeAll(connection, statement, resultSet);
			}
		}
	
		public Page getItems(String productID, int start, int count, Locale locale) throws ReferencesDAOSysException {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			try {
				connection = getDataSource().getConnection();
				String[] parameterValues = new String[] { locale.toString(), productID };
				if (TRACE) {
					printSQLStatement(sqlStatements, XML_GET_ITEMS, parameterValues);
				}
				statement = buildSQLStatement(connection, sqlStatements, XML_GET_ITEMS, parameterValues);
				resultSet = statement.executeQuery();
				if (start >= 0 && resultSet.absolute(start + 1)) {
					boolean hasNext = false;
					List items = new ArrayList();
					do {
						int i = 1;
						items.add(
							new Item(
								productID,
								resultSet.getString(i++).trim(),
								resultSet.getString(i++),
								resultSet.getString(i++).trim(),
								resultSet.getString(i++).trim(),
								resultSet.getString(i++),
								resultSet.getString(i++),
								resultSet.getString(i++),
								resultSet.getString(i++),
								resultSet.getString(i++),
								resultSet.getString(i++),
								resultSet.getDouble(i++),
								resultSet.getDouble(i++)));
					} while ((hasNext = resultSet.next()) && (--count > 0));
					return new Page(items, start, hasNext);
				}
				return Page.EMPTY_PAGE;
			} catch (SQLException exception) {
				throw new ReferencesDAOSysException("SQLException: " + exception.getMessage());
			} finally {
				closeAll(connection, statement, resultSet);
			}
		}
	
		public Page searchItems(String searchQuery, int start, int count, Locale locale) throws ReferencesDAOSysException {
			Collection keywordSet = new HashSet();
			StringTokenizer tokenizer = new StringTokenizer(searchQuery);
			while (tokenizer.hasMoreTokens()) {
				keywordSet.add(tokenizer.nextToken());
			}
			if (keywordSet.isEmpty()) {
				return Page.EMPTY_PAGE;
			}
			String[] keywords = (String[]) keywordSet.toArray(new String[0]);
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			try {
				connection = getDataSource().getConnection();
				String[] parameterValues = new String[1 + (keywords.length * 3)];
				parameterValues[0] = locale.toString();
				for (int i = 0; i < keywords.length; i++) {
					parameterValues[(i * 3) + 1] = "%" + keywords[i] + "%";
					parameterValues[(i * 3) + 2] = "%" + keywords[i] + "%";
					parameterValues[(i * 3) + 3] = "%" + keywords[i] + "%";
				}
				if (TRACE) {
					printSQLStatement(sqlStatements, XML_SEARCH_ITEMS, parameterValues);
				}
				statement = buildSQLStatement(connection, sqlStatements, XML_SEARCH_ITEMS, parameterValues);
				resultSet = statement.executeQuery();
				if (start >= 0 && resultSet.absolute(start + 1)) {
					boolean hasNext = false;
					List items = new ArrayList();
					do {
						int i = 1;
						items.add(
							new Item(
								resultSet.getString(i++).trim(),
								resultSet.getString(i++).trim(),
								resultSet.getString(i++),
								resultSet.getString(i++).trim(),
								resultSet.getString(i++).trim(),
								resultSet.getString(i++),
								resultSet.getString(i++),
								resultSet.getString(i++),
								resultSet.getString(i++),
								resultSet.getString(i++),
								resultSet.getString(i++),
								resultSet.getDouble(i++),
								resultSet.getDouble(i++)));
					} while ((hasNext = resultSet.next()) && (--count > 0));
					return new Page(items, start, hasNext);
				}
				return Page.EMPTY_PAGE;
			} catch (SQLException exception) {
				throw new ReferencesDAOSysException("SQLException: " + exception.getMessage());
			} finally {
				closeAll(connection, statement, resultSet);
			}
		}
	*/
	private PreparedStatement buildSQLStatement(Connection connection, Map sqlStatements, String sqlStatementKey, String[] parameterValues, String orderBy) throws SQLException {
		Statement statement = (Statement) sqlStatements.get(sqlStatementKey);
		if (statement != null) {
			return buildSQLStatement(connection, statement, parameterValues, orderBy);
		}
		return null;
	}

	private PreparedStatement buildSQLStatement(Connection connection, Statement sqlStatement, String[] parameterValues, String orderBy) throws SQLException {
		StringBuffer buffer = new StringBuffer();
		int totalParameterValueNb = parameterValues != null ? parameterValues.length : 0;
		for (int i = 0; i < sqlStatement.fragments.length; i++) {
			if (sqlStatement.fragments[i].variableOccurrence) {
				while (totalParameterValueNb > 0 && totalParameterValueNb >= sqlStatement.fragments[i].parameterNumber) {
					buffer.append(sqlStatement.fragments[i].text);
					totalParameterValueNb -= sqlStatement.fragments[i].parameterNumber;
				}
			} else {
				buffer.append(sqlStatement.fragments[i].text);
				totalParameterValueNb -= sqlStatement.fragments[i].parameterNumber;
			}
		}
		if (totalParameterValueNb > 0) {
			System.err.println("Number of values doesn't match number of parameters: " + totalParameterValueNb + "/" + parameterValues.length);
		}
		buffer.append(" order by " + orderBy);
		PreparedStatement statement = connection.prepareStatement(buffer.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		if (parameterValues != null) {
			for (int i = 0; i < parameterValues.length; i++) {
				statement.setString(i + 1, parameterValues[i]);
			}
		}
		return statement;
	}

	private void printSQLStatement(Map sqlStatements, String sqlStatementKey, String[] parameterValues, String orderBy) {
		Statement statement = (Statement) sqlStatements.get(sqlStatementKey);
		if (statement != null) {
			printSQLStatement(statement, parameterValues, orderBy);
		} else {
			System.err.println("No statement found for: " + sqlStatementKey);
		}
		return;
	}

	private void printSQLStatement(Statement sqlStatement, String[] parameterValues, String orderBy) {
		StringBuffer buffer = new StringBuffer();
		int totalParameterValueNb = parameterValues != null ? parameterValues.length : 0;
		for (int i = 0; i < sqlStatement.fragments.length; i++) {
			if (sqlStatement.fragments[i].variableOccurrence) {
				while (totalParameterValueNb > 0 && totalParameterValueNb >= sqlStatement.fragments[i].parameterNumber) {
					buffer.append(' ').append(sqlStatement.fragments[i].text);
					totalParameterValueNb -= sqlStatement.fragments[i].parameterNumber;
				}
			} else {
				buffer.append(' ').append(sqlStatement.fragments[i].text);
				totalParameterValueNb -= sqlStatement.fragments[i].parameterNumber;
			}
		}
		if (totalParameterValueNb > 0) {
			System.err.println("Number of values doesn't match number of parameters: " + totalParameterValueNb + "/" + parameterValues.length);
		}
		StringTokenizer tokenizer = new StringTokenizer(buffer.toString(), "?", true);
		for (int i = 0; tokenizer.hasMoreTokens();) {
			String token = tokenizer.nextToken();
			if (token.equals("?")) {
				System.out.print("\'" + parameterValues[i++] + "\'");
			} else {
				System.out.print(token);
			}
		}
		System.out.println(" order by " + orderBy + ";");
		return;
	}

	private static class ParsingDoneException extends SAXException {

		ParsingDoneException() {
			super("");
		}
	}

	private static class Statement {
		Fragment[] fragments;

		static class Fragment {
			boolean variableOccurrence = false;
			int parameterNumber = 0;
			String text;

			public String toString() {
				return new StringBuffer(text).append('/').append(parameterNumber).append('/').append(variableOccurrence).toString();
			}
		}

		public String toString() {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < fragments.length; i++) {
				buffer.append(fragments[i].toString()).append("\n\t");
			}
			return buffer.toString();
		}
	}

	private void loadSQLStatements(SAXParser parser, final String database, InputSource source) throws SAXException, IOException {
		try {
			parser.parse(source, new DefaultHandler() {
				private boolean foundEntry = false;
				private String operation = null;
				List fragments = new ArrayList();
				Statement.Fragment fragment;
				private StringBuffer buffer = new StringBuffer();

				public void startElement(String namespace, String name, String qName, Attributes attrs) throws SAXException {
					if (!foundEntry) {
						if (name.equals(XML_DAO_STATEMENTS) && attrs.getValue(XML_DATABASE).equals(database)) {
							foundEntry = true;
						}
					} else if (operation != null) {
						if (name.equals(XML_SQL_FRAGMENT)) {
							fragment = new Statement.Fragment();
							String value = attrs.getValue(XML_PARAMETER_NB);
							if (value != null) {
								try {
									fragment.parameterNumber = Integer.parseInt(value);
								} catch (NumberFormatException exception) {
									//throw new SAXException(exception);
								}
							}
							value = attrs.getValue(XML_OCCURRENCE);
							fragment.variableOccurrence = (value != null && value.equals(XML_VARIABLE));
							buffer.setLength(0);
						}
					} else {
						if (name.equals(XML_SQL_STATEMENT)) {
							operation = attrs.getValue(XML_METHOD);
							fragments.clear();
						}
					}
					return;
				}

				public void characters(char[] chars, int start, int length) throws SAXException {
					if (foundEntry && operation != null) {
						buffer.append(chars, start, length);
					}
					return;
				}

				public void endElement(String namespace, String name, String qName) throws SAXException {
					if (foundEntry) {
						if (name.equals(XML_DAO_STATEMENTS)) {
							foundEntry = false;
							throw new ParsingDoneException(); // Interrupt the parsing since everything has been collected
						} else if (name.equals(XML_SQL_STATEMENT)) {
							Statement statement = new Statement();
							statement.fragments = (Statement.Fragment[]) fragments.toArray(new Statement.Fragment[0]);
							sqlStatements.put(operation, statement);
							operation = null;
						} else if (name.equals(XML_SQL_FRAGMENT)) {
							fragment.text = buffer.toString().trim();
							fragments.add(fragment);
							fragment = null;
						}
					}
					return;
				}

				public void warning(SAXParseException exception) {
					System.err.println("[Warning]: " + exception.getMessage());
					return;
				}

				public void error(SAXParseException exception) {
					System.err.println("[Error]: " + exception.getMessage());
					return;
				}

				public void fatalError(SAXParseException exception) throws SAXException {
					System.err.println("[Fatal Error]: " + exception.getMessage());
					throw exception;
				}
			});
		} catch (ParsingDoneException exception) {
		} // Ignored
		return;
	}

	//�������� �����:
	public Page executeSelect(Connection con, String xml_get_pagem, String[] parameterValues, int start, int count, String orderBy) throws ReferencesDAOSysException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			if (TRACE) {
				printSQLStatement(sqlStatements, xml_get_pagem, parameterValues, orderBy);
			}
			statement = buildSQLStatement(con, sqlStatements, xml_get_pagem, parameterValues, orderBy);
			resultSet = statement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			// determine the number of columns from the mete data
			int numberOfColumns = metaData.getColumnCount();
			// ���������� ���������:
			List headers = new ArrayList(numberOfColumns);
			for (int i = 1; i <= numberOfColumns; i++) {
				headers.add(metaData.getColumnName(i));
			}
			//�������� ������:
			boolean hasNext = false;
			List list = null;
			if (start >= 0 && resultSet.absolute(start + 1)) {
				list = new ArrayList();
				do {
					list.add(populateRow(resultSet, numberOfColumns));
				} while ((hasNext = resultSet.next()) && (--count > 0));
				return new Page(headers, list, start, hasNext);
			}
			return new Page(headers, Collections.EMPTY_LIST, 0, false);
		} catch (SQLException exception) {
			throw new ReferencesDAOSysException("SQLException: " + exception.getMessage());
		} finally {
			closeAll(null, statement, resultSet);
		}
	}

	//populateRow:
	protected Object populateRow(ResultSet rs, int numberOfColumns) throws ReferencesDAOSysException {
		try {
			Object[] values = new Object[numberOfColumns];
			// Read values for current row and save
			// them in the values array
			for (int i = 0; i < numberOfColumns; i++) {
				Object columnValue = rs.getObject(i + 1);
				values[i] = columnValue;
			}
			return values;
		} catch (SQLException e) {
			throw new ReferencesDAOSysException("SQLException: " + e.getMessage());
		}
	}
}
