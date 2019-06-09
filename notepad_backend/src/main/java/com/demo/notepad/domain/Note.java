package com.demo.notepad.domain;


import java.util.UUID;

public class Note {

    private String uuid;
    private String name;
    private String content;

    public Note() {
    }

    public Note(String name, String content) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.content = content;
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
}
