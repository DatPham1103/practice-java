/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author s0ra
 */
public interface I_List {
    void createAccount();
    Bank logIn();
    void withdrawMoney(Bank accountBank);
    void depositMoney(Bank accountBank);
    void transferMoney(Bank accountBank);
    Bank removeAccount(Bank banking);
    void loadDataBankingFromFile();
    void saveDataBankingToFile(String fileName);
    void saveDataAccountToFile(String file);
}
