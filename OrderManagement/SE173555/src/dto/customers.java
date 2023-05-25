
package dto;

public class customers {
//    customerID, customerName, customerAddress, customerPhone
         String   customerID; 
         String   customerName; 
         String   customerAddress; 
         String   customerPhone; 

    public customers(String customerID, String customerName, String customerAddress, String customerPhone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }

    public customers() {
    }

    public customers(String customerID) {
        this.customerID = customerID;
    }
     @Override
    public boolean equals(Object obj) {
        customers p= (customers)obj;
       return this.customerID.equalsIgnoreCase(p.customerID);
    }
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
         //    customerID, customerName, customerAddress, customerPhone

          @Override
    public String toString() {
        return customerID+ ","+
               customerName+ ","+
               customerAddress + ","+
               
                customerPhone  ;         
    }    
        
}
