package no.hvl.quiz153;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

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
        buttonEasy = (Button) findViewById(R.id.button_easy);
        buttonHard = (Button) findViewById(R.id.button_hard);
        buttonStart = (Button) findViewById(R.id.button_start);
        buttonDatabase = (Button) findViewById(R.id.button_db);
        this.names.add(new QuizEntry("Iver", R.drawable.iver));
        this.names.add(new QuizEntry("Ty", R.drawable.ty));
        this.names.add(new QuizEntry("ISELIN", R.drawable.iselin));
        this.names.add(new QuizEntry("abab", R.drawable.ic_launcher_background));
        this.names.add(new QuizEntry("PAPPA",R.drawable.ic_launcher_foreground));




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

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.names = (ArrayList<QuizEntry>) getIntent().getSerializableExtra("names");
        }
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
        intent.putExtra("names", names);
        startActivity(intent);


    }


}