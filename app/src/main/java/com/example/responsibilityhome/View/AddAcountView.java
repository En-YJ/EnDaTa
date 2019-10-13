package com.example.responsibilityhome.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.responsibilityhome.Model.DataElement;
import com.example.responsibilityhome.Model.DataProvision;
import com.example.responsibilityhome.R;

import java.util.ArrayList;

public class AddAcountView extends AppCompatActivity {
    private ArrayList<String> banks = new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acount_view);

        initializeData();
        listView = findViewById(R.id.listView);

        AccountAdapter accountAdapter = new AccountAdapter(this, banks);
        listView.setAdapter(accountAdapter);

    }

    public void initializeData(){
        // 데이터 초기화
        banks.add("국민은행");
        banks.add("우리은행");
        banks.add("신한은행");
        banks.add("KEB하나은행");
        banks.add("기업은행");
        banks.add("SC제일은행");
        banks.add("씨티은행");
        banks.add("케이뱅크");
        banks.add("농협은행");
        banks.add("우체국");
        banks.add("수협은행");
        banks.add("산업은행");
        banks.add("부산은행");
        banks.add("대구은행");
        banks.add("경남은행");
        banks.add("광주은행");
        banks.add("전북은행");
        banks.add("제주은행");
    }

}
class AccountAdapter extends BaseAdapter {
    Context context = null;
    LayoutInflater layoutInflater = null;
    ArrayList<String> data;

    public AccountAdapter(Context context, ArrayList<String> data) {
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
        View v = layoutInflater.inflate(R.layout.item_account, null);

        TextView name = v.findViewById(R.id.name);
        name.setText(data.get(i));

        ToggleButton addBtn = v.findViewById(R.id.btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((ToggleButton)view).isChecked()){
                    // 연동 되어 있는 경우
                    Toast.makeText(context, "계좌에 연동되었습니다.", Toast.LENGTH_SHORT).show();
                } else{

                    Toast.makeText(context, "연동이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}