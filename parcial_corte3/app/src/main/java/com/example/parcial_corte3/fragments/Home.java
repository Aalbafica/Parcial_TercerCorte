package com.example.parcial_corte3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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

import org.json.JSONArray;
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
        String url = "https://gateway.marvel.com:443/v1/public/characters?ts=1717464391&apikey=f2504a0cfb1304478b67f0c140fc624d&hash=6c32ab4bbde49d4466aeca72ccbb101b";


        StringRequest miPedido = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("API_RESPONSE",response);
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
            JSONArray results = respuesta.getJSONObject("data").getJSONArray("results");
            for (int i=0; i<=results.length();i++){

                int id = results.getJSONObject(i).getInt("id");
                String nombre = results.getJSONObject(i).getString("name");
                String descripcion = results.getJSONObject(i).getString("description");
                String ruta = respuesta.getJSONObject("thumbnail").getString("path");
                String extension = respuesta.getJSONObject("thumbnail").getString("extension");

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