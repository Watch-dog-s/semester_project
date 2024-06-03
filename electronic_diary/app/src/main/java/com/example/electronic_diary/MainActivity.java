package com.example.electronic_diary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button1=findViewById(R.id.button);
        EditText login=findViewById(R.id.editEmail);
        button1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent();
                String email=login.getText().toString();

                boolean teacher=email.contains("teacher");
                boolean student=email.contains("student");

                if (student) {
                    launchNextScreenStudent();
                }

                else if (teacher)
                {
                    Log.e("teacher","teacher!!!!!!!");
                    launchNextScreenTeacher();

                }




            }
        });


    }

    private void launchNextScreenStudent()
    {
        Intent intent1 = new Intent(this, StudentActivity.class);
        startActivity(intent1);
    }

    private void launchNextScreenTeacher()
    {
        Intent intent2 = new Intent(this, TeacherActivity.class);
        startActivity(intent2);
    }
}