package com.iit.oop.cw;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Vendor {
    private final TicketPool ticketPool;
    private int vendorID;
    private int ticketsPerReleaseRate;
    private int releaseInterval;
    private final Lock lock = new ReentrantLock();
    private boolean running = true;

    public Vendor(TicketPool ticketPool, int vendorID, int ticketsPerReleaseRate, int releaseInterval) {
        this.ticketPool = ticketPool;
        this.vendorID = vendorID;
        this.ticketsPerReleaseRate = ticketsPerReleaseRate;
        this.releaseInterval = 60000;
    }

    public Vendor(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public int getVendorID() {
        return vendorID;
    }

    public int getTicketsPerReleaseRate() {
        return ticketsPerReleaseRate;
    }

    public int getReleaseInterval() {
        return releaseInterval;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public void setTicketsPerReleaseRate(int ticketsPerReleaseRate) {
        this.ticketsPerReleaseRate = ticketsPerReleaseRate;
    }

    public void setReleaseInterval(int releaseInterval) {
        this.releaseInterval = releaseInterval;
    }

    public void startReleasingTickets(int noOfTickets,int releaseInterval){
        new Thread(() -> {
            while (running) {
                lock.lock();
                try {
                    new TicketReleaseWorker(noOfTickets, ticketPool).run();
                    Thread.sleep(releaseInterval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

    public void stopReleasingTickets() {
        running = false;
    }
    public boolean isRunning() {
        return running;
    }
}