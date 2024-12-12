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
    private Thread releaseThread;

    // Constructor with parameters
    public Vendor(TicketPool ticketPool, int vendorID, int ticketsPerReleaseRate, int releaseInterval) {
        this.ticketPool = ticketPool;
        this.vendorID = vendorID;
        this.ticketsPerReleaseRate = ticketsPerReleaseRate;
        this.releaseInterval = releaseInterval;
    }

    // Default constructor
    public Vendor(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }


    // Getters
    public int getVendorID() {
        return vendorID;
    }
    public int getReleaseInterval() {
        return releaseInterval;
    }
    public int getTicketsPerReleaseRate() {
        return ticketsPerReleaseRate;
    }


    // Setters
    public void setTicketsPerReleaseRate(int ticketsPerReleaseRate) {
        this.ticketsPerReleaseRate = ticketsPerReleaseRate;
    }
    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }
    public void setReleaseInterval(int releaseInterval) {
        this.releaseInterval = releaseInterval;
    }


    // Method to start releasing tickets
    public void startReleasingTickets(int noOfTickets, int releaseInterval) {
        releaseThread = new Thread(() -> {
            while (running) {
                lock.lock();
                try {
                    // Create a new TicketReleaseWorker and run it
                    TicketReleaseWorker worker = new TicketReleaseWorker(noOfTickets, ticketPool, releaseInterval);
                    worker.run();
                    // Stop the worker if max capacity is reached
                    if (ticketPool.isMaxCapacityReached()) {
                        worker.stop();
                        running = false;
                    }
                    // Sleep for the specified release interval
                    Thread.sleep(releaseInterval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });
        releaseThread.start();
    }

    // Method to stop releasing tickets
    public void stopReleasingTickets() {
        running = false;
        if (releaseThread != null) {
            releaseThread.interrupt();
        }
    }

    // Method to check if the release process is running
    public boolean isRunning() {
        return running;
    }
}