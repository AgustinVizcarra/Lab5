package com.example.lab5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActividadesAdapter extends FirebaseRecyclerAdapter<Actividad,ActividadesAdapter.myViewHolder> {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();


    public ActividadesAdapter(@NonNull FirebaseRecyclerOptions<Actividad> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Actividad actividad) {
        int orden = position;
        holder.botonBorrarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String solicitudID = getRef(orden).getKey();

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



    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list,parent,false);
        return new myViewHolder(view);
    }



    public class myViewHolder extends RecyclerView.ViewHolder{
        Button botonBorrarActividad;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            //botonBorrarActividad = itemView.findViewById(R.id.botonVerDetalle);

        }
    }
}
