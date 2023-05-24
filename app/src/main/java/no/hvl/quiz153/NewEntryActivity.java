package no.hvl.quiz153;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class NewEntryActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;

    private Button backButton;
    private ImageView imageView;
    private Button addPictureButton;
    private EditText inputEditText;
    private Button confirmButton;
    public Uri currentImage;

    private final int GALLERY_REQ_CODE = 200;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() != RESULT_OK || result.getData() == null) return;
                        currentImage = result.getData().getData();
                        getContentResolver().takePersistableUriPermission(currentImage, FLAG_GRANT_READ_URI_PERMISSION);
                    imageView.setImageURI(currentImage);
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
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                launcher.launch(intent);
            }
        });

        // Listener for confirm button.
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // addToList();
                QuizEntry quizEntry = new QuizEntry(inputEditText.getText().toString(),currentImage);
                mainViewModel.insertQuizEntry(quizEntry);
                act("back");

            }
        });
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
}