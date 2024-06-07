package com.example.parcial_corte3.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial_corte3.R;
import com.example.parcial_corte3.RecyclerViewInterface;
import com.example.parcial_corte3.clases.personajes;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PersonajeAdaptador extends RecyclerView.Adapter<PersonajeAdaptador.ViewHolder> {

    private RecyclerViewInterface recyclerViewInterface;
    private List<personajes> datos;

    public PersonajeAdaptador(List<personajes> datos, RecyclerViewInterface recyclerViewInterface){
        this.datos = datos;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public PersonajeAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personajes, parent, false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonajeAdaptador.ViewHolder holder, int position) {
        personajes dato = datos.get(position);
        holder.bind(dato);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_id, txt_nombre_personaje,txt_descripcion;
        ImageView img_personaje;

        Button VerDetalles;


        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_nombre_personaje = itemView.findViewById(R.id.txt_nombre_personaje);
            txt_descripcion = itemView.findViewById(R.id.txt_descripcion);

            img_personaje = itemView.findViewById(R.id.img_personaje);

            VerDetalles = itemView.findViewById(R.id.btn_VerDetalles);

            VerDetalles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface !=null){
                        int pos = getAdapterPosition();

                        if (pos!= RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }

                    }
                }
            });

        }

        public void bind(personajes dato){
            txt_id.setText(dato.getId());
            txt_nombre_personaje.setText(dato.getName());
            txt_descripcion.setText(dato.getDescription());

            // imagen picasso
            Picasso.get().load(dato.getImage()).into(img_personaje);

        }

    }

}
