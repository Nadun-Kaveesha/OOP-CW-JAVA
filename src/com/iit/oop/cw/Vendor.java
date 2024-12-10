package com.iit.oop.cw;

import java.util.Timer;
import java.util.TimerTask;

public class Vendor {
    //Instance Variable initialization
    private final TicketPool ticketPool;
    int vendorID;
    int ticketsPerReleaseRate;
    int releaseInterval;

    //Constructor
    public Vendor(TicketPool ticketPool, int vendorID, int ticketsPerReleaseRate, int releaseInterval) {
        this.ticketPool = ticketPool;
        this.vendorID = vendorID;
        this.ticketsPerReleaseRate = ticketsPerReleaseRate;
        this.releaseInterval = releaseInterval;
    }
    public Vendor(TicketPool ticketPool){
        this.ticketPool = ticketPool;
    }


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
    public synchronized void startReleasingTickets(int noOfTickets) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new TicketReleaseWorker(noOfTickets, ticketPool).run();
            }
        }, 0, 6000);
    }
}
