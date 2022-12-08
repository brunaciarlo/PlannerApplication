package com.example.plannerapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNovaTarefa extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";

    private EditText novaTarefaEditText;
    private Button novaTarefaButton;
    private BancoDadosToDo db;

    public static AddNovaTarefa novaInstancia(){
        return new AddNovaTarefa();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.nova_tarefa, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        novaTarefaEditText = getView().findViewById(R.id.novaTarefaEditText);
        novaTarefaButton = getView().findViewById(R.id.novaTarefaButton);

        db = new BancoDadosToDo(getActivity());
        db.abreDataBase();

        boolean isUpdate = false;
        final Bundle bundle = getArguments();
        if (bundle != null){
            isUpdate = true;
            String tarefa = bundle.getString("tarefa");
            novaTarefaEditText.setText(tarefa);
            if (tarefa.length() > 0)
                novaTarefaButton.setTextColor(ContextCompat.getColor(getContext(),R.color.lightGreen));
        }
        novaTarefaEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")){
                    novaTarefaButton.setEnabled(false);
                    novaTarefaButton.setTextColor(Color.LTGRAY);
                } else {
                    novaTarefaButton.setEnabled(true);
                    novaTarefaButton.setTextColor(ContextCompat.getColor(getContext(),R.color.lightGreen));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        boolean finalIsUpdate = isUpdate;
        novaTarefaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = novaTarefaEditText.getText().toString();
                if (finalIsUpdate){
                    db.updateTarefa(bundle.getInt("id"), texto);
                } else {
                    ToDoModelo tarefa = new ToDoModelo();
                    tarefa.setTarefa(texto);
                    tarefa.setStatus(0);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener){
            ((DialogCloseListener)activity).handleDialogClose(dialog);
        }
    }

}
