package com.ccl.android.ssufood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String fromActivity ="";
    String Point="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();//액티비티로부터 넘어온 데이터를 받을 준비
        fromActivity = intent.getStringExtra("mainkey");//액티비티로부터 넘어온 데이터를 key값으로 받음
        Toast.makeText(getApplicationContext(), "인텐트 키 : "+fromActivity,Toast.LENGTH_SHORT);
        Log.v("받은 ID",fromActivity);

        ((IDClass)this.getApplication()).setData(fromActivity);
        String hello = ((IDClass)this.getApplication()).getData().toString();

        if(((IDClass)this.getApplication()).getPoint().equals("")) {
            Intent pointIntent = new Intent(getApplicationContext(), SocketActivity.class);
            String pointQuery = "";
            pointQuery += "7";
            pointQuery += ((IDClass) this.getApplication()).getData().toString();
            pointIntent.putExtra("key", pointQuery);
            startActivityForResult(pointIntent, 0);
        }

        TextView tv1 = (TextView)findViewById(R.id.t_main_1);
        TextView tv2 = (TextView)findViewById(R.id.t_main_2);
        TextView tv3 = (TextView)findViewById(R.id.t_main_3);

        tv1.setText(hello+" 님, \n안녕하세요!");
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("\0");
        setSupportActionBar(mToolbar);

        // 정보 확인 창으로 가는 버튼이얌
        Button infoButton = (Button) findViewById(R.id.infoButton);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoIntent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(infoIntent);
            }
        });

        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent killApp = new Intent(getApplicationContext(), LoginActivity.class);
                killApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                killApp.putExtra("KILL_APP", true);
                startActivity(killApp);

        }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_button:
                //툴바의 아이콘이 할 기능 정의할 것
                //Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                // startActivity(intent);
                break;
            case R.id.toolbutton1:
                //툴바의 아이콘이 할 기능 정의할 것  Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                //                    mainIntent.putExtra("mainkey",result);
                //                    startActivityForResult(mainIntent,0);
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                intent1.putExtra("ID",fromActivity);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                break;
            case R.id.toolbutton2:
                //툴바의 아이콘이 할 기능 정의할 것
                Intent intent2 = new Intent(getApplicationContext(), BuyActivity.class);
                intent2.putExtra("ID",fromActivity);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                break;
            case R.id.toolbutton3:
                //툴바의 아이콘이 할 기능 정의할 것
                Intent intent3 = new Intent(getApplicationContext(), BuyGiftActivity.class);
                intent3.putExtra("ID",fromActivity);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                break;
            case R.id.toobutton4:
                Intent intent4 = new Intent(getApplicationContext(), PointActivity.class);
                intent4.putExtra("ID",fromActivity);
                intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent4);
                break;
    }
        return super.onOptionsItemSelected(item);

    }
    //포인트읽어왕
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        Point = data.getStringExtra("key");
        char[] chs = Point.toCharArray();
        Toast toast = Toast.makeText(getApplicationContext(),Point,Toast.LENGTH_LONG);
        toast.show();

        Log.v("**********1st**********",chs[1]+"");
        /*
        *  Intent btnIntent1 = new Intent(getApplicationContext(),MainActivity.class );
                btnIntent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(btnIntent1);
        * */
        switch (requestCode)
        {
            case 0:
                    Log.v("****리턴값****",Point);

                    ((IDClass)this.getApplication()).setPoint(Point);
                     Log.v("포인트", ((IDClass)this.getApplication()).getPoint().toString());

                break;
             default:
                 break;

        }
    }
}
