package com.ccl.android.ssufood;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// 리스트뷰 전용 클래스얌 (건들지마루요)
public class ListItemView extends LinearLayout {
    TextView textView;
    ImageView imageView;
    public ListItemView(Context context) {
        super(context);
        init(context);
    }
    public ListItemView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ticket_item, this, true);

        imageView = (ImageView) findViewById(R.id.ticket_icon);
        textView = (TextView) findViewById(R.id.serialText);
    }

    void setSID(String SID)
    {
        textView.setText(SID);
    }

}
