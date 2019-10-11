package com.example.responsibilityhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class RealEstateDetail extends AppCompatActivity {

    private ViewGroup viewLayout;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = getLayoutInflater();
        viewLayout = (ViewGroup)inflater.inflate(R.layout.activity_real_estate_detail, null);
        setContentView(viewLayout);

        TextView roomTitle = findViewById(R.id.room_title);
        roomTitle.setText(getIntent().getStringExtra("title"));

        TextView roomDesc = findViewById(R.id.room_desc);
        roomDesc.setText(getIntent().getStringExtra("charter"));

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
