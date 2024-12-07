package com.iit.oop.cw;

public class TicketReleaseWorker implements Runnable {
    //Instance Variable initialization
    private final int noOfTickets;
    private final TicketPool ticketPool;

    //Constructor
    public TicketReleaseWorker(int noOfTickets, TicketPool ticketPool) {
        this.noOfTickets = noOfTickets;
        this.ticketPool = ticketPool;
    }

    //Method to Release Tickets by overriding the run method
    @Override
    public void run() {
        ticketPool.addTickets(noOfTickets);
    }
}