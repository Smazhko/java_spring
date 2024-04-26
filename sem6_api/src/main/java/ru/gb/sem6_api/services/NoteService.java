package ru.gb.sem6_api.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.sem6_api.model.Note;
import ru.gb.sem6_api.repository.NoteRepository;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class NoteService {
    private NoteRepository noteRep;

    public List<Note> findAllNotes (){
        return noteRep.findAll();
    }

    public Optional<Note> findById(Long id){
        return noteRep.findById(id);
    }

    public Note addNewNote(Note newNote){
        return noteRep.save(newNote);
    }

    public Note updateNoteByid(Long id, Note note){
        note.setId(id);
        noteRep.save(note);
        return note;
    }

    public void deleteById(Long id){
        noteRep.deleteById(id);
    }
}
