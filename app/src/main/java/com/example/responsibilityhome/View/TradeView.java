package com.example.responsibilityhome.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.responsibilityhome.Model.Trade;
import com.example.responsibilityhome.R;

import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;

public class TradeView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_view);

        Intent intent = getIntent();
        Trade trade = (Trade) intent.getSerializableExtra("trade data");

        TextView name = findViewById(R.id.name);
        name.setText(trade.getName());

        TextView location = findViewById(R.id.location);
        location.setText(trade.getLocation());

        TextView landlordId = findViewById(R.id.landlord_id);
        landlordId.setText(trade.getLandlordId());

        TextView renterId = findViewById(R.id.renter_id);
        renterId.setText(trade.getRenterId());

        TextView graphTitle = findViewById(R.id.title_graph);
        graphTitle.setText("' " + getOpponentId(trade.getLandlordId(), trade.getRenterId()) + " ' 님의 최근 신용 추이");

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


    }

    public String getOpponentId(String landlordId, String renterId){
        // 거래 상대방의 아이디를 반환
        return renterId;
    }
    //엑스 버튼 클릭
    public void TradeViewXClicked(View view) {
        finish();
    }
}
