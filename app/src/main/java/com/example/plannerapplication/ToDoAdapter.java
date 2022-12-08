package com.example.plannerapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModelo> toDoList;
    private ToDoListActivity activity;

    public ToDoAdapter(ToDoListActivity activity){
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celula_tarefa, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        ToDoModelo item = toDoList.get(position);
        holder.tarefa.setText(item.getTarefa());
        holder.tarefa.setChecked(toBoolean(item.getStatus()));
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

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox tarefa;

        ViewHolder(View view){
            super(view);
            tarefa = view.findViewById(R.id.toDoCheckBox);
        }
    }
}
