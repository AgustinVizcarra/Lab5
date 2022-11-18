package com.example.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class ActualizaAgendaActivity extends AppCompatActivity  {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    EditText fechaFin,horaFin,fechaInicio,horaInicio,titulo,descripcion;
    Button btnAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualiza_agenda);
        fechaFin = (EditText) findViewById(R.id.fechaFin);
        horaFin = (EditText) findViewById(R.id.horaFin);
        fechaInicio = (EditText) findViewById(R.id.fechaInicio);
        horaInicio = (EditText) findViewById(R.id.horaInicio);
        titulo = (EditText) findViewById(R.id.tituloActividad);
        descripcion = (EditText) findViewById(R.id.descripcionActividad);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                String strDATE = Calendar.getInstance().getTime().toString();
                try {
                    Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(strDATE);
                    Date fechaFinDt = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin.getText().toString());
                    Date fechaInicioDt = new SimpleDateFormat("yyy-MM-dd").parse(fechaInicio.getText().toString());
                    boolean fechaFinvalida = fechaFinDt.after(currentDate)&&fechaFinDt.after(fechaInicioDt);
                    boolean fechaIniciovalida = fechaInicioDt.after(currentDate);
                    if(fechaFinvalida&&fechaIniciovalida){
                        Actividad actividad = new Actividad(fechaFin.getText().toString(),horaFin.getText().toString(),fechaInicio.getText().toString(),horaInicio.getText().toString(),titulo.getText().toString(),descripcion.getText().toString());
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                databaseReference.setValue(actividad);
                                Toast.makeText(ActualizaAgendaActivity.this, "Data Añadida correctamente", Toast.LENGTH_SHORT).show();
                                fechaFin.setText("");
                                horaFin.setText("");
                                descripcion.setText("");
                                titulo.setText("");
                                fechaInicio.setText("");
                                horaInicio.setText("");
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }else{
                        if(!fechaIniciovalida){
                            fechaInicio.setError("La fecha ingresada es incorrecta");
                            fechaInicio.setTextColor(R.color.red);
                        }
                        if(!fechaFinvalida){
                            fechaFin.setError("La fecha ingresada es incorrecta");
                            fechaFin.setTextColor(R.color.red);
                        }
                    }
                } catch (ParseException e) {
                    Toast.makeText(ActualizaAgendaActivity.this, "Se ingreso un formato de fecha incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}