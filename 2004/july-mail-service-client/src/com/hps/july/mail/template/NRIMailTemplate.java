/*
 *  $Id: NRIMailTemplate.java,v 1.2 2007/06/02 12:33:14 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.mail.template;

/**
 * 
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/06/02 12:33:14 $
 * Date: 04.04.2006
 * Time: 14:50:25
 */
public abstract class NRIMailTemplate extends AbstractMailTemplate {

    public static String FROM_NRI_TECHNICAL_SUPPORT_ADDRESS =
        "\"NRI Technical Support\"<support@nri-application.vimpelcom.ru>";

    public static String FROM_NRI_TECHNICAL_SUPPORT_ADDRESS_TEST =
        "\"NRI Technical Support (test)\"<support@nri-database.vimpelcom.ru>";


}
