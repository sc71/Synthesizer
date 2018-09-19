package com.example.synthesizer;

public class Note {
    private int delay, noteId;
    private static final int WHOLE_NOTE = 500;

    public Note(int delay, int noteId) {
        this.delay = delay;
        this.noteId = noteId;
    }

    public Note(int noteId) {
        this.noteId = noteId;
        delay = WHOLE_NOTE;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    @Override
    public String toString() {
        return "Note{" +
                "delay=" + delay +
                ", noteId=" + noteId +
                '}';
    }
}
