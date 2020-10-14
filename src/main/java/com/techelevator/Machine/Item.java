package com.techelevator.Machine;

import java.math.BigDecimal;

public abstract class Item {
    //constant used in constructor
    private static final int DEFAULT_QUANTITY = 5;

    private String name;
    private BigDecimal price;
    private String itemCode;
    private String foodType;
    private int quantity;

    //constructor
    public Item(String name, BigDecimal price, String foodType) {
        this.name = name;
        this.price = price;
        this.foodType = foodType;
        this.quantity = DEFAULT_QUANTITY;
    }

    //getters, setters

    public String getPrice() {
        return price.toString();
    }

    public BigDecimal getPriceAsDecimal() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String makeSound(){
     return "Default Sound";
    }
}
