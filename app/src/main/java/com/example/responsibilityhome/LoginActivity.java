package com.example.responsibilityhome;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.responsibilityhome.Model.Trade;
import com.example.responsibilityhome.View.CreditView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends Activity {
    String email;
    String password;
    EditText _email;
    EditText _password;

    String myJSON;
    String userData;

    private static final String TAG_AMOUNT = "result";

    JSONArray posts = null;

    Trade trade;

    //대출내역
    String[] userDataSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _email = (EditText) findViewById(R.id.input_email);
                email = _email.getText().toString();
                _password = (EditText) findViewById(R.id.input_password);
                password = _password.getText().toString();

                GetUserDataJSON g5 = new GetUserDataJSON();
                try {
                    userData = g5.execute("http://49.236.135.136/User_select.php", email).get();
                    userDataSplit=userData.split("/");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(email.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(),"아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else if(userData=="") {
                    Toast.makeText(getApplicationContext(),"아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else if(email.equals(userDataSplit[1]) && password.equals(userDataSplit[2])) {
                    startActivity(new Intent(getApplication(), MainActivity.class));

                    LoginActivity.this.finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
            // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
            AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
            alBuilder.setMessage("종료하시겠습니까?");

            // "예" 버튼을 누르면 실행되는 리스너
            alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish(); // 현재 액티비티를 종료한다. (MainActivity에서 작동하기 때문에 애플리케이션을 종료한다.)
                }
            });
            // "아니오" 버튼을 누르면 실행되는 리스너
            alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return; // 아무런 작업도 하지 않고 돌아간다
                }
            });

            alBuilder.setTitle("믿집");
            alBuilder.setIcon(R.drawable.th_icon); //아이콘 설정
            alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.
        }


    class GetUserDataJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String uri = params[0];
            String email = params[1];

            String postParameters = "email=" + email;

            BufferedReader bufferedReader = null;
            try {
                String userData="";
                URL url = new URL(uri);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();
                StringBuilder sb = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }
                myJSON = sb.toString().trim();
                JSONObject jsonObj = new JSONObject(myJSON);
                posts = jsonObj.getJSONArray(TAG_AMOUNT);
                for (int i = 0; i < posts.length(); i++) {
                    JSONObject c = posts.getJSONObject(i);
                    if (email.equals(c.getString("id"))) {
                        userData += ""+ c.getString("name") + "/" + c.getString("id") + "/" + c.getString("password") + "/";
                    }
                }
                return userData;
            } catch (Exception e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(String result) {
        }
    }

}
