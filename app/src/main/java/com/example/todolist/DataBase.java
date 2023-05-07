package com.example.todolist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//imitation behavior of real DB
public class DataBase {
    private ArrayList<Note> storage = new ArrayList<>();
    private static DataBase instance = null;

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    private DataBase() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Note note = new Note(i, "Note" + i, random.nextInt(3));
            storage.add(note);
        }
    }

    public void add(Note note) {
        storage.add(note);
    }

    public void remove(int id) {
        for (int i = 0; i < storage.size(); i++) {
            Note note = storage.get(i);
            if (note.getId() == id) {
                storage.remove(note);
            }
        }
    }

    public ArrayList<Note> getStorage() {
        return new ArrayList<Note>(storage);
    }
}