package com.quannm18.quanlykho;

import java.io.Serializable;

//Serialize
public class Employee implements Serializable {
    private String employeeName;
    private String employeePassword;
    private String employeeGender;
    private int employeeAge;
    private String employeeAddress;
    private String employeeStartWorkDate;
    private String employeeImage;

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

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
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
}
