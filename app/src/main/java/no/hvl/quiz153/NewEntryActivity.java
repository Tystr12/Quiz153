package no.hvl.quiz153;

import static java.lang.Float.NaN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act("back");
            }
        });
        addPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.ty);
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToList();
            }
        });
    }
    private void addToList() {
        String text = acquireText();
        int pic = acquirePicture();
        if(!text.equals("") && pic != 0){
            this.names.add(new QuizEntry(text, pic));
        } else {
            // Bruker får en error
            Log.d("asddas", "ugyldig input");
        }

    }
    private int acquirePicture() {

        Integer p = (Integer) imageView.getTag();
        imageView.setImageResource(0);
        return (int) p;
    }
    private String acquireText() {
        String out = String.valueOf(text.getText());
        text.setText("");
        return out;
    }
    private void act(String type) {
        Intent intent = new Intent();
        switch (type) {
            case "back":
                intent.setClass(this, MenuActivity.class);
                break;
        }
        intent.putExtra("names",names);
        startActivity(intent);


    }

}