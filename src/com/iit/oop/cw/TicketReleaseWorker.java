package com.iit.oop.cw;

public class TicketReleaseWorker implements Runnable {
    private final int noOfTickets;

    public TicketReleaseWorker(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    @Override
    public void run() {
        // Logic to retrieve tickets
        TicketPool ticketPool = new TicketPool();
        if (ticketPool.loadConfigurationFromDB().getTotalTickets() + noOfTickets <= ticketPool.loadConfigurationFromDB().getMaxTicketCapacity()) {
            ticketPool.addTickets(noOfTickets);
            System.out.println("Released " + noOfTickets + " tickets.");
        } else {
            System.out.println("Total Tickets should not exceed the Max Ticket Capacity");
        }
    }
}