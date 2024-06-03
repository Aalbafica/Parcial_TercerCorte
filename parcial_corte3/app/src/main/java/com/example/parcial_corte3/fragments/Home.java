package com.example.parcial_corte3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parcial_corte3.R;
import com.example.parcial_corte3.adaptadores.PersonajeAdaptador;
import com.example.parcial_corte3.clases.personajes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    List<personajes> listaPersonaje = new ArrayList<>();

    RecyclerView rcv_marvel;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcv_marvel =  view.findViewById(R.id.rcv_marvel);

        cargarInformacion();

        return view;




    }

    public void cargarInformacion() { // recogiendo la info del api de marvel
        String url = "https://gateway.marvel.com:443/v1/public/characters?apikey=f2504a0cfb1304478b67f0c140fc624d";

        StringRequest miPedido = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    recibirRespuesta(new JSONObject(response));
                } catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Error en el servidor", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error en el servidor", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue rq = Volley.newRequestQueue(getContext());
        rq.add(miPedido);

    }

    private void recibirRespuesta(JSONObject respuesta) {
        try {

            for (int i=0; i<=respuesta.getJSONObject("data").getJSONArray("results").length();i++){

                int id = respuesta.getJSONObject("data").getJSONArray("results").getJSONObject(i).getInt("id");
                String nombre = respuesta.getJSONObject("data").getJSONArray("results").getJSONObject(i).getString("name");
                String descripcion = respuesta.getJSONObject("data").getJSONArray("results").getJSONObject(i).getString("description");
                String ruta = respuesta.getJSONObject("data").getJSONArray("results").getJSONObject(i).getString("path");
                String extension = respuesta.getJSONObject("data").getJSONArray("results").getJSONObject(i).getString("extension");

                personajes p = new personajes(id, nombre, descripcion, ruta, extension);
                listaPersonaje.add(p);

            }

            rcv_marvel.setLayoutManager(new LinearLayoutManager(getContext()));
            rcv_marvel.setAdapter(new PersonajeAdaptador(listaPersonaje));
        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Error en el servidor", Toast.LENGTH_SHORT).show();
        }
    }
}