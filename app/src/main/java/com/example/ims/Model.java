package com.example.ims;

public class Model {
    String c1;
    String username;
    String role;
    String mname;
    String model;
    String brand;
    String quantity;
    String sold;
    String total;
    String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    Model()
    {

    }

    public Model(String c1, String username, String role, String mname, String model, String brand, String quantity, String sold,String total,String price) {
        this.c1 = c1;
        this.username = username;
        this.role = role;
        this.mname = mname;
        this.model = model;
        this.brand = brand;
        this.quantity = quantity;
        this.sold = sold;
        this.total=total;
        this.price=price;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }
}