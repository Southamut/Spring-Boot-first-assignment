package com.techup.spring_demo.service;

import com.techup.spring_demo.dto.NoteRequest;
import com.techup.spring_demo.dto.NoteResponse;
import com.techup.spring_demo.entity.Note;
import com.techup.spring_demo.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    /**
     * Create a new note
     */
    @Transactional
    public NoteResponse createNote(NoteRequest request) {
        Note note = Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrl(request.getImageUrl())
                .build();

        Note savedNote = noteRepository.save(note);
        return NoteResponse.fromEntity(savedNote);
    }

    /**
     * Get all notes
     */
    @Transactional(readOnly = true)
    public List<NoteResponse> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(NoteResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Get note by id
     */
    @Transactional(readOnly = true)
    public NoteResponse getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
        return NoteResponse.fromEntity(note);
    }

    /**
     * Update note by id
     */
    @Transactional
    public NoteResponse updateNote(Long id, NoteRequest request) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id: " + id));

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setImageUrl(request.getImageUrl());

        Note updatedNote = noteRepository.save(note);
        return NoteResponse.fromEntity(updatedNote);
    }

    /**
     * Delete note by id
     */
    @Transactional
    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note not found with id: " + id);
        }
        noteRepository.deleteById(id);
    }
}

