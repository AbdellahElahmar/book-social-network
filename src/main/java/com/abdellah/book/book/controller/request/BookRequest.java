package com.abdellah.book.book.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class BookRequest {

        private Integer id;

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        private String title;

        @NotNull(message = "101")
        @NotEmpty(message = "101")
        private String authorName;

        @NotNull(message = "102")
        @NotEmpty(message = "102")
        private String isbn;

        @NotNull(message = "103")
        @NotEmpty(message = "103")
        private String synopsis;

        private Boolean shareable;

}