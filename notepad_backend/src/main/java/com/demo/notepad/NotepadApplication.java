package com.demo.notepad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotepadApplication {

    public static void main(String[] args) {
        if (null != args && args.length == 1) {
            String prop = args[0];
            if (!prop.endsWith("/")) {
                prop = prop + "/";
            }
            System.setProperty("storage.path", prop);
        }
        SpringApplication.run(NotepadApplication.class, args);
    }

}
