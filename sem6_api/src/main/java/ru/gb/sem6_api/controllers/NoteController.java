package ru.gb.sem6_api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.sem6_api.model.Note;
import ru.gb.sem6_api.services.NoteService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private NoteService noteSrvc;

    @GetMapping("/")
    public ResponseEntity<List<Note>> findAllNotes() {
        return ResponseEntity.ok(noteSrvc.findAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Note> currNote = noteSrvc.findById(id);
        if (currNote.isPresent()) {
            noteSrvc.findById(id);
            return ResponseEntity.ok(currNote);
        } else
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("error : Such id (" + id + ") is not found");
    }

    @PostMapping("/")
    public ResponseEntity<Note> addNewNote(@RequestBody Note newNote) {
        return ResponseEntity.ok(noteSrvc.addNewNote(newNote));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id, @RequestBody Note newNote) {
        if (noteSrvc.findById(id).isPresent())
            return ResponseEntity.ok(noteSrvc.updateNoteByid(id, newNote));
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("error : Such id (" + id + ") is not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (noteSrvc.findById(id).isPresent()) {
            noteSrvc.deleteById(id);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("error : Such id (" + id + ") is not found");
    }
}

/*

{
    "title":"title1",
    "text":"text1",
    "creationTime":"2024-01-20T15:30:45"
}

 */
