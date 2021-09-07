package com.techienotes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieRequest {
    private String name;
    private String releaseYear;
    private int starCount;
}
