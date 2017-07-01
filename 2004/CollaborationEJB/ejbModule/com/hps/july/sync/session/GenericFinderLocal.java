package com.hps.july.sync.session;
import com.hps.july.sync.model.Page;
/**
 * Local interface for Enterprise Bean: GenericFinder
 */
public interface GenericFinderLocal extends javax.ejb.EJBLocalObject {
	//бизнес методы:
	/**
	 * @param xml_get_pagem
	 * @param parameterValues
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return Page
	 */
	public Page findPage(String xml_get_pagem, String[] parameterValues, int start, int count, String orderBy);
}
