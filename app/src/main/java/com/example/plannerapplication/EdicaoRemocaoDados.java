//package com.example.plannerapplication;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//public class EdicaoRemocaoDados extends AppCompatActivity {
//
//    EditText nomeEventoEditText, dataEventoEditText, horaEventoEditText;
//    Button salvarAltBt, excluirEveBt;
//    Intent i;
//    BancoDadosEventos bd;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edicao_remocao_dados);
//
//        nomeEventoEditText = findViewById(R.id.novoNomeEventoEditText);
//        dataEventoEditText = findViewById(R.id.novaDataEventoEditText);
//        horaEventoEditText = findViewById(R.id.novoHorarioEventoEditText);
//        salvarAltBt = findViewById(R.id.salvarAlteracoesButton);
//        excluirEveBt = findViewById(R.id.excluirEventoButton);
//
//        i = getIntent();
//        String id = i.getExtras().getString("id");
//        bd = new BancoDadosEventos(this);
//
//        carregarDadosEventos(id);
//
//        salvarAltBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setResult(0,i);
//                finish();
//            }
//        });
//
//        excluirEveBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setResult(1,i);
//                finish();
//            }
//        });
//    }
//
//    private void carregarDadosEventos(String id) {
//        bd.EventoSelecionadoPorId(Integer.parseInt(id));
//    }
//}