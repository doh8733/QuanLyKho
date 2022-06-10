package com.quannm18.quanlykho.Model;

import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("_id")
    private String _id;
    @SerializedName("token")
    private String token;

    public Token() {
    }

    public Token(String _id, String token) {
        this._id = _id;
        this.token = token;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
