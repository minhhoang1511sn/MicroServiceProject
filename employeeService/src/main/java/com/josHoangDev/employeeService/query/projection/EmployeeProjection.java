package com.josHoangDev.employeeService.query.projection;

import com.josHoangDev.employeeService.command.data.Employee;
import com.josHoangDev.employeeService.command.data.EmployeeRepository;
import com.josHoangDev.employeeService.query.model.EmployeeResponeModel;
import com.josHoangDev.employeeService.query.queries.GetAllEmployeesQuery;
import com.josHoangDev.employeeService.query.queries.GetEmployeeQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeProjection {

    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryHandler
    public EmployeeResponeModel handle(GetEmployeeQuery getEmployeeQuery) {
        EmployeeResponeModel bookResponeModel = new EmployeeResponeModel();
        Employee employee = employeeRepository.getById(getEmployeeQuery.getEmployeeId());
        BeanUtils.copyProperties(employee, bookResponeModel);
        return bookResponeModel;
    }
    @QueryHandler
    List<EmployeeResponeModel> handle(GetAllEmployeesQuery getAllBooksQuery) {
        List<Employee> listEntity = employeeRepository.findAll();
        List<EmployeeResponeModel> bookResponeModels = new ArrayList<>();
        listEntity.forEach(entity -> {
            EmployeeResponeModel bookResponeModel = new EmployeeResponeModel();
            BeanUtils.copyProperties(entity, bookResponeModel);
            bookResponeModels.add(bookResponeModel);
        });
    return bookResponeModels;
    }
}
