package com.quannm18.quanlykho.Model;

import com.google.gson.annotations.SerializedName;

public class UserAdding {
    @SerializedName("message")
    private String message;

    public UserAdding(String message) {
        this.message = message;
    }

    public UserAdding() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
