package com.example.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MakeNoteViewModel extends AndroidViewModel {
    private NotesDao notesDao;
    private MutableLiveData<Boolean> shouldCloseScreen = new MutableLiveData<>();

    public MakeNoteViewModel(@NonNull Application application) {
        super(application);
        notesDao = NoteDataBase.getInstance(application).notesDao();
    }
    public void saveNote(Note note){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                notesDao.add(note);
                shouldCloseScreen.postValue(true);
            }
        });
        thread.start();
    }

    public LiveData<Boolean> getShouldCloseScreen() {
        return shouldCloseScreen;
    }
}
