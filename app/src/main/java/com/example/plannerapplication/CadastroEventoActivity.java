package com.example.plannerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroEventoActivity extends AppCompatActivity {

    private EditText nomeEventoEditText,dataEventoEditText, horaEventoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        initWidgets();
    }

    private void initWidgets() {
        nomeEventoEditText = findViewById(R.id.nomeEventoEditText);
        dataEventoEditText = findViewById(R.id.dataEventoEditText);
        horaEventoEditText = findViewById(R.id.horaEventoEditText);
    }

    public void salvarEvento(View view) {

        BancoDadosEventos bancoDadosEventos = BancoDadosEventos.instanceOfDataBase(this);
        String nomeEvento = String.valueOf(nomeEventoEditText.getText());
        String dataEvento = String.valueOf(dataEventoEditText.getText());
        String horaEvento = String.valueOf(horaEventoEditText.getText());
        Evento novoEvento = new Evento(nomeEvento, dataEvento, horaEvento);
        Evento.eventoArrayList.add(novoEvento);
        bancoDadosEventos.addEventoDB(novoEvento);
        finish();
    }

    public void excluirEvento(View view) {

    }
}