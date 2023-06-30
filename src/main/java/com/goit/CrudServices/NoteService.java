package com.goit.CrudServices;

import com.goit.Repository.NoteRepository;
import com.goit.Entities.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    public final NoteRepository repository;

    public Map<Long, Note> listAll() {
        return repository.findAll().stream().collect(Collectors.toMap(Note::getId, note -> note));
    }

    public Note add(Note note) {
        return repository.saveAndFlush(note);
    }

    public void deleteById(long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("There is not any note to DELETE with such ID");
        }
        repository.deleteById(id);
    }

    public void update(Note note) {
        if (!repository.existsById(note.getId())) {
            throw new IllegalArgumentException("There is not any note to UPDATE with such ID");
        }
        Long id = note.getId();
        repository.getReferenceById(id).setTitle(note.getTitle());
        repository.getReferenceById(id).setContent(note.getContent());
        repository.saveAndFlush(note);
    }

    public Note getById(long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("There is not any note to GET with such ID");
        }
        return repository.getReferenceById(id);
    }
}
