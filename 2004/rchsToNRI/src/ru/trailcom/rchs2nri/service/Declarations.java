package ru.trailcom.rchs2nri.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import ru.trailcom.rchs2nri.dao.BaseStationDao;
import ru.trailcom.rchs2nri.dao.DecisionDao;
import ru.trailcom.rchs2nri.dao.DeclarationDao;
import ru.trailcom.rchs2nri.dao.LicenseDao;
import ru.trailcom.rchs2nri.dao.jdbc.*;
import ru.trailcom.rchs2nri.domain.BaseStation;
import ru.trailcom.rchs2nri.domain.Decision;
import ru.trailcom.rchs2nri.domain.Declaration;
import ru.trailcom.rchs2nri.domain.License;
import ru.trailcom.rchs2nri.sax.DocumentHandler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.*;

public class Declarations {
	protected Log logger = LogFactory.getLog(this.getClass());
	
	private Connection connection; 
	
	private DeclarationDao declarationDao;
	private BaseStationDao baseStationDao;
	
	private DecisionDao decisionDao;
	private LicenseDao licenseDao;
	
	
	public void setConnection(Connection aConnection){
		this.connection = aConnection;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.connection);
		
		DeclarationDaoJdbcImpl declarationDaoJdbc = new DeclarationDaoJdbcImpl();
		declarationDaoJdbc.setJdbcTemplate(jdbcTemplate);
		declarationDao = declarationDaoJdbc;
		
		BaseStationDaoJdbcImpl baseStationDaoJdbcImpl = new BaseStationDaoJdbcImpl();
		baseStationDaoJdbcImpl.setJdbcTemplate(jdbcTemplate);
		baseStationDao = baseStationDaoJdbcImpl;
		
		DecisionDaoJdbcImpl decisionDaoJdbcImpl = new DecisionDaoJdbcImpl();
		decisionDaoJdbcImpl.setJdbcTemplate(jdbcTemplate);
		decisionDao = decisionDaoJdbcImpl;
		
		LicenseDaoJdbcImpl licenseDaoJdbcImpl = new LicenseDaoJdbcImpl();
		licenseDaoJdbcImpl.setJdbcTemplate(jdbcTemplate);
		licenseDao = licenseDaoJdbcImpl;
		
	}
	
	public List xmlToDeclarationList(String path) throws IOException, SAXException{
        XMLReader xr = new org.apache.xerces.parsers.SAXParser();
        // Set the ContentHandler...
        DocumentHandler documentHandler = new DocumentHandler(xr);
        xr.setContentHandler( documentHandler );
        // Parse the file...
        xr.parse( new InputSource(new FileReader( path )));

        return documentHandler.getDeclarations();
	}
	
	/**
	 * Обработка списка заявок
	 * @param declarations
	 */
	private void processDeclarationsList(List declarations, List errors){
		if(connection==null){
			throw new IllegalArgumentException("Не установленн параметр connection");
		}

        Collections.sort(declarations, new Comparator() {
            public int compare(Object o1, Object o2) {
                Declaration declaration1 = (Declaration) o1;
                Declaration declaration2 = (Declaration) o2;
                if (declaration1.getDateGRCCout() == null) {
                    return declaration2.getDateGRCCout() != null ? -1 : 0;
                }
                return declaration1.getDateGRCCout().before(declaration2.getDateGRCCout()) ? 1 : 0;
            }
        });

        for(Iterator iterator = declarations.iterator(); iterator.hasNext();){
			Declaration declaration = (Declaration)iterator.next();

            declaration = declarationDao.insertDeclaration(declaration);
			logger.info(declaration);
			if(declaration.getSeqId() != null){
				logger.info(declaration.getBaseStations());
				if(declaration.getBaseStations() != null){
					this.processBaseStationList(declaration, declaration.getBaseStations());
				}


				if(declaration.getDecisions().size()>0){
					for(Iterator iterator2 = declaration.getDecisions().iterator(); iterator2.hasNext();){

						Decision decision = (Decision)iterator2.next();
						decision.setDeclaration(declaration);
						logger.info("insert decision");
						decision = decisionDao.insertDecision(decision);

						if(decision.getId()!=null && decision.getListBaseStation()!=null){
							this.processBaseStationList(decision, decision.getListBaseStation());
						}

						if(decision.getId()!=null && decision.getLicenses().size()>0){
							processLicenseList(decision, decision.getLicenses());
						}

					}
				}
			}else{
				logger.info("Ахтунг!!");
				logger.info("Ахтунг declaration.getIdBsZaj()"+declaration.getId()+" не добавилась");
				errors.add("declaration.getIdBsZaj()"+declaration.getId()+" не добавилась");
			}
		}
	}
	
	private void setFilialIdInDeclarations(List errors){
		if(connection==null){
			throw new IllegalArgumentException("Не установленн параметр connection");
		}

        List declarations = declarationDao.getDeclarationFilialIdIsNullList();
        int declarationCount = declarations.size();
        int i=0;
        for(Iterator iterator=declarations.iterator(); iterator.hasNext(); i++){
            logger.info("Осталось " + (declarationCount - i) + " заявок");
			Declaration declaration = (Declaration)iterator.next();
			declarationDao.findFilialIdByRegionInReference(declaration);

            if(declaration.getRegionid()!=null && declaration.getRegionid().intValue()>0){
				logger.info("declaration.getRegionid(id)="+declaration.getRegionid());
				// c) заполняем поле regionid
				declarationDao.update(declaration);
				// d) 
				List baseStations = baseStationDao.getBaseStationByDeclarationStorageplaceIsNullList(declaration);
				logger.info("baseStations.size()="+baseStations.size());

                int basestationCount = baseStations.size();
                int j=0;
                for(Iterator iterator2=baseStations.iterator(); iterator2.hasNext(); j++){
                    logger.info("Осталось " + (basestationCount - j) + " базовых станций");
					BaseStation baseStation = (BaseStation)iterator2.next();
					int number = baseStation.getNumber().intValue();
					logger.info("number="+number);

                    if(baseStation.getNumber().toString().length()<5 &&  declaration.getFilialId().intValue()!=2){
						number = declaration.getFilialId().intValue()*10000 + number;
					}

                    logger.info("number6="+number);
					baseStation.setNumber5(new Integer(number));
					//ищем в nri bs по number и basestations.numgsmpermit || basestations.numdcspermit
					List storageplaces = baseStationDao.findStorageplaceByNumberAndFilialIdInNRI(declaration.getRegionid(), baseStation.getNumber(), declaration.getDiap());
					logger.info("ищем в nri bs по number и basestations.numgsmpermit || basestations.numdcspermit");
					int countFlag = storageplaces.size();//1 нашли одну бс. 0-ничего не нашли,

                    //2 - нашли больше 1 по basestations.numgsmpermit || basestations.numdcspermit
					//3 - нашли больше 1 по basestations.number
                    if(storageplaces.size()==1){
						Integer storageplace = (Integer)storageplaces.get(0);
						baseStation.setStorageplace(storageplace);	
						logger.info("нащли в NRI по по number и basestations.numgsmpermit || basestations.numdcspermit");
						logger.info("seting storageplace="+storageplace);
					}else{
						if(storageplaces.size()>1){
							countFlag = 2;
							logger.error(baseStation+" have "+storageplaces.size()+" with number="+baseStation.getNumber());
						}else{
							logger.info("ничего не нашли");
							logger.info("ищем в nri bs по number5 и basestations.number");
//							//ищем в nri bs по number5 и basestations.number
							storageplaces = baseStationDao.findStorageplaceByNumber5AndFilialIdInNRI(declaration.getRegionid(), baseStation.getNumber5(), declaration.getDiap());
							//b)
							if(storageplaces.size()==1){
								countFlag = 1;
								logger.info("нащли в NRI по по number5 и basestations.number");
								Integer storageplace = (Integer)storageplaces.get(0);
								baseStation.setStorageplace(storageplace);
							}else{
								if(storageplaces.size()>1){
									countFlag = 3;
									logger.error(baseStation+" have "+storageplaces.size()+" with number=" + baseStation.getNumber5());
								}
							}
						}
					}

                    if (!baseStationDao.existsBs2Nri(baseStation.getIdBsBd())) {
                        baseStationDao.saveInBs2Nri(baseStation, declaration.getRegionid(), new Integer(countFlag), declaration.getDiap());
                    }
				}
			}
		}
	}
	
	public void makeActualPermit(List errors){
		if(connection==null){
			throw new IllegalArgumentException("Не установленн параметр connection");
		}

        baseStationDao.makeActualPerimt(baseStationDao.getBaseStationList(), errors);
	}
	
	/**
	 * Лицензия должна быть одна
	 * @param decision
	 * @param licenses
	 */
	private void processLicenseList(Decision decision, List licenses){
		for(Iterator iterator = licenses.iterator(); iterator.hasNext();){
			License license = (License)iterator.next();
			license.setDecision(decision);
			logger.info("insert license");
			
			if(license.getIsTemporary().booleanValue()){
				license.setType(License.TYPE_TEMP);
			}else{
				license.setType(License.TYPE_NORMAL);
			}
			
			license = licenseDao.insertLicense(license);
			
			if(license.getId()!=null){
				logger.info("insert licensed BS");
				for(Iterator iterator2=license.getLicensedBaseStations().iterator();iterator2.hasNext();){
					BaseStation baseStation = (BaseStation)iterator2.next();
					logger.info("insert licensed BS "+baseStation);
					licenseDao.insertLicenseBaseStation(license.getId(), baseStation.getIdBsZaj());
				}
			}
		}
		
	}
	
	private void processBaseStationList(Declaration declaration, List baseStations){
		for(Iterator iterator = baseStations.iterator(); iterator.hasNext();){
			BaseStation baseStation = (BaseStation)iterator.next();
			baseStation = baseStationDao.insertBaseStation(baseStation);
			//logger.debug(baseStation);
			logger.debug("processBaseStationList");
			if(baseStation.getIdBsZaj()!=null){
				//logger.debug("insertDeclarationBaseStation "+baseStation.getIdBsZaj());
				declarationDao.insertDeclarationBaseStation(declaration.getSeqId(), baseStation.getIdBsZaj());
                //declarationDao.insertDeclarationBaseStation(declaration.getSeqId(), baseStation.getIdBsBd());
            }
		}
	}
	
	private void processBaseStationList(Decision decision, List baseStations){
		for(Iterator iterator = baseStations.iterator(); iterator.hasNext();){
			BaseStation baseStation = (BaseStation)iterator.next();
			//baseStation = baseStationDao.insertBaseStation(baseStation);
			//logger.debug(baseStation);
			logger.debug("processBaseStationList");
			if(baseStation.getIdBsZaj()!=null){
				//logger.debug("insertDeclarationBaseStation "+baseStation.getIdBsZaj());
				decisionDao.insertDecisionBaseStation(decision.getId(), baseStation.getIdBsZaj());
			}
		}
		
	}

	public boolean execute(Connection conn, Connection log, File file, List errors) throws IOException, SAXException {
		//milliseconds
		if(conn==null){
			throw new IllegalArgumentException("conn не может быть null");
		}
		if(log==null){
			throw new IllegalArgumentException("log не может быть null");
		}
		if(errors==null){
			throw new IllegalArgumentException("errors не может быть null");
		}

        this.setConnection(conn);
		long start = new Date().getTime();

        logger.info("start execute");
        logger.info("eeeeeeee");

        declarationDao.clearPermits(errors);
		if(errors.isEmpty()){
            this.processDeclarationsList(xmlToDeclarationList(file.getAbsolutePath()), errors);
            baseStationDao.updateStatictics();
            logger.info("processDeclarationsList finished");

            this.setFilialIdInDeclarations(errors);
            baseStationDao.updateStatictics();
			logger.info("setFilialIdInDeclarations finished");

            this.makeActualPermit(errors);
            baseStationDao.updateStatictics();
            logger.info("makeActualPermit finished");
			logger.info("end execute");

            start = new Date().getTime()-start;
		}
		logger.info("executing " + start/1000 + " secs");
		return true;
	}


    public static void main(String[] args){
		String url = "jdbc:informix-sqli://172.21.9.133:1541/july:INFORMIXSERVER=beeinf_app;DB_LOCALE=ru_RU.1251;CLIENT_LOCALE=ru_RU.1251;";
		String userlogin = "bdv";
		String password = "25yGo1yx"; //test
		
//		String url = "jdbc:informix-sqli://172.21.9.131:1541/july:INFORMIXSERVER=beeinf_app;DB_LOCALE=ru_ru.1251;CLIENT_LOCALE=ru_RU.1251;";
//		String userlogin = "trailcom";
//		String password = "fymnputv"; //war

//		String url = "jdbc:informix-sqli://172.21.9.132:1541/july:INFORMIXSERVER=beeinf_app;DB_LOCALE=ru_RU.1251;CLIENT_LOCALE=ru_RU.1251;";
//		String userlogin = "nri";
//		String password = "Y9q5s26v"; //test
        Connection connection = null;
        try{
		    Class.forName("com.informix.jdbc.IfxDriver");
			connection = DriverManager.getConnection(url,userlogin,password);

            connection.setAutoCommit(true);
            setLockModeWait(connection);

            Declarations declarations = new Declarations();

            List error = new ArrayList();
            declarations.execute(connection, connection, new File("c:\\java\\project\\bs_new.xml"), error);
        }catch (SQLException e) {
            System.out.println("e.getSQLState() = " + e.getSQLState());
            System.out.println("e.getErrorCode() = " + e.getErrorCode());
            e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally{
			try{
				connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

    private static void setLockModeWait(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("SET LOCK MODE TO WAIT 60");
        preparedStatement.execute();
        preparedStatement.close();
    } 

}