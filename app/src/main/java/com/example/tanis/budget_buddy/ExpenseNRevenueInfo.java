package com.example.tanis.budget_buddy;

/**
 * Created by tanis on 2018-03-23.
 */

public class ExpenseNRevenueInfo {

    String description,currency,month;
    Double amount;

    ExpenseNRevenueInfo(String description,Double amount,String currency, String month)
    {
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.month = month;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setMonth (String month) {
        this.month = month;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public String getMonth () {return month;}
}
