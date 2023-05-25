
package dto;

public class products {

    String productID;
    String productName;
    String unit;
    String origin;
    Double price;

    public products(String productID, String productName, String unit, String origin, Double price) {
        this.productID = productID;
        this.productName = productName;
        this.unit = unit;
        this.origin = origin;
        this.price = price;
    }

    public products(String productID) {
        this.productID = productID;
    }
    
    @Override
    public boolean equals(Object obj) {
        products b = (products)obj;
        return this.productID.equalsIgnoreCase(b.productID);
    }
    public String getProductID() {
        return productID;
    }

    
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
      //    productID, productName, unit, origin, price        
    @Override
    public String toString() {
        return productID+ ","+
               productName+ ","+
               unit + ","+
                origin + ","+
                price  ;         
    }    
}
