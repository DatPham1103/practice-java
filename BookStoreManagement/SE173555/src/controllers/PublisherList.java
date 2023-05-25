package controllers;

import dto.Book;
import dto.Publisher;
import utils.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class PublisherList extends ArrayList<Publisher> {

    final String publisherFile = "Publisher.dat";
    final String ID_FORMAT = "^P\\d{5}";
    final String NAME_FORMAT = "[a-z_A-Z_0-9- ]{5,30}$";
    final String PHONE_FORMAT = "^(\\d{10,12})$";

    ArrayList<Publisher> sortedList = new ArrayList<>();

    public PublisherList getList() {
        return this;
    }

    public boolean checkExistId(String publisherId) {
        boolean check = true;
        if (this.isEmpty()) {
            return true;
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getPublisherID().equals(publisherId)) {
                    System.out.println("-> This ID has already been used!");
                    return !check;
                }
            }
            return check;
        }
    }

    public void createPublisher() {
        System.out.println("-> Create new Publisher");
        this.add(publisherData());
        System.out.println("-> Publisher created successfully!");
    }

    public Publisher publisherData() {
        boolean isSameId;
        String publisherID, publisherName, publisherPhone;
        do {
            publisherID = Utils.getStringRegex("Enter publisher ID: ", ID_FORMAT, "-> Invalid ID format,<P***** with * is a number>");
            isSameId = checkExistId(publisherID);
        } while (isSameId == false);
        publisherName = Utils.getStringRegex("Enter publisher name: ", NAME_FORMAT, "-> Publisher name must be from 5 to 30 characters.");
        publisherPhone = Utils.getStringRegex("Enter publisher phone: ", PHONE_FORMAT, "-> Publisher phone must be from 10 to 12 digits.");
        return new Publisher(publisherID, publisherName, publisherPhone);
    }

    public void removePublisher() {
        Menu menu = new Menu();
        menu.addItem("Are you sure you want to delete this Pulisher?");
        menu.addItem("1.Yes");
        menu.addItem("2.No");
        boolean isExist = false;
        String idTemp;
        int index = 0;
        idTemp = Utils.getStringRegex("Enter publisher ID: ", ID_FORMAT, "-> Invalid ID format,<P***** with * is a number>");
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getPublisherID().equals(idTemp)) {
                isExist = true;
                index = i;
            }
        }
        if (isExist == true) {
            menu.showMenu();
            int choice = Utils.getInt("Enter your choice <1 or 2>: ", 1, 2);
            switch (choice) {
                case 1:
                    this.remove(index);
                    System.out.println("Delete " + idTemp + " successfully!!");
                    break;
                case 2:
                    System.out.println("Delete " + idTemp + " failed!");
                    break;
            }
        } else {
            System.out.println("Delete failed! Publisher's ID doesn't exist!");
        }
    }

    public void getPublisherFromFile(String fileName) {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                System.out.println("File not found!");
                System.exit(0);
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, "-");
                String publisherID = stk.nextToken();
                String publisherName = stk.nextToken();
                String publisherPhone = stk.nextToken();
                Publisher x = new Publisher(publisherID, publisherName, publisherPhone);
                this.add(x);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void saveToDatabase(String fileName) {
        try {
            File f = new File(fileName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            this.forEach((x) -> {
                pw.println(x.getPublisherID() + "-" + x.getPublisherName() + "-" + x.getPublisherPhone());
            });
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void sortAndPrint(String fileName) {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                System.out.println("File not found!");
                System.exit(0);
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, "-");
                String publisherID = stk.nextToken();
                String publisherName = stk.nextToken();
                String publisherPhone = stk.nextToken();
                Publisher x = new Publisher(publisherID, publisherName, publisherPhone);
                sortedList.add(x);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        int listSize = sortedList.size();
        if (listSize == 0) {
            System.out.println("Empty list!");
        } else {
            Collections.sort(sortedList);
            System.out.println("There are " + listSize + " Publisher(s) in this file!");
            for (int i = 0; i < listSize; i++) {
                System.out.println(sortedList.get(i));
            }
            sortedList.clear();
        }
    }

    public void publisherFunction() {
        boolean flag = true;
        Menu menu = new Menu();
        int choice;
        menu.addItem("===========================================");
        menu.addItem(" -> Publisher's Management");
        menu.addItem("1.Create new Publisher");
        menu.addItem("2.Delete the Publisher");
        menu.addItem("3.Save list Publishers to file");
        menu.addItem("4.Print the Publishers list from the file");
        menu.addItem("5.Return to main menu");
        menu.addItem("===========================================");
        do {
            menu.showMenu();
            choice = menu.getChoice(1, 5);
            switch (choice) {
                case 1:
                    createPublisher();
                    flag = false;
                    break;
                case 2:
                    removePublisher();
                    flag = false;
                    break;
                case 3:
                    saveToDatabase(publisherFile);
                    flag = false;
                    break;
                case 4:
                    sortAndPrint(publisherFile);
                    flag = false;
                    break;
                case 5:
                    return;
            }
        } while (flag);
    }
}
