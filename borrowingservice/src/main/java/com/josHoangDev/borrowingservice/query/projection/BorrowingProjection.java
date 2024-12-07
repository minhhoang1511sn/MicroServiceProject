package com.josHoangDev.borrowingservice.query.projection;


import com.josHoangDev.borrowingservice.command.api.data.Borrowing;
import com.josHoangDev.borrowingservice.command.api.data.BorrowingRepository;
import com.josHoangDev.borrowingservice.query.model.BorrowingResponseModel;
import com.josHoangDev.borrowingservice.query.queries.GetAllBorrowing;
import com.josHoangDev.borrowingservice.query.queries.GetListBorrowingByEmployeeQuery;
import com.josHoangDev.commonservice.model.BookResponseCommonModel;
import com.josHoangDev.commonservice.model.EmployeeResponseCommonModel;
import com.josHoangDev.commonservice.query.GetDetailsBookQuery;
import com.josHoangDev.commonservice.query.GetDetailsEmployeeQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BorrowingProjection {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired QueryGateway queryGateway;

    @QueryHandler
    public List<BorrowingResponseModel> handle(GetListBorrowingByEmployeeQuery query){
        List<BorrowingResponseModel> list = new ArrayList<>();

        List<Borrowing> borrowings = borrowingRepository.findByEmployeeIdAndReturnDateIsNull(query.getEmployeeId());

        borrowings.forEach(borrowing -> {
            BorrowingResponseModel model = new BorrowingResponseModel();
            BeanUtils.copyProperties(borrowing, model);
            BookResponseCommonModel book = queryGateway.query(new GetDetailsBookQuery(model.getBookId()), ResponseTypes.instanceOf(BookResponseCommonModel.class)).join();
            model.setNameBook(book.getName());
            EmployeeResponseCommonModel employee = queryGateway.query(new GetDetailsEmployeeQuery(model.getEmployeeId()), ResponseTypes.instanceOf(EmployeeResponseCommonModel.class)).join();
            model.setNameEmployee(employee.getFirstName()+" "+employee.getLastName());
            list.add(model);
        });
        return list;
    }

    @QueryHandler
    public List<BorrowingResponseModel> handle(GetAllBorrowing query){
        List<BorrowingResponseModel> list  = new ArrayList<>();
        List<Borrowing> listEntity = borrowingRepository.findAll();
        listEntity.forEach(s ->{
            BorrowingResponseModel model = new BorrowingResponseModel();
            BeanUtils.copyProperties(s, model);
            model.setNameBook(queryGateway.query(new GetDetailsBookQuery(model.getBookId()), ResponseTypes.instanceOf(BookResponseCommonModel.class)).join().getName());
            EmployeeResponseCommonModel employee = queryGateway.query(new GetDetailsEmployeeQuery(model.getEmployeeId()), ResponseTypes.instanceOf(EmployeeResponseCommonModel.class)).join();
            model.setNameEmployee(employee.getFirstName()+" "+employee.getLastName());
            list.add(model);
        });
        return list;
    }
}
