package com.example.responsibilityhome.View;


import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.responsibilityhome.Entity.PieEntry;
import com.example.responsibilityhome.R;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import android.view.View;

import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CreditView extends Activity {
    private PieView mPieView;

    String myJSON;

    private static final String TAG_AMOUNT = "result";

    JSONArray posts = null;
    String userAccountData;
    String userCardData;
    String userCreditCardData;
    String userLoanData;
    String userData;

    //재정
    String[] userAccountDataSplit;
    int accountMoneySum=0;

    //현금서비스
    String[] userCardDataSplit;

    //신용카드
    String[] userCreditCardDataSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_view);
        mPieView = findViewById(R.id.pieView);
        initPieView();
        TextView name = (TextView)findViewById(R.id.name);
        name.setText("2등급");

        TextView sumScore = (TextView)findViewById(R.id.sum_score);

        GetAccountDataJSON g1 = new GetAccountDataJSON();
        try {
            userAccountData = g1.execute("http://49.236.135.136/holding_account_select.php").get();
            Log.d("yeop", ""+userAccountData);
            userAccountDataSplit = userAccountData.split("/");

            Integer.valueOf(userAccountDataSplit[1]);
        } catch (ExecutionException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //예금/신탁
        TextView accountScore = findViewById(R.id.account_score);
        if(Integer.parseInt(userAccountDataSplit[1])>=50000000)
            accountScore.setText("200");
        else if(Integer.parseInt(userAccountDataSplit[1])>=10000000)
            accountScore.setText("160");
        else if(Integer.parseInt(userAccountDataSplit[1])>=5000000)
            accountScore.setText("120");
        else if(Integer.parseInt(userAccountDataSplit[1])>=1000000)
            accountScore.setText("80");
        else if(Integer.parseInt(userAccountDataSplit[1])>=100000)
            accountScore.setText("40");
        else
            accountScore.setText("0");

        //점수 합
        sumScore.setText(String.valueOf(Integer.parseInt(accountScore.getText().toString())+Integer.parseInt(sumScore.getText().toString())));

        //투자
        TextView investScore = findViewById(R.id.invest_score);
        if(Integer.parseInt(userAccountDataSplit[5]) + Integer.parseInt(userAccountDataSplit[13])>=50000000)
            investScore.setText("100");
        else if(Integer.parseInt(userAccountDataSplit[5]) + Integer.parseInt(userAccountDataSplit[13])>=10000000)
            investScore.setText("80");
        else if(Integer.parseInt(userAccountDataSplit[5]) + Integer.parseInt(userAccountDataSplit[13])>=2000000)
            investScore.setText("60");
        else if(Integer.parseInt(userAccountDataSplit[5]) + Integer.parseInt(userAccountDataSplit[13])>=1000000)
            investScore.setText("40");
        else if(Integer.parseInt(userAccountDataSplit[5]) + Integer.parseInt(userAccountDataSplit[13])>=500000)
            investScore.setText("20");
        else
            investScore.setText("20");

        sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt(investScore.getText().toString())));

        //대출
        TextView loanScore = findViewById(R.id.loan_score);
        if(Integer.parseInt(userAccountDataSplit[9])>=20000000)
            loanScore.setText("0");
        else if(Integer.parseInt(userAccountDataSplit[9])>=10000000)
            loanScore.setText("20");
        else if(Integer.parseInt(userAccountDataSplit[9])>=3000000)
            loanScore.setText("40");
        else if(Integer.parseInt(userAccountDataSplit[9])>=1000000)
            loanScore.setText("60");
        else if(Integer.parseInt(userAccountDataSplit[9])>=600000)
            loanScore.setText("80");
        else
            loanScore.setText("100");

        sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt(loanScore.getText().toString())));

        GetCardDataJSON g2 = new GetCardDataJSON();
        try {
            userCardData = g2.execute("http://49.236.135.136/CardService_select.php").get();
            Log.d("yeop", ""+userCardData);
            userCardDataSplit = userCardData.split("/");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //대출
        TextView cashScore = findViewById(R.id.cash_score);
        if(Integer.parseInt(userCardDataSplit[0])+Integer.parseInt(userCardDataSplit[1])>=1000000)
            cashScore.setText("0");
        else if(Integer.parseInt(userCardDataSplit[0])+Integer.parseInt(userCardDataSplit[1])>500000)
            cashScore.setText("20");
        else if(Integer.parseInt(userCardDataSplit[0])+Integer.parseInt(userCardDataSplit[1])>200000)
            cashScore.setText("40");
        else if(Integer.parseInt(userCardDataSplit[0])+Integer.parseInt(userCardDataSplit[1])>100000)
            cashScore.setText("60");
        else if(Integer.parseInt(userCardDataSplit[0])+Integer.parseInt(userCardDataSplit[1])>50000)
            cashScore.setText("80");
        else
            cashScore.setText("100");

        sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt(cashScore.getText().toString())));

        GetCreditCardDataJSON g3 = new GetCreditCardDataJSON();
        try {
            userCreditCardData = g3.execute("http://49.236.135.136/CreditCard_select.php").get();
            Log.d("yeop", ""+userCreditCardData);
            userCreditCardDataSplit = userCreditCardData.split("/");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TextView cardScore = findViewById(R.id.card_score);


        GetLoanDataJSON g4 = new GetLoanDataJSON();
        try {
            userLoanData = g4.execute("http://49.236.135.136/LoanData_select.php").get();
            Log.d("yeop", ""+userLoanData);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        GetUserDataJSON g5 = new GetUserDataJSON();
        try {
            userData = g5.execute("http://49.236.135.136/User_select.php").get();
            Log.d("yeop", ""+userData);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


/*
        //막대그래프 추가
        StackedBarChart mStackedBarChart = findViewById(R.id.stackedbarchart);

        StackedBarModel s1 = new StackedBarModel("2개월전");

        s1.addBar(new BarModel(2.3f, Color.parseColor("#EBBF03")));
        s1.addBar(new BarModel(2.3f, Color.parseColor("#ff4d4d")));
        s1.addBar(new BarModel(2.3f, Color.parseColor("#8d5ff5")));
        s1.addBar(new BarModel(2.3f, Color.parseColor("#41D230")));
        s1.addBar(new BarModel(2.3f, Color.parseColor("#4FAAFF")));

        StackedBarModel s2 = new StackedBarModel("1개월전");
        s2.addBar(new BarModel(1.1f, Color.parseColor("#EBBF03")));
        s2.addBar(new BarModel(2.7f, Color.parseColor("#ff4d4d")));
        s2.addBar(new BarModel(0.7f, Color.parseColor("#8d5ff5")));
        s2.addBar(new BarModel(0.7f, Color.parseColor("#41D230")));
        s2.addBar(new BarModel(0.7f, Color.parseColor("#4FAAFF")));

        StackedBarModel s3 = new StackedBarModel("현재");

        s3.addBar(new BarModel(2.3f, Color.parseColor("#EBBF03")));
        s3.addBar(new BarModel(2.f, Color.parseColor("#ff4d4d")));
        s3.addBar(new BarModel(3.3f, Color.parseColor("#8d5ff5")));
        s3.addBar(new BarModel(3.3f, Color.parseColor("#41D230")));
        s3.addBar(new BarModel(3.3f, Color.parseColor("#4FAAFF")));

        mStackedBarChart.addBar(s1);
        mStackedBarChart.addBar(s2);
        mStackedBarChart.addBar(s3);

        mStackedBarChart.startAnimation();

        Button detailbutton = (Button) findViewById(R.id.detailbutton);
        detailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreditView.this, ResponsibilityDetailActivity.class);
                startActivity(intent);
            }
        });
*/
    }

       class GetAccountDataJSON extends AsyncTask<String, Void, String> {
            @Override
           protected String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    String userData="";
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    myJSON = sb.toString().trim();
                    JSONObject jsonObj = new JSONObject(myJSON);
                    posts = jsonObj.getJSONArray(TAG_AMOUNT);
                    for (int i = 0; i < posts.length(); i++) {
                        JSONObject c = posts.getJSONObject(i);
                        if (c.getString("joinKey").equals("yujinBBam")) {
                                userData += ""+ c.getString("data") + "/" + c.getString("resAccountBalance") + "/" + c.getString("resOverdraftAcctYN") + "/" + c.getString("resAccountNickName") + "/";
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

    class GetCardDataJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String uri = params[0];

            BufferedReader bufferedReader = null;
            try {
                String userData="";
                URL url = new URL(uri);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }
                myJSON = sb.toString().trim();
                JSONObject jsonObj = new JSONObject(myJSON);
                posts = jsonObj.getJSONArray(TAG_AMOUNT);
                for (int i = 0; i < posts.length(); i++) {
                    JSONObject c = posts.getJSONObject(i);
                    if (c.getString("joinKey").equals("yujinBBam")) {
                        userData += ""+ c.getString("resCashService") + "/" + c.getString("resCardLoan") + "/";
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

    class GetCreditCardDataJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String uri = params[0];

            BufferedReader bufferedReader = null;
            try {
                String userData="";
                URL url = new URL(uri);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }
                myJSON = sb.toString().trim();
                JSONObject jsonObj = new JSONObject(myJSON);
                posts = jsonObj.getJSONArray(TAG_AMOUNT);
                for (int i = 0; i < posts.length(); i++) {
                    JSONObject c = posts.getJSONObject(i);
                    if (c.getString("joinKey").equals("youngjinBBam")) {
                        userData += ""+ c.getString("resUsedAmount") + "/" + c.getString("resRemaionLimit") + "/";
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

    class GetLoanDataJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String uri = params[0];

            BufferedReader bufferedReader = null;
            try {
                String userData="";
                URL url = new URL(uri);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }
                myJSON = sb.toString().trim();
                JSONObject jsonObj = new JSONObject(myJSON);
                posts = jsonObj.getJSONArray(TAG_AMOUNT);
                for (int i = 0; i < posts.length(); i++) {
                    JSONObject c = posts.getJSONObject(i);
                    if (c.getString("joinKey").equals("youngjinBBam")) {
                        userData += ""+ c.getString("resAccountStartDate") + "/" + c.getString("resAccountEndDate") + "/" + c.getString("resLoanBalance") + "/" + c.getString("resPrincipal") + "/" + c.getString("resRate") + "/" + c.getString("resState") + "/";
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

    class GetUserDataJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String uri = params[0];

            BufferedReader bufferedReader = null;
            try {
                String userData="";
                URL url = new URL(uri);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }
                myJSON = sb.toString().trim();
                JSONObject jsonObj = new JSONObject(myJSON);
                posts = jsonObj.getJSONArray(TAG_AMOUNT);
                for (int i = 0; i < posts.length(); i++) {
                    JSONObject c = posts.getJSONObject(i);
                    if (c.getString("joinKey").equals("youngjinBBam")) {
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

    private void initPieView() {
        mPieView.setColors(createColors());
        mPieView.setData(createData());
    }

    private ArrayList<PieEntry> createData() {
        ArrayList<PieEntry> pieLists = new ArrayList<>();
        pieLists.add(new PieEntry(20.00F, "상환이력"));
        pieLists.add(new PieEntry(20.00F, ""));
        pieLists.add(new PieEntry(5.00F, "재정수준"));
        pieLists.add(new PieEntry(0.00F, ""));
        pieLists.add(new PieEntry(10.00F, "거래기간"));
        pieLists.add(new PieEntry(5.00F, ""));
        pieLists.add(new PieEntry(10.00F, "대출"));
        pieLists.add(new PieEntry(20.00F, ""));
        pieLists.add(new PieEntry(5.00F, "임의"));
        pieLists.add(new PieEntry(5.00F, ""));
        return pieLists;
    }

    private ArrayList<Integer> createColors() {
        ArrayList<Integer> colorLists = new ArrayList<>();
        colorLists.add(Color.parseColor("#EBBF03"));
        colorLists.add(Color.parseColor("#F0F0F0"));
        colorLists.add(Color.parseColor("#ff4d4d"));
        colorLists.add(Color.parseColor("#F0F0F0"));
        colorLists.add(Color.parseColor("#8d5ff5"));
        colorLists.add(Color.parseColor("#F0F0F0"));
        colorLists.add(Color.parseColor("#41D230"));
        colorLists.add(Color.parseColor("#F0F0F0"));
        colorLists.add(Color.parseColor("#4FAAFF"));
        colorLists.add(Color.parseColor("#F0F0F0"));
        return colorLists;
    }

    // 엑스 버튼 클릭
    public void CreditViewXClicked(View view){
        finish();
    }

}