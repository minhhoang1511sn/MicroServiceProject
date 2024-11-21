package com.josHoangDev.employeeService.query.controller;

import com.josHoangDev.employeeService.query.model.BookResponseCommonModel;
import com.josHoangDev.employeeService.query.model.EmployeeResponeModel;
import com.josHoangDev.employeeService.query.queries.GetAllEmployeesQuery;
import com.josHoangDev.employeeService.query.queries.GetEmployeeQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{employeeId}")
    public EmployeeResponeModel getEmployeeDetails(@PathVariable("employeeId") String employeeId) {
        GetEmployeeQuery getBookQuery = new GetEmployeeQuery();
        getBookQuery.setEmployeeId(employeeId);

        EmployeeResponeModel employeeResponeModel =
                queryGateway.query(getBookQuery,
                        ResponseTypes.instanceOf(EmployeeResponeModel.class)).join();
        return employeeResponeModel;
    }
    @GetMapping
    public List<EmployeeResponeModel> getAllEmployee() {
        GetAllEmployeesQuery getAllEmployeesQuery = new GetAllEmployeesQuery();

        List<EmployeeResponeModel> list =
                queryGateway.query(getAllEmployeesQuery,
                        ResponseTypes.multipleInstancesOf(EmployeeResponeModel.class)).join();
        return list;
    }
//    @GetMapping("{employeeId}/books")
//    public List<BookResponseCommonModel> getEmployeeBorrowedBooks(@PathVariable("employeeId") String employeeId) {
//
//    }
}
