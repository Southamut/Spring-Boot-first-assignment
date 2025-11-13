package com.techup.spring_demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notes")
@Data // รวม getter/setter/toString/equals/hashCode
@NoArgsConstructor // Constructor ว่าง
@AllArgsConstructor // Constructor ทุก field
@Builder
public class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false)
  private String title;
  
  @Column(columnDefinition = "TEXT")
  private String content;

  @Column(name = "image_url")
  private String imageUrl;
}