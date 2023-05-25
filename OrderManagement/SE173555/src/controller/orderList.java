
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import dto.order;
import dto.I_ListOrder;

public class orderList extends ArrayList<order> implements I_ListOrder{
    public static String fName="order.dat";
    @Override
    public void readFromFile(){
        File f = new File(fName);
        if(!f.exists()){
            System.out.println("File is not existed!");
            System.exit(0);
        }
        try{
            FileReader fr = new  FileReader(f); 
            BufferedReader bf = new BufferedReader(fr); 
        
            String line;
            while((line = bf.readLine()) != null){
                  // orderID, customerID, productID, orderQuantity, orderDate, status
                StringTokenizer stk = new StringTokenizer(line,",");
                String orderID = stk.nextToken().trim().toUpperCase();
                //System.out.println(ID);
                String customerID = stk.nextToken().trim().toUpperCase();
                //System.out.println(name);
                String productID = stk.nextToken().trim().toUpperCase();
                String orderQuantity = stk.nextToken().trim().toUpperCase();
                String orderDate = stk.nextToken().trim().toUpperCase();
                String status = stk.nextToken().trim().toUpperCase();
                order p = new order( orderID, customerID, productID, orderQuantity, orderDate, status);
                this.add(p);
            }
            bf.close();
            fr.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    @Override
      public void print(){
         for (order p: this) System.out.println(p);
     }
      
    @Override
         public void printAllAscendingName()
     {
         Collections.sort(this);
         System.out.println("---------------------");
         for (order p: this) System.out.println(p);
     }
         
    @Override
          public void writeToFile() throws Exception{
        if(this.isEmpty()){
            System.out.println("Empty list!");
        }else{
            PrintWriter pw = new PrintWriter(fName);
            for(order p: this){
                pw.println(p);              
            }
            pw.close();
            System.out.println("Writing file: DONE.");
        }
    }
//         public static void main(String[] args) throws Exception {
//        orderList test= new  orderList();
//        test.readFromFile();
//        test.printAllAscendingName();
//       
//        test.print();
//    }
}
