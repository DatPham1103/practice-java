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

public class BookList extends ArrayList<Book> {

    ArrayList<Publisher> publisherList = new ArrayList<Publisher>();
    ArrayList<Book> sortedList = new ArrayList<>();

    public BookList() {
    }
    
    ArrayList getBookList() {
        return this;
    }

    final String bookFile = "Book.dat";
    final String publisherFile = "Publisher.dat";
    final String ID_FORMAT = "^B\\d{5}";
    final String NAME_FORMAT = "^[a-zA-Z0-9_ ]{5,30}$";

    public boolean checkExistId(String bookId) {
        boolean check = true;
        if (this.isEmpty()) {
            return true;
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getBookID().equals(bookId)) {
                    return !check;
                }
            }
            return check;
        }
    }

    public boolean isExistPublisher(String publisherId) {
        boolean check = true;
        if (publisherList.isEmpty()) {
            return true;
        } else {
            for (int i = 0; i < publisherList.size(); i++) {
                if (publisherList.get(i).getPublisherID().equals(publisherId)) {
                    return !check;
                }
            }
            return check;
        }
    }

    public String publisherName(String publisherID) {
        String result = null;
        for (int i = 0; i < publisherList.size(); i++) {
            if (publisherList.get(i).getPublisherID().equals(publisherID)) {
                result = publisherList.get(i).getPublisherName();
            }
        }
        return result;
    }

    public void createBook() {
        System.out.println("-> Create new Book");
        this.add(bookData());
        System.out.println("-> Book created successfully!");
    }

    public Book bookData() {
        Menu menu = new Menu();
        boolean flag;
        boolean isSameId;
        boolean isExistPublisher;
        String bookID, bookName, status = null, publisherID;
        int quantity, choice;
        double price;
        do {
            bookID = Utils.getStringRegex("Enter book ID: ", ID_FORMAT, "-> Invalid ID format, <B***** with * is a number>");
            isSameId = checkExistId(bookID);
        } while (isSameId == false);
        bookName = Utils.getStringRegex("Enter book name: ", NAME_FORMAT, "-> Book name must be from 5 to 30 characters.");
        price = Utils.getDouble("Enter book price: ", 0, 100000000);
        quantity = Utils.getInt("Enter book quantity: ", 0, 100000);
        do {
            publisherID = Utils.getStringRegex("Enter Publisher ID <Please enter exist Publisher ID!>: ", "^P\\d{5}", "-> Invalid ID format,<P***** with * is a number>");
            isExistPublisher = isExistPublisher(publisherID);
        } while (isExistPublisher);
        menu.addItem("Chooce product status: ");
        menu.addItem("1.Available");
        menu.addItem("2.Not available: ");
        do {
            menu.showMenu();
            choice = menu.getChoice(1, 2);
            switch (choice) {
                case 1:
                    status = "Available";
                    flag = true;
                    break;
                case 2:
                    status = "Not Available";
                    flag = true;
                    break;
                default:
                    System.out.println("Please choose between 1 and 2!");
                    flag = false;
                    break;
            }
        } while (flag == false);
        return new Book(bookID, bookName, price, quantity, publisherID, status);
    }

    public void searchBook() {
        boolean flag = true;
        Menu menu = new Menu();
        int choice;
        menu.addItem("1.Search by Book name");
        menu.addItem("2.Search by Publisher ID");
        do {
            menu.showMenu();
            choice = menu.getChoice(1, 2);
            switch (choice) {
                case 1:
                    searchByName();
                    flag = false;
                    break;
                case 2:
                    searchByPublisher();
                    flag = false;
                    break;
            }
        } while (flag);
    }

    public void searchByName() {
        boolean isExist = false;
        String temp;
        System.out.println("-> Search book by name");
        temp = Utils.getString("Enter book name: ").toLowerCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBookName().toLowerCase().contains(temp)) {
                System.out.println(this.get(i));
                isExist = true;
            }
        }
        if (isExist == false) {
            System.out.println("Have no book!");
        }
    }

    public void searchByPublisher() {
        boolean isExist = false;
        String temp;
        System.out.println("-> Search book by Publisher");
        temp = Utils.getStringRegex("Enter publisher ID: ", "^P\\d{5}", "-> Invalid ID format,<P***** with * is a number>").toLowerCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getPublisherID().toLowerCase().equals(temp)) {
                System.out.println(this.get(i));
                isExist = true;
            }
        }
        if (isExist == false) {
            System.out.println("This Publisher doesn't exist!");
        }
    }

    public void updateBook() {
        Menu menu = new Menu();
        boolean flag = true;
        String choice;
        String idTemp = Utils.getStringRegex("Enter book ID: ", ID_FORMAT, "-> Invalid ID format,<B***** with * is a number>");
        int index = 0;
        boolean isExist = false;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBookID().equals(idTemp)) {
                index = i;
                isExist = true;
            }
        }
        if (isExist == false) {
            System.out.println("Book changed information failed!");
        }
        if (isExist == true) {
            System.out.println("Update " + idTemp + " information");
            this.get(index).setBookID(this.get(index).getBookID());
            this.get(index).setBookName(Utils.getUpdateStringRegex("Enter book name <Book name must be from 5 to 30 characters>: ", NAME_FORMAT, this.get(index).getBookName()));
            this.get(index).setPrice(Utils.getUpdateDouble("Enter book price <0 to 100000000>: ", 0, 100000000, this.get(index).getPrice()));
            this.get(index).setQuantity(Utils.getUpdateInt("Enter book quantity <0 to 100000>: ", 0, 100000, (int) this.get(index).getQuantity()));
            this.get(index).setPublisherID(Utils.getUpdateStringRegex("Enter publisher ID,<P***** with * is a number>: ", "^P\\d{5}", this.get(index).getPublisherID()));
            menu.addItem("Chooce product status: ");
            menu.addItem("1.Available");
            menu.addItem("2.Not available: ");
            do {
                menu.showMenu();
                choice = Utils.getUpdateString("Enter your choice: ", "");
                switch (choice) {
                    case "1":
                        this.get(index).setStatus("Available");
                        flag = false;
                        break;
                    case "2":
                        this.get(index).setStatus("Not available");
                        flag = false;
                        break;
                    case "":
                        this.get(index).setStatus(this.get(index).getStatus());
                        flag = false;
                        break;
                }
            } while (flag);
            System.out.println("Successfully!");
        }
    }

    public void removeBook() {
        Menu menu = new Menu();
        menu.addItem("Are you sure you want to delete this Book?");
        menu.addItem("1.Yes");
        menu.addItem("2.No");
        boolean isExist = false;
        String idTemp;
        int index = 0;
        idTemp = Utils.getStringRegex("Enter Book ID: ", ID_FORMAT, "-> Invalid ID format,<B***** with * is a number>");
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBookID().equals(idTemp)) {
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
            System.out.println("Delete failed! Book's ID doesn't exist!");
        }
    }

    public void getBookFromFile(String fileName) {
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
                String bookID = stk.nextToken();
                String bookName = stk.nextToken();
                double price = Double.parseDouble(stk.nextToken());
                int quantity = Integer.parseInt(stk.nextToken());
                String publisherID = stk.nextToken();
                String status = stk.nextToken();
                Book x = new Book(bookID, bookName, price, quantity, publisherID, status);
                this.add(x);
            }
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getPublisherID() == null) {
                    this.remove(i);
                }
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
                    pw.println(x.getBookID() + "-" + x.getBookName() + "-" + x.getPrice() + "-" + (int) x.getQuantity() + "-" + x.getPublisherID() + "-" + x.getStatus());
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
                String bookID = stk.nextToken();
                String bookName = stk.nextToken();
                double price = Double.parseDouble(stk.nextToken());
                int quantity = (int) Integer.parseInt(stk.nextToken());
                String publisherID = stk.nextToken();
                String status = stk.nextToken();
                Book x = new Book(bookID, bookName, price, quantity, publisherID, status);
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
            System.out.println("There are " + listSize + " book(s) in this file!");
            for (int i = 0; i < listSize; i++) {
                System.out.println("[ Book ID: " + sortedList.get(i).getBookID()
                        + ", Book Name: " + sortedList.get(i).getBookName()
                        + ", Price: " + sortedList.get(i).getPrice()
                        + ", Quantity: " + sortedList.get(i).getQuantity()
                        + ", Subtotal: " + sortedList.get(i).getPrice() * sortedList.get(i).getQuantity()
                        + ", Publisher name: " + publisherName(sortedList.get(i).getPublisherID())
                        + ", Status: " + sortedList.get(i).getStatus() + " ]"
                );
            }
            sortedList.clear();
        }
    }

    public void getPublisherListFromFile(String fileName) {
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
                publisherList.add(x);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void bookFunction() {
        boolean flag = true;
        Menu menu = new Menu();
        int choice;
        menu.addItem("===========================================");
        menu.addItem(" -> Book's Management");
        menu.addItem("1.Create new book");
        menu.addItem("2.Search a book");
        menu.addItem("3.Update a book");
        menu.addItem("4.Delete a book");
        menu.addItem("5.Save list books to file");
        menu.addItem("6.Print the books list from the file");
        menu.addItem("7.Return to main menu");
        menu.addItem("===========================================");
        do {
            getBookFromFile(bookFile);
            getPublisherListFromFile(publisherFile);
            menu.showMenu();
            choice = menu.getChoice(1, 7);
            switch (choice) {
                case 1:
                    createBook();
                    flag = false;
                    break;
                case 2:
                    searchBook();
                    flag = false;
                    break;
                case 3:
                    updateBook();
                    flag = false;
                    break;
                case 4:
                    removeBook();
                    flag = false;
                    break;
                case 5:
                    saveToDatabase(bookFile);
                    flag = false;
                    break;
                case 6:
                    sortAndPrint(bookFile);
                    flag = false;
                    break;
                case 7:
                    return;
            }
        } while (flag);
    }
}
