package com.techienotes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Movie {
    private String name;
    private String releaseYear;
    private int starCount;
}
