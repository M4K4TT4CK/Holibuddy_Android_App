package com.mikem.vacationapp;

import com.mikem.vacationapp.entities.Excursion;
import com.mikem.vacationapp.entities.Vacation;
import com.mikem.vacationapp.util.DateValidator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test for the HoliBuddy app, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HoliBuddyUnitTest {
    // Final tests for HoliBuddy App
    DateValidator dateValidator = new DateValidator();

    /**
     * Test to check if the endDate is correctly identified as after the startDate.
     */
    @Test
    public void endDate_isAfterStartDate() {
        assertTrue(dateValidator.dateCheck("12/10/2023", "12/15/2023"));
    }

    /**
     * Test to check if the endDate is correctly identified as before the startDate.
     */
    @Test
    public void endDate_isBeforeStartDate() {
        assertFalse(dateValidator.dateCheck("12/15/2023", "12/10/2023"));
    }

    /**
     * Test to check if a valid startDate format is correctly identified.
     */
    @Test
    public void validStartDateFormat() {
        assertTrue(dateValidator.dateValidationStart("12/10/2023"));
    }

    /**
     * Test to check if an invalid startDate format is correctly identified.
     */
    @Test
    public void invalidStartDateFormat() {
        assertFalse(dateValidator.dateValidationStart("2023/12/10"));
    }

    /**
     * Test to check if a valid endDate format is correctly identified.
     */
    @Test
    public void validEndDateFormat() {
        assertTrue(dateValidator.dateValidationEnd("12/15/2023"));
    }

    /**
     * Test to check if an invalid endDate format is correctly identified.
     */
    @Test
    public void invalidEndDateFormat() {
        assertFalse(dateValidator.dateValidationEnd("2023-12-15"));
    }

    /**
     * Test for getting the title of a vacation from the Vacation class.
     * This test ensures the getTitle method returns the correct vacation title.
     */
    @Test
    public void vacationTitle_isCorrect() {
        Vacation vacation = new Vacation(1, "Summer Trip", "Hotel Beach", "2023-07-10", "2023-07-15", "12:00:00:00");
        assertEquals("Summer Trip", vacation.getVacationTitle());
    }

    /**
     * Test for getting the title of an excursion from the Excursion class.
     * This test ensures the getExcursionTitle method returns the correct excursion title.
     */
    @Test
    public void excursionTitle_isCorrect() {
        Excursion excursion = new Excursion(1, 2, "Mountain Hiking", "2023-08-10", "12:00:00:00");
        assertEquals("Mountain Hiking", excursion.getExcursionTitle());
    }


}
