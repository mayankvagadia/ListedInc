package com.ListedCo.listedcotask.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ListedCo.listedcotask.R;
import com.ListedCo.listedcotask.activity.HomeActivity;
import com.ListedCo.listedcotask.api.response.RecentLink;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;

import java.util.ArrayList;

/***
 *Created By Mayank
 **/
public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.LinksViewHolder> {

    ArrayList<RecentLink> links;
    Context context;

    public LinksAdapter(Context context) {
        this.context = context;
        this.links = new ArrayList<>();
    }

    @NonNull
    @Override
    public LinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_link, parent, false);
        return new LinksViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LinksViewHolder holder, int position) {
        Glide.with(context).load(links.get(position).original_image).placeholder(R.drawable.link_icon).error(R.drawable.link_icon).into(holder.img_link);
        holder.txt_name.setText(links.get(position).title);
        holder.txt_link.setText(links.get(position).web_link);
        String[] date = links.get(position).created_at.split("T");
        holder.txt_date.setText(date[0]);
        holder.txt_link_count.setText("" + links.get(position).total_clicks);
    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    class LinksViewHolder extends RecyclerView.ViewHolder {

        ImageView img_link, img_copy;
        TextView txt_name, txt_date, txt_link_count, txt_link;

        public LinksViewHolder(@NonNull View itemView) {
            super(itemView);

            img_link = itemView.findViewById(R.id.img_link);
            img_copy = itemView.findViewById(R.id.img_copy);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_link_count = itemView.findViewById(R.id.txt_link_count);
            txt_link = itemView.findViewById(R.id.txt_link);
        }
    }

    public void setData(ArrayList<RecentLink> links_NEW) {
        this.links.clear();
        this.links.addAll(links_NEW);
        notifyDataSetChanged();
    }
}
