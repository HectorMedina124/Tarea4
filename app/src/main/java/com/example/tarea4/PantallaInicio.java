package com.example.tarea4;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.tarea4.Fragments.CambiarContra;
import com.example.tarea4.Fragments.CambiarEmail;
import com.example.tarea4.Fragments.FragmentInicio;
import com.example.tarea4.Fragments.Registrar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PantallaInicio extends AppCompatActivity implements CambiarEmail.OnFragmentInteractionListener,
FragmentInicio.OnFragmentInteractionListener,CambiarContra.OnFragmentInteractionListener{
    private FirebaseUser user;
    private FirebaseAuth mAuht;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);
        Fragment nuevo = new FragmentInicio();
        getSupportFragmentManager().beginTransaction().add(R.id.Pantallla, nuevo,"Inicio").commit();

    }
    public void cambiarEmail(View view){

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
