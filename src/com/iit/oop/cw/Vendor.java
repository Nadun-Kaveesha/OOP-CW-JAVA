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
                Thread ticketReleaseThread = new Thread(new TicketReleaseWorker(noOfTickets));
                ticketReleaseThread.start();
            }
        };
    };

    public void startReleasingTickets(int noOfTickets) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(releaseTickets(noOfTickets),0,5000);
    }
}
