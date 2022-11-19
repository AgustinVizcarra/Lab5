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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class ActualizaAgendaActivity extends AppCompatActivity  {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("actividades");
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();
    Actividad actividad;
    Uri imageUri;
    EditText fechaFin,horaFin,fechaInicio,horaInicio,titulo,descripcion;
    Button btnAgregar,subirImagen;
    ActivityResultLauncher<Intent> openImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{
        if(result.getResultCode()== Activity.RESULT_OK){
            imageUri=result.getData().getData();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Asumiendo que es un proceso de edición
        Intent intent = getIntent();
        //Si en caso viene de parte de un activity anterior
        boolean edicion = intent.hasExtra("actividad");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualiza_agenda);
        fechaFin = (EditText) findViewById(R.id.fechaFin);
        horaFin = (EditText) findViewById(R.id.horaFin);
        fechaInicio = (EditText) findViewById(R.id.fechaInicio);
        horaInicio = (EditText) findViewById(R.id.horaInicio);
        titulo = (EditText) findViewById(R.id.tituloActividad);
        descripcion = (EditText) findViewById(R.id.descripcionActividad);
        btnAgregar = (Button) findViewById(R.id.btnAregar);
        subirImagen = (Button) findViewById(R.id.subirImagen);
        subirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subirImagen();
            }
        });
        if(edicion){
            actividad = (Actividad) intent.getSerializableExtra("actividad");
            btnAgregar.setText("Actualizar Usuario");
            subirImagen.setText("Actualizar Imagen");
            fechaFin.setText(actividad.getFechaFin());
            horaFin.setText(actividad.getHoraFin());
            fechaInicio.setText(actividad.getFechaInicio());
            horaInicio.setText(actividad.getHoraInicio());
            descripcion.setText(actividad.getDescripcion());
            titulo.setText(actividad.getTitulo());
        }
        //
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                String strDATE = LocalDate.now().toString();
                Log.d("msg", strDATE);
                try {
                    SimpleDateFormat inSdf = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat outSdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date currentDate = outSdf.parse(strDATE);
                    Date fechaFinDt = outSdf.parse(outSdf.format(inSdf.parse(fechaFin.getText().toString())));
                    Date fechaInicioDt = outSdf.parse(outSdf.format(inSdf.parse(fechaInicio.getText().toString())));
                    boolean fechaFinvalida = fechaFinDt.after(currentDate);
                    boolean fechaIniciovalida = fechaInicioDt.after(currentDate);
                    if(fechaFinvalida&&fechaIniciovalida){
                        if(edicion){
                            String filename = "";
                            if(imageUri!=null){
                                String[] path= imageUri.toString().split("/");
                                filename = path[path.length-1];
                                StorageReference imageReference = storageReference.child("img/"+filename);
                                imageReference.putFile(imageUri).addOnSuccessListener(taskSnapshot ->
                                        Log.d("msg","Archivo Subido correctamente")).addOnFailureListener(e->Log.d("msg","Error",e.getCause()));
                            }
                            databaseReference.child(actividad.getKey()).child("descripcion").setValue(descripcion.getText().toString());
                            databaseReference.child(actividad.getKey()).child("fechaFin").setValue(fechaFin.getText().toString());
                            databaseReference.child(actividad.getKey()).child("fechaInicio").setValue(fechaInicio.getText().toString());
                            databaseReference.child(actividad.getKey()).child("horaFin").setValue(horaFin.getText().toString());
                            databaseReference.child(actividad.getKey()).child("horaInicio").setValue(horaInicio.getText().toString());
                            databaseReference.child(actividad.getKey()).child("titulo").setValue(titulo.getText().toString());
                            databaseReference.child(actividad.getKey()).child("filename").setValue(imageUri!=null?filename:actividad.getFilename());
                            Toast.makeText(ActualizaAgendaActivity.this, "Data Actualizada correctamente", Toast.LENGTH_SHORT).show();
                        }else{
                            //Creacion
                            if(imageUri!=null){
                                String[] path= imageUri.toString().split("/");
                                String filename = path[path.length-1];
                                StorageReference imageReference = storageReference.child("img/"+filename);
                                imageReference.putFile(imageUri).addOnSuccessListener(taskSnapshot ->
                                        Log.d("msg","Archivo Subido correctamente")).addOnFailureListener(e->Log.d("msg","Error",e.getCause()));
                                //Guardo el valor en la base de datos
                                String keyActividad = databaseReference.push().getKey();
                                Actividad actividad = new Actividad(fechaFin.getText().toString(),horaFin.getText().toString(),fechaInicio.getText().toString(),horaInicio.getText().toString(),titulo.getText().toString(),descripcion.getText().toString(),filename,keyActividad);
                                databaseReference.child(keyActividad).setValue(actividad);
                                fechaFin.setText("");
                                horaFin.setText("");
                                descripcion.setText("");
                                titulo.setText("");
                                fechaInicio.setText("");
                                horaInicio.setText("");
                                imageUri = null;
                                Toast.makeText(ActualizaAgendaActivity.this, "Data Añadida correctamente", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ActualizaAgendaActivity.this, "Debe Añadir una imagen!", Toast.LENGTH_SHORT).show();
                            }
                        }
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
                    System.out.println(e.getMessage());
                }
            }
        });
    }
    public void subirImagen(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        openImageLauncher.launch(intent);
    }
}