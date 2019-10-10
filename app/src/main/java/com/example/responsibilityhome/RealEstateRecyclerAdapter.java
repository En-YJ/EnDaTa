package com.example.responsibilityhome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RealEstateRecyclerAdapter extends RecyclerView.Adapter<RealEstateRecyclerAdapter.ViewHolder> {
    Context context;
    List<RealEstateItem> items;
    int item_layout;

    public RealEstateRecyclerAdapter(Context context, List<RealEstateItem> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.real_estate_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RealEstateItem item = items.get(position);

        Glide.with(context)
                .load(item.getImage())
                .into(holder.image);

        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getDesc());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RealEstateDetail.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image",item.getImage());
                intent.putExtra("desc",item.getDesc());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView desc;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.real_estate_list_image);
            title = (TextView) itemView.findViewById(R.id.real_estate_list_title);
            desc = (TextView) itemView.findViewById(R.id.real_estate_list_desc);
            cardview = (CardView) itemView.findViewById(R.id.real_estate_list_item);
        }
    }
}
