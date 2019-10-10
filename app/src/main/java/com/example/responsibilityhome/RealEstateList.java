package com.example.responsibilityhome;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RealEstateList extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.content_main, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.real_estate_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.room1);
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        byte[] bytes = byteArray.toByteArray();

        List<RealEstateItem> items = new ArrayList<>();
        RealEstateItem[] item = new RealEstateItem[5];
        item[0] = new RealEstateItem(bytes, "유진","유진 유진");
        item[1] = new RealEstateItem(bytes,"유진", "유진 유진");
        item[2] = new RealEstateItem(bytes, "유진","유진 유진");
        item[3] = new RealEstateItem(bytes,"유진", "유진 유진");
        item[4] = new RealEstateItem(bytes, "유진","유진");

        for (int i = 0; i < 5; i++) {
            items.add(item[i]);
        }

        recyclerView.setAdapter(new RealEstateRecyclerAdapter(getContext(), items, R.layout.content_main));


        return view;
    }


}

