package com.japocoin.apiproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;

public class acceso extends AppCompatActivity {
    EditText etxtUsuario, etxtPassword;
    Button btnEntrar;

   // RequestQueue requestQueue;

    String baseURL = "http://10.1.2.10/apiservice/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        etxtPassword = findViewById(R.id.etxtPassword);
        etxtUsuario = findViewById(R.id.etxtUsuario);

        
    }
}