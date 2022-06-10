package com.quannm18.quanlykho.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NhanVien {
    @SerializedName("_id")
    private String _id;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("userName")
    private String userName;
    @SerializedName("password")
    private String password;
    @SerializedName("gender")
    private String gender;
    @SerializedName("age")
    private String age;
    @SerializedName("address")
    private String address;
    @SerializedName("startWorkDate")
    private String startWorkDate;
    @SerializedName("role")
    private String role;
    @SerializedName("tokens")
    private List<Token> tokens;

    public NhanVien(String _id, String fullName, String userName, String password, String gender, String age, String address, String startWorkDate, String role, List<Token> tokens) {
        this._id = _id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.startWorkDate = startWorkDate;
        this.role = role;
        this.tokens = tokens;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(String startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens( List<Token>  tokens) {
        this.tokens = tokens;
    }
}