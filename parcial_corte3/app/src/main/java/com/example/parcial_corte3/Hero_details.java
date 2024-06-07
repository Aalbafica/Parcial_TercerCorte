package com.example.parcial_corte3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Hero_details extends AppCompatActivity {
    TextView txt_id2, txt_nombre_personaje2, txt_descripcion;
    ImageView img_personaje2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_details);

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("nombre_personaje");
        String descripcion = getIntent().getStringExtra("descripcion");
        String imagen = getIntent().getStringExtra("img_personaje");

        txt_id2 = findViewById(R.id.txt_id2);
        txt_nombre_personaje2 = findViewById(R.id.txt_nombre_personaje2);
        txt_descripcion = findViewById(R.id.txt_descripcion2);
        img_personaje2 = findViewById(R.id.img_personaje2);

        txt_id2.setText(id);
        txt_nombre_personaje2.setText(name);
        txt_descripcion.setText(descripcion);
        Picasso.get().load(imagen).into(img_personaje2);



    }
}