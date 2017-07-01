package com.hps.july.sync.client;

import com.hps.july.core.Collaboration;
import com.hps.july.core.DB;
import com.hps.july.core.Query;

import java.util.Properties;
/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public abstract class AbstractFactory {
	//Группы данных для Collaboration:
	public final static int APP_NFS = 1;
	public final static int APP_DelibashMSAccess = 2;
	public final static int APP_BeliakovDBase = 3;
	public final static int APP_NRI = 4;
	public final static int APP_KozlovzevInterbase = 5;
	public final static int APP_BichkovMSAccess = 6;
	public final static int APP_GalchenkovMSSQL = 7;
	public final static int APP_CALCPROLET = 8;
	public final static int APP_CALCPHOTOS = 9;
	public final static int APP_MOLKOM = 10;
	public final static int APP_TERRABYTE = 11;
	public final static int APP_OSS = 12;
	public final static int APP_TRAILCOM = 13;
	public final static int APP_Optimize = 14;
	public final static int APP_Plan = 15;
	public final static int APP_NFSResources = 16;
	public final static int APP_EQUIPMENT = 17;
	public final static int APP_BEEPLAN = 18;
	public final static int APP_NRI_TN_DATACOMM = 19;
    public final static int APP_KLADR = 20;
    public final static int APP_ARENDA = 21;

	public static AbstractFactory getFactory(int whichFactory) {
		switch (whichFactory) {
			case APP_NFS :
				return new FactoryNFS();
			case APP_NFSResources :
				return new FactoryNFSResources();
			case APP_DelibashMSAccess :
				return new FactoryDelibashMSAccess();
			case APP_BeliakovDBase :
				return new FactoryBeliakovDBase();
			case APP_NRI :
				return new FactoryNRI();
			case APP_KozlovzevInterbase :
				return new FactoryKozlovzevInterbase();
			case APP_BichkovMSAccess :
				return new FactoryBichkovMSAccess();
			case APP_GalchenkovMSSQL :
				return new FactoryGalchenkovMSSQL();
			case APP_CALCPROLET :
				return new FactoryCalcProlet();
			case APP_CALCPHOTOS :
				return new FactoryPhotos();
			case APP_MOLKOM :
				return new FactoryMolkom();
			case APP_TERRABYTE :
				return new FactoryTerrabytePhotos();
			case APP_OSS :
				return new FactoryOSS();
			case APP_TRAILCOM:
				return new FactoryTrailcom();
			case APP_Optimize :
				return new FactoryOptimize();
			case APP_Plan :
				return new FactoryPlan();
			case APP_EQUIPMENT :
				return new FactoryEquipment();
			case APP_BEEPLAN :
				return new FactoryBeePlanMSSQL();
			case APP_NRI_TN_DATACOMM :
				return new FactoryNRITNDatacomm();
            case APP_KLADR :
                return new FactoryKladr();
            case APP_ARENDA :
                return new FactoryArenda();
            default :
				return null;
		}
	}

	public abstract Collaboration getAdapter(Query qry, DB logDB, Properties prop);

}
