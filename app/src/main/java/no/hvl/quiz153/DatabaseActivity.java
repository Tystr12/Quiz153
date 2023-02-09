package no.hvl.quiz153;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.Collections;

//This class provides a visual of all elements in the database

public class DatabaseActivity extends AppCompatActivity {
    ArrayList<QuizEntry> names = new ArrayList<>();

    ListView listView;

    Button button_sort;
    Button button_back;

    Button button_newentry;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        //Creates the view dynamic
        listView = (ListView) findViewById(R.id.my_list);

        names = (ArrayList<QuizEntry>) getIntent().getSerializableExtra("names");

        CustomAdaptr customAdaptr = new CustomAdaptr(getApplicationContext(),names);
        listView.setAdapter(customAdaptr);
        button_sort = (Button) findViewById(R.id.button_sort);
        button_back = (Button) findViewById(R.id.button_back);
        button_newentry = (Button) findViewById(R.id.button_addentry);
        Collections.sort(names);
        button_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(names);
                listView.setAdapter(new CustomAdaptr(getApplicationContext(),names));
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act("menu");
            }
        });

        button_newentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act("newentry");
            }
        });




    }

    private void act(String type) {
        Intent intent = new Intent();
        switch (type) {
            case "menu":
                intent.setClass(this, MenuActivity.class);
                break;

            case "newentry":
                intent.setClass(this, NewEntryActivity.class);
                break;
        }
        intent.putExtra("names", names);
        startActivity(intent);


    }
}