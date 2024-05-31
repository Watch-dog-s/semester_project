package com.example.electronic_diary;

import java.util.ArrayList;

public class Database
{
    private ArrayList<Record> records=new ArrayList<>();

    public Database()
    {
        Record test1=new Record(1,5,1)
        records.add(test1);

    }

    public void add(Record record)
    {
        records.add(record);
    }

    public void remove(int id)
    {

    }

    public ArrayList<Record> getReords()
    {
        return new ArrayList<>(records);
    }



}
