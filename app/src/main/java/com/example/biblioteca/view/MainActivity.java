package com.example.biblioteca.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biblioteca.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editTextNome;
    EditText editTextSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        editTextNome = findViewById(R.id.editTextNome);
        editTextSenha = findViewById(R.id.editTextSenha);
    }


    public void botaoEntrar(View v){

        String nome;
        String senha;
        nome = editTextNome.getText().toString();
        senha = editTextSenha.getText().toString();

        if(nome.isEmpty() || senha.isEmpty()){

            System.out.println("Algum dos campos está vazio");


        }else {

            RequestQueue fila = Volley.newRequestQueue(this);
            String url = "http://"+Constantes.ip+"/bibliotecascripts/login.php";
            Intent itHome = new Intent(this,HomeActivity.class);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String respostaCortada = response.substring(0);

                            String [] respostaSplitada = response.split("_");


                            System.out.println(respostaSplitada[1]);
                            Constantes.id = respostaSplitada[1];


                            String resposta = response.substring(0).trim();
                            //testa o valor da resposta obtida do servidor
                            if (resposta.contains("logado")) {
                                System.out.println("Você logou");
                                startActivity(itHome);

                            } else if (resposta.equals("senha_incorreta")) {
                                System.out.println("A senha está incorreta.");
                            } else if (resposta.equals("conta_n_encontrada")){
                                System.out.println("Conta não foi encontrada");
                            }else if (resposta.equals("erro_post")){
                                System.out.println("Dados não chegaram");
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
                    params.put("nome", nome);
                    params.put("senha", senha);
                    return params;
                }

            };
            fila.add(stringRequest);
        }
    }
    public void botaoCadastrar(View v){
        Intent itCadastro = new Intent(this, CadastroActivity.class);
        startActivity(itCadastro);
    }
}