
package view;

import controllers.BookList;
import controllers.PublisherList;
import controllers.Menu;
import java.io.IOException;

public class BookStoreManagement {

    public static void main(String[] args) throws IOException {
        String bookFile = "Book.dat";
        String publisherFile = "Publisher.dat";
        Menu menu = new Menu();
        BookList blist = new BookList();
        PublisherList plist = new PublisherList();
        blist.getBookFromFile(bookFile);
        plist.getPublisherFromFile(publisherFile);
        menu.addItem("");
        menu.addItem("===================== Book Store Management =====================");
        menu.addItem("|           1.Publisher's Management                            |");
        menu.addItem("|           2.Book's Management                                 |");
        menu.addItem("|           3.Exit                                              |");
        menu.addItem("=================================================================");
        menu.addItem("");
        int choice;
        boolean flag = false;
        do {
            menu.showMenu();
            choice = menu.getChoice(1, 3);
            switch (choice) {
                case 1:
                    plist.publisherFunction();
                    break;
                case 2:
                    blist.bookFunction();
                    break;
                case 3:
                    System.out.println("Thank you");
                    System.exit(0);
                    break;
            }
        } while (choice >= 1 && choice <= 3 && !flag);
    }
}
