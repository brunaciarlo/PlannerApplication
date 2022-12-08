package com.example.plannerapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity {

    private RecyclerView tarefasRecyclerView;
    private ToDoAdapter tarefasAdapter;

    private List<ToDoModelo> listaTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        listaTarefas = new ArrayList<>();

        tarefasRecyclerView = findViewById(R.id.tarefasRecyclerView);
        tarefasRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tarefasAdapter = new ToDoAdapter(this);
        tarefasRecyclerView.setAdapter(tarefasAdapter);

        ToDoModelo tarefa = new ToDoModelo();
        tarefa.setTarefa("Essa é uma tarefa teste");
        tarefa.setStatus(0);
        tarefa.setId(1);

        listaTarefas.add(tarefa);
        listaTarefas.add(tarefa);
        listaTarefas.add(tarefa);
        listaTarefas.add(tarefa);
        listaTarefas.add(tarefa);

        tarefasAdapter.setTarefa(listaTarefas);
    }
}