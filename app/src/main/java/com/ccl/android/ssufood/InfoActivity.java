package com.ccl.android.ssufood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    TextView userSID;
    TextView Tickets;
    TextView Points;
    String ticketList;
    int count = 0;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        userSID = (TextView)findViewById(R.id.userSID);
        Tickets = (TextView)findViewById(R.id.Tickets);
        Points = (TextView)findViewById(R.id.Points);

        userSID.setText(((IDClass)this.getApplication()).getData().toString());
        Points.setText("포인트 : "+((IDClass)this.getApplication()).getPoint().toString());
        Tickets.setText("식권 : "+count);

        // 백버튼이얌
        ImageButton backButton = (ImageButton)findViewById(R.id.gobackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //ListView listView = (ListView) findViewById(R.id.listView);

        Intent ticketIntent = new Intent(getApplicationContext(), SocketActivity.class);
        String ticketQuery = "";
        ticketQuery += "3";
        ticketQuery += ((IDClass) this.getApplication()).getData().toString();
        ticketIntent.putExtra("key", ticketQuery);
        startActivityForResult(ticketIntent, 0);





        // 리스트뷰에 아이템 추가 (반복문으로 변경 해야댐)
        // 인자로 쿠폰 번호 넘기면 댐

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        ticketList = data.getStringExtra("key");
        char[] chs = ticketList.toCharArray();
        Toast toast = Toast.makeText(getApplicationContext(),ticketList,Toast.LENGTH_LONG);
        toast.show();

        //Log.v("**********1st**********",chs[1]+"");
        /*
        *  Intent btnIntent1 = new Intent(getApplicationContext(),MainActivity.class );
                btnIntent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(btnIntent1);
        * */
        switch (requestCode)
        {
            case 0:
                Log.v("****리턴값****",ticketList);
                if(ticketList.equals("\0")) {
                    Tickets.setText("식권 : 0");
                }
                else{
                    final ListAdapter adapter = new ListAdapter();

                        for(int i=1; i<ticketList.length(); i+=6)
                        {
                            //adapter.addItem(new ListItem(ticketList.substring(i,i+6)));
                            adapter.addItem(new ListItem(ticketList.substring(i,i+6)));
                            count++;
                        }
                        Tickets.setText("식권 : "+count);
                        ListView listView = (ListView) findViewById(R.id.listView);
                        listView.setAdapter(adapter);
                }
                break;
            default:
                break;

        }

    }
    // 리스트뷰 어댑터얌 (건들필요 음슴)
    private class ListAdapter extends BaseAdapter {
        private ArrayList<ListItem> items = new ArrayList();
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListItemView view = (ListItemView) convertView;

            if(convertView == null)
                view = new ListItemView(getApplicationContext());

            ListItem item = items.get(position);
            view.setSID(item.getSID());
            return view;
        }

        void addItem(ListItem item) { items.add(item);}
    }
}

