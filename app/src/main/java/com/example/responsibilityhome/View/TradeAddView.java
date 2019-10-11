package com.example.responsibilityhome.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.responsibilityhome.R;

public class TradeAddView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_add_view);

    }

    //엑스 버튼 클릭
    public void TradeViewXClicked(View view) {
        finish();
    }
}
