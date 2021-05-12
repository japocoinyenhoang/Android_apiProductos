package com.japocoin.apiproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etxtcodigo, etxtproducto, etxtprecio, etxtmarca;
    Button btnBuscar,btnAdd,btnEditar,btnBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Editables textos
        etxtcodigo = findViewById(R.id.etxtCodigo);
        etxtproducto = findViewById(R.id.etxnombre);
        etxtprecio = findViewById(R.id.etxtprecio);
        etxtmarca = findViewById(R.id.etxtmarca);

        //botones
        btnAdd = findViewById(R.id.btnadd);
        btnEditar = findViewById(R.id.btbedit);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnBuscar = findViewById(R.id.btnBuscar);
    }
}