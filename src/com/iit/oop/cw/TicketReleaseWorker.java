package com.iit.oop.cw;

public class TicketReleaseWorker implements Runnable {
    private final int noOfTickets;
    private final TicketPool ticketPool;
    private final int releaseInterval;
    private volatile boolean running = true;

    public TicketReleaseWorker(int noOfTickets, TicketPool ticketPool, int releaseInterval) {
        this.noOfTickets = noOfTickets;
        this.ticketPool = ticketPool;
        this.releaseInterval = releaseInterval;
    }

    @Override
    public void run() {
        while (running) {
            ticketPool.addTickets(noOfTickets);
            if (ticketPool.isMaxCapacityReached()) {
                stop();
            }
            try {
                Thread.sleep(releaseInterval); // Use the passed release interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }
}