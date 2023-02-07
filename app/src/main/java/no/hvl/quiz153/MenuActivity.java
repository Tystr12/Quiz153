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

    ArrayList<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        buttonEasy = (Button) findViewById(R.id.button_easy);
        buttonHard = (Button) findViewById(R.id.button_hard);
        buttonStart = (Button) findViewById(R.id.button_start);
        buttonDatabase = (Button) findViewById(R.id.button_db);
        this.names.add("Iselin");
        this.names.add("Iver");
        this.names.add("Ty");



        buttonDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act("db");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.names = getIntent().getStringArrayListExtra("names");
        }
        //this.names = getIntent().getStringArrayListExtra("names");
    }

    private void act(String type) {
        switch (type) {
            case "db":
                Log.d("aaa", names.toString());
                Intent intent = new Intent(this, DatabaseActivity.class).putExtra("names", names);
                startActivity(intent);

        }


    }
}