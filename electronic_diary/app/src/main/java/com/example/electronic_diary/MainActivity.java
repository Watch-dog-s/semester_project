package com.example.electronic_diary;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app_database").allowMainThreadQueries().build();
        userDao = db.userDao();

        User user1=new User("student-petrov","12345");
        User user2=new User("student-ivanov","12345");
        User user3=new User("teacher-kuznetsov","12345");
        db.userDao().insertUser(user1);
        db.userDao().insertUser(user2);
        db.userDao().insertUser(user3);


        Button button1 = findViewById(R.id.button);
        EditText usernameEditText = findViewById(R.id.editEmail);
        EditText passwordEditText = findViewById(R.id.EditPassword);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User user = userDao.getUser(username, password);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (user != null) {
                                    if (username.contains("teacher")) {
                                        launchNextScreenTeacher();
                                    } else if (username.contains("student")) {
                                        launchNextScreenStudent();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void launchNextScreenStudent() {
        Intent intent1 = new Intent(this, StudentActivity.class);
        startActivity(intent1);
    }

    private void launchNextScreenTeacher() {
        Intent intent2 = new Intent(this, TeacherActivity.class);
        startActivity(intent2);
    }
}



//            String originalString = "Hello, world!";
//            String substringToRemove = "student-";
//            String resultString = originalString.replace(substringToRemove, "");