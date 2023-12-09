package com.mikem.vacationapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mikem.vacationapp.entities.Excursion;

import java.util.List;

// Task B - code that includes encapsulation

@Dao
public interface ExcursionDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Excursion excursion);

    @Update
    void update(Excursion excursion);

    @Delete
    void delete(Excursion excursion);

    @Query("SELECT * FROM EXCURSIONS ORDER BY EXCURSIONTITLE ASC")
    List<Excursion> getAllExcursions();

    @Query("SELECT * FROM EXCURSIONS  WHERE VACATIONID=:vacationID ORDER BY EXCURSIONTITLE ASC")
    List<Excursion> getAssociatedExcursions(int vacationID);
}
