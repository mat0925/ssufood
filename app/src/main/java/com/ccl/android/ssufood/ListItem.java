package com.ccl.android.ssufood;

// 리스트뷰 전용 클래스얌 (건들지마루요)
public class ListItem {
    private String SID;
    private int resId;
    ListItem(String SID){
        this.resId = R.drawable.ticket;
        this.SID = SID;
    }

    String getSID(){return SID;}
    void setSID(String SID){this.SID = SID;}
    void setResId(int resId){this.resId = resId;}
}
