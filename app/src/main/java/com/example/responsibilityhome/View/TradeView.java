package com.example.responsibilityhome.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.responsibilityhome.Model.Trade;
import com.example.responsibilityhome.R;

public class TradeView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_view);

        Intent intent = getIntent();
        Trade trade = (Trade) intent.getSerializableExtra("trade data");

        TextView name = findViewById(R.id.name);
        name.setText(trade.getName());

    }
    //엑스 버튼 클릭
    public void TradeViewXClicked(View view) {
        finish();
    }
}
