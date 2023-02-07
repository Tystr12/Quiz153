package no.hvl.quiz153;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.Collections;



public class DatabaseActivity extends AppCompatActivity {
    ArrayList<String> names = new ArrayList<>();

    ListView listView;

    Button button_sort;
    Button button_back;

    //Button button_newentry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        listView = (ListView) findViewById(R.id.my_list);
        this.names = getIntent().getStringArrayListExtra("names");
        CustomAdaptr customAdaptr = new CustomAdaptr(getApplicationContext(),names);
        listView.setAdapter(customAdaptr);
        button_sort = (Button) findViewById(R.id.button_sort);
        button_back = (Button) findViewById(R.id.button_back);
        //button_newentry = (Button) findViewById(R.id.button_newentry);
        button_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(names);
                listView.setAdapter(new CustomAdaptr(getApplicationContext(),names));
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act("menu");
            }
        });




    }
    private void act(String type) {
        switch (type) {
            case "menu":
                Intent intent = new Intent(this, MenuActivity.class).putExtra("names", names);
                startActivity(intent);

            case "newentry":
            //    Intent intent = new Intent(this, )
        }


    }
}