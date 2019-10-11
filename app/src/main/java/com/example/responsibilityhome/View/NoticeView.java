package com.example.responsibilityhome.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.responsibilityhome.Model.DataElement;
import com.example.responsibilityhome.Model.DataProvision;
import com.example.responsibilityhome.R;

import java.util.ArrayList;

public class NoticeView extends Activity {
    private ArrayList<String> noticeUsers = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_view);

        initializeData();
        listView = findViewById(R.id.listView);
        NoticeAdapter noticeAdapter = new NoticeAdapter(this, noticeUsers);
        listView.setAdapter(noticeAdapter);
    }

    public void initializeData(){
        // 데이터 초기화
        noticeUsers.add("ann0905");
        noticeUsers.add("yeop96");
        noticeUsers.add("qazyj");
    }

    //엑스 버튼 클릭
    public void TradeViewXClicked(View view) {
        finish();
    }

}
class NoticeAdapter extends BaseAdapter {
    Context context = null;
    LayoutInflater layoutInflater = null;
    ArrayList<String> data;

    public NoticeAdapter(Context context, ArrayList<String> data) {
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
        View v = layoutInflater.inflate(R.layout.item_notice, null);

        ImageView userIcon = v.findViewById(R.id.user_icon);
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 사용자 정보 화면 띄움
                Intent intent = new Intent(context, UserCreditView.class);
                context.startActivity(intent);
            }
        });

        final String id = data.get(i);
        TextView userId = v.findViewById(R.id.id);
        userId.setText(id);

        Button acceptBtn = v.findViewById(R.id.btn_accept);
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "' " + id + "' ";
                Toast.makeText(context, message + "의 신용 열람 요청을 승인하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
