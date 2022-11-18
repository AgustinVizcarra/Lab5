package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class InicioSesion extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        firebaseAuth = firebaseAuth.getInstance();
        /**
        String mensaje_exito = getIntent().getStringExtra("exito");
        if (mensaje_exito != null && !mensaje_exito.equals("")) {
            Snackbar.make(findViewById(R.id.activity_iniciar_sesion), mensaje_exito, Snackbar.LENGTH_LONG).show();
        }**/

    }
}