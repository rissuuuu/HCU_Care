package com.example.hcucare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentstatusAdapter extends RecyclerView.Adapter<AppointmentstatusAdapter.ViewHolder> {
    List<getAppointmentstatus> listarray;

    public AppointmentstatusAdapter(List<getAppointmentstatus> list) {
        this.listarray = list;
    }

    @NonNull
    @Override
    public AppointmentstatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_appointmentstatus,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentstatusAdapter.ViewHolder holder, int position) {
        getAppointmentstatus getm=listarray.get(position);
        holder.textView.setText(getm.getToken_no());
        holder.textView1.setText(getm.getSymptom());
        holder.textView2.setText(getm.getTime());
        holder.textView3.setText(getm.getStatus());
    }

    @Override
    public int getItemCount() {
        return  listarray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView,textView1,textView2,textView3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.id_Doctorname);
            textView1=itemView.findViewById(R.id.id_specialist);
            textView2=itemView.findViewById(R.id.id_degree);
            textView3=itemView.findViewById(R.id.id_experience);
        }
    }
}
