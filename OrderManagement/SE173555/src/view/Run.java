
package view;

import controller.Menu;
import util.MyUtil;
import java.util.Scanner;
import controller.cusList;
import dto.customers;
import dto.order;
import controller.orderList;
import controller.proList;
import dto.products;

public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
         Menu nmu = new Menu();
        nmu.add("Products: print all");
        nmu.add("Customers: print all");
        nmu.add("Customers: search");
        nmu.add("Customers: add");
        nmu.add("Customers: update");
        nmu.add("Customers: save to file");
  
        nmu.add("order: print all");
        nmu.add("order: print all pending");
        nmu.add("order: add");
        nmu.add("order: Update");
        nmu.add("order: delete");
        nmu.add("order: Save to file");
        nmu.add("order: read file");

        nmu.add("Quit");
        
        int nChoice = nmu.size();
       
        proList pList= new proList();
        pList.readFromFile();
       
        cusList cList= new cusList();
        cList.readFromFile();
        
        orderList oList=new orderList();
        oList.readFromFile();
        
        
        int userChoice,posorderID;
        
        boolean pChange = false;
        boolean oChange = false;
        boolean cChange = false;
          String orderID,
                     customerID,
                     productID = "",
                     orderDate,
                     status,
                     proIDtmp,
                     orderQuantity="";
           int pos,
                     tmp = 0; 
            Scanner sc = new Scanner(System.in);
        do{
            System.out.println("\n ---store---");
            userChoice = nmu.getUserChoice();    
            switch(userChoice){
                case 1: 
                    pList.print();
                    break;
                case 2:
                    cList.print();
                    cChange = true;
                    break;
                case 3:
                    cList.search();
                    cChange = true;
                    break;
                case 4:
                    cList.addCustomer();
                    cChange = true;
                    break;
                case 5:
                     cList.updateCustomers();
                     cChange = true;
                     break;
                 case 6:
                     cList.writeToFile();
                    cChange = false;
                    break;
                case 7:
                    oList.print();
                    oChange = true;
                    break;
                case 8:
                  oList.printAllAscendingName();
                    oChange = true;
                    break;
                case 9:
//            orderID, customerID, productID, orderQuantity, orderDate, status
                   
                   // vị trí tìm kiếm 
                    do {
                        orderID = MyUtil.readPattern("ID of order to add :", "D[\\d]{3}");

                        posorderID = oList.indexOf(new order(orderID));

                        if (posorderID >= 0) {
                            System.out.println("order is existed!");
                        }
                    } while (posorderID >= 0);
                    do {
                       customerID = MyUtil.readPattern("customerID: ", "C[\\d]{3}");

                        pos = cList.indexOf(new customers(customerID));

                        if (pos < 0) {
                            System.out.println("customerID is not exist!");
                        }
                    } while (pos < 0);
                    
                    pList.print();

                    do {
                      
                        proIDtmp = MyUtil.readPattern("choosen productID :", "P[\\d]{3}");

                        pos = pList.indexOf(new products(proIDtmp));

                        if (pos < 0) {
                            System.out.println("product is not existed!");
                        } else {
                            productID=productID.concat(proIDtmp + " ");
                            System.out.print("quantity :");
                           
                            orderQuantity = orderQuantity.concat(sc.nextLine()+" ");
                        }
                        System.out.print("enter 1 to exit choosen product, enter 2 to continue order :");
                        
                        tmp = Integer.parseInt(sc.nextLine());
                    } while (tmp != 1);

                    

                    orderDate = MyUtil.readString("date :", 7, 10);

                    status = "FALSE ";

                    order b = new order(orderID, customerID, productID, orderQuantity, orderDate, status);

                    oList.add(b);

                    System.out.println("add succesful");
                oChange = true;
                break;
                case 10:
                  // order update
//                  String orderID,customerID,productID = "",orderDate,status,proIDtmp,orderQuantity;
                    productID="";
                    
                    do {
                        orderID = MyUtil.readPattern("ID of order to update:", "D[\\d]{3}");

                        posorderID = oList.indexOf(new order(orderID));

                        if (posorderID <0) {
                            System.out.println("order is not existed!");
                        }
                    } while (posorderID <0);
                    do {
                       customerID = MyUtil.readPattern("customerID: ", "C[\\d]{3}");

                        pos = cList.indexOf(new customers(customerID));

                        if (pos < 0) {
                            System.out.println("customerID is not exist!");
                        }else 
                            oList.get(posorderID).setCustomerID(customerID);
                    } while (pos < 0);
                    //oList.get(pos).setCustomerID(MyUtil.readPattern("customerID: ", "C[\\d]{3}"));
                    pList.print();
                    System.out.println("update product");
                    do {
                        
                        
                        proIDtmp = MyUtil.readPattern("choosen productID :", "P[\\d]{3}");

                        pos = pList.indexOf(new products(proIDtmp));

                        if (pos < 0) {
                            System.out.println("product is not existed!");
                        } else {
                             productID=productID.concat(proIDtmp + " ");
                        }
                        System.out.print("enter 1 to exit choosen product, enter 2 to continue order :");
                        tmp = Integer.parseInt(sc.nextLine());
                    } while (tmp != 1);
                    oList.get(posorderID).setProductID(productID);
                    oList.get(posorderID).setOrderDate(MyUtil.readString("date :", 7, 10));
                    System.out.println("enter 1 to update status true,2 is false");
                    tmp=Integer.parseInt(sc.nextLine());
                    if (tmp==1) oList.get(posorderID).setStatus("TRUE");
                    else oList.get(posorderID).setStatus("FALSE");
                    System.out.println("updated");
                    oChange = true;
                    break;
                case 11:
                 
                    do {
                        orderID = MyUtil.readPattern("ID of order to delete:", "D[\\d]{3}");

                        pos = oList.indexOf(new order(orderID));

                        if (pos <0) {
                            System.out.println("order is not existed!");
                        }
                    } while (pos <0);
                    oList.remove(pos);
                     System.out.println("removed");
                    oChange= true;
                    break;
                case 12: 
                   oList.writeToFile();
                    oChange = true;
                    break;
                case 13:
                    oList.readFromFile();
                    break;
                case 14:
                    userChoice=-5;
                    break;             
            }
        }while(userChoice >0 && userChoice <= nChoice);
        // hỏi người dùng lưu file khi có sự cố thay đổi
        String response;
        if(pChange == true || cChange == true|| oChange==true){
            System.out.println("Data changed. Save files ?- Y/N:");
            response= MyUtil.SC.nextLine().trim().toUpperCase();
            if(response.startsWith("Y")){
                    if(pChange == true){
                        pList.writeToFile();
                    } 
                    if(cChange == true){
                        cList.writeToFile();
                    }
                     if(oChange == true){
                        oList.writeToFile();
                    }
            }
        }
    }
    }
    

