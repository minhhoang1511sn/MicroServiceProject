package com.josHoangDev.employeeService.command.events;

import com.josHoangDev.employeeService.command.data.Employee;
import com.josHoangDev.employeeService.command.data.EmployeeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventsHandler {

    @Autowired
    private EmployeeRepository employeeRepository;

    @EventHandler
    public void on(EmployeeCreatedEvent event) {
        Employee book = new Employee();
        BeanUtils.copyProperties(event, book);
        employeeRepository.save(book);
    }
    @EventHandler
    public void on(EmployeeUpdatedEvent event) {
        Employee employee = employeeRepository.getById(event.getEmployeeId());
        employee.setIsDisciplined(event.getIsDisciplined());
        employee.setKin(event.getKin());
        employee.setFirstName(event.getFirstName());
        employee.setLastName(event.getLastName());
        employeeRepository.save(employee);
    }
    @EventHandler
    public void on(EmployeeDeletedEvent event) {

        employeeRepository.deleteById(event.getEmployeeId());
    }
}
