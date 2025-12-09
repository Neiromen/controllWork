package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @JsonProperty(required = false)
    private Integer id;
    private String title;
    @JsonProperty(required = false)
    private Integer price;
    private String author;
    private String message;
    @JsonProperty(required = false)
    private LocalDateTime createdAt;
    @JsonProperty(required = false)
    private LocalDateTime updatedAt;

}
