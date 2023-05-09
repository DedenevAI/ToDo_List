package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MakeNoteActivity extends AppCompatActivity {
    private EditText editTextEnterNote;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private Button buttonSaveNote;
    private NoteDataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_note);

        database = NoteDataBase.getInstance(getApplication());
        initViews();


        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void initViews() {
        editTextEnterNote = findViewById(R.id.editTextEnterNote);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
        buttonSaveNote = findViewById(R.id.buttonSaveNote);
    }

    private void saveNote() {
        if (editTextEnterNote.getText().toString().isEmpty()) {
            Toast.makeText(this,
                    getString(R.string.empty_note_warning),
                    Toast.LENGTH_SHORT).show();
        } else {
            String text = editTextEnterNote.getText().toString().trim();
            int priority = getPriority();
            Note note = new Note(text,priority);
            database.notesDao().add(note);

            finish();
        }
    }
    private int getPriority(){
        int priority;
        if(radioButtonLow.isChecked()){
            priority = 0;
        } else if (radioButtonMedium.isChecked()) {
            priority = 1;
        }else {
            priority = 2;
        }
        return priority;
    }
    public static Intent newIntent(Context context){
        return new Intent(context, MakeNoteActivity.class);
    }
}