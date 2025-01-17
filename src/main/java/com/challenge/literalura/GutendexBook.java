package com.challenge.literalura;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexBook {
    private String title;
    private String[] authors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getAuthorsString() {
        if (authors == null || authors.length == 0) {
            return "Desconocido";
        }
        return String.join(", ", authors);
    }
}
