package com.example.jacky.questionappmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class IntroductionActivity extends AppCompatActivity {
    private Button btnPlay;
    private Button btnHighscore;
    private Button btnAbout;
    private TextView lblHighscore;
    private int highscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "LV5RUhrx07WXKsaNFOoSzecTi9v4b7pQrlCspYct", "DrZfXs0W2um48mrVRJ1niAZPBKqaPFLKdMrsaBXU");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        lblHighscore = (TextView)findViewById(R.id.lblHighscore);
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnHighscore = (Button) findViewById(R.id.btnHighscore);
        btnAbout = (Button) findViewById(R.id.btnAbout);

        Paper.init(this);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, MainActivity.class);
                startActivity(i);

            }

        });


        btnHighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, HighScoreActivity.class);
                startActivity(i);
            }
        });


        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

        highscore = 0;

        // if no highscores are found, set the value to 0
        if (highScores.size() == 0) {

            lblHighscore.setText("High Score: 0");
        } else {
            lblHighscore.setText("High Score: " + highScores.get(0).getName() + (" - ") + highScores.get(0).getScore());
        }
    }
}
