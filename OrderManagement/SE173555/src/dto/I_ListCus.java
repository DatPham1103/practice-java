/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Dat Pham
 */
public interface I_ListCus {
    void readFromFile();
    void print();
    void search();
    void updateCustomers();
    void writeToFile() throws Exception;
    void addCustomer();
}
