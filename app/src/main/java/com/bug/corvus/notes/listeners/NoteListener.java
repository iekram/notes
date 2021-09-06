package com.bug.corvus.notes.listeners;

import com.bug.corvus.notes.entities.Note;

public interface NoteListener {
    void onNoteClicked(Note note, int position);
}
