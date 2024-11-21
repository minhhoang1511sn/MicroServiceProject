package com.josHoangDev.employeeService.command.data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;

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
    public void setIsDisciplined(Boolean isDisciplined) {
        this.isDisciplined = isDisciplined;
    }
}
