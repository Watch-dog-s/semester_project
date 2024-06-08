package com.example.electronic_diary;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class TeacherActivity extends AppCompatActivity {

    private Button ButtonAdd;
    private Spinner userSpinner;


    public static String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teacher);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        user="student-petrov";


        findViewById(R.id.buttonMarks).setOnClickListener(this::Marks);
        findViewById(R.id.buttonVisits).setOnClickListener(this::Visits);
        findViewById(R.id.Back).setOnClickListener(this::onPreviousActivity);

        userSpinner = findViewById(R.id.spinner);
        loadUsernames();
    }

    private void loadUsernames() {
        AsyncTask.execute(() -> {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            List<String> usernames = db.userDao().getAllUsernamesWithStudent();

            // Удаление повторяющихся имен
            Set<String> uniqueUsernames = new HashSet<>(usernames);

            runOnUiThread(() -> populateSpinner(uniqueUsernames));
        });
    }

    private void populateSpinner(Set<String> usernames) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(usernames));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(adapter);

        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedUsername = (String) parent.getItemAtPosition(position);
                user=selectedUsername;
                Marks(view);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    protected void onPreviousActivity(View view) {
        finish();
    }

    protected void Marks(View view) {
        replaceFragment(new MarkFragment());
    }

    protected void Visits(View view) {
        replaceFragment(new VisitFragment());
    }

    private void replaceFragment(Fragment fragment)
    {

        Bundle args = new Bundle();
        args.putString("message", user);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }


}
