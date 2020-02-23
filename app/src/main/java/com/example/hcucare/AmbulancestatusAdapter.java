package com.example.hcucare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AmbulancestatusAdapter extends RecyclerView.Adapter<AmbulancestatusAdapter.ViewHolder> {
    List<getAmbulance> listarray;

    public AmbulancestatusAdapter(List<getAmbulance> list) {
        this.listarray = list;
    }

    @NonNull
    @Override
    public AmbulancestatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ambulancestatus,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmbulancestatusAdapter.ViewHolder holder, int position) {
        getAmbulance getm=listarray.get(position);
        String hostladd=getm.getHostelname()+" Room no: "+getm.getRoomno();
        holder.textView.setText(getm.getToken());
        holder.textView1.setText(hostladd);
        holder.textView2.setText(getm.getCondition());
        holder.textView3.setText(getm.getStatus());
        holder.textView4.setText(getm.getPhoneno());
        holder.textView5.setText(getm.getTime());
    }

    @Override
    public int getItemCount() {
        return  listarray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView,textView1,textView2,textView3,textView4,textView5;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.id_tokenno);
            textView1=itemView.findViewById(R.id.id_hostel);
            textView2=itemView.findViewById(R.id.id_condition);
            textView3=itemView.findViewById(R.id.id_experience);
            textView4=itemView.findViewById(R.id.id_phoneno);
            textView5=itemView.findViewById(R.id.id_time);
        }
    }
}
