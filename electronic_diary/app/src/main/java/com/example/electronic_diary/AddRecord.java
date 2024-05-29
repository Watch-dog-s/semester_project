package com.example.electronic_diary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddRecord extends AppCompatActivity
{
    private RadioButton Skip;
    private RadioButton NotSkip;
    private RadioButton Attendance;

    private RadioButton No_mark;
    private RadioButton FourMark;
    private RadioButton FiveMark;
    private RadioButton ThreeMark;
    private RadioButton TwoMark;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_record);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button ButtonSave= findViewById(R.id.buttonSave);

        RadioButton Skip=findViewById(R.id.skipp);
        RadioButton NotSkip=findViewById(R.id.not_skipp);
        RadioButton Attendance=findViewById(R.id.attendance);

        RadioButton No_mark=findViewById(R.id.NoMark);
        RadioButton FourMark=findViewById(R.id.FourMark);
        RadioButton FiveMark=findViewById(R.id.FiveMark);
        RadioButton ThreeMark=findViewById(R.id.ThreeMark);
        RadioButton TwoMark=findViewById(R.id.TwoMark);


        ButtonSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void saveRecord()
    {
        int mark=getMark();
        int visit =getVisit();


    }

    private int getMark()
    {
        int mark=0;
        if (FiveMark.isChecked())
        {mark=5;}
        else if (FourMark.isChecked())
        {mark=4;}

        else if (ThreeMark.isChecked())
        {mark=3;}

        else if (TwoMark.isChecked())
        {mark=2;}

        else if (No_mark.isChecked())
        {mark=0;}
        return mark;


    }

    private int getVisit()
    {
        int visit=-1;
        if (Skip.isChecked())
        {visit=-1;}

        else if (Attendance.isChecked())
        {visit=1;}

        else if (NotSkip.isChecked())
        {visit=0;}
        return visit;

    }


    public static Intent newIntent(Context context)
    {
        return new Intent(context, AddRecord.class);

    }


}