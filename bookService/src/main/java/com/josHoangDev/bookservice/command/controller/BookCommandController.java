package com.josHoangDev.bookservice.command.controller;

import com.josHoangDev.bookservice.command.command.CreateBookCommand;
import com.josHoangDev.bookservice.command.command.DeleteBookCommand;
import com.josHoangDev.bookservice.command.command.UpdateBookCommand;
import com.josHoangDev.bookservice.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
public class BookCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addBook(@RequestBody BookRequestModel model){
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(),model.getBookName(),
                model.getAuthor(),true);
        commandGateway.sendAndWait(command);
        return "Book added successfully";
    }
    @PutMapping
    public String updateBook(@RequestBody BookRequestModel model){
        UpdateBookCommand command = new UpdateBookCommand(model.getBookId(), model.getBookName(),
                model.getAuthor(), model.getIsReady());
        commandGateway.sendAndWait(command);
        return "Book updated successfully";
    }
    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId){
        DeleteBookCommand command = new DeleteBookCommand(bookId);
        commandGateway.sendAndWait(command);
        return "Book deleted successfully";
    }
}
