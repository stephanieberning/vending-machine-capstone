package com.techelevator.Machine;

import java.math.BigDecimal;

public class Chip extends Item{

    public Chip(String name, BigDecimal price, String foodType){
        super(name, price, foodType);
    }

    @Override
    public String makeSound() {
        return "Crunch Crunch, Yum!";
    }
}
