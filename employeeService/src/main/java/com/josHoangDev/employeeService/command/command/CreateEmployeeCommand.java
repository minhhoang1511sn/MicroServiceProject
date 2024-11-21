package com.josHoangDev.employeeService.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateEmployeeCommand {

    @TargetAggregateIdentifier
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;

    public CreateEmployeeCommand(String employeeId, String firstName, String lastName, String kin, Boolean isDisciplined) {
       super();
       this.employeeId = employeeId;
       this.firstName = firstName;
       this.lastName = lastName;
       this.kin = kin;
       this.isDisciplined = isDisciplined;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getIsDisciplined() {
        return isDisciplined;
    }

    public String getKin() {
        return kin;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setKin(String kin) {
        this.kin = kin;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setIsDisciplined(Boolean disciplined) {
        isDisciplined = disciplined;
    }
}
