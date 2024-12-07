package com.iit.oop.cw;

import java.util.Scanner;

public class App {
    // Centralized object instantiation
    private static Scanner scanner = new Scanner(System.in);
    private static Configuration config = new Configuration();
    private static TicketPool ticketPool = new TicketPool();
    private static Vendor vendor = new Vendor(ticketPool);
    private static Customer customer = new Customer(ticketPool);


    public static void main(String[] args) {
        setConfiguration();
        int userChoice = showMenu();
        mainFunction(userChoice);
    }

    // Function to set up the configuration
    public static void setConfiguration() {
        // Set the configuration with validation
        while (true) {
            try {
                System.out.print("Enter the total number of tickets: ");
                int totalTickets = Integer.parseInt(scanner.nextLine()); // Use nextLine to avoid input issues
                if (totalTickets > 0) {
                    config.setTotalTickets(totalTickets);
                    break;
                } else {
                    System.out.println("Error: Total tickets must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }

        // Input validation for ticket release rate
        while (true) {
            try {
                System.out.print("Enter the ticket release rate: ");
                int ticketReleaseRate = Integer.parseInt(scanner.nextLine());
                if (ticketReleaseRate > 0) {
                    config.setTicketReleaseRate(ticketReleaseRate);
                    break;
                } else {
                    System.out.println("Error: Ticket release rate must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }

        // Input validation for customer retrieval rate
        while (true) {
            try {
                System.out.print("Enter the customer retrieval rate: ");
                int customerRetrievalRate = Integer.parseInt(scanner.nextLine());
                if (customerRetrievalRate > 0) {
                    config.setCustomerRetrievalRate(customerRetrievalRate);
                    break;
                } else {
                    System.out.println("Error: Customer retrieval rate must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }

        // Input validation for max ticket capacity
        while (true) {
            try {
                System.out.print("Enter the maximum ticket capacity: ");
                int maxTicketCapacity = Integer.parseInt(scanner.nextLine());
                if (maxTicketCapacity > config.getTotalTickets()) {
                    config.setMaxTicketCapacity(maxTicketCapacity);
                    break;
                } else {
                    System.out.println("Error: Maximum ticket capacity must be greater than total tickets (" + config.getTotalTickets() + ").");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }
    }

    // Function to show the menu and get the user choice
    public static int showMenu() {
        System.out.println("\n" + " ".repeat(40) + "*".repeat(5) + " Main Menu " + "*".repeat(5));
        int userChoice;
        while (true) {
            try {
                System.out.println("1. Press [1] to Buy Tickets");
                System.out.println("2. Press [2] to Sell Tickets");
                System.out.println("3. Press [3] to Start Retrieving Tickets");
                System.out.println("4. Press [4] to Start Releasing Tickets");
                System.out.println("5. Press [5] to Start Both Releasing and Retrieving Tickets");
                System.out.print("\nPlease Enter Your Choice : ");
                int passedValue = Integer.parseInt(scanner.nextLine());
                if (passedValue <= 5 && passedValue > 0) {
                    userChoice = passedValue;
                    break;
                } else {
                    System.out.println("Invalid choice. Try again !\n");
                    delay(1000);
                }
            } catch (NumberFormatException q) {
                System.out.println("Invalid input format. Try again !\n");
                delay(1000);
            }
        }
        return userChoice;
    }

    // Main Functionality to run the CLI
    public static void mainFunction(int userChoice) {
        if (userChoice == 1) {
            System.out.print("Please Enter the amount of tickets you want to Buy: ");
            ticketPool.removeTickets(scanner.nextInt());
        } else if (userChoice == 2) {
            System.out.print("Please Enter the amount of tickets you want to Sell: ");
            ticketPool.addTickets(scanner.nextInt());
        } else if (userChoice == 3) {
            customer.startRetrievingTickets(config.getCustomerRetrievalRate());
        } else if (userChoice == 4) {
            vendor.startReleasingTickets(config.getTicketReleaseRate());
        } else if (userChoice == 5) {
            vendor.startReleasingTickets(config.getTicketReleaseRate());
            customer.startRetrievingTickets(config.getCustomerRetrievalRate());
        }
    }

    // Function to add delay in between
    public static void delay(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
