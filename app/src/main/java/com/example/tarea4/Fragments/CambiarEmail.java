package com.example.tarea4.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CambiarEmail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CambiarEmail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CambiarEmail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private Button cambiar;
    private EditText emailNuevo;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TextView regresar;
    private ProgressBar pb;

    private OnFragmentInteractionListener mListener;

    public CambiarEmail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CambiarEmail.
     */
    // TODO: Rename and change types and number of parameters
    public static CambiarEmail newInstance(String param1, String param2) {
        CambiarEmail fragment = new CambiarEmail();
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
        view=inflater.inflate(R.layout.fragment_cambiar_email, container, false);
        emailNuevo= view.findViewById(R.id.emailEmail);
        cambiar=view.findViewById(R.id.btnEmail2);
        regresar=view.findViewById(R.id.RegresarCE);
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        pb=view.findViewById(R.id.progressBarCE);
        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emailNuevo.getText().toString().isEmpty()){
                    pb.setVisibility(View.VISIBLE);
                    currentUser.updateEmail(emailNuevo.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    pb.setVisibility(View.GONE);
                                  if(task.isSuccessful()){
                                      Toast.makeText(getActivity(), "Email Cambiado",
                                              Toast.LENGTH_SHORT).show();
                                      Fragment f= new FragmentInicio();
                                      getActivity().getSupportFragmentManager().beginTransaction().add(R.id.Pantallla,f,"Inicio").commit();
                                      getFragmentManager(). beginTransaction().remove(getFragmentManager().findFragmentByTag("CCEMAIL"));

                                  }
                                  else{
                                      Toast.makeText(getActivity(), "Fallo en actualizar email",
                                              Toast.LENGTH_SHORT).show();
                                  }
                                }
                            });
                }
                else{
                    Toast.makeText(getActivity(), "Favor de introducir el nuevo email",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f=new FragmentInicio();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.Pantallla,f,"Inicio").commit();
                getFragmentManager(). beginTransaction().remove(getFragmentManager().findFragmentByTag("CCEMAIL"));

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
