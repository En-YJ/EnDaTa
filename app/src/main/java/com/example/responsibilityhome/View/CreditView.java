package com.example.responsibilityhome.View;


import android.app.Activity;
import android.content.Intent;
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

    //대출내역
    String[] userLoanDataSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_view);

        Button addAccountBtn = findViewById(R.id.btn_add_account);
        addAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 계좌 연동 페이지 띄움
                Intent intent = new Intent(getApplicationContext(), AddAcountView.class);
                startActivity(intent);
            }
        });

        mPieView = findViewById(R.id.pieView);
        initPieView();
        TextView name = (TextView)findViewById(R.id.name);

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
        if(Integer.parseInt(userAccountDataSplit[5])>=50000000)
            investScore.setText("100");
        else if(Integer.parseInt(userAccountDataSplit[5])>=50000000)
            investScore.setText("80");
        else if(Integer.parseInt(userAccountDataSplit[5])>=50000000)
            investScore.setText("60");
        else if(Integer.parseInt(userAccountDataSplit[5])>=50000000)
            investScore.setText("40");
        else if(Integer.parseInt(userAccountDataSplit[5])>=50000000)
            investScore.setText("20");
        else
            investScore.setText("0");

        sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt(investScore.getText().toString())));

        //보험
        TextView insuranceScore = findViewById(R.id.insurance_score);
        if(Integer.parseInt(userAccountDataSplit[13])>=5000000)
            insuranceScore.setText("100");
        else if(Integer.parseInt(userAccountDataSplit[13])>=3000000)
            insuranceScore.setText("80");
        else if(Integer.parseInt(userAccountDataSplit[13])>=1000000)
            insuranceScore.setText("60");
        else if(Integer.parseInt(userAccountDataSplit[13])>=500000)
            insuranceScore.setText("40");
        else if(Integer.parseInt(userAccountDataSplit[13])>=100000)
            insuranceScore.setText("20");
        else
            insuranceScore.setText("0");

        sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt(insuranceScore.getText().toString())));

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
        if(Integer.parseInt(userCardDataSplit[0])+Integer.parseInt(userCardDataSplit[1])>=10000000)
            cashScore.setText("0");
        else if(Integer.parseInt(userCardDataSplit[0])+Integer.parseInt(userCardDataSplit[1])>=5000000)
            cashScore.setText("20");
        else if(Integer.parseInt(userCardDataSplit[0])+Integer.parseInt(userCardDataSplit[1])>=2000000)
            cashScore.setText("40");
        else if(Integer.parseInt(userCardDataSplit[0])+Integer.parseInt(userCardDataSplit[1])>=1000000)
            cashScore.setText("60");
        else if(Integer.parseInt(userCardDataSplit[0])+Integer.parseInt(userCardDataSplit[1])>500000)
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

        TextView cardScore = findViewById(R.id.card_service_score);
        if(Integer.parseInt(userCreditCardDataSplit[0])+Integer.parseInt(userCreditCardDataSplit[1])>=1000000)
            cardScore.setText("0");
        else if(Integer.parseInt(userCreditCardDataSplit[0])+Integer.parseInt(userCreditCardDataSplit[1])>500000)
            cardScore.setText("20");
        else if(Integer.parseInt(userCreditCardDataSplit[0])+Integer.parseInt(userCreditCardDataSplit[1])>200000)
            cardScore.setText("40");
        else if(Integer.parseInt(userCreditCardDataSplit[0])+Integer.parseInt(userCreditCardDataSplit[1])>100000)
            cardScore.setText("60");
        else if(Integer.parseInt(userCreditCardDataSplit[0])+Integer.parseInt(userCreditCardDataSplit[1])>50000)
            cardScore.setText("80");
        else
            cardScore.setText("100");

        sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt(cardScore.getText().toString())));


        GetLoanDataJSON g4 = new GetLoanDataJSON();
        try {
            userLoanData = g4.execute("http://49.236.135.136/LoanData_select.php").get();
            Log.d("yeop", ""+userLoanData);
            userLoanDataSplit = userLoanData.split("/");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TextView loanNumber = findViewById(R.id.overdue_score);
        loanNumber.setText(userLoanDataSplit[5]);
        if(Integer.parseInt(userLoanDataSplit[5])>=10)
            sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt("0")));
        else if(Integer.parseInt(userLoanDataSplit[5])>=7)
            sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt("40")));
        else if(Integer.parseInt(userLoanDataSplit[5])>=5)
            sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt("80")));
        else if(Integer.parseInt(userLoanDataSplit[5])>=3)
            sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt("120")));
        else if(Integer.parseInt(userLoanDataSplit[5])>=1)
            sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt("160")));
        else
            sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt("200")));

        //상환률
        TextView repaymentPercent = findViewById(R.id.repayment_score);
        repaymentPercent.setText(String.valueOf(Integer.parseInt(userLoanDataSplit[2])*100/Integer.parseInt(userLoanDataSplit[3])));
        if(Integer.parseInt(repaymentPercent.getText().toString())>=100)
            sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt("100")));
        else if(Integer.parseInt(repaymentPercent.getText().toString())>=80)
            sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt("80")));
        else if(Integer.parseInt(repaymentPercent.getText().toString())>=60)
            sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt("60")));
        else if(Integer.parseInt(repaymentPercent.getText().toString())>=40)
            sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt("40")));
        else if(Integer.parseInt(repaymentPercent.getText().toString())>=20)
            sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt("20")));
        else
            sumScore.setText(String.valueOf(Integer.parseInt(sumScore.getText().toString())+Integer.parseInt("0")));

        GetUserDataJSON g5 = new GetUserDataJSON();
        try {
            userData = g5.execute("http://49.236.135.136/User_select.php").get();
            Log.d("yeop", ""+userData);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(Integer.parseInt(sumScore.getText().toString())>=900)
            name.setText("1등급");
        else if(Integer.parseInt(sumScore.getText().toString())>=800)
            name.setText("2등급");
        else if(Integer.parseInt(sumScore.getText().toString())>=700)
            name.setText("3등급");
        else if(Integer.parseInt(sumScore.getText().toString())>=600)
            name.setText("4등급");
        else if(Integer.parseInt(sumScore.getText().toString())>=500)
            name.setText("5등급");
        else if(Integer.parseInt(sumScore.getText().toString())>=400)
            name.setText("6등급");
        else if(Integer.parseInt(sumScore.getText().toString())>=300)
            name.setText("7등급");
        else if(Integer.parseInt(sumScore.getText().toString())>=200)
            name.setText("8등급");
        else if(Integer.parseInt(sumScore.getText().toString())>=100)
            name.setText("9등급");
        else
            name.setText("10등급");



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
                    if (c.getString("joinKey").equals("yujinBBam")) {
                        userData += ""+ c.getString("resUsedAmount") + "/" + c.getString("resRemainLimit") + "/";
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