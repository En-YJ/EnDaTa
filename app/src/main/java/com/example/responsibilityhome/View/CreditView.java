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
import com.example.responsibilityhome.ResponsibilityDetailActivity;


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
    String userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_view);
        mPieView = findViewById(R.id.pieView);
        initPieView();
        TextView name = (TextView)findViewById(R.id.name);
        name.setText("2등급");

        GetDataJSON g = new GetDataJSON();
        try {
            userData = g.execute("http://49.236.135.136/holding_account_select.php").get();
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

       class GetDataJSON extends AsyncTask<String, Void, String> {
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
                    Log.d("yeop", "what1");
                    JSONObject jsonObj = new JSONObject(myJSON);
                    posts = jsonObj.getJSONArray(TAG_AMOUNT);
                    for (int i = 0; i < posts.length(); i++) {
                        JSONObject c = posts.getJSONObject(i);
                        if (c.getString("joinKey").equals("youngjinBBam")) {
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