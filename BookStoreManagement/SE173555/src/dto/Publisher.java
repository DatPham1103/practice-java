package dto;

public class Publisher implements Comparable<Publisher> {
    
    private String publisherID;
    private String publisherName;
    private String publisherPhone;

    public Publisher() {
    }

    public Publisher(String publisherID, String publisherName, 
            String publisherPhone) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
        this.publisherPhone = publisherPhone;
    }
    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }
    
    @Override
    public String toString() {
        return "[ Publisher ID: " + publisherID + ", Publisher name: " + publisherName + ", Publisher phone: " 
                + publisherPhone + " ]";
    }
    
    @Override
    public int compareTo(Publisher o) {
        return this.getPublisherName().compareTo(o.getPublisherName());
    }

}
