package com.example.plannerapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ArrayList<LocalDate> dias;
    public final View parentView;
    public final TextView diaDoMes;
    private final com.example.plannerapplication.CalendarioAdapter.OnItemListener onItemListener;

    public CalendarioViewHolder(@NonNull View itemView, com.example.plannerapplication.CalendarioAdapter.OnItemListener onItemListener, ArrayList<LocalDate> dias) {
        super(itemView);
        parentView = itemView.findViewById(R.id.parentView);
        diaDoMes = itemView.findViewById(R.id.DiaTextView);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.dias = dias;
    }

    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(),dias.get(getAdapterPosition()));
    }
}
