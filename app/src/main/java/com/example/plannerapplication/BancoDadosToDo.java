package com.example.plannerapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDadosToDo extends SQLiteOpenHelper {

    private static final String NOME_DB = "ToDoDB";
    private static final int VERSAO_DB = 1;
    private static final String NOME_TABELA = "todo";
    private static final String ID = "id";
    private static final String TAREFA = "tarefa";
    private static final String STATUS = "status";

    private SQLiteDatabase db;

    BancoDadosToDo(Context context){
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE todo (" +
                "id INTEGER PRIMARY KEY," +
                "tarefa TEXT NOT NULL," +
                "status INTEGER NOT NULL)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void abreDataBase(){
        db = this.getWritableDatabase();
    }

    public void inserirTarefa(ToDoModelo tarefa){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TAREFA, tarefa.getTarefa());
        contentValues.put(STATUS, 0);
        db.insert(NOME_TABELA, null, contentValues);
    }

    @SuppressLint("Range")
    public List<ToDoModelo> todasTarefas(){
        List<ToDoModelo> listaTarefas = new ArrayList<>();
        Cursor cursor = null;
        db.beginTransaction();
        try{
            cursor = db.query(NOME_TABELA, null, null,null, null, null, null, null);
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        ToDoModelo tarefa = new ToDoModelo();
                        tarefa.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                        tarefa.setTarefa(cursor.getString(cursor.getColumnIndex(TAREFA)));
                        tarefa.setStatus(cursor.getColumnIndex(STATUS));
                        listaTarefas.add(tarefa);
                    } while (cursor.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            cursor.close();
        }
        return listaTarefas;
    }

    public void updateStatus(int id, int status){
        ContentValues contentValues = new ContentValues();
        contentValues.put(STATUS, status);
        db.update(NOME_TABELA, contentValues, ID + "=?", new String[] {String.valueOf(id)});
    }

    public void updateTarefa(int id, String tarefa){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TAREFA, tarefa);
        db.update(NOME_TABELA, contentValues, ID + "=?", new String[] {String.valueOf(id)});
    }

    public void excluirTarefa(int id){
        db.delete(NOME_TABELA, ID + "=?", new String[] {String.valueOf(id)});
    }
}
