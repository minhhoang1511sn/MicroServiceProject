package com.josHoangDev.borrowingservice.command.api.aggregate;

import com.josHoangDev.borrowingservice.command.api.command.CreateBorrowCommand;
import com.josHoangDev.borrowingservice.command.api.command.DeleteBorrowCommand;
import com.josHoangDev.borrowingservice.command.api.command.SendMessageCommand;
import com.josHoangDev.borrowingservice.command.api.command.UpdateBookReturnCommand;
import com.josHoangDev.borrowingservice.command.api.events.BorrowCreatedEvent;
import com.josHoangDev.borrowingservice.command.api.events.BorrowDeletedEvent;
import com.josHoangDev.borrowingservice.command.api.events.BorrowSendMessageEvent;
import com.josHoangDev.borrowingservice.command.api.events.BorrowingUpdateBookReturnEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate
public class BorrowAggregate {
    @AggregateIdentifier
    private String id;

    private String bookId;
    private String employeeId;
    private Date borrowDate;
    private Date returnDate;

    private String message;

    public BorrowAggregate() {}

    @CommandHandler
    public BorrowAggregate(CreateBorrowCommand command) {
        BorrowCreatedEvent event = new BorrowCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(UpdateBookReturnCommand command) {
        BorrowingUpdateBookReturnEvent event = new BorrowingUpdateBookReturnEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(DeleteBorrowCommand command) {
        BorrowDeletedEvent event = new BorrowDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(SendMessageCommand command) {
        BorrowSendMessageEvent event = new BorrowSendMessageEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(BorrowCreatedEvent event) {
        this.id = event.getId();
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployeeId();
        this.borrowDate = event.getBorrowDate();
    }
    @EventSourcingHandler
    public void on(BorrowDeletedEvent event) {
        this.id = event.getId();

    }
    @EventSourcingHandler
    public void on(BorrowingUpdateBookReturnEvent event) {

        this.returnDate = event.getReturnDate();
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployee();
    }
    @EventSourcingHandler
    public void on(BorrowSendMessageEvent event) {
        this.id = event.getId();
        this.message = event.getMessage();
        this.employeeId = event.getEmployeeId();
    }
}
