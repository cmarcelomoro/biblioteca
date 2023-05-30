package com.example.biblioteca.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biblioteca.R;

import java.util.HashMap;
import java.util.Map;

public class AdicionarActivity extends AppCompatActivity {

    EditText editTextNomeLivro;
    EditText editTextAutor;
    EditText editTextAno;
    EditText editTextNumPaginas;
    EditText editTextGenero;
    EditText editTextClassificacao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);
        getSupportActionBar().hide();

        editTextNomeLivro = findViewById(R.id.editTextNomeLivro);
        editTextAutor = findViewById(R.id.editTextAutor);
        editTextAno = findViewById(R.id.editTextAno);
        editTextGenero = findViewById(R.id.editTextGenero);
        editTextNumPaginas = findViewById(R.id.editTextNumPaginas);
        editTextClassificacao = findViewById(R.id.editClassificacao);
    }

    public void botaoVoltar(View v){
        Intent itHome = new Intent(this,HomeActivity.class);
        startActivity(itHome);

    }

    public void botaoConfirmar(View v){
        String nomeLivro = editTextNomeLivro.getText().toString();
        String nomeAutor = editTextAutor.getText().toString();
        String ano = editTextAno.getText().toString();
        String genero = editTextGenero.getText().toString();
        String numPaginas = editTextNumPaginas.getText().toString();
        String classificacao = editTextClassificacao.getText().toString();


        if(nomeLivro.isEmpty() || nomeAutor.isEmpty()  ||
        ano.isEmpty() || genero.isEmpty() ||
                numPaginas.isEmpty() || classificacao.isEmpty()){
            System.out.println("Está faltando algum dado.");
        }else{
            System.out.println("Dados Ok");
            RequestQueue fila = Volley.newRequestQueue(this);
            String url = "http://"+Constantes.ip+"/bibliotecascripts/inserirlivro.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            String resposta = response.substring(0).trim();
                            Toast.makeText(AdicionarActivity.this, resposta, Toast.LENGTH_SHORT).show();
                            System.out.println(resposta);
                            //testa o valor da resposta obtida do servidor
                            if (resposta.equals("pesquisa_foi")) {

                                System.out.println("pesquisa foi");
                                //finish();
                            } else if (resposta.equals("erro_ao_inserir")) {
                                System.out.println("Houve um erro de inserção.");
                            } else if (resposta.equals("erro_post")){
                                System.out.println("Houve um erro no POST.");
                            } else if (resposta.equals("pesquisa_n_foi")){
                                System.out.println("pesquisa nao foi");
                            }else if(resposta.equals("dados_recebidos_post")){
                                System.out.println("Dados recebidos");
                            }else if(resposta.equals("resposta")){
                                System.out.println("Resposta");
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
                    params.put("titulo", nomeLivro);
                    params.put("autor", nomeAutor);
                    params.put("ano",ano);
                    params.put("numPaginas",numPaginas);
                    params.put("genero",genero);
                    params.put("classificacao",classificacao);
                    params.put("meuId",Constantes.id);
                    return params;
                }

            };

            fila.add(stringRequest);

        }


        }

    }




