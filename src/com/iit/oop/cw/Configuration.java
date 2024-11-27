package com.iit.oop.cw;

public class Configuration {
    //Instance Variable initialization
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    //Constructor
    public Configuration(int totalTickets,int ticketReleaseRate,int customerRetrievalRate,int maxTicketCapacity){
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }
    public Configuration(){}

    //Getters
    public int getTotalTickets() {
        return totalTickets;
    }
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    //Setters
    public void setTotalTickets(int newTotalTickets) {
        if(newTotalTickets >= 0){
            this.totalTickets = newTotalTickets;
        }else {
            throw new IllegalArgumentException("Total Tickets should be a positive integer");
        }
    }
    public void setTicketReleaseRate(int newTicketReleaseRate) {
        if (newTicketReleaseRate >= 1){
            this.ticketReleaseRate = newTicketReleaseRate;
        }else {
            throw new IllegalArgumentException("Ticket Release Rate should be a positive integer that is more than 1");
        }
    }
    public void setCustomerRetrievalRate(int newCustomerRetrievalRate) {
        if(newCustomerRetrievalRate >=1){
            this.customerRetrievalRate = newCustomerRetrievalRate;
        }else {
            throw new IllegalArgumentException("Customer Retrieval Rate should be a positive number that is more than 1");
        }
    }
    public void setMaxTicketCapacity(int newMaxTicketCapacity) {
        if(newMaxTicketCapacity >= 0 && newMaxTicketCapacity > this.totalTickets){
            this.maxTicketCapacity = newMaxTicketCapacity;
        }else {
            throw new IllegalArgumentException("Max Ticket Capacity should be a positive integer and it should be more than the total tickets");
        }
    }
}

