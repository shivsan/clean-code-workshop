package com.movierental;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {
    @Before
    public void setup() {
    }

    @Test
    public void statementHasCorrectBreakup() {
        String testCustomer = "testCustomer";
        Customer customer = new Customer(testCustomer);

        String testMovieName1 = "testMovie1";
        Movie testMovie1 = new Movie(testMovieName1, 0);
        Rental rental1 = new Rental(testMovie1, 5);
        customer.addRental(rental1);

        String testMovieName2 = "testMovie2";
        Movie testMovie2 = new Movie(testMovieName2, 1);
        Rental rental2 = new Rental(testMovie2, 2);
        customer.addRental(rental2);

        String testMovieName3 = "testMovie3";
        Movie testMovie3 = new Movie(testMovieName3, 2);
        Rental rental3 = new Rental(testMovie3, 3);
        customer.addRental(rental3);

        String result = customer.statement();

        String expectedResult = "Rental Record for " + testCustomer + "\n";
        expectedResult += "\t" + testMovie1.getTitle() + "\t" +
                String.valueOf(getRentalAmount(testMovie1.getPriceCode(), rental1.getDaysRented())) + "\n";

        expectedResult += "\t" + testMovie2.getTitle() + "\t" +
                String.valueOf(getRentalAmount(testMovie2.getPriceCode(), rental2.getDaysRented())) + "\n";

        expectedResult += "\t" + testMovie3.getTitle() + "\t" +
                String.valueOf(getRentalAmount(testMovie3.getPriceCode(), rental3.getDaysRented())) + "\n";

        double totalAmount = getRentalAmount(testMovie1.getPriceCode(), rental1.getDaysRented()) +
                getRentalAmount(testMovie2.getPriceCode(), rental2.getDaysRented()) +
                getRentalAmount(testMovie3.getPriceCode(), rental3.getDaysRented());

        int frequentRenterPoints = 4;

        expectedResult += "Amount owed is " + totalAmount + "\n";
        expectedResult += "You earned " + String.valueOf(frequentRenterPoints)
                + " frequent renter points";

        assertEquals(expectedResult, result);
    }

    @Test
    public void frequentPointsHasCorrectValue() {
        String testCustomer = "testCustomer";
        Customer customer = new Customer(testCustomer);

        String testMovieName1 = "testMovie1";
        Movie testMovie1 = new Movie(testMovieName1, 0);
        Rental rental1 = new Rental(testMovie1, 5);
        customer.addRental(rental1);

        String testMovieName2 = "testMovie2";
        Movie testMovie2 = new Movie(testMovieName2, 1);
        Rental rental2 = new Rental(testMovie2, 2);
        customer.addRental(rental2);

        String testMovieName3 = "testMovie3";
        Movie testMovie3 = new Movie(testMovieName3, 2);
        Rental rental3 = new Rental(testMovie3, 3);
        customer.addRental(rental3);

        int frequenterRentalPointsForCustomer = customer.getAllFrequenterRentalPoints();

        int frequentPoints = 4;

        assertEquals(frequentPoints, frequenterRentalPointsForCustomer);
    }

    private double getRentalAmount(int rentalCode, int daysRented){
        double thisAmount = 0;

        switch (rentalCode) {
            case 0:
                thisAmount += 2;
                if (daysRented > 2)
                    thisAmount += (daysRented - 2) * 1.5;
                break;
            case 1:
                thisAmount += daysRented * 3;
                break;
            case 2:
                thisAmount += 1.5;
                if (daysRented > 3)
                    thisAmount += (daysRented - 3) * 1.5;
                break;
        }

        return thisAmount;
    }
}