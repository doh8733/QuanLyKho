package com.quannm18.quanlykho.Model;

import com.google.gson.annotations.SerializedName;

public class Position {
    @SerializedName("_id")
    private String _id;
    @SerializedName("idWarehouse")
    private String idWarehouse;
    @SerializedName("namePosition")
    private String namePosition;
    @SerializedName("status")
    private String status;
    @SerializedName("__v")
    private String __v;

    public Position(String _id, String idWarehouse, String namePosition, String status, String __v) {
        this._id = _id;
        this.idWarehouse = idWarehouse;
        this.namePosition = namePosition;
        this.status = status;
        this.__v = __v;
    }

    public Position() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(String idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}
