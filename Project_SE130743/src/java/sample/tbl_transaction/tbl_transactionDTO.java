/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_transaction;

import java.io.Serializable;

/**
 *
 * @author tmax3
 */
public class tbl_transactionDTO implements Serializable{
    private int transID;
    private String transDate;
    private int type;
    private float amount;
    private String reason;
    private boolean status;

    public tbl_transactionDTO() {
    }

    public tbl_transactionDTO(int transID, String transDate, int type, float amount, String reason, boolean status) {
        this.transID = transID;
        this.transDate = transDate;
        this.type = type;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
    }

    

    public int getTransID() {
        return transID;
    }

    public int getType() {
        return type;
    }

    public float getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    public boolean isStatus() {
        return status;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setIsHided(boolean status) {
        this.status = status;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }
    
    
    
    
}
