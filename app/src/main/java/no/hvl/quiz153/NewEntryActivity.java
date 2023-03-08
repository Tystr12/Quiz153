package no.hvl.quiz153;

import static java.lang.Float.NaN;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class NewEntryActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;

    private Button backButton;
    private ImageView imageView;
    private Button addPictureButton;
    private EditText inputEditText;
    private Button confirmButton;
    private Uri currentImage;

    private final int GALLERY_REQ_CODE = 200;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        Cursor cursor = getContentResolver().query(
                                data.getData(),
                                new String[]{MediaStore.Images.Media.DATA},
                                null,
                                null,
                                null
                        );
                        cursor.moveToFirst();
                        addImg(data.getData().normalizeScheme());
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);


        backButton = findViewById(R.id.button_back);
        imageView = findViewById(R.id.imageView_upload);
        addPictureButton = findViewById(R.id.button_addpicture);
        inputEditText = findViewById(R.id.input);
        confirmButton = findViewById(R.id.button_confirm);

        mainViewModel =new ViewModelProvider(this).get(MainViewModel.class);
        // Listener for back button.
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act("back");
            }
        });

        // Listener for add picture button.
        addPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                launcher.launch(intent);
            }
        });

        // Listener for confirm button.
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToList();
            }
        });
    }

    // Adds new entry to the "database" list.
    private void addToList() {
        String text = acquireText();
        Uri pic = acquirePicture();
        if (!text.isEmpty() && pic != null) {
            mainViewModel.insertQuizEntry(new QuizEntry(text, pic));
            act("back");
        } else {
            // User gets error
        }
    }

    // Displays image on the imageview and saves ID.
    private void addImg(Uri uri) {
        imageView.setImageURI(uri);
        currentImage = uri;
    }

    // Gets image from imageview and clears the imageview.
    private Uri acquirePicture() {
        Uri out = currentImage;
        currentImage = null;
        imageView.setImageResource(0);
        return out;
    }

    // Gets text from edit text view and clears.
    private String acquireText() {
        String out = String.valueOf(inputEditText.getText());
        inputEditText.setText("");
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
        startActivity(intent);
    }
    public ActivityResultLauncher<Intent> getLauncher() {
        return launcher;
    }
}