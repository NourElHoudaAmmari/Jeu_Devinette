package com.example.devinette;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class JouerActivity extends AppCompatActivity {
int count = 0;
TextView nameValue,textScore,voirscore;
Button newbtn;
    int result;
TextView countdown_text,scor;
CountDownTimer myCountDownTimer;
int score =0;
DataBaseManager dataBaseManager;

int timepass = 0;
private  SharedPreferences prefs;


    private ListView list_id;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> lishh= new ArrayList<>();

    static int getRandomNumber(int max, int min)
    {
        return (int)((Math.random()
                * (max - min)) + min);
    }

    public void makeToast(String str)
    {
        Toast.makeText(
                        JouerActivity.this,
                        str,
                        Toast.LENGTH_SHORT)
                .show();
    }
    public void clickFunction(View view)
    {
        int userGuessing;
        EditText variable
                = (EditText)findViewById(
                R.id.editId);
        userGuessing
                = Integer.parseInt(
                variable
                        .getText()
                        .toString());


        count++;
        if (userGuessing < result) {

            makeToast("Pensez à un nombre plus élevé, réessayez");
            variable.getText().clear();
            String str =String.valueOf(count);
            lishh.add(str+"=="+userGuessing);
        }
        else if (userGuessing > result) {
            makeToast("Pensez au nombre inférieur, réessayez");
            variable.getText().clear();
            String str =String.valueOf(count);
            lishh.add(str+"=="+userGuessing);
        }
        else {
            makeToast(
                    "Bravo!c'est gagné aprés  " + count +" tentatives");
            variable.setFocusable(false);
            countdown_text.setVisibility(View.GONE);
            score= 100 - (count+timepass);
            String monIntString2=String.valueOf(score);
            scor.setText(monIntString2);
        }
        String str =String.valueOf(count);
        lishh.add(str+"=="+userGuessing);
    }
    public void saveScore(View view){
        String name = String.valueOf(nameValue.getText());
        String mscore = String.valueOf(scor.getText());
        int idScore = Score.scoreArrayList.size();
        Score newScore = new Score(idScore,name,mscore);
        Score.scoreArrayList.add(newScore);
        dataBaseManager.insertScore(name,score);
        finish();
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(
            Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouer);
        voirscore=(TextView)findViewById(R.id.voirscore);
       voirscore.setPaintFlags(voirscore.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
       voirscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JouerActivity.this,ScoreActivity.class);
                startActivity(intent);
            }
        });

        textScore=(TextView)findViewById(R.id.textScore);
      textScore.setPaintFlags(textScore.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JouerActivity.this,ScoreActivity.class);
                startActivity(intent);
            }
        });
        newbtn=(Button)findViewById(R.id.newbtn);
        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JouerActivity.this,JouerActivity.class);
                startActivity(intent);
            }
        });
        list_id=(ListView) findViewById(R.id.list_id);
        prefs=getSharedPreferences("MY_DATA",MODE_PRIVATE);
        nameValue=(TextView)findViewById(R.id.nameValue);
        String name =getIntent().getStringExtra("my_name");
        nameValue.setText(name);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("my_name",name);
        arrayAdapter = new ArrayAdapter<String>(
                JouerActivity.this, android.R.layout.simple_expandable_list_item_1,lishh
        );
        list_id.setAdapter(arrayAdapter);

        int min = 1;
        int max = 100;
        result = getRandomNumber(min, max);
        countdown_text=findViewById(R.id.countdown_text);
        scor=(TextView) findViewById(R.id.scor);
        long duration = TimeUnit.MINUTES.toMillis(1);
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration = String.format(Locale.FRANCE,"%02d : %02d"
                ,TimeUnit.MILLISECONDS.toMinutes(l)
                        ,TimeUnit.MILLISECONDS.toSeconds(l)-
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                countdown_text.setText(sDuration);
            }

            @Override
            public void onFinish() {
                countdown_text.setVisibility(View.GONE);
                AlertDialog alertDialog = new AlertDialog.Builder(JouerActivity.this).create();
                alertDialog.setTitle("Time Over");
                alertDialog.setMessage("Voulez-vous commencer une nouvelle partie?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Oui",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String name =getIntent().getStringExtra("my_name");
                                nameValue.setText(name);
                                Intent intent = new Intent(JouerActivity.this,MainActivity.class);
                                startActivity(intent);

                            }

                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Non",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(JouerActivity.this,"Merci pour votre participation",Toast.LENGTH_SHORT).show();
                            }
                        });
                alertDialog.show();

            }
        }.start();

    }
}