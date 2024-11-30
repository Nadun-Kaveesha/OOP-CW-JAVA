package com.iit.oop.cw;

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
    public void releaseTickets(int noOfTickets){
        if(noOfTickets > 0){
            TicketPool ticketPool = new TicketPool();
            if(ticketPool.getTotalTickets() + noOfTickets <= ticketPool.getMaxTicketCapacity()){
                ticketPool.addTickets(noOfTickets);
            }else {
                throw new IllegalArgumentException("Total Tickets should not exceed the Max Ticket Capacity");
            }
        }else {
            throw new IllegalArgumentException("Number of tickets should be a positive integer");
        }
    }
}
