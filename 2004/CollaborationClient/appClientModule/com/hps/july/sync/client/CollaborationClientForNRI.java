package com.hps.july.sync.client;

import com.hps.july.core.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @author Shafigullin Ildar
 *
 * клиентский класс для реализации Алгоритм переноса данных из БД-source в БД-target.
 */
//TODO перенести в другой пакет
public class CollaborationClientForNRI {

	private Logger log = Logger.getLogger(CollaborationClientForNRI.class);

	// Интервал между опросами таблицы запросов (в миллисекундах)
	public static final long SERVICE_TIME = 5 * 1000;
	public static final long SLEEP_TIME = 10 * 1000;
	public static final SimpleDateFormat dateFormater = new java.text.SimpleDateFormat("dd.MM.yyyy");

	public static final String CONTEXT_CONFIG_LOCATION = "collaborationApplicationContext.xml";
	//конеккст бинов
	private ApplicationContext springAppContext;

	/**
     *  Основной метод
	 *
	 *  Процедура тестирования:
	 *  delete from informix.join_db_nriupdate
	 *  insert into informix.join_db_query values(_, 1, 'R', current, null, null, "ALLUSERS", null, null)
	 *  insert into informix.join_db_query values(_, 2, 'R', current, null, null, "ALLPOSITIONS", null, null)
	 *  insert into informix.join_db_query values(_, 3, 'R', current, null, null, "ALLALLBS", null, null)
	 *  insert into join_db_query (idquery, reqstate, posttime, starttime, finishtime, reqtype, selstartdate, selenddate, idapp) values (1017, "R", current, null, null, "ALLRATES", today-4, today, 1)
	 */
	public void process() throws IOException {
		System.out.println("Starting CollaborationClientForNRI");
		Properties prop = null;
		try {
			prop = readSyncProperties("sync.cfg");
		} catch (IOException fnfe) {
			// prop = readSyncProperties("C:/workspases/WSAD_Collaboration/CollaborationClient/appClientModule/sync.cfg");
			// prop = readSyncProperties("D:/WSADA51/workspacework/CollaborationClient/appClientModule/sync.cfg");
			prop = readSyncProperties("C:\\java\\project\\collaboration\\bin\\release\\sync.cfg");
			//prop = readSyncProperties("C:/IBM/wsappdev51/workspace/CollaborationClient/appClientModule/sync.cfg");
		}

		try {
			springAppContext = new ClassPathXmlApplicationContext("classpath*:" + CONTEXT_CONFIG_LOCATION);
		} catch(RuntimeException e) {
			log.error("Error creating spring application Context", e);
		}

		DB logDB = new DB(prop, "NRI");

		QueryProcessing.clearQueries(logDB);
		while (true) {
			List qryList = QueryProcessing.findListQuery(logDB);
			Iterator iter = qryList.iterator();
			while (iter.hasNext()) {
				Query qry = (Query) iter.next();

				boolean result = QueryProcessing.isExistRunProcessForIdApp(logDB, qry.getIdApp());
				if (!result) {
					Collaboration adaptor = null;
					AbstractFactory factory = AbstractFactory.getFactory(qry.getIdApp());
					//System.out.println("factory.getClass()=" + factory.getClass());//temp
					if (factory != null) {
						adaptor = factory.getAdapter(qry, logDB, prop);
					}
					if (adaptor != null) {
						//System.err.println("adaptor.getClass()=" + adaptor.getClass());//temp
						System.out.println("------------------------------------------------------------------------"); //temp
						System.out.println("Thread for qry.ID=" + qry + " started !"); //temp
						System.out.println("START TIME=" + dateFormater.format(new java.util.Date(System.currentTimeMillis()))); //temp
						if(adaptor instanceof SpringContextSupport) {
							//injection ApplicationContext to CollaborationAdaptor
							((SpringContextSupport)adaptor).setSpringApplicationContext(springAppContext);
						}
						new Thread(new RunnableProcess(qry, adaptor)).start();

						try {
							Thread.sleep(SERVICE_TIME);
						} catch (InterruptedException e) {
							System.out.println("Interrupt received - exiting");
							break;
						}

					} else {
						QueryProcessing.startProcessing(logDB, qry);
						QueryProcessing.addLogMessage(logDB, qry, QueryProcessing.MSG_ERROR, "Неизвестный тип запроса ["+qry.getReqType()+"] - запрос игнорируется");
						QueryProcessing.finishError(logDB, qry);
					}
				}
			}
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				System.out.println("Interrupt received - exiting");
				break;
			}
		}
	}
	/**
	 * Перенесен в метод run()RunnableProcess
	 * @param qry
	 * @param adaptor

		private void processRequest(Query qry, Collaboration adaptor) {
			if (qry.isDelQuery())
				adaptor.findDeletedInSourseDeleteInTarget(qry);
			else if (qry.isAllQuery())
				adaptor.findChangesAndUpdate(qry);
			else
				adaptor.doTask(qry);
		}
	 */
	public static Properties readSyncProperties(String fileName) throws IOException, FileNotFoundException {
		InputStream inStream = null;
		try {
			File fl = new File(fileName);
			//deprecated
			inStream = new FileInputStream(fl);
			//inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			return readSyncProperties(inStream);
		} catch (FileNotFoundException e) {
			System.out.println("Config file " + fileName + " not found");
			throw e;
		} catch (IOException e) {
			System.out.println("IO Error while load sync file");
			throw e;
		} finally {
			if(inStream != null) {
				try {
					inStream.close();
				} catch(IOException e) {
					System.out.println("Couldn't close input stream, cause " + e.toString());
				}
			}
		}
	}

	public static Properties readSyncProperties(InputStream is) throws IOException, FileNotFoundException {
		Properties prop = new Properties();
		try {
			prop.load(is);
			prop.list(System.out);
			return prop;
		} catch (IOException ioExc) {
			System.out.println("Cannot Load properties from file !");
			throw ioExc;
		} catch (Exception ex) {
			System.out.println("Unhandled exception while load properties from file ! " + ex.getMessage());
			throw new IOException("Unhandled exception ");
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {
			}
		}
	}

	public static void main(String[] args) throws IOException {
        try {
            System.out.println("11111111111111111111111111111111111111111111111111111111111111111111");
            new CollaborationClientForNRI().process();
            System.out.println("11111111111111111111111111111111111111111111111111111111111111111111");
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}
}
