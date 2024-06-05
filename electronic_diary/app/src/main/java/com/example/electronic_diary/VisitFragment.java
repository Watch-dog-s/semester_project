package com.example.electronic_diary;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VisitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VisitFragment extends Fragment
{

    private LinearLayout LinerMark;
    private AddRecordViewModel viewModel;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VisitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VisitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VisitFragment newInstance(String param1, String param2) {
        VisitFragment fragment = new VisitFragment();
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

        viewModel = new ViewModelProvider(this).get(AddRecordViewModel.class);

        buttonAdd.setOnClickListener(v -> {
            Intent intent = AddRecord.newIntent(getContext());
            startActivity(intent);
        });

        viewModel.getAllRecords().observe(getViewLifecycleOwner(), new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                showRecords(records);
            }
        });

        return view;
    }


    protected void showRecords(List<Record> records)
    {
        LinerMark.removeAllViews();
        for (Record record : records)
        {
            View recordView = LayoutInflater.from(getContext()).inflate(R.layout.record_item, LinerMark, false);
            TextView textViewRecord = recordView.findViewById(R.id.TextView_Record);

            int visit = record.getVisit();




                int colorBlue = ContextCompat.getColor(getContext(), android.R.color.holo_blue_light);
                int colorGreen = ContextCompat.getColor(getContext(), android.R.color.holo_green_light);;
                int colorRed = ContextCompat.getColor(getContext(), android.R.color.holo_red_light);



                if (visit==1) {textViewRecord.setBackgroundColor(colorBlue);textViewRecord.setText("П");}
                if (visit==0) {textViewRecord.setBackgroundColor(colorGreen);textViewRecord.setText("У");}
                if (visit==-1) {textViewRecord.setBackgroundColor(colorRed);textViewRecord.setText("Н");}


                LinerMark.addView(recordView);

        }
    }
}