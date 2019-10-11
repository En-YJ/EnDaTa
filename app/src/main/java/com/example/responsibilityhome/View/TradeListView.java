package com.example.responsibilityhome.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.responsibilityhome.Model.Trade;
import com.example.responsibilityhome.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TradeListView extends Activity {
    private ListView listView;
    private ArrayList<Trade> tradeList = new ArrayList<Trade>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_list_view);

        initializeData();
        listView = findViewById(R.id.listView);
        TradeAdapter tradeAdapter = new TradeAdapter(this, tradeList);
        listView.setAdapter(tradeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 거래 상세 페이지 보여줌
                Intent intent = new Intent(getApplicationContext(), TradeView.class);
                intent.putExtra("trade data", tradeList.get(i));
                startActivity(intent);
            }
        });

        FloatingActionButton addTradeFab = findViewById(R.id.fab_add_trade);
        addTradeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 거래 추가 페이지 보여줌
                Intent intent = new Intent(getApplicationContext(), TradeAddView.class);
                startActivity(intent);
            }
        });
    }

    public void initializeData(){
        // 데이터 초기화
        tradeList.add(new Trade("우리집", "서울시 구로구 신도림동 신도림sk뷰 104동 2301호", "ann0905", "yeop96"));
        tradeList.add(new Trade("자양동집", "서울시 광진구 자양3동 우성7차 703동 1102호", "yeop96", "qazyj"));
        tradeList.add(new Trade("논현동집", "인천광역시 남동구 논현동 현대힐스테이트 104동 1601호", "qazyj", "ann0905"));
    }

    //엑스 버튼 클릭
    public void TradeViewXClicked(View view) {
        finish();
    }

}
class TradeAdapter extends BaseAdapter{
    Context context = null;
    LayoutInflater layoutInflater = null;
    ArrayList<Trade> data;

    public TradeAdapter(Context context, ArrayList<Trade> data) {
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = layoutInflater.inflate(R.layout.item_trade_list, null);

        TextView name = v.findViewById(R.id.name);
        TextView location = v.findViewById(R.id.location);

        name.setText(data.get(i).getName());
        location.setText(data.get(i).getLocation());

        return v;
    }
}