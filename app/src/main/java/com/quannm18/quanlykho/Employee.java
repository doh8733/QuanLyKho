package com.quannm18.quanlykho;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//Serialize
public class Employee implements Serializable {
    @SerializedName("hoTen")
    private String employeeName;

    @SerializedName("matKhau")
    private String employeePassword;

    @SerializedName("gioiTinh")
    private String employeeGender;
    private String employeeAge;
    private String employeeAddress;
    private String employeeStartWorkDate;
    private String employeeImage;
    private String token;

    public Employee() {
    }

    public Employee(String employeeName, String employeePassword) {
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
    }

    public Employee(String employeeName, String employeeGender, String employeeAge, String employeeAddress, String employeeStartWorkDate) {
        this.employeeName = employeeName;
        this.employeeGender = employeeGender;
        this.employeeAge = employeeAge;
        this.employeeAddress = employeeAddress;
        this.employeeStartWorkDate = employeeStartWorkDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(String employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeStartWorkDate() {
        return employeeStartWorkDate;
    }

    public void setEmployeeStartWorkDate(String employeeStartWorkDate) {
        this.employeeStartWorkDate = employeeStartWorkDate;
    }

    public String getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(String employeeImage) {
        this.employeeImage = employeeImage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
