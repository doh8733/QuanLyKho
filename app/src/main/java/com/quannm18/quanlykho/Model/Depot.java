package com.quannm18.quanlykho.Model;

public class Depot {
    private String name;
    private int row;
    private int floor;
    private int position;
    private String description;

    public Depot(String name, int row, int floor, int position, String description) {
        this.name = name;
        this.row = row;
        this.floor = floor;
        this.position = position;
        this.description = description;
    }

    public Depot() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "name='" + name + '\'' +
                ", row='" + row + '\'' +
                ", floor='" + floor + '\'' +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
