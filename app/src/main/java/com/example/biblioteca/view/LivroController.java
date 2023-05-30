package com.example.biblioteca.view;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LivroController extends AppCompatActivity {

    public LivroController(String titulo, String autor, String genero, String classificacao, String ano, String numPaginas ){

        RequestQueue fila = Volley.newRequestQueue(LivroController.this);
        String url = "http://"+Constantes.ip+"/bibliotecascripts/inserirlivro.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String resposta = response.substring(0).trim();

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
                params.put("titulo", titulo);
                params.put("autor", autor);
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
