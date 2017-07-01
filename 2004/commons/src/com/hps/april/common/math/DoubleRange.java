/*
 *  $Id: DoubleRange.java,v 1.1 2007/01/25 17:16:20 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.april.common.math;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/01/25 17:16:20 $
 */
public class DoubleRange {

    private org.apache.commons.lang.math.DoubleRange range = null;
    private BigDecimal minObject;
    private BigDecimal maxObject;

    public DoubleRange() {
    }

    public BigDecimal getMinObject() {
        return minObject;
    }

    public void setMinObject(BigDecimal minObject) {
        this.minObject = minObject;
        set();
    }

    public BigDecimal getMaxObject() {
        return maxObject;
    }

    public void setMaxObject(BigDecimal maxObject) {
        this.maxObject = maxObject;
        set();
    }

    private void set() {
        if(minObject != null && maxObject != null)
            range = new org.apache.commons.lang.math.DoubleRange(minObject.doubleValue(), maxObject.doubleValue());
    }


}
