package com.josHoangDev.userservice.model;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String employeeId;
    private String token;
    private String refreshtoken;

    public UserDTO(){}

    public UserDTO(long l, String mail, String password, String employeeID, String token, String refresh) {
        this.id = l;
        this.username = mail;
        this.password = password;
        this.employeeId = employeeID;
        this.token = token;
        this.refreshtoken = refresh;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }
    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }


}