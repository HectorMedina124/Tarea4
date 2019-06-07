package com.example.tarea4.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarea4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContraseniaOlvidada.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContraseniaOlvidada#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContraseniaOlvidada extends Fragment {
    private EditText email;
    private Button btn;
    private TextView regresar;
    private TextView regresar2;
    private View view;
    private OnFragmentInteractionListener mListener;
    private FirebaseAuth mAuth;
    private ProgressBar pb;


    public ContraseniaOlvidada() {

    }


    public static ContraseniaOlvidada newInstance(String param1, String param2) {
        ContraseniaOlvidada fragment = new ContraseniaOlvidada();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_contrasenia_olvidada, container, false);
        btn=view.findViewById(R.id.btnFp);
        email=view.findViewById(R.id.emailFp);
        regresar=view.findViewById(R.id.RegresarFp);
        mAuth = FirebaseAuth.getInstance();
        pb=view.findViewById(R.id.progressBarFp);
        regresar2=view.findViewById(R.id.Regresar2Fp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.getText().toString().isEmpty()) {
                    pb.setVisibility(View.VISIBLE);
                    mAuth.sendPasswordResetEmail(email.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    pb.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Email enviado",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(), "Email no valido",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(getActivity(), "Email no ingresado",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

         regresar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Fragment f= new IniciarSesion();
                 getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content_main,f).commit();
             }
         });
         regresar2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Fragment f= new Registrar();
                 getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content_main,f).commit();
             }
         });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
