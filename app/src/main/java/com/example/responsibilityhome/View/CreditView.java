package com.example.responsibilityhome.View;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import com.example.responsibilityhome.Entity.PieEntry;
import com.example.responsibilityhome.R;
import com.example.responsibilityhome.ResponsibilityDetailActivity;


import java.util.ArrayList;
import android.view.View;

import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;


public class CreditView extends Activity {
    private PieView mPieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_view);
        mPieView = findViewById(R.id.pieView);
        initPieView();
        TextView name = (TextView)findViewById(R.id.name);
        name.setText("2등급");

        /*
        Button detailbutton = (Button) findViewById(R.id.detailbutton);
        detailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreditView.this, ResponsibilityDetailActivity.class);
                startActivity(intent);
            }
        });
*/
    }

    private void initPieView() {
        mPieView.setColors(createColors());
        mPieView.setData(createData());
    }

    private ArrayList<PieEntry> createData() {
        ArrayList<PieEntry> pieLists = new ArrayList<>();
        pieLists.add(new PieEntry(20.00F, "양념치킨"));
        pieLists.add(new PieEntry(20.00F, ""));
        pieLists.add(new PieEntry(5.00F, "간장치킨"));
        pieLists.add(new PieEntry(0.00F, ""));
        pieLists.add(new PieEntry(10.00F, "후라이드"));
        pieLists.add(new PieEntry(5.00F, ""));
        pieLists.add(new PieEntry(10.00F, "닭강정"));
        pieLists.add(new PieEntry(20.00F, ""));
        pieLists.add(new PieEntry(5.00F, "뿌링클"));
        pieLists.add(new PieEntry(5.00F, ""));
        return pieLists;
    }

    private ArrayList<Integer> createColors() {
        ArrayList<Integer> colorLists = new ArrayList<>();
        colorLists.add(Color.parseColor("#EBBF03"));
        colorLists.add(Color.parseColor("#F0F0F0"));
        colorLists.add(Color.parseColor("#ff4d4d"));
        colorLists.add(Color.parseColor("#F0F0F0"));
        colorLists.add(Color.parseColor("#8d5ff5"));
        colorLists.add(Color.parseColor("#F0F0F0"));
        colorLists.add(Color.parseColor("#41D230"));
        colorLists.add(Color.parseColor("#F0F0F0"));
        colorLists.add(Color.parseColor("#4FAAFF"));
        colorLists.add(Color.parseColor("#F0F0F0"));
        return colorLists;
    }

    // 엑스 버튼 클릭
    public void CreditViewXClicked(View view){
        finish();
    }

}