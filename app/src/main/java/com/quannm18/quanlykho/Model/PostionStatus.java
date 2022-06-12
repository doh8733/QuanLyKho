package com.quannm18.quanlykho.Model;

import com.google.gson.annotations.SerializedName;

public class PostionStatus {
    @SerializedName("status")
    private String status;

    public PostionStatus(String status) {
        this.status = status;
    }

    public PostionStatus() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
