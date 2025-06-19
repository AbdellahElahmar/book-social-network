package com.abdellah.book.book.service;

import com.abdellah.book.book.controller.request.BookRequest;
import com.abdellah.book.book.controller.request.BookResponse;
import com.abdellah.book.book.controller.request.BorrowedBookResponse;
import com.abdellah.book.book.domain.Book;
import com.abdellah.book.history.domain.BookTransactionHistory;
import com.abdellah.book.file.FileUtils;
import com.abdellah.book.history.domain.BookTransactionHistory;
import org.springframework.stereotype.Service;

@Service

public class BookMapper {
    public Book toBook(BookRequest request) {
        Book book = new Book();
        book.setId(request.getId());
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setAuthorName(request.getAuthorName());
        book.setSynopsis(request.getSynopsis());
        book.setArchived(false);
        book.setShareable(request.getShareable());
        return book;
    }

    public BookResponse toBookResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthorName(book.getAuthorName());
        response.setIsbn(book.getIsbn());
        response.setSynopsis(book.getSynopsis());
        response.setRate(book.getRate());
        response.setArchived(book.isArchived());
        response.setShareable(book.getShareable());
        // response.setOwner(book.getOwner().fullName());
        response.setCover(FileUtils.readFileFromLocation(book.getBookCover()));
        return response;
    }

    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory history) {
        BorrowedBookResponse response = new BorrowedBookResponse();
        response.setId(history.getBook().getId());
        response.setTitle(history.getBook().getTitle());
        response.setAuthorName(history.getBook().getAuthorName());
        response.setIsbn(history.getBook().getIsbn());
        response.setRate(history.getBook().getRate());
        response.setReturned(history.isReturned());
        response.setReturnApproved(history.isReturnApproved());
        return response;
    }
}