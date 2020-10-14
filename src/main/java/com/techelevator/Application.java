package com.techelevator;

import com.techelevator.Exceptions.NotAWholeDollarAmountException;
import com.techelevator.Machine.VendingMachine;
import com.techelevator.view.BasicUI;
import com.techelevator.view.MenuDrivenCLI;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Application {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};

    private static final String SUBMENU_OPTIONS_FEED_MONEY = "Feed Money";
    private static final String SUBMENU_OPTIONS_SELECT_PRODUCT = "Select Product";
    private static final String SUBMENU_OPTIONS_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] SUBMENU_OPTIONS = {SUBMENU_OPTIONS_FEED_MONEY, SUBMENU_OPTIONS_SELECT_PRODUCT, SUBMENU_OPTIONS_FINISH_TRANSACTION};

    // our instance of vending machine
    private VendingMachine myMachine = new VendingMachine();

    private final BasicUI ui;

    public Application(BasicUI ui) {
        this.ui = ui;
    }

    public static void main(String[] args) {
        BasicUI cli = new MenuDrivenCLI();
        Application application = new Application(cli);
        application.run();
    }

    //vendng machine main menu
    public void run() {
        boolean finished = false;
        while (!finished) {
            String selection = ui.promptForSelection(MAIN_MENU_OPTIONS, true);

            if (selection.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                ui.output(myMachine.infoForPurchases());
            } else if (selection.equals(MAIN_MENU_OPTION_PURCHASE)) {
                handlePurchaseMenu();
            } else if (selection.equals(MAIN_MENU_OPTION_EXIT)) {
                finished = true;
            } else if (selection.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
                ui.output(myMachine.getMyInventory().displayInventoryForSalesReport() + "\n" + "**Total Sales** $" + myMachine.getMySalesReports().getMachineTotalSales());
            }
        }
    }

    //vending machine purchase menu
    private void handlePurchaseMenu() {
        String selection = ui.promptForSelection(SUBMENU_OPTIONS, false);
        boolean done = false;

        while (!done) {
            if (selection.equals(SUBMENU_OPTIONS_FEED_MONEY)) {
                myMachine.feedMoney();
                selection = ui.promptForSelection(SUBMENU_OPTIONS,false);
            } else if (selection.equals(SUBMENU_OPTIONS_SELECT_PRODUCT)) {
                ui.output(myMachine.infoForPurchases());
                ui.output("Please enter code of item you would like to purchase: ");
                String itemCode = ui.promptForString();
                ui.output(myMachine.purchase(itemCode));
                selection = ui.promptForSelection(SUBMENU_OPTIONS, false);
            } else if (selection.equals(SUBMENU_OPTIONS_FINISH_TRANSACTION)) {
                //language to display change and update current balance to zero
                ui.output(myMachine.giveChange());
                done = true;
            }
        }
    }

}
