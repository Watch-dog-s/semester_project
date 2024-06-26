package com.example.electronic_diary;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "records")
public class Record {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int mark;
    private int visit;
    private String name;

    public Record(int id, int mark, int visit, String name) {
        this.id = id;
        this.mark = mark;
        this.visit = visit;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getMark() {
        return mark;
    }

    public int getVisit() {
        return visit;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public void setName(String name) {
        this.name = name;
    }
}
