package com.example.plannerapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancoDadosEventos extends SQLiteOpenHelper {

    private static BancoDadosEventos bancoDadosEventos;

    private static final String NOME_DB = "EventosDB";
    private static final int VERSAO_DB = 1;
    private static final String NOME_TABELA = "Eventos";
    private static final String COUNTER = "Counter";

    private static final String NOME_EV = "nome";
    private static final String DATA_EV = "data";
    private static final String HORA_EV = "hora";

    public BancoDadosEventos(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    public static BancoDadosEventos instanceOfDataBase(Context context){
        if(bancoDadosEventos == null)
            bancoDadosEventos = new BancoDadosEventos(context);

        return bancoDadosEventos;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Eventos (" +
                "id INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "data TEXT NOT NULL," +
                "hora TEXT NOT NULL)";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addEventoDB(Evento evento){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOME_EV, evento.getNome());
        contentValues.put(DATA_EV, evento.getData());
        contentValues.put(HORA_EV, evento.getHora());

        sqLiteDatabase.insert(NOME_TABELA, null, contentValues);
    }

    public void todosEventos(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT nome, data, hora FROM " + NOME_TABELA, null)){
            if (result.getCount() != 0){
                while (result.moveToNext()){
                    String nome = result.getString(0);
                    String data = result.getString(1);
                    String hora = result.getString(2);
                    Evento evento = new Evento(nome,data,hora);
                    Evento.eventoArrayList.add(evento);
                }
            }
        }
    }

    public Evento EventoSelecionadoPorId (int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM eventos WHERE id=?", new String[]{String.valueOf(id)});
        c.moveToFirst();
        if(c.getCount() == 1) {
            String nome = c.getString(c.getColumnIndex("nome"));
            String data = c.getString(c.getColumnIndex("data"));
            String hora = c.getString(c.getColumnIndex("hora"));
            return new Evento(id, nome, data, hora);
        }return null;
    }

    public void editarEvento(Evento evento){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOME_EV, evento.getNome());
        contentValues.put(DATA_EV, evento.getData());
        contentValues.put(HORA_EV, evento.getHora());

        sqLiteDatabase.update(NOME_TABELA, contentValues, NOME_EV + " =? ", new String[]{String.valueOf(evento.getNome())});
    }
}