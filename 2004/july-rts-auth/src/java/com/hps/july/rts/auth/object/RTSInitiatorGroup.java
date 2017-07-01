package com.hps.july.rts.auth.object;

import com.hps.july.rts.auth.object.RTSGroup;

public class RTSInitiatorGroup extends RTSGroup {
    private static final long serialVersionUID = 1L;

    private Integer initiatorCode;

    public Integer getInitiatorCode() {
        return initiatorCode;
    }

    public void setInitiatorCode(Integer initiatorCode) {
        this.initiatorCode = initiatorCode;
    }
}
