package controllers;

import java.util.ArrayList;
import utils.Utils;

public class Menu extends ArrayList<String> {
    
    public Menu() {
        super();
    }

    public void addItem(String content) {
        this.add(content);
    }

    public int getChoice(int min, int max) {
        int choice;
        choice = Utils.getInt("Enter your choice <" + min + " to " + max + ">: ", min, max);
        return choice;
    }

    public void showMenu() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        }
    }
}
