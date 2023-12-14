package com.mikem.vacationapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "excursions")
public class Excursion extends BaseEntity {

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
        this.creationTimeStamp = BaseEntity.getCurrentTimestamp(); // Set the timestamp when an Excursion is created

    }

    // Getters and Setters

    @NonNull
    @Override
    public String toString() {
        return "Excursion{" +
                "excursionID=" + excursionID +
                ", vacationID=" + vacationID +
                ", excursionTitle='" + excursionTitle + '\'' +
                ", excursionDate='" + excursionDate + '\'' +
                ", Created on: " + creationTimeStamp +
                '}';
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
}
