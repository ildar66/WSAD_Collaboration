package com.hps.july;

import com.hps.july.core.Query;
import com.hps.july.util.DateConverter;
import junit.framework.TestCase;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public abstract class AbstractTest extends TestCase {
//    private static final String SYNC_CFG_PATH = "c:\\java\\project\\nri_tn_datacomm_cvs\\CollaborationClient\\appClientModule\\sync.cfg";
    private static final String SYNC_CFG_PATH = "/usr/local/src/collaboration/collaboration/CollaborationClient/appClientModule/sync.cfg";
    protected Properties properties;

    protected void setUp() throws Exception {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(SYNC_CFG_PATH));

            ConvertUtils.register(new DateConverter(), Date.class);
            PropertyConfigurator.configure("/usr/local/src/collaboration/collaboration/CollaborationClient/appClientModule/log4j.properties");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected Query getTaskQuery() {
        return new Query("TASK_TEST");
    }
}
