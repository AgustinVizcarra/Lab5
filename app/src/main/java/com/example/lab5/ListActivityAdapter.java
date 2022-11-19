package com.example.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab5.Entity.Actividad;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ListActivityAdapter extends RecyclerView.Adapter<ListActivityAdapter.ActivityViewHolder> {
    private List<Actividad> listaActividad;
    private Context context;
    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    StorageReference storageReference = firebaseStorage.getReference();
    private Context mContext = getContext();


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Actividad> getListaActividad() {
        return listaActividad;
    }

    public void setListaActividad(List<Actividad> listaActividad) {
        this.listaActividad = listaActividad;
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder{
        Actividad actividad;


        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ActivityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder holder, int position) {
        Actividad a = listaActividad.get(position);
        holder.actividad = a;
        TextView titulo = holder.itemView.findViewById(R.id.textView_Titulo);
        TextView Descrip = holder.itemView.findViewById(R.id.textView_Descripcion);
        TextView detalle = holder.itemView.findViewById(R.id.textView_Detalle);
        ImageView imageView = holder.itemView.findViewById(R.id.idImagenactividad);
        titulo.setText(a.getTitulo());
        Descrip.setText(a.getDescripcion());
        detalle.setText(a.getDetalle());
        StorageReference imageRef = storageReference.child("img/"+a.getFilename()+".jpeg");
        //Glide.with(mContext).load(imageRef).into(imageView);
    }


    @Override
    public int getItemCount() {
        return listaActividad.size();
    }
}
