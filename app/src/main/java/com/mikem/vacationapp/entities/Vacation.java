package com.mikem.vacationapp.entities;

import android.net.vcn.VcnConfig;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "vacations")
public class Vacation extends BaseEntity{
    // include the following  details for each vacation

    @PrimaryKey(autoGenerate = true)
    private int vacationID;
    private String vacationTitle;
    private String vacationLodging;
    private String vacationStartDate;
    private String vacationEndDate;

    private String creationTimeStamp;

    @Ignore
    public Vacation(){
    }

    public Vacation(int vacationID, String vacationTitle, String vacationLodging, String vacationStartDate, String vacationEndDate, String creationTimeStamp) {
        this.vacationID = vacationID;
        this.vacationTitle = vacationTitle;
        this.vacationLodging = vacationLodging;
        this.vacationStartDate = vacationStartDate;
        this.vacationEndDate = vacationEndDate;
        this.creationTimeStamp = BaseEntity.getCurrentTimestamp();
    }

    public int getVacationID() {
        return vacationID;
    }

    public void setVacationID(int vacationID) {
        this.vacationID = vacationID;
    }

    public String getVacationTitle() {
        return vacationTitle;
    }

    public void setVacationTitle(String vacationTitle) {
        this.vacationTitle = vacationTitle;
    }

    public String getVacationLodging() {
        return vacationLodging;
    }

    public void setVacationLodging(String vacationLodging) {
        this.vacationLodging = vacationLodging;
    }

    public String getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(String vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public String getVacationEndDate() {
        return vacationEndDate;
    }

    public void setVacationEndDate(String vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }

//    private String getCurrentTimestamp() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        return sdf.format(new Date());
//    }

//    public String getCreationTimeStamp() {
//        return creationTimeStamp;
//    }
}

