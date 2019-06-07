package com.example.tarea4;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarea4.Fragments.ContraseniaOlvidada;
import com.example.tarea4.Fragments.IniciarSesion;
import com.example.tarea4.Fragments.Registrar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements Registrar.OnFragmentInteractionListener,
        IniciarSesion.OnFragmentInteractionListener,
        ContraseniaOlvidada.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment nuevo = new IniciarSesion();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main, nuevo).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
