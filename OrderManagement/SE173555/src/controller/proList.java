
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import dto.products;
import dto.I_ListPro;

public class proList extends ArrayList<products> implements I_ListPro{
    public static String fName="products.dat";

    public proList() {
    }
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
                      //    productID, productName, unit, origin, price        
                StringTokenizer stk = new StringTokenizer(line,",");
                String productID = stk.nextToken().trim().toUpperCase();
                //System.out.println(ID);
                String productName = stk.nextToken().trim().toUpperCase();
                //System.out.println(name);
                String unit = stk.nextToken().trim().toUpperCase();
                String origin = stk.nextToken().trim().toUpperCase();
                Double price = Double.parseDouble(stk.nextToken().trim());
               
                products p = new products( productID, productName, unit, origin, price);
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
         for (products p: this) System.out.println(p);
     }
    @Override
  public void writeToFile() throws Exception{
        if(this.isEmpty()){
            System.out.println("Empty list!");
        }else{
            PrintWriter pw = new PrintWriter(fName);
            for(products p: this){
                pw.println(p);              
            }
            pw.close();
            System.out.println("Writing file: DONE.");
        }
    }

// test
//     public static void main(String[] args) throws Exception {
//        proList test= new proList();
//        test.readFromFile();
//        test.print();
//    }
}