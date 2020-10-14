package com.techelevator.view;

import java.math.BigDecimal;
import java.util.Scanner;

public class MenuDrivenCLI implements BasicUI {

    private final Scanner userInput = new Scanner(System.in);
    private final Menu menu = new Menu(System.in, System.out);


    @Override
    public void output(String content) {
        System.out.println(); //Print blank line
        System.out.println(content);
    }

    @Override
    public void pauseOutput() {
        System.out.println("(Press enter to continue)");
        userInput.nextLine();
    }

    @Override
    public String promptForSelection(String[] options, boolean hideOption4) {
        return (String) menu.getChoiceFromOptions(options, hideOption4);
    }

    //get deposit amount from user
    public BigDecimal promptForBigDecimal() {
        return new BigDecimal(userInput.nextLine());
    }

    //get item code from user
    public String promptForString() {
        return new String(userInput.nextLine());
    }
}
