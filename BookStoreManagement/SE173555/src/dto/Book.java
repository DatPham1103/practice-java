package dto;

public class Book implements Comparable<Book> {

    private String bookID;
    private String bookName;
    private double price;
    private int quantity;
    private String publisherID;
    private String status;

    public Book() {
    }

    public Book(String bookID, String bookName,
            double price, int quantity, String publisherID,
            String status) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
        this.publisherID = publisherID;
        this.status = status;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[ " + bookID + "-" + bookName + "-" + price + "-" + quantity + "-" + status + " ]";
    }

    @Override
    public int compareTo(Book b) {
        Book a = (Book) b;
        int x = this.quantity - a.quantity;
        double y = this.price - a.price;
        if (x == 0) {
            return (int) y;
        }
        return -x;
    }
}
