package com.movierental.View;

import com.movierental.Customer;
import com.movierental.Movie;
import com.movierental.Rental;
import org.junit.Test;

import static org.junit.Assert.*;

public class HtmlFormatterTest {

    @Test
    public void htmlStatementHasCorrectBreakup() {
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

        String result = customer.htmlStatement(customer);

        String expectedResult = "Rental Record for <b>" + testCustomer + "</b><br>";
        expectedResult += "\t" + testMovie1.getTitle() + "\t" +
                String.valueOf(getRentalAmount(testMovie1.getPriceCode(), rental1.getDaysRented())) + "<br>";

        expectedResult += "\t" + testMovie2.getTitle() + "\t" +
                String.valueOf(getRentalAmount(testMovie2.getPriceCode(), rental2.getDaysRented())) + "<br>";

        expectedResult += "\t" + testMovie3.getTitle() + "\t" +
                String.valueOf(getRentalAmount(testMovie3.getPriceCode(), rental3.getDaysRented())) + "<br>";

        double totalAmount = getRentalAmount(testMovie1.getPriceCode(), rental1.getDaysRented()) +
                getRentalAmount(testMovie2.getPriceCode(), rental2.getDaysRented()) +
                getRentalAmount(testMovie3.getPriceCode(), rental3.getDaysRented());

        int frequentRenterPoints = 4;

        expectedResult += "Amount owed is <b>" + totalAmount + "</b><br>";
        expectedResult += "You earned <b>" + String.valueOf(frequentRenterPoints)
                + "</b> frequent renter points";

        assertEquals(expectedResult, result);
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