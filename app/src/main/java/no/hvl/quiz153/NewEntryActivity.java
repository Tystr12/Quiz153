package no.hvl.quiz153;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class NewEntryActivity extends AppCompatActivity {

    ArrayList<QuizEntry> names = new ArrayList<>();

    Button backButton;
    ImageView imageView;
    Button addPictureButton;
    EditText text;
    Button confirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        names =  (ArrayList<QuizEntry>) getIntent().getSerializableExtra("names");
        backButton = (Button) findViewById(R.id.button_back);
        imageView = (ImageView) findViewById(R.id.imageView_upload);
        addPictureButton = (Button) findViewById(R.id.button_addpicture);
        text = (EditText) findViewById(R.id.input);
        confirmButton = (Button) findViewById(R.id.button_confirm);
    }
}