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
        while (true) {
            int globalUserChoice = showGlobalMenu();
            if (globalUserChoice == 0) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            }
            mainFunction(globalUserChoice);
        }
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
    public static int showGlobalMenu() {
        System.out.println("\n" + " ".repeat(40) + "*".repeat(5) + " Main Menu " + "*".repeat(5));
        int globalUserChoice;
        while (true) {
            try {
                System.out.println("1. Press [1] If You are a Customer");
                System.out.println("2. Press [2] If You are a Vendor");
                System.out.println("3. Press [3] If you are an Admin");
                System.out.println("0. Press [0] to Exit");
                System.out.print("\nPlease Enter Your Choice: ");
                int passedValue = Integer.parseInt(scanner.nextLine());
                if (passedValue <= 3 && passedValue >= 0) {
                    globalUserChoice = passedValue;
                    break;
                } else {
                    System.out.println("Invalid choice. Try again!\n");
                    delay(1000);
                }
            } catch (NumberFormatException q) {
                System.out.println("Invalid input format. Try again!\n");
                delay(1000);
            }
        }
        return globalUserChoice;
    }

    // Showing the Personal Menu
    public static void mainFunction(int globalUserChoice) {
        if (globalUserChoice == 1) {
            while (true) {
                try {
                    System.out.print("Press [1] to Buy Tickets: ");
                    int passedValue = Integer.parseInt(scanner.nextLine());
                    if (passedValue == 1) {
                        System.out.print("Please Enter the amount of tickets you want to Buy: ");
                        int noOfTickets = Integer.parseInt(scanner.nextLine());
                        if (noOfTickets > 0) {
                            ticketPool.removeTickets(noOfTickets);
                            break;
                        } else {
                            System.out.println("Error: Number of tickets must be greater than 0.");
                        }
                    } else {
                        System.out.println("Invalid choice. Try again!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid positive integer.");
                }
            }
        } else if (globalUserChoice == 2) {
            while (true) {
                try {
                    System.out.print("Press [1] to Add Tickets to the System: ");
                    int passedValue = Integer.parseInt(scanner.nextLine());
                    if (passedValue == 1) {
                        System.out.print("Please Enter the amount of tickets you want to Add: ");
                        int noOfTickets = Integer.parseInt(scanner.nextLine());
                        if (noOfTickets > 0) {
                            ticketPool.addTickets(noOfTickets);
                            break;
                        } else {
                            System.out.println("Error: Number of tickets must be greater than 0.");
                        }
                    } else {
                        System.out.println("Invalid choice. Try again!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid positive integer.");
                }
            }
        } else if (globalUserChoice == 3) {
            while (true) {
                try {
                    System.out.println("\nPress [1] to Start Releasing Tickets");
                    System.out.println("Press [2] to Start Retrieving Tickets");
                    System.out.println("Press [3] to Start Both");
                    System.out.println("Press [4] to Reconfigure the Releasing Rate");
                    System.out.println("Press [5] to Reconfigure the Retrieving Rate");
                    System.out.println("Press [6] to Show Status");
                    System.out.println("Press [7] to Stop a Running process");
                    System.out.print("Please Enter Your Choice: ");
                    int passedValue = Integer.parseInt(scanner.nextLine());

                    if (passedValue == 1) {
                        System.out.print("Enter the release interval in milliseconds: ");
                        int releaseInterval = Integer.parseInt(scanner.nextLine());
                        new Thread(() -> vendor.startReleasingTickets(config.getTicketReleaseRate(), releaseInterval)).start();
                        System.out.println("Process started successfully in the Background. Check the log files for more information.");
                        return;

                    } else if (passedValue == 2) {
                        System.out.print("Enter the retrieval interval in milliseconds: ");
                        int retrievalInterval = Integer.parseInt(scanner.nextLine());
                        new Thread(() -> customer.startRetrievingTickets(config.getCustomerRetrievalRate(), retrievalInterval)).start();
                        System.out.println("Process started successfully in the Background. Check the log files for more information.");
                        return;

                    } else if (passedValue == 3) {
                        System.out.print("Enter the release interval in milliseconds: ");
                        int releaseInterval = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter the retrieval interval in milliseconds: ");
                        int retrievalInterval = Integer.parseInt(scanner.nextLine());
                        new Thread(() -> vendor.startReleasingTickets(config.getTicketReleaseRate(), releaseInterval)).start();
                        new Thread(() -> customer.startRetrievingTickets(config.getCustomerRetrievalRate(), retrievalInterval)).start();
                        System.out.println("Both processes started successfully in the Background. Check the log files for more information.");
                        return;

                    } else if (passedValue == 4) {
                        while (true) {
                            try {
                                System.out.print("Enter the new ticket release rate: ");
                                int newRate = Integer.parseInt(scanner.nextLine());
                                if (newRate > 0) {
                                    config.setTicketReleaseRate(newRate);
                                    System.out.println("Ticket release rate updated successfully to " + newRate);
                                    break;
                                } else {
                                    System.out.println("Error: Ticket release rate must be greater than 0.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid positive integer.");
                            }
                        }
                        break;

                    } else if (passedValue == 5) {
                        while (true) {
                            try {
                                System.out.print("Enter the new customer retrieval rate: ");
                                int newRate = Integer.parseInt(scanner.nextLine());
                                if (newRate > 0) {
                                    config.setCustomerRetrievalRate(newRate);
                                    System.out.println("Customer retrieval rate has been updated to " + newRate);
                                    break;
                                } else {
                                    System.out.println("Error: Customer retrieval rate must be greater than 0.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid positive integer.");
                            }
                        }
                        break;

                    } else if (passedValue == 6) {
                        showStatus();
                        return;

                    } else if (passedValue == 7) {
                        stopProcess();
                        return;
                    } else {
                        System.out.println("Invalid choice. Try again!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid positive integer.");
                }
            }
        }
    }


    // Function to stop the process
    public static void stopProcess() {
        while (true) {
            try {
                System.out.println("\nPress [1] to Stop Releasing Tickets");
                System.out.println("Press [2] to Stop Retrieving Tickets");
                System.out.println("Press [3] to Stop Both");
                System.out.print("Please Enter Your Choice: ");
                int passedValue = Integer.parseInt(scanner.nextLine());

                if (passedValue == 1) {
                    if (vendor.isRunning()) {
                        vendor.stopReleasingTickets();
                        System.out.println("Releasing tickets process stopped.");
                    } else {
                        System.out.println("No releasing process is running.");
                    }
                    break;
                } else if (passedValue == 2) {
                    if (customer.isRunning()) {
                        customer.stopRetrievingTickets();
                        System.out.println("Retrieving tickets process stopped.");
                    } else {
                        System.out.println("No retrieving process is running.");
                    }
                    break;
                } else if (passedValue == 3) {
                    boolean anyProcessRunning = false;
                    if (vendor.isRunning()) {
                        vendor.stopReleasingTickets();
                        anyProcessRunning = true;
                        System.out.println("Releasing tickets process stopped.");
                    }
                    if (customer.isRunning()) {
                        customer.stopRetrievingTickets();
                        anyProcessRunning = true;
                        System.out.println("Retrieving tickets process stopped.");
                    }
                    if (!anyProcessRunning) {
                        System.out.println("No process is running.");
                    }
                    break;
                } else {
                    System.out.println("Invalid choice. Try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }
    }

    // Function to show the status
    public static void showStatus() {
        Thread statusThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                Configuration currentConfig = config.loadConfigurationFromDB();
                if (currentConfig != null) {
                    System.out.println("Total Tickets: " + currentConfig.getTotalTickets());
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        statusThread.start();

        // Wait for any key press to abort the status display
        scanner.nextLine();
        statusThread.interrupt();
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