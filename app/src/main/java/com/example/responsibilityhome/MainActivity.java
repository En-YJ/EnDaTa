package com.example.responsibilityhome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.responsibilityhome.Network.NetworkTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private View view;
    private Inflater inflater;
    public List<RealEstateItem> items;

    String key = "ZhSKuf9bGy86oevKmjyx%2F8qSdyG73oHw1FQYmv%2BgqSc3Z1U3tdPmIyoG%2B907ISVccIAqiSr7VW0E5qXspg6xoA%3D%3D";
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        items = new ArrayList<>();

        /*//일단 임의 데이터 후에 서버에서 불러와서 그리는 작업
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.real_estate_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);*/



        /*List<RealEstateItem> items = new ArrayList<>();
        RealEstateItem[] item = new RealEstateItem[5];
        item[0] = new RealEstateItem(null, "유진","유진 유진");
        item[1] = new RealEstateItem(null,"유진", "유진 유진");
        item[2] = new RealEstateItem(null, "유진","유진 유진");
        item[3] = new RealEstateItem(null,"유진", "유진 유진");
        item[4] = new RealEstateItem(null, "유진","유진");

        for (int i = 0; i < 5; i++) {
            items.add(item[i]);
        }
        recyclerView.setAdapter(new RealEstateRecyclerAdapter(MainActivity.this, items, R.layout.content_main));*/

        String data = "";
        String url = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent?"//요청 URL
                +"LAWD_CD=" + "11530"
                +"&DEAL_YMD=" + "201910"
                +"&serviceKey=" + key;
        NetworkTask networkTask = new NetworkTask(MainActivity.this, url, data, 1);
        networkTask.execute();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //drawer 열려 있을 경우 닫기
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
            AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
            alBuilder.setMessage("종료하시겠습니까?");

            // "예" 버튼을 누르면 실행되는 리스너
            alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish(); // 현재 액티비티를 종료한다. (MainActivity에서 작동하기 때문에 애플리케이션을 종료한다.)
                }
            });
            // "아니오" 버튼을 누르면 실행되는 리스너
            alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return; // 아무런 작업도 하지 않고 돌아간다
                }
            });

            alBuilder.setTitle("믿집");
            alBuilder.setIcon(R.drawable.th_icon); //아이콘 설정
            alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //오른쪽 설정 그거 누르면 하는 행동
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // 왼쪽 drawer 버튼 클릭시 행동들!

        int id = item.getItemId();

        if (id == R.id.nav_credit) {
            //신용등급
            Intent intent = new Intent(getApplicationContext(), CreditView.class);
            startActivity(intent);
        } else if (id == R.id.nav_trade) {
            //거래관리
            Intent intent = new Intent(getApplicationContext(), TradeView.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_using) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
