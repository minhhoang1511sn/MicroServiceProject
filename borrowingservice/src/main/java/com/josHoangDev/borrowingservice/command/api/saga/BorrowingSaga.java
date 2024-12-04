package com.josHoangDev.borrowingservice.command.api.saga;

import com.josHoangDev.borrowingservice.command.api.command.DeleteBorrowCommand;
import com.josHoangDev.borrowingservice.command.api.events.BorrowCreatedEvent;
import com.josHoangDev.commonservice.command.UpdateStatusBookCommand;
import com.josHoangDev.commonservice.model.BookResponeCommonModel;
import com.josHoangDev.commonservice.query.GetDetailsBookQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class BorrowingSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    private void handle(BorrowCreatedEvent event){
        System.out.println("BorrowCreatedEvent in Saga for BookId" + event.getBookId() +" EmployeeId:" + event.getEmployeeId());

        try{
            SagaLifecycle.associateWith("bookId", event.getBookId());

            GetDetailsBookQuery getDetailsBookQuery
                    = new GetDetailsBookQuery(event.getBookId());

            BookResponeCommonModel bookResponeCommonModel
                    =queryGateway.query(getDetailsBookQuery,
                    ResponseTypes.instanceOf(BookResponeCommonModel.class)).join();
            if(bookResponeCommonModel.getIsReady()){
                UpdateStatusBookCommand command = new UpdateStatusBookCommand(event.getBookId(), false,event.getEmployeeId(), event.getId());
                commandGateway.sendAndWait(command);
            } else{
                throw new Exception("Sach da co nguoi mua");
            }
        }catch (Exception e){
            rollBackBorrowRecord(event.getId());

            System.out.println(e.getMessage());
        }
    }

    private void rollBackBorrowRecord(String id){
        commandGateway.sendAndWait(new DeleteBorrowCommand(id));
    }
}
