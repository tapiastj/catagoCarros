package com.example.android.catalagocarros;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Agregar_carro extends AppCompatActivity {
    private EditText txtPlaca, txtPrecio;
    private Spinner cmbMarca,cmbModelo,cmbColor;
    private ArrayAdapter<String> adapterMaca,adapterModelo,adapterColor;
    private String opcMarca[],opcModelo[],opcColor[];
    private ArrayList<Integer> fotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carro);

        txtPlaca = findViewById(R.id.txtPlaca);
        txtPrecio = findViewById(R.id.txtPrecio);
        cmbMarca = findViewById(R.id.cmbMarca);
        cmbModelo = findViewById(R.id.cmbModelo);
        cmbColor = findViewById(R.id.cmbColor);

        opcMarca = this.getResources().getStringArray(R.array.arrayMarca);
        adapterMaca = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opcMarca);
        cmbMarca.setAdapter(adapterMaca);

        opcModelo = this.getResources().getStringArray(R.array.arrayModelo);
        adapterModelo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opcModelo);
        cmbModelo.setAdapter(adapterModelo);

        opcColor = this.getResources().getStringArray(R.array.arrayColor);
        adapterColor = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opcColor);
        cmbColor.setAdapter(adapterColor);

        fotos = new ArrayList<Integer>();
        fotos.add(R.drawable.carro1);
        fotos.add(R.drawable.carro2);
        fotos.add(R.drawable.carro3);
        fotos.add(R.drawable.carro4);

    }

    public void registrar(View v){
        String placa, precio;
        int marca,modelo,color, foto;
        foto = fotoAleatoria(fotos);

        if(validar()) {
            placa = txtPlaca.getText().toString().toUpperCase();
            precio = txtPrecio.getText().toString();
            marca = cmbMarca.getSelectedItemPosition();
            modelo = cmbModelo.getSelectedItemPosition();
            color = cmbColor.getSelectedItemPosition();

            Carro c = new Carro(foto, placa, marca, modelo, color, precio);
            Datos.guardar(c);

            Snackbar.make(v, getResources().getString(R.string.mensaje_guardado), Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            limpiar();
        }
    }

    public void limpiar(){
        txtPlaca.setText("");
        txtPrecio.setText("");
        cmbMarca.setSelection(0);
        cmbModelo.setSelection(0);
        cmbColor.setSelection(0);
        txtPlaca.requestFocus();
    }

    public boolean validar(){

        String evalplaca = txtPlaca.getText().toString().trim();
        String evalPrecio = txtPrecio.getText().toString().trim();

        if (evalplaca.isEmpty()){
            txtPlaca.setError("El campo no puede ser vacio");
            txtPlaca.requestFocus();
            return false;
        }else if (evalplaca.length() == 6) {
            Pattern p = Pattern.compile("^[a-zA-Z]*$");
            Matcher m = p.matcher(evalplaca.substring(0, 3));
            boolean b = m.matches();

            if (b == false) {
                txtPlaca.setError("Los tres primeros caracteres de la placa deben ser letras");
                txtPlaca.requestFocus();
                return false;
            }
            p = Pattern.compile("^[0-9]*$");
            m = p.matcher(evalplaca.substring(3, 6));
            b = m.matches();

            if (b == false) {
                txtPlaca.setError("Los tres ultimos caracteres deben ser numeros");
                txtPlaca.requestFocus();
                return false;
            }
        }else{
            txtPlaca.setError("La placa debe tener 6 caracteres");
            txtPlaca.requestFocus();
            return false;
        }
        if (evalPrecio.isEmpty()){
            txtPrecio.setError("El campo no puede ser vacio");
            txtPrecio.requestFocus();
            return false;
        }

        return true;
    }

    public void limpiar(View v){
        limpiar();
    }

    public static int fotoAleatoria(ArrayList<Integer> fotos){
        int fotoSeleccionada;
        Random r = new Random();
        fotoSeleccionada = r.nextInt(fotos.size());
        return fotos.get(fotoSeleccionada);
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(Agregar_carro.this,Principal.class);
        startActivity(i);
    }
}
