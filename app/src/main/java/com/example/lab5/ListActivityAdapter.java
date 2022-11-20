package com.example.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab5.Entity.Actividad;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();



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
        int posicion = position;
        Actividad a = listaActividad.get(position);
        holder.actividad = a;
        TextView titulo = holder.itemView.findViewById(R.id.textView_Titulo);
        TextView Descrip = holder.itemView.findViewById(R.id.textView_Descripcion);
        TextView detalle = holder.itemView.findViewById(R.id.textView_Detalle);
        ImageView imageView = holder.itemView.findViewById(R.id.idImagenactividad);
        Button buttonBorrar = holder.itemView.findViewById(R.id.btn_eilminar);
        buttonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String solicitudID = a.getKey();
                databaseReference.child("actividades").child(solicitudID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(view.getContext(), "Borrado con exito!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(view.getContext(), "Borrado fallido!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        titulo.setText(a.getTitulo());
        Descrip.setText(a.getDescripcion());
        detalle.setText(a.getDetalle());
        StorageReference imageRef = storageReference.child("img/"+a.getFilename());
        Glide.with(getContext()).load(imageRef).into(imageView);
    }

    @Override
    public int getItemCount() {
        return listaActividad.size();
    }
}
