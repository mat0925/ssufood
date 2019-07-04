package com.ccl.android.ssufood;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketActivity extends Activity {

    private String fromServer = "";//서버로 부터 받은 값 저장

    private Socket socket;

    private BufferedReader networkReader;
    private BufferedWriter networkWriter;

    char header;
    String ip = "10.27.0.30"; // 서버의 IP 주소
    String fromActivity; // 액티비티로 부터 받은 값
    int port = 9190; // PORT번호

    protected void onStop() {
        super.onStop();
        Log.v("소켓삭제","딜리티드 맨");
        try {
            socket.close();//소켓 삭제잼
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        Intent intent = getIntent();//액티비티로부터 넘어온 데이터를 받을 준비
        fromActivity = intent.getStringExtra("key");//액티비티로부터 넘어온 데이터를 key값으로 받음
        header = fromActivity.charAt(0);//헤더를 딴다.
        Log.e("헤더",header+"");
        Log.v("쓰레드 연결",fromActivity);

        checkUpdate.start();//쓰레드시작 소켓생성하고 정보를 주고 받는다.

    }

    private Thread checkUpdate = new Thread() {

        public void run() {
            try {
                try {
                    setSocket(ip, port);//소켓생성
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (fromActivity != null || !fromActivity.equals("")) {//액티비티로부터온 데이터를 서버로 보낸다.
                    PrintWriter out = new PrintWriter(networkWriter, true);
                    String return_msg = fromActivity;
                    out.println(return_msg);
                }

                String line="";//서버로부터온 데이터를 먼저 받는다.
                Log.w("쓰레드 시작", "Start Thread");
                while (true) {
                    Log.w("쓰레드 실행", "chatting is running");
                    line = networkReader.readLine();//서버로 부터 온 데이터를 읽어들인다.
                    fromServer = line; // 서버에서온 데이터를 저장한다.
                    Log.w("받은 값", fromServer);
                    Intent data = new Intent();
                    data.putExtra("key",fromServer);//서버로부터 받은 데이터를 인텐트에 담는다.
                    setResult(0,data);//결과값을 저장
                    finish();//소켓액티비티 종료
                }
            } catch (Exception e) {

            }
        }
    };

    public void setSocket(String ip, int port) throws IOException {
        //소켓생성 함수
        try {
            socket = new Socket(ip, port);
            networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            networkReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}