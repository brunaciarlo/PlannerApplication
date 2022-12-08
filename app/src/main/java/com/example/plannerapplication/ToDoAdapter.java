package com.example.plannerapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModelo> toDoList;
    private ToDoListActivity activity;
    private BancoDadosToDo db;

    public ToDoAdapter(BancoDadosToDo db, ToDoListActivity activity){
        this.db = db;
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celula_tarefa, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        db.abreDataBase();
        ToDoModelo item = toDoList.get(position);
        holder.tarefa.setText(item.getTarefa());
        holder.tarefa.setChecked(toBoolean(item.getStatus()));
        holder.tarefa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    db.updateStatus(item.getId(), 1);
                } else {
                    db.updateStatus(item.getId(), 0);
                }

            }
        });
    }

    public int getItemCount(){
        return toDoList.size();
    }

    private boolean toBoolean(int n){
        return n!=0;
    }

    public void setTarefa(List<ToDoModelo> toDoList){
        this.toDoList = toDoList;
        notifyDataSetChanged();
    }
    public Context getContext(){
        return activity;
    }

    public void editItem(int position){
        ToDoModelo item = toDoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("tarefa", item.getTarefa());
        AddNovaTarefa fragmento = new AddNovaTarefa();
        fragmento.setArguments(bundle);
        fragmento.show(activity.getSupportFragmentManager(), AddNovaTarefa.TAG);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox tarefa;

        ViewHolder(View view){
            super(view);
            tarefa = view.findViewById(R.id.toDoCheckBox);
        }
    }
}
