package com.example.plannerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListagemEventosActivity extends AppCompatActivity {

    private ListView eventosListView;
    private static boolean firstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_eventos);
        initWidgets();
        salvarNoBD();
        setEventoAdapter();

//        eventosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Intent intent = new Intent(ListagemEventosActivity.this, EdicaoRemocaoDados.class);
//
//                startActivity(intent);
//            }
//        });
    }


    private void initWidgets() {
        eventosListView = findViewById(R.id.eventosListView);
    }

    private void salvarNoBD() {
        if (firstRun) {
            BancoDadosEventos bancoDadosEventos = BancoDadosEventos.instanceOfDataBase(this);
            bancoDadosEventos.todosEventos();
            bancoDadosEventos.close();
            finish();
        }
        firstRun = false;
    }

    private void setEventoAdapter() {
        EventoAdapter eventoAdapter = new EventoAdapter(getApplicationContext(), Evento.eventoArrayList);
        eventosListView.setAdapter(eventoAdapter);
    }
}