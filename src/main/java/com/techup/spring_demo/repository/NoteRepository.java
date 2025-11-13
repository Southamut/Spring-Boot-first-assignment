package com.techup.spring_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techup.spring_demo.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
}