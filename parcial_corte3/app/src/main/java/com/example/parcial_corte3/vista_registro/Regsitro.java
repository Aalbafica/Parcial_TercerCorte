package com.example.parcial_corte3.vista_registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.parcial_corte3.R;
import com.example.parcial_corte3.fragments.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Regsitro extends AppCompatActivity {

    EditText edt_nombre, edt_correo, edt_password, edt_fechaNacimiento;
    Button btn_registrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsitro);

        edt_nombre = findViewById(R.id.edt_nombre);
        edt_correo = findViewById(R.id.edt_correo);
        edt_password = findViewById(R.id.edt_password);
        edt_fechaNacimiento = findViewById(R.id.edt_fechaNacimiento);

        btn_registrar = findViewById(R.id.btn_registrar);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // va a recibir cada uno de los datos del editText
                String nombre = edt_nombre.getText().toString();
                String correo = edt_correo.getText().toString();
                String password = edt_password.getText().toString();
                String fecha_nacimiento = edt_fechaNacimiento.getText().toString();
                Log.d("Registro", "Datos: " + nombre + ", " + correo + ", " + password + ", " + fecha_nacimiento);


                Response.Listener<String> respoListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d("Registro1", "Respuesta del servidor: " + response);
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                Intent entrar = new Intent(Regsitro.this, MainActivity.class);
                                startActivity(entrar);
                            }else{
                                Toast.makeText(Regsitro.this, "Error en el Registro", Toast.LENGTH_LONG).show();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                Log.d("Registro", "Boton presionado");
                // objeto Request
                RegisterRequest registerRequest = new RegisterRequest(nombre, correo, password, fecha_nacimiento, respoListener);
                RequestQueue queue = Volley.newRequestQueue(Regsitro.this);
                queue.add(registerRequest);

            }
        });

    }
}