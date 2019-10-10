package com.example.responsibilityhome;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class TradeView extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_view);
    }


    //엑스 버튼 클릭
    public void TradeViewXClicked(View view){
        finish();
    }

}
