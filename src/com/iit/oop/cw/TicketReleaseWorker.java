package com.iit.oop.cw;

public class TicketReleaseWorker implements Runnable {
    private final int noOfTickets;
    private final TicketPool ticketPool;
    private final int releaseInterval;
    private volatile boolean running = true;

    // Constructor to initialize the worker with the number of tickets, ticket pool, and release interval
    public TicketReleaseWorker(int noOfTickets, TicketPool ticketPool, int releaseInterval) {
        this.noOfTickets = noOfTickets;
        this.ticketPool = ticketPool;
        this.releaseInterval = releaseInterval;
    }

    @Override
    public void run() {
        while (running) {
            // Add tickets to the pool
            ticketPool.addTickets(noOfTickets);
            // Stop the worker if max capacity is reached
            if (ticketPool.isMaxCapacityReached()) {
                stop();
            }
            try {
                // Sleep for the specified release interval
                Thread.sleep(releaseInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Method to stop the worker
    public void stop() {
        running = false;
    }
}