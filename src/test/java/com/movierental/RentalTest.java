package com.movierental;

import org.junit.Test;

import static org.junit.Assert.*;

public class RentalTest {

    @Test
    public void getFrequentRenterPoints() {
        Rental rental = new Rental(new Movie(null, Movie.CHILDRENS), 2);
        final int frequentRenterPoints = rental.getFrequentRenterPoints();
        assertEquals(frequentRenterPoints, 1);
    }

    @Test
    public void getAdditionalFrequentRenterPoints_MovieRelease() {
        Rental rental = new Rental(new Movie(null, Movie.NEW_RELEASE), 2);
        final int frequentRenterPoints = rental.getFrequentRenterPoints();
        assertEquals(frequentRenterPoints, 2);
    }
}