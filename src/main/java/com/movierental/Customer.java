package com.movierental;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();
    private HtmlFormatter htmlFormatter;

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

    private int getAllFrequentRentalPoints() {
        return rentals.stream().mapToInt(r -> r.getFrequentRenterPoints()).sum();
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public String htmlStatement(final Customer customer) {
        return this.htmlFormatter.htmlStatement(customer);
    }

    private double getTotalAmount() {
        return rentals.stream().mapToDouble(r -> r.getAmount()).sum();
    }

    private class HtmlFormatter {

        public String htmlStatement(final Customer customer) {
            String result = "Rental Record for <b>" + customer.getName() + "</b><br>";
            for (Rental each : customer.getRentals()) {
                double thisAmount = each.getAmount();

                //show figures for this rental
                result += "\t" + each.getMovie().getTitle() + "\t" +
                        String.valueOf(thisAmount) + "<br>";
            }

            //add footer lines result
            result += "Amount owed is <b>" + String.valueOf(customer.getTotalAmount()) + "</b><br>";
            result += "You earned <b>" + customer.getAllFrequentRentalPoints()
                    + "</b> frequent renter points";
            return result;
        }

    }
}

