package com.example.plannerapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventoAdapter extends ArrayAdapter<Evento> {

    private List<Evento> listaEvento;

    public EventoAdapter(@NonNull Context context, List<Evento> lista) {
        super(context, 0,lista);
        listaEvento = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            Context ctx = getContext();
            LayoutInflater li = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.celula_evento,null);
        }
        Evento evento = listaEvento.get(position);
        TextView dataEvento = view.findViewById(R.id.diaEventoTextView);
        TextView celulaEvento = view.findViewById(R.id.eventoTextView);
        String diaEvento = (evento.getData());
        String tituloEvento = evento.getNome() + " - " + evento.getHora();
        celulaEvento.setText(tituloEvento);
        dataEvento.setText(diaEvento);
        return view;
    }
}
