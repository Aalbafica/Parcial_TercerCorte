package com.example.parcial_corte3.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.parcial_corte3.MainActivity;
import com.example.parcial_corte3.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Config_fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Config_fragment3 extends Fragment {
    public static final String dataUser = "dataUser";
    private static final int modo_private = Context.MODE_PRIVATE;
    String dato;
    TextView txt_usu, txt_correo, txt_contra, txt_fecha;

    Button btn_cerrarSesion;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3_config, container, false);

        txt_usu = view.findViewById(R.id.txt_usu);
        txt_correo = view.findViewById(R.id.txt_correo);
        txt_contra = view.findViewById(R.id.txt_contra);
        txt_fecha = view.findViewById(R.id.txt_fecha);

        btn_cerrarSesion = view.findViewById(R.id.btn_cerrarSesion);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(dataUser, modo_private);
        String usuario = sharedPreferences.getString("usuario", "usuario no encontrado");
        String correo = sharedPreferences.getString("correo", "correo no encontrado");
        String contrasena = sharedPreferences.getString("contrasena", "contrasena no encontrado");
        String fecha = sharedPreferences.getString("fecha", "fecha no encontrado");


        txt_usu.setText(usuario);
        txt_correo.setText(correo);
        txt_contra.setText(contrasena);
        txt_fecha.setText(fecha);

        btn_cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences config = requireActivity().getSharedPreferences(dataUser, modo_private);
                config.edit().clear().commit();
                Intent back = new Intent(getActivity(), MainActivity.class);
                startActivity(back);
                requireActivity().finish();
            }
        });

        return view;

    }
}