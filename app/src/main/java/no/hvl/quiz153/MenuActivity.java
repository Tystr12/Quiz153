package no.hvl.quiz153;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



//This class provides a visual of the main page of the application with the choices the user can make

public class MenuActivity extends AppCompatActivity {

    Button buttonEasy;
    Button buttonHard;
    Button buttonStart;
    Button buttonDatabase;

    ArrayList<QuizEntry> names = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //The buttons are implemented
        buttonEasy = (Button) findViewById(R.id.button_easy);
        buttonHard = (Button) findViewById(R.id.button_hard);
        buttonStart = (Button) findViewById(R.id.button_start);
        buttonDatabase = (Button) findViewById(R.id.button_db);
        // Get a ContentResolver instance
        MainViewModel mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getAllQuizEntrys().observe(this, quizEntries -> {
            // Add the new data to the names ArrayList
            names.clear();
            names.addAll(quizEntries);

        });


// Create a ContentValues object to hold the entry's values

        //Redirects the user to the correct page
        buttonDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act("db");
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act("quiz");
            }
        });

    }



    private void act(String type) {
        Intent intent = new Intent();
        switch (type) {
            case "db":
                intent.setClass(this,DatabaseActivity.class);
        break;
            case "quiz":
                intent.setClass(this, QuizActivity.class);
        }

        startActivity(intent);


    }


}