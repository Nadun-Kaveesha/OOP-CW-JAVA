package com.iit.oop.cw;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();
        Vendor vendor = new Vendor();
        Customer customer = new Customer();
        TicketPool ticketPool = new TicketPool();


        // Set the configuration
//        System.out.print("Enter the total number of tickets: ");
//        config.setTotalTickets(scanner.nextInt());
//        System.out.print("Enter the ticket release rate: ");
//        config.setTicketReleaseRate(scanner.nextInt());
//        System.out.print("Enter the customer retrieval rate: ");
//        config.setCustomerRetrievalRate(scanner.nextInt());
//        System.out.print("Enter the maximum ticket capacity: ");
//        config.setMaxTicketCapacity(scanner.nextInt());

        //Main Menu
        System.out.println("\n"+" ".repeat(40)+"*".repeat(5)+" Main Menu "+"*".repeat(5));
        System.out.println("1. Press [1] to Buy Tickets");
        System.out.println("2. Press [2] to Sell Tickets");
        System.out.print("\nPlease Enter Your Choice : ");
        int userChoice = scanner.nextInt();
        if (userChoice == 1 ){
            System.out.print("Please Enter the amount to of Tickets you want to Buy : ");
            ticketPool.removeTickets(scanner.nextInt());
        }else if(userChoice == 2 ){
            System.out.print("Please Enter the amount to of Tickets you want to Sell : ");
            ticketPool.addTickets(scanner.nextInt());

        }






    }
}
