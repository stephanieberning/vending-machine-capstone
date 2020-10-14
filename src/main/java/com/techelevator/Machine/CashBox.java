package com.techelevator.Machine;

import com.techelevator.Exceptions.NotAWholeDollarAmountException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CashBox {

    //contains customer balance during purchase transaction
    private BigDecimal customerBalance = new BigDecimal("0.00");

    // getters, setters
    public BigDecimal getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(BigDecimal customerBalance) {
        this.customerBalance = customerBalance;
    }

    //deposit method used in purchase menu
    public void deposit(BigDecimal moneyIn) throws NotAWholeDollarAmountException {

        BigDecimal is10 = new BigDecimal("10.00");
        BigDecimal is0 = new BigDecimal("0.00");
        if (((moneyIn.multiply(is10)).remainder(is10)).compareTo(is0) != 0 || moneyIn.compareTo(is0) < 0 ) {
            throw new NotAWholeDollarAmountException("Not a whole dollar amount.");
        } else{
            customerBalance = customerBalance.add(moneyIn);
        }
    }

    //calculate how to make change out of quarters, dimes, and nickels
    public String makeChange(BigDecimal customerChange) {
        BigDecimal total = customerChange;
        BigDecimal quarters = new BigDecimal("0");
        BigDecimal dimes = new BigDecimal("0");
        BigDecimal nickels = new BigDecimal("0");

        quarters = total.divide(new BigDecimal("0.25")).setScale(0, RoundingMode.DOWN);
        total = total.subtract(quarters.multiply(new BigDecimal("0.25")));

        dimes = total.divide(new BigDecimal("0.10")).setScale(0, RoundingMode.DOWN);
        total = total.subtract(dimes.multiply(new BigDecimal("0.10")));
        nickels = total.divide(new BigDecimal("0.05")).setScale(0, RoundingMode.DOWN);

        return quarters + " Quarter(s), " + dimes + " Dime(s), and " + nickels + " Nickel(s).";
    }
}



