package com.demo.notepad.web.rest.controller;

import com.demo.notepad.bl.asset.NoteStorageService;
import com.demo.notepad.domain.Note;
import com.demo.notepad.web.rest.dto.ContentDto;
import com.demo.notepad.web.rest.dto.NoteDto;
import com.demo.notepad.web.rest.dto.PersistedNoteDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/notes")
public class NoteController {

    private final NoteStorageService noteStorageService;

    public NoteController(NoteStorageService noteStorageService) {
        this.noteStorageService = noteStorageService;
    }

    @GetMapping
    public List<PersistedNoteDto> fetchAllNotes() {
        List<Note> notes = noteStorageService.fetchAllNotes();
        return notes.stream()
                .map(PersistedNoteDto::new)
                .sorted()
                .collect(Collectors.toList());
    }

    @PostMapping
    public PersistedNoteDto saveNote(@Valid @RequestBody NoteDto dto) {
        Note note = noteStorageService.saveNote(dto);
        return new PersistedNoteDto(note);
    }

    @PutMapping("/{name}")
    public PersistedNoteDto updateNote(@PathVariable @NotBlank String name, @RequestBody ContentDto dto) {
        Note note = noteStorageService.updateNote(name, dto.getContent());
        return new PersistedNoteDto(note);
    }

    @DeleteMapping("/{name}")
    public void deleteNote(@PathVariable @NotBlank String name) {
        noteStorageService.deleteByName(name);
    }

}
