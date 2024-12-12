package com.iit.oop.cw;

import java.util.logging.Logger;

public class TicketRetrievalWorker implements Runnable {
    private final int noOfTickets;
    private final TicketPool ticketPool;
    private final int retrievalInterval;
    private volatile boolean running = true;

    public TicketRetrievalWorker(int noOfTickets, TicketPool ticketPool, int retrievalInterval) {
        this.noOfTickets = noOfTickets;
        this.ticketPool = ticketPool;
        this.retrievalInterval = retrievalInterval;
    }

    @Override
    public void run() {
        while (running) {
            ticketPool.removeTickets(noOfTickets);
            if (ticketPool.isSoldOut()) {
                stop();
            }
            try {
                Thread.sleep(retrievalInterval); // Use the passed retrieval interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }
}