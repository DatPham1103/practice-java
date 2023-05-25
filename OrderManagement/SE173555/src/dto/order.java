
package dto;

public class order implements Comparable{
     String orderID;
     String customerID;
      String productID;
       String orderQuantity;
        String orderDate;
         String status;

    public order() {
    }

    public order(String orderID, String customerID, String productID, String orderQuantity, String orderDate, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    public order(String orderID) {
        this.orderID = orderID;
    }
     @Override
      public boolean equals(Object obj) {
        order b = (order)obj;
        return this.orderID.equalsIgnoreCase(b.orderID);
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
         
    
          @Override
    public String toString() {
        return orderID+ ","+
               customerID+ ","+
               productID + ","+
                orderQuantity + ","+
                 orderDate + ","+
                 status ;
                  
    }    

    @Override
    public int compareTo(Object o) {
        order ps=  (order)o;
         String []n1 = ps.customerID.split(" ");
        String name1 = n1[n1.length -1];
        String []n2 = this.customerID.split(" ");
        String name2 = n2[n2.length -1];
        
        int compare = name2.compareTo(name1);
        
        if(compare == 0){
            if(n1[0].compareTo(n2[0])>0)
            return -1;
            else
                return 1;
        }else if (compare > 0){
            return -1;
        }else{
            return 1;
        }
      
    }
}
