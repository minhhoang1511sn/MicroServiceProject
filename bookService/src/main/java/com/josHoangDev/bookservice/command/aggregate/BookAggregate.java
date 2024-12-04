package com.josHoangDev.bookservice.command.aggregate;

import com.josHoangDev.bookservice.command.command.CreateBookCommand;
import com.josHoangDev.bookservice.command.command.DeleteBookCommand;
import com.josHoangDev.bookservice.command.command.UpdateBookCommand;
import com.josHoangDev.bookservice.command.events.BookCreatedEvent;
import com.josHoangDev.bookservice.command.events.BookDeletedEvent;
import com.josHoangDev.bookservice.command.events.BookUpdatedEvent;
import com.josHoangDev.commonservice.command.UpdateStatusBookCommand;
import com.josHoangDev.commonservice.events.BookUpdatedStatusEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class BookAggregate {

    @AggregateIdentifier
    private String bookId;
    private String bookName;
    private String author;
    private Boolean isReady;

    public BookAggregate() {

    }

    @CommandHandler
    public BookAggregate(CreateBookCommand createBookCommand) {
        BookCreatedEvent bookCreatedEvent
                = new BookCreatedEvent();
        BeanUtils.copyProperties(createBookCommand, bookCreatedEvent);

        AggregateLifecycle.apply(bookCreatedEvent);
    }
    @CommandHandler
    public void handle(UpdateBookCommand updateBookCommand) {
        BookUpdatedEvent bookUpdatedEvent
                = new BookUpdatedEvent();
        BeanUtils.copyProperties(updateBookCommand, bookUpdatedEvent);

        AggregateLifecycle.apply(bookUpdatedEvent);
    }
    @CommandHandler
    public void handle(DeleteBookCommand deleteBookCommand) {
        BookDeletedEvent bookDeletedEvent
                = new BookDeletedEvent();
        BeanUtils.copyProperties(deleteBookCommand, bookDeletedEvent);

        AggregateLifecycle.apply(bookDeletedEvent);
    }
    @CommandHandler
    public void handle(UpdateStatusBookCommand command){
        BookUpdatedStatusEvent event = new BookUpdatedStatusEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(BookCreatedEvent event) {
        this.bookId = event.getBookId();
        this.bookName = event.getBookName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }
    @EventSourcingHandler
    public void on(BookUpdatedEvent event) {
        this.bookId = event.getBookId();
        this.bookName = event.getBookName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }
    @EventSourcingHandler
    public void on(BookDeletedEvent event) {
        this.bookId = event.getBookId();
    }

    @EventSourcingHandler
    public void on(BookUpdatedStatusEvent event){
        this.bookId = event.getBookId();
        this.isReady = event.getIsReady();
    }
}
