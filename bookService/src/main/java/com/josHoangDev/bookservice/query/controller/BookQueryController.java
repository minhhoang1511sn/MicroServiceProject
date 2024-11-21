package com.josHoangDev.bookservice.query.controller;

import com.josHoangDev.bookservice.query.model.BookResponeModel;
import com.josHoangDev.bookservice.query.queries.GetAllBooksQuery;
import com.josHoangDev.bookservice.query.queries.GetBookQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{bookId}")
    public BookResponeModel getBookDetails(@PathVariable("bookId") String bookId) {
        GetBookQuery getBookQuery = new GetBookQuery();
        getBookQuery.setBookId(bookId);

        BookResponeModel bookResponeModel =
                queryGateway.query(getBookQuery,
                        ResponseTypes.instanceOf(BookResponeModel.class)).join();
        return bookResponeModel;
    }
    @GetMapping
    public List<BookResponeModel> getAllBooks() {
        GetAllBooksQuery getAllBooksQuery = new GetAllBooksQuery();

        List<BookResponeModel> list =
                queryGateway.query(getAllBooksQuery,
                        ResponseTypes.multipleInstancesOf(BookResponeModel.class)).join();
        return list;
    }
}
