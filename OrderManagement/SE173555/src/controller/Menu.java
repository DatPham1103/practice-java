
package controller;

import dto.I_Menu;
import util.MyUtil;
import java.util.ArrayList;

public class Menu extends ArrayList<String> implements I_Menu{
    public Menu() {
    }   
    @Override
      public int getUserChoice(){
          int choice ;
    int n=this.size();
    for(int i=1;i<=n;i++){
            System.out.println(i+"-"+this.get(i-1));         
    }
     //System.out.println((n+1)+ ": quit");
            System.out.println("______________");
            System.out.println("Choose (1.."+(n+1)+"):");
            choice =Integer.parseInt(MyUtil.SC.nextLine());
    return choice;
}
}
