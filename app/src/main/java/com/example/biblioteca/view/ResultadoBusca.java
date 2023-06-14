package com.example.biblioteca.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biblioteca.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResultadoBusca extends AppCompatActivity {

    TextView textViewTitulo;
    TextView textViewAutor;

    TextView textViewAno;
    TextView textViewClassificacao;
    TextView textViewGenero;
    TextView textViewNumPag;

    TextView textViewDoador;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
        getSupportActionBar().hide();

        textViewTitulo = findViewById(R.id.TextViewTitulo);
        textViewAutor = findViewById(R.id.TextViewAutor);
        textViewAno = findViewById(R.id.TextViewAno);
        textViewGenero = findViewById(R.id.TextViewGenero);
        textViewNumPag = findViewById(R.id.TextViewNumPag);
        textViewClassificacao = findViewById(R.id.TextViewClassificacao);
        textViewDoador = findViewById(R.id.TextViewDoador);


         String titulo = getIntent().getStringExtra("titulo");
         String autor = getIntent().getStringExtra("autor");
         String genero = getIntent().getStringExtra("genero");
         String ano = getIntent().getStringExtra("ano");
         String classificacao = getIntent().getStringExtra("classificacao");
         String numPaginas = getIntent().getStringExtra("numPaginas");
         String doador = getIntent().getStringExtra("doador");


        textViewTitulo.setText(titulo);
        textViewAutor.setText(autor);
        textViewNumPag.setText(numPaginas);
        textViewGenero.setText(genero);
        textViewAno.setText(ano);
        textViewClassificacao.setText(classificacao);
        textViewDoador.setText(doador);

    }
    public ResultadoBusca(){

    }

    public void botaoVoltarHome(View v){

        Intent itHome = new Intent(this,HomeActivity.class);
        startActivity(itHome);

    }

    public void escolherLivro(View v){
        String idLivro = getIntent().getStringExtra("idLivro");
        RequestQueue fila = Volley.newRequestQueue(this);
        String url = "http://"+Constantes.ip+"/bibliotecascripts/pegarlivro.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String resposta = response.substring(0).trim();

                        System.out.println(resposta);
                        //testa o valor da resposta obtida do servidor
                        if (resposta.equals("livro_adquirido")) {
                            System.out.println("O livro foi adquirido!");
                            Toast.makeText(ResultadoBusca.this, "Livro adquirido", Toast.LENGTH_SHORT).show();
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
                params.put("idLivro", idLivro);
                params.put("meuId",Constantes.id);
                return params;
            }

        };

        fila.add(stringRequest);

    }


}
