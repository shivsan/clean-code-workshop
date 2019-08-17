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
        RentalDetails rentalDetails = new RentalDetails();
        rentalDetails.addAll(rentals);
        return new TextFormatter().statement(getName(), rentalDetails);
    }


    private double getTotalAmount() {
        return rentals.stream().mapToDouble(r -> r.getAmount()).sum();
    }

    public int getAllFrequentRentalPoints() {
        return rentals.stream().mapToInt(r -> r.getFrequentRenterPoints()).sum();
    }

    public String htmlStatement() {
        RentalDetails rentalDetails = new RentalDetails();
        rentalDetails.addAll(rentals);
        return new HtmlTextFormatter().htmlStatement(getName(), rentalDetails);
    }
}


class HtmlTextFormatter {

    public String htmlStatement(String customerName, RentalDetails rentalDetails) {
        String result = "Rental Record for <b>" + customerName + "</b><br>";
        for (Rental each : rentalDetails) {
            double thisAmount = each.getAmount();

            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(thisAmount) + "<br>";
        }

        //add footer lines result
        result += "Amount owed is <b>" + String.valueOf(rentalDetails.getTotalAmount()) + "</b><br>";
        result += "You earned <b>" + rentalDetails.getAllFrequentRentalPoints()
                + "</b> frequent renter points";
        return result;
    }
}

class TextFormatter {

    public String statement(String customerName, RentalDetails rentalDetails) {
        String result = "Rental Record for " + customerName + "\n";
        for (Rental each : rentalDetails) {
            double thisAmount = each.getAmount();

            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(thisAmount) + "\n";
        }

        //add footer lines result
        result += "Amount owed is " + String.valueOf(rentalDetails.getTotalAmount()) + "\n";
        result += "You earned " + rentalDetails.getAllFrequentRentalPoints()
                + " frequent renter points";
        return result;
    }
}