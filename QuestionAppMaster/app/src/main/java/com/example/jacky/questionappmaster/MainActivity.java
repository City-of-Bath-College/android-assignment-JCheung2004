package com.example.jacky.questionappmaster;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button btnTrue;
    private Button btnFalse;
    private TextView lblQuestion;
    private ImageView imgPicture;
    private String username ="";

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

        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);

        lblQuestion.setText("Loading");

        imgPicture.setImageResource(R.drawable.emblem);

        index = 0;
        score = 0;

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(false);
            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(true);
            }
        });

        generateQuestions();

        setUpQuestion();

        Paper.init(this);

    }

    private void generateQuestions() {
        questions = new ArrayList<>();
        questions.add(new QuestionObject("0NXGiexSx0", R.drawable.england));
        questions.add(new QuestionObject("gWrrkXmchy", R.drawable.japan));
        questions.add(new QuestionObject("A5xX0MNhvA", R.drawable.australia));
        questions.add(new QuestionObject("DeWaTzMz8r", R.drawable.maths));
        questions.add(new QuestionObject("cV6F0zwAP9", R.drawable.china));
        questions.add(new QuestionObject("9UbNo2GWj9", R.drawable.indian));
        questions.add(new QuestionObject("W3Dc6b443W", R.drawable.canada));
        questions.add(new QuestionObject("gq3VAAjegJ", R.drawable.kenya));
        questions.add(new QuestionObject("P2d1wy0W1y", R.drawable.germany));
        questions.add(new QuestionObject("hBtucyLHLy", R.drawable.earth));
    }
    private void setUpQuestion() {
        if (index == questions.size()) {
            endgame();
        } else {
            currentQuestion = questions.get(index);

            ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
            query.getInBackground(currentQuestion.getQuestion(), new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {

                        String NewQuestion = object.getString("question");
                        lblQuestion.setText(NewQuestion);
                        expectedAnswer = object.getBoolean("answer");
                        imgPicture.setImageResource(currentQuestion.getPicture());
                        index++;
                    } else {
                    }
                }
            });
        }
    }

    private void determineButtonPress(boolean answer) {

        if (answer == expectedAnswer) {
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(MainActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();

        }
        setUpQuestion();
    }

    private void endgame() { // Inspired from: http://stackoverflow.com/questions/11585099/alertdialog-show-new-alertdialog-builderthis-is-undefined

        AlertDialog.Builder builder = new AlertDialog.Builder(this); // declares builder which is going to be the prompt for users to type in their high scores
        builder.setTitle("Enter your name here:");

// Set up the input
        final EditText input = new EditText(MainActivity.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);
        builder.setCancelable(false);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                username = input.getText().toString(); // user types in their name here

                // new high score!
                HighScoreObject highScore = new HighScoreObject(score, username, new Date().getTime());

                // get user prefs
                List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

                // add item
                highScores.add(highScore);

                // this is ordering the highscores from highest to lowest
                Collections.sort(highScores, new Comparator<HighScoreObject>() {
                    public int compare(HighScoreObject a, HighScoreObject b) {

                        if (a.getScore() < b.getScore()) {
                            return 1;
                        } else if (a.getScore() > b.getScore()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });


                // save again
                Paper.book().write("highscores", highScores); // saving the highscore then the name the user put in

                finish();


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // takes you back to main menu
            }
        });
        builder.show();
    }
}



