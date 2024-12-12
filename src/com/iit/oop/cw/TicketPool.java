package com.iit.oop.cw;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.ConsoleHandler;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool extends Configuration {
    private Lock ticketLock;
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());

    static {
        try {
            // Remove the default console handler
            Logger rootLogger = Logger.getLogger("");
            ConsoleHandler consoleHandler = null;
            for (var handler : rootLogger.getHandlers()) {
                if (handler instanceof ConsoleHandler) {
                    consoleHandler = (ConsoleHandler) handler;
                    break;
                }
            }
            if (consoleHandler != null) {
                rootLogger.removeHandler(consoleHandler);
            }

            // Add file handler
            FileHandler fileHandler = new FileHandler("src/com/iit/oop/cw/log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TicketPool(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        super(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        ticketLock = new ReentrantLock();
    }

    public TicketPool() {
        ticketLock = new ReentrantLock();
    }

    public void addTickets(int noOfTickets) {
        if (noOfTickets <= 0) {
            throw new IllegalArgumentException("Number of tickets should be a positive integer");
        }
        ticketLock.lock();
        try {
            Configuration currentConfig = this.loadConfigurationFromDB();
            int totalTicketsFromDB = currentConfig.getTotalTickets();
            int maxTicketCapacityFromDB = currentConfig.getMaxTicketCapacity();

            if (totalTicketsFromDB + noOfTickets <= maxTicketCapacityFromDB) {
                int newTotal = totalTicketsFromDB + noOfTickets;
                this.setTotalTickets(newTotal);
                logger.info("✅ You have Successfully Added " + noOfTickets + " to the System. Total is " + newTotal);
                if (newTotal >= maxTicketCapacityFromDB) {
                    logger.info("💯 All the tickets are released.");
                }
            } else {
                throw new IllegalArgumentException("Total Tickets should not exceed the Max Ticket Capacity");
            }
        } finally {
            ticketLock.unlock();
        }
    }

    public void removeTickets(int noOfTickets) {
        if (noOfTickets <= 0) {
            throw new IllegalArgumentException("Number of tickets should be a positive integer");
        }

        ticketLock.lock();
        try {
            Configuration currentConfig = this.loadConfigurationFromDB();
            int totalTicketsFromDB = currentConfig.getTotalTickets();

            if (totalTicketsFromDB - noOfTickets >= 0) {
                int newTotal = totalTicketsFromDB - noOfTickets;
                this.setTotalTickets(newTotal);
                logger.info("❌ You have Successfully bought " + noOfTickets + " From the System. Remaining is " + newTotal);
                if (newTotal == 0) {
                    logger.info("💯 All the tickets have been sold out.");
                }
            } else {
                throw new IllegalArgumentException("Total Tickets should not be less than 0");
            }
        } finally {
            ticketLock.unlock();
        }
    }

    public boolean isSoldOut() {
        Configuration currentConfig = this.loadConfigurationFromDB();
        return currentConfig.getTotalTickets() == 0;
    }

    public boolean isMaxCapacityReached() {
        Configuration currentConfig = this.loadConfigurationFromDB();
        return currentConfig.getTotalTickets() >= currentConfig.getMaxTicketCapacity();
    }

}