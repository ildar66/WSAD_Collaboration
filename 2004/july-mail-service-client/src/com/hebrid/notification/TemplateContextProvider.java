/*
 *  $Id: TemplateContextProvider.java,v 1.1 2007/02/21 17:39:11 mmorev Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hebrid.notification;

import java.util.Map;

/**
 * Интерфейс реализующий метод получающий требуемые параметры для заполнения
 * темплейта отправляемого письма
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/02/21 17:39:11 $
 */
public interface TemplateContextProvider {

    /**
     * Метод возвращает таблицу параметров требуемых для наполния
     * тела письма
     * @param initParams 
     * @return таблицу необходимых параметров параметров
     */
    public Map findContext(Map initParams);
}
