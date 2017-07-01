package com.hps.july.rts.task.object;

import com.hps.april.common.object.ExtensibleObject;

import java.sql.Timestamp;

/**
 * Класс описывающий таблицу rts_task содержащую задания на выполнения для модуля
 * 'Заявки на расширение региональной ТС'
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 15.06.2006
 * Time: 12:06:55
 */
public class RTSTask extends ExtensibleObject {

    public final static String STATUS_READY = "R";
    public final static String STATUS_USED = "U";
    public final static String STATUS_DELETED = "D";

    private Integer requestId;
    private String requestNumber;
    private String requestType;
    private Integer taskType;
    private String status;
    private Timestamp created;
    private Timestamp lastSending;
	private String host;

	public RTSTask() {
        this.requestNumber = null;
        this.taskType = null;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

	public Timestamp getLastSending() {
		return lastSending;
	}

	public void setLastSending(Timestamp lastSending) {
		this.lastSending = lastSending;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
