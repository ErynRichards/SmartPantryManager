package com.example.smartpantrymanager;

public class PantryItem {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private String unit;
    private String expiryDate;

    public PantryItem(int id, String name, String category, int quantity, String unit, String expiryDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCategory(){
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit(){
        return unit;
    }

    public String getExpiryDate(){
        return expiryDate;
    }
}
