package com.example.enriq.ecards;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ValentinaR on 22/01/2018.
 */

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>{
    private List<Usuario> items;

    class UsuarioViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView nombre, horasl, horast,hl,ht;

        UsuarioViewHolder(View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.imageV);
            nombre = itemView.findViewById(R.id.nombre);
            horasl = itemView.findViewById(R.id.horas1);
            horast = itemView.findViewById(R.id.horas2);
            hl = itemView.findViewById(R.id.hlab);
            ht = itemView.findViewById(R.id.htra);
        }
    }

    UsuarioAdapter(List<Usuario> items){
        this.items = items;
    }

    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.usuario_card, parent, false);
        return new UsuarioAdapter.UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UsuarioViewHolder holder, int position) {
        holder.imagen.setImageResource(items.get(position).getImage());
        holder.nombre.setText(items.get(position).getNombre());
        holder.horasl.setText(items.get(position).getHorasl());
        holder.horast.setText(items.get(position).getHorast());
        holder.hl.setText(items.get(position).getHl());
        holder.ht.setText(items.get(position).getHt());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
