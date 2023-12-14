package com.mikem.vacationapp.entities;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class BaseEntity {

    public String creationTimeStamp;

    public BaseEntity() {
        this.creationTimeStamp = getCurrentTimestamp();
    }

    public static String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    public String getCreationTimeStamp() {
        return creationTimeStamp;
    }

    @NonNull
    @Override
    public String toString() {
        return "Created on: " + creationTimeStamp;
    }
}
