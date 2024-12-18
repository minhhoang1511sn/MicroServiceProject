package com.josHoangDev.borrowingservice.command.api.controller;

import com.josHoangDev.borrowingservice.command.api.command.CreateBorrowCommand;
import com.josHoangDev.borrowingservice.command.api.command.UpdateBookReturnCommand;
import com.josHoangDev.borrowingservice.command.api.model.BorrowRequestModel;
import com.josHoangDev.borrowingservice.command.api.service.BorrowService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/borrowing")
public class BorrowCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public String addBookBorrowing(@RequestBody BorrowRequestModel model){
        try{
            CreateBorrowCommand command
                    = new CreateBorrowCommand(UUID.randomUUID().toString(),
                    model.getBookId(), model.getEmployeeId(), new Date());
            commandGateway.sendAndWait(command);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "Book borrowing added";
    }
    @PutMapping
    public String updateBookReturn(@RequestBody BorrowRequestModel model) {
        UpdateBookReturnCommand command = new UpdateBookReturnCommand(borrowService.findIdBorrowing(model.getEmployeeId(), model.getBookId()), model.getBookId(),model.getEmployeeId(),new Date());
        commandGateway.sendAndWait(command);
        return "Book returned";
    }
}
