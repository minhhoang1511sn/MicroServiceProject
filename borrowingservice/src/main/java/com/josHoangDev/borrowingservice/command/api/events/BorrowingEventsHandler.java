package com.josHoangDev.borrowingservice.command.api.events;

import com.josHoangDev.borrowingservice.command.api.data.Borrowing;
import com.josHoangDev.borrowingservice.command.api.data.BorrowingRepository;
import com.josHoangDev.borrowingservice.command.api.model.Message;
import com.josHoangDev.borrowingservice.command.api.service.BorrowService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BorrowingEventsHandler {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private BorrowService borrowService;

    @EventHandler
    public void on(BorrowCreatedEvent event) {
        Borrowing model = new Borrowing();
        BeanUtils.copyProperties(event, model);
        borrowingRepository.save(model);
    }
    @EventHandler
    public void on(BorrowDeletedEvent event){
        if(borrowingRepository.findById(event.getId()).isPresent()){
            borrowingRepository.deleteById(event.getId());
        }
        else return;
    }
    @EventHandler
    public void on(BorrowSendMessageEvent event) {
        Message message = new Message(event.getEmployeeId(), event.getMessage());
        borrowService.sendMessage(message);
    }
    @EventHandler
    public void on(BorrowingUpdateBookReturnEvent event) {
        Borrowing model = borrowingRepository.findByEmployeeIdAndBookIdAndReturnDateIsNull(event.getEmployee(), event.getBookId());
        model.setReturnDate(event.getReturnDate());
        borrowingRepository.save(model);
    }
}
