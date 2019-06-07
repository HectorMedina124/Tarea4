package com.example.tarea4.Fragments;

import android.content.Context;
import android.content.Intent;
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

import com.example.tarea4.PantallaInicio;
import com.example.tarea4.R;
import com.example.tarea4.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registrar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registrar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registrar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private EditText email2;
    private EditText pass2;
    private TextInputLayout email;
    private TextInputLayout pass;
    private Button btn;
    private FirebaseAuth mAuth;
    private TextView fPass;
    private TextView in;
    private ProgressBar pb;


    private OnFragmentInteractionListener mListener;

    public Registrar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registrar.
     */
    // TODO: Rename and change types and number of parameters
    public static Registrar newInstance(String param1, String param2) {
        Registrar fragment = new Registrar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_registrar, container, false);
        email=view.findViewById(R.id.Email);
        pass=view.findViewById(R.id.Contraseña);
        pass2=pass.getEditText();
        email2=email.getEditText();
        in=view.findViewById(R.id.txtInciarS);
        fPass=view.findViewById(R.id.Pass);
        mAuth = FirebaseAuth.getInstance();
        btn= view.findViewById(R.id.btn);
        pb= view.findViewById(R.id.progressBarR);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!pass2.getText().toString().isEmpty()&&!email2.getText().toString().isEmpty()){
                System.out.println("----------------------------------------: "+pass2.getText().toString());
                pb.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email2.getText().toString(), pass2.getText().toString())
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pb.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(getActivity(), "Email registrado",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getActivity(), PantallaInicio.class);
                                    startActivity(intent);
                                    getActivity().finish();


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getActivity(), "Fallo en registrar email prueba iniciando sesion ",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
            else{
                    Toast.makeText(getActivity(), "Favor de llenar los campos solicitados",
                            Toast.LENGTH_SHORT).show();
                }}
        });
         in.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Fragment nuevo = new IniciarSesion();
                 getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content_main, nuevo).commit();
             }
         });
         fPass.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Fragment nuevo = new ContraseniaOlvidada();
                 getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content_main, nuevo).commit();
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
