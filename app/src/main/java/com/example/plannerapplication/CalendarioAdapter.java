package com.example.plannerapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarioAdapter extends RecyclerView.Adapter<CalendarioViewHolder> {

    private final ArrayList<LocalDate> dias;
    private final OnItemListener onItemListener;

    public CalendarioAdapter(ArrayList<LocalDate> dias, OnItemListener onItemListener) {
        this.dias = dias;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celula_calendario, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (dias.size() > 15)
            layoutParams.height = (int) (parent.getHeight() *0.166666666);
        else
            layoutParams.height = (int) parent.getHeight();

        return new CalendarioViewHolder(view, onItemListener, dias);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarioViewHolder holder, int position) {
        final LocalDate date = dias.get(position);

        holder.diaDoMes.setText(String.valueOf(date.getDayOfMonth()));

        if(date.equals(CalendarioUtils.dataSelecionada))
            holder.parentView.setBackgroundColor(Color.LTGRAY);

        if (date.getMonth().equals(CalendarioUtils.dataSelecionada.getMonth()))
            holder.diaDoMes.setTextColor(Color.BLACK);
        else
            holder.diaDoMes.setTextColor(Color.LTGRAY);
    }

    @Override
    public int getItemCount() {
        return dias.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, LocalDate date);
    }
}
