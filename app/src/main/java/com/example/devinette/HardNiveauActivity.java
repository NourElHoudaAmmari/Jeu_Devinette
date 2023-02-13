package com.example.devinette;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class HardNiveauActivity extends AppCompatActivity {
    int count = 0;
    TextView nameValue1,textScore1;
    Button newbtn1;
    int result;
    TextView countdown_text1,scor1;
    CountDownTimer myCountDownTimer;
    int score =0;
    int timepass = 0;
    private SharedPreferences prefs;

    static int getRandomNumber(int max, int min)
    {
        return (int)((Math.random()
                * (max - min)) + min);
    }

    public void makeToast(String str)
    {
        Toast.makeText(
                        HardNiveauActivity.this,
                        str,
                        Toast.LENGTH_SHORT)
                .show();
    }
    public void clickFunction1(View view)
    {
        int userGuessing;
        EditText variable
                = (EditText)findViewById(
                R.id.editId1);
        userGuessing
                = Integer.parseInt(
                variable
                        .getText()
                        .toString());


        count++;
        if (userGuessing < result) {

            makeToast("Pensez à un nombre plus élevé, réessayez");
            variable.getText().clear();

        }
        else if (userGuessing > result) {
            makeToast("Pensez au nombre inférieur, réessayez");
            variable.getText().clear();

        }
        else {
            makeToast(
                    "Bravo!c'est gagné aprés  " + count +" tentatives");
            variable.setFocusable(false);
            myCountDownTimer.cancel();
            score= 100 - (count+timepass);
            String monIntString2=String.valueOf(score);
            scor1.setText(monIntString2);
            Intent intent = new Intent(HardNiveauActivity.this,ScoreActivity.class);
            String myScore=scor1.getText().toString();
            intent.putExtra("score",myScore);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_niveau);
        newbtn1=(Button)findViewById(R.id.newbtn1);
        newbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HardNiveauActivity.this,HardNiveauActivity.class);
                startActivity(intent);
            }
        });

        prefs=getSharedPreferences("MY_DATA",MODE_PRIVATE);
        nameValue1=(TextView)findViewById(R.id.nameValue1);
        String name =getIntent().getStringExtra("my_name");
        nameValue1.setText(name);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("my_name",name);
        int min = 1;
        int max = 1000;
        result = getRandomNumber(min, max);
        countdown_text1=findViewById(R.id.countdown_text1);
        scor1=(TextView) findViewById(R.id.scor1);
        long duration = TimeUnit.MINUTES.toMillis(1);
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration = String.format(Locale.FRANCE,"%02d : %02d"
                        ,TimeUnit.MILLISECONDS.toMinutes(l)
                        ,TimeUnit.MILLISECONDS.toSeconds(l)-
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                countdown_text1.setText(sDuration);
            }

            @Override
            public void onFinish() {
                countdown_text1.setVisibility(View.GONE);
                AlertDialog alertDialog = new AlertDialog.Builder(HardNiveauActivity.this).create();
                alertDialog.setTitle("Time Over");
                alertDialog.setMessage("Voulez-vous commencer une nouvelle partie?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Oui",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String name =getIntent().getStringExtra("my_name");
                                nameValue1.setText(name);
                                Intent intent = new Intent(HardNiveauActivity.this,MainActivity.class);
                                startActivity(intent);

                            }

                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Non",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(HardNiveauActivity.this,ScoreActivity.class);
                                startActivity(intent);
                            }
                        });
                alertDialog.show();

            }
        }.start();

    }
}