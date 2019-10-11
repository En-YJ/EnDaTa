package com.example.responsibilityhome.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.responsibilityhome.R;

public class LandlordCreditView extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlord_credit_view);
    }


    //엑스 버튼 클릭
    public void LandlordCreditViewXClicked(View view){
        finish();
    }



}
