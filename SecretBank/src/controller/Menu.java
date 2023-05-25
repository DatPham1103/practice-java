/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.I_Menu;
import java.util.ArrayList;
import utils.Util;

/**
 *
 * @author s0ra
 */
public class Menu extends ArrayList<String> implements I_Menu {

    ArrayList<String> listMenu = new ArrayList();

    public Menu() {
        super();
    }

    @Override
    public void addItem(String s) {
        listMenu.add(s);
    }

    @Override
    public int getChoice(int min, int max) {
        int choice;
        choice = Util.getInt("Enter your choice: ", min, max);
        return choice;
    }

    @Override
    public void showMenu() {
        for (int i = 0; i < listMenu.size(); i++) {
            System.out.println(listMenu.get(i));
        }
    }
}
