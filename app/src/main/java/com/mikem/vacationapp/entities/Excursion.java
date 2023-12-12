package com.mikem.vacationapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "excursions")
public class Excursion {

    // Include the following details for each excursion
    @PrimaryKey(autoGenerate = true)
    private int excursionID;
    private int vacationID;
    private String excursionTitle;
    private String excursionDate;
    private String creationTimeStamp;

    @Ignore
    public Excursion(){

    }

    public Excursion(int excursionID, int vacationID, String excursionTitle, String excursionDate, String creationTimeStamp) {
        this.excursionID = excursionID;
        this.vacationID = vacationID;
        this.excursionTitle = excursionTitle;
        this.excursionDate = excursionDate;
        this.creationTimeStamp = getCurrentTimestamp();

    }

    public int getExcursionID() {
        return excursionID;
    }

    public void setExcursionID(int excursionID) {
        this.excursionID = excursionID;
    }

    public int getVacationID() {
        return vacationID;
    }

    public void setVacationID(int vacationID) {
        this.vacationID = vacationID;
    }

    public String getExcursionTitle() {
        return excursionTitle;
    }

    public void setExcursionTitle(String excursionTitle) {
        this.excursionTitle = excursionTitle;
    }

    public String getExcursionDate() {
        return excursionDate;
    }

    public void setExcursionDate(String excursionDate) {
        this.excursionDate = excursionDate;
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }


    @NonNull
    @Override
    public String toString() {
        return "Excursion{" +
                "excursionID=" + excursionID +
                ", vacationID=" + vacationID +
                ", excursionTitle='" + excursionTitle + '\'' +
                ", excursionDate='" + excursionDate + '\'' +
                '}';
    }

    public String getCreationTimeStamp() {
        return creationTimeStamp;
    }
}
