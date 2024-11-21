package com.josHoangDev.employeeService.command.controller;

import com.josHoangDev.employeeService.command.command.CreateEmployeeCommand;
import com.josHoangDev.employeeService.command.command.DeleteEmployeeCommand;
import com.josHoangDev.employeeService.command.command.UpdateEmployeeCommand;
import com.josHoangDev.employeeService.command.model.EmployeeRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addEmployee(@RequestBody EmployeeRequestModel model){
        CreateEmployeeCommand command = new CreateEmployeeCommand(UUID.randomUUID().toString(),model.getFirstName(),
                model.getLastName(),model.getKin(),false);
        commandGateway.sendAndWait(command);
        return "Employee added successfully";
    }
    @PutMapping
    public String updateEmployee(@RequestBody EmployeeRequestModel model){
        UpdateEmployeeCommand command = new UpdateEmployeeCommand(model.getEmployeeId(),model.getFirstName(),
                model.getLastName(),model.getKin(),model.getIsDisciplined());
        commandGateway.sendAndWait(command);
        return "Employee updated successfully";
    }
    @DeleteMapping("/{employeeId}")
    public String deleteEmlpoyee(@PathVariable String employeeId){
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
        commandGateway.sendAndWait(command);
        return "Employee deleted successfully";
    }
}
