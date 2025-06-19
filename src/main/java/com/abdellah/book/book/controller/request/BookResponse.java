package com.abdellah.book.book.controller.request;

import lombok.*;

@Data
@AllArgsConstructor
public class BookResponse {

    private Integer id;
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String owner;
    private byte[] cover;
    private double rate;
    private boolean archived;
    private boolean shareable;

    public BookResponse() {

    }
}