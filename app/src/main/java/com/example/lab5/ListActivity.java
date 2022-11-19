package com.example.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.lab5.Entity.Actividad;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<Actividad> actividad = new ArrayList<Actividad>();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        Log.d("msg", "1.sa");

        //Firebase
        getFromFirebased();
    }

    private void getFromFirebased(){
        firebaseDatabase.getReference().child("actividades").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot children: snapshot.getChildren()){
                    Actividad actividad1 = children.getValue(Actividad.class);
                    Log.d("Osc", actividad1.getDetalle());
                    actividad.add(actividad1);
                }
                ListActivityAdapter activityAdapter = new ListActivityAdapter();
                activityAdapter.setListaActividad(actividad);
                activityAdapter.setContext(mContext);
                RecyclerView recyclerView = findViewById(R.id.Recycler_list);
                recyclerView.setAdapter(activityAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}