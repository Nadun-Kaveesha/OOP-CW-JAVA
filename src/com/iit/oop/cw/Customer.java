package com.iit.oop.cw;
import java.util.Timer;
import java.util.TimerTask;

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
    public TimerTask retrieveTickets(int noOfTickets) {
        return new TimerTask() {
            @Override
            public void run() {
                Thread ticketRetrieveThread = new Thread(new TicketRetrievalWorker(noOfTickets));
                ticketRetrieveThread.start();
            }
        };
    }

    public void startRetrievingTickets(int noOfTickets) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(retrieveTickets(noOfTickets),0,5000);
    }

}
