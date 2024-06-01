package com.example.electronic_diary;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MarkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarkFragment extends Fragment
{
    private LinearLayout LinerMark;
    private RecordDatabase database;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static MarkFragment newInstance(String param1, String param2) {
        MarkFragment fragment = new MarkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_mark, container, false);

        Button buttonAdd = view.findViewById(R.id.ButtonAdd);
        LinerMark = view.findViewById(R.id.LinerMark);

        database = RecordDatabase.getInstance(requireActivity().getApplication());

        buttonAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = AddRecord.newIntent(getContext());
                startActivity(intent);
                showRecords();
            }
        });

        //showRecords();

        return view;
    }


    protected void addRecord()
    {

    }

    protected void showRecords()
    {
        for (int i = 0; i < database.RecordDao().getRecords().size(); i++)
        {
            View recordView = LayoutInflater.from(getContext()).inflate(R.layout.record_item, LinerMark, false);
            TextView textViewRecord = recordView.findViewById(R.id.TextView_Record);

            String mark= Integer.toString(database.RecordDao().getRecords().get(i).getMark());
            textViewRecord.setText(mark);

            int colorMark = ContextCompat.getColor(getContext(), android.R.color.darker_gray);
            textViewRecord.setBackgroundColor(colorMark);

            LinerMark.addView(recordView);
        }
    }
}