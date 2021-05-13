package com.japocoin.apiproductos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText etxtcodigo, etxtproducto, etxtprecio, etxtmarca;
    Button btnBuscar,btnAdd,btnEditar,btnBorrar;

    RequestQueue requestQueue;

    String baseURL = "http://10.1.2.10/apiservice/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtcodigo = findViewById(R.id.etxtCodigo);
        etxtproducto = findViewById(R.id.etxnombre);
        etxtprecio = findViewById(R.id.etxtprecio);
        etxtmarca = findViewById(R.id.etxtmarca);

        btnAdd = findViewById(R.id.btnadd);
        btnEditar = findViewById(R.id.btbedit);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnBuscar = findViewById(R.id.btnBuscar);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProducto( baseURL +"insertar_producto.php");
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleartxt();
                buscarProducto( baseURL +"iproducto.php?codigo="+etxtcodigo.getText()+"");
            }
        });

        btnEditar.setOnClickListener();
    }
    private  void addProducto(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Añadido correctamente", Toast.LENGTH_LONG).show();

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
                parametros.put("codigo", etxtcodigo.getText().toString());
                parametros.put("producto", etxtproducto.getText().toString());
                parametros.put("precio", etxtprecio.getText().toString());
                parametros.put("marca", etxtmarca.getText().toString());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void buscarProducto(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        etxtproducto.setText(jsonObject.getString("producto"));
                        etxtprecio.setText(jsonObject.getString("precio"));
                        etxtmarca.setText(jsonObject.getString("marca"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error de conexión o código no encontrado", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private  void cleartxt(){
        etxtproducto.setText("");
        etxtprecio.setText("");
        etxtmarca.setText("");
    }


}