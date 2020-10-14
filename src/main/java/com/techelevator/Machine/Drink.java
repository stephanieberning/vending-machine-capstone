package com.techelevator.Machine;

import java.math.BigDecimal;

public class Drink extends Item {

    public Drink(String name, BigDecimal price, String foodType){
        super(name, price, foodType);
    }

    @Override
    public String makeSound() {
        return "Glug Glug, Yum!";
    }
}
