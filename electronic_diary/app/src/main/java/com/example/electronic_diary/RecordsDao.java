package com.example.electronic_diary;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RecordsDao
{

    @Query("SELECT * FROM records")
    List<Record> getRecords();

    @Insert()
    void add(Record record);


}
