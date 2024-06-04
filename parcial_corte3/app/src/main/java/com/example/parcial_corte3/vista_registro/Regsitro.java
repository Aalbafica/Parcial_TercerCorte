package com.example.parcial_corte3.vista_registro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.example.parcial_corte3.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Regsitro extends AppCompatActivity {

    EditText edt_user, edt_correo, edt_password, edt_fechaNacimiento;
    Button btn_registrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsitro);

        edt_user = findViewById(R.id.edt_user);
        edt_correo = findViewById(R.id.edt_correo);
        edt_password = findViewById(R.id.edt_password);
        edt_fechaNacimiento = findViewById(R.id.edt_fechaNacimiento);

        btn_registrar = findViewById(R.id.btn_registrar);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // va a recibir cada uno de los datos del editText
                String nombre = edt_user.getText().toString();
                String correo = edt_correo.getText().toString();
                String password = edt_password.getText().toString();
                String fecha_nacimiento = edt_fechaNacimiento.getText().toString();

                Response.Listener<String> respoListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };


                // objeto Request
                RegisterRequest registerRequest = new RegisterRequest(nombre, correo, password, fecha_nacimiento, respoListener);

            }
        });

    }
}