package com.example.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class CambioContrasenia extends AppCompatActivity {

    boolean correoValido = true;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_contrasenia);
        getSupportActionBar().setTitle("Cambio de contraseña");
        firebaseAuth = firebaseAuth.getInstance();
    }

    public void validarCorreo(View view) {
        TextInputLayout correo = findViewById(R.id.inputCorreo_cambiocontrasenia);

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+";
        boolean correoValido = true;
        if (correo.getEditText().getText().toString() != null && !correo.getEditText().getText().toString().equals("")) {
            if (!correo.getEditText().getText().toString().matches(emailPattern)) {
                correo.setError("Ingrese un correo válido");
                correoValido = false;
            } else {
                correo.setErrorEnabled(false);
            }
        } else {
            correo.setError("Ingrese un correo");
            correoValido = false;
        }

        if (correoValido) {
            firebaseAuth.sendPasswordResetEmail(correo.getEditText().getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Log.d("forgetpsw", "Correo enviado para cambio de contrasenia");
                    Intent intent = new Intent(CambioContrasenia.this, InicioSesion.class);
                    intent.putExtra("exito", "Se le ha enviado un correo para proceder con la solicitud de cambio de contraseña");
                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("forgetpsw", "Ourrio un error - " + e.getMessage());
                }
            });
        }
    }

}