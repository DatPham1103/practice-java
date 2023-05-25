package util;

import java.util.Scanner;

public class MyUtil {
    public static final Scanner SC = new Scanner(System.in);
    public static boolean validStr(String str, String regEx){
       
       return str.matches(regEx);
    }
    // đọc 1 chuỗi có độ dài min, max
    public static String readString(String message, int minL, int maxL){
        if(minL > maxL){
            int t = minL; minL = maxL; maxL = t; 
        }
        String input= "";
        boolean OK= true;
        do
        {   OK=true;
            System.out.print(message);
            input = SC.nextLine().trim();
            int L = input.length();
            
            if(L < minL || L > maxL) OK= false;
            
        }while(!OK);
        return input;
    }
    public static String readPattern(String message, String pattern){
        String input = "";
        boolean OK;
        do{
             System.out.print(message);
            input = SC.nextLine().trim().toUpperCase();
            OK= validStr(input, pattern);
           if(!OK) System.out.println("false format please retype");
        }while(!OK);
        return input;
    }
}// class Mytool
