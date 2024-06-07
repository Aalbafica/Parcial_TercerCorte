package com.example.parcial_corte3.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parcial_corte3.Hero_details;
import com.example.parcial_corte3.R;
import com.example.parcial_corte3.RecyclerViewInterface;
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
public class Home extends Fragment implements RecyclerViewInterface {

    List<personajes> listaPersonaje = new ArrayList<>();

    personajes personajes1 = new personajes();
    RecyclerView rcv_marvel;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcv_marvel =  view.findViewById(R.id.rcv_marvel);



        cargarInformacion();




        /*if (edt_filtro.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "No puede estar vacio", Toast.LENGTH_LONG).show();
        }else{

        }**/



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
                    Toast.makeText(getContext(), "Error en el servidor1", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error"+ error.toString());
                Toast.makeText(getContext(), "Error en el servidor2", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue rq = Volley.newRequestQueue(requireContext());
        rq.add(miPedido);

    }
    private void recibirRespuesta(JSONObject respuesta) {
        try {
            JSONArray results = respuesta.getJSONObject("data").getJSONArray("results");
            for (int i=0; i<results.length();i++){

                String id = results.getJSONObject(i).getString("id");
                String nombre = results.getJSONObject(i).getString("name");
                String descripcion = results.getJSONObject(i).getString("description");
                String image = results.getJSONObject(i).getJSONObject("thumbnail").getString("path") + "." + results.getJSONObject(i).getJSONObject("thumbnail").getString("extension");

                personajes p = new personajes(id, nombre,descripcion, image);
                listaPersonaje.add(p);

            }


            rcv_marvel.setLayoutManager(new LinearLayoutManager(getContext()));
            rcv_marvel.setAdapter(new PersonajeAdaptador(listaPersonaje, this));

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Error en el servidor3", Toast.LENGTH_SHORT).show();
        }
    }
    // metodo que pasa los datos hacia Hero_details.java
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), Hero_details.class);
        intent.putExtra("id", listaPersonaje.get(position).getId());
        intent.putExtra("nombre_personaje", listaPersonaje.get(position).getName());
        intent.putExtra("descripcion", listaPersonaje.get(position).getDescription());
        intent.putExtra("img_personaje", listaPersonaje.get(position).getImage());
        startActivity(intent);
    }
}