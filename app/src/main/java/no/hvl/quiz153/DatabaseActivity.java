package no.hvl.quiz153;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.Collections;

//This class provides a visual of all elements in the database

public class DatabaseActivity extends AppCompatActivity {
    ArrayList<QuizEntry> names = new ArrayList<>();

    MainViewModel mViewModel;

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
        button_sort = (Button) findViewById(R.id.button_sort);
        button_back = (Button) findViewById(R.id.button_back);
        button_newentry = (Button) findViewById(R.id.button_addentry);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getAllQuizEntrys().observe(this, quizEntries -> {
            // Add the new data to the names ArrayList
            CustomAdaptr customAdaptr = new CustomAdaptr(getApplicationContext(), mViewModel,this);
            listView.setAdapter(customAdaptr);

        });
        button_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(new CustomAdaptr(getApplicationContext(), mViewModel,DatabaseActivity.this));
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
        startActivity(intent);


    }
}