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
public class Bank implements Serializable {
    private Account acc;
    private double money;

    public Bank(Account acc, double money) {
        this.acc = acc;
        this.money = money;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Bank{" + "acc=" + acc + ", money=" + money + '}';
    }
    
}
