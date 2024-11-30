package com.iit.oop.cw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Configuration {
    //Instance Variable initialization
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    //Constructor
    public Configuration(int totalTickets,int ticketReleaseRate,int customerRetrievalRate,int maxTicketCapacity){
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }
    public Configuration(){}

    //Getters
    public int getTotalTickets() {
        return totalTickets;
    }
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    //Setters
    public void setTotalTickets(int newTotalTickets) {
        if(newTotalTickets >= 0){
            this.totalTickets = newTotalTickets;
            this.saveConfiguration("totalTickets", newTotalTickets);
        }else {
            throw new IllegalArgumentException("Total Tickets should be a positive integer");
        }
    }
    public void setTicketReleaseRate(int newTicketReleaseRate) {
        if (newTicketReleaseRate >= 1){
            this.ticketReleaseRate = newTicketReleaseRate;
            this.saveConfiguration("ticketReleaseRate", newTicketReleaseRate);
        }else {
            throw new IllegalArgumentException("Ticket Release Rate should be a positive integer that is more than 1");
        }
    }
    public void setCustomerRetrievalRate(int newCustomerRetrievalRate) {
        if(newCustomerRetrievalRate >=1){
            this.customerRetrievalRate = newCustomerRetrievalRate;
            this.saveConfiguration("customerRetrievalRate", newCustomerRetrievalRate);
        }else {
            throw new IllegalArgumentException("Customer Retrieval Rate should be a positive number that is more than 1");
        }
    }
    public void setMaxTicketCapacity(int newMaxTicketCapacity) {
        if(newMaxTicketCapacity >= 0 && newMaxTicketCapacity > this.totalTickets){
            this.maxTicketCapacity = newMaxTicketCapacity;
            this.saveConfiguration("maxTicketCapacity", newMaxTicketCapacity);
        }else {
            throw new IllegalArgumentException("Max Ticket Capacity should be a positive integer and it should be more than the total tickets");
        }
    }


    //Methods

    //Save the configuration to the database
    public void saveConfiguration(String attribute, int value) {
        try (Connection connection = DBConfig.getConnection()) {
            String query = "UPDATE Configuration SET " + attribute + " = ? WHERE id = 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, value);
                preparedStatement.executeUpdate();
                System.out.println("Configuration ["+attribute+"] updated successfully in the DataBase.");

            }
        } catch (SQLException e) {
            System.out.println("Failed to update configuration.");
            e.printStackTrace();
        }
    }

    //Load the configuration from the database
    public void loadConfiguration() {
        try (Connection connection = DBConfig.getConnection()) {
            String query = "SELECT * FROM Configuration WHERE id = 1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        this.totalTickets = resultSet.getInt("totalTickets");
                        this.ticketReleaseRate = resultSet.getInt("ticketReleaseRate");
                        this.customerRetrievalRate = resultSet.getInt("customerRetrievalRate");
                        this.maxTicketCapacity = resultSet.getInt("maxTicketCapacity");
                        System.out.println("Configuration loaded successfully.");
                        System.out.println("Total Tickets: " + this.totalTickets);
                        System.out.println("Ticket Release Rate: " + this.ticketReleaseRate);
                        System.out.println("Customer Retrieval Rate: " + this.customerRetrievalRate);
                        System.out.println("Max Ticket Capacity: " + this.maxTicketCapacity);
                    } else {
                        System.out.println("No configuration found with id = 1.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to load configuration.");
            e.printStackTrace();
        }
    }

}

