package com.example.responsibilityhome;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.responsibilityhome.Network.RealEstateItem;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

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

        if(item.getMonthly().equals("0")) {
            holder.event.setText(" 전세 ");
            holder.event.setBackgroundColor(Color.parseColor("#8013B9A5"));
        }
        else{
            holder.event.setText(" 월세 ");
            holder.event.setBackgroundColor(Color.parseColor("#804FB7F8"));
        }

        holder.title.setText(item.getCharter()+"/"+item.getMonthly()+"  "+item.getTitle());
        holder.charter.setText("구로구 "+item.getDong());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RealEstateDetail.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image",item.getImage());
                intent.putExtra("charter",item.getCharter());
                intent.putExtra("monthly",item.getMonthly());
                intent.putExtra("dong",item.getDong());
                intent.putExtra("m2",item.getM2());
                intent.putExtra("build",item.getBuildYear());
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
        TextView charter;
        //TextView monthly;
        TextView event;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.real_estate_list_image);
            title = (TextView) itemView.findViewById(R.id.real_estate_list_title);
            charter = (TextView) itemView.findViewById(R.id.real_estate_list_desc);
            //monthly = (TextView) itemView.findViewById(R.id.real_estate_list_monthly);
            event = (TextView) itemView.findViewById(R.id.real_estate_list_event);
            cardview = (CardView) itemView.findViewById(R.id.real_estate_list_item);
        }
    }
}
