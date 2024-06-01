package com.example.parcial_corte3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcial_corte3.vista_registro.Regsitro;

public class MainActivity extends AppCompatActivity {

    Button btn_ingresar, btn_Registrar;
    EditText edt_usuario, edt_contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_ingresar = findViewById(R.id.btn_ingresar);
        btn_Registrar = findViewById(R.id.btn_Registrar);

        edt_usuario = findViewById(R.id.edt_usuario);
        edt_contrasena = findViewById(R.id.edt_contrasena);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usu = edt_usuario.getText().toString();
                String contra = edt_contrasena.getText().toString();

                if (!usu.isEmpty() && !contra.isEmpty()){
                    Intent entrar = new Intent(MainActivity.this, com.example.parcial_corte3.fragments.MainActivity.class);
                    startActivity(entrar);
                }else {
                    Toast.makeText(getApplicationContext(), "Llene los espacios en blanco", Toast.LENGTH_SHORT).show();
                }
                // *****FALTA HACER OTRA VALIDACION PARA COMPROBAR DE QUE
                // EL USUARIO ESTE EN LA BASE DATOS, SI NO LO ESTA
                // AVISARLE QUE NO ESTA Y SE REGISTRE PRIMERO*****
            }
        });

        btn_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(MainActivity.this, Regsitro.class);
                startActivity(registro);
            }
        });

    }
}