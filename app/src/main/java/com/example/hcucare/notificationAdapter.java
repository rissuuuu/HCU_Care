package com.example.hcucare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class notificationAdapter extends RecyclerView.Adapter<notificationAdapter.ViewHolder> {
    List<getnotifications> listarray;

    public notificationAdapter(List<getnotifications> list) {
        this.listarray = list;
    }

    @NonNull
    @Override
    public notificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notification,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notificationAdapter.ViewHolder holder, int position) {
        getnotifications getm=listarray.get(position);
        holder.textView.setText(getm.getHeading());
        holder.textView1.setText(getm.getNotifications());
        holder.textViewtime.setText(getm.getTime());
    }

    @Override
    public int getItemCount() {
        return  listarray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView,textView1,textViewtime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.id_heading);
            textView1=itemView.findViewById(R.id.id_message);
            textViewtime=itemView.findViewById(R.id.id_time);
        }
    }
}
