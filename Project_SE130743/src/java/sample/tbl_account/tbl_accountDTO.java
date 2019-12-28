/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_account;

import java.io.Serializable;

/**
 *
 * @author tmax3
 */
public class tbl_accountDTO implements Serializable{
    private String accountID;
    private String pin;
    private float balance;
    private float benefit;
    private boolean isExpired;

    public tbl_accountDTO() {
    }

    public tbl_accountDTO(String accountID, String pin, float balance, float benefit, boolean isExpired) {
        this.accountID = accountID;
        this.pin = pin;
        this.balance = balance;
        this.benefit = benefit;
        this.isExpired = isExpired;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getPin() {
        return pin;
    }

    public float getBalance() {
        return balance;
    }

    public float getBenefit() {
        return benefit;
    }

    public boolean isIsExpired() {
        return isExpired;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setBenefit(float benefit) {
        this.benefit = benefit;
    }

    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }
    
    
    
}
