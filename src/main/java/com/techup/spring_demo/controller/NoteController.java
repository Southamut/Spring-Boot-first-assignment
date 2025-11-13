package com.techup.spring_demo.controller;

import com.techup.spring_demo.dto.NoteRequest;
import com.techup.spring_demo.dto.NoteResponse;
import com.techup.spring_demo.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    /**
     * POST /api/notes - Create a new note
     */
    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody NoteRequest request) {
        NoteResponse response = noteService.createNote(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET /api/notes - Get all notes
     */
    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllNotes() {
        List<NoteResponse> notes = noteService.getAllNotes();
        return ResponseEntity.ok(notes);
    }

    /**
     * GET /api/notes/{id} - Get note by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id) {
        NoteResponse response = noteService.getNoteById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * PUT /api/notes/{id} - Update note by id
     */
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest request) {
        NoteResponse response = noteService.updateNote(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE /api/notes/{id} - Delete note by id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}

