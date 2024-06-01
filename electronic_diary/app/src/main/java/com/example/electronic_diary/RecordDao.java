package com.example.electronic_diary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecordDao {
    @Query("SELECT * FROM records")
    LiveData<List<Record>> getRecords();

    @Query("SELECT COUNT(*) FROM records")
    int getRecordsCount();

    @Insert
    void insert(Record record);
}
