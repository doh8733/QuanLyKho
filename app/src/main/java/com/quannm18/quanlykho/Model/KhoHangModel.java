package com.quannm18.quanlykho.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KhoHangModel {
    @Expose
    @SerializedName("_id")
    private String _id;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("row")
    private String row;
    @Expose
    @SerializedName("floors")
    private String floors;
    @Expose
    @SerializedName("position")
    private String position;
    @Expose
    @SerializedName("description")
    private String description;

    public KhoHangModel() {
    }
    public KhoHangModel(String id, String name, String row, String floors, String position, String description) {
        this._id = id;
        this.name = name;
        this.row = row;
        this.floors = floors;
        this.position = position;
        this.description = description;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
