package com.example.plannerapplication;

import static com.example.plannerapplication.CalendarioUtils.diasDoMesArray;
import static com.example.plannerapplication.CalendarioUtils.mesDoAnoFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class AgendaActivity extends AppCompatActivity implements CalendarioAdapter.OnItemListener{

    private TextView mesDoAno;
    private RecyclerView calendarioRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        initWidgets();
        CalendarioUtils.dataSelecionada = LocalDate.now();
        setMonthView();
    }

    private void setMonthView() {
        mesDoAno.setText(mesDoAnoFromDate(CalendarioUtils.dataSelecionada));
        ArrayList<LocalDate> diasDoMes = diasDoMesArray();

        CalendarioAdapter calendarioAdapter = new CalendarioAdapter(diasDoMes, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarioRecyclerView.setLayoutManager(layoutManager);
        calendarioRecyclerView.setAdapter(calendarioAdapter);
    }

    private void initWidgets() {
        calendarioRecyclerView = findViewById(R.id.calendarioRecyclerView);
        mesDoAno = findViewById(R.id.MesDoAnoTextView);
    }

    public void mostraMesAnterior(View view) {
        CalendarioUtils.dataSelecionada = CalendarioUtils.dataSelecionada.minusMonths(1);
        setMonthView();
    }

    public void mostraMesSeguinte(View view) {
        CalendarioUtils.dataSelecionada = CalendarioUtils.dataSelecionada.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        if(date != null){
            CalendarioUtils.dataSelecionada = date;
            setMonthView();
        }

    }

    public void AddEventos(View view) {
        Intent addEvento = new Intent(this, CadastroEventoActivity.class);
        startActivity(addEvento);
    }

    public void MostraEventos(View view) {
        Intent listaEventos = new Intent(this, ListagemEventosActivity.class);
        startActivity(listaEventos);
    }
}