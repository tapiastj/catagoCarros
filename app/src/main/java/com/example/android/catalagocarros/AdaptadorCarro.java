package com.example.android.catalagocarros;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by android on 28/04/2018.
 */

public class AdaptadorCarro extends RecyclerView.Adapter<AdaptadorCarro.CarroViewHolder> {
    private ArrayList<Carro> carros;

    public AdaptadorCarro(ArrayList<Carro> carros){
        this.carros=carros;
    }

    @Override
    public CarroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_carros,parent,false);
        return new CarroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CarroViewHolder holder, int position) {
        Resources res = holder.itemView.getContext().getResources();
        Carro c = carros.get(position);
        holder.foto.setImageResource(c.getFoto());
        holder.placa.setText(c.getPlaca());
        holder.marca.setText(res.getStringArray(R.array.arrayMarca)[c.getMarca()]);
        holder.modelo.setText(res.getStringArray(R.array.arrayModelo)[c.getModelo()]);
        holder.color.setText(res.getStringArray(R.array.arrayColor)[c.getColor()]);
        holder.precio.setText(c.getPrecio());
    }

    @Override
    public int getItemCount() {
        return carros.size();
    }

    public static class CarroViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView placa;
        private TextView marca;
        private TextView modelo;
        private TextView color;
        private TextView precio;
        private View v;
        public CarroViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.imgFoto);
            placa = v.findViewById(R.id.txtPlaca);
            marca = v.findViewById(R.id.txtMarca);
            modelo = v.findViewById(R.id.txtModelo);
            color = v.findViewById(R.id.txtColor);
            precio = v.findViewById(R.id.txtPrecio);
        }
    }

}
