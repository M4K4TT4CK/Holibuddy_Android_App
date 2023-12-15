package com.mikem.vacationapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateValidator {

    // Constructor
    public DateValidator() {

    }

    // Method to check if end date is after start date
    public boolean dateCheck(String start_date, String end_date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.US);
        try {
            Date startDate = sdf.parse(start_date);
            Date endDate = sdf.parse(end_date);
            return startDate != null && endDate != null && startDate.before(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to validate start date format
    public boolean dateValidationStart(String start_date){
        return validateDate(start_date);
    }

    // Method to validate end date format
    public boolean dateValidationEnd(String end_date){
        return validateDate(end_date);
    }

    // Common method for date format validation
    private boolean validateDate(String date){
        if (!date.trim().equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.US);
            sdf.setLenient(false);
            try {
                sdf.parse(date);
                return true;
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
