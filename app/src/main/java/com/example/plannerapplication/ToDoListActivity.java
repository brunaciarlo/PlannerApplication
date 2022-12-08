package com.example.plannerapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity implements DialogCloseListener{

    private RecyclerView tarefasRecyclerView;
    private ToDoAdapter tarefasAdapter;
    private FloatingActionButton fab;

    private List<ToDoModelo> listaTarefas;
    private BancoDadosToDo db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        db = new BancoDadosToDo(this);
        db.abreDataBase();

        listaTarefas = new ArrayList<>();

        tarefasRecyclerView = findViewById(R.id.tarefasRecyclerView);
        tarefasRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tarefasAdapter = new ToDoAdapter(db,this);
        tarefasRecyclerView.setAdapter(tarefasAdapter);

        fab = findViewById(R.id.botaoFlutuante);

        listaTarefas = db.todasTarefas();
        Collections.reverse(listaTarefas);
        tarefasAdapter.setTarefa(listaTarefas);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNovaTarefa.novaInstancia().show(getSupportFragmentManager(), AddNovaTarefa.TAG);
            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        listaTarefas = db.todasTarefas();
        Collections.reverse(listaTarefas);
        tarefasAdapter.setTarefa(listaTarefas);
        tarefasAdapter.notifyDataSetChanged();
    }
}