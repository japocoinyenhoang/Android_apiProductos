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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProducto("http://192.168.64.2//apiservice/insertar_producto.php");
            }
        });
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}