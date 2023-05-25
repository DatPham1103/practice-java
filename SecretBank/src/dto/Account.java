/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
/**
 *
 * @author s0ra
 */
public class Account implements Serializable{

    private String accountId;
    private String accountName;
    private String password;

    public Account(String accountId, String password) {
        this.accountId = accountId;
        this.password = password;
    }
    
    public Account(String accountId, String accountName, String password) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.password = password;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", accountName=" + accountName + ", password=" + password + '}';
    } 
}   

   