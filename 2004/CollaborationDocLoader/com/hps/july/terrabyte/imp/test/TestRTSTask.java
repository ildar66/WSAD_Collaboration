/*
 *  $Id: TestRTSTask.java,v 1.3 2007/06/15 17:12:51 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.terrabyte.imp.test;

import org.springframework.beans.BeansException;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.3 $ $Date: 2007/06/15 17:12:51 $
 */
public class TestRTSTask {
	public static final String CONTEXT_CONFIG_LOCATION = "collaborationApplicationContext.xml";

	public static void main(String[] args) {
		try {
//			String dir = "C:\\6\\2\\";
//			File mainDir = new File(dir);
//			if(mainDir.isDirectory()) {
//				File [] list = mainDir.listFiles();
//				for(int i = 0; i < list.length; i++) {
//					File file = list[i];
//					if(file.isFile()) {
//						long lastModified = file.lastModified();
//						//System.out.println("c=" + new Date(lastModified));
//						GregorianCalendar calendar = new GregorianCalendar();
//						calendar.setTimeInMillis(System.currentTimeMillis());
//						calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
//						long currentDate = calendar.getTimeInMillis();
//						//System.out.println("cd=" + new Date(currentDate));
//						if(currentDate > lastModified) {
//							System.out.println("["+file.getName()+"] need del");
//						}
//					}
//
//				}
//			} else {
//				System.out.println("Указаный путь ["+dir+"] не является директорией ");
//			}

/*
			//ApplicationContext context = new FileSystemXmlApplicationContext(CONTEXT_CONFIG_LOCATION);
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:" + CONTEXT_CONFIG_LOCATION);
			//RTSTaskModule module = (RTSTaskModule)context.getBean(RTSTaskModule.SERVICE_NAME);
//			BeanFactoryLocator factory = DefaultLocatorFactory.getInstance();
//			BeanFactoryReference bf = factory.useBeanFactory("com.hps.july.rts-task.factory");
//			AuthService module = (AuthService)bf.getFactory().getBean(AuthService.SERVICE_NAME);
			AuthService autSrevice= (AuthService)context.getBean(AuthService.SERVICE_NAME);
			autSrevice.getSystemAuthInfo();
			RTSTaskModule module = (RTSTaskModule)context.getBean(RTSTaskModule.SERVICE_NAME);
			ArrayList list = new ArrayList();
			module.processReadyTasks(list);
			System.out.println("->["+list.size()+"] ");
			for(int i = 0; i < list.size(); i++) {
				RTSTaskInfo info = (RTSTaskInfo)list.get(i);
				System.out.println("->["+info.getFlag()+"] ["+info.getComment()+"]");
			}
*/

		} catch(BeansException e) {
			e.printStackTrace();
			Throwable t = e.getCause();
			if(t != null) {
				t.printStackTrace();
			}
		}
	}
}
