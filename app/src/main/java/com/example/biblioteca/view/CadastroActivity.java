package com.example.biblioteca.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

public class CadastroActivity extends AppCompatActivity {

    EditText editTextNomeCadastro;
    EditText editTextSenhaCadastro;
    EditText editTextSenhaRep;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        editTextNomeCadastro = findViewById(R.id.editTextNomeCadastro);
        editTextSenhaCadastro = findViewById(R.id.editTextSenhaCadastro);
        editTextSenhaRep = findViewById(R.id.editTextSenhaRep);
    }

    public void botaoConfirmar(View v) {
        String nome = editTextNomeCadastro.getText().toString();
        String senha = editTextSenhaCadastro.getText().toString();
        String senharep = editTextSenhaRep.getText().toString();

        if (nome.isEmpty() || senha.isEmpty()) {

            System.out.println("Algum dos campos está vazio");

        } else if (senha.length() < 8) {
            System.out.println("A senha deve ter 8 caracteres ou mais!");
            return;
        } else if (!senha.equals(senharep)) {
            System.out.println("As senhas inseridas devem ser iguais");
            return;

        } else {
            System.out.println("Dados Ok");
            RequestQueue fila = Volley.newRequestQueue(this);
            String url = "http://"+Constantes.ip+"/bibliotecascripts/cadastro.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            String resposta = response.substring(0);
                            //testa o valor da resposta obtida do servidor
                            if (resposta.equals("dados_inseridos")) {
                                System.out.println("Inserido no banco.");
                                finish();
                            } else if (resposta.equals("erro_ao_inserir")) {
                                System.out.println("Houve um erro de inserção.");
                            } else if (resposta.equals("erro_post")){
                                System.out.println("Houve um erro no POST.");
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


        //

    }
}