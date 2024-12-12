package com.iit.oop.cw;

public class TicketRetrievalWorker implements Runnable {
    private final int noOfTickets;
    private final TicketPool ticketPool;
    private final int retrievalInterval;
    private volatile boolean running = true;

    // Constructor to initialize the worker with the number of tickets, ticket pool, and retrieval interval
    public TicketRetrievalWorker(int noOfTickets, TicketPool ticketPool, int retrievalInterval) {
        this.noOfTickets = noOfTickets;
        this.ticketPool = ticketPool;
        this.retrievalInterval = retrievalInterval;
    }

    @Override
    public void run() {
        while (running) {
            // Remove tickets from the pool
            ticketPool.removeTickets(noOfTickets);
            // Stop the worker if tickets are sold out
            if (ticketPool.isSoldOut()) {
                stop();
            }
            try {
                // Sleep for the specified retrieval interval
                Thread.sleep(retrievalInterval);
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