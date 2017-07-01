/*
 * Created on 19.05.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.nri.adapters;

import com.hps.beeline.imageLoader.ImageProcessor;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.QueryProcessing;
import com.hps.july.core.DefaultCollaboration;

import java.util.Properties;

/**
 * @author Vadim Glushkov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImageShrinkerAdapter extends DefaultCollaboration {

	Properties prop = null;
	/**
	 * @param targerDB
	 * @param logDB
	 * @param prop
	 */
	public ImageShrinkerAdapter(DB targerDB, DB logDB, Properties prop) {
		super(targerDB, null, "_SHRINKPHOTOBYID", null, null, null, logDB);
		this.prop = prop;
	}

	/* (non-Javadoc)
	 * @see com.hps.july.core.DefaultCollaboration#doBeforeTask(com.hps.july.core.Query)
	 */
	protected boolean doBeforeTask(Query qry) {
		//Вызов сторонней процедуры:
		Integer photoId = null;
		try {
			photoId = new Integer((String)qry.getParameter("photoId"));
		} catch (Exception e) {}
		if(photoId == null) {
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Parameter 'photoId' is null");
			return false;
		}

		try {
			System.out.println("Вызов сторонней процедуры ImageProcessor.shrinkPhoto :");
			ImageProcessor.shrinkPhoto(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), photoId);
			System.out.println("Окончание вызова сторонней процедуры ImageProcessor.shrinkPhoto :");
			return true;
		} catch (Exception e) {
			System.out.println("ImageProcessorAdapter.doBeforeTask  :Вызов сторонней процедуры ImageProcessor.shrinkPhoto ERROR: " + e.getMessage());
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Unexpected exception while shrinking image with id ["+photoId+"], " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}


}
