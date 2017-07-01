/*
 *  $Id: RTSMailTemplate.java,v 1.2 2007/04/15 16:54:50 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.mail.template;

import com.hps.july.mail.template.SimpleHTMLMailTemplate;
//import com.hps.july.util.AppUtils;

import java.util.List;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @author Dimitry Krivenko
 * @version $Revision: 1.2 $ $Date: 2007/04/15 16:54:50 $
 */
public abstract class RTSMailTemplate extends SimpleHTMLMailTemplate {

    public String getFrom() {
        //String rtsMailTest = AppUtils.getNamedValueString("RTS_TESTING");
        boolean rtsMailTestBool = false;//(rtsMailTest != null && !rtsMailTest.equals(""));
        return (rtsMailTestBool)?FROM_NRI_TECHNICAL_SUPPORT_ADDRESS_TEST:FROM_NRI_TECHNICAL_SUPPORT_ADDRESS;
    }

    public List getAttachmentList() {
        return null;
    }
}
