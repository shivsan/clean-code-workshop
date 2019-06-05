package com.movierental;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        String result = "Rental Record for " + getName() + "\n";
        for (Rental each : rentals) {
            double thisAmount = each.getAmount();

            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(thisAmount) + "\n";
        }

        //add footer lines result
        result += "Amount owed is " + String.valueOf(getTotalAmount()) + "\n";
        result += "You earned " + getAllFrequentRentalPoints()
                + " frequent renter points";
        return result;
    }

    public int getAllFrequentRentalPoints() {
        return rentals.stream().mapToInt(r -> r.getFrequentRenterPoints()).sum();
    }

    private double getTotalAmount() {
        return rentals.stream().mapToDouble(r -> r.getAmount()).sum();
    }

    public String htmlStatement() {
        return new TextFormatter().htmlStatement(getName(), rentals, getTotalAmount(), getAllFrequentRentalPoints());
    }
}


class TextFormatter{

    public String htmlStatement(String customerName, List<Rental> rentals, double totalAmount, int allFrequentRentalPoints) {
        String result = "Rental Record for <b>" + customerName + "</b><br>";
        for (Rental each : rentals) {
            double thisAmount = each.getAmount();

            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(thisAmount) + "<br>";
        }

        //add footer lines result
        result += "Amount owed is <b>" + String.valueOf(totalAmount) + "</b><br>";
        result += "You earned <b>" + allFrequentRentalPoints
                + "</b> frequent renter points";
        return result;
    }
}