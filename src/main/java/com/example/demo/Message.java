package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @JsonIgnore
    private int id;
    private String title;
    private int price;
    private String author;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
