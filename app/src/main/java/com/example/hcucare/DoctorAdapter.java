package com.example.hcucare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    List<getDoctors> listarray;

    public DoctorAdapter(List<getDoctors> list) {
        this.listarray = list;
    }

    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_doctors,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {
        getDoctors getm=listarray.get(position);
        holder.textView.setText(getm.getName());
        holder.textView1.setText(getm.getSpecialist());
        holder.textView2.setText(getm.getDegree());
        holder.textView3.setText(getm.getExperience());
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
