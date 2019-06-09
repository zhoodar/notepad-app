package com.demo.notepad.common;

public class FileDidNotLoadException extends RuntimeException {

    public FileDidNotLoadException(Exception ex) {
        super("Your file could not be loaded ((" + ex.getLocalizedMessage(), ex);
    }
}
