package com.AccLedgerApp.model;
import java.util.Date;

public class Transaction {

    private Long id;
    private Date date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    // Constructors

    public Transaction() {
    }

    public Transaction(Date date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // toString method (makes it purty)

    @Override
    public String toString() {
        return
                "\nDate | " + date +
                "\nTime | " + time +
                "\nDescription | " + description +
                "\nVendor | " + vendor +
                "\nAmount | " + amount;
    }
}
