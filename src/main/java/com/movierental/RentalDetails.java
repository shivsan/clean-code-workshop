package com.movierental;

import java.util.ArrayList;

public class RentalDetails extends ArrayList<Rental> {

    public double getTotalAmount() {
        return this.stream().mapToDouble(r -> r.getAmount()).sum();
    }

    public int getAllFrequentRentalPoints() {
        return this.stream().mapToInt(r -> r.getFrequentRenterPoints()).sum();
    }
}
