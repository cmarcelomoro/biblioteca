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
        String titulo = editTextNomeLivro.getText().toString();
        String autor = editTextAutor.getText().toString();
        String ano = editTextAno.getText().toString();
        String genero = editTextGenero.getText().toString();
        String numPaginas = editTextNumPaginas.getText().toString();
        String classificacao = editTextClassificacao.getText().toString();


        if(titulo.isEmpty() || autor.isEmpty()  ||
        ano.isEmpty() || genero.isEmpty() ||
                numPaginas.isEmpty() || classificacao.isEmpty()){
            System.out.println("Está faltando algum dado.");
        }else{

            LivroController livro = new LivroController(titulo,autor,ano,genero,numPaginas,classificacao);

        }


        }

    }




