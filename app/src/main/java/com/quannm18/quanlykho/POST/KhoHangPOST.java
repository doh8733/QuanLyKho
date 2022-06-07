package com.quannm18.quanlykho.POST;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KhoHangPOST {
    private String name;
    private String row;
    private String floors;
    private String position;
    private String description;

    public KhoHangPOST(String name, String row, String floors, String position, String description) {
        this.name = name;
        this.row = row;
        this.floors = floors;
        this.position = position;
        this.description = description;
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
