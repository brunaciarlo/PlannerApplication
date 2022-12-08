package com.example.plannerapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Evento implements Serializable {

    public static ArrayList<Evento> eventoArrayList = new ArrayList<>();

    private String nome, data, hora;

    public Evento(String nome, String data, String hora) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


}