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
public class tbl_transactionCreateError implements Serializable{
    private String invalidAmount; //Lỗi tiền ko hợp lệ(nhiều hơn balance, nhập chữ)
    private String accountNotExisted; //Lỗi tài khoản ko tồn tại
    private String accountIsExpired; //Lỗi tài khoản hết hạn
    private String accountsAreTheSame; //Lỗi người chuyển và người nhận trùng ID
    private String sessionTimeout; //Session hết hạn

    public tbl_transactionCreateError() {
    }

    public String getInvalidAmount() {
        return invalidAmount;
    }

    public String getAccountNotExisted() {
        return accountNotExisted;
    }

    public String getAccountIsExpired() {
        return accountIsExpired;
    }

    public String getAccountsAreTheSame() {
        return accountsAreTheSame;
    }

    public String getSessionTimeout() {
        return sessionTimeout;
    }

    public void setInvalidAmount(String invalidAmount) {
        this.invalidAmount = invalidAmount;
    }

    public void setAccountNotExisted(String accountNotExisted) {
        this.accountNotExisted = accountNotExisted;
    }

    public void setAccountIsExpired(String accountIsExpired) {
        this.accountIsExpired = accountIsExpired;
    }

    public void setAccountsAreTheSame(String accountsAreTheSame) {
        this.accountsAreTheSame = accountsAreTheSame;
    }

    public void setSessionTimeout(String sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
    
}
