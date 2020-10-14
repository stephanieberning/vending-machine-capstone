package com.techelevator.Machine;

import com.techelevator.Exceptions.NotAWholeDollarAmountException;
import com.techelevator.reports.SalesReports;
import com.techelevator.view.BasicUI;
import com.techelevator.view.MenuDrivenCLI;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachine {

    private final BasicUI myUI = new MenuDrivenCLI();

    //vending machine has instances of cashbox, salesreport and inventory
    private CashBox myCashBox = new CashBox();
    private static Inventory myInventory = new Inventory();
    private SalesReports mySalesReports = new SalesReports();


    //constructor
    public VendingMachine() {
        myInventory.createInventory("inventory.txt");
    }

    //getters, setters, and helper methods
    public CashBox getMyCashBox() {
        return myCashBox;
    }

    public String infoForPurchases() {
        return myInventory.displayInventory();
    }

    public BigDecimal priceByCode(String itemCode) {
        return myInventory.getAPrice(itemCode);
    }

    public SalesReports getMySalesReports() {
        return mySalesReports;
    }

    public Inventory getMyInventory() {
        return myInventory;
    }

    //make sure to handle if someone tries to deposit a neg amount
    //vending machine deposit helper method - connects to cashbox
    public void feedMoney() {
        myUI.output("Please enter a whole dollar value to deposit.");
        try {
            BigDecimal amount = myUI.promptForBigDecimal();
            BigDecimal amountReFormatted = amount.setScale(2, RoundingMode.CEILING); //for formatting
            try {
                getMyCashBox().deposit(amount);
                getMySalesReports().addToTransactionLog(" FEED MONEY: $" + amountReFormatted + " $" + getMyCashBox().getCustomerBalance());
                myUI.output("Your balance is: $" + getMyCashBox().getCustomerBalance().toString());
            } catch (NotAWholeDollarAmountException e) {
                myUI.output("Not a whole dollar amount. Please try again.");
            }
        } catch (NumberFormatException n) {
            myUI.output("Not a dollar amount. Please try again.");
        }
    }

    //purchase method used by purchase menu
    public String purchase(String itemCode) {
        BigDecimal another0 = new BigDecimal("0.00");
        if (myCashBox.getCustomerBalance().compareTo(another0) == 0) {
            return "Error: Zero balance.";
        } else if (!myInventory.checkForItemCode(itemCode)) {
            return "Error: please select an item from the available options.";
        } else if (priceByCode(itemCode).compareTo(myCashBox.getCustomerBalance()) > 0) {
            return "Error: Deposit more money to purchase this item";
        } else if (myInventory.itemSoldOut(itemCode)) {
            return "Item sold out, choose something else.";
        } else {
            myCashBox.setCustomerBalance(myCashBox.getCustomerBalance().subtract(priceByCode(itemCode)));
            mySalesReports.addToTransactionLog(" " + myInventory.getItem(itemCode).getName() + " " + itemCode + " $" + myInventory.getAPrice(itemCode) + " $" + myCashBox.getCustomerBalance());
            myInventory.setQuantity(itemCode);
            mySalesReports.setMachineTotalSales(myInventory.getAPrice(itemCode));
        }
        return myInventory.getItem(itemCode).makeSound() + "\n" + "You have " + myCashBox.getCustomerBalance() + " left to spend.";
    }

    //set customer balance to zero and say "returned ___ change"
    public String giveChange() {
        String change = "Your change is " + myCashBox.getCustomerBalance() + "\n" + "Which is " + myCashBox.makeChange(myCashBox.getCustomerBalance());
        mySalesReports.addToTransactionLog(" GIVE CHANGE: $" + myCashBox.getCustomerBalance() + " " + "$0.00" + "\n");
        myCashBox.setCustomerBalance(new BigDecimal("0.00"));
        return change;
    }
}
