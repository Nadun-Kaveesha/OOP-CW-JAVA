package com.iit.oop.cw;

public class Customer {
    //Instance Variable initialization
    int customerID;
    int retrievalInterval;

    //Constructor
    public Customer(int customerID, int retrievalInterval) {
        this.customerID = customerID;
        this.retrievalInterval = retrievalInterval;
    }
    public Customer(){}


    //Getters
    public int getCustomerID() {
        return customerID;
    }

    public int getRetrievalInterval() {
        return retrievalInterval;
    }

    //Setters
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setRetrievalInterval(int retrievalInterval) {
        this.retrievalInterval = retrievalInterval;
    }


    //Methods
    //Method to Retrieve Tickets
    public void retrieveTickets(int noOfTickets){
        if(noOfTickets > 0){
            TicketPool ticketPool = new TicketPool();
            if(ticketPool.getTotalTickets() - noOfTickets > 0){
                ticketPool.removeTickets(noOfTickets);
            }else {
                throw new IllegalArgumentException("Total Tickets should not be less than 0");
            }
        }else {
            throw new IllegalArgumentException("Number of tickets should be a positive integer");
        }
    }

}
