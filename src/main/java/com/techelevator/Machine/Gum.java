package com.techelevator.Machine;

import java.math.BigDecimal;

public class Gum extends Item{

    public Gum(String name, BigDecimal price, String foodType){
        super(name, price, foodType);
    }

    @Override
    public String makeSound() {
        return "Chew Chew, Yum!";
    }
}
