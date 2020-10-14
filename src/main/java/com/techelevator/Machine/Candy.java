package com.techelevator.Machine;

import java.math.BigDecimal;

public class Candy extends Item {

    //call constructor from parent
    public Candy(String name, BigDecimal price, String foodType){
        super(name, price, foodType);
    }

    @Override
    public String makeSound() {
        return "Munch Munch, Yum!";
    }
}
