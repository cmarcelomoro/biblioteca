package com.example.biblioteca.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.biblioteca.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

    }

    public void botaoAdicionarLivro(View v){

        Intent itAdicionarLivro = new Intent(this, AdicionarActivity.class);
        startActivity(itAdicionarLivro);

    }

    public void listarLivros(View v){
        //Cria um objeto para gerenciar a fila de requisições
        RequestQueue fila = Volley.newRequestQueue(this);

        //URL que será feita a requisição
        String url ="http://172.25.0.199/bibliotecascripts/listar.php";
        final JSONObject corpo = new JSONObject();
        try {
            corpo.put("x", "oi");
            //Montando o jsonObjectRequest com a requisição que desejamos fazer
            JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        //espera-se uma resposta também no formato JSON
                        public void onResponse(JSONArray listaLivros) {
                            try {
                                String ids = "";
                                for (int i=0; i< listaLivros.length(); i++){
                                    JSONObject livro = listaLivros.getJSONObject(i);
                                    ids+=livro.getString("titulo")+", ";
                                }
                                Toast.makeText(HomeActivity.this, "recebi "+ids, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(HomeActivity.this, "Erro " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("ERRO", error.getMessage());
                }
            });
            // Adiciona a requisição na fila de requisições.
            fila.add(jsonRequest);
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }








}
