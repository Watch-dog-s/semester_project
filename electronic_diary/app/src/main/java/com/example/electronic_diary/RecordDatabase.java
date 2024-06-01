package com.example.electronic_diary;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Record.class},version = 1)
public abstract class RecordDatabase extends RoomDatabase
{
    private static RecordDatabase instance=null;
    private static final String DB_Name="Records.db";

    public static RecordDatabase getInstance(Application application)
    {
        if (instance == null)
        {
            instance= Room.databaseBuilder(application,RecordDatabase.class,DB_Name).build();
        }
        return instance;
    }


    public abstract RecordsDao RecordDao();       //экземпляр интерфейса

}
