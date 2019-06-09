package com.demo.notepad.web.rest.dto;

import com.demo.notepad.domain.Note;

public class PersistedNoteDto implements Comparable<PersistedNoteDto> {

    private String uuid;
    private String name;
    private String content;

    public PersistedNoteDto() {
    }

    public PersistedNoteDto(Note note) {
        this.uuid = note.getUuid();
        this.name = note.getName();
        this.content = note.getContent();
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int compareTo(PersistedNoteDto o) {
        return this.name.compareTo(o.name);
    }
}
