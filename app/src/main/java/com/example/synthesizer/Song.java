package com.example.synthesizer;

import java.util.ArrayList;
import java.util.List;

public class Song {
    private List<Note> notes;

    public Song(List<Note> notes) {
        this.notes = notes;
    }

    public Song() {
        notes = new ArrayList<>();

    }

    public List<Note> getNotes() {
        return notes;
    }

    public void add(Note n){
        notes.add(n);
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Song{" +
                "notes=" + notes +
                '}';
    }
}
