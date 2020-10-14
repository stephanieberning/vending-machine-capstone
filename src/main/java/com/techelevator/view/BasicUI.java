package com.techelevator.view;

import java.math.BigDecimal;

public interface BasicUI {

    void output(String content);

    void pauseOutput();

    BigDecimal promptForBigDecimal();

    String promptForSelection(String[] options, boolean hideOption4);

    String promptForString();
}