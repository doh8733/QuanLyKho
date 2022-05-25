package com.quannm18.quanlykho.Model;

public class Kho {
    String  Row;
    String Floors;
    String Position;
    String Used;
    String Available;
    String Product;
    String Broken;
    String Finished;

    public Kho(String row, String floors, String position, String used, String available, String product, String broken, String finished) {
        Row = row;
        Floors = floors;
        Position = position;
        Used = used;
        Available = available;
        Product = product;
        Broken = broken;
        Finished = finished;
    }

    public String getRow() {
        return Row;
    }

    public void setRow(String row) {
        Row = row;
    }

    public String getFloors() {
        return Floors;
    }

    public void setFloors(String floors) {
        Floors = floors;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getUsed() {
        return Used;
    }

    public void setUsed(String used) {
        Used = used;
    }

    public String getAvailable() {
        return Available;
    }

    public void setAvailable(String available) {
        Available = available;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getBroken() {
        return Broken;
    }

    public void setBroken(String broken) {
        Broken = broken;
    }

    public String getFinished() {
        return Finished;
    }

    public void setFinished(String finished) {
        Finished = finished;
    }
}
