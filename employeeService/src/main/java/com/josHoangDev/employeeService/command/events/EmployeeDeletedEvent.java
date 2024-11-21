package com.josHoangDev.employeeService.command.events;

public class EmployeeDeletedEvent {
    private String employeeId;

    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
