
package controller;

import util.MyUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import dto.customers;
import dto.I_ListCus;

public class cusList extends ArrayList<customers> implements I_ListCus{
    public cusList() {
    }
     public static String fName="customer.dat";
    
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
                  //    customerID, customerName, customerAddress, customerPhone
                StringTokenizer stk = new StringTokenizer(line,",");
                String customerID = stk.nextToken().trim().toUpperCase();
                //System.out.println(ID);
                String customerName = stk.nextToken().trim().toUpperCase();
                //System.out.println(name);
                String customerAddress = stk.nextToken().trim().toUpperCase();
                String customerPhone = stk.nextToken().trim().toUpperCase();            
                customers p = new customers( customerID, customerName, customerAddress, customerPhone);
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
         for (customers p: this) System.out.println(p);
     }
        
    @Override
        public void search(){
        String ID;
        System.out.println("ID of searched customer: ");
        ID = MyUtil.SC.nextLine().trim().toUpperCase();
        int pos = this.indexOf(new customers(ID));
        if(pos < 0) System.out.println("Not Found!");
        
       else System.out.println("Found: " + this.get(pos));      
    
    }    
//        customerID, customerName, customerAddress, customerPhone
    @Override
         public void updateCustomers(){
       String customerID;
       String customerName;
       String customerAddress;
       String customerPhone;
       System.out.print("ID of customer to update: ");
        customerID = MyUtil.SC.nextLine().trim().toUpperCase();
        int pos = this.indexOf(new customers(customerID));
        if(pos < 0) System.out.println("Not Found!");
        else {
             customers p = this.get(pos);
            
             customerName = MyUtil.readString("customerName: ", 5, 30);
            System.out.print("customerAddress: ");
             customerAddress = MyUtil.SC.nextLine();           
             customerPhone = MyUtil.readPattern("customerPhone: ", "[\\d]{10,12}");
             p.setCustomerName(customerName);            
             p.setCustomerAddress(customerAddress);
             p.setCustomerPhone(customerPhone);
             System.out.println("Updated.");
         }
     }
         
    @Override
          public void writeToFile() throws Exception{
        if(this.isEmpty()){
            System.out.println("Empty list!");
        }else{
            PrintWriter pw = new PrintWriter(fName);
            for(customers p: this){
                pw.println(p);              
            }
            pw.close();
            System.out.println("Writing file: DONE.");
        }
    }
    @Override
          public void addCustomer(){
        Scanner sc = new Scanner(System.in);
        String customerID, customerName, customerAddress, customerPhone ;
       
        System.out.println("ID of new customer:");
        int pos; // vị trí tìm kiếm 
        do{
            customerID= MyUtil.readPattern("customerID:","C[\\d]{3}");
           
            pos = this.indexOf(new customers(customerID));
           
            if (pos >= 0) System.out.println("bookID is existed!");
        }while( pos >= 0);
        
         customerName = MyUtil.readString("customerName: ", 5, 30);
         
        customerAddress=MyUtil.readString("customerAddress: ", 5, 70);
         customerPhone= MyUtil.readPattern("customerPhone: ", "[\\d]{10,12}");
        
         customers b = new customers(customerID, customerName, customerAddress, customerPhone );
         //bookID, bookName, price, quantity, publisherId, status
         this.add(b);
         
    }
//         
//         public static void main(String[] args) throws Exception {
//        cusList test= new cusList();
//        test.readFromFile();
//        //test.print();
//        //test.search();
//        test.updateCustomers();
//        test.writeToFile();
//        test.readFromFile();
//        test.print();
//    }
}
