/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.Account;
import dto.Bank;
import dto.I_List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import utils.Util;

/**
 *
 * @author s0ra
 */
public class List extends ArrayList implements I_List {

    ArrayList<Account> listAcc = new ArrayList();
    ArrayList<Bank> list = new ArrayList();

    public boolean checkExistID(String accountID) {
        boolean flag = true;
        for (int i = 0; i < listAcc.size(); i++) {
            if (listAcc.get(i).getAccountId().equals(accountID)) {
                System.out.println("ID Existed!");
                return !flag;
            }
        }
        return flag;
    }

    public String encodedPassword(String password) {
        return Base64.getEncoder().withoutPadding().encodeToString(password.getBytes());
    }

    public String decodedPassword(String password) {
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

    public Bank getBankByID(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAcc().getAccountId().equals(id)) {
                return list.get(i);
            }
        }
        return null;
    }

    @Override
    public void loadDataBankingFromFile() {
        list.clear();
        try {
            FileInputStream fis = new FileInputStream("bank.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList<Bank>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void saveDataBankingToFile(String fileName) {
        try {
            File f = new File(fileName);
            try (FileWriter fw = new FileWriter(f); PrintWriter pw = new PrintWriter(fw)) {
                list.forEach((x) -> {
                    pw.println(x.getAcc()+ "-" + x.getMoney());
                });
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void saveDataAccountToFile(String file) {
        try {
            File f = new File(file);
            try (FileWriter fw = new FileWriter(f); PrintWriter pw = new PrintWriter(fw)) {
                listAcc.forEach((x) -> {
                    pw.println(x.getAccountId()+ "-" + x.getAccountName()+ "-" + encodedPassword(x.getPassword()));
                });
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    @Override
    public void createAccount() {
        boolean isSameID;
        boolean isConfirmedPassword = false;
        String accountId, accountName, password, passwordConfirm;
        do {
            accountId = Util.getStringRegex("Enter user ID (BANKxxx): ", "^BANK\\d{3}");
            isSameID = checkExistID(accountId);
        } while (isSameID == false);
        accountName = Util.getString("Enter user name: ");
        password = Util.getStringRegex("Enter password: ", "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$");
        do {
            passwordConfirm = Util.getString("Confirm your password: ");
            if (passwordConfirm.equals(password)) {
                isConfirmedPassword = true;
            } else {
                System.out.println("Password does not match!");
            }
        } while (!isConfirmedPassword);
        listAcc.add(new Account(accountId, encodedPassword(password)));
        Account acc = new Account(accountId, accountName, encodedPassword(password));
        list.add(new Bank(acc, 0));
        System.out.println("Create account successfully!!! ");
//        saveDataAccountToFile();
//        saveDataBankingToFile();
    }

    @Override
    public Bank logIn() {
        String accountID = Util.getStringRegex("Enter Account ID: ", "^BANK\\d{3}");
        String password = Util.getString("Enter Password: ");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAcc().getAccountId().equals(accountID) && decodedPassword(list.get(i).getAcc().getPassword()).equals(password)) {
                return list.get(i);
            }
        }
        return null;
    }

    @Override
    public void withdrawMoney(Bank accountBank) {
        Bank objBanking = getBankByID(accountBank.getAcc().getAccountId());
        double moneyToWithdraw = Util.getDouble("Enter The Amount Want To Withdraw: ", "More than 0.Input again!", 0);
        if (moneyToWithdraw > objBanking.getMoney()) {
            System.out.println("The amount you withdraw is higher than the amount in the account.Error.");
        } else {
            objBanking.setMoney(objBanking.getMoney() - moneyToWithdraw);
            System.out.println("You just withdrew " + moneyToWithdraw + " from your account.");
            System.out.println("Balance: " + objBanking.getMoney());
//            saveDataBankingToFile();
        }
    }

    @Override
    public void depositMoney(Bank accountBank) {
        Bank objBanking = getBankByID(accountBank.getAcc().getAccountId());
        double moneyToDeposit = Util.getDouble("Enter The Amount Want To Deposit: ", "More than 0.Input again!", 0);
        boolean confirm = Util.confirm("Are you sure(Y/N): ", "Y/N Only");
        if (confirm == true) {
            objBanking.setMoney(objBanking.getMoney() + moneyToDeposit);
            System.out.println("You just deposited " + moneyToDeposit + " into your account.");
            System.out.println("Balance: " + objBanking.getMoney());
//            saveDataBankingToFile();
        } else {
            System.out.println("You have just canceled the deposit to your account.");
        }
    }

    @Override
    public void transferMoney(Bank accountBank) {
        Bank objBanking = getBankByID(accountBank.getAcc().getAccountId());
        String accountIDReceive = Util.getStringRegex("Enter Receiver's ID: ", "^BANK\\d{3}");
        double moneyTransfer = Util.getDouble("Enter Money To Transfer: ", "More than 0", 0);
        Bank bankingReceive = getBankByID(accountIDReceive);
        if (bankingReceive == null) {
            System.out.println("Account's Banking does not exist.");
        } else {
            System.out.println("Account Banking Receive: " + bankingReceive.getAcc().getAccountName());
            if (moneyTransfer > objBanking.getMoney()) {
                System.out.println("The amount you transfer is greater than the account balance..Error.");
            } else {
                boolean confirm = Util.confirm("Are you sure transfer(Y/N): ", "Y/N Only");
                if (confirm == true) {
                    objBanking.setMoney(objBanking.getMoney() - moneyTransfer);
                    bankingReceive.setMoney(bankingReceive.getMoney() + moneyTransfer);
                    System.out.println("Successfully.");
                    System.out.println("Balance: " + objBanking.getMoney());
//                    saveDataBankingToFile();
                } else {
                    System.out.println("You cancel transfer..FAIL.");
                }
            }
        }
    }

    @Override
    public Bank removeAccount(Bank banking) {
        boolean confirm = Util.confirm("Are you sure remove(Y/N): ", "Just Y or N.Input again!");
        if (confirm == true) {
            Bank bank = getBankByID(banking.getAcc().getAccountName());
            list.remove(bank);
            System.out.println("Account Banking " + banking.getAcc().getAccountId() + " has been removed..Successfull.");
//            saveDataBankingToFile();
        } else {
            System.out.println("You cancel removing account's banking.");
        }
        return null;
    }
}
