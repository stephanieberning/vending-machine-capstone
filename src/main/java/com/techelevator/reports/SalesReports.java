package com.techelevator.reports;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.Date;


public class SalesReports {

    //total sales for sales report- updated every time purchase is made
    private BigDecimal machineTotalSales = new BigDecimal("0.00");

    //getters and setters
    public BigDecimal getMachineTotalSales() {
        return machineTotalSales;
    }

    public void setMachineTotalSales(BigDecimal priceToAdd) {
        machineTotalSales = machineTotalSales.add(priceToAdd);
    }

    //make a timestamp for the log
    public String getTimeStamp() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        String pattern = "MM/dd/yyyy hh:mm:ss a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formattedDate = simpleDateFormat.format(timestamp);
        return formattedDate;
    }

    //add a transaction to the audit log
    public void addToTransactionLog(String transactionDescription) {
        try{
            FileOutputStream stream = new FileOutputStream("log.txt", true);
            PrintWriter writer = new PrintWriter(stream);
            writer.println(getTimeStamp() + transactionDescription);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not find file. Try again.");
        }
    }
}







