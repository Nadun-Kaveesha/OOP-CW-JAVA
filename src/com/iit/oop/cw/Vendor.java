package com.iit.oop.cw;

import java.util.Timer;
import java.util.TimerTask;

public class Vendor {
    //Instance Variable initialization
    int vendorID;
    int ticketsPerReleaseRate;
    int releaseInterval;

    //Constructor
    public Vendor(int vendorID, int ticketsPerReleaseRate, int releaseInterval) {
        this.vendorID = vendorID;
        this.ticketsPerReleaseRate = ticketsPerReleaseRate;
        this.releaseInterval = releaseInterval;
    }
    public Vendor(){}


    //Getters
    public int getVendorID() {
        return vendorID;
    }

    public int getTicketsPerReleaseRate() {
        return ticketsPerReleaseRate;
    }

    public int getReleaseInterval() {
        return releaseInterval;
    }

    //Setters
    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public void setTicketsPerReleaseRate(int ticketsPerReleaseRate) {
        this.ticketsPerReleaseRate = ticketsPerReleaseRate;
    }

    public void setReleaseInterval(int releaseInterval) {
        this.releaseInterval = releaseInterval;
    }

    //Methods
    //Method to Release Tickets
    public TimerTask releaseTickets(int noOfTickets) {
        return new TimerTask() {
            @Override
            public void run() {
                if(noOfTickets > 0){
                    TicketPool ticketPool = new TicketPool();
                    if(ticketPool.loadConfigurationFromDB().getTotalTickets() + noOfTickets <= ticketPool.loadConfigurationFromDB().getMaxTicketCapacity()){
                        ticketPool.addTickets(noOfTickets);
                    } else {
                        System.out.println("Total Tickets should not exceed the Max Ticket Capacity");
                        this.cancel();

                    }
                } else {
                    System.out.println("Number of tickets should be a positive integer");
                    this.cancel();
                }
            }
        };
    }

    public void startReleasingTickets(int noOfTickets) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(releaseTickets(noOfTickets),0,5000);
    }
}
