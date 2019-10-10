package com.example.responsibilityhome;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CreditView extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_view);
    }


    //엑스 버튼 클릭
    public void CreditViewXClicked(View view){
        finish();
    }



}
