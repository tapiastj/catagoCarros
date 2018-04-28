package com.example.android.catalagocarros;


import java.util.ArrayList;

public class Datos {
    private static ArrayList<Carro> lista = new ArrayList<Carro>();

    public static void guardar(Carro o) {
        lista.add(o);
    }

    public static ArrayList<Carro> getCarros() {
        return lista;
    }
}