package com.example.mariia.withoutdoctor;

/**
 * Created by ekaterina on 30.11.17.
 */

public class CompanyAccount implements Account {
    private long amountOfMoney;

    public long getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(long amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public CompanyAccount() {
        this.amountOfMoney=1000000;
    }
}
