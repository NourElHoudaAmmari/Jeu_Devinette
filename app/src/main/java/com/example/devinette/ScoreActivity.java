package com.example.devinette;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {
private TextView scoresView;
private DataBaseManager databaseManager;
private ListView scoreListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoresView=(TextView) findViewById(R.id.scoresView);
       databaseManager = new DataBaseManager(this);
      // databaseManager.insertScore("asma",55);
      // databaseManager.insertScore("ahmed",10);
       // databaseManager.insertScore("aymen",60);
        //databaseManager.insertScore("nour",70);
        List<ScoreData> scores = databaseManager.readTop10();
        for (ScoreData score : scores){
            scoresView.append(score.toString()+"\n\n");
        }
databaseManager.close();
    }








}