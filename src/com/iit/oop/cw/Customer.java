package com.iit.oop.cw;
import java.util.Timer;
import java.util.TimerTask;

public class Customer {
    //Instance Variable initialization
    private final TicketPool ticketPool;
    private  int customerID;
    private  int retrievalInterval;

    //Constructor
    public Customer(TicketPool ticketPool, int customerID, int retrievalInterval) {
        this.ticketPool = ticketPool;
        this.customerID = customerID;
        this.retrievalInterval = retrievalInterval;
    }
    public Customer(TicketPool ticketPool){
        this.ticketPool = ticketPool;
    }


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
    public synchronized void startRetrievingTickets(int noOfTickets) {
        //Timer to schedule the task of retrieving tickets
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new TicketRetrievalWorker(noOfTickets, ticketPool).run();
            }
        }, 0, 5000);
    }

}
