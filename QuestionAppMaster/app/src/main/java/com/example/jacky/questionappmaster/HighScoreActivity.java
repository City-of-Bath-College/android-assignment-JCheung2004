package com.example.jacky.questionappmaster;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class HighScoreActivity extends AppCompatActivity {
    private ListView listview;

    private List<HighScoreObject> highscores;

    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        listview = (ListView) findViewById(R.id.listView);

        Paper.init(this);

        highscores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

        HighscoreAdapter adapter = new HighscoreAdapter(highscores);

        listview.setAdapter(adapter);
        btnReset = (Button) findViewById(R.id.btnReset); // Reset Button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().delete("highscores"); // resets highscores
                setContentView(R.layout.activity_high_score);
            }
        });

    }
        private class HighscoreAdapter extends ArrayAdapter<HighScoreObject> {

        public HighscoreAdapter(List<HighScoreObject> items) {
            super(HighScoreActivity.this, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.row_highscore, null);
            }

            //get the highscore object for the row we're looking at
            HighScoreObject highscore = highscores.get(position);
            Date date = new Date(highscore.getTimestamp());
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");
            TextView lblTitle = (TextView)convertView.findViewById(R.id.lblTitle);
            lblTitle.setText(highscore.getScore() + " - " + highscore.getName() + " - " + fmtOut.format(date));
            return convertView;
        }// end get view

    }// end adapter class


}
