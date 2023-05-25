/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author s0ra
 */
public class Util {

    public static String getString(String message) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(message);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("This fill cannot be empty!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getStringRegex(String message, String pattern) {
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message);
            result = sc.nextLine();
            if (result.length() == 0) {
                System.out.println("This fill cannot be empty");
            } else if (!result.matches(pattern)) {
                System.out.println("Input wrong format, please try it again!");
            } else {
                check = false;
            }
        } while (check == true);
        return result;
    }

    public static int getInt(String message, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(message);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Please input only number!");
            }
            if(number>max){
                System.out.println("Goodbye!!");
                System.exit(0);
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static double getDouble(String inputMsg, String errorMsg, double min) {
        double n;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < min) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static boolean confirm(String message, String error){
        String regexConfirm = getStringRegex(message, "^[Y|y|n|N]$");
        if(regexConfirm.equalsIgnoreCase("Y"))
            return true;
        return false;
    }
}
