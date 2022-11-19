package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.lab5.Entity.Actividad;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //Obtener los datos
        //Proceso

        /**
        Actividad actividad[];
        ListActivityAdapter activityAdapter = new ListActivityAdapter();
        activityAdapter.setListaActividad(actividad);
        activityAdapter.setContext(ListActivity.this);
        RecyclerView recyclerView = findViewById(R.id.List);
        recyclerView.setAdapter(activityAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
         **/
    }
}