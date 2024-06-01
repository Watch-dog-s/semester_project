package com.example.electronic_diary;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Record.class}, version = 1)
public abstract class RecordDatabase extends RoomDatabase {
    private static RecordDatabase instance;

    public abstract RecordDao RecordDao();

    public static synchronized RecordDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            RecordDatabase.class, "record_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
