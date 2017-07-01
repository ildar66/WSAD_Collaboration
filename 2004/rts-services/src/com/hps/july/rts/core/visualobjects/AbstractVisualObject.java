package com.hps.july.rts.core.visualobjects;

import com.hps.april.common.object.ValueObject;

/**
 * RTS ("������ �� ���������� ������������ ��")
 * 
 *  @author abaykov
 *  Created on 29.12.2005
 */
public abstract class AbstractVisualObject
    extends ValueObject implements Comparable {

    private String idvo;

    public AbstractVisualObject()	{
        generateId();
    }

    protected void generateId()	{
        setIdvo("");  // ������� Id ���������
    }

    public String getIdVO(){
        return idvo;
    }

    public void setIdvo(String id){
        this.idvo = id;
    }

    public boolean equals(Object o){
        try
        {
            ValueObject vo = (ValueObject) o;

            if (vo.getId().equals(getIdVO()))
            {
                return true;
            }
            return false;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public int compareTo(Object o){
        // ������� �����
        return 0;
    }
}
