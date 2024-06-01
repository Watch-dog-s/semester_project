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
 * Use the {@link MarkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarkFragment extends Fragment {
    private LinearLayout LinerMark;
    private AddRecordViewModel viewModel;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

    protected void showRecords(List<Record> records) {
        LinerMark.removeAllViews();
        for (Record record : records) {
            View recordView = LayoutInflater.from(getContext()).inflate(R.layout.record_item, LinerMark, false);
            TextView textViewRecord = recordView.findViewById(R.id.TextView_Record);

            String mark = Integer.toString(record.getMark());
            textViewRecord.setText(mark);

            int colorMark = ContextCompat.getColor(getContext(), android.R.color.darker_gray);
            textViewRecord.setBackgroundColor(colorMark);

            LinerMark.addView(recordView);
        }
    }
}

