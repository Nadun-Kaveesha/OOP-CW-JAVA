package com.iit.oop.cw;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Customer {
    private final TicketPool ticketPool;
    private int customerID;
    private int retrievalInterval;
    private final Lock lock = new ReentrantLock();
    private boolean running = true;

    public Customer(TicketPool ticketPool, int customerID, int retrievalInterval) {
        this.ticketPool = ticketPool;
        this.customerID = customerID;
        this.retrievalInterval = retrievalInterval;
    }

    public Customer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getRetrievalInterval() {
        return retrievalInterval;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setRetrievalInterval(int retrievalInterval) {
        this.retrievalInterval = retrievalInterval;
    }

    public void startRetrievingTickets(int noOfTickets, int retrievalInterval) {
        new Thread(() -> {
            while (running) {
                lock.lock();
                try {
                    new TicketRetrievalWorker(noOfTickets, ticketPool).run();
                    Thread.sleep(retrievalInterval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

    public void stopRetrievingTickets() {
        running = false;
    }
    public boolean isRunning() {
        return running;
    }
}