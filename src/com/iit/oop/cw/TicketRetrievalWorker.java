package com.iit.oop.cw;

public class TicketRetrievalWorker implements Runnable {
    //Instance Variable initialization
    private final int noOfTickets;
    private final TicketPool ticketPool;

    //Constructor
    public TicketRetrievalWorker(int noOfTickets, TicketPool ticketPool) {
        this.noOfTickets = noOfTickets;
        this.ticketPool = ticketPool;
    }

    @Override
    //Method to Retrieve Tickets by overriding the run method
    public void run() {
        ticketPool.removeTickets(noOfTickets);
    }
}
