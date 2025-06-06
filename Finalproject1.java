/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.reyescompany.finalproject1;

/**
 *
 * @author Administrator
 */
import java.util.*;
import java.util.regex.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Finalproject1 {
    
    static Scanner scn = new Scanner(System.in);
    static int[]index = {1,2,3,4};
    static String[]prodname = {"J1 - Bae Burger", "J2 - Bae Fries", "J3 - Choco SunBae", "J4 - Chicken Bae"};
    static double[]prodprice = {45.00,35.00,40.00,79.00};
    
    public static void displayHome(){
        System.out.println("--------------------------------------------------");
        System.out.println("|                  JOLLIBAE                      |");
        System.out.println("--------------------------------------------------");
        System.out.println(" ");
        System.out.println("Choose Option: ");
        System.out.println("1 - Menu");
        System.out.println("2 - Add Order");
        System.out.println("3 - Show Orders");
        System.out.println("4 - Remove order");
        System.out.println("5 - Payment");

        System.out.print("Enter Option: ");  
    }
    
    public static void displayMenu(){
     

        System.out.println("--------------------------------------------------");
        System.out.println("                  BAE MEALS                       ");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-5s %-30s %-11s%n","ID","Product Name","Price");
        for (int i = 0; i<prodname.length; i++){
            System.out.printf("%-5s %-30s %-11.2f%n",index[i],prodname[i],prodprice[i]);
            
        }
        System.out.println("--------------------------------------------------");
    }
    
    public static void addOrder(ArrayList<String> productName, ArrayList<Integer> productQuantity, ArrayList<Double> productPrice, ArrayList<Double> grandTotal){
                    System.out.println("--------------------------------------------------");
                    System.out.println("                 STARTING ORDER...");
                    System.out.println("         Type 0 to Finish your Order.");
                    System.out.println("--------------------------------------------------");
        while (true){ 
                    System.out.print("Enter Order: ");
                    int orderChoice;
                    if (!scn.hasNextInt()) {
                System.out.println("Error input");
                scn.nextLine();
                continue;
            }
            
            orderChoice = scn.nextInt();
            scn.nextLine();
            
            if (orderChoice == 0) {
                System.out.println("----------------- Done Order ---------------------");
                break;
            } else if (orderChoice >= 1 && orderChoice <= prodname.length) {
                String foodname = prodname[orderChoice-1];
                double foodprice = prodprice[orderChoice-1];
                
                System.out.print("Enter Order Quantity: ");
                if (!scn.hasNextInt()) {
                    System.out.println("------------- Invalid quantity input -------------");
                    scn.nextLine();
                    continue;
                }
                
                int orderQuantity = scn.nextInt();
                scn.nextLine();
                
                if (orderQuantity <= 0) {
                    System.out.println("-------- Quantity must be greater than 0 ---------");
                    continue;
                }
                
                productName.add(foodname);
                productPrice.add(foodprice);
                productQuantity.add(orderQuantity);
                grandTotal.add(foodprice * orderQuantity);
                System.out.println("Added " + orderQuantity + " " + foodname + " to your order.");
            } else {
                System.out.println("Invalid order number. Please enter a number between 1 and " + prodname.length);
            }
        }
    }
    
    public static void showOrders(ArrayList<String> productName, ArrayList<Integer> productQuantity, ArrayList<Double> productPrice, ArrayList<Double> grandTotal){
                System.out.println("--------------------------------------------------");
                System.out.println("                   MY ORDERS...   ");
                System.out.println("--------------------------------------------------");
                if (productName.isEmpty()) {
                System.out.println("----------------- No orders yet! -----------------");
                return;
                }
        
                double total = 0;
                System.out.printf("%-30s %-15s %-15s %-15s%n", "Product Name", "Quantity", "Price", "Subtotal");
                for (int i = 0; i < productName.size(); i++) {
                System.out.printf("%-30s %-15s Php%-14.2f Php%-5.2f%n",productName.get(i),productQuantity.get(i),productPrice.get(i),grandTotal.get(i));
                total += grandTotal.get(i);
                }
        
                System.out.println("--------------------------------------------------");
                System.out.printf("Total: Php%.2f%n", total);
                System.out.println("--------------------------------------------------");
    }
    
    public static void  productRemover(ArrayList<String> productName, ArrayList<Integer> productQuantity, ArrayList<Double> productPrice, ArrayList<Double> grandTotal){
                    System.out.print("Enter Number of the order you want to Remove: ");
                    int removeOrder = scn.nextInt();
                    scn.nextLine();
                    
                    if (removeOrder < 1 || removeOrder>prodname.length){
                        System.out.println("------------------ Error Input -------------------");
                        return;
                    }
                    
                    String prodRemove = prodname[removeOrder-1];
                    int foundIn = -1;
                    
                    for (int i = 0; i <productName.size(); i++){
                        if (productName.get(i).equalsIgnoreCase(prodRemove)){
                            foundIn = i;
                            break;
                        }
                    }
                    if (foundIn != -1){
                        System.out.println("Product Found " +productName.get(foundIn));
                        productName.remove(foundIn);
                        productQuantity.remove(foundIn);
                        productPrice.remove(foundIn);
                        grandTotal.remove(foundIn);
                    } else {
                        System.out.println("Product not found");
                    }
    }
    
    public static void displayreceipt(ArrayList<String> productName, ArrayList<Integer> productQuantity, ArrayList<Double> productPrice, ArrayList<Double> grandTotal, String currentUser){
                    double totalAmount = 0;
                    for (Double amount : grandTotal){
                    totalAmount += amount;
                    } 
                    
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("                                ORDER SUMMARY                     ");
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.printf("%-30s %-15s %-15s %-15s%n", "Product Name", "Quantity", "Price", "Subtotal");
    
                    for (int i = 0; i < productName.size(); i++) {
                    System.out.printf("%-30s %-15s Php%-14.2f Php%-5.2f%n", productName.get(i), productQuantity.get(i), productPrice.get(i), grandTotal.get(i));
                    }
    
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.printf("Total Amount Due: Php%.2f%n", totalAmount);
                    System.out.println("-------------------------------------------------------------------------------");
                
                    System.out.print("Enter payment amount: Php");
                    double paymentAmount;
                    
                    while (true) {
                    if (!scn.hasNextDouble()) {
                    System.out.println("Invalid input. Please enter a valid amount.");
                    scn.nextLine();
                    System.out.print("Enter payment amount: Php");
                    continue;
                    }
                    paymentAmount = scn.nextDouble();
                    scn.nextLine();
        
                    if (paymentAmount < totalAmount) {
                    System.out.println("Insufficient payment. Please enter an amount equal to or greater than Php" + totalAmount);
                    System.out.print("Enter payment amount: Php");
                   }else {
                    break;
                   }
                   }
                   double change = paymentAmount - totalAmount;
                   
                   System.out.println("\n\n");
                   System.out.println("-------------------------------------------------------------------------------");
                   System.out.println("|                                   JOLLIBAE                                  |");
                   System.out.println("|                               OFFICIAL RECEIPT                              |");
                   System.out.println("-------------------------------------------------------------------------------");
                   System.out.println("                    The Best ang Saya pagkasama ang BAErkada                   ");
                   System.out.println("-------------------------------------------------------------------------------");
                   System.out.printf("%-30s %-15s %-15s %-15s%n", "Product Name", "Quantity", "Price", "Subtotal");
    
                   for (int i = 0; i < productName.size(); i++) {
                   System.out.printf("%-30s %-15s Php%-14.2f Php%-5.2f%n", 
                   productName.get(i), productQuantity.get(i), productPrice.get(i), grandTotal.get(i));
                   }
    
                   System.out.println("-------------------------------------------------------------------------------");
                   System.out.printf("Total Amount:               Php%.2f%n", totalAmount);
                   System.out.printf("Cash Payment:               Php%.2f%n", paymentAmount);
                   System.out.printf("Change:                     Php%.2f%n", change);
                   System.out.println("-------------------------------------------------------------------------------");
                   System.out.println("|                          THANK YOU FOR ORDERING!                            |");
                   System.out.println("|                            PLEASE COME AGAIN!                               |");
                   System.out.println("-------------------------------------------------------------------------------");
    
                   try (FileWriter writer = new FileWriter("transactions.txt", true)) {

                writer.write("Date/Time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
                writer.write("Cashier: " + currentUser + "\n");
                writer.write("Items Purchased: \n");

                for (int i = 0; i <productName.size(); i++) {
                writer.write(String.format(" %s Qty: %d | Price: Php%.2f | Subtotal: Php%.2f\n",productName.get(i),productQuantity.get(i), productPrice.get(i), grandTotal.get(i)));
                }
                writer.write(String.format("Total Amount: Php%.2f\n", totalAmount));
                writer.write("---\n");
                } catch (IOException e) {
                System.out.println("Error writing transaction to file."); }

                   
                   productName.clear();
                   productQuantity.clear();
                   productPrice.clear();
                   grandTotal.clear();
    
                   System.out.println("\n---------------Payment completed successfully!---------------");
    }
    
    public static void main(String[] args) {
        ArrayList<String> productName = new ArrayList<String>();
        ArrayList<String> username = new ArrayList<String>();
        ArrayList<String> password = new ArrayList<String>();
        ArrayList<Integer> productQuantity = new ArrayList<Integer>();
        ArrayList<Double> productPrice = new ArrayList<Double>();
        ArrayList<Double> grandTotal = new ArrayList<Double>();
       
        System.out.println("--- Welcome to JOLLIBAE---");
        
        String currentUser = "";
        
        while(true){
            System.out.println("1 - Sign up");
            System.out.println("2 - Log in ");
            System.out.print("Choose Option: ");
            int userAcc = scn.nextInt();
            scn.nextLine();
            
         switch(userAcc){
             case 1:
                 String UserName;
                 while (true){
                     System.out.print("Enter Username (5-15 letters/numbers): ");
                     UserName = scn.nextLine();
                    if (Pattern.matches("^[a-zA-Z\\d]{5,15}$", UserName)){
                        break;
                    } else {
                        System.out.println("Error. Does not meet requirements.");
                    }
                 }
                 String PassWord;
                 while(true){
                     System.out.print("Enter Password (8-20 chacters, atleast 1 Uppercase Letter and 1 Number): ");
                     PassWord = scn.nextLine();
                    if (Pattern.matches("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d\\W]{8,20}$", PassWord)){
                        break;
                    } else {
                        System.out.println("Error. Does not meet requirements.");
                    }
                 }
                 
                 username.add(UserName); 
                 password.add(PassWord);
                 
                 System.out.println("--- Sign up successful!!! ---");
                 
             case 2:
                 while (true){
                     System.out.println("-----------------------");
                     System.out.println("        LOG IN");
                     System.out.println("-----------------------");
                     System.out.print("Enter Username: ");
                     String Uname = scn.nextLine();
                     System.out.print("Enter Password: ");
                     String Pword = scn.nextLine();
                     
                boolean meron = false;
                for (int i = 0; i < username.size(); i++){
                    if (username.get(i).equals(Uname)&& password.get(i).equals(Pword)){
                        meron = true;
                        currentUser = Uname;
                        break;
                    }
                }
                if (meron){
                    System.out.println("--- Log In Successful! ---");
                    break;
                } else {
                    System.out.println("Invalid Username or Password. Please Try again.");
                }
                
                }   
                
             }
        
        String choice;
        while(true){
            
        displayHome();
        int option = scn.nextInt();
        scn.nextLine();
        
        switch (option){
            case 1:
                do {
                    displayMenu();
                    System.out.print("Do you want to go back in options? (y/n): ");
                    choice = scn.nextLine();
                   }while (choice.equals("n"));
                    break;
            case 2:
                do {
                    displayMenu();
                    addOrder(productName, productQuantity, productPrice, grandTotal);
                    System.out.print("Do you want to go back in options? (y/n): ");
                    choice = scn.nextLine();
                   }while (choice.equals("n"));
                    break;
            case 3:
                do {
                    showOrders(productName, productQuantity, productPrice, grandTotal);
                    System.out.print("Do you want to go back in options? (y/n): ");
                    choice = scn.nextLine();
                   }while (choice.equals("n"));
                    break;
            case 4:   
                do {
                    displayMenu();
                    showOrders(productName, productQuantity, productPrice, grandTotal);
                    productRemover(productName, productQuantity, productPrice, grandTotal);
                    System.out.print("Do you want to go back in options? (y/n): ");
                    choice = scn.nextLine();
                   }while (choice.equals("n"));
                    break; 
            case 5:
                do {
                    displayreceipt(productName, productQuantity, productPrice, grandTotal, currentUser);
                    System.out.print("Do you want to have another Transaction? (y/n): ");
                    choice = scn.nextLine();
                   }while (choice.equals("n"));
                    break; 
            }
        }
    } 
}
        
}