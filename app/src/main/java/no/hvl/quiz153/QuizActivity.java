package no.hvl.quiz153;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//This class shows the images, and updates the score if the user guess correctly
public class QuizActivity extends AppCompatActivity {


    MainViewModel mainViewModel;
    int score;
    int total;
    ArrayList<QuizEntry> names = new ArrayList<>();
    ImageView imageView;
    TextView textView;
    Button button_back;

    ArrayList<Button> button_list = new ArrayList<>();
    QuizEntry curr_answer;

    ArrayList<QuizEntry> curr_wrongs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        imageView = (ImageView) findViewById(R.id.image_answer);
        textView = (TextView) findViewById(R.id.text_score);
        button_back = (Button) findViewById(R.id.button_back);
        button_list.add(findViewById(R.id.button_answer1));
        button_list.add(findViewById(R.id.button_answer2));
        button_list.add(findViewById(R.id.button_answer3));
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getAllQuizEntrys().observe(this, quizEntries -> {
            // Add the new data to the names ArrayList
            names.clear();
            names.addAll(quizEntries);
            setAnswers();
            List<ScoreEntity> scores = mainViewModel.getAllScores().getValue();
            if (scores == null || scores.isEmpty()) {
                mainViewModel.insertScore(new ScoreEntity(curr_wrongs,curr_answer));
            }
        });



        mainViewModel.getAllScores().observe(this, scoreEntities -> {
            if (!scoreEntities.isEmpty()){
                score = scoreEntities.get(0).getScore();
                total = scoreEntities.get(0).getTotal();
            }

            setScore();
        });
        button_list.forEach((x) -> x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(x.getText().toString());
            }
        }));
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act("back");
            }
        });
    }




    private void act(String type) {
        Intent intent = new Intent();
        switch (type) {
            case "back":
                intent.setClass(this, MenuActivity.class);
                break;
        }
        startActivity(intent);


    }


    public ArrayList<QuizEntry> getRandomSublist(ArrayList<QuizEntry> list) {
        ArrayList<QuizEntry> subList = new ArrayList<>();
        Random random = new Random();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(list.size());
            subList.add(list.get(randomIndex));
            list.remove(randomIndex);
        }
        curr_answer = subList.remove(0);
        return subList;
    }


    public void setAnswers() {
        ArrayList<Button> button_copy = (ArrayList<Button>) button_list.clone();
        Random random = new Random();
        curr_wrongs.clear();
        curr_wrongs = getRandomSublist((ArrayList<QuizEntry>)names.clone());
        button_copy.remove(random.nextInt(3)).setText(curr_answer.getText());
        button_copy.remove(0).setText(curr_wrongs.get(0).getText());
        button_copy.remove(0).setText(curr_wrongs.get(1).getText());
        imageView.setImageURI(curr_answer.getImg());
    }

    private void checkAnswer(String text) {
        if (text.equals(curr_answer.getText())) {
            mainViewModel.updateScore();
        }
        mainViewModel.updateTotal();

        setAnswers();
    }

    @SuppressLint("SetTextI18n")
    private void setScore() {
        textView.setText(score + " / " + total);
    }



}