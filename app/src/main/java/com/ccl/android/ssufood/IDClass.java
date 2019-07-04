package com.ccl.android.ssufood;

import android.app.Application;

public class IDClass extends Application {
    private String ID;
    private String Point="";
    public String getData()
    {
        return ID;
    }
    public void setData(String id)
    {
        this.ID = id;
    }
    public String getPoint()
    {
        return Point;
    }
    public void setPoint(String points)
    {
        this.Point = points;
    }
}
