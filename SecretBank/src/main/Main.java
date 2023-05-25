/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dto.I_List;
import controller.List;
import controller.Menu;
import dto.Bank;
import dto.I_Menu;
import java.io.IOException;

/**
 *
 * @author s0ra
 */
public class Main {
    public static void main(String[] args) throws IOException {
        I_Menu menu = new Menu();
        I_List list = new List();
        Bank objBanking = null;
        String fileName = "bank.dat";
        String file = "user.dat";
        menu.addItem("----------------------------  MENU ----------------------------");
        menu.addItem("            1.Create new account                               ");
        menu.addItem("            2.Log in                                           ");
        menu.addItem("            3.Withdraw money                                   ");
        menu.addItem("            4.Deposit money                                    ");
        menu.addItem("            5.Transfer money                                   ");
        menu.addItem("            6.Remove account                                   ");
        menu.addItem("            Others = Quit                                      ");
        menu.addItem("---------------------------------------------------------------");
        int choice;
        boolean flag = false;
        list.loadDataBankingFromFile();
        do {
            menu.showMenu();
            choice = menu.getChoice(1, 6);
            switch (choice) {
                case 1:
                    list.createAccount();
                    break;
                case 2:
                    objBanking = list.logIn();
                    if (objBanking != null) {
                        System.out.println("Login is successful.User can use the bellow fuctions.");
                        list.saveDataAccountToFile(file);
                        list.saveDataBankingToFile(fileName);
                    } else {
                        System.out.println("Login failed.");
                    }
                    break;
                case 3:
                    if (objBanking == null) {
                        System.out.println("You must login before use...Return Menu.");
                    } else {
                        list.withdrawMoney(objBanking);
                        list.saveDataBankingToFile(fileName);
                    }
                    break;
                case 4:
                    if (objBanking == null) {
                        System.out.println("You must login before use...Return Menu.");
                    } else {
                        list.depositMoney(objBanking);
                        list.saveDataBankingToFile(fileName);
                    }
                    break;
                case 5:
                    if (objBanking == null) {
                        System.out.println("You must login before use...Return Menu.");
                    } else {
                        list.transferMoney(objBanking);
                        list.saveDataBankingToFile(fileName);
                    }
                    break;
                case 6:
                    if (objBanking == null) {
                        System.out.println("You must login before use...Return Menu.");
                    } else {
                        list.removeAccount(objBanking);
                        list.saveDataBankingToFile(fileName);
                        objBanking = null;
                    }
                    break;
                default:
                    System.out.println("Goodbye!!");
                    break;
            }
        } while (choice >= 1 && choice <= 6 && !flag);
    }
}
