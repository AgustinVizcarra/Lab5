package com.example.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5.Entity.Actividad;

public class ListActivityAdapter extends RecyclerView.Adapter<ListActivityAdapter.ActivityViewHolder> {
    private Actividad[] listaActividad;
    private Context context;

    public class ActivityViewHolder extends RecyclerView.ViewHolder{
        Actividad actividad;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        return new ActivityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder holder, int position) {
        Actividad a = listaActividad[position];
        holder.actividad = a;
        TextView nombreActividad = holder.itemView.findViewById(R.id.nombreActividad);
        TextView fechaInicio = holder.itemView.findViewById(R.id.fechaInicio);
        TextView horaInicio = holder.itemView.findViewById(R.id.horaInicio);
        TextView fechaFin = holder.itemView.findViewById(R.id.fechaFin);
        TextView horaFin = holder.itemView.findViewById(R.id.horaFin);
        nombreActividad.setText(a.getNombre());
        fechaInicio.setText(a.getFechaInicio());
        fechaFin.setText(a.getFechaFin());
        horaInicio.setText(a.getHoraInicio());
        horaFin.setText(a.getHoraFin());
    }


    @Override
    public int getItemCount() {
        return listaActividad.length;
    }
}
