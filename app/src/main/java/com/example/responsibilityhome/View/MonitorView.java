package com.example.responsibilityhome.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.responsibilityhome.Model.DataElement;
import com.example.responsibilityhome.Model.DataProvision;
import com.example.responsibilityhome.R;

import java.util.ArrayList;

public class MonitorView extends Activity {
    private ArrayList<DataProvision> dataProvisions = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_view);

        initializeData();
        listView = findViewById(R.id.listView);

        MonitorAdapter monitorAdapter = new MonitorAdapter(this, dataProvisions);
        listView.setAdapter(monitorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 개인정보 사용내역 상세 설정
            }
        });
    }

    public void initializeData(){
        // 데이터 초기화
        ArrayList<DataElement> datas = new ArrayList<>();
        datas.add(new DataElement("부동산 거래내역", false));
        datas.add(new DataElement("신용 점수", false));
        datas.add(new DataElement("신용 등급", false));
        datas.add(new DataElement("서비스 가입년월", false));
        datas.add(new DataElement("서비스 사용 빈도수", false));

        dataProvisions.add(new DataProvision("직방", datas));
        dataProvisions.add(new DataProvision("다방", datas));

        dataProvisions.add(new DataProvision("니소스씨앤디", datas));
        dataProvisions.add(new DataProvision("건물과사람들", datas));
        dataProvisions.add(new DataProvision("와이낫플래닝 ", datas));
        dataProvisions.add(new DataProvision("컬리넌홀딩스", datas));
        dataProvisions.add(new DataProvision("한아름", datas));


        dataProvisions.add(new DataProvision("모두이사", datas));
        dataProvisions.add(new DataProvision("영구크린", datas));
    }

    //엑스 버튼 클릭
    public void TradeViewXClicked(View view) {
        finish();
    }
}

class MonitorAdapter extends BaseAdapter {
    Context context = null;
    LayoutInflater layoutInflater = null;
    ArrayList<DataProvision> data;

    public MonitorAdapter(Context context, ArrayList<DataProvision> data) {
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
        View v = layoutInflater.inflate(R.layout.item_data_monitor, null);

        TextView companyName = v.findViewById(R.id.name_company);
        TextView details = v.findViewById(R.id.details);

        final String name = data.get(i).getCompany();
        companyName.setText(name);
        details.setText(data.get(i).toString());

        CheckBox checkBox = v.findViewById(R.id.checkbox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "' " + name + "' ";
                if(((CheckBox)view).isChecked()){
                    Toast.makeText(context, message + "에게 정보 제공을 동의하였습니다." , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, message + "에게 정보 제공을 취소하였습니다." , Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}

