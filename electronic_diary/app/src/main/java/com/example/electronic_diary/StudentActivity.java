package com.example.electronic_diary;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class StudentActivity extends AppCompatActivity
{


    public static String user;
  
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        findViewById(R.id.buttonMarks).setOnClickListener(this::Marks);
        findViewById(R.id.buttonVisits).setOnClickListener(this::Visits);
        findViewById(R.id.Back).setOnClickListener(this::onPreviousActivity);


         user=getIntent().getStringExtra("message");
         Log.i("studentActivity",user);


    }




    protected void onPreviousActivity(View view){
        finish();
    }

    protected void Marks(View view)
    {
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

        getSupportFragmentManager().executePendingTransactions();
        View fragmentView = fragment.getView();if (fragmentView != null)
    {   fragmentView.findViewById(R.id.ButtonAdd).setVisibility(View.INVISIBLE);
    }
    }


}