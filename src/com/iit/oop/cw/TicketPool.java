package com.iit.oop.cw;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool extends Configuration{
    //Instance Variable initialization
    private Lock ticketLock;

    //Constructor
    public TicketPool(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        super(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        ticketLock = new ReentrantLock();
    }
    public TicketPool(){
        ticketLock = new ReentrantLock();
    }



    //Methods
    //Method to Add Tickets
    public void addTickets(int noOfTickets) {
        if (noOfTickets <= 0) {
            throw new IllegalArgumentException("Number of tickets should be a positive integer");
        }
        ticketLock.lock();
        try {
            // Load configuration once
            Configuration currentConfig = this.loadConfigurationFromDB();
            int totalTicketsFromDB = currentConfig.getTotalTickets();
            int maxTicketCapacityFromDB = currentConfig.getMaxTicketCapacity();

            if (totalTicketsFromDB + noOfTickets <= maxTicketCapacityFromDB) {
                int newTotal = totalTicketsFromDB + noOfTickets;
                this.setTotalTickets(newTotal);
                System.out.println("✅ You have Successfully Added " + noOfTickets +
                        " to the System. Total is " + newTotal);
            } else {
                throw new IllegalArgumentException("Total Tickets should not exceed the Max Ticket Capacity");
            }
        } finally {
            ticketLock.unlock();
        }
    }


    //Method to Remove Tickets
    public void removeTickets(int noOfTickets) {
        if (noOfTickets <= 0) {
            throw new IllegalArgumentException("Number of tickets should be a positive integer");
        }

        ticketLock.lock();
        try {
            // Load configuration once
            Configuration currentConfig = this.loadConfigurationFromDB();
            int totalTicketsFromDB = currentConfig.getTotalTickets();

            if (totalTicketsFromDB - noOfTickets >= 0) {
                int newTotal = totalTicketsFromDB - noOfTickets;
                this.setTotalTickets(newTotal);
                System.out.println("❌You have Successfully bought " + noOfTickets +
                        " From the System. Remaining is " + newTotal);
            } else {
                throw new IllegalArgumentException("Total Tickets should not be less than 0");
            }
        } finally {
            ticketLock.unlock();
        }
    }
}
