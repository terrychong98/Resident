package com.example.terry.resident;

public class TransactionDetails {
    String action;
    String date;
    Double amount;

    public TransactionDetails() {
    }

    public TransactionDetails(String action,String date, Double amount) {
        this.action = action;
        this.date = date;
        this.amount = amount;
    }

    public String getAction() {
        return action;
    }

    public String getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }
}
