package com.example.parcial_corte3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parcial_corte3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Solicitar_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Solicitar_fragment extends Fragment {

    Spinner sp_comics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2_solicitar, container, false);

        sp_comics = view.findViewById(R.id.sp_comics);

        cargarComics();

        return view;
    }

    private void cargarComics() {
        String url = "https://gateway.marvel.com:443/v1/public/characters?ts=1717464391&apikey=f2504a0cfb1304478b67f0c140fc624d&hash=6c32ab4bbde49d4466aeca72ccbb101b";

        StringRequest pedidoComics = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    recibirComics(new JSONObject(response));
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Error en el servidor1", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error en el servidor2", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue rq = Volley.newRequestQueue(requireContext());
        rq.add(pedidoComics);
    }

    private void recibirComics(JSONObject respuesta) {
        try {
            JSONArray results = respuesta.getJSONObject("data").getJSONArray("results");

            List<String> listaComics = new ArrayList<>();

            for (int i=0; i<results.length();i++){
                JSONObject personaje = results.getJSONObject(i);
                JSONObject comics = personaje.getJSONObject("comics");
                JSONArray items = comics.getJSONArray("items");
                for (int j=0; j<items.length();i++){

                    JSONObject item = items.getJSONObject(j);
                    String titulo = item.getString("name");
                    listaComics.add(titulo);


                }



            }
            llenarSpinner(listaComics);
        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Error en el servidor3", Toast.LENGTH_SHORT).show();
        }
    }

    private void llenarSpinner(List<String> listaComics) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, listaComics);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_comics.setAdapter(adapter);
    }
}