package com.iit.oop.cw;

public class App {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setTotalTickets(100);
        config.setTicketReleaseRate(20);
        config.setCustomerRetrievalRate(25);
        config.setMaxTicketCapacity(2000);
        config.loadConfiguration();
    }
}
