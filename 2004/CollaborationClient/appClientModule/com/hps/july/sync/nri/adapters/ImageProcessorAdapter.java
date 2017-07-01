/*
 * Created on 22.11.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.nri.adapters;

import java.util.Properties;

import com.hps.beeline.LoaderException;
import com.hps.beeline.imageLoader.ImageProcessor;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImageProcessorAdapter extends DefaultCollaboration {
	Properties prop = null;
	/**
	 * @param targerDB
	 * @param logDB
	 * @param prop
	 */
	public ImageProcessorAdapter(DB targerDB, DB logDB, Properties prop) {
		//super(targerDB, logDB);
		super(targerDB, null, "_LOADPHOTOS", null, null, null, logDB);
		this.prop = prop;
	}

	/* (non-Javadoc)
	 * @see com.hps.july.core.DefaultCollaboration#doBeforeTask(com.hps.july.core.Query)
	 */
	protected boolean doBeforeTask(Query qry) {
		//Вызов сторонней процедуры:
		String imageDirPath = prop.getProperty("imageDirPath");
		int maxSize = Integer.valueOf(prop.getProperty("maxSize")).intValue();
		try {
			System.out.println("Вызов сторонней процедуры ImageProcessor.loadPhotos :");
			ImageProcessor.loadPhotos(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), imageDirPath, maxSize);
			return true;
		} catch (LoaderException le) {
			System.out.println("ImageProcessorAdapter.doBeforeTask  :Вызов сторонней процедуры ImageProcessor.loadPhotos: LoaderException: " + le.getMessage());
			le.printStackTrace(System.out);
			return false;
		} catch (Exception e) {
			System.out.println("ImageProcessorAdapter.doBeforeTask  :Вызов сторонней процедуры ImageProcessor.loadPhotos ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}

}
