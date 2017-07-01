package com.hps.july.sync.utility;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;

/**
 * @author Shafigullin Ildar
 * 
 */
public class HomeFactory {

	static private HomeFactory homefactory = new HomeFactory();
	private InitialContext initialContext = null;

	protected HomeFactory() {
		super();
		try {
			initialContext = new InitialContext();
		} catch (NamingException namingException) {
			namingException.printStackTrace();
		}
	}

	static public HomeFactory singleton() {
		return homefactory;
	}

	public Object getHome(String ejbRef) throws NamingException {
		if (initialContext != null) {
			Object nsObject = initialContext.lookup(new StringBuffer("java:comp/env/").append(ejbRef).toString());
			System.out.println("class=" + nsObject.getClass());
			if (nsObject instanceof EJBLocalHome) {
				System.out.println("ejbRef " + ejbRef + " is a local reference.");
				return nsObject;
			} else {
				EJBHome ejbHome =
					(EJBHome) javax.rmi.PortableRemoteObject.narrow((org.omg.CORBA.Object) nsObject, EJBHome.class);
				System.out.println("ejbRef " + ejbRef + " is a remote reference.");
				return ejbHome;
			}
		} else {
			throw new NamingException("HomeFactory: no InitialContext");
		}
	}

	public Object getJMS(String jmsRef) throws NamingException {
		if (initialContext != null) {
			Object nsObject = initialContext.lookup(new StringBuffer("java:comp/env/").append(jmsRef).toString());
			System.out.println("class=" + nsObject.getClass());
			return nsObject;
		} else {
			throw new NamingException("HomeFactory: no InitialContext");
		}
	}

}
