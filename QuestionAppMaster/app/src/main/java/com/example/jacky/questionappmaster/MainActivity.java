package com.example.jacky.questionappmaster;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnTrue;
    private Button btnFalse;
    private TextView lblQuestion;
    private ImageView imgPicture;

    private List<QuestionObject> questions;
    private QuestionObject currentQuestion;
    private int index;
    private int score;
    private boolean expectedAnswer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);

        lblQuestion.setText("Is my name Jacky?");

        imgPicture.setImageResource(R.drawable.emblem);

        index = 0;
        score = 0;

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "wrong!!", Toast.LENGTH_SHORT).show();
            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "true!!", Toast.LENGTH_SHORT).show();
            }
        });

        generateQuestions();

        setUpQuestion();


    }

        private void generateQuestions() {
        questions = new ArrayList<>();
            questions.add (new QuestionObject("Is the capital of England, London?", true, R.drawable.england));
            questions.add (new QuestionObject("Is the capital of France, Paris?", true, R.drawable.england));
            questions.add(new QuestionObject("Is tokyo in China?", false, R.drawable.england));
            questions.add(new QuestionObject("Are indian people asian?", true, R.drawable.england));
            questions.add (new QuestionObject("Is the capital of Canada, Toronto?", false, R.drawable.england));
            questions.add(new QuestionObject("Is Australia the same country as New Zealand?", false, R.drawable.england));
            questions.add(new QuestionObject("Is the capital of Germany, Berlin?", false, R.drawable.england));
            questions.add(new QuestionObject("Is the capital of England London?", true, R.drawable.england));
            questions.add(new QuestionObject("Is 5x2 = 10?", true, R.drawable.england));
            questions.add(new QuestionObject("Is the capital of China, Hong Kong?", true, R.drawable.england));



        }

    private void setUpQuestion(){
        currentQuestion = questions.get(index);

        lblQuestion.setText(currentQuestion.getQuestion());
        imgPicture.setImageResource(currentQuestion.getPicture());

        index++;
    }

    private void determineButtonPress (boolean answer){
        boolean expectedAnswer = currentQuestion.isAnswer();

        if (answer == expectedAnswer) {
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
            score++;
        }
        setUpQuestion();
    }

    }
