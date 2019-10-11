package com.example.responsibilityhome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.responsibilityhome.View.LandlordCreditView;

public class RealEstateDetail extends AppCompatActivity {

    private ViewGroup viewLayout;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = getLayoutInflater();
        viewLayout = (ViewGroup)inflater.inflate(R.layout.activity_real_estate_detail, null);
        setContentView(viewLayout);

        TextView roomEvent = findViewById(R.id.room_event);
        if(getIntent().getStringExtra("monthly").equals("0")) {
            roomEvent.setText(" 전세 ");
            roomEvent.setBackgroundColor(Color.parseColor("#8013B9A5"));
        }
        else{
            roomEvent.setText(" 월세 ");
            roomEvent.setBackgroundColor(Color.parseColor("#804FB7F8"));
        }

        TextView roomTitle = findViewById(R.id.room_title);
        roomTitle.setText(getIntent().getStringExtra("title"));

        TextView roomDesc = findViewById(R.id.room_desc);
        roomDesc.setText(getIntent().getStringExtra("charter") +"만원");

        TextView roomMonthly = findViewById(R.id.room_monthly);
        roomMonthly.setText(getIntent().getStringExtra("monthly")+"만원");

        TextView roomDong = findViewById(R.id.room_dong);
        roomDong.setText("서울특별시 구로구 "+getIntent().getStringExtra("dong"));

        TextView roomM2 = findViewById(R.id.room_m2);
        roomM2.setText(getIntent().getStringExtra("m2")+"㎡");

        TextView roomBuild = findViewById(R.id.room_build);
        roomBuild.setText(getIntent().getStringExtra("build")+"년");

        //이미지를 바꿔준다
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        ImageView imageView = findViewById(R.id.room_image);

        //글라이드처리
        Glide.with(RealEstateDetail.this)
                .load(byteArray)
                .into(imageView);

    }


    //임대인 신용 버튼 클릭
    public void LandlordCreditButton(View view){
        Intent intent = new Intent(getApplicationContext(), LandlordCreditView.class);
        startActivity(intent);
    }
}
