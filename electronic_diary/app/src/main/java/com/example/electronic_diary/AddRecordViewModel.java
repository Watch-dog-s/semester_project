package com.example.electronic_diary;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddRecordViewModel extends AndroidViewModel {
    private final RecordDatabase database;
    private final ExecutorService executorService;

    public AddRecordViewModel(Application application) {
        super(application);
        database = RecordDatabase.getInstance(application);
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Record>> getAllRecords() {
        return database.RecordDao().getRecords();
    }

    public void saveRecord(int mark, int visit) {
        executorService.execute(() -> {
            int id = database.RecordDao().getRecordsCount() + 1;
            Record record = new Record(id, mark, visit);
            database.RecordDao().insert(record);
        });
    }
}
