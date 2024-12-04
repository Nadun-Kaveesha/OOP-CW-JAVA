package com.iit.oop.cw;

public class TicketPool extends Configuration{
    //Global variables


    //Constructor
    public TicketPool(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        super(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }
    public TicketPool(){}





    //Methods
    //Method to Add Tickets
    public synchronized void addTickets(int noOfTickets){
        if(noOfTickets > 0){
            int totalTicketsFromDB = this.loadConfigurationFromDB().getTotalTickets();
            int maxTicketCapacityFromDB = this.loadConfigurationFromDB().getMaxTicketCapacity();
            if(totalTicketsFromDB + noOfTickets <= maxTicketCapacityFromDB){
                this.setTotalTickets(totalTicketsFromDB + noOfTickets);
                System.out.println("You have Successfully Added "+noOfTickets+" to the Store. Total is "+this.loadConfigurationFromDB().getTotalTickets());
            }else {
                throw new IllegalArgumentException("Total Tickets should not exceed the Max Ticket Capacity");
            }
        }else {
            throw new IllegalArgumentException("Number of tickets should be a positive integer");
        }
    }


    //Method to Remove Tickets
    public synchronized void removeTickets(int noOfTickets){
        if(noOfTickets > 0){
            int totalTicketsFromDB = this.loadConfigurationFromDB().getTotalTickets();
            if(totalTicketsFromDB - noOfTickets >= 0){
                this.setTotalTickets(totalTicketsFromDB - noOfTickets);
                System.out.println("You have Successfully bought "+noOfTickets+" From the Store. Remaining is "+this.loadConfigurationFromDB().getTotalTickets());
            }else {
                throw new IllegalArgumentException("Total Tickets should not be less than 0");
            }
        }else {
            throw new IllegalArgumentException("Number of tickets should be a positive integer");
        }
    }
}
