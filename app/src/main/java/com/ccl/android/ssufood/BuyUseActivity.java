package com.ccl.android.ssufood;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BuyUseActivity extends AppCompatActivity {
    String text;
    Spinner buySpinner;
    String ID;
    String count;
    String Point;
    int temp = 0;
    TextView tv;
    String fromActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyuse);

        ID = ((IDClass)this.getApplication()).getData().toString();

        Intent intent = getIntent();//액티비티로부터 넘어온 데이터를 받을 준비
        fromActivity = intent.getStringExtra("ID");//액티비티로부터 넘어온 데이터를 key값으로 받음
        Toast.makeText(getApplicationContext(), "인텐트 키 : "+fromActivity,Toast.LENGTH_SHORT);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("\0");
        setSupportActionBar(mToolbar);


        //스피너 객체 생성
        buySpinner = (Spinner)findViewById(R.id.buyuse_spinner);
        ArrayAdapter numAdapter = ArrayAdapter.createFromResource(this, R.array.spinnerArray, android.R.layout.simple_spinner_item);
        numAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buySpinner.setAdapter(numAdapter);
        buySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text = buySpinner.getSelectedItem().toString();
                count = text;

                try{
                    temp = Integer.parseInt(text);
                }catch(Exception e) {
                    return;
                }
                temp = temp*4000;
                text = Integer.toString(temp);
                tv.setText(text);
                //Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString()+"을 선택하셨습니다", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //텍스트뷰
        tv = (TextView)findViewById(R.id.declinePoint);
        tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //구매버튼
        Button pb = (Button)findViewById(R.id.payButton);
        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String Query = "";
                    Query += "4";
                    Query += ID;
                    Query += count;
                    //Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SocketActivity.class);
                    intent.putExtra("key",Query);
                    startActivityForResult(intent,0);
                    Log.v("보낸 메세지",Query);
                // Intent btnIntent1 = new Intent(getApplicationContext(),BuyUseActivity.class );
                // startActivity(btnIntent1);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_button:
                //툴바의 아이콘이 할 기능 정의할 것
                //Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                // startActivity(intent);
                break;
            case R.id.toolbutton1:
                //툴바의 아이콘이 할 기능 정의할 것
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.putExtra("ID",fromActivity);
                startActivity(intent1);
                break;
            case R.id.toolbutton2:
                //툴바의 아이콘이 할 기능 정의할 것
               // Intent intent2 = new Intent(getApplicationContext(), BuyActivity.class);
                //intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent2);
                break;
            case R.id.toolbutton3:
                //툴바의 아이콘이 할 기능 정의할 것
                Intent intent3 = new Intent(getApplicationContext(), BuyGiftActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent3.putExtra("ID",fromActivity);
                startActivity(intent3);
                break;
            case R.id.toobutton4:
                Intent intent4 = new Intent(getApplicationContext(), PointActivity.class);
                intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent4.putExtra("ID",fromActivity);
                startActivity(intent4);
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
