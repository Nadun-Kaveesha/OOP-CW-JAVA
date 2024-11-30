package com.iit.oop.cw;

public class TicketPool extends Configuration{

    public TicketPool(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        super(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }
    public TicketPool(){}

    public void addTickets(int noOfTickets){
        if(noOfTickets > 0){
            if(this.getTotalTickets() + noOfTickets <= this.getMaxTicketCapacity()){
                this.setTotalTickets(this.getTotalTickets() + noOfTickets);
            }else {
                throw new IllegalArgumentException("Total Tickets should not exceed the Max Ticket Capacity");
            }
        }else {
            throw new IllegalArgumentException("Number of tickets should be a positive integer");
        }
    }

    public void removeTickets(int noOfTickets){
        if(noOfTickets > 0){
            if(this.getTotalTickets() - noOfTickets >= 0){
                this.setTotalTickets(this.getTotalTickets() - noOfTickets);
            }else {
                throw new IllegalArgumentException("Total Tickets should not be less than 0");
            }
        }else {
            throw new IllegalArgumentException("Number of tickets should be a positive integer");
        }
    }
}
