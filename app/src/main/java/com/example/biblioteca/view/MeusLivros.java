package com.example.biblioteca.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biblioteca.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MeusLivros extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meuslivros);
        getSupportActionBar().hide();
    }

    public void botaoListarLivros(View v){

        RequestQueue fila = Volley.newRequestQueue(this);
        String url = "http://"+Constantes.ip+"/bibliotecascripts/listar.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String resposta = response.substring(0).trim();
                        System.out.println(resposta);
                        //testa o valor da resposta obtida do servidor
                        if (resposta.equals("id_recebido")) {
                            System.out.println("id foi recebido pelo server");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        System.out.println("Erro de comunicação");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("meuId",Constantes.id);
                return params;
            }

        };

        fila.add(stringRequest);

        //Montando o jsonObjectRequest com a requisição que desejamos fazer
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    //espera-se uma resposta também no formato JSON
                    public void onResponse(JSONArray listaLivros) {
                        try {
                            Toast.makeText(MeusLivros.this, "oi", Toast.LENGTH_SHORT).show();
                            String titulos = "";
                            for (int i=0; i< listaLivros.length(); i++){
                                JSONObject livro = listaLivros.getJSONObject(i);
                                titulos+=livro.getString("titulo")+", ";
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MeusLivros.this, "Erro " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("ERRO", error.getMessage());
            }
        });
        // Adiciona a requisição na fila de requisições.
        fila.add(jsonRequest);

    }
}
