/*
 *  $Id: CheckLeaseProlongateProcessor.java,v 1.5 2007/06/23 12:21:26 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.terrabyte.imp.processor.arenda;

import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;
import com.hps.july.terrabyte.imp.Info;
import com.hps.july.terrabyte.imp.mail.template.arenda.CheckLeaseProlongateTemplate;
import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.command.arenda.CheckLeaseProlongateCommand;
import com.hps.july.terrabyte.imp.processor.AbstractProcessor;
import com.hps.july.mail.JulyMailServiceFactory;
import com.hps.july.mail.service.JulyMailService;
import com.hps.april.auth.object.AuthInfoImpl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.text.Position;

import org.apache.log4j.Logger;

/**
 * Процессор проверки необходимости пролонгации договоров
 *
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.5 $ $Date: 2007/06/23 12:21:26 $
 */
public class CheckLeaseProlongateProcessor extends AbstractProcessor {

	private Command command;
	private static final String UNKNOWN = "не указана";
	private Logger logger = Logger.getLogger(CheckLeaseProlongateProcessor.class); 

	public CheckLeaseProlongateProcessor() {
		this.command = null;
	}

	public void execute(Command command) throws LoaderException, BaseException {
		this.command = command;
		Integer days = (Integer)command.getParameter(CheckLeaseProlongateCommand.COUNT_DAY_WARNING);
		if(days == null) {
			throw new BaseException("Cannot get parameter 'AddDayWarning'");
		}
		checkLeaseProlongateDate(days);
	}

	/**
	 * create procedure repWarningProlong(
			-- Процедура подготавливает данные для отчета
			-- "Отчет о необходимости продления договоров"
			  iDate date,             -- дата, начиная с которой надо продлять договор
			  iWorker  integer default 0,           -- ответсвенный экономист
			  iFilial integer  default 0
			) returning
		1	date,      --Дата, до которой предупреждать   DATEWarning
		2	char(80),  -- Ответ экономист
		3	integer,   -- gsm
		4	integer,   -- damps
		5	char(80),  -- наименование позиции            Position
		6	char(30),  --номер договора                   NumberContract
		7	date,      -- дата договора                   DateContract
		8	char(80),  -- заказчик                        Customer
		9	char(80),  -- исполнитель                     Executor
		10	date,      -- начало договора                 DateStartContract
		11	date,       -- окончание договора             DateEndContract
		12	char(80),    -- ответственный поисковик за договор
		13	char(80),   -- услуга
		14	decimal(15,2),  -- годовая стоимость
		15	char(50),       -- валюта
		16	decimal(15,2),  -- годовая стоимость в долларах
		17	char(50),       -- состояние
		18	char(30),        -- тип продления
		19	varchar(50),      -- email ответственный поисковик за договор
		20	varchar(50)      -- email ответственный экономист за договор

	 * @param days
	 * @throws LoaderException
	 */
	protected void checkLeaseProlongateDate(Integer days) throws LoaderException, BaseException {
		Throwable errorT = null;
		Connection connection = command.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		File eof = null;
		File file = null;
		boolean error = false;
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - days.intValue());
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		try {
			pstmt = connection.prepareStatement("execute procedure repWarningProlong(?, 0, 0)");
			pstmt.clearParameters();
			pstmt.setDate(1, new java.sql.Date(calendar.getTimeInMillis()));
			rs = pstmt.executeQuery();
			boolean first = true;
			long count = 0;
			while(rs.next()) {

				HashMap params = new HashMap();
				
				String responsibleEconomist = rs.getString(2);
				if(rs.wasNull()) {
					responsibleEconomist = null;
				} else {
					params.put("responsibleEconomist", responsibleEconomist);
				}

				String positionName = rs.getString(5);
				if(rs.wasNull()) {
					positionName = null;
				} else {
					int gsmId = rs.getInt(3);
					if(!rs.wasNull()) {
						positionName += (" D-" + gsmId); 
					}
					int dampsId = rs.getInt(4);
					if(!rs.wasNull()) {
						positionName += (" A-" + dampsId); 
					}
					params.put("positionName", positionName);
				}

				String numberContract = rs.getString(6);
				if(rs.wasNull()) {
					numberContract = null;
				} else {
					params.put("numberContract", numberContract);
				}

				java.sql.Date dateContract = rs.getDate(7);
				if(rs.wasNull()) {
					dateContract = null;
				} else {
					params.put("dateContract", sdf.format(dateContract));
				}

				String customer = rs.getString(8);
				if(rs.wasNull()) {
					customer = null;
				} else {
					params.put("customer", customer);
				}

				String executor = rs.getString(9);
				if(rs.wasNull()) {
					executor = null;
				} else {
					params.put("executor", executor);
				}

				java.sql.Date dateStartContract = rs.getDate(10);
				if(rs.wasNull()) {
					dateStartContract = null;
				} else {
					params.put("dateStartContract", sdf.format(dateStartContract));
				}

				java.sql.Date dateEndContract = rs.getDate(11);
				if(rs.wasNull()) {
					dateEndContract = null;
				} else {
					params.put("dateEndContract", sdf.format(dateEndContract));
					params.put("dateEndContract_", dateEndContract);
				}

				String responsibleHunter = rs.getString(12);
				if(rs.wasNull()) {
					responsibleHunter = null;
				} else {
					params.put("responsibleHunter", responsibleHunter);
				}

				String service = rs.getString(13);
				if(rs.wasNull()) {
					service = null;
					params.put("service", UNKNOWN);
				} else {
					params.put("service", service);
				}

				String emailHunter = rs.getString(19);
				if(rs.wasNull()) {
					emailHunter = null;
				} else {
					params.put("emailHunter", emailHunter);
				}

				String emailEconomist = rs.getString(20);
				if(rs.wasNull()) {
					service = null;
				} else {
					params.put("emailEconomist", emailEconomist);
				}

				//check
				if(emailHunter == null) {
					String msg = numberContract + " - у ответст за поиск ["+responsibleHunter+"] отсутствует эл адрес ";
					addInfo(new Info(Info.ERROR, msg));
					continue;
				}

				if(emailEconomist == null) {
					String msg = numberContract + " - у ответст экономиста ["+responsibleEconomist+"] отсутствует эл адрес ";
					addInfo(new Info(Info.ERROR, msg));
					continue;
				}
				count++;
				sendMail(params);
				//System.out.println("count="+ count);	
			}
		} catch (SQLException e) {
			String msg = "Database error while creating report , " + e.toString();
			addInfo(new Info(Info.ERROR, msg));
			logger.error(msg, e);
			throw new BaseException(e.toString() + " code: " + e.getErrorCode() );
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "Unexpected error while creating report , " + e.toString();
			addInfo(new Info(Info.ERROR, msg));
			logger.error(msg, e);
			throw new BaseException(msg);
		} finally {
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(pstmt != null) try { pstmt.close(); } catch (Exception e) {}
			if(connection != null) try { connection.close(); } catch (Exception e) {}
		}
	}

	private void sendMail(HashMap params) {
		JulyMailService service = JulyMailServiceFactory.createHttpMailService();
		String subject = "Подходит время пролонгации договора № " + params.get("numberContract") + " по позиции " + params.get("positionName");
		String attention = "Подходит время пролонгации договора № " + params.get("numberContract");
		Date dateEndContract = (Date)params.get("dateEndContract_");
		if(dateEndContract != null && (dateEndContract.getTime() < System.currentTimeMillis())) {
			subject = "Время пролонгации договора № " + params.get("numberContract") + " по позиции " + params.get("positionName") + " просрочено";
			attention = "Время пролонгации договора № " + params.get("numberContract") + " просрочено";
		}
		logger.info("Sending mail with subject ["+subject+"]");
		params.put("subject", subject);
		params.put("attention", attention);
		CheckLeaseProlongateTemplate template = new CheckLeaseProlongateTemplate(null, CheckLeaseProlongateTemplate.class.getName(), subject, params);
		AuthInfoImpl authInfo = new AuthInfoImpl("vad");
		authInfo.setOperatorId(new Integer(604));
		authInfo.setPersonId(new Integer(22173));
		service.sendBaseMail(authInfo, template, "collaboration.arenda.leaseProlongate");
	}
}