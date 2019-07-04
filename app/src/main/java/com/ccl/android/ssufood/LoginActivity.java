package com.ccl.android.ssufood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText inputID;   // 아이디 입력창이얌
    EditText inputPW;   // 비번 입력창이얌
    String temp = ""; // 아이디+비번 변수얌
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputID = (EditText) findViewById(R.id.inputID);
        inputPW = (EditText) findViewById(R.id.inputPW);

        inputPW.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        inputPW.setTransformationMethod(PasswordTransformationMethod.getInstance());

        //툴바 창이얌
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("\0");
        setSupportActionBar(mToolbar);

        //로그인 버튼이얌
        Button loginButton = (Button)findViewById(R.id.loginButton);
        //로그인 버튼 리스너얌
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputID.getText().length() != 8)
                {
                    Toast.makeText(getApplicationContext(), "ID는 8자리여야 합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(inputPW.getText().length() != 8)
                {
                    Toast.makeText(getApplicationContext(), "PW는 8자리여야 합니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    temp += inputID.getText().toString();
                    String text = "";
                    text += "0";
                    text += inputID.getText().toString();
                    text += inputPW.getText().toString();
                    Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SocketActivity.class);
                    intent.putExtra("key",text);
                    startActivityForResult(intent,0);
                    /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);*/
                    Log.v("보낸 메세지",text);



                }
            }
        });

        //회원가입 버튼이얌
        Button joinButton = (Button)findViewById(R.id.joinButton);
        //회원가입 버튼 리스너얌
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnIntent1 = new Intent(getApplicationContext(),AssignActivity.class );
                btnIntent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(btnIntent1);
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
                //Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                //intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent1);
                break;
            case R.id.toolbutton2:
                //툴바의 아이콘이 할 기능 정의할 것
                //Intent intent2 = new Intent(getApplicationContext(), BuyActivity.class);
                //intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent2);
                break;
            case R.id.toolbutton3:
                //툴바의 아이콘이 할 기능 정의할 것
                //Intent intent3 = new Intent(getApplicationContext(), BuyGiftActivity.class);
                //intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent3);
                break;
            case R.id.toobutton4:
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        result = data.getStringExtra("key");
        char[] chs = result.toCharArray();
        Toast toast = Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG);
        toast.show();
        switch (requestCode)
        {
            case 0:
                if(chs[1] == '1')
                {
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    mainIntent.putExtra("mainkey",temp);
                    startActivityForResult(mainIntent,0);
                    Log.v("보낸 메세지",result);
                    finish();
                }
                else
                    Log.v("****리턴값****","없어요");
                break;

        }
    }
}
