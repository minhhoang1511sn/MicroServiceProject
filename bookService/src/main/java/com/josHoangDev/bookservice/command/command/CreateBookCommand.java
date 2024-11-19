package com.josHoangDev.bookservice.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateBookCommand {

    @TargetAggregateIdentifier
    private String bookId;
    private String bookName;
    private String author;
    private Boolean isReady;

    public CreateBookCommand(String bookId, String bookName, String author, Boolean isReady) {
       super();
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.isReady = isReady;
    }
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }
    public Boolean getIsReady() {
        return isReady;
    }
    public void setIsReady(Boolean isReady) {
        this.isReady = isReady;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
