package com.iit.oop.cw;

public class App {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setTotalTickets(100);
        config.setTicketReleaseRate(10);
        config.setCustomerRetrievalRate(5);
        config.setMaxTicketCapacity(1000);
        System.out.println("Total Tickets: " + config.getTotalTickets());
        System.out.println("Ticket Release Rate: " + config.getTicketReleaseRate());
        System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
        System.out.println("Max Ticket Capacity: " + config.getMaxTicketCapacity());
    }
}
