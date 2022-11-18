package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lab5.Entity.Actividad;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //Obtener los datos
        //Proceso
        Actividad actividad[];
        ListActivityAdapter activityAdapter = new ListActivityAdapter();
        activityAdapter.setListaActividad(actividad);

    }
}