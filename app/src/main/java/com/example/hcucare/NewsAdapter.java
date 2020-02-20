package com.example.hcucare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    List<getnews> listarray;

    public NewsAdapter(List<getnews> list) {
        this.listarray = list;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news,parent,false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        getnews getm=listarray.get(position);
        holder.textView.setText(getm.getTitle());
        holder.textView1.setText(getm.getDescription());
        Picasso.get().load(getm.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
            return  listarray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView,textView1;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.id_heading);
            textView1=itemView.findViewById(R.id.id_detail);
            imageView=itemView.findViewById(R.id.id_image);
        }
    }
}
