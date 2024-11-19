package com.josHoangDev.bookservice.command.events;

import com.josHoangDev.bookservice.command.data.Book;
import com.josHoangDev.bookservice.command.data.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookEventsHandler {

    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(BookCreatedEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }
    @EventHandler
    public void on(BookUpdatedEvent event) {
        Book book = bookRepository.getById(event.getBookId());
        book.setBookName(event.getBookName());
        book.setIsReady(event.getIsReady());
        book.setAuthor(event.getAuthor());
        bookRepository.save(book);
    }
    @EventHandler
    public void on(BookDeletedEvent event) {

        bookRepository.deleteById(event.getBookId());
    }
}
