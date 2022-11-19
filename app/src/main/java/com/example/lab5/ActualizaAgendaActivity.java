package com.example.lab5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab5.Entity.Actividad;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class ActualizaAgendaActivity extends AppCompatActivity  {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();
    Uri imageUri;
    EditText fechaFin,horaFin,fechaInicio,horaInicio,titulo,descripcion;
    Button btnAgregar,subirImagen;
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
        subirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageUri=selectImage();
            }
        });
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
                    if(fechaFinvalida&&fechaIniciovalida&&imageUri!=null){
                        Actividad actividad = new Actividad(fechaFin.getText().toString(),horaFin.getText().toString(),fechaInicio.getText().toString(),horaInicio.getText().toString(),titulo.getText().toString(),descripcion.getText().toString());
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //Guardo el valor en la base de datos
                                databaseReference.setValue(actividad);
                                String[] path= imageUri.toString().split("/");
                                String filename = path[path.length-1];
                                System.out.println("El filename seleccionado es: "+filename);
                                StorageReference imageReference = storageReference.child("img/"+filename);
                                //
                                imageReference.putFile(imageUri).addOnSuccessListener(taskSnapshot ->
                                    Log.d("msg","Archivo Subido correctamente")).addOnFailureListener(e->Log.d("msg","Error",e.getCause()));
                                //
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
                            fechaInicio.setTextColor(Color.RED);
                        }
                        if(!fechaFinvalida){
                            fechaFin.setError("La fecha ingresada es incorrecta");
                            fechaFin.setTextColor(Color.RED);
                        }
                        if(imageUri==null){
                            Toast.makeText(ActualizaAgendaActivity.this, "No se ha seleccionado una foto!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (ParseException e) {
                    Toast.makeText(ActualizaAgendaActivity.this, "Se ingreso un formato de fecha incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //Se crea el metodo para la subida de archivo
    private Uri selectImage(){
        AtomicReference<Uri> auxUri = null;
        ActivityResultLauncher<Intent> openImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{
            if(result.getResultCode()== Activity.RESULT_OK){
                auxUri.set(result.getData().getData());
            }
        });
        Intent intent = new Intent();
        intent.setType("image/*");
        openImageLauncher.launch(intent);
        return auxUri.get();
    }
}