package com.hps.july.rts.task;

import com.hps.april.auth.object.AuthInfoImpl;
import com.hps.july.rts.SystemException;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * ����� ��������� ��� ������ ���������� ����������� �� ������������ ��
 * ���� ������ �� ����������� � ������� ������� �������������� ����������� ��������
 * "������������ ������ �� ����������� �������� �� � �� ����������",
 * �������� ��������� ������� ���������� ��
 * ����������� �����.
 *
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 30.05.2006
 * Time: 18:05:07
 */
public class ConsiderInitialRequestByKMTask extends RTSTimerTask {

    protected Logger logger = Logger.getLogger(ConsiderInitialRequestByKMTask.class);
    //protected long DEFAULT_PERIOD = 1000L * 60L * 60L * 24L;
    protected long DEFAULT_PERIOD = 1000L * 60L * 5L;

    public ConsiderInitialRequestByKMTask() {
        //setFirstTime(new Date(System.currentTimeMillis() + DEFAULT_PERIOD));
        //setPeriod(DEFAULT_PERIOD);
    }

    public ConsiderInitialRequestByKMTask(String requestNumber, Integer requestId, String requestType) {
        this.requestNumber = requestNumber;
        this.requestId = requestId;
        this.requestType = requestType;
        //����� ������� ����� �����
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
//        getLogger().debug("A-->["+calendar.getTime()+"] ");
//        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
//        getLogger().debug("B-->["+calendar.getTime()+"] ");
        //getLogger().debug("A-->["+calendar.getTime()+"] ");
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 1);
        //getLogger().debug("B-->["+calendar.getTime()+"] ");
        //setFirstTime(calendar.getTime());
        //������ �����
        //setPeriod(DEFAULT_PERIOD);
    }

    /**
     * ����� ��������� ��� ������ ���������� ����������� �� ������������ ��
     * � ������ �� ���� ������ �� ���������� � ������� ���
     * ��� �� �������� ���������� � ������������� ��������
     * @throws SystemException
     */
    public void process() throws SystemException {
        boolean needCancel = false;
        try {
            //�������� ������� !
			AuthInfoImpl authInfo = new AuthInfoImpl("automat");
            authInfo.setPersonId(new Integer(100001));
            needCancel = false;//getTaskService().executeTaskConsiderInitiatorRequestByKM(authInfo, requestId, getCreated());
        } catch(Exception e) {
            //logger.error("Cannot execute task 'executeTaskConsiderInitiatorRequestByKM' with id ["+requestId+"]");
        }
        if(needCancel) {
           //cancel();
           //fireCancelTask(this);
        }
    }

}
