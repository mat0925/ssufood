package com.ccl.android.ssufood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BuyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("\0");
        setSupportActionBar(mToolbar);


        Button b1 = (Button)findViewById(R.id.buyforuseButton);     // 사용하기 위한 구매 액티비티로 넘어감
        Button b2 = (Button)findViewById(R.id.buyforgiftButton);    // 선물하기 위한 구매 액티비티로 넘어감

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnIntent1 = new Intent(getApplicationContext(),BuyUseActivity.class );
                startActivity(btnIntent1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnIntent2 = new Intent(getApplicationContext(), BuyGiftActivity.class);
                startActivity(btnIntent2);
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
                //툴바의 아이콘이 할 기능 정의할 것
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                break;
            case R.id.toolbutton2:
                //툴바의 아이콘이 할 기능 정의할 것
                Intent intent2 = new Intent(getApplicationContext(), BuyActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                break;
            case R.id.toolbutton3:
                //툴바의 아이콘이 할 기능 정의할 것
                Intent intent3 = new Intent(getApplicationContext(), BuyGiftActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                break;
            case R.id.toobutton4:
                Intent intent4 = new Intent(getApplicationContext(), PointActivity.class);
                intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent4);
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
