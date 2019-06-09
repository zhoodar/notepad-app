package com.demo.notepad.web.rest.dto;

import javax.validation.constraints.NotBlank;

public class NoteDto {
    @NotBlank
    private String name;
    @NotBlank(message = "Please, provide some content (")
    private String content;

    public NoteDto() {
    }

    public NoteDto(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
