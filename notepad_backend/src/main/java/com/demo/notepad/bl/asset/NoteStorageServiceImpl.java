package com.demo.notepad.bl.asset;

import com.demo.notepad.common.FileDidNotLoadException;
import com.demo.notepad.config.StorageProperty;
import com.demo.notepad.domain.Note;
import com.demo.notepad.web.rest.dto.NoteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NoteStorageServiceImpl implements NoteStorageService {

    private final static Logger logger = LoggerFactory.getLogger(NoteStorageServiceImpl.class);
    private final StorageProperty storageProperty;

    public NoteStorageServiceImpl(StorageProperty storageProperty) {
        this.storageProperty = storageProperty;
    }

    @Override
    public List<Note> fetchAllNotes() {
        List<Note> result = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(storageProperty.getPath()))) {

            paths.filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            String content = Files.lines(path, StandardCharsets.UTF_8).collect(Collectors.joining("\n"));
                            result.add(new Note(path.toFile().getName(), content));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        return result;
    }

    @Override
    public Note saveNote(NoteDto dto) {
        String filePath = storageProperty.getPath() + dto.getName();
        try {
            Files.write(Paths.get(filePath), dto.getContent().getBytes());
            return new Note(dto.getName(), dto.getContent());
        } catch (IOException ex) {
            throw new FileDidNotLoadException(ex);
        }
    }

    @Override
    public Note updateNote(String name, String content) {
        return saveNote(new NoteDto(name, content));
    }

    @Override
    public void deleteByName(String name) {
        String filePath = storageProperty.getPath() + name;
        Path fileToDeletePath = Paths.get(filePath);
        try {
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            throw new FileSystemNotFoundException("Your file could not be found," + e.getLocalizedMessage());
        }
    }

    @PostConstruct
    public void init() {
        final Path path = Paths.get(storageProperty.getPath());
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
