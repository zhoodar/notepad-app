package com.demo.notepad.bl.asset;

import com.demo.notepad.domain.Note;
import com.demo.notepad.web.rest.dto.NoteDto;

import java.util.List;

public interface NoteStorageService {

    List<Note> fetchAllNotes();
    Note saveNote(NoteDto dto);
    Note updateNote(String name, String content);
    void deleteByName(String name);
}
