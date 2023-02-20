package no.hvl.quiz153;

import static java.lang.Float.NaN;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
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
    int curr_image;
    private final int GALLERY_REQ_CODE = 200;


    //Not used yet
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        Cursor cursor = getContentResolver().query(data.getData(), new String[]{MediaStore.Images.Media.DATA},null, null, null);
                        cursor.moveToFirst();
                        imageView.setImageURI(data.getData());
                    }
                }
            }

    );
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

        //Listener for back button.
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act("back");
            }
        });
    //    addPictureButton.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View v) {
    //            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    //            launcher.launch(intent);
    //        }
    //    });

        //Listener for confirm button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImg(R.drawable.ty);
                addToList();
            }
        });
    }

//adds new entry to the "database" list
    private void addToList() {
        String text = acquireText();
        int pic = acquirePicture();
        if(!text.equals("") && pic != 0){
            this.names.add(new QuizEntry(text, pic));
            act("back");
        } else {
            // User gets error
        }

    }

    //displays image on the imageview and saves id
    private void addImg(int i) {
        imageView.setImageResource(i);
        curr_image = i;
    }
    //get image from Imageview and clear the imageview
    private int acquirePicture() {
        int out = curr_image;
        curr_image = 0;
        imageView.setImageResource(0);
        return out;
    }

    //get text from editText view and clear
    private String acquireText() {
        String out = String.valueOf(text.getText());
        text.setText("");
        return out;
    }

    //method for creating intent and starting new activity
    private void act(String type) {
        Intent intent = new Intent();
        switch (type) {
            case "back":
                intent.setClass(this, DatabaseActivity.class);

                break;
            }
        intent.putExtra("names",names);
        startActivity(intent);
    }


    public ActivityResultLauncher<Intent> getLauncher() {
        return launcher;
    }
}