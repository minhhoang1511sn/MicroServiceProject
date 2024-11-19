package com.josHoangDev.bookservice.query.projection;

import com.josHoangDev.bookservice.command.data.Book;
import com.josHoangDev.bookservice.command.data.BookRepository;
import com.josHoangDev.bookservice.query.model.BookResponeModel;
import com.josHoangDev.bookservice.query.queries.GetAllBooksQuery;
import com.josHoangDev.bookservice.query.queries.GetBookQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookProjection {

    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public BookResponeModel handle(GetBookQuery getBookQuery) {
        BookResponeModel bookResponeModel = new BookResponeModel();
        Book book = bookRepository.getById(getBookQuery.getBookId());
        BeanUtils.copyProperties(book, bookResponeModel);
        return bookResponeModel;
    }
    @QueryHandler
    List<BookResponeModel> handle(GetAllBooksQuery getAllBooksQuery) {
        List<Book> listEntity = bookRepository.findAll();
        List<BookResponeModel> bookResponeModels = new ArrayList<>();
        listEntity.forEach(entity -> {
            BookResponeModel bookResponeModel = new BookResponeModel();
            BeanUtils.copyProperties(entity, bookResponeModel);
            bookResponeModels.add(bookResponeModel);
        });
    return bookResponeModels;
    }
}
