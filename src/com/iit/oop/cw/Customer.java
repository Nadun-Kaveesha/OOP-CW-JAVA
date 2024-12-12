package com.iit.oop.cw;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Customer {
    private final TicketPool ticketPool;
    private int customerID;
    private int retrievalInterval;
    private final Lock lock = new ReentrantLock();
    private boolean running = true;
    private Thread retrievalThread;

    // Constructor with parameters
    public Customer(TicketPool ticketPool, int customerID, int retrievalInterval) {
        this.ticketPool = ticketPool;
        this.customerID = customerID;
        this.retrievalInterval = retrievalInterval;
    }

    // Default constructor
    public Customer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    // Getters
    public int getCustomerID() {
        return customerID;
    }
    public int getRetrievalInterval() {
        return retrievalInterval;
    }

    // Setters
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setRetrievalInterval(int retrievalInterval) {
        this.retrievalInterval = retrievalInterval;
    }

    // Method to start retrieving tickets
    public void startRetrievingTickets(int noOfTickets, int retrievalInterval) {
        retrievalThread = new Thread(() -> {
            while (running) {
                lock.lock();
                try {
                    // Create a new TicketRetrievalWorker and run it
                    TicketRetrievalWorker worker = new TicketRetrievalWorker(noOfTickets, ticketPool, retrievalInterval);
                    worker.run();
                    // Stop the worker if tickets are sold out
                    if (ticketPool.isSoldOut()) {
                        worker.stop();
                        running = false;
                    }
                    // Sleep for the specified retrieval interval
                    Thread.sleep(retrievalInterval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });
        retrievalThread.start();
    }

    // Method to stop retrieving tickets
    public void stopRetrievingTickets() {
        running = false;
        if (retrievalThread != null) {
            retrievalThread.interrupt();
        }
    }

    // Method to check if the retrieval process is running
    public boolean isRunning() {
        return running;
    }
}