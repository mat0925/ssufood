package com.ccl.android.ssufood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AssignActivity extends AppCompatActivity {
    EditText inputID;
    EditText inputPW;
    EditText checkPW;
    TextView tv;
    boolean check = false;  // PW 확인 변수

    String result ="";
    String ID;
    String PW;
    String passWord = "\0";
    String warn1 = "비밀번호가 일치하지 않습니다.";
    String warn2 = "비밀번호 일치";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);

        //툴바 코드얌
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("\0");
        setSupportActionBar(mToolbar);

        tv = (TextView)findViewById(R.id.warnPW);

        inputID = (EditText)findViewById(R.id.inputID);
        inputPW = (EditText)findViewById(R.id.inputPW);
        checkPW = (EditText)findViewById(R.id.checkPW);

        inputPW.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        inputPW.setTransformationMethod(PasswordTransformationMethod.getInstance());

        checkPW.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        checkPW.setTransformationMethod(PasswordTransformationMethod.getInstance());

        // 비밀번호 일치 검사 (110줄로 넘어가잇)
        inputPW.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                passWord = s.toString();
                Toast.makeText(getApplicationContext(), "비밀번호:"+passWord, Toast.LENGTH_SHORT).show();
            }
        });


        // 비밀번호 일치 검사
        checkPW.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(passWord.equals("\0"))
                {
                    Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_SHORT).show();
                    tv.setTextColor(0xAA88001b);
                    tv.setText(warn1);
                }
                else if( passWord.equals(s.toString()) )
                {

                    Toast.makeText(getApplicationContext(), "비밀번호 : "+passWord+", 입력 : "+s.toString(), Toast.LENGTH_SHORT).show();
                    tv.setText(warn2);
                    tv.setTextColor(0xAA1c6330);
                    check = true;
                }
                else
                {
                    Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_SHORT).show();
                    tv.setTextColor(0xAA88001b);
                    tv.setText(warn1);
                }
            }
        });


        //회원가입 버튼이얌
        Button joinButton = (Button)findViewById(R.id.joinButton);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check)
                {
                    ID = inputID.getText().toString();
                    String text = "";
                    text += "1";
                    text += ID;
                    text += inputPW.getText().toString();
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SocketActivity.class);
                    intent.putExtra("key",text);
                    startActivityForResult(intent,0);
                    Log.v("보낸 메세지",text);

                }
                else {
                    Toast.makeText(getApplicationContext(), "올바른 정보를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        result = data.getStringExtra("key");
        char[] chs = result.toCharArray();
        Toast toast = Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG);
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
                if(chs[1] == '1')
                {
                    Log.v("****리턴값****",result);
                    Toast.makeText(this.getApplicationContext(),"회원가입 성공! 로그인 요망",Toast.LENGTH_SHORT);
                    Log.v("보낸 메세지",result);
                    finish();
                }
                else
                {
                    Toast.makeText(this.getApplicationContext(),"회원가입 실패!",Toast.LENGTH_SHORT);
                    Log.v("****리턴값****","없어요");
                }
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
                //Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                //intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent1);
                break;
            case R.id.toolbutton2:
                //툴바의 아이콘이 할 기능 정의할 것
                //Intent intent2 = new Intent(getApplicationContext(), BuyActivity.class);
                //intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // startActivity(intent2);
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
}