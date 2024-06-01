package com.example.electronic_diary;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Records")
public class Record
{
    @PrimaryKey
    private int id;
    private int mark;
    private int visit;

    public Record(int id,int mark,int visit)
    {
        this.id=id;
        this.mark=mark;
        this.visit=visit;
    }

    public int getId() {return id;}

    public int getMark() {
        return mark;
    }

    public int getVisit()
    {
        return this.visit;
    }


}
