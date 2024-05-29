package com.example.parcial_corte3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.parcial_corte3.R;
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

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });




    }

    private void recibirRespuesta(JSONObject jsonObject) {
    }
}