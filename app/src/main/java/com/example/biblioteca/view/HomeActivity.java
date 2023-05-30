package com.example.biblioteca.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.biblioteca.R;

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








}
