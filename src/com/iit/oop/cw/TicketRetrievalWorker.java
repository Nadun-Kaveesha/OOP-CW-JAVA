package com.iit.oop.cw;

public class TicketRetrievalWorker implements Runnable{
    private final int noOfTickets;

    public TicketRetrievalWorker(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    @Override
    public void run() {
        //Logic to Release the Tickets
        TicketPool ticketPool = new TicketPool();
        if(noOfTickets > 0){
            if(ticketPool.loadConfigurationFromDB().getTotalTickets() - noOfTickets > 0){
                ticketPool.removeTickets(noOfTickets);
            } else {
                System.out.println("Total Tickets should not be less than 0");
            }
        } else {
            System.out.println("Number of tickets should be a positive integer");
        }
    }
}
