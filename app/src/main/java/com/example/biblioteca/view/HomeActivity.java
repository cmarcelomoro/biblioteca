package com.example.biblioteca.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biblioteca.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    EditText editTextTituloBusca;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        editTextTituloBusca = findViewById(R.id.editTextTituloBusca);


    }

    public void botaoAdicionarLivro(View v){

        Intent itAdicionarLivro = new Intent(this, AdicionarActivity.class);
        startActivity(itAdicionarLivro);

    }
    public void botaoMeusLivros(View v){
        Intent itMeusLivros = new Intent(this,MeusLivros.class);
        startActivity(itMeusLivros);
    }

    public void listarLivros(View v){
        //Cria um objeto para gerenciar a fila de requisições

        RequestQueue fila = Volley.newRequestQueue(this);
        //URL que será feita a requisição
        String url ="http://"+Constantes.ip+"/bibliotecascripts/listar.php";
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
                                String titulos = "";
                                for (int i=0; i< listaLivros.length(); i++){
                                    JSONObject livro = listaLivros.getJSONObject(i);
                                    titulos+=livro.getString("titulo")+", ";
                                }
                                Toast.makeText(HomeActivity.this, titulos, Toast.LENGTH_SHORT).show();
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

    public void buscarLivro(View v){
        String tituloBusca = editTextTituloBusca.getText().toString();
        if(tituloBusca.isEmpty()){
            System.out.println("Insira um titulo!");
        }else {
            RequestQueue fila = Volley.newRequestQueue(this);

            //URL que será feita a requisição
            String url ="http://"+Constantes.ip+"/bibliotecascripts/buscarlivro.php";
            try {
                Intent itResultado = new Intent(this,ResultadoBusca.class);
                final JSONObject corpo = new JSONObject();
                corpo.put("tituloBusca",tituloBusca);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, corpo,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject resposta) {

                                if(resposta.toString().equals("nao_tem_livro")){
                                    System.out.println("Não tem livro com esse titulo");
                                }
                                try {
                                    String idLivro = resposta.get("idLivro").toString().trim();
                                    String titulo = resposta.get("titulo").toString().trim();
                                    String autor = resposta.get("autor").toString().trim();
                                    String numPaginas = resposta.get("numPaginas").toString().trim();
                                    String genero = resposta.get("genero").toString().trim();
                                    String ano = resposta.get("ano").toString().trim();
                                    String classificacao = resposta.get("classificacao").toString().trim();
                                    String doador = resposta.get("doador").toString().trim();
                                    System.out.println(titulo);
                                    System.out.println(autor);
                                    System.out.println(idLivro);
                                    itResultado.putExtra("idLivro",idLivro);
                                    itResultado.putExtra("titulo",titulo);
                                    itResultado.putExtra("autor",autor);
                                    itResultado.putExtra("numPaginas",numPaginas);
                                    itResultado.putExtra("ano",ano);
                                    itResultado.putExtra("genero",genero);
                                    itResultado.putExtra("classificacao",classificacao);
                                    itResultado.putExtra("doador",doador);

                                    startActivity(itResultado);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                });
                fila.add(jsonObjectRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        }








}
