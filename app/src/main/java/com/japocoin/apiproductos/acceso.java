package com.japocoin.apiproductos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class acceso extends AppCompatActivity {
    EditText etxtUsuario, etxtPassword;
    Button btnEntrar;

   RequestQueue requestQueue;

    String baseURL = "http://10.1.2.10/apiservice/";

    String usuario, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        etxtPassword = findViewById(R.id.etxtPassword);
        etxtUsuario = findViewById(R.id.etxtUsuario);

        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = etxtUsuario.getText().toString();
                password = etxtPassword.getText().toString();
                if (!usuario.isEmpty() && !password.isEmpty()) {
                    validarUsuario(baseURL + "validar_usuario.php");
                } else {
                    Toast.makeText(getApplicationContext(), "no se permiten campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    private void validarUsuario(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               if (!response.isEmpty()) {
                   //ha encontrado los parametros de usuario y contraseña en la base de datos
                   //y le mando a la otra pantalla
                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(intent);
                   finish();
               }else {
                   Toast.makeText(getApplicationContext(), "usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error al añadir" + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("usuario", etxtUsuario.getText().toString());
                parametros.put("password", etxtPassword.getText().toString());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}