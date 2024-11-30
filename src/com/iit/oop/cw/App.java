package com.iit.oop.cw;

public class App {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setTotalTickets(69);
        config.setTicketReleaseRate(10);
        config.setCustomerRetrievalRate(5);
        config.setMaxTicketCapacity(1000);
        config.loadConfiguration();
    }
}
