package com.techup.spring_demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String content;

    private String imageUrl;
}

